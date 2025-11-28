package steps.UISteps;

import org.testng.asserts.SoftAssert;
import pages.DeliveryPage;
import steps.base.BaseUISteps;

public class DeliveryUISteps extends BaseUISteps {

    public DeliveryUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public DeliveryUISteps verifyAddress(String country, String name, String mobile
            , String zipCode, String streetAddress, String city, String state) {
        performStep(DeliveryPage.ADDRESS_CONTROL_BLOCK, "Verify address is right on "
                + DeliveryPage.ADDRESS_CONTROL_BLOCK.getPageName(), () ->
        {
            elementActions.shouldContainText(DeliveryPage.ADDRESS_CONTROL_BLOCK.getElement(), country);
            elementActions.shouldContainText(DeliveryPage.ADDRESS_CONTROL_BLOCK.getElement(), name);
            elementActions.shouldContainText(DeliveryPage.ADDRESS_CONTROL_BLOCK.getElement(), mobile);
            elementActions.shouldContainText(DeliveryPage.ADDRESS_CONTROL_BLOCK.getElement(), zipCode);
            elementActions.shouldContainText(DeliveryPage.ADDRESS_CONTROL_BLOCK.getElement(), streetAddress);
            elementActions.shouldContainText(DeliveryPage.ADDRESS_CONTROL_BLOCK.getElement(), city);
            elementActions.shouldContainText(DeliveryPage.ADDRESS_CONTROL_BLOCK.getElement(), state);
        });
        return this;
    }
}
