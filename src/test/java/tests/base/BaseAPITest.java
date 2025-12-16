package tests.base;

import java.lang.reflect.Method;
import java.util.List;
import annotations.LoginUser;
import annotations.RegisterUser;
import annotations.SeveralProductsToBasket;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.restassured.ReportPortalRestAssuredLoggingFilter;
import config.Config;
import dtos.login.LoginResponseDTO;
import dtos.products.ProductDTO;
import dtos.registration.RegisterRequestDTO;
import dtos.registration.RegisterResponseDTO;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import steps.APISteps.BasketApiSteps;
import steps.APISteps.LoginApiSteps;
import steps.APISteps.ProductsApiSteps;
import steps.APISteps.RegisterApiSteps;
import utils.RandomDataGenerator;

@Epic("API Tests")
public class BaseAPITest {
    protected static final Logger log = LoggerFactory.getLogger(BaseAPITest.class);
    protected RegisterRequestDTO createdUser;
    protected RegisterResponseDTO registeredUser;
    protected LoginResponseDTO loggedInUser;
    protected final SoftAssert softAssert = new SoftAssert();
    protected final RegisterApiSteps registerApiSteps = new RegisterApiSteps(softAssert);
    protected final LoginApiSteps loginApiSteps = new LoginApiSteps(softAssert);
    protected final BasketApiSteps basketApiSteps = new BasketApiSteps(softAssert);
    protected final ProductsApiSteps productsApiSteps = new ProductsApiSteps(softAssert);

    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {
        log.info("Setting up REST-assured...");
        RestAssured.baseURI = Config.getBaseUrl();
        if ("true".equals(System.getenv("CI"))) {
            RestAssured.filters(new ReportPortalRestAssuredLoggingFilter(42, LogLevel.INFO));
        }

        createdUser = registerApiSteps.createTestUser();
        if (method.isAnnotationPresent(RegisterUser.class)) {
            registeredUser = registerApiSteps.registerViaApi(createdUser);
        }
        if (method.isAnnotationPresent(LoginUser.class)) {
            loggedInUser = loginApiSteps.loginAsViaApi(createdUser.getEmail(), createdUser.getPassword());
        }
        if (method.isAnnotationPresent(SeveralProductsToBasket.class)) {
            List<ProductDTO> productDTOList = productsApiSteps
                    .getRandomProductsFromSite(RandomDataGenerator.getRandomIntFrom1To10());
            basketApiSteps.addSeveralProductsToBasket(loggedInUser.getAuthentication().getToken(),
                    loggedInUser.getAuthentication().getBid(), productDTOList);
        }

    }

}
