package dtos.login;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private Authentication authentication;

    @Data
    public static class Authentication {
        private String token;
        private int bid;
        private String umail;

    }
}
