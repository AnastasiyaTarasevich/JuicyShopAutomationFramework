package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public interface PageElement {

    String getId();

    default SelenideElement getElement() {
        String id = getId();
        if (id != null && !id.isEmpty() && $("#" + id).exists()) {
            return $("#" + id);
        }
        throw new IllegalStateException("No locator defined for element: " + this.getClass().getSimpleName());
    }


    boolean isRequired();
    String getPageName();
}
