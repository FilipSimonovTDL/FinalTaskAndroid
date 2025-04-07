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

public class SavedTripsPage {

    protected AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/wishlist_name\")")
    private RemoteWebElement title;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/property_card_view_root\")")
    private RemoteWebElement apartment;

    public SavedTripsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Step("Validating if Saved Trips page is loaded")
    public boolean SavedTripsPageIsLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(title)).isDisplayed();
    }
    @Step("Validating if apartment is saved")
    public boolean apartmentIsSaved(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(apartment)).isDisplayed();
    }
}
