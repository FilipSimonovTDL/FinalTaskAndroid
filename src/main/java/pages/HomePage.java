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

public class HomePage {

    protected AndroidDriver driver;


    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign in\")")
    private RemoteWebElement signIn;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Saved\")")
    private RemoteWebElement saved;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Enter your destination\")")
    private RemoteWebElement searchField;

    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.booking:id/facet_index_section_search_view_item\"]/android.view.View/*[1]/*[3]")
    private RemoteWebElement roomsField;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Search\").instance(0)")
    private RemoteWebElement searchButton;

    @Step("Validating if Home Page is loaded")
    public boolean HomePageIsLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(signIn)).isDisplayed();
    }

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Open Sign in page")
    public void openSignInPage(){
        signIn.click();
    }

    @Step("Open Saved trips page")
    public void openSavedTripsPage(){
        saved.click();
    }

    @Step("Opening search bar")
    public void openSearchingPage(){
        searchField.click();
    }

    @Step("Opening Rooms page")
    public void chooseRooms(){
        roomsField.click();
    }

    @Step("Starting search")
    public void startSearch(){
        searchButton.click();
    }
}
