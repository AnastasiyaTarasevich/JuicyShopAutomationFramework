package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$;

public enum BasketPage implements PageElement {
    BASKET_TITLE("", false),
    TOTAL_PRICE("price", true),
    CHECKOUT_BUTTON("checkoutButton", true),
    BASKET_EMAIL("", false),
    PRODUCT_ROWS("", false),
    ;

    private final String id;
    private final boolean required;

    private final String pageName = "BASKET PAGE";

    BasketPage(String id, boolean required) {
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
            case BASKET_TITLE:
                return $$("h1").find(Condition.text("Your Basket"));
            case BASKET_EMAIL:
                return $$("h1").find(Condition.text("Your Basket")).$("small");
            default:
                throw new IllegalStateException("No locator defined for element: " + this.name());
        }
    }

    public ElementsCollection getElements() {
        switch (this) {
            case PRODUCT_ROWS:
                return $$("mat-row");
            default:
                throw new IllegalStateException("No collection defined for element: " + this.name());
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
