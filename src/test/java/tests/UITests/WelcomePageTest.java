package tests.UITests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.UISteps.HomeUISteps;
import tests.base.BaseUITest;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class WelcomePageTest extends BaseUITest {

    private final SoftAssert softAssert = new SoftAssert();
    private final HomeUISteps homeSteps = new HomeUISteps(softAssert);

    @Test(groups = {TestGroups.UI})
    @Feature("Welcome Page")
    @Description("User can interact with banner elements and close it")
    @Severity(CRITICAL)
    @TmsLinks({@TmsLink("JUICYSHOP-2"), @TmsLink("JUICYSHOP-3"), @TmsLink("JUICYSHOP-5"),
            @TmsLink("JUICYSHOP-6"), @TmsLink("JUICYSHOP-7"), @TmsLink("JUICYSHOP-8"),
            @TmsLink("JUICYSHOP-9"), @TmsLink("JUICYSHOP-10"), @TmsLink("JUICYSHOP-4")
    })
    public void interactWithWelcomePageBannerAndCloseItTest() {

        homeSteps
                .verifyUserRedirectToOWASPUrlAndBack()
                .verifyUserRedirectToJuicyUrlAndBack()
                .clickOnTipsButton()
                .verifyTipsChangesOnClick()
                .closeTipsBanner()
                .verifyHomePageVisible();

        softAssert.assertAll();

    }

    @Test(groups = {TestGroups.UI})
    @Feature("Search on Welcome Page")
    @Description("User can search products without log in")
    @Severity(CRITICAL)
    public void searchWithoutLogIn() {
        String searchQuery = "apple";
        homeSteps
                .closeWelcomeBanner()
                .clickOnSearchButton()
                .searchText(searchQuery)
                .verifySearchResults(searchQuery);

        softAssert.assertAll();
    }

}
