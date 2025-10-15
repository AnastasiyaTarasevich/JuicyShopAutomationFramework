package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public enum LoginPage implements PageElement{

    EMAIL("email", true),
    PASSWORD("password", true),
    LOGIN_BUTTON("loginButton", false);

    private final String id;
    private final boolean required;
    public  final String pageName = "LOGIN PAGE";

    LoginPage(String id, boolean required) {
        this.id = id;
        this.required = required;
    }

    @Override
    public SelenideElement getElement() {
        if (!id.isEmpty() && $("#" + id).exists()) {
            return $("#" + id);
        }
        throw new IllegalStateException("No locator defined for element: " + this.name());
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
