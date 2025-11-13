package steps.base;

import actions.BrowserActions;
import actions.ElementActions;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import pages.PageElement;

public abstract class BaseUISteps {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected SoftAssert softAssert;
    protected ElementActions elementActions;
    protected BrowserActions browserActions;

    public BaseUISteps(SoftAssert softAssert) {
        this.softAssert = softAssert;
        this.elementActions = new ElementActions();
        this.browserActions = new BrowserActions();
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

}
