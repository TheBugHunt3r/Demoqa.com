package core.base;

import core.config.ConfigManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.getConfig().apiUrl();
    }
}
