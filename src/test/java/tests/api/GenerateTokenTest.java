package tests.api;

import api.client.AccountClient;
import api.models.GenerateTokenRequest;
import api.models.GenerateTokenResponse;
import core.base.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateTokenTest extends ApiBaseTest {

    AccountClient accountClient = new AccountClient();

    @Test
    public void generateToken() {
        GenerateTokenRequest request = GenerateTokenRequest.builder()
                .userName("admin6")
                .password("Password123!")
                .build();
        Response response = accountClient.generateToken(request);
        response.then().statusCode(200);
        GenerateTokenResponse tokenResponse = response.as(GenerateTokenResponse.class);
        Assert.assertNotNull(tokenResponse.getToken());
        Assert.assertEquals(tokenResponse.getStatus(), "Success");
    }
}
