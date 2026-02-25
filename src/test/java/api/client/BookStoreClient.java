package api.client;

import api.Endpoints;
import api.specifications.RequestSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreClient {

    public Response getBooks() {
        return given()
                .spec(RequestSpec.baseRequest())
                .get(Endpoints.BOOKS);
    }

    public Response deleteBook(Object body) {
        return given()
                .spec(RequestSpec.baseRequest())
                .body(body)
                .delete(Endpoints.BOOK);
    }
}
