package api.client;

import api.Endpoints;
import api.specifications.RequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountClient {

    public Response authorize(Object body) {
        return given()
                .spec(RequestSpec.baseRequest())
                .body(body)
                .post(Endpoints.AUTHORIZED);
    }

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
