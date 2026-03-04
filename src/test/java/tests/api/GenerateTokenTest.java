package tests.api;

import api.client.AccountClient;
import api.models.GenerateTokenRequest;
import api.models.GenerateTokenResponse;
import api.specifications.ResponseSpec;
import core.base.ApiBaseTest;
import core.config.ConfigManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateTokenTest extends ApiBaseTest {

    AccountClient accountClient = new AccountClient();

    @Test
    @Step("Тест генерации токена")
    @Description("Проверка успешной генерации токена")
    public void generateToken() {
        GenerateTokenRequest request = GenerateTokenRequest.builder()
                .userName(ConfigManager.getConfig().username())
                .password(ConfigManager.getConfig().password())
                .build();
        Response response = accountClient.generateToken(request);
        response.then().spec(ResponseSpec.statusCode200());
        GenerateTokenResponse tokenResponse = response.as(GenerateTokenResponse.class);
        Assert.assertNotNull(tokenResponse.getToken());
        Assert.assertEquals(tokenResponse.getStatus(), "Success");
    }
}
