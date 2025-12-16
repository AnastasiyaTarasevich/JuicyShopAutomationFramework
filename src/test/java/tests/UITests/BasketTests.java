package tests.UITests;

import java.util.List;
import java.util.Random;
import annotations.LoginUser;
import annotations.RegisterUser;
import dtos.products.ProductDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import steps.APISteps.BasketApiSteps;
import steps.APISteps.ProductsApiSteps;
import steps.UISteps.BasketUISteps;
import steps.UISteps.HomeUISteps;
import steps.UISteps.ProductUISteps;
import tests.base.BaseUITest;
import utils.RandomDataGenerator;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class BasketTests extends BaseUITest {

    private final SoftAssert softAssert = new SoftAssert();
    private final ProductsApiSteps productsApiSteps = new ProductsApiSteps(softAssert);
    private final ProductUISteps productUISteps = new ProductUISteps(softAssert);
    private final HomeUISteps homeUISteps = new HomeUISteps(softAssert);
    private final BasketUISteps basketUISteps = new BasketUISteps(softAssert);
    private final BasketApiSteps basketApiSteps = new BasketApiSteps(softAssert);

    @Test(groups = {TestGroups.UI})
    @Feature("Basket")
    @Description("Verify if user can search and add one product to basket")
    @Severity(CRITICAL)
    @RegisterUser
    @LoginUser
    public void searchAndAddToBasket() {
        ProductDTO product = productsApiSteps.getRandomProduct();
        homeUISteps
                .clickOnElement(HomePage.SEARCH_BUTTON)
                .searchText(product.getName());
        productUISteps
                .addProductToBasket(product.getName());
        homeUISteps
                .clickOnElement(HomePage.BASKET_ICON);
        basketUISteps
                .verifyBasketPageVisible()
                .verifyProductInBasket(product);
        //TODO delete (added some error to see RP behaviour)
        homeUISteps
                .closeWelcomeBanner();
        softAssert.assertAll();
    }

    @Test(groups = {TestGroups.UI})
    @Feature("Basket")
    @Description("Verify if user can delete product from basket")
    @Severity(CRITICAL)
    @RegisterUser
    @LoginUser
    public void deleteProductFromBasket() {
        int amountOfProducts = RandomDataGenerator.getRandomIntFrom1To10();
        List<ProductDTO> productList = productsApiSteps.getRandomProductsFromSite(amountOfProducts);
        basketApiSteps
                .addSeveralProductsToBasket(loggedUserToken, loggedUserBasketId, productList);
        ProductDTO product = productList.get(new Random().nextInt(productList.size()));
        homeUISteps
                .clickOnElement(HomePage.BASKET_ICON);
        basketUISteps
                .verifyBasketPageVisible()
                .verifyProductsInBasket(productList)
                .deleteProductFromBasket(product)
                .verifyProductIsNotInBasket(product);
        softAssert.assertAll();
    }

    @Test
    public void reportPortalLogCheck() {
        log.info("CI_LOG_CHECK: this log should appear in ReportPortal");
    }

}
