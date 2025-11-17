package tests.APITests;

import annotations.RegisterUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseAPITest;
import utils.TestGroups;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class BasketTests extends BaseAPITest {
    private final SoftAssert softAssert = new SoftAssert();

    @Test(groups = {TestGroups.API})
    @Feature("Basket")
    @Description("Verify if user can add one product to basket")
    @Severity(CRITICAL)
    @RegisterUser
    public void addOneProductToBasket() {


    }

}
