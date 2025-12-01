package steps.UISteps;

import com.codeborne.selenide.SelenideElement;
import org.testng.asserts.SoftAssert;
import pages.PaymentOptionsPage;
import steps.base.BaseUISteps;

public class PaymentOptionsSteps extends BaseUISteps<PaymentOptionsSteps> {
    public PaymentOptionsSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public PaymentOptionsSteps verifyPaymentOptionsPageVisible() {
        checkRequiredElementsVisibility(PaymentOptionsPage.getRequiredElements());
        return this;
    }

    //TODO refactor (add card number)
    public PaymentOptionsSteps fillPaymentCard(String name, String cardNumber,
                                               String expiryMonth, String expiryYear) {
        performStep(PaymentOptionsPage.CARD_NUMBER_INPUT, "Fill the card ", () -> {
            elementActions.setValue(PaymentOptionsPage.NAME_INPUT.getElement(), name);
            elementActions.setValue(PaymentOptionsPage.CARD_NUMBER_INPUT.getElement(), cardNumber);
            //TODO refactor (move selectOption to elementActions)
            SelenideElement monthSelect = PaymentOptionsPage.EXPIRATION_MONTH_INPUT.getElement();
            monthSelect.selectOptionByValue(expiryMonth);


            SelenideElement yearSelect = PaymentOptionsPage.EXPIRATION_YEAR_INPUT.getElement();
            yearSelect.selectOptionByValue(expiryYear);

        });
        return this;
    }

    public PaymentOptionsSteps clickOnContinueButton() {
        performStep(PaymentOptionsPage.CONTINUE_BUTTON, "Click on the continue button ", () ->
        {
            elementActions.click(PaymentOptionsPage.CONTINUE_BUTTON.getElement(), 1);
        });
        return this;
    }

}
