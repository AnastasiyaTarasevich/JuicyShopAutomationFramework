package steps;

import com.codeborne.selenide.SelenideElement;
import org.testng.asserts.SoftAssert;
import pages.PageElement;

import static com.codeborne.selenide.Condition.visible;

public abstract class BaseSteps {

    protected SoftAssert softAssert;

    public BaseSteps(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }

    protected void click(SelenideElement element) {
        element.shouldBe(visible).click();
    }

    protected void setValue(SelenideElement element, String value) {
        element.shouldBe(visible).setValue(value);
    }

    protected boolean isElementDisplayed(SelenideElement element) {
        return element.isDisplayed();
    }
    protected void isElementDisplayed(SelenideElement element, String elementName) {
        boolean visible = element.isDisplayed();
        softAssert.assertTrue(visible, "Element should be visible: " + elementName);
    }

    protected void checkPageVisible(PageElement[] requiredElements) {
        for (PageElement element : requiredElements) {
            if (!isElementDisplayed(element.getElement())) {
                throw new AssertionError("Required element not visible: " + element);
            }
        }
    }
}
