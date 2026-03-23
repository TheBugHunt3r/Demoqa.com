package tests.ui;

import core.base.UiBaseTest;
import core.helpers.DatabaseHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class BookStoreTest extends UiBaseTest {

    @Test
    @Step("Тест поиска книги")
    @Description("Поиск книги по названию")
    public void searchBookTest() {
        String bookName = "Programming JavaScript Applications";
        log.info("тест поиска книги '{}'", bookName);
        DatabaseHelper.prepareTestData(config.username(), config.password(), bookName);
        loginPage.open(config.baseUrl() + "/login")
                .login(config.username(), config.password())
                .goToBookStore()
                .searchAndVerify(bookName);
        log.info("тест успешно пройден");
    }
}
