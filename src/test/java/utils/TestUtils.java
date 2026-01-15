package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestUtils {
    private static final Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(TestUtils.class.getName());

    static {
        try (java.io.InputStream input = TestUtils.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                logger.warning("⚠️ Файл application.properties не найден, будут использованы значения по умолчанию.");
            } else {
                properties.load(input);
            }
        } catch (java.io.IOException ex) {
            logger.log(Level.SEVERE, "Ошибка при загрузке properties", ex);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url", "https://stellarburgers.education-services.ru");
    }

    public static String getBrowserType() {
        // Сначала проверяем системное свойство (из Maven профиля), затем application.properties
        String browserType = System.getProperty("browser.type");
        if (browserType == null || browserType.isEmpty()) {
            browserType = properties.getProperty("browser.type");
        }
        if (browserType == null || browserType.isEmpty()) {
            browserType = "chrome"; // значение по умолчанию
        }
        logger.info("🔄 Определен браузер для тестов: " + browserType);
        return browserType.toLowerCase();
    }

    public static WebDriver createDriver() {
        String browserType = getBrowserType().toLowerCase();
        logger.info("🔄 Запуск тестов в браузере: " + browserType);

        WebDriver driver;

        if (browserType.equals("yandex")) {
            driver = setupYandexBrowser();
        } else {
            driver = setupChromeBrowser();
        }

        // Переход на сайт
        driver.get(getBaseUrl());
        logger.info("✅ Переход на сайт: " + getBaseUrl());

        return driver;
    }

    private static WebDriver setupChromeBrowser() {
        logger.info("✅ Запуск тестов в Google Chrome");

        // Selenium 4.6+ автоматически управляет драйверами через встроенный Selenium Manager
        // Не нужно явно устанавливать ChromeDriver - Selenium сделает это автоматически
        logger.info("✅ Используется встроенный Selenium Manager для автоматического управления ChromeDriver");

        // Настройка опций Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }

    private static WebDriver setupYandexBrowser() {
        logger.info("✅ Запуск тестов в Яндекс.Браузере");

        // Используем WebDriverManager для загрузки ChromeDriver версии 142 (совместимой с Yandex 142)
        // Указываем browserVersion чтобы WebDriverManager загрузил правильную версию
        WebDriverManager.chromedriver().browserVersion("142").setup();
        logger.info("✅ ChromeDriver для Yandex настроен через WebDriverManager (версия 142)");

        // Специальные опции для Яндекс.Браузера
        ChromeOptions options = new ChromeOptions();
        
        // Указываем путь к Yandex браузеру
        String yandexBrowserPath = "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe";
        if (new java.io.File(yandexBrowserPath).exists()) {
            options.setBinary(yandexBrowserPath);
            logger.info("✅ Путь к Yandex браузеру: " + yandexBrowserPath);
        } else {
            // Альтернативный путь
            yandexBrowserPath = "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";
            if (new java.io.File(yandexBrowserPath).exists()) {
                options.setBinary(yandexBrowserPath);
                logger.info("✅ Путь к Yandex браузеру: " + yandexBrowserPath);
            } else {
                logger.warning("⚠️ Yandex браузер не найден по стандартным путям, используется системный Chrome");
            }
        }
        
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }
}
