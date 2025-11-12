package dtos.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String passwordRepeat;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SecurityQuestion {
        private int id;
        private SecurityQuestionEnum question;
    }
}
