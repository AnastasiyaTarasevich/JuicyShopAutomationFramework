package steps.APISteps;

import config.ApiEndpoints;
import dtos.login.LoginRequestDTO;
import dtos.login.LoginResponseDTO;
import org.testng.asserts.SoftAssert;
import steps.base.BaseAPISteps;
import utils.StatusCode;

public class LoginApiSteps extends BaseAPISteps {
    private LoginResponseDTO response = new LoginResponseDTO();

    public LoginApiSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public LoginResponseDTO loginAsViaApi(String email, String password) {
        performStep("Login as " + email + " Via API", () -> {

            LoginRequestDTO request = new LoginRequestDTO(email, password);
            response = apiClient.post(ApiEndpoints.LOGIN, request)
                    .then()
                    .statusCode(StatusCode.OK.getStatusCode())
                    .extract()
                    .as(LoginResponseDTO.class);
        });
        return response;
    }

    public LoginApiSteps verifyCorrectEmail(LoginResponseDTO response, String email) {
        performStep("Verify email", () -> {
            softAssert.assertEquals(response.getAuthentication().getUmail(), email);
        });
        return this;
    }

    public LoginApiSteps verifyToken(LoginResponseDTO response) {
        performStep("Verify token", () -> {
            softAssert.assertNotNull(response.getAuthentication().getToken());
        });
        return this;
    }
}
