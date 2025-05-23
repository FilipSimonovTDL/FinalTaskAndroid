package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.DriverSetup.driver;

public class ScreenshotUtility {

    public void captureScreenshot(ITestResult result, String status) {
        System.out.println("Driver session ID: " + driver.getSessionId());
        String destDir = "";
        String passFailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

        if (status.equalsIgnoreCase("fail")) {
            destDir = "screenshots/Fails";
        } else if (status.equalsIgnoreCase("pass")) {
            destDir = "screenshots/Pass";
        }

        new File(destDir).mkdirs();
        String destFile = passFailMethod + " - " + dateFormat.format(new Date()) + ".png";

        try {
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
