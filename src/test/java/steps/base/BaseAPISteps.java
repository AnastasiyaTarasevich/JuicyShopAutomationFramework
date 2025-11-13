package steps.base;

import api.ApiClient;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

public abstract class BaseAPISteps {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected SoftAssert softAssert;
    protected ApiClient apiClient;

    public BaseAPISteps(SoftAssert softAssert) {
        this.softAssert = softAssert;
        this.apiClient = new ApiClient();
    }

    protected void performStep(String description, Runnable action) {
        Allure.step(description, action::run);
    }
}
