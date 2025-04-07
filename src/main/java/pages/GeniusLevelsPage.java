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

public class GeniusLevelsPage extends Helpers {

    protected AndroidDriver driver;
    protected GlobalVariables globalVariables;


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/genius_value_title\")")
    private RemoteWebElement geniusTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/genius_see_all_levels\")")
    private RemoteWebElement levels;

    public GeniusLevelsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        globalVariables = new GlobalVariables(driver);
    }

    @Step("Validating if Genius Levels Page is loaded")
    public boolean geniusLevelsPageIsLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(geniusTitle)).isDisplayed();
    }

    @Step("Validating if driver returned to Genius Levels Page")
    public boolean checkIfItGotBack(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(levels)).isDisplayed();
    }

    @Step("Searching for all level button")
    public void openAllLevels() {
        scrollTo(driver,levels,Directions.UP,5);
        levels.click();
    }
    @Step("Returning to previous page")
    public void goBack(){
        globalVariables.goBack();
    }
}
