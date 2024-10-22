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

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FAQTest {
    private WebDriver driver;

    // Параметры для теста
    private final String question;
    private final String expectedAnswer;

    // Конструктор для параметров
    public FAQTest(String question, String expectedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    // Параметры для разных наборов данных
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                // Здесь можно добавить больше вопросов и ожидаемых ответов
        });
    }

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();  // Открываем окно на весь экран
    }

    // Тест для проверки открытия ответа на вопрос в FAQ
    @Test
    public void testFAQAnswer() {
        driver.get("https://qa-scooter.praktikum-services.ru/");  // Открываем сайт
        HomePage homePage = new HomePage(driver);  // Инициализируем объект страницы

        homePage.acceptCookies();  // Закрываем баннер с куки
        homePage.scrollToFAQAndExpand();  // Прокручиваем до FAQ и открываем ответ

        // Проверяем, что ответ отображается
        assertTrue("Ответ на вопрос не отображается", homePage.isFAQAnswerDisplayed());  // Убедитесь, что метод работает без параметров

        // Получаем текст ответа и проверяем его соответствие с ожидаемым
        String actualAnswer = homePage.getFAQAnswerText();  // Используем метод без параметров
        assertEquals("Ответ не соответствует ожидаемому", expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
