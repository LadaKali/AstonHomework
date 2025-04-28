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


public class MtsByPaymentBlockTest {
    private static final Logger logger = LoggerFactory.getLogger(MtsByPaymentBlockTest.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private OnlinePaymentPage paymentPage;

    public static class TestData {
        public static final String BASE_URL = "https://www.mts.by/";
        public static final String BLOCK_TITLE = "Онлайн пополнение без комиссии";
        public static final String PHONE_NUMBER = "297777777";
        public static final String AMOUNT = "30.00";
        public static final int EXPECTED_LOGO_COUNT = 5;
        public static final String DETAILS_URL = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        public static final String DETAILS_PAGE_TITLE = "Оплата банковской картой";
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
    }

    @Test
    public void testPaymentBlockTitle() {
        assertEquals(TestData.BLOCK_TITLE, paymentPage.getBlockTitle(), "Заголовок блока не соответствует");
    }


    @Test
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
    public void testPaymentOptions() {
        processPaymentOption("Услуги связи", "Номер телефона", TestData.PHONE_NUMBER);
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
            paymentPage.closePopup();
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


