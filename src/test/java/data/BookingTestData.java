package data;

import org.testng.annotations.DataProvider;


public class BookingTestData {

    @DataProvider(name = "BookingData")
    public Object[][] BookingData() {
        return new Object[][] {
                {"20", "April", "2025", "25", "April", "2025"},
                {"05", "May", "2025", "10", "May", "2025"},
                {"20", "June", "2025", "25", "June", "2025"}
        };
    }
}
