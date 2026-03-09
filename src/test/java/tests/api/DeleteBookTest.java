package tests.api;

import api.client.BookStoreClient;
import api.models.*;
import core.base.ApiBaseTest;
import core.config.TokenManager;
import core.helpers.AuthHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteBookTest extends ApiBaseTest {

    private final BookStoreClient bookClient = new BookStoreClient();

    @BeforeClass
    public void auth() {
        AuthHelper.authorize();
    }

    @Test
    @Step("Тест удаления книги из коллекции")
    @Description("Успешное добавление и удаление книги из коллекции")
    public void deleteBookTest() {
        String userId = TokenManager.getUserId();
        BooksResponse allBooks = bookClient.getBooks().as(BooksResponse.class);
        String isbn = allBooks.getBooks().get(0).getIsbn();
        bookClient.addBook(userId, List.of(isbn)).then().statusCode(201);
        bookClient.deleteBook(userId, isbn).then().statusCode(204);
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
        Response finalCheck = bookClient.getUserBooks(userId);
        finalCheck.then().statusCode(200);
        System.out.println("сервер подтвердил удаление (204) и доступ к корзине (200)");
    }
}