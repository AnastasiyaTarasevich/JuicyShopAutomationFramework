package tests.APITests;

import annotations.RegisterUser;
import dtos.login.LoginResponseDTO;
import dtos.registration.RegisterRequestDTO;
import dtos.registration.RegisterResponseDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.APISteps.LoginApiSteps;
import steps.APISteps.RegisterApiSteps;
import tests.base.BaseAPITest;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class AuthTests extends BaseAPITest {

    private final SoftAssert softAssert = new SoftAssert();
    private final LoginApiSteps loginSteps = new LoginApiSteps(softAssert);
    private final RegisterApiSteps registerSteps = new RegisterApiSteps(softAssert);


    @Test(groups = {TestGroups.API})
    @Feature("Registration")
    @Description("User is able to register successfully via API")
    @Severity(CRITICAL)
    public void successfulRegistrationTest() {

        RegisterRequestDTO request = registerSteps.createTestUser();
        RegisterResponseDTO response = registerSteps.registerViaApi(request);
        registerSteps.verifyRegisterSuccess(response, request.getEmail());
        softAssert.assertAll();
    }

    @Test(groups = {TestGroups.API})
    @Feature("Login")
    @Description("This test attempts to log into the website using valid login and password")
    @Severity(CRITICAL)
    @RegisterUser
    public void validLoginTest() {
        LoginResponseDTO response = loginSteps.loginAsViaApi(createdUser.getEmail(), createdUser.getPassword());
        loginSteps
                .verifyCorrectEmail(response, createdUser.getEmail())
                .verifyToken(response);

        softAssert.assertAll();
    }
}
