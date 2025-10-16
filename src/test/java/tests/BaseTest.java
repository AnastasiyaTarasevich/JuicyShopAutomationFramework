package tests;

import com.codeborne.selenide.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public abstract class BaseTest {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        log.info("Setting up selenide...");
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://localhost:3000";
        log.info("Open start page: {}", Configuration.baseUrl);
        open("/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Tearing down selenide...");
        closeWebDriver();
    }
}
