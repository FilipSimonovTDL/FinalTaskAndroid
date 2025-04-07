package util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import pages.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@Listeners({ITestListenerUtility.class})
public class DriverSetup extends ConfigReader {

    public static AndroidDriver driver;
    private final AppiumServerManager appiumServerManager = new AppiumServerManager();

    protected GeniusLevelsPage geniusLevelsPage;
    protected  HomePage homePage;
    protected RoomSelectionPage roomSelectionPage;
    protected SavedTripsPage savedTripsPage;
    protected SearchingPage searchingPage;
    protected SearchOutcomePage searchOutcomePage;
    protected SignInPage signInPage;
    protected SignUpPopUp signUpPopUp;
    protected WelcomePage welcomePage;
    protected AllLevelsPage allLevelsPage;

    @Step("Appium server is started")
    @BeforeSuite
    public void startAppiumServer() {
        appiumServerManager.startAppiumServer();
    }

    @Step("Driver is started")
    @BeforeMethod
    public void setUp() {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setAvd(getProperty("avd"))
                .setApp(getProperty("app"))
                .setAppPackage(getProperty("app.package"))
                .setAppActivity(getProperty("app.activity"))
                .setNoReset(false)
                .setFullReset(true)
                .autoGrantPermissions()
                .setCapability("appium:disableIdLocatorAutocompletion", true);

        try {
            driver = new AndroidDriver(new URI(GlobalVariables.appiumLocalUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        geniusLevelsPage = new GeniusLevelsPage(driver);
        homePage = new HomePage(driver);
        roomSelectionPage = new RoomSelectionPage(driver);
        savedTripsPage = new SavedTripsPage(driver);
        searchingPage = new SearchingPage(driver);
        searchOutcomePage = new SearchOutcomePage(driver);
        signInPage = new SignInPage(driver);
        signUpPopUp = new SignUpPopUp(driver);
        welcomePage = new WelcomePage(driver);
        allLevelsPage = new AllLevelsPage(driver);

    }

    @Step("Driver is closed")
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }

    @Step("Appium server is stopped")
    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        appiumServerManager.stopAppiumServer();
    }
}
