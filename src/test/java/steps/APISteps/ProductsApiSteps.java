package steps.APISteps;

import java.util.Collections;
import java.util.List;
import config.ApiEndpoints;
import dtos.products.ProductDTO;
import dtos.products.ProductsResponseDTO;
import org.testng.asserts.SoftAssert;
import steps.base.BaseAPISteps;
import utils.StatusCode;

public class ProductsApiSteps extends BaseAPISteps {
    List<ProductDTO> products;
    ProductsResponseDTO response;

    public ProductsApiSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public List<ProductDTO> getAllProducts() {
        performStep("Get all products via API", () ->
        {
            response = apiClient.get(ApiEndpoints.ALL_PRODUCTS)
                    .then()
                    .statusCode(StatusCode.OK.getStatusCode())
                    .extract()
                    .as(ProductsResponseDTO.class);
            products = response.getData();
        });
        return products;
    }

    public ProductsApiSteps verifyProductsNotEmpty(List<ProductDTO> products) {
        performStep("Verify products list is not empty", () -> {
            softAssert.assertFalse(products.isEmpty(), "Products list should not be empty");
        });
        return this;
    }

    private ProductsResponseDTO getProductsByName(String productName) {
        response = apiClient.getWithQueryParam(ApiEndpoints.ALL_PRODUCTS, productName)
                .then()
                .statusCode(StatusCode.OK.getStatusCode())
                .extract()
                .as(ProductsResponseDTO.class);
        log.info("Products received: " + response.getData().size());
        return response;
    }

    public ProductsApiSteps verifyProductNameExists(String productName) {
        performStep("Verify product exists via API " + productName, () -> {
            ProductsResponseDTO response = getProductsByName(productName);
            boolean exists = response.getData().stream().anyMatch(product -> product.getName().toLowerCase()
                    .contains(productName.toLowerCase()));
            softAssert.assertTrue(exists, "Product " + productName + " does not exist");

        });
        return this;
    }

    public ProductsApiSteps verifyProductNameDoesNotExist(String productName) {
        performStep("Verify product doesn't exist via API " + productName, () -> {
            ProductsResponseDTO response = getProductsByName(productName);
            boolean exists = response.getData().stream().anyMatch(product -> product.getName().toLowerCase()
                    .contains(productName.toLowerCase()));
            softAssert.assertFalse(exists, "Product " + productName + " should not exist");
        });
        return this;
    }

    public List<ProductDTO> getRandomProductsFromSite(int count) {
        List<ProductDTO> allProducts = getAllProducts();
        verifyProductsNotEmpty(allProducts);

        Collections.shuffle(allProducts);
        return allProducts.subList(0, count);
    }

    public ProductDTO getRandomProduct() {
        List<ProductDTO> allProducts = getAllProducts();
        Collections.shuffle(allProducts);
        return allProducts.get(0);
    }
}
