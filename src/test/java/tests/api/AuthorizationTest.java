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
        GenerateTokenRequest request = GenerateTokenRequest.builder()
                .userName(ConfigManager.getConfig().username())
                .password(ConfigManager.getConfig().password())
                .build();
        Response response = accountClient.authorize(request);
        response.then().spec(ResponseSpec.statusCode200());
        Assert.assertEquals(response.asString(), "true");
    }
}
