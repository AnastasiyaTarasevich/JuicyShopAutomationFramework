package dtos.basket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasketItemDTO {
    private int id;
    private int quantity;

    @JsonProperty("ProductId")
    private int productId;

    @JsonProperty("BasketId")
    private int basketId;
    
}
