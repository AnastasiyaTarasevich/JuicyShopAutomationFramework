package tests.UITests;

import annotations.RegisterUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.UISteps.HomeUISteps;
import steps.UISteps.LoginUISteps;
import tests.base.BaseUITest;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;


public class LoginTest extends BaseUITest {
    private final SoftAssert softAssert = new SoftAssert();
    private final HomeUISteps homeSteps = new HomeUISteps(softAssert);
    private final LoginUISteps loginSteps = new LoginUISteps(softAssert);

    @Test(groups = {TestGroups.UI})
    @Feature("Login")
    @Description("This test attempts to log into the website using valid login and password")
    @Severity(CRITICAL)
    @TmsLink("JUICYSHOP-1")
    @RegisterUser
    public void validUserLoginTest() {

        homeSteps.closeWelcomeBanner()
                .verifyHomePageVisible()
                .goToLoginPage();

        loginSteps.verifyLoginPageVisible()
                .loginAs(createdUser.getEmail(), createdUser.getPassword())
                .verifyLoginSuccess(createdUser.getEmail());
        softAssert.assertAll();
    }
}
