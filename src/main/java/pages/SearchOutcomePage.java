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

import java.time.Month;

public class SearchOutcomePage {

    protected AndroidDriver driver;
    protected GlobalVariables globalVariables;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/searchbox_destination\")")
    private RemoteWebElement location;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/searchbox_dates\")")
    private RemoteWebElement dates;

    @AndroidFindBy(xpath = "(//androidx.recyclerview.widget.RecyclerView//android.widget.ImageView[@a11y-important='true'])[1]")
    private RemoteWebElement firstHeartButton;

    public SearchOutcomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        globalVariables = new GlobalVariables(driver);
    }

    @Step("Validating if Search outcome page is loaded")
    public boolean SearchOutcomePageIsLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(location)).isDisplayed();
    }

    @Step("Going back to previous page")
    public void goBackToPreviousPage(){
        globalVariables.goBack();
    }

    @Step("Verifying if location in search matches input")
    public boolean verifyLocation(String inputLocation){
        String searchLocation = location.getText();
        return searchLocation.equals(inputLocation);
    }

    @Step("Verifying if dates in search match input")
    public boolean verifyDates(String startDay, String endDay, String startMonth, String endMonth){
        startMonth = startMonth.substring(0, 3);
        endMonth = endMonth.substring(0, 3);
        String searchDate = dates.getText();
        return (searchDate.contains(startDay) && searchDate.contains(endDay) && searchDate.contains(startMonth) && searchDate.contains(endMonth));
    }

    @Step("Adding apartment to favorites")
    public void markApartmentAsFavorite(){
        firstHeartButton.click();
    }
}
