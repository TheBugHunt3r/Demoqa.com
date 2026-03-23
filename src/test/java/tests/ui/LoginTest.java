package tests.ui;

import core.base.UiBaseTest;
import core.helpers.DatabaseHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

public class LoginTest extends UiBaseTest {

    @Test
    @Step("Тест логина")
    @Description("Проверка успешного логина")
    public void successLoginTest() {
        String user = "admin6";
        String passFromDb = DatabaseHelper.getDataFromDb("password", user);
        driver.get(config.baseUrl() + "/login");
        boolean isOpened = new LoginPage(driver)
                .login(user, passFromDb)
                .isProfilePageOpened();
        Assert.assertTrue(isOpened, "страница профиля не открылась");
    }

    @Test
    @Step("Тест логина с неверным паролем")
    @Description("Проверка ошибки при вводе неверного пароля")
    public void failedLoginTest() {
        driver.get(config.baseUrl() + "/login");
        new LoginPage(driver)
                .login(config.username(), "wrongPassword123");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "не на странице логина");
    }
}
