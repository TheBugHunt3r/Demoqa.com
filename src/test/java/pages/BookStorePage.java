package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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
        wait.until(ExpectedConditions.urlContains("books"));
        writeText(searchBox, bookName);
        return this;
    }

    @Step("Получение количества найденных книг")
    public int getVisibleBooksCount() {
        List<WebElement> results = driver.findElements(By.xpath("//div[@class='action-buttons']//a"));
        return results.size();
    }

    @Step("Проверка открытия страницы Book Store")
    public boolean isBookStoreOpened() {
        return wait.until(ExpectedConditions.urlContains("books"));
    }

    @Step("Ожидание появления книги в списке")
    public BookStorePage waitForBookVisible(String bookName) {
        String bookXpath = String.format("//div[@class='action-buttons']//a[contains(text(), '%s')]", bookName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(bookXpath)));
        return this;
    }
}
