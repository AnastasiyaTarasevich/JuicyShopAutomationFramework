package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public enum DeliveryPage implements PageElement {
    ADDRESS_CONTROL_BLOCK("", false),
    ONE_DAY_RADIO("mat-radio-42-input", true),
    FAST_RADIO("mat-radio-43-input", true),
    STANDARD_RADIO("mat-radio-44-input", true),
    CONTINUE_BUTTON("", false),
    ;

    private final String id;
    private final boolean required;

    private final String pageName = "DELIVERY PAGE";

    DeliveryPage(String id, boolean required) {
        this.id = id;
        this.required = required;
    }

    @Override
    public String getId() {
        return id;
    }

    //TODO refactor: move block to default method
    @Override
    public SelenideElement getElement() {
        if (id != null && !id.isEmpty()) {
            return PageElement.super.getElement();
        }
        switch (this) {
            case ADDRESS_CONTROL_BLOCK:
                return $(".addressCont");
            case CONTINUE_BUTTON:
                return $("button[aria-label='Proceed to delivery method selection']");
            default:
                throw new IllegalStateException("No locator defined for element: " + this.name());
        }
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public String getPageName() {
        return pageName;
    }
}
