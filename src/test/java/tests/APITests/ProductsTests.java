package tests.APITests;

import java.util.List;
import dtos.products.ProductDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.APISteps.ProductsApiSteps;
import tests.base.BaseAPITest;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class ProductsTests extends BaseAPITest {
    private final SoftAssert softAssert = new SoftAssert();
    private final ProductsApiSteps productsApiSteps = new ProductsApiSteps(softAssert);

    @Test(groups = {TestGroups.API})
    @Feature("Products")
    @Description("Verify if user can get all products list")
    @Severity(CRITICAL)
    public void getAllProducts() {
        List<ProductDTO> products = productsApiSteps.getAllProducts();
        productsApiSteps.verifyProductsNotEmpty(products);
        softAssert.assertAll();
    }

    @Test(groups = {TestGroups.API})
    @Feature("Products")
    @Description("Verify specific product exists in products list")
    @Severity(CRITICAL)
    public void verifySpecificProductExists() {
        String productName = "apple";
        productsApiSteps.verifyProductNameExists(productName);
        softAssert.assertAll();
    }

    @Test(groups = {TestGroups.API})
    @Feature("Products")
    @Description("Verify wrong product is not returned by APi")
    @Severity(CRITICAL)
    public void verifyWrongProductNotReturnedByAPi() {
        String productName = "notExist";
        productsApiSteps.verifyProductNameDoesNotExist(productName);
        softAssert.assertAll();
    }
}
