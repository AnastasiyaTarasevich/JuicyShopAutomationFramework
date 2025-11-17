package tests.UITests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.UISteps.HomeUISteps;
import tests.base.BaseUITest;
import utils.Language;
import utils.RandomDataGenerator;
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

    }

    @Test(groups = {TestGroups.UI})
    @Feature("Switch language")
    @Description("User can switch the language")
    @Severity(CRITICAL)
    public void switchLanguage() {
        Language language = RandomDataGenerator.randomLanguage();
        homeSteps
                .closeWelcomeBanner()
                .closeCookiesBanner()
                .clickOnSwitchLanguageButton()
                .clickOnLanguageRadioButton(language.name)
                .refreshHomePage()
                .verifyLanguageHasChanged(language);

    }

}
