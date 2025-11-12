package steps.APISteps;

import config.ApiEndpoints;
import dtos.registration.RegisterRequestDTO;
import dtos.registration.RegisterResponseDTO;
import dtos.registration.SecurityQuestionEnum;
import org.testng.asserts.SoftAssert;
import steps.base.BaseAPISteps;
import utils.RandomDataGenerator;

public class RegisterApiSteps extends BaseAPISteps {
    private RegisterResponseDTO response;

    public RegisterApiSteps(SoftAssert softAssert) {
        super(softAssert);
    }

    //TODO CREATE ENUM FOR STATUS CODES

    public RegisterRequestDTO createTestUser() {
        String email = RandomDataGenerator.randomEmail();
        String password = RandomDataGenerator.randomPassword();
        int questionId = RandomDataGenerator.randomQuestionId();
        String answer = RandomDataGenerator.randomAnswer();
        RegisterRequestDTO.SecurityQuestion securityQuestion =
                new RegisterRequestDTO.SecurityQuestion(questionId, SecurityQuestionEnum.fromId(questionId));

        return new RegisterRequestDTO(email, password, password, securityQuestion, answer);
    }

    public RegisterResponseDTO registerViaApi(RegisterRequestDTO request) {
        performStep("Register as " + request.getEmail() + " Via API", () -> {

            response = apiClient.post(ApiEndpoints.REGISTER, request)
                    .then()
                    .log().ifError()
                    .statusCode(201)
                    .extract()
                    .as(RegisterResponseDTO.class);
        });
        return response;
    }

    public RegisterApiSteps verifyStatus(RegisterResponseDTO response) {
        performStep("Verify status", () -> {
            softAssert.assertEquals(response.getStatus(), "success", "Status should be success");
        });
        return this;
    }

    public RegisterApiSteps verifyId(RegisterResponseDTO response) {
        performStep("Verify user ID", () -> {
            softAssert.assertNotNull(response.getData().getId(), "User ID should not be null");
        });
        return this;
    }

    public RegisterApiSteps verifyEmail(RegisterResponseDTO response, String expectedEmail) {
        performStep("Verify email", () -> {
            softAssert.assertEquals(response.getData().getEmail(), expectedEmail, "Email should match");
        });
        return this;
    }

    public RegisterApiSteps verifyActive(RegisterResponseDTO response) {
        performStep("Verify user is active", () -> {
            softAssert.assertTrue(response.getData().getIsActive(), "User should be active");
        });
        return this;
    }

    public RegisterApiSteps verifyRole(RegisterResponseDTO response) {
        performStep("Verify user role", () -> {
            softAssert.assertEquals(response.getData().getRole(), "customer", "Role should match");
        });
        return this;
    }

    public RegisterApiSteps verifyRegisterSuccess(RegisterResponseDTO response, String expectedEmail) {
        return this.verifyStatus(response)
                .verifyId(response)
                .verifyEmail(response, expectedEmail)
                .verifyActive(response)
                .verifyRole(response);
    }
}
