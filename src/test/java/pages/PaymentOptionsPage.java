package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public enum PaymentOptionsPage implements PageElement {
    ADD_NEW_CARD_PANEL("mat-expansion-panel-header-0", true),
    NAME_INPUT("mat-input-11", false),
    CARD_NUMBER_INPUT("mat-input-12", false),
    EXPIRATION_MONTH_INPUT("mat-input-13", false),
    EXPIRATION_YEAR_INPUT("mat-input-14", false),
    SUBMIT_BUTTON("submitButton", false),
    PAYMENT_RADIO("mat-radio-45-input", false),
    CONTINUE_BUTTON("", false),
    ;
    private final String id;
    private final boolean required;

    private final String pageName = "PAYMENT OPTIONS PAGE";

    PaymentOptionsPage(String id, boolean required) {
        this.id = id;
        this.required = required;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public SelenideElement getElement() {
        if (id != null && !id.isEmpty()) {
            return PageElement.super.getElement();
        }
        switch (this) {
            case CONTINUE_BUTTON:
                return $("button[aria-label='Proceed to review']");
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

    public static PageElement[] getRequiredElements() {
        return values();
    }
}
