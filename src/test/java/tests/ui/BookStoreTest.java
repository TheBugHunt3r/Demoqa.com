package tests.ui;

import core.base.UiBaseTest;
import core.helpers.DatabaseHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class BookStoreTest extends UiBaseTest {

    @Test
    @Step("Тест поиска книги")
    @Description("Поиск книги по названию")
    public void searchBookTest() {
        String user = "admin6";
        String pass = DatabaseHelper.getDataFromDb("password", user);
        String targetBook = DatabaseHelper.getDataFromDb("target_book", user);
        driver.get(config.baseUrl() + "/login");
        int booksCount = new LoginPage(driver)
                .login(user, pass)
                .goToBookStore()
                .searchBook(targetBook)
                .waitForBookVisible(targetBook)
                .getVisibleBooksCount();
        Assert.assertTrue(booksCount > 0, "книги по запросу '" + targetBook + "' не найдены");
    }
}
