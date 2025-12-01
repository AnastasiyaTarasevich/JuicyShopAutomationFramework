package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

//TODO refactor (add base page that implements pageElement)
public enum OrderCompletionPage implements PageElement {
    ORDER_COMPLETION_HEADER("", true),
    HEADING_TITLE("", true),
    TABLE_CONTAINER("", true),
    TRACK_ORDERS_LINK("", true);

    private final String id;
    private final boolean required;

    private final String pageName = "ORDER COMPLETION PAGE";

    OrderCompletionPage(String id, boolean required) {
        this.id = id;
        this.required = required;
    }

    @Override
    public SelenideElement getElement() {
        if (id != null && !id.isEmpty()) {
            return PageElement.super.getElement();
        }
        switch (this) {
            case ORDER_COMPLETION_HEADER:
                return $("div.order-completion-header");
            case HEADING_TITLE:
                return $("span.heading-text");
            case TABLE_CONTAINER:
                return $("div.table-container");
            case TRACK_ORDERS_LINK:
                return $("a[routerLink = '/track-result/new']");
            default:
                throw new IllegalStateException("No locator defined for element: " + this.name());
        }
    }

    @Override
    public String getId() {
        return id;
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
