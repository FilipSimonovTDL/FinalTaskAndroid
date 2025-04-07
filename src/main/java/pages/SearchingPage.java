package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;
import util.Helpers;

public class SearchingPage extends Helpers {

    protected AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/facet_with_bui_free_search_booking_header_toolbar_content\")")
    private RemoteWebElement searchInput;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").instance(1)")
    private RemoteWebElement firstLocationSuggestion;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/facet_date_picker_confirm\")")
    private RemoteWebElement confirmDatesButton;

    @Step("Validating if searching page is loaded")
    public boolean searchingPageIsLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(searchInput)).isDisplayed();
    }

    public SearchingPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Inserting search location")
    public void searchLocation(String location){
        searchInput.sendKeys(location);
    }

    @Step("Pressing on search suggestion")
    public void selectLocation(){
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(firstLocationSuggestion)).isDisplayed();
        firstLocationSuggestion.click();
    }

    @Step("Selecting date")
    public void selectDates(String day, String month, String year,int swipes){
        String date = day + " " + month + " " + year;
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(confirmDatesButton)).isDisplayed();
        String uiSelector = "new UiSelector().description(\"" + date + "\")";
        WebElement element = null;
        for (int i = 0; i < swipes; i++) {
            try {
                element = driver.findElement(AppiumBy.androidUIAutomator(uiSelector));
                break;
            } catch (NoSuchElementException e) {
                swipeByDirection(driver, Directions.UP);
            }
        }
        if(element!=null){
            element.click();
        }else {
            throw new AssertionError("Date " + date + " was not found after " + swipes + " swipes");
        }
    }

    @Step("Confirming date")
    public void confirmDates(){
        confirmDatesButton.click();
    }

}
