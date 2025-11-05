package tests.UITests;

import com.codeborne.selenide.Selenide;
import config.Config;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.UISteps.HomeSteps;
import steps.UISteps.LoginSteps;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;


public class LoginTest extends BaseUITest {
    private final SoftAssert softAssert = new SoftAssert();
    private final HomeSteps homeSteps = new HomeSteps(softAssert);
    private final LoginSteps loginSteps = new LoginSteps(softAssert);

    @Test(groups = {TestGroups.UI, TestGroups.DEBUG})
    @Feature("Login")
    @Description("This test attempts to log into the website using valid login and password")
    @Severity(CRITICAL)
    @TmsLink("JUICYSHOP-1")
    public void validUserLoginTest() {

        homeSteps.closeWelcomeBanner()
                .verifyHomePageVisible()
                .goToLoginPage();

        loginSteps.verifyLoginPageVisible()
                    .loginAs(Config.getUserLogin(), Config.getUserPassword())
                    .verifyLoginSuccess();
        Selenide.sleep(2000);
        softAssert.assertAll();
    }
}
