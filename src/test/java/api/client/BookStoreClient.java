package api.client;

import api.Endpoints;
import api.models.GenerateTokenRequest;
import api.specifications.RequestSpec;
import core.config.ConfigManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreClient {

    private static String token;

    public Response getBooks() {
        return given()
                .spec(RequestSpec.baseRequest())
                .when()
                .get(Endpoints.BOOKS);
    }

    public Response deleteAllBooks(String userId) {
        return given()
                .spec(RequestSpec.baseRequest())
                .header("Authorization", "Bearer " + getToken())
                .when()
                .delete(Endpoints.BOOKS + "?UserId=" + userId);
    }

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

    public Response addBook(String userId, String isbn) {
        String token = getToken();
        String body = String.format(
                "{\n" +
                        "  \"userId\": \"string\",\n" +
                        "  \"collectionOfIsbns\": [\n" +
                        "    {\n" +
                        "      \"isbn\": \"string\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}",
                userId, isbn
        );
        return given()
                .spec(RequestSpec.baseRequest())
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(Endpoints.BOOKS);
    }
}
