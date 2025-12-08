package dtos.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToBasketRequest {
    @JsonProperty("ProductId")
    private Integer productId;

    @JsonProperty("BasketId")
    private Integer basketId;

    private Integer quantity;
}

