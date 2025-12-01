package steps.UISteps;

import org.testng.asserts.SoftAssert;
import pages.TrackOrdersPage;
import steps.base.BaseUISteps;

public class TrackOrdersUISteps extends BaseUISteps {

    public TrackOrdersUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public TrackOrdersUISteps verifyTrackNumberOnPage(String orderId) {
        performStep(TrackOrdersPage.ORDER_NUMBER, "Verify track number is right on page: "
                + TrackOrdersPage.ORDER_NUMBER.getPageName(), () ->
        {
            elementActions.shouldContainText(TrackOrdersPage.ORDER_NUMBER.getElement(), orderId);
        });
        return this;
    }
}
