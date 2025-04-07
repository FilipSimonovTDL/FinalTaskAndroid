package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class WelcomePage{


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/identity_header_title\")")
    private RemoteWebElement title;

    protected AndroidDriver driver;
    protected GlobalVariables globalVariables;

    public WelcomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        globalVariables = new GlobalVariables(driver);
    }
    @Step("Validating if Welcome page is loaded")
    public boolean welcomePageIsLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(title)).isDisplayed();
    }
    @Step("Going back to previous page")
    public void continueToHomePage(){
        globalVariables.goBack();
    }
}
