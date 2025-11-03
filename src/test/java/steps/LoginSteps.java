package steps;

import com.codeborne.selenide.Condition;
import config.Config;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.HomePageLoggedIn;
import pages.LoginPage;

public class LoginSteps extends BaseSteps {
    public LoginSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public LoginSteps verifyLoginPageVisible() {
        checkRequiredElementsVisibility(LoginPage.getRequiredElements());
        return this;
    }

    public LoginSteps loginAs(String email, String password) {
        performStep(LoginPage.EMAIL, "Log in as " + email, () -> {
            elementActions.setValue(LoginPage.EMAIL.getElement(), email);
            elementActions.setValue(LoginPage.PASSWORD.getElement(), password);
            elementActions.click(LoginPage.LOGIN_BUTTON.getElement());
        });
        return this;
    }

    public LoginSteps verifyLoginSuccess() {
        performStep(HomePageLoggedIn.ACCOUNT_BUTTON, "Verify login success", () -> {
            elementActions.click(HomePageLoggedIn.ACCOUNT_BUTTON.getElement());
            elementActions.shouldBeVisible(HomePageLoggedIn.LOGOUT_BUTTON.getElement());
            elementActions.shouldBeVisible(HomePageLoggedIn.GO_TO_PROFILE_BUTTON
                    .getElement()
                    .$("span")
                    .shouldHave(Condition.text(Config.getUserLogin())));
        });
        return this;
    }
}
