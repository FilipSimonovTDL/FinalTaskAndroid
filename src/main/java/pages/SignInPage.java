package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class SignInPage {

    protected AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Genius loyalty program\")")
    private RemoteWebElement loyaltyProgram;

    @Step("Validating if Sign in page is loaded")
    public boolean SignInPageIsLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(loyaltyProgram)).isDisplayed();
    }

    public SignInPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Opening loyalty program")
    public void openLoyaltyProgram(){
        loyaltyProgram.click();
    }
}
