package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

//TODO add elements
public enum HomePageLoggedIn implements PageElement {
    ACCOUNT_BUTTON("navbarAccount", true),
    LOGOUT_BUTTON("navbarLogoutButton", false),
    GO_TO_PROFILE_BUTTON("", false)
    ;

    private final String pageName = "Auth Home Page";
    private final String id;
    private final boolean required;

    HomePageLoggedIn(String id, boolean required) {
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
            case GO_TO_PROFILE_BUTTON:
                return $("button[aria-label='Go to user profile']");
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
