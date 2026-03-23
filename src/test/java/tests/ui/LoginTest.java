package tests.ui;

import core.base.UiBaseTest;
import core.helpers.DatabaseHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;


@Slf4j
public class LoginTest extends UiBaseTest {

    @Test
    @Step("Тест логина")
    @Description("Проверка успешного логина")
    public void successLoginTest() {
        String user = config.username();
        String pass = config.password();
        log.info("успешный логин для пользователя: {}", user);
        DatabaseHelper.ensureUserExists(user, pass);
        loginPage.open(config.baseUrl() + "/login")
                .login(user, pass)
                .checkProfileIsVisible();
        log.info("тест успешно пройден");
    }

    @Test
    @Step("Тест логина с неверным паролем")
    @Description("Проверка ошибки при вводе неверного пароля")
    public void failedLoginTest() {
        String user = config.username();
        String wrongPass = "wrongPassword123";
        log.info("невалидный логин для пользователя: {}", user);
        loginPage.open(config.baseUrl() + "/login")
                .loginWithInvalidCredentials(user, wrongPass)
                .checkStillOnLoginPage();
        log.info("негативный тест успешно завершен");
    }
}
