package by.mts.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class OnlinePaymentPage {
    private static final Logger logger = LoggerFactory.getLogger(OnlinePaymentPage.class);
    private WebDriver driver;
    private static WebDriverWait wait;
    private static Duration DEFAULT_TIMEOUT = Duration.ofSeconds(20);

    private final By blockTitleLocator = By.xpath("//h2[contains(normalize-space(.), 'Онлайн пополнение')]");
    private final By paymentLogosLocator = By.cssSelector(".pay__partners img");
    private final By detailsLinkLocator = By.xpath("//a[contains(text(), 'Подробнее о сервисе')]");
    private static final By cookieButtonLocator = By.cssSelector("#cookie-agree");

    private final By tabButtonLocator = By.cssSelector("div.select__wrapper button.select__header");
    private final By tabListLocator = By.cssSelector("div.select-wrapper.opened ul.select__list");
    private final By mobileServicesTabLocator = By.xpath("//li[contains(@class, 'select__item')]//p[text()='Услуги связи']");
    private final By homeInternetTabLocator = By.xpath("//li[contains(@class, 'select__item') and .//p[normalize-space()='Домашний интернет']]");
    private final By installmentTabLocator = By.xpath("//li[contains(@class, 'select__item')]//p[text()='Рассрочка']");
    private final By debtTabLocator = By.xpath("//li[contains(@class, 'select__item')]//p[text()='Задолженность']");

    private final By phoneFieldLocator = By.id("connection-phone");
    private final By amountFieldLocator = By.id("connection-sum");
    private final By installmentAmountFieldLocator = By.id("instalment-sum");
    private final By accountNumberFieldForInternetLocator = By.id("internet-phone");
    private final By accountNumberFieldForInstallmentLocator = By.id("score-instalment");
    private final By accountNumberFieldForDebtLocator = By.id("score-arrears");
    private final By continueButtonLocator = By.cssSelector(".button.button__default");

    private final By iframeLocator = By.cssSelector("iframe.bepaid-iframe");
    private final By popupCloseButtonLocator = By.cssSelector("body > app-root > div > div > app-header > header > div > app-back-navigation > div > div > svg-icon");

    public OnlinePaymentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public static void handleCookiePopup() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieButtonLocator));
            if (cookieButton.isDisplayed()) {
                cookieButton.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cookie__wrapper")));
                logger.info("Окно cookie закрыто");
            } else {
                logger.info("Окно cookie не отображено");
            }
        } catch (TimeoutException e) {
            logger.warn("Не удалось обработать окно cookie: {}", e.getMessage());
        }
    }

    public String getBlockTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitleLocator));
        return title.getText().replaceAll("\\s+", " ").trim();
    }

    public int getPaymentLogosCount() {
        List<WebElement> logos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(paymentLogosLocator));
        return logos.size();
    }

    public List<WebElement> getPaymentLogos(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentLogosLocator));

        List<WebElement> logos = driver.findElements(paymentLogosLocator);

        System.out.println("Найдено логотипов: " + logos.size());
        for (WebElement logo : logos) {
            System.out.println("Логотип: " + logo.getAttribute("alt") + ", отображается: " + logo.isDisplayed());
        }

        return logos;

    }

    public void clickDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(detailsLinkLocator));
        link.click();
    }

    public void selectPaymentOption(@org.jetbrains.annotations.NotNull String option) {
        By tabLocator;
        By identifierFieldLocator;
        By amountFieldLocator;
        By emailFieldLocator;
        switch (option) {
            case "Услуги связи":
                tabLocator = mobileServicesTabLocator;
                identifierFieldLocator = phoneFieldLocator;
                amountFieldLocator = By.id("connection-sum");
                emailFieldLocator = By.id("connection-email");
                break;
            case "Домашний интернет":
                tabLocator = homeInternetTabLocator;
                identifierFieldLocator = By.id("internet-phone");
                amountFieldLocator = By.id("internet-sum");
                emailFieldLocator = By.id("internet-email");
                break;
            case "Рассрочка":
                tabLocator = installmentTabLocator;
                identifierFieldLocator = accountNumberFieldForInstallmentLocator;
                amountFieldLocator = By.id("instalment-sum");
                emailFieldLocator = By.id("instalment-email");
                break;
            case "Задолженность":
                tabLocator = debtTabLocator;
                identifierFieldLocator = accountNumberFieldForDebtLocator;
                amountFieldLocator = By.id("arrears-sum");
                emailFieldLocator = By.id("arrears-email");
                break;
            default:
                throw new IllegalArgumentException("Неизвестная вкладка: " + option);
        }

        try {

            // Открываем выпадающий список
            logger.info("Открытие списка вкладок");
            WebElement tabButton = wait.until(ExpectedConditions.elementToBeClickable(tabButtonLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabButton);
            logger.info("Список вкладок открыт");

            // Ожидаем видимости списка вкладок и всех элементов
//            wait.withTimeout(Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(tabListLocator));
            logger.info("tabListLocator: " + tabListLocator.toString());
            logger.info("tabLocator: " + tabLocator.toString());


//            wait.until(ExpectedConditions.presenceOfElementLocated(tabListLocator));
//
//            WebElement tabList = driver.findElement(tabListLocator);
//            logger.info("tabListLocator: " + tabList.toString());
//
//            logger.info("HTML списка вкладок: {}", tabList.getAttribute("outerHTML"));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(tabListLocator));
//            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.select__list li.select__item")));
            logger.info("Выбор вкладки {}", option);

            // Кликаем на вкладку в выпадающем списке
            WebElement tab = wait.until(ExpectedConditions.presenceOfElementLocated(tabLocator));
            logger.info("Вкладка {} найдена, видима: {}, активна: {}", option, tab.isDisplayed(), tab.isEnabled());
            tab = wait.until(ExpectedConditions.elementToBeClickable(tabLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
            logger.info("Выбрана вкладка: {}", option);

            // Ожидаем загрузки формы
            Thread.sleep(300); // Задержка для JavaScript
            logger.info("Ожидание загрузки формы для вкладки {}", option);

            // Проверяем, что поле идентификатора отобразилось
//            wait.until(ExpectedConditions.presenceOfElementLocated(identifierFieldLocator));
            //WebElement identifierField = driver.findElement(identifierFieldLocator);
            WebElement identifierField = wait.until(ExpectedConditions.presenceOfElementLocated(identifierFieldLocator));
            logger.info("HTML поля идентификатора для {}: {}", option, identifierField.getAttribute("outerHTML"));
            logger.info("Поле {} видимо: {}, активно: {}", option, identifierField.isDisplayed(), identifierField.isEnabled());
//            wait.until(ExpectedConditions.visibilityOfElementLocated(identifierFieldLocator));

            // Проверяем, что все три поля ввода отобразились
            WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(amountFieldLocator));
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator));
            logger.info("Поля ввода для вкладки {} отобразились: идентификатор, сумма, e-mail", option);

            // Проверяем, что вкладка активна
            wait.until(ExpectedConditions.textToBePresentInElementLocated(tabButtonLocator, option));
            logger.info("Вкладка {} активна", option);
        } catch (TimeoutException | InterruptedException e) {
            logger.error("Ошибка при выборе вкладки {}: {}", option, e.getMessage());
        }
    }


    public String getIdentifierFieldPlaceholder (String option) {
        By locator;
        switch (option) {
            case "Услуги связи":
                locator = phoneFieldLocator;
                break;
            case "Домашний интернет":
                locator = accountNumberFieldForInternetLocator;
                break;
            case "Рассрочка":
                locator = accountNumberFieldForInstallmentLocator;
                break;
            case "Задолженность":
                locator = accountNumberFieldForDebtLocator;
                break;
            default:
                throw new IllegalArgumentException("Неизвестная вкладка: " + option);
        }
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return field.getAttribute("placeholder");
    }

    public String getAmountFieldPlaceholder(@NotNull String option) {
        By locator;
//        By locator = option.equals("Рассрочка") ? installmentAmountFieldLocator : amountFieldLocator;
        switch (option) {
            case "Услуги связи":
                locator = amountFieldLocator;
                break;
            case "Домашний интернет":
                locator = By.id("internet-sum");
                break;
            case "Рассрочка":
                locator = By.id("instalment-sum");
                break;
            case "Задолженность":
                locator = By.id("arrears-sum");
                break;
            default:
                throw new IllegalArgumentException("Неизвестная вкладка: " + option);
        }
        WebElement amountField = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return amountField.getAttribute("placeholder");
    }

    public void enterPaymentDetails(String option, String identifier, String amount) {
        By identifierLocator;
        By amountLocator;

        logger.info("option!!! " + option.toString());
        switch (option) {
            case "Услуги связи":
                identifierLocator = phoneFieldLocator;
                amountLocator = amountFieldLocator;
                break;
            case "Домашний интернет":
                identifierLocator = accountNumberFieldForInternetLocator;
                amountLocator = By.id("internet-sum");
                break;
            case "Рассрочка":
                identifierLocator = accountNumberFieldForInstallmentLocator;
                amountLocator = installmentAmountFieldLocator;
                break;
            case "Задолженность":
                identifierLocator = accountNumberFieldForDebtLocator;
                amountLocator = amountFieldLocator;
                break;
            default:
                throw new IllegalArgumentException("Неизвестная вкладка: " + option);
        }
        try {
            WebElement identifierField = wait.until(ExpectedConditions.elementToBeClickable(identifierLocator));
            logger.info("Элемент для ввода идентификатора ({}) найден и кликабелен", identifierLocator);

            if (identifierField.getAttribute("disabled") != null && identifierField.getAttribute("disabled").equals("true")) {
                logger.error("Элемент для ввода идентификатора ({}) отключен (disabled)", identifierLocator);
                throw new IllegalStateException("Поле ввода отключено для " + option);
            }

            identifierField.clear();
            identifierField.sendKeys(identifier);
            String actualValue = identifierField.getAttribute("value");
            logger.info("Введён идентификатор: {}, фактическое значение поля: {}", identifier, actualValue);

            WebElement amountField = wait.until(ExpectedConditions.elementToBeClickable(amountLocator));
            amountField.clear();
            amountField.sendKeys(amount);
            String actualAmountValue = amountField.getAttribute("value");
            logger.info("Введены данные для {}: идентификатор = {}, сумма = {}, фактическая сумма = {}",
                    option, identifier, amount, actualAmountValue);
        } catch (TimeoutException e) {
            logger.error("Ошибка при взаимодействии с полем для {}: {}", option, e.getMessage());
            logger.info("Текущий DOM: {}", driver.getPageSource());
            logger.info("Состояние элемента ({}): isDisplayed={}, isEnabled={}",
                    identifierLocator,
                    driver.findElements(identifierLocator).size() > 0 && driver.findElement(identifierLocator).isDisplayed(),
                    driver.findElements(identifierLocator).size() > 0 && driver.findElement(identifierLocator).isEnabled());
            throw new RuntimeException("Не удалось ввести данные для " + option, e);
        }
    }

    public void clickContinue() {
        try {
            WebDriverWait longerWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement button = longerWait.until(ExpectedConditions.elementToBeClickable(continueButtonLocator));
            logger.info("Кнопка 'Продолжить' найдена и кликабельна");

            if (button.getAttribute("disabled") != null && button.getAttribute("disabled").equals("true")) {
                logger.error("Кнопка 'Продолжить' отключена (disabled)");
                throw new IllegalStateException("Кнопка 'Продолжить' отключена");
            }

            button.click();
            logger.info("Нажата кнопка 'Продолжить'");
        } catch (TimeoutException e) {
            logger.error("Ошибка при ожидании кликабельности кнопки 'Продолжить': {}", e.getMessage());
            logger.info("Текущий DOM: {}", driver.getPageSource());
            logger.info("Состояние кнопки ({}): isDisplayed={}, isEnabled={}",
                    continueButtonLocator,
                    driver.findElements(continueButtonLocator).size() > 0 && driver.findElement(continueButtonLocator).isDisplayed(),
                    driver.findElements(continueButtonLocator).size() > 0 && driver.findElement(continueButtonLocator).isEnabled());
            throw new RuntimeException("Не удалось нажать кнопку 'Продолжить'", e);
        }
    }

    public void closePopup() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLocator));
            logger.info("Переключено на iframe для закрытия всплывающего окна");
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(popupCloseButtonLocator));
            closeButton.click();
            logger.info("Нажата кнопка закрытия всплывающего окна");
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(iframeLocator));
            logger.info("Всплывающее окно закрыто");
            wait.until(ExpectedConditions.elementToBeClickable(tabButtonLocator));
            logger.info("Поле с вкладками готово для следующего выбора");
        } catch (TimeoutException e) {
            logger.error("Ошибка при закрытии всплывающего окна: {}", e.getMessage());
            driver.switchTo().defaultContent();
            throw new RuntimeException("Не удалось закрыть всплывающее окно", e);
        }
    }
}