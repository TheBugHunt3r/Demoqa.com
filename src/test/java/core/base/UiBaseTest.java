package core.base;

import core.config.ConfigManager;
import core.config.TestConfig;
import core.driver.DriverFactory;
import core.utils.TestListener;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.LoginPage;

import java.time.Duration;

@Slf4j
@Listeners({TestListener.class})
public abstract class UiBaseTest {

    protected WebDriver driver;
    protected TestConfig config;
    protected WebDriverWait wait;

    protected LoginPage loginPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        log.info("запуск теста в браузере: {} ", browser);
        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
        config = ConfigManager.getConfig();
        wait = new WebDriverWait(driver, Duration.ofSeconds(config.timeout()));

        loginPage = new LoginPage(driver);

        driver.get(config.baseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("завершение теста и закрытие драйвера");
        DriverFactory.quitDriver();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        log.info("скриншот для отчета Allure");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}