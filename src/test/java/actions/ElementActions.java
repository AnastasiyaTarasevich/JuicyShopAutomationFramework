package actions;

import java.time.Duration;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.*;

public class ElementActions {
    public void click(SelenideElement element) {
        shouldBeVisible(element).click();
    }

    public void click(SelenideElement element, int timeoutSeconds) {
        try {
            element.click();
        } catch (ElementNotInteractableException | UIAssertionError e) {
            Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", element);
            shouldBeEnabled(element, timeoutSeconds);
            shouldBeVisible(element, timeoutSeconds)
                    .click();
        }
    }

    public void clickWithoutVisible(SelenideElement element) {
        element.click();
    }

    public void setValue(SelenideElement element, String value) {
        shouldBeVisible(element).setValue(value);
    }

    public void sendKeys(SelenideElement element, String value) {
        shouldBeVisible(element).sendKeys(value);
    }

    public void sendKeys(SelenideElement element, Keys key) {
        shouldBeVisible(element).sendKeys(key);
    }

    public boolean isElementDisplayed(SelenideElement element) {
        try {
            shouldBeVisible(element, 5);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SelenideElement findBy(ElementsCollection elements, WebElementCondition condition,
                                  int timeout, boolean scroll) {
        SelenideElement element = elements.findBy(condition);
        if (scroll) {
            try {
                return shouldExist(element, timeout);
            } catch (Throwable e) {

            }
            if (!element.isDisplayed()) {
                SelenideElement container = element
                        .$$x("./ancestor::mat-radio-button | ./ancestor::label").first();
                Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", container);

            }
            return shouldExist(element, timeout);
        }
        return element;

    }

    public SelenideElement shouldBeVisible(SelenideElement element) {
        return element.shouldBe(visible);
    }

    public SelenideElement shouldBeVisible(SelenideElement element, int durationOfSeconds) {
        return element.shouldBe(visible, Duration.ofSeconds(durationOfSeconds));
    }

    public SelenideElement shouldExist(SelenideElement element, int durationOfSeconds) {
        return element.should(exist, Duration.ofSeconds(durationOfSeconds));
    }

    public SelenideElement shouldBeEnabled(SelenideElement element, int durationOfSeconds) {
        return element.should(enabled, Duration.ofSeconds(durationOfSeconds));
    }

    public SelenideElement doubleClick(SelenideElement element) {
        return element.doubleClick();
    }

    public SelenideElement shouldHaveText(SelenideElement element, String text) {
        return element.shouldHave(Condition.text(text));
    }

    public SelenideElement shouldHaveText(ElementsCollection elements, String text) {
        return findBy(elements, Condition.text(text), 2, true);
    }

    public SelenideElement shouldContainText(SelenideElement element, String text) {
        return element.shouldHave(Condition.partialText(text));
    }

    public SelenideElement shouldHaveAriaLabel(SelenideElement element, String label) {
        return element.shouldHave(Condition.attribute("aria-label", label));
    }

    public SelenideElement shouldHaveAriaLabel(ElementsCollection elements, String label) {
        return findBy(elements, Condition.attribute("aria-label", label), 2, true);
    }

    public void scrollToElement(SelenideElement element) {
        element.scrollTo();
    }
}
