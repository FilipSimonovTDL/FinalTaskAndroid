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
import util.Helpers;

public class SignUpPopUp extends Helpers {


    protected AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/facet_with_bottom_sheet_header_drag_line\")")
    private RemoteWebElement slider;

    public SignUpPopUp(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Validating if Sign up PopUp is displayed")
    public boolean signUpPopUpPageIsLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(slider)).isDisplayed();
    }

    @Step("Closing Sign up PopUp")
    public void closePopUp(){
        pressOutsideSlider(driver,slider); // More consistent
      //  swipeDownElementByCoordinates(driver, slider); one solution
    }

}
