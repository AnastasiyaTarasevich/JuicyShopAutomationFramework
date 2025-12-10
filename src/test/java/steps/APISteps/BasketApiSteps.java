package steps.APISteps;

import java.util.Collections;
import java.util.List;
import api.ApiEndpoints;
import dtos.basket.AddToBasketRequest;
import dtos.basket.AddToBasketResponse;
import dtos.basket.UserBasketResponse;
import dtos.products.ProductDTO;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import steps.base.BaseAPISteps;
import utils.StatusCode;

public class BasketApiSteps extends BaseAPISteps {
    AddToBasketResponse addToBasketResponse;
    UserBasketResponse userBasketResponse;

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
            addToBasketResponse = apiClient.postWithAuth(
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

    public UserBasketResponse getUserBasket(int bid, String token) {
        Response response = getBasketResponse(bid, token);

        performStep("Get user basket", () -> {
            softAssert.assertEquals(response.getStatusCode(), StatusCode.OK.getStatusCode());
        });

        return response.as(UserBasketResponse.class);
    }

    public Response getBasketResponse(int bid, String token) {
        return apiClient.getWithPathParamAndAuth(ApiEndpoints.GET_BASKET, String.valueOf(bid), token)
                .then()
                .log().ifError()
                .extract()
                .response();
    }

    public ProductDTO getAnyBasketItemId(UserBasketResponse userBasketResponse) {
        List<ProductDTO> products = userBasketResponse.getData().getProductDTOList();

        if (products.isEmpty()) {
            throw new RuntimeException("Basket is empty — nothing to delete");
        }
        Collections.shuffle(products);
        return products.get(0);
    }

    public BasketApiSteps deleteAnItemFromBasket(ProductDTO basketItem, String token) {
        performStep("Delete item" + basketItem.getName() + " from basket", () -> {
            int basketItemId = basketItem.getBasketItem().getId();

            Response response = deleteBasketItem(basketItemId, token);
            softAssert.assertEquals(response.getStatusCode(), 200);
        });
        return this;
    }

    public Response deleteBasketItem(int basketItemId, String token) {
        return apiClient.delete(ApiEndpoints.DELETE_BASKET_ITEM, token, basketItemId);
    }

    public BasketApiSteps verifyBasketItemWasDeleted(UserBasketResponse basket, ProductDTO item) {
        boolean isPresent = basket.getData().getProductDTOList().stream()
                .anyMatch(p -> p.getBasketItem() != null && p.getBasketItem().getId() == item.getBasketItem().getId());

        softAssert.assertFalse(isPresent, "The product should be deleted from basket");
        return this;
    }


}
