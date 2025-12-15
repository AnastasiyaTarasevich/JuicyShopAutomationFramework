package steps.base;

import java.util.Date;
import actions.BrowserActions;
import actions.ElementActions;
import com.codeborne.selenide.SelenideElement;
import com.epam.reportportal.service.ReportPortal;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import pages.PageElement;

public abstract class BaseUISteps<SELF extends BaseUISteps<SELF>> {
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
        Allure.step(stepName, () -> {
            try {
                ReportPortal.emitLog("STEP: " + stepName, "INFO", new Date());
                action.run();
            } catch (Exception e) {
                ReportPortal.emitLog("STEP FAILED: " + stepName + "\n" + e.getMessage(), "ERROR", new Date());
                throw e;
            }
        });
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
    public SELF isElementDisplayed(PageElement element, boolean shouldDisplayed) {
        boolean isDisplayed = elementActions.isElementDisplayed(element.getElement());
        if (shouldDisplayed) {
            softAssert.assertTrue(isDisplayed, "Element should be displayed: " + element.getElement().name());
        } else {
            softAssert.assertFalse(isDisplayed, "Element should not be displayed: " + element.getElement().name());
        }
        return (SELF) this;
    }

    @Step("{element.pageName} - Click on the {element}")
    public SELF clickOnElement(PageElement element) {
        SelenideElement el = element.getElement();
        if (el.isDisplayed()) {
            elementActions.click(el);
        } else {
            elementActions.clickWithoutVisible(el);
        }
        return (SELF) this;
    }

}
