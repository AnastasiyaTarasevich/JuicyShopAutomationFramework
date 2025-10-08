package pages;

import com.codeborne.selenide.SelenideElement;

public interface PageElement {

    SelenideElement getElement();

    boolean isRequired();
}
