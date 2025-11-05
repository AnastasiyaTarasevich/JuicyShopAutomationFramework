package actions;

import java.time.Duration;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ElementActions {
    public void click(SelenideElement element) {
        shouldBeVisible(element).click();
    }

    public void setValue(SelenideElement element, String value) {
        shouldBeVisible(element).setValue(value);
    }

    public boolean isElementDisplayed(SelenideElement element) {
        try {
            shouldBeVisible(element, 5);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SelenideElement shouldBeVisible(SelenideElement element) {
        return element.shouldBe(Condition.visible);
    }

    public SelenideElement shouldBeVisible(SelenideElement element, int durationOfSeconds) {
        return element.shouldBe(Condition.visible, Duration.ofSeconds(durationOfSeconds));
    }

    public SelenideElement doubleClick(SelenideElement element) {
        return element.doubleClick();
    }
}
