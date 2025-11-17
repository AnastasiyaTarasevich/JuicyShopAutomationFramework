package tests.base;

import java.lang.reflect.Method;
import annotations.RegisterUser;
import config.Config;
import dtos.registration.RegisterRequestDTO;
import dtos.registration.RegisterResponseDTO;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import steps.APISteps.RegisterApiSteps;

@Epic("API Tests")
public class BaseAPITest {
    protected static final Logger log = LoggerFactory.getLogger(BaseAPITest.class);
    protected RegisterRequestDTO createdUser;
    protected RegisterResponseDTO registeredUser;
    protected final SoftAssert softAssert = new SoftAssert();
    protected final RegisterApiSteps registerApiSteps = new RegisterApiSteps(softAssert);

    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {
        log.info("Setting up REST-assured...");
        RestAssured.baseURI = Config.getBaseUrl();
        if (!method.isAnnotationPresent(RegisterUser.class)) return;

        createdUser = registerApiSteps.createTestUser();
        registeredUser = registerApiSteps.registerViaApi(createdUser);
    }

}
