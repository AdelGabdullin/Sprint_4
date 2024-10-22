package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;
import pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    private WebDriver driver;

    // Параметры для теста
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    private final String comment;

    // Конструктор для параметров
    public ScooterOrderTest(String name, String surname, String address, String phone, String date, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    // Параметры для разных наборов данных
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                { "Адель", "Габдуллин", "Улица Пушкина 10", "89811510666", "20.10.2024", "Первый заказ" },
                { "Иван", "Иванов", "Ленина 5", "89111112233", "22.10.2024", "Второй заказ" },
                { "Анна", "Смирнова", "Красная площадь", "89031234567", "25.10.2024", "Третий заказ" }
        });
    }

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    // Тест для оформления заказа с параметрами
    @Test
    public void testOrderFlow() {
        driver.get("https://qa-scooter.praktikum-services.ru/");  // Открываем сайт
        HomePage homePage = new HomePage(driver);  // Инициализируем объект страницы

        homePage.acceptCookies();  // Закрываем баннер с куки
        homePage.clickOrderButton();  // Нажимаем на кнопку "Заказать"

        OrderPage orderPage = new OrderPage(driver);  // Инициализируем объект страницы заказа
        // Используем параметры для заполнения формы
        orderPage.fillOrderForm(name, surname, address, phone, date, comment);
        orderPage.selectRentalDuration();  // Выбираем срок аренды
        orderPage.chooseColor();  // Выбираем цвет самоката
        orderPage.submitFinalOrder();  // Отправляем заказ
        orderPage.confirmOrder();  // Подтверждаем заказ

        // Проверяем, что заказ успешно оформлен
        assertTrue("Сообщение о том, что заказ оформлен, не появилось", orderPage.isOrderSuccessDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

