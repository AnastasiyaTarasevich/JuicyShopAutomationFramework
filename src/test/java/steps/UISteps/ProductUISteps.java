package steps.UISteps;

import com.codeborne.selenide.SelenideElement;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import steps.base.BaseUISteps;

public class ProductUISteps extends BaseUISteps {

    public ProductUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public ProductUISteps addProductToBasket(String product) {
        performStep(HomePage.PRODUCT_CARDS, "Add product" + product + "to basket", () ->
        {
            SelenideElement firstCard = HomePage.PRODUCT_CARDS.getElements().first();

            SelenideElement addButton = firstCard.$("button[aria-label='Add to Basket']");

            elementActions.shouldHaveText(addButton, "Add to Basket").click();

        });
        return this;
    }

}
