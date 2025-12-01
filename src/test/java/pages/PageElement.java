package pages;

import java.time.Duration;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public interface PageElement {

    String getId();

    default SelenideElement getElement() {
        String id = getId();
        if (id != null && !id.isEmpty()) {
            SelenideElement element = $("#" + id);
            element.should(Condition.exist, Duration.ofSeconds(5));
            return element;
        }
        throw new IllegalStateException("No locator defined for element: " + this.getClass().getSimpleName());
    }

    boolean isRequired();

    String getPageName();
}
