package api.client;

import api.Endpoints;
import api.models.AddBookRequest;
import api.models.DeleteBookRequest;
import api.models.GenerateTokenRequest;
import api.specifications.RequestSpec;
import core.config.ConfigManager;
import core.config.TokenManager;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreClient {

    private static String token;

    @Step("Получение списка книг")
    public Response getBooks() {
        return given()
                .spec(RequestSpec.baseRequest())
                .when()
                .get(Endpoints.BOOKS);
    }

    @Step("Удаление книги")
    public Response deleteBook(DeleteBookRequest request) {
        return given()
                .spec(RequestSpec.baseRequest())
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(request)
                .delete(Endpoints.BOOK);
    }

    @Step("Получение токена")
    private String getToken() {
        if (token == null) {
            GenerateTokenRequest authRequest = GenerateTokenRequest.builder()
                    .userName(ConfigManager.getConfig().username())
                    .password(ConfigManager.getConfig().password())
                    .build();
            Response response = given()
                    .spec(RequestSpec.baseRequest())
                    .body(authRequest)
                    .post(Endpoints.GENERATE_TOKEN);
            token = response.jsonPath().getString("token");
        }
        return token;
    }

    @Step("Добавление книг в коллекцию пользователя")
    public Response addBook(AddBookRequest request) {
        return given()
                .spec(RequestSpec.baseRequest())
                .header("Authorization", "Bearer " + TokenManager.getToken())
                .body(request)
                .when()
                .post(Endpoints.BOOKS);
    }
}
