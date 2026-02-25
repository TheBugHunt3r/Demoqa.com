package core.driver;

import core.config.ConfigManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        String browser = ConfigManager.getConfig().browser();
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().window().setSize(new Dimension(1920, 1080));
            driver.set(webDriver);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
