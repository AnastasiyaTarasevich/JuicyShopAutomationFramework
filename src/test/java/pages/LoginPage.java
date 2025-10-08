package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public enum LoginPage implements PageElement{

    EMAIL("email", true),
    PASSWORD("password", true),
    LOGIN_BUTTON("", true);

    private final String id;
    private final boolean required;

    LoginPage(String id, boolean required) {
        this.id = id;
        this.required = required;
    }

    @Override
    public SelenideElement getElement() {
        if (!id.isEmpty() && $("#" + id).exists()) {
            return $("#" + id);
        }

        switch (this) {
            case LOGIN_BUTTON:
                return $("button[aria-label='Go to login page']");
            default:
                throw new IllegalStateException("No locator defined for element: " + this.name());
        }
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public static PageElement[] getRequiredElements() {
        return values();
    }
}
