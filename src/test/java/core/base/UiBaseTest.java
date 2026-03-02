package core.base;

import core.config.ConfigManager;
import core.driver.DriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class UiBaseTest {

    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(
                ConfigManager.getConfig().baseUrl()
        );

        wait = new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(ConfigManager.getConfig().timeout())
        );
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
