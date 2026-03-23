package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Slf4j
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void click(WebElement element) {
        log.debug("ожидание кликабельности и клик по элементу");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        element.click();
    }

    protected void writeText(WebElement element, String text) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                log.debug("ввод текста '{}'. попытка {}", text, attempts + 1);
                wait.until(ExpectedConditions.visibilityOf(element));
                scrollToElement(element);
                element.clear();
                element.sendKeys(text);
                break;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                log.warn("элемент стал неактуальным");
            }
            attempts++;
        }
    }

    protected String getElementText(WebElement element) {
        String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
        log.debug("получен текст из элемента: {}", text);
        return text;
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean isDisplayed = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
            log.debug("элемент отображается: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            log.error("элемент не найден или не отображается после ожидания");
            return false;
        }
    }
}