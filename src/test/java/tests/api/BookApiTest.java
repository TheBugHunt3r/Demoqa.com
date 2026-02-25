package tests.api;

import api.client.BookStoreClient;
import api.models.Book;
import api.models.BooksResponse;
import core.base.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookApiTest extends ApiBaseTest {

    BookStoreClient bookClient = new BookStoreClient();

    @Test
    public void getBooksList() {
        Response response = bookClient.getBooks();
        response.then().statusCode(200);
        BooksResponse booksResponse = response.as(BooksResponse.class);
        Assert.assertFalse(booksResponse.getBooks().isEmpty(), "cписок книг пуст");
        Book firstBook = booksResponse.getBooks().get(0);
        Assert.assertNotNull(firstBook.getIsbn(), "у книги должен быть ISBN");
        Assert.assertNotNull(firstBook.getTitle(), "у книги должно быть название");
        Assert.assertNotNull(firstBook.getAuthor(), "у книги должен быть автор");
    }
}
