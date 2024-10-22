package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    private WebDriver driver;

    // Локатор для кнопки "Заказать"
    private final By orderButton = By.xpath("//button[text()='Заказать']");  // Пример локатора для кнопки "Заказать"
    private final By faqQuestion = By.xpath("//div[text()='Сколько это стоит? И как оплатить?']");
    private final By faqAnswer = By.xpath("//div[@class='accordion__panel' and text()='Сутки — 400 рублей. Оплата курьеру — наличными или картой.']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для закрытия баннера с куки
    public void acceptCookies() {
        // Логика закрытия баннера с куки (если необходима)
    }

    // Метод для прокрутки до раздела FAQ и открытия ответа на вопрос
    public void scrollToFAQAndExpand() {
        WebElement questionElement = driver.findElement(faqQuestion);
        Actions actions = new Actions(driver);
        actions.moveToElement(questionElement).perform();  // Прокрутка до элемента
        questionElement.click();  // Открытие ответа на вопрос
    }

    // Метод для проверки отображения ответа на вопрос
    public boolean isFAQAnswerDisplayed() {
        return driver.findElement(faqAnswer).isDisplayed();  // Проверка отображения элемента ответа
    }

    // Метод для получения текста ответа на вопрос
    public String getFAQAnswerText() {
        return driver.findElement(faqAnswer).getText();  // Получение текста ответа
    }

    // Метод для клика по кнопке "Заказать"
    public void clickOrderButton() {
        WebElement orderBtn = driver.findElement(orderButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(orderBtn).click().perform();  // Прокрутка и клик по кнопке "Заказать"
    }
}
