package steps;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BaseSteps {
    public LoginSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    @Step("Close welcome banner if present")
    public void closeWelcomeBanner() {
        if (HomePage.CLOSE_WELCOME_BANNER.getElement().isDisplayed()) {
            click(HomePage.CLOSE_WELCOME_BANNER.getElement());
        }
    }

    @Step("Navigate to login page")
    public void goToLoginPage() {
        click(HomePage.ACCOUNT_BUTTON.getElement());
        click(HomePage.LOGIN_PAGE_BUTTON.getElement());
    }

    @Step("Login as user with email: {email}")
    public void loginAs(String email, String password) {
        checkPageVisible(LoginPage.getRequiredElements());

        setValue(LoginPage.EMAIL.getElement(), email);
        setValue(LoginPage.PASSWORD.getElement(), password);
        click(LoginPage.LOGIN_BUTTON.getElement());
    }
}
