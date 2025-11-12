package steps.UISteps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Keys;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.TipsBanner;
import pages.WelcomeBanner;
import steps.base.BaseUISteps;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class HomeUISteps extends BaseUISteps {

    public HomeUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public HomeUISteps verifyHomePageVisible() {
        checkRequiredElementsVisibility(HomePage.getRequiredElements());
        return this;
    }

    public HomeUISteps gotoOWASPLink() {
        performStep(WelcomeBanner.OWASP_LINK, "Navigate to OWASP Link", () -> {
            elementActions.click(WelcomeBanner.OWASP_LINK.getElement());
            switchSeleniumContextToNewTab();

        });
        return this;
    }

    public HomeUISteps verifyUserRedirectToOWASPUrlAndBack() {
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

    public HomeUISteps closeWelcomeBanner() {
        performStep(WelcomeBanner.CLOSE_WELCOME_BANNER_BUTTON, "Close Welcome Banner", () -> {
            elementActions.click(WelcomeBanner.CLOSE_WELCOME_BANNER_BUTTON.getElement());
        });
        return this;
    }

    public HomeUISteps goToLoginPage() {
        performStep(HomePage.ACCOUNT_BUTTON, "Navigate to Login Page", () -> {
            elementActions.click(HomePage.ACCOUNT_BUTTON.getElement());
            elementActions.click(HomePage.LOGIN_PAGE_BUTTON.getElement());
        });
        return this;
    }

    public HomeUISteps goToJuicyLink() {
        performStep(WelcomeBanner.JUICY_SHOP_LINK, "Navigate to Juicy Shop", () -> {
            elementActions.click(WelcomeBanner.JUICY_SHOP_LINK.getElement());
            switchSeleniumContextToNewTab();
        });
        return this;
    }

    public HomeUISteps verifyUserRedirectToJuicyUrlAndBack() {
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

    public HomeUISteps clickOnTipsButton() {
        performStep(WelcomeBanner.TIPS_BUTTON, "Click on Tips", () -> {
            elementActions.click(WelcomeBanner.TIPS_BUTTON.getElement());
        });
        return this;
    }

    public HomeUISteps verifyTipsChangesOnClick() {
        performStep(TipsBanner.TIPS_HACKER_IMG, "Verify Tips Changes", () -> {
            Selenide.sleep(10000);
            String firstText = TipsBanner.DESCRIPTION.getElement().getText();
            elementActions.doubleClick(TipsBanner.TIPS_HACKER_IMG.getElement());
            String secondText = TipsBanner.DESCRIPTION.getElement().getText();
            softAssert.assertFalse(firstText.equalsIgnoreCase(secondText), "Tips did not change");
        });
        return this;
    }

    public HomeUISteps closeTipsBanner() {
        performStep(TipsBanner.CLOSE_BUTTON, "Close Tips", () -> {
            elementActions.click(TipsBanner.CLOSE_BUTTON.getElement());
        });
        return this;
    }

    public HomeUISteps clickOnSearchButton() {
        performStep(HomePage.SEARCH_BUTTON, "Click on search button", () ->
        {
            elementActions.click(HomePage.SEARCH_BUTTON.getElement());
        });
        return this;
    }

    public HomeUISteps searchText(String searchText) {
        performStep(HomePage.SEARCH_INPUT, "Set text in search input", () ->
        {
            elementActions.setValue(HomePage.SEARCH_INPUT.getElement(), searchText);
            elementActions.sendKeys(HomePage.SEARCH_INPUT.getElement(), Keys.ENTER);
        });
        return this;
    }

    public HomeUISteps verifySearchResults(String searchQuery) {
        performStep(HomePage.SEARCH_INPUT, "Verify search results", () -> {
            ElementsCollection cards = HomePage.PRODUCT_CARDS.getElements();
            cards.shouldHave(sizeGreaterThan(0));
            cards.forEach(card -> card.shouldHave(text(searchQuery)));
        });
        return this;
    }
}
