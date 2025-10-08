package tests.UITests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.testng.annotations.Test;
import tests.BaseTest;
import utils.TestGroups;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;


public class LoginTest extends BaseTest {

    @Test(groups = {TestGroups.UI, TestGroups.DEBUG})
    @Description("This test attempts to log into the website using a login and a password")
    @Severity(CRITICAL)
    public void validUserLoginTest() {
        $("button[aria-label='Close Welcome Banner']").click();
        $("#navbarAccount").click();
        $("button[aria-label='Go to login page']").click();
        sleep(2000);
        $("#email").setValue("demo@juice-sh.op");
        sleep(1000);
        $("#password").setValue("demo123");
        sleep(500);
        $("#loginButton").click();
        sleep(500);

    }
}
