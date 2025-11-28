package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public enum SelectAddressPage implements PageElement {
    SELECT_ADDRESS_TITLE("", false),
    ADD_ADDRESS_BUTTON("", false),
    COUNTRY_INPUT("mat-input-4", false),
    NAME_INPUT("mat-input-5", false),
    PHONE_INPUT("mat-input-6", false),
    ZIP_INPUT("mat-input-7", false),
    ADDRESS_INPUT("address", false),
    CITY_INPUT("mat-input-9", false),
    STATE_INPUT("mat-input-10", false),
    SUBMIT_BUTTON("submitButton", true),
    SELECT_RADIO_BUTTON("mat-radio-41-input", false),
    CONTINUE_BUTTON("", false),
    ;

    private final String id;
    private final boolean required;

    private final String pageName = "SELECT ADDRESS PAGE";

    SelectAddressPage(String id, boolean required) {
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
            case SELECT_ADDRESS_TITLE:
                return $x("//h1[contains(normalize-space(.),'Select an address')]");
            case ADD_ADDRESS_BUTTON:
                return $("button[aria-label = 'Add a new address']");
            case CONTINUE_BUTTON:
                return $("button[aria-label = 'Proceed to payment selection']");
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
