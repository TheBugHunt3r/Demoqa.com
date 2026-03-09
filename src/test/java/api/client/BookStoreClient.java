package api.client;

import api.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class BookStoreClient extends BaseApiClient {

    private static String token;

    @Step("Получение списка книг")
    public Response getBooks() {
        return getWithAuth(Endpoints.BOOKS);
    }

    @Step("Получение книг пользователя {userId}")
    public Response getUserBooks(String userId) {
        return getWithAuth(Endpoints.BOOKS + "?userId=" + userId);
    }

    @Step("Удаление книги с ISBN {isbn} из коллекции пользователя {userId}")
    public Response deleteBook(String userId, String isbn) {
        Map<String, String> requestBody = Map.of("isbn", isbn, "userId", userId);
        return deleteWithAuth(Endpoints.BOOK, requestBody);
    }

    @Step("Удаление всех книг пользователя {userId}")
    public Response deleteAllBooks(String userId) {
        String endpoint = Endpoints.BOOKS + "?UserId=" + userId;
        return deleteWithAuth(endpoint);
    }

    @Step("Добавление книг в коллекцию пользователя {userId}")
    public Response addBook(String userId, List<String> isbns) {
        Assert.assertNotNull(userId, "userId не должен быть null");
        Assert.assertNotNull(isbns, "список ISBN не должен быть null");
        Map<String, Object> body = Map.of("userId", userId, "collectionOfIsbns", isbns.stream()
                        .map(isbn -> Map.of("isbn", isbn))
                        .toList()
        );
        return postWithAuth(Endpoints.BOOKS, body);
    }
}
