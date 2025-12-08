package dtos.basket;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dtos.products.ProductDTO;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBasketResponse {

    private String status;
    @JsonProperty("data")
    private DataSet data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataSet {
        private int id;
        @JsonProperty("UserId")
        private int userId;
        @JsonProperty("Products")
        private List<ProductDTO> productDTOList;
    }
}
