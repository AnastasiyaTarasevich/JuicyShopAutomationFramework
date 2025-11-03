package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public enum WelcomeBanner implements PageElement {
    OWASP_LINK("", true),
    JUICY_SHOP_LINK("", true),
    TIPS_BUTTON("", true),
    CLOSE_WELCOME_BANNER_BUTTON("", true);

    private final String id;
    private final boolean required;
    public final String pageName = "WELCOME PAGE BANNER";

    WelcomeBanner(String id, boolean required) {
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
            case OWASP_LINK:
                return $("a[href='https://owasp.org']");
            case JUICY_SHOP_LINK:
                return $("a[href='https://owasp-juice.shop']");
            case TIPS_BUTTON:
                return $x("//button[.//span[contains(normalize-space(.), 'Help getting started')]]");
            case CLOSE_WELCOME_BANNER_BUTTON:
                return $("button[aria-label='Close Welcome Banner']");
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
