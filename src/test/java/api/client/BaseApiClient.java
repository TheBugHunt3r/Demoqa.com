package api.client;

import api.specifications.RequestSpec;
import core.config.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    protected RequestSpecification getSpecWithAuth() {
        String token = TokenManager.getToken();
        return getBaseSpec()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON) // Обязательно для DemoQA
                .log().ifValidationFails();
    }

    protected RequestSpecification getBaseSpec() {
        return RequestSpec.baseRequest().contentType(ContentType.JSON);
    }

    protected Response get(String endpoint) {
        return given().spec(getBaseSpec()).get(endpoint);
    }

    protected Response getWithAuth(String endpoint) {
        return given().spec(getSpecWithAuth()).get(endpoint);
    }

    protected Response post(String endpoint, Object body) {
        return given().spec(getBaseSpec()).body(body).post(endpoint);
    }

    protected Response postWithAuth(String endpoint, Object body) {
        return given().spec(getSpecWithAuth())
                .body(body)
                .post(endpoint);
    }

    protected Response deleteWithAuth(String endpoint) {
        return given().spec(getSpecWithAuth())
                .delete(endpoint);
    }

    protected Response deleteWithAuth(String endpoint, Object body) {
        return given().spec(getSpecWithAuth())
                .body(body)
                .delete(endpoint);
    }
}