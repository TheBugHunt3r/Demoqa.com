package core.helpers;

import api.client.AccountClient;
import api.models.GenerateTokenRequest;
import api.models.GenerateTokenResponse;
import api.models.UserRequest;
import api.models.UserResponse;
import core.config.TokenManager;

import java.util.UUID;

public class AuthHelper {

    private static final AccountClient accountClient = new AccountClient();

    public static void authorize() {
        String username = "user_" + UUID.randomUUID();
        String password = "Password123!";
        UserRequest userRequest = UserRequest.builder()
                .userName(username)
                .password(password)
                .build();
        UserResponse userResponse = accountClient.createUser(userRequest).as(UserResponse.class);
        TokenManager.setUserId(userResponse.getUserID());
        GenerateTokenRequest tokenRequest = GenerateTokenRequest.builder()
                        .userName(username)
                        .password(password)
                        .build();
        GenerateTokenResponse tokenResponse = accountClient.generateToken(tokenRequest).as(GenerateTokenResponse.class);
        TokenManager.setToken(tokenResponse.getToken());
    }
}
