package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

//TODO add necessary elements
public enum HomePage implements PageElement {

    ACCOUNT_BUTTON("navbarAccount", true),
    LOGIN_PAGE_BUTTON("", false),
    SEARCH_BUTTON("searchQuery", true),
    SEARCH_INPUT("mat-input-1", false),
    PRODUCT_CARDS("", false),
    ;

    private final String id;
    private final boolean required;

    private final String pageName = "HOME PAGE";

    HomePage(String id, boolean required) {
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
            case LOGIN_PAGE_BUTTON:
                return $("button[aria-label='Go to login page']");
            default:
                throw new IllegalStateException("No locator defined for element: " + this.name());
        }
    }

    public ElementsCollection getElements() {
        switch (this) {
            case PRODUCT_CARDS:
                return $$("div.mdc-card");
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
