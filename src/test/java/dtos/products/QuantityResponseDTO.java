package dtos.products;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuantityResponseDTO {
    private String status;
    private List<DataSet> data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataSet {
        @JsonProperty("ProductId")
        private int productId;
        private int quantity;
    }
}
