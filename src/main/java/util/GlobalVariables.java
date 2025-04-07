package util;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class GlobalVariables {

    public static final String appiumLocalUrl = "http://127.0.0.1:4723/";
    public static final Duration globalTimeout = Duration.ofSeconds(10);

    public GlobalVariables(WebDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, globalTimeout), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Navigate up\")")
    private RemoteWebElement navigateButton;

    public void goBack(){
        navigateButton.click();
    }
}
