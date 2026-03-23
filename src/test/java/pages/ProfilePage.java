package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
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
        String name = getElementText(userNameValue);
        log.info("имя пользователя в профиле: {}", name);
        return name;
    }

    @Step("Проверка открытия профиля")
    public ProfilePage checkProfileIsVisible() {
        log.info("страница профиля должна быть открыта");
        wait.until(ExpectedConditions.urlContains("profile"));
        log.debug("страница профиля открыта");
        return this;
    }

    @Step("Переход на страницу Book Store")
    public BookStorePage goToBookStore() {
        log.info("переход в магазин");
        click(gotoStoreButton);
        return new BookStorePage(driver);
    }
}
