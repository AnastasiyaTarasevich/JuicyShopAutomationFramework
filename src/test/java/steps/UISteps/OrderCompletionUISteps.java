package steps.UISteps;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import dtos.products.ProductDTO;
import org.testng.asserts.SoftAssert;
import pages.OrderCompletionPage;
import steps.base.BaseUISteps;

public class OrderCompletionUISteps extends BaseUISteps<OrderCompletionUISteps> {

    public OrderCompletionUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public OrderCompletionUISteps verifyOrderCompletionPageIsVisible() {
        checkRequiredElementsVisibility(OrderCompletionPage.getRequiredElements());
        return this;
    }

    public OrderCompletionUISteps checkOrderCompletion(String country, String mobile,
                                                       String address, String city,
                                                       List<ProductDTO> productList) {
        performStep(OrderCompletionPage.TABLE_CONTAINER, "Verify order completion", () ->
        {
            elementActions.shouldContainText(OrderCompletionPage.ORDER_COMPLETION_HEADER.getElement(), country);
            elementActions.shouldContainText(OrderCompletionPage.ORDER_COMPLETION_HEADER.getElement(), mobile);
            elementActions.shouldContainText(OrderCompletionPage.ORDER_COMPLETION_HEADER.getElement(), address);
            elementActions.shouldContainText(OrderCompletionPage.ORDER_COMPLETION_HEADER.getElement(), city);
            for (ProductDTO product : productList) {
                verifyProductInTable(product);
            }

        });
        return this;
    }

    public String extractTrackingNumberFromUrl() {
        AtomicReference<String> orderNumber = new AtomicReference<>();
        performStep(OrderCompletionPage.ORDER_COMPLETION_HEADER, "Extract order tracking number from url", () ->
        {
            String url = browserActions.currentUrl();
            orderNumber.set(url.substring(url.lastIndexOf("/") + 1));
        });
        return orderNumber.get();
    }

    public OrderCompletionUISteps verifyProductInTable(ProductDTO product) {
        performStep(OrderCompletionPage.TABLE_CONTAINER, "Verify completion table contains product: " + product, () -> {

            elementActions.shouldHaveText(OrderCompletionPage.TABLE_CONTAINER.getElement(), product.getName());
            elementActions.shouldHaveText(OrderCompletionPage.TABLE_CONTAINER.getElement(), "1");
            String expectedPrice = String.valueOf((int) product.getPrice());
            elementActions.shouldContainText(OrderCompletionPage.TABLE_CONTAINER.getElement(), expectedPrice);
        });
        return this;
    }

}
