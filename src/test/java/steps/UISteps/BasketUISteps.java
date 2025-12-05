package steps.UISteps;

import java.util.List;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import dtos.products.ProductDTO;
import org.testng.asserts.SoftAssert;
import pages.BasketPage;
import steps.base.BaseUISteps;

public class BasketUISteps extends BaseUISteps {

    public BasketUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public BasketUISteps verifyBasketPageVisible() {
        checkRequiredElementsVisibility(BasketPage.getRequiredElements());
        return this;
    }

    public BasketUISteps verifyBasketContainsRightEmail(String email) {
        performStep(BasketPage.BASKET_EMAIL, "Verify basket contains email: " + email, () -> {
            elementActions.shouldHaveText(BasketPage.BASKET_EMAIL.getElement(), email);
        });
        return this;
    }

    public BasketUISteps verifyProductsInBasket(List<ProductDTO> products) {
        performStep(BasketPage.PRODUCT_ROWS, "Verify basket contains all products: ", () -> {
            for (ProductDTO product : products) {
                verifyProductInBasket(product);
            }
        });
        return this;
    }

    public BasketUISteps verifyProductInBasket(ProductDTO product) {
        performStep(BasketPage.PRODUCT_ROWS, "Verify basket contains product: " + product.getName(), () -> {

            SelenideElement row = BasketPage.PRODUCT_ROWS.getElements()
                    .filterBy(Condition.text(product.getName()))
                    .first();

            elementActions.shouldHaveText(row, product.getName());
            elementActions.shouldHaveText(row, "1");
            String expectedPrice = String.valueOf((int) product.getPrice());
            elementActions.shouldContainText(row, expectedPrice);
        });
        return this;
    }


}
