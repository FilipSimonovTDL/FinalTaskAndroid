package tests;

import data.BookingTestData;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;


public class BookingAppTests extends DriverSetup  {
    @Description("Checking Genius levels and Sign up")
    @Story("Checking Genius Levels")
    @Test(testName = "Checking Genius Levels")
    public void checkGeniusLevels(){
        Assert.assertTrue(welcomePage.welcomePageIsLoaded(),"Failed to load Welcome Page");
        welcomePage.continueToHomePage();
        Assert.assertTrue(signUpPopUp.signUpPopUpPageIsLoaded(),"Failed to load Sign In PopUp");
        signUpPopUp.closePopUp();
        Assert.assertTrue(homePage.HomePageIsLoaded(),"Failed to load Home Page");
        homePage.openSignInPage();
        Assert.assertTrue(signInPage.SignInPageIsLoaded(), "Failed to load Sign In Page");
        signInPage.openLoyaltyProgram();
        Assert.assertTrue(geniusLevelsPage.geniusLevelsPageIsLoaded(),"Failed to load Levels Page");
        geniusLevelsPage.openAllLevels();
        Assert.assertTrue(allLevelsPage.allLevelsPageIsLoaded(),"Failed to load All levels PopUp Page");
        allLevelsPage.findThirdLevel();
        Assert.assertTrue(allLevelsPage.checkIfThirdLevelIsDisplayed(),"Failed to display third level");
        allLevelsPage.confirmLevels();
        Assert.assertTrue(geniusLevelsPage.checkIfItGotBack(),"Failed to go back to Genius Levels Page");
        geniusLevelsPage.goBack();
        Assert.assertTrue(signInPage.SignInPageIsLoaded(), "Failed to load Sign In Page");
    }

    @Description("Search for a location and add an hotel to favorites")
    @Story("Successful search and favorites selection")
    @Test(testName = "Search and Favorites Test", dataProviderClass = BookingTestData.class, dataProvider = "BookingData")
    public void searchAndAddToFavoritesTest(String startDay,String startMonth, String startYear, String endDay,  String endMonth, String endYear) {
        Assert.assertTrue(welcomePage.welcomePageIsLoaded(), "Failed to load Welcome Page");
        welcomePage.continueToHomePage();
        Assert.assertTrue(signUpPopUp.signUpPopUpPageIsLoaded(), "Failed to load Sign In PopUp");
        signUpPopUp.closePopUp();
        Assert.assertTrue(homePage.HomePageIsLoaded(), "Failed to load Home Page");
        homePage.openSearchingPage();
        Assert.assertTrue(searchingPage.searchingPageIsLoaded(), "Failed to load Searching Page");
        searchingPage.searchLocation("Skopje");
        searchingPage.selectLocation();
        searchingPage.selectDates(startDay, startMonth, startYear, 5);
        searchingPage.selectDates(endDay, endMonth, endYear,5);
        searchingPage.confirmDates();
        Assert.assertTrue(homePage.HomePageIsLoaded(), "Failed to load Home Page");
        homePage.chooseRooms();
        Assert.assertTrue(roomSelectionPage.RoomSelectionPageIsLoaded(), "Failed to load Rooms selection Page");
        roomSelectionPage.increaseRoomsByCount(2);
        roomSelectionPage.increaseAdultsByCount(3);
        roomSelectionPage.confirmRoomsChoice();
        Assert.assertTrue(homePage.HomePageIsLoaded(), "Failed to load Home Page");
        homePage.startSearch();
        Assert.assertTrue(searchOutcomePage.SearchOutcomePageIsLoaded(), "Failed to load Search Outcome Page");
        searchOutcomePage.verifyLocation("Skopje");
        searchOutcomePage.verifyDates(startDay,endDay,startMonth,endMonth);
        searchOutcomePage.markApartmentAsFavorite();
        searchOutcomePage.goBackToPreviousPage();
        Assert.assertTrue(homePage.HomePageIsLoaded(), "Failed to load Home Page");
        homePage.openSavedTripsPage();
        Assert.assertTrue(savedTripsPage.SavedTripsPageIsLoaded(), "Failed to load Saved Trips Page");
        Assert.assertTrue(savedTripsPage.apartmentIsSaved(),"Apartment wasn't saved");
    }
}
