package steps;

import actions.ElementActions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import pages.PageElement;

//TODO move non steps methods to BrowserActions
public abstract class BaseSteps {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected SoftAssert softAssert;
    protected ElementActions elementActions;

    public BaseSteps(SoftAssert softAssert) {
        this.softAssert = softAssert;
        this.elementActions = new ElementActions();
    }

    /**
     * Universal method for business steps
     * description — description of the step
     * pageElement — one of the page element to use pageName
     * actions — technical actions that performed on this step
     */
    protected void performStep(PageElement pageElement, String description, Runnable action) {
        String stepName = pageElement.getPageName() + " — " + description;
        Allure.step(stepName, action::run);
    }

    protected void performStep(String description, Runnable action) {
        Allure.step(description, action::run);
    }

    protected void checkRequiredElementsVisibility(PageElement[] elements) {
        if (elements.length == 0) return;
        String pageName = elements[0].getPageName();

        Allure.step(pageName + " — Verify required elements are visible", () -> {
            for (PageElement element : elements) {
                if (element.isRequired()) {
                    boolean visible = elementActions.isElementDisplayed(element.getElement());
                    softAssert.assertTrue(visible,
                            "Required element not visible: " + element.getElement().name() + " on page " + pageName);
                }
            }
        });
    }

    @Step("{element.pageName} — Verify visibility of {elementName}")
    protected void isElementDisplayed(PageElement element, String elementName) {
        boolean visible = elementActions.isElementDisplayed(element.getElement());
        softAssert.assertTrue(visible, "Element should be visible: " + elementName);
    }

    protected String getCurrentWindowHandle() {
        String handle = WebDriverRunner.getWebDriver().getWindowHandle();
        return handle;
    }

    protected void switchToWindow(String windowHandle) {
        WebDriverRunner.getWebDriver().switchTo().window(windowHandle);
        log.info("Switched to tab: {}", WebDriverRunner.getWebDriver().getTitle());
    }

    protected void switchSeleniumContextToNewTab() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String originalWindow = driver.getWindowHandle();
        log.info("Current window handle: {}", originalWindow);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                switchToWindow(windowHandle);
                break;
            }
        }
    }

    protected String currentUrl() {
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
        log.info("Current URL: {}", url);
        return url;
    }

    protected void goBack() {
        String url = currentUrl();
        log.info("Go to the previous page from URL: {}", url);
        Selenide.back();
    }

    protected void refreshPage() {
        String url = currentUrl();
        log.info("Refresh page: {}", url);
        Selenide.refresh();
    }

    protected void closeCurrentTab() {
        String url = currentUrl();
        String windowHandle = getCurrentWindowHandle();
        WebDriverRunner.getWebDriver().close();
        log.info("Closed tab: {} with URL: {}", windowHandle, url);
    }
}
