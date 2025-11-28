package tests.UITests;

import java.util.List;
import annotations.LoginUser;
import annotations.RegisterUser;
import com.codeborne.selenide.Selenide;
import dtos.products.ProductDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasketPage;
import pages.DeliveryPage;
import pages.OrderSummaryPage;
import pages.PaymentOptionsPage;
import pages.SelectAddressPage;
import steps.APISteps.BasketApiSteps;
import steps.APISteps.ProductsApiSteps;
import steps.UISteps.BasketUISteps;
import steps.UISteps.DeliveryUISteps;
import steps.UISteps.HomeUISteps;
import steps.UISteps.OrderSummaryUISteps;
import steps.UISteps.PaymentOptionsSteps;
import steps.UISteps.SelectAddressUISteps;
import tests.base.BaseUITest;
import utils.RandomDataGenerator;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class CheckoutTest extends BaseUITest {
    private final SoftAssert softAssert = new SoftAssert();
    private final HomeUISteps homeSteps = new HomeUISteps(softAssert);
    private final ProductsApiSteps productsApiSteps = new ProductsApiSteps(softAssert);
    private final BasketApiSteps basketApiSteps = new BasketApiSteps(softAssert);
    private final BasketUISteps basketUISteps = new BasketUISteps(softAssert);
    private final SelectAddressUISteps selectAddressUISteps = new SelectAddressUISteps(softAssert);
    private final DeliveryUISteps deliveryUISteps = new DeliveryUISteps(softAssert);
    private final PaymentOptionsSteps paymentOptionsSteps = new PaymentOptionsSteps(softAssert);
    private final OrderSummaryUISteps orderSummaryUISteps = new OrderSummaryUISteps(softAssert);

    @Test(groups = {TestGroups.UI})
    @Feature("Checkout")
    @Description("Verify user can successfully complete an order")
    @Severity(CRITICAL)
    @RegisterUser
    @LoginUser
    public void checkoutTest() {
        String country = RandomDataGenerator.randomCountry();
        String name = RandomDataGenerator.randomName();
        String mobile = RandomDataGenerator.randomPhoneNumber();
        String zip = RandomDataGenerator.randomZipCode();
        String address = RandomDataGenerator.randomAddress();
        String city = RandomDataGenerator.randomCity();
        String state = RandomDataGenerator.randomState();
        String card = RandomDataGenerator.randomCardNumber();
        String month = RandomDataGenerator.randomMonth();
        String year = RandomDataGenerator.randomYear();
        List<ProductDTO> productList = productsApiSteps.getRandomProductsFromSite(4);
        basketApiSteps
                .addSeveralProductsToBasket(loggedUserToken, loggedUserBasketId, productList);
        homeSteps
                .clickOnBasket();

        basketUISteps
                .verifyBasketPageVisible()
                .verifyBasketContainsRightEmail(createdUser.getEmail())
                .verifyProductsInBasket(productList)
                .clickOnElement(BasketPage.CHECKOUT_BUTTON);
        selectAddressUISteps
                .clickOnElement(SelectAddressPage.ADD_ADDRESS_BUTTON);
        selectAddressUISteps
                .fillNewAddressForm(country, name, mobile, zip, address, city, state)
                .clickOnElement(SelectAddressPage.SUBMIT_BUTTON);
        //TODO refactor sleep (maybe custom waits)
        Selenide.sleep(1000);
        selectAddressUISteps
                .clickOnElement(SelectAddressPage.SELECT_RADIO_BUTTON);
        selectAddressUISteps
                .clickOnElement(SelectAddressPage.CONTINUE_BUTTON);
        deliveryUISteps
                .verifyAddress(country, name, mobile, zip, address, city, state)
                .clickOnElement(DeliveryPage.ONE_DAY_RADIO);
        deliveryUISteps
                .clickOnElement(DeliveryPage.CONTINUE_BUTTON);
        paymentOptionsSteps
                .verifyPaymentOptionsPageVisible()
                .clickOnElement(PaymentOptionsPage.ADD_NEW_CARD_PANEL);
        paymentOptionsSteps
                .fillPaymentCard(name, card, month, year)
                .clickOnElement(PaymentOptionsPage.SUBMIT_BUTTON);
        //TODO refactor sleep
        Selenide.sleep(1000);
        paymentOptionsSteps
                .clickOnElement(PaymentOptionsPage.PAYMENT_RADIO);
        paymentOptionsSteps
                .clickOnContinueButton();
        //TODO refactor (verify order)
        orderSummaryUISteps
                .verifyOrderSummaryPageVisible()
                .clickOnElement(OrderSummaryPage.COMPLETE_PURCHASE_BUTTON);
        // TODO refactor (add order completion)
        softAssert.assertAll();
    }
}
