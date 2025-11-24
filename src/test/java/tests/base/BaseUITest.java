package tests.base;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import annotations.RegisterUser;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Config;
import dtos.registration.RegisterRequestDTO;
import dtos.registration.RegisterResponseDTO;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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

    @BeforeSuite(alwaysRun = true)
    public void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws Exception {
        log.info("Setting up selenide...");
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.baseUrl = Config.getBaseUrl();

        boolean isCi = System.getenv("CI") != null;
        Configuration.headless = isCi;

        ChromeOptions options = new ChromeOptions();
        if (isCi) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
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
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
                String html = WebDriverRunner.source();
                Allure.addAttachment("Page Source", "text/html", html);
            }
        } catch (Exception e) {
            log.warn("Failed to capture screenshot/page source: " + e.getMessage());
        } finally {
            closeWebDriver();
        }
    }

}
