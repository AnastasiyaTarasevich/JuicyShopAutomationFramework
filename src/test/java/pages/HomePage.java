package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public enum HomePage implements PageElement {

    CLOSE_WELCOME_BANNER("", false),
    ACCOUNT_BUTTON("navbarAccount", true),
    LOGIN_PAGE_BUTTON("", false);

    private final String id;
    private final boolean required;

    private final String pageName = "HOME PAGE";

    HomePage(String id, boolean required) {
        this.id = id;
        this.required = required;
    }

    @Override
    public SelenideElement getElement() {
        if (!id.isEmpty() && $("#" + id).exists()) {
            return $("#" + id);
        }

        // альтернативные селекторы для элементов без id
        switch (this) {
            case CLOSE_WELCOME_BANNER:
                return $("button[aria-label='Close Welcome Banner']");
            case LOGIN_PAGE_BUTTON:
                return $("button[aria-label='Go to login page']");
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
