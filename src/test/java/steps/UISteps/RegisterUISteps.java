package steps.UISteps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import dtos.registration.RegisterRequestDTO;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;
import steps.base.BaseUISteps;

public class RegisterUISteps extends BaseUISteps {

    public RegisterUISteps(SoftAssert softAssert) {
        super(softAssert);
    }

    public RegisterUISteps verifyRegisterPageVisible() {
        checkRequiredElementsVisibility(RegisterPage.getRequiredElements());
        return this;
    }

    public RegisterUISteps registerAs(String email, String password, String passwordRepeat,
                                      RegisterRequestDTO.SecurityQuestion securityQuestion,
                                      String securityAnswer) {
        performStep(RegisterPage.EMAIL_INPUT, "Register as" + email, () ->
        {
            elementActions.setValue(RegisterPage.EMAIL_INPUT.getElement(), email);
            elementActions.setValue(RegisterPage.PASSWORD_INPUT.getElement(), password);
            elementActions.setValue(RegisterPage.REPEAT_PASSWORD_INPUT.getElement(), passwordRepeat);

            elementActions.click(RegisterPage.QUESTIONS_INPUT.getElement());
            isElementDisplayed(RegisterPage.QUESTIONS_PANEL, RegisterPage.QUESTIONS_PANEL.name());
            elementActions.findBy(RegisterPage.QUESTIONS_OPTIONS.getElements(),
                    Condition.text(securityQuestion.getQuestion().getQuestion()), 2, true).click();
            elementActions.setValue(RegisterPage.ANSWER_INPUT.getElement(), securityAnswer);
            elementActions.click(RegisterPage.REGISTER_BUTTON.getElement(), 1);
            Selenide.sleep(10000);
        });
        return this;
    }

}
