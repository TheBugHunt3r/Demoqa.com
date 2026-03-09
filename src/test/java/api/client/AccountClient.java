package api.client;

import api.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AccountClient extends BaseApiClient {

    @Step("Авторизация пользователя {username}")
    public Response authorize(String username, String password) {
        Map<String, String> body = Map.of("userName", username, "password", password);
        return given()
                .spec(getBaseSpec())
                .body(body)
                .post(Endpoints.AUTHORIZED);
    }

    @Step("Генерация токена для пользователя {username}")
    public Response generateToken(String username, String password) {
        Map<String, String> body = Map.of("userName", username, "password", password);
        return given()
                .spec(getBaseSpec())
                .body(body)
                .post(Endpoints.GENERATE_TOKEN);
    }

    @Step("Создание пользователя {username}")
    public Response createUser(String username, String password) {
        Map<String, String> authData = new HashMap<>();
        authData.put("userName", username);
        authData.put("password", password);
        return given()
                .spec(getBaseSpec())
                .body(authData)
                .post(Endpoints.USER);
    }
}
