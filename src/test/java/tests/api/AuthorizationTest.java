package tests.api;

import api.client.AccountClient;
import api.models.GenerateTokenRequest;
import api.specifications.ResponseSpec;
import core.base.ApiBaseTest;
import core.config.ConfigManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthorizationTest extends ApiBaseTest {

    AccountClient accountClient = new AccountClient();

    @Test
    @Step("Тест авторизации пользователя")
    @Description("Проверка успешной авторизации")
    public void authorizeUser() {
        Response response = accountClient.authorize(ConfigManager.getConfig().username(),
                ConfigManager.getConfig().password());
        response.then().spec(ResponseSpec.statusCode200());
        String responseBody = response.asString();
        Assert.assertNotNull(responseBody, "ответ не должен быть null");
        Assert.assertFalse(responseBody.isEmpty(), "ответ не должен быть пустым");
        Boolean isAuthorized = response.as(Boolean.class);
        Assert.assertTrue(isAuthorized, "должен быть true");
    }
}
