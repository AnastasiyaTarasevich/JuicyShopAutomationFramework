package tests.base;

import java.lang.reflect.Method;
import annotations.RegisterUser;
import com.codeborne.selenide.Configuration;
import config.Config;
import dtos.registration.RegisterRequestDTO;
import dtos.registration.RegisterResponseDTO;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import steps.APISteps.RegisterApiSteps;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Epic("UI Tests")
public abstract class BaseUITest {
    protected static final Logger log = LoggerFactory.getLogger(BaseUITest.class);
    protected RegisterRequestDTO createdUser;
    protected RegisterResponseDTO registeredUser;
    protected final SoftAssert softAssert = new SoftAssert();
    protected final RegisterApiSteps registerApiSteps = new RegisterApiSteps(softAssert);

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws Exception {
        log.info("Setting up selenide...");
        Configuration.browser = "chrome";
        Configuration.baseUrl = Config.getBaseUrl();

        boolean isCi = System.getenv("CI") != null;
        Configuration.headless = isCi;

        ChromeOptions options = new ChromeOptions();
        if (isCi) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-ua");
        }
        Configuration.browserCapabilities = options;

        log.info("Open start page: {}", Configuration.baseUrl);
        open("/");
        if (!method.isAnnotationPresent(RegisterUser.class)) return;

        RestAssured.baseURI = Config.getBaseUrl();
        createdUser = registerApiSteps.createTestUser();
        registeredUser = registerApiSteps.registerViaApi(createdUser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Tearing down selenide...");
        closeWebDriver();
    }
}
