package steps.UISteps;

import com.codeborne.selenide.Condition;
import org.testng.asserts.SoftAssert;
import pages.HomePageLoggedIn;
import pages.LoginPage;
import steps.base.BaseUISteps;

public class LoginUISteps extends BaseUISteps<LoginUISteps> {
    public LoginUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public LoginUISteps verifyLoginPageVisible() {
        checkRequiredElementsVisibility(LoginPage.getRequiredElements());
        return this;
    }

    public LoginUISteps loginAs(String email, String password) {
        performStep(LoginPage.EMAIL, "Log in as " + email, () -> {
            elementActions.setValue(LoginPage.EMAIL.getElement(), email);
            elementActions.setValue(LoginPage.PASSWORD.getElement(), password);
            elementActions.click(LoginPage.LOGIN_BUTTON.getElement());
        });
        return this;
    }

    public LoginUISteps verifyLoginSuccess(String expectedEmail) {
        performStep(HomePageLoggedIn.ACCOUNT_BUTTON, "Verify login success", () -> {
            elementActions.click(HomePageLoggedIn.ACCOUNT_BUTTON.getElement());
            elementActions.shouldBeVisible(HomePageLoggedIn.LOGOUT_BUTTON.getElement());
            elementActions.shouldBeVisible(HomePageLoggedIn.GO_TO_PROFILE_BUTTON
                    .getElement()
                    .$("span")
                    .shouldHave(Condition.text(expectedEmail)));
        });
        return this;
    }

    public LoginUISteps clickOnRegisterLink() {
        performStep(LoginPage.REGISTER_LINK, "Click on Register link", () -> {
            elementActions.click(LoginPage.REGISTER_LINK.getElement());
        });
        return this;
    }
}
