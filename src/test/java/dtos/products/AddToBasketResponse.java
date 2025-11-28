package dtos.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddToBasketResponse {
    private String status;

    @JsonProperty("data")
    private DataSet data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataSet {
        private int id;
        @JsonProperty("ProductId")
        private int ProductId;

        @JsonProperty("BasketId")
        private int BasketId;

        private int quantity;
    }
}
