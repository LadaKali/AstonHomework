import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MtsByPaymentBlockTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        // Обработка окна cookie

        try {
            By acceptCookieButtonLocator = By.cssSelector("#cookie-agree");
            WebElement acceptCookieButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButtonLocator));
            String buttonText = acceptCookieButton.getText();
            if (buttonText.contains("Принять")) {
                acceptCookieButton.click();
                // Дожидаемся исчезновения окна cookie
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(".cookie__wrapper")));
                System.out.println("Окно cookie успешно закрыто (кнопка 'Принять')");
            } else {
                System.out.println("Кнопка 'Приянть' не найдена, текст кнопки: " + buttonText);
            }
        } catch (Exception e) {
            System.out.println("Окно cookie не найдено или уже закрыто: " + e.getMessage());
        }

    }

    @Test
    public void testOnlinePaymentBlock() {
        // Проверка заголовка блока
        By blockTitleLocator = By.xpath("//h2[contains(normalize-space(.), 'Онлайн пополнение ')]");
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitleLocator));
        String actualText = blockTitle.getText().replaceAll("\\s+", " ").trim(); // Удаляет переносы строк и лишние пробелы
        System.out.println("Найденный текст заголовка: '" + actualText + "'"); // Отладка
        assertEquals("Онлайн пополнение без комиссии", actualText, "Название блока не соответствует ожидаемому");

        // Проверка логотипов платежных систем
        By paymentLogosLocator = By.cssSelector(".pay__partners img");
        List<WebElement> paymentLogos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(paymentLogosLocator));
        System.out.println("Найдено логотипов: " + paymentLogos.size()); // Отладка
        for (WebElement logo : paymentLogos) {
            String src = logo.getAttribute("src");
            System.out.println("Логотип src: " + src); // Отладка
            wait.until(ExpectedConditions.attributeToBeNotEmpty(logo, "src")); // Ожидание загрузки src
            assertTrue(!src.isEmpty() && src.contains("http"), "Логотип имеет некорректный src: " + src);
        }

        // Проверка ссылки "Подробнее о сервисе"
        By detailsLinkLocator = By.xpath("//a[contains(text(), 'Подробнее о сервисе')]");
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(detailsLinkLocator));
        detailsLink.click();

        // Ожидание изменения URL
        wait.until(ExpectedConditions.urlContains("help"));
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("help"),
                "URL не содержит 'help', текущий URL: " + driver.getCurrentUrl());

        // Возврат к главной странице с блоком "Онлайн пополнение"
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitleLocator));
        System.out.println("Возвращено к блоку 'Онлайн пополнение'");


        // Ввод данных в поля и нажатие кнопки "Продолжить"
        By phoneFieldLocator = By.id("connection-phone");
        By amountFieldLocator = By.id("connection-sum");
        By continueButtonLocator = By.xpath("//button[contains(text(), 'Продолжить')]");

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneFieldLocator));
        phoneField.clear();
        phoneField.sendKeys("297777777");

        WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(amountFieldLocator));
        amountField.clear();
        amountField.sendKeys("300");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(continueButtonLocator));
        continueButton.click();

        By popupLocator = By.cssSelector(".body > app-root > div > div > div");
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
        assertTrue(popup.isDisplayed(), "Popup не отображается после нажатия кнопки 'Продолжить'");
        System.out.println("Popup успешно отображен");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}