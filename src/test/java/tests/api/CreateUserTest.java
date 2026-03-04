package tests.api;

import api.client.AccountClient;
import api.models.UserRequest;
import api.specifications.ResponseSpec;
import core.base.ApiBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.UUID;

public class CreateUserTest extends ApiBaseTest {

    AccountClient accountClient = new AccountClient();

    @Test
    @Step("Тест создания юзера")
    @Description("Проверка успешного создания юзера")
    public void createUserTest() {
        String uniqueUsername = "user_" + UUID.randomUUID();
        UserRequest request = UserRequest.builder()
                .userName(uniqueUsername)
                .password("Password123!")
                .build();
        accountClient.createUser(request)
                .then()
                .spec(ResponseSpec.statusCode201());
    }
}
