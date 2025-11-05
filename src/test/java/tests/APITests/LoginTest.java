package tests.APITests;

import config.Config;
import dtos.LoginResponseDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.APISteps.LoginSteps;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class LoginTest extends BaseAPITest {

    private final SoftAssert softAssert = new SoftAssert();
    private final LoginSteps loginSteps = new LoginSteps(softAssert);

    @Test(groups = {TestGroups.API, TestGroups.DEBUG})
    @Feature("Login")
    @Description("This test attempts to log into the website using valid login and password")
    @Severity(CRITICAL)
    public void validLoginTest() {
        LoginResponseDTO response = loginSteps.loginAsViaApi(Config.getUserLogin(), Config.getUserPassword());
        loginSteps
                .verifyCorrectEmail(response, Config.getUserLogin())
                .verifyToken(response);

        softAssert.assertAll();
    }
}
