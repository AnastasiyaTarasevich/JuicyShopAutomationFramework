package tests.APITests;

import java.util.List;
import annotations.LoginUser;
import annotations.RegisterUser;
import annotations.SeveralProductsToBasket;
import dtos.basket.UserBasketResponse;
import dtos.products.ProductDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.APISteps.BasketApiSteps;
import steps.APISteps.ProductsApiSteps;
import tests.base.BaseAPITest;
import utils.RandomDataGenerator;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

public class BasketTests extends BaseAPITest {
    private final SoftAssert softAssert = new SoftAssert();
    private final BasketApiSteps basketApiSteps = new BasketApiSteps(softAssert);
    private final ProductsApiSteps productsApiSteps = new ProductsApiSteps(softAssert);

    @Test(groups = {TestGroups.API})
    @Feature("Basket")
    @Description("Verify if user can add one product to basket")
    @Severity(CRITICAL)
    @RegisterUser
    @LoginUser
    public void addOneProductToBasketTest() {
        ProductDTO product = productsApiSteps.getRandomProduct();
        basketApiSteps.addProductToBasket(loggedInUser.getAuthentication().getToken(),
                loggedInUser.getAuthentication().getBid(), product);

        softAssert.assertAll();
    }

    @Test(groups = {TestGroups.API})
    @Feature("Basket")
    @Description("Verify if user can add several products to basket")
    @Severity(CRITICAL)
    @RegisterUser
    @LoginUser
    public void addSeveralProductsToBasketTest() {
        int amountOfProducts = RandomDataGenerator.getRandomIntFrom1To10();
        List<ProductDTO> productList = productsApiSteps.getRandomProductsFromSite(amountOfProducts);
        basketApiSteps.addSeveralProductsToBasket(loggedInUser.getAuthentication().getToken(),
                loggedInUser.getAuthentication().getBid(), productList);
        softAssert.assertAll();
    }

    @Test(groups = {TestGroups.API})
    @Feature("Basket")
    @Description("Return the basket of the logged in user")
    @Severity(CRITICAL)
    @RegisterUser
    @LoginUser
    @SeveralProductsToBasket
    public void getBasketTest() {
        basketApiSteps.getUserBasket(loggedInUser.getAuthentication().getBid(),
                loggedInUser.getAuthentication().getToken());
        softAssert.assertAll();
    }

    @Test(groups = {TestGroups.API})
    @Feature("Basket")
    @Description("Delete the item from basket")
    @Severity(NORMAL)
    @RegisterUser
    @LoginUser
    @SeveralProductsToBasket
    public void deleteProductFromBasketTest() {

        UserBasketResponse basket = basketApiSteps.getUserBasket(loggedInUser.getAuthentication().getBid(),
                loggedInUser.getAuthentication().getToken());
        ProductDTO item = basketApiSteps.getAnyBasketItemId(basket);
        basketApiSteps.deleteAnItemFromBasket(item, loggedInUser.getAuthentication().getToken());
        basket = basketApiSteps.getUserBasket(loggedInUser.getAuthentication().getBid(),
                loggedInUser.getAuthentication().getToken());
        basketApiSteps.verifyBasketItemWasDeleted(basket, item);

        softAssert.assertAll();
    }


}
