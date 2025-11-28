package steps.UISteps;

import org.testng.asserts.SoftAssert;
import pages.OrderSummaryPage;
import steps.base.BaseUISteps;

public class OrderSummaryUISteps extends BaseUISteps {
    public OrderSummaryUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public OrderSummaryUISteps verifyOrderSummaryPageVisible() {
        checkRequiredElementsVisibility(OrderSummaryPage.getRequiredElements());
        return this;
    }
}
