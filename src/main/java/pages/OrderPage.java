package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

public class OrderPage {
    private WebDriver driver;

    // Локаторы для элементов формы заказа
    private By nameField = By.xpath("//div[@class='Order_Form__17u6u']/div[1]/input");  // Поле ввода имени
    private By surnameField = By.xpath("//div[@class='Order_Form__17u6u']/div[2]/input");  // Поле ввода фамилии
    private By addressField = By.xpath("//div[@class='Order_Form__17u6u']/div[3]/input");  // Поле ввода адреса
    private By metroDropdown = By.xpath("//input[@class='select-search__input']");  // Выпадающий список метро
    private By stationLocator = By.xpath("//div[text()='Черкизовская']");  // Конкретная станция метро
    private By phoneField = By.xpath("//div[5]/input");  // Поле ввода номера телефона
    private By orderSubmitButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");  // Кнопка для отправки заказа

    // Локатор для выбора даты аренды
    private By dateInput = By.xpath("//div[@class='react-datepicker__input-container']/input");

    // Локаторы для выбора срока аренды
    private By rentalDurationDropdown = By.xpath("//div[@class='Dropdown-control']");
    private By rentalDurationOption = By.xpath("//div[@class='Dropdown-menu']/div[.='двое суток']");

    // Локатор для чекбокса с выбором цвета
    private By colorCheckbox = By.xpath("//input[@id='black']");

    // Локатор для поля комментария
    private By commentField = By.xpath("//div[4]/input");

    // Локатор для финальной кнопки заказа
    private By finalOrderSubmitButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор для подтверждения заказа
    private By confirmYesButton = By.xpath("//button[contains(text(),'Да')]");

    // Локатор для сообщения об успешном заказе
    private By orderSuccessMessage = By.xpath("//div[contains(text(),'Заказ оформлен')]");

    // Конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для заполнения полей заказа
    public void fillOrderForm(String name, String surname, String address, String phone, String date, String comment) {
        driver.findElement(nameField).sendKeys(name);  // Вводим имя
        driver.findElement(surnameField).sendKeys(surname);  // Вводим фамилию
        driver.findElement(addressField).sendKeys(address);  // Вводим адрес
        driver.findElement(metroDropdown).click();  // Открываем выпадающий список метро
        driver.findElement(stationLocator).click();  // Выбираем станцию
        driver.findElement(phoneField).sendKeys(phone);  // Вводим телефон
        driver.findElement(orderSubmitButton).click();  // Нажимаем кнопку "Заказать"

        WebElement dateElement = driver.findElement(dateInput);  // Выбираем поле даты
        dateElement.clear();  // Очищаем его
        dateElement.sendKeys(date);  // Вводим дату
        dateElement.sendKeys(Keys.ENTER);  // Подтверждаем выбор даты
    }

    // Метод для выбора срока аренды
    public void selectRentalDuration() {
        driver.findElement(rentalDurationDropdown).click();  // Открываем выпадающий список
        driver.findElement(rentalDurationOption).click();  // Выбираем срок аренды
    }

    // Метод для выбора цвета
    public void chooseColor() {
        driver.findElement(colorCheckbox).click();  // Нажимаем на чекбокс
    }

    // Метод для ввода комментария
    public void submitComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);  // Вводим комментарий
    }

    // Метод для финальной отправки заказа
    public void submitFinalOrder() {
        driver.findElement(finalOrderSubmitButton).click();  // Нажимаем кнопку "Заказать"
    }

    // Метод для подтверждения заказа
    public void confirmOrder() {
        driver.findElement(confirmYesButton).click();  // Подтверждаем заказ
    }

    // Метод проверки успешного завершения заказа
    public boolean isOrderSuccessDisplayed() {
        return driver.findElement(orderSuccessMessage).isDisplayed();
    }
}
