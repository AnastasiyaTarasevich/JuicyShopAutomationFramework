package dtos.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RegisterResponseDTO {
    private String status;
    private DataDTO data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataDTO {
        private int id;
        private String email;
        private Boolean isActive;
        private String role;
    }
}
