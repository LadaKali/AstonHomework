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
import io.qameta.allure.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тестирование сайта mts.by")
@Feature("Проверка блока 'Онлайн пополнение без комиссии'")
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
        public static final String DETAILS_URL = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        public static final String DETAILS_PAGE_TITLE = "Оплата банковской картой";
    }

    @BeforeEach
    @Step("Инициализация браузера и открытие страницы")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        paymentPage = new OnlinePaymentPage(driver, wait);
        driver.get(TestData.BASE_URL);
        OnlinePaymentPage.handleCookiePopup();
    }

    @Test
    @Story("Проверка заголовка блока")
    @Description("Тест проверяет заголовок блока на соответствие")
    public void testPaymentBlockTitle() {
        assertEquals(TestData.BLOCK_TITLE, paymentPage.getBlockTitle(), "Заголовок блока не соответствует");
    }


    @Test
    @Story("Проверка логотипов платёжных систем")
    @Description("Проверка количества логотипов и их отображения")
    public void testPaymentLogos() {
        assertEquals(TestData.EXPECTED_LOGO_COUNT, paymentPage.getPaymentLogosCount(),
                "Неверное количество иконок платёжных систем");

        assertEquals(TestData.EXPECTED_LOGO_COUNT, paymentPage.getPaymentLogosCount());

        List<String> expectedLogos = List.of("Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт");
        List<WebElement> logoElements = paymentPage.getPaymentLogos();
        for (String logo : expectedLogos) {
            boolean logoFound = logoElements.stream()
                    .anyMatch(element -> element.getAttribute("alt").contains(logo) && element.isDisplayed());
            assertTrue(logoFound, "Логотип " + logo + " не найден или не отображается");
        }
    }

    @Test
    @Story("Проверка ссылка 'Подробнее о сервисе'")
    @Description("Проверка URL, заголовка и контента")
    public void testDetailsLink() {
        paymentPage.clickDetailsLink();

        wait.until(ExpectedConditions.urlToBe(TestData.DETAILS_URL));
        assertEquals(TestData.DETAILS_URL, driver.getCurrentUrl(), "URL не соответствует");

        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[contains(normalize-space(.), '" + TestData.DETAILS_PAGE_TITLE + "')]")));
        assertTrue(pageTitle.isDisplayed(), "Заголовок страницы не отображается");

        driver.navigate().back();


    }


    @Test
    @Story("Проверка полей и вкладок")
    @Description("Проверка плэйсхолдеров и ввода данных")
    public void testPaymentOptions() {
        processPaymentOption("Услуги связи", "Номер телефона", TestData.PHONE_NUMBER);
        processPaymentOption("Домашний интернет", "Номер абонента", TestData.ACCOUNT_NUMBER_INTERNET);
        processPaymentOption("Рассрочка", "Номер счета на 44", TestData.ACCOUNT_NUMBER_INSTALLMENT);
        processPaymentOption("Задолженность", "Номер счета на 2073", TestData.ACCOUNT_NUMBER_DEBT);

    }


    private void processPaymentOption(String option, String expectedIdentifierPlaceholder, String identifier) {
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
        } else if (option.equals("Домашний интернет")) {
            logger.info("Домашний интернет!!!");
            logger.info("Плейсхолдер поля " + expectedIdentifierPlaceholder + ": " + paymentPage.getIdentifierFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Сумма: " + paymentPage.getAmountFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Email: " + paymentPage.getEmailFieldPlaceholder(option));


        } else if (option.equals("Рассрочка")) {
            logger.info("Рассрочка!!!");
            logger.info("Плейсхолдер поля " + expectedIdentifierPlaceholder + ": " + paymentPage.getIdentifierFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Сумма: " + paymentPage.getAmountFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Email: " + paymentPage.getEmailFieldPlaceholder(option));


        } else if (option.equals("Задолженность")) {
            logger.info("Задолженность!!!");
            logger.info("Плейсхолдер поля " + expectedIdentifierPlaceholder + ": " + paymentPage.getIdentifierFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Сумма: " + paymentPage.getAmountFieldPlaceholder(option));
            logger.info("Плейсхолдер поля Email: " + paymentPage.getEmailFieldPlaceholder(option));

        }
    }


    @AfterEach
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Браузер закрыт");
        }
    }
}


