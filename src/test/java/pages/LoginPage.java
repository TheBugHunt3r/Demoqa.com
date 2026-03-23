package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@Slf4j
public class LoginPage extends BasePage {

    @FindBy(id = "userName")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы логина")
    public LoginPage open(String url) {
        log.info("открытие страницы: {}", url);
        driver.get(url);
        return this;
    }

    @Step("Ввод логина {username} и пароля")
    public ProfilePage login(String username, String password) {
        log.info("попытка входа для пользователя: {}", username);
        writeText(usernameInput, username);
        writeText(passwordInput, password);
        click(loginButton);
        log.debug("переход на ProfilePage");
        return new ProfilePage(driver);
    }

    @Step("Вход с невалидными данными: {username}")
    public LoginPage loginWithInvalidCredentials(String username, String password) {
        log.info("вход под '{}' с паролем '{}'", username, password);
        writeText(usernameInput, username);
        writeText(passwordInput, password);
        click(loginButton);
        return this;
    }

    @Step("Проверка URL содержит '{part}'")
    public boolean isUrlContaining(String part) {
        String currentUrl = driver.getCurrentUrl();
        log.debug("ожидается: {}, фактически: {}", part, currentUrl);
        return currentUrl.contains(part);
    }

    @Step("Проверка, что мы остались на странице логина")
    public LoginPage checkStillOnLoginPage() {
        log.info("Проверка: URL должен содержать 'login'");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Ошибка: мы покинули страницу логина!");
        return this;
    }
}
