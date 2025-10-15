package steps;

import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BaseSteps {
    public LoginSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public void verifyLoginPageVisible() {
        checkRequiredElementsVisibility(LoginPage.getRequiredElements());
    }

    public void loginAs(String email, String password) {
        performStep(LoginPage.EMAIL, "Log in as " + email, () -> {
            elementActions.setValue(LoginPage.EMAIL.getElement(), email);
            elementActions.setValue(LoginPage.PASSWORD.getElement(), password);
            elementActions.click(LoginPage.LOGIN_BUTTON.getElement());
        });
    }

//    public void verifyLoginSuccess() {
//        isElementDisplayed(LoginPage.USER_ICON, "User icon");
//    }
}
