package pages;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$;

public enum RegisterPage implements PageElement {
    EMAIL_INPUT("emailControl", true),
    PASSWORD_INPUT("passwordControl", true),
    REPEAT_PASSWORD_INPUT("repeatPasswordControl", true),
    QUESTIONS_INPUT("mat-mdc-form-field-label-8", true),
    QUESTIONS_PANEL("mat-select-1-panel", false),
    QUESTIONS_OPTIONS("", false),
    ANSWER_INPUT("securityAnswerControl", true),
    REGISTER_BUTTON("registerButton", true);

    private final String id;
    private final boolean required;
    public final String pageName = "REGISTER PAGE";

    RegisterPage(String id, boolean required) {
        this.id = id;
        this.required = required;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public String getPageName() {
        return pageName;
    }

    public static PageElement[] getRequiredElements() {
        return values();
    }

    public ElementsCollection getElements() {
        switch (this) {
            case QUESTIONS_OPTIONS:
                return $$("mat-option[role='option']");
            default:
                throw new IllegalStateException("No collection defined for element: " + this.name());
        }
    }
}
