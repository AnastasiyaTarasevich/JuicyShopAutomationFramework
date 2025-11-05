package steps.APISteps;

import api.ApiClient;
import dtos.LoginRequestDTO;
import dtos.LoginResponseDTO;
import org.testng.asserts.SoftAssert;
import steps.BaseSteps;

public class LoginSteps extends BaseSteps {

    private final ApiClient apiClient = new ApiClient();
    private LoginResponseDTO response = new LoginResponseDTO();

    public LoginSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public LoginResponseDTO loginAsViaApi(String email, String password) {
        performStep("Login as " + email + " Via API", () -> {

            LoginRequestDTO request = new LoginRequestDTO(email, password);
            response = apiClient.post("user/login", request)
                    .then()
                    .statusCode(200)
                    .extract()
                    .as(LoginResponseDTO.class);
        });
        return response;
    }

    public LoginSteps verifyCorrectEmail(LoginResponseDTO response, String email) {
        performStep("Verify email", () -> {
            softAssert.assertEquals(response.getAuthentication().getUmail(), email);
        });
        return this;
    }

    public LoginSteps verifyToken(LoginResponseDTO response) {
        performStep("Verify token", () -> {
            softAssert.assertNotNull(response.getAuthentication().getToken());
        });
        return this;
    }
}
