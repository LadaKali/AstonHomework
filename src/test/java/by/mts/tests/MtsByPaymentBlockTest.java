package by.mts.tests;

import by.mts.pages.OnlinePaymentPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MtsByPaymentBlockTest {
    private static final Logger logger = LoggerFactory.getLogger(MtsByPaymentBlockTest.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private OnlinePaymentPage paymentPage;

    public static class TestData {
        public static final String BASE_URL = "https://www.mts.by/";
        public static final String BLOCK_TITLE = "Онлайн пополнение без комиссии";
        public static final String PHONE_NUMBER = "297777777";
        public static final String ACCOUNT_NUMBER_INTERNET = "1234567890";
        public static final String ACCOUNT_NUMBER_INSTALLMENT = "123456789";
        public static final String ACCOUNT_NUMBER_DEBT = "123456789";
        public static final String AMOUNT = "30.00";
        public static final int EXPECTED_LOGO_COUNT = 5;
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        paymentPage = new OnlinePaymentPage(driver, wait);
        driver.get(TestData.BASE_URL);
        OnlinePaymentPage.handleCookiePopup();
        try {
            WebElement overlay = driver.findElement(By.cssSelector(".modal-overlay, .popup, .banner"));
            if (overlay.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", overlay);
                logger.info("Закрыт оверлей, перекрывающий страницу");
            }
        } catch (Exception e) {
            logger.info("Оверлей не найден или не отображается");
        }
    }

    @Test
    public void testPaymentBlock() {
        logger.info("Проверка заголовка блока");
        assertEquals(TestData.BLOCK_TITLE, paymentPage.getBlockTitle(), "Заголовок блока не соответствует");

        logger.info("Проверка количества иконок платёжных систем");
        assertEquals(TestData.EXPECTED_LOGO_COUNT, paymentPage.getPaymentLogosCount(),
                "Неверное количество иконок платёжных систем");

        logger.info("Проверка ссылки 'Подробнее о сервисе'");
        paymentPage.clickDetailsLink();
        wait.until(ExpectedConditions.urlContains("help"));
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("help"), "URL не содержит 'help'");
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(normalize-space(.), 'Онлайн пополнение')]")));
        logger.info("Возвращены на страницу с блоком");

        testPaymentOption("Услуги связи", "Номер телефона", TestData.PHONE_NUMBER);
        testPaymentOption("Домашний интернет", "Номер абонента", TestData.ACCOUNT_NUMBER_INTERNET);
        testPaymentOption("Рассрочка", "Номер счета на 44", TestData.ACCOUNT_NUMBER_INSTALLMENT);
        testPaymentOption("Задолженность", "Номер счета на 2073", TestData.ACCOUNT_NUMBER_DEBT);
    }

    private void testPaymentOption(String option, String expectedIdentifierPlaceholder, String identifier) {
        logger.info("Тестирование вкладки: {}", option);
        paymentPage.selectPaymentOption(option);

        assertEquals(expectedIdentifierPlaceholder, paymentPage.getIdentifierFieldPlaceholder(option),
                "Неверный placeholder для идентификатора в " + option);
        assertEquals("Сумма", paymentPage.getAmountFieldPlaceholder(option),
                "Неверный placeholder для суммы в " + option);
        logger.info("Проверены placeholder'ы полей для {}", option);

        if (option.equals("Услуги связи")) {
            paymentPage.enterPaymentDetails(option, identifier, TestData.AMOUNT);
            paymentPage.clickContinue();
            assertTrue(paymentPage.isPopupDisplayed(), "Всплывающее окно не отображается для " + option);
            assertEquals(TestData.AMOUNT, paymentPage.getPopupAmount(),
                    "Сумма во всплывающем окне не соответствует для " + option);
            assertEquals(TestData.AMOUNT, paymentPage.getPopupButtonAmount(),
                    "Сумма на кнопке во всплывающем окне не соответствует для " + option);
            assertEquals(identifier, paymentPage.getPopupIdentifier(),
                    "Идентификатор во всплывающем окне не соответствует для " + option);
            logger.info("Всплывающее окно проверено для {}", option);
            paymentPage.closePopup();
        } else if(option.equals("Домашний интернет")) {
            logger.info("Домашний интернет!!!");
            logger.info("Плейсхолдер поля "+expectedIdentifierPlaceholder+": " + paymentPage.getIdentifierFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Сумма: " + paymentPage.getAmountFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Email: " + paymentPage.getEmailFieldPlaceholder(option));


        } else if(option.equals("Рассрочка")){
            logger.info("Рассрочка!!!");
            logger.info("Плейсхолдер поля "+expectedIdentifierPlaceholder+": " + paymentPage.getIdentifierFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Сумма: " + paymentPage.getAmountFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Email: " + paymentPage.getEmailFieldPlaceholder(option));


        } else if (option.equals("Задолженность")) {
            logger.info("Задолженность!!!");
            logger.info("Плейсхолдер поля "+expectedIdentifierPlaceholder+": " + paymentPage.getIdentifierFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Сумма: " + paymentPage.getAmountFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Email: " + paymentPage.getEmailFieldPlaceholder(option));

        }
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Браузер закрыт");
        }
    }
}


