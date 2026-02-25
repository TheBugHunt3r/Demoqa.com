package tests.api;

import api.client.AccountClient;
import api.models.GenerateTokenRequest;
import core.base.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthorizationTest extends ApiBaseTest {

    AccountClient accountClient = new AccountClient();

    @Test
    public void authorizeUser() {
        GenerateTokenRequest request = GenerateTokenRequest.builder()
                .userName("admin6")
                .password("Password123!")
                .build();
        Response response = accountClient.authorize(request);
        response.then().statusCode(200);
        Assert.assertTrue(response.asString().contains("true"));
    }
}
