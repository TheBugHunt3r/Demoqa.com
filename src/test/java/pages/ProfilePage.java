package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage{

    @FindBy(id = "userName-value")
    private WebElement userNameValue;

    @FindBy(id = "gotoStore")
    private WebElement gotoStoreButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение имени пользователя из профиля")
    public String getUsername() {
        return getElementText(userNameValue);
    }

    @Step("Проверка открытия профиля")
    public boolean isProfilePageOpened() {
        return wait.until(ExpectedConditions.urlContains("profile"));
    }

    @Step("Переход на страницу Book Store")
    public BookStorePage goToBookStore() {
        driver.get("https://demoqa.com/books");
        return new BookStorePage(driver);
    }
}
