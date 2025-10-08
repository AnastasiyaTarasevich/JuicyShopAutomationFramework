package tests.UITests;

import org.testng.annotations.Test;
import tests.BaseTest;
import utils.TestGroups;

import static com.codeborne.selenide.Selenide.*;


public class LoginTest extends BaseTest {

    @Test(groups = {TestGroups.UI})
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
