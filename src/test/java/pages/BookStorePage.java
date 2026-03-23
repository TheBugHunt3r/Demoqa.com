package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

@Slf4j
public class BookStorePage extends BasePage{

    @FindBy(id = "searchBox")
    private WebElement searchBox;

    @FindBy(className = "rt-tr-group")
    private List<WebElement> bookRows;

    @FindBy(id = "login")
    private WebElement loginButton;

    public BookStorePage(WebDriver driver) {
        super(driver);
    }

    @Step("Поиск книги: {bookName}")
    public BookStorePage searchBook(String bookName) {
        log.info("поиск книги по названию: '{}'", bookName);
        wait.until(ExpectedConditions.urlContains("books"));
        writeText(searchBox, bookName);
        return this;
    }

    @Step("Получение количества найденных книг")
    public int getVisibleBooksCount() {
        List<WebElement> results = driver.findElements(By.xpath("//div[@class='action-buttons']//a"));
        int count = results.size();
        log.info("найдено книг в списке: {}", count);
        return count;
    }

    @Step("Проверка открытия страницы Book Store")
    public boolean isBookStoreOpened() {
        return wait.until(ExpectedConditions.urlContains("books"));
    }

    @Step("Ожидание появления книги в списке")
    public BookStorePage waitForBookVisible(String bookName) {
        log.debug("ожидание появления книги '{}' в результатах поиска", bookName);
        String bookXpath = String.format("//div[@class='action-buttons']//a[contains(text(), '%s')]", bookName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(bookXpath)));
        return this;
    }

    @Step("Поиск и проверка наличия книги '{bookName}'")
    public BookStorePage searchAndVerify(String bookName) {
        searchBook(bookName);
        waitForBookVisible(bookName);
        int count = getVisibleBooksCount();
        log.info("ожидается минимум 1 книга, найдено {}", count);
        Assert.assertTrue(count > 0, "книга '" + bookName + "' не найдена");
        return this;
    }
}
