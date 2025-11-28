package pages;

import com.codeborne.selenide.SelenideElement;

public enum OrderSummaryPage implements PageElement {

    COMPLETE_PURCHASE_BUTTON("checkoutButton", true);
    private final String id;
    private final boolean required;

    private final String pageName = "ORDER SUMMARY PAGE";

    OrderSummaryPage(String id, boolean required) {
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
