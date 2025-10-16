package tests.UITests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.HomeSteps;
import steps.LoginSteps;
import tests.BaseTest;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;


public class LoginTest extends BaseTest {
    private final SoftAssert softAssert = new SoftAssert();
    private final HomeSteps homeSteps = new HomeSteps(softAssert);
    private final LoginSteps loginSteps = new LoginSteps(softAssert);

    @Test(groups = {TestGroups.UI, TestGroups.DEBUG})
    @Feature("Login")
    @Description("This test attempts to log into the website using a login and a password")
    @Severity(CRITICAL)
    @TmsLink("JUICYSHOP-1")
    public void validUserLoginTest() {

        homeSteps.closeWelcomeBanner();
        homeSteps.verifyHomePageVisible();
        homeSteps.goToLoginPage();

        loginSteps.verifyLoginPageVisible();
        loginSteps.loginAs("demo@juice-sh.op", "demo123");

        softAssert.assertAll();
    }
}
