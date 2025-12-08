package tests.UITests;

import annotations.LoginUser;
import annotations.RegisterUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import steps.UISteps.HomeUISteps;
import steps.UISteps.LoginUISteps;
import steps.UISteps.RegisterUISteps;
import tests.base.BaseUITest;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class AuthTest extends BaseUITest {
    private final SoftAssert softAssert = new SoftAssert();
    private final HomeUISteps homeSteps = new HomeUISteps(softAssert);
    private final LoginUISteps loginSteps = new LoginUISteps(softAssert);
    private final RegisterUISteps registerUISteps = new RegisterUISteps(softAssert);

    @Test(groups = {TestGroups.UI})
    @Feature("Registration")
    @Description("Native registration")
    @Severity(CRITICAL)
    public void registration() {
        homeSteps.closeWelcomeBanner()
                .verifyHomePageVisible()
                .goToLoginPage();
        loginSteps
                .verifyLoginPageVisible()
                .clickOnRegisterLink();
        createdUser = registerApiSteps.createTestUser();
        registerUISteps.verifyRegisterPageVisible()
                .registerAs(createdUser.getEmail(), createdUser.getPassword(),
                        createdUser.getPasswordRepeat(), createdUser.getSecurityQuestion(),
                        createdUser.getSecurityAnswer());
        loginSteps
                .verifyLoginPageVisible()
                .loginAs(createdUser.getEmail(), createdUser.getPassword())
                .verifyLoginSuccess(createdUser.getEmail());
        softAssert.assertAll();
    }

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

    @Test(groups = {TestGroups.UI})
    @Feature("Logout")
    @Description("User is able to logout")
    @Severity(CRITICAL)
    @RegisterUser
    @LoginUser
    public void logoutTest() {
        loginSteps.clickOnElement(HomePage.ACCOUNT_BUTTON)
                .clickOnElement(HomePage.LOGOUT_BUTTON)
                .clickOnElement(HomePage.ACCOUNT_BUTTON)
                .isElementDisplayed(HomePage.LOGIN_PAGE_BUTTON, true);
        softAssert.assertAll();
    }
}
