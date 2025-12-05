package steps.APISteps;

import java.util.List;
import config.ApiEndpoints;
import dtos.products.AddToBasketRequest;
import dtos.products.AddToBasketResponse;
import dtos.products.ProductDTO;
import org.testng.asserts.SoftAssert;
import steps.base.BaseAPISteps;
import utils.StatusCode;

public class BasketApiSteps extends BaseAPISteps {
    AddToBasketResponse response;

    public BasketApiSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public BasketApiSteps addProductToBasket(String token, int basketId, ProductDTO product) {
        performStep("Add product to basket: " + product.getName(), () ->
        {
            AddToBasketRequest request = new AddToBasketRequest(
                    product.getId(),
                    basketId,
                    1
            );
            System.out.println("Add to basket request: " + request.toString());
            response = apiClient.postWithAuth(
                            ApiEndpoints.ADD_ITEM_TO_BASKET,
                            request, token
                    )
                    .then()
                    .statusCode(StatusCode.OK.getStatusCode())
                    .extract()
                    .as(AddToBasketResponse.class);

        });

        return this;
    }

    public BasketApiSteps addSeveralProductsToBasket(String token, int basketId,
                                                     List<ProductDTO> products) {
        performStep("Add several products to basket", () -> {
            for (ProductDTO product : products) {
                addProductToBasket(token, basketId, product);
            }
        });
        return this;
    }
}
