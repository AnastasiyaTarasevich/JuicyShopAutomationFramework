package utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

public class ElementActions {
    public void click(SelenideElement element) {
        element.shouldBe(visible).click();
    }

    public void setValue(SelenideElement element, String value) {
        element.shouldBe(visible).setValue(value);
    }

    public boolean isElementDisplayed(SelenideElement element) {
        return element.isDisplayed();
    }
}
