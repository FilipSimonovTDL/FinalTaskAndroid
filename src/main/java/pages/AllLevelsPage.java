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

public class AllLevelsPage extends Helpers {

    protected AndroidDriver driver;


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Genius Level 3\")")
    private RemoteWebElement thirdLevel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/action_button\")")
    private RemoteWebElement gotItButton;

    public AllLevelsPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Validating all Levels Page")
    public boolean allLevelsPageIsLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(gotItButton)).isDisplayed();
    }

    @Step("Searching for Third Level")
    public void findThirdLevel(){
        swipeTo(driver,thirdLevel,Directions.LEFT,5);
    }

    @Step("Validating Third Level is displayed")
    public boolean checkIfThirdLevelIsDisplayed(){
        return thirdLevel.isDisplayed();
    }

    @Step("Pressing on Got it")
    public void confirmLevels(){
        gotItButton.click();
    }




}
