package steps;

import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

public class HomeSteps extends BaseSteps {

    public HomeSteps(SoftAssert softAssert) {
        super(softAssert);
    }
    public void verifyHomePageVisible() {
        checkRequiredElementsVisibility(HomePage.getRequiredElements());
    }

    public void closeWelcomeBanner() {
        performStep(HomePage.CLOSE_WELCOME_BANNER, "Close Welcome Banner", ()->{
            elementActions.click(HomePage.CLOSE_WELCOME_BANNER.getElement());
        });

    }
    public void goToLoginPage() {
        performStep(HomePage.ACCOUNT_BUTTON, "Navigate to Login Page", () -> {
            elementActions.click(HomePage.ACCOUNT_BUTTON.getElement());
            elementActions.click(HomePage.LOGIN_PAGE_BUTTON.getElement());
        });
    }
}
