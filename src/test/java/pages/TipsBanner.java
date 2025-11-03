package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public enum TipsBanner implements PageElement {
    TIPS_HACKER_IMG("", true),
    DESCRIPTION("", true),
    CLOSE_BUTTON("cancelButton", true);
    private final String id;
    private final boolean required;
    private final String pageName = "TIPS BANNER";

    TipsBanner(String id, boolean required) {
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
            case TIPS_HACKER_IMG:
                return $("#hacking-instructor img");
            case DESCRIPTION:
                return $("#hacking-instructor span");
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
