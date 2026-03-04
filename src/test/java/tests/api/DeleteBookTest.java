package tests.api;

import api.client.BookStoreClient;
import api.models.*;
import api.specifications.ResponseSpec;
import core.base.ApiBaseTest;
import core.config.TokenManager;
import core.helpers.AuthHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteBookTest extends ApiBaseTest {

    BookStoreClient bookClient = new BookStoreClient();

    @BeforeClass
    public void auth() {
        AuthHelper.authorize();
    }

    @Test
    @Step("Тест удаления книги")
    @Description("Проверка успешного удаления книги")
    public void deleteBookTest() {
        BooksResponse booksResponse = bookClient.getBooks()
                        .then()
                        .spec(ResponseSpec.statusCode200())
                        .extract()
                        .as(BooksResponse.class);
        String isbn = booksResponse.getBooks().get(0).getIsbn();
        AddBookRequest booksRequest = AddBookRequest.builder()
                .userId(TokenManager.getUserId())
                .collectionOfIsbns(List.of(new Isbn(isbn)))
                .build();
        bookClient.addBook(booksRequest)
                .then()
                .spec(ResponseSpec.statusCode201());
        DeleteBookRequest deleteRequest = DeleteBookRequest.builder()
                .isbn(isbn)
                .userId(TokenManager.getUserId())
                .build();
        bookClient.deleteBook(deleteRequest)
                .then()
                .spec(ResponseSpec.statusCode204());
    }
}
