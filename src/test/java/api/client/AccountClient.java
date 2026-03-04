package api.client;

import api.Endpoints;
import api.specifications.RequestSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountClient {

    @Step("Авторизация пользователя")
    public Response authorize(Object body) {
        return given()
                .spec(RequestSpec.baseRequest())
                .body(body)
                .post(Endpoints.AUTHORIZED);
    }

    @Step("Генерация токена")
    public Response generateToken(Object body) {
        return given()
                .spec(RequestSpec.baseRequest())
                .body(body)
                .post(Endpoints.GENERATE_TOKEN);
    }

    public Response createUser(Object body) {
        return given()
                .spec(RequestSpec.baseRequest())
                .body(body)
                .post(Endpoints.USER);
    }
}
