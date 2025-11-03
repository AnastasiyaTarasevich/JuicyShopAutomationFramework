package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.TipsBanner;
import pages.WelcomeBanner;
import static com.codeborne.selenide.Selenide.$;

public class HomeSteps extends BaseSteps {

    public HomeSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public HomeSteps verifyHomePageVisible() {
        checkRequiredElementsVisibility(HomePage.getRequiredElements());
        return this;
    }

    public HomeSteps gotoOWASPLink() {
        performStep(WelcomeBanner.OWASP_LINK, "Navigate to OWASP Link", () -> {
            elementActions.click(WelcomeBanner.OWASP_LINK.getElement());
            switchSeleniumContextToNewTab();

        });
        return this;
    }

    public HomeSteps verifyUserRedirectToOWASPUrlAndBack() {
        performStep(WelcomeBanner.OWASP_LINK, "Verify user redirect to OWASP site and back", () -> {
            String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();
            gotoOWASPLink();
            elementActions.shouldBeVisible($("body"));
            softAssert.assertTrue(currentUrl().contains("owasp"), "User did not navigate to OWASP page");
            closeCurrentTab();
            switchToWindow(originalWindow);
        });
        return this;
    }

    public HomeSteps closeWelcomeBanner() {
        performStep(WelcomeBanner.CLOSE_WELCOME_BANNER_BUTTON, "Close Welcome Banner", () -> {
            elementActions.click(WelcomeBanner.CLOSE_WELCOME_BANNER_BUTTON.getElement());
        });
        return this;
    }

    public HomeSteps goToLoginPage() {
        performStep(HomePage.ACCOUNT_BUTTON, "Navigate to Login Page", () -> {
            elementActions.click(HomePage.ACCOUNT_BUTTON.getElement());
            elementActions.click(HomePage.LOGIN_PAGE_BUTTON.getElement());
        });
        return this;
    }

    public HomeSteps goToJuicyLink() {
        performStep(WelcomeBanner.JUICY_SHOP_LINK, "Navigate to Juicy Shop", () -> {
            elementActions.click(WelcomeBanner.JUICY_SHOP_LINK.getElement());
            switchSeleniumContextToNewTab();
        });
        return this;
    }

    public HomeSteps verifyUserRedirectToJuicyUrlAndBack() {
        performStep(WelcomeBanner.JUICY_SHOP_LINK, "Verify user redirect to Juicy Shop site and back", () -> {
                    String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();
                    goToJuicyLink();
                    elementActions.shouldBeVisible($("body"));
                    softAssert.assertTrue(currentUrl().contains("juice"), "User did not navigate to Juicy Shop page");
                    closeCurrentTab();
                    switchToWindow(originalWindow);
                }

        );
        return this;
    }

    public HomeSteps clickOnTipsButton() {
        performStep(WelcomeBanner.TIPS_BUTTON, "Click on Tips", () -> {
            elementActions.click(WelcomeBanner.TIPS_BUTTON.getElement());
        });
        return this;
    }

    public HomeSteps verifyTipsChangesOnClick() {
        performStep(TipsBanner.TIPS_HACKER_IMG, "Verify Tips Changes", () -> {
            Selenide.sleep(10000);
            String firstText = TipsBanner.DESCRIPTION.getElement().getText();
            elementActions.doubleClick(TipsBanner.TIPS_HACKER_IMG.getElement());
            String secondText = TipsBanner.DESCRIPTION.getElement().getText();
            softAssert.assertFalse(firstText.equalsIgnoreCase(secondText), "Tips did not change");
        });
        return this;
    }

    public HomeSteps closeTipsBanner() {
        performStep(TipsBanner.CLOSE_BUTTON, "Close Tips", () -> {
            elementActions.click(TipsBanner.CLOSE_BUTTON.getElement());
        });
        return this;
    }
}
