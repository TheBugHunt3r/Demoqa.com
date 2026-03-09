package tests.api;

import api.client.AccountClient;
import api.models.UserResponse;
import api.specifications.ResponseSpec;
import core.base.ApiBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class CreateUserTest extends ApiBaseTest {

    AccountClient accountClient = new AccountClient();

    @Test
    @Step("Тест создания юзера")
    @Description("Проверка успешного создания юзера")
    public void createUserTest() {
        String uniqueUsername = "user_" + UUID.randomUUID();
        String password = "Password123!";
        Response response = accountClient.createUser(uniqueUsername, password);
        response.then().spec(ResponseSpec.statusCode201());
        UserResponse userResponse = response.as(UserResponse.class);
        String userId = userResponse.getUserID();
        Assert.assertNotNull(userId, "userID не должен быть null");
        Assert.assertFalse(userId.isEmpty(), "userID не должен быть пустым");
        Assert.assertNotNull(userResponse.getUsername(), "username не должен быть null");
        Assert.assertEquals(userResponse.getUsername(), uniqueUsername, "username в ответе должен" +
                " совпадать с отправленным");
    }
}
