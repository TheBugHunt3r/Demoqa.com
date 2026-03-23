package core.base;

import core.config.ConfigManager;
import core.utils.TestListener;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.getConfig().apiUrl();
    }
}
