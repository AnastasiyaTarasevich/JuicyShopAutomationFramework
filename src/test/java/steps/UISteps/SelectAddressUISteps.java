package steps.UISteps;

import org.testng.asserts.SoftAssert;
import pages.SelectAddressPage;
import steps.base.BaseUISteps;

public class SelectAddressUISteps extends BaseUISteps {

    public SelectAddressUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public SelectAddressUISteps fillNewAddressForm(String country, String name, String mobile
            , String zipCode, String streetAddress, String city, String state) {
        performStep(SelectAddressPage.SUBMIT_BUTTON, "Fill inputs for new address", () -> {
            elementActions.setValue(SelectAddressPage.COUNTRY_INPUT.getElement(), country);
            elementActions.setValue(SelectAddressPage.NAME_INPUT.getElement(), name);
            elementActions.setValue(SelectAddressPage.PHONE_INPUT.getElement(), mobile);
            elementActions.setValue(SelectAddressPage.ZIP_INPUT.getElement(), zipCode);
            elementActions.setValue(SelectAddressPage.ADDRESS_INPUT.getElement(), streetAddress);
            elementActions.setValue(SelectAddressPage.CITY_INPUT.getElement(), city);
            elementActions.setValue(SelectAddressPage.STATE_INPUT.getElement(), state);
        });
        return this;
    }

}
