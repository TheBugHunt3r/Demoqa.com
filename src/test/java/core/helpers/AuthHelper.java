package core.helpers;

import api.client.AccountClient;
import api.client.BookStoreClient;
import api.models.*;
import api.specifications.ResponseSpec;
import core.config.TokenManager;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AuthHelper {

    private static final Logger log = LoggerFactory.getLogger(AuthHelper.class);
    private static final AccountClient accountClient = new AccountClient();
    private static final BookStoreClient bookClient = new BookStoreClient();

    @Step("Авторизация и подготовка чистого окружения")
    public static void authorize() {
        String username = "user_" + UUID.randomUUID().toString().substring(0, 8);
        String password = "Password123!";
        UserResponse user = accountClient.createUser(username, password)
                .then().spec(ResponseSpec.statusCode201())
                .extract().as(UserResponse.class);
        TokenManager.setUserId(user.getUserID());
        GenerateTokenResponse tokenData = accountClient.generateToken(username, password)
                .then().spec(ResponseSpec.statusCode200())
                .extract().as(GenerateTokenResponse.class);
        TokenManager.setToken(tokenData.getToken());
        log.info("✅ Пользователь {} готов к работе. ID: {}", username, user.getUserID());
    }
}