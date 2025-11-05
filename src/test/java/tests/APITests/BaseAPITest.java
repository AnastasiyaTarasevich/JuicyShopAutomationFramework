package tests.APITests;

import config.Config;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

@Epic("API Tests")
public class BaseAPITest {

    protected final static Logger log = LoggerFactory.getLogger(BaseAPITest.class);

    @BeforeClass(alwaysRun = true)
    public void setup() {
        log.info("Setting up REST-assured...");
        RestAssured.baseURI = Config.getBaseUrl() + "/rest/";
    }
}
