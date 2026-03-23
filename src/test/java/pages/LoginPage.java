package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id = "userName")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод логина {username} и пароля")
    public ProfilePage login(String username, String password) {
        writeText(usernameInput, username);
        writeText(passwordInput, password);
        click(loginButton);
        return new ProfilePage(driver);
    }
}
