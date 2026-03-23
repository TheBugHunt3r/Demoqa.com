package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Slf4j
public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void initDriver(String browser) {
        if (getDriver() == null) {
            WebDriver webDriver;
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"))
                    || System.getenv("CI") != null;
            log.info("Инициализация драйвера для браузера: {} (Headless: {})", browser, isHeadless);
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    if (isHeadless) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.addArguments("--disable-gpu");
                        log.debug("Chrome запущен с аргументами для CI/Headless");
                    }
                    chromeOptions.addArguments("--window-size=1920,1080");
                    webDriver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.addArguments("-headless");
                    }
                    webDriver = new FirefoxDriver(firefoxOptions);
                    break;
                default:
                    log.error("Попытка запуска неподдерживаемого браузера: {}", browser);
                    throw new RuntimeException("Браузер " + browser + " не поддерживается.");
            }
            driver.set(webDriver);
            log.info("Драйвер успешно создан и сохранен в ThreadLocal");
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Закрытие сессии драйвера");
            driver.get().quit();
            driver.remove();
        }
    }
}
