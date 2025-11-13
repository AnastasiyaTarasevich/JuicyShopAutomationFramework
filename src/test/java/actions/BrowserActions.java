package actions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserActions {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected String getCurrentWindowHandle() {
        String handle = WebDriverRunner.getWebDriver().getWindowHandle();
        return handle;
    }

    public void switchToWindow(String windowHandle) {
        WebDriverRunner.getWebDriver().switchTo().window(windowHandle);
        log.info("Switched to tab: {}", WebDriverRunner.getWebDriver().getTitle());
    }

    public void switchSeleniumContextToNewTab() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String originalWindow = driver.getWindowHandle();
        log.info("Current window handle: {}", originalWindow);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                switchToWindow(windowHandle);
                break;
            }
        }
    }

    public String currentUrl() {
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
        log.info("Current URL: {}", url);
        return url;
    }

    protected void goBack() {
        String url = currentUrl();
        log.info("Go to the previous page from URL: {}", url);
        Selenide.back();
    }

    public void refreshPage() {
        String url = currentUrl();
        log.info("Refresh page: {}", url);
        Selenide.refresh();
    }

    public void closeCurrentTab() {
        String url = currentUrl();
        String windowHandle = getCurrentWindowHandle();
        WebDriverRunner.getWebDriver().close();
        log.info("Closed tab: {} with URL: {}", windowHandle, url);
    }
}
