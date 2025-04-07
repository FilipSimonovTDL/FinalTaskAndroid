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

public class RoomSelectionPage {

    protected AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/bui_input_stepper_add_button\").instance(0)")
    private RemoteWebElement addRoomsButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/bui_input_stepper_add_button\").instance(1)")
    private RemoteWebElement addAdultsButton;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='com.booking:id/bui_input_stepper_buttons_block'])[1]/*[2]")
    private RemoteWebElement roomsCount;

    @AndroidFindBy(xpath = "(//android.widget.LinearLayout[@resource-id='com.booking:id/bui_input_stepper_buttons_block'])[2]/*[2]")
    private RemoteWebElement adultsCount;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.booking:id/group_config_apply_button\")")
    private RemoteWebElement applyButton;



    public RoomSelectionPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @Step("Validating if Room selection page is loaded")
    public boolean RoomSelectionPageIsLoaded(){
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(addRoomsButton)).isDisplayed();
    }

    @Step("Increasing room count")
    public void increaseRoomsByCount(int numberOfRooms){
        int currentValue = Integer.parseInt(roomsCount.getText());
        while (currentValue < numberOfRooms) {
            addRoomsButton.click();
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            currentValue = Integer.parseInt(roomsCount.getText());
        }
    }
    @Step("Increasing adults count")
    public void increaseAdultsByCount(int numberOfAdults){
        int currentValue = Integer.parseInt(adultsCount.getText());
        while (currentValue < numberOfAdults) {
            addAdultsButton.click();
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            currentValue = Integer.parseInt(adultsCount.getText());
        }
    }
    @Step("Increasing room count")
    public void confirmRoomsChoice(){
        applyButton.click();
    }



}
