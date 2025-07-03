package com.tripgain.testscripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import com.tripgain.collectionofpages.Tripgain_Bookingpage;
import com.tripgain.common.*;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripgain.collectionofpages.Tripgain_Login;
import com.tripgain.collectionofpages.Tripgain_homepage;
import com.tripgain.collectionofpages.Tripgain_resultspage;

@Listeners(com.tripgain.common.TestListener.class)
public class TC_01_VerifySearchFunctionalityForOneWayForTwoDay extends BaseClass{

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    String className = "";
    Log Log;
    ScreenShots screenShots;
    ExtantManager extantManager;
    int number = 1;

    // ThreadLocal to store Excel data per test thread
    static ThreadLocal<Map<String, String>> excelDataThread = new ThreadLocal<>();

    @Test(dataProvider = "sheetBasedData", dataProviderClass = DataProviderUtils.class)
    public void myTest(Map<String, String> excelData) throws InterruptedException, IOException {
        System.out.println("Running test with: " + excelData);
        number++;
        try {
            String userName1 = excelData.get("UserName");
            String password1 = excelData.get("Password");
            String[] dates = GenerateDates.GenerateDatesToSelectFlights();
            String fromDate = dates[11];
            String fromMonthYear = dates[12];
            String origin = excelData.get("Origin");
            String destination = excelData.get("Destination");
            String travelClass = excelData.get("Class");
            int adults = (int) Double.parseDouble(excelData.get("Adults"));
            int selectFlightIndex = (int) Double.parseDouble(excelData.get("SelectFlightIndex"));
            String fareType = excelData.get("FareType");
            String reason = excelData.get("Reason");

            test.log(Status.INFO, "Flight Sector Origin:" +" "+origin+" "+"and Destination:"+" "+destination);
            // Login to Application
            Tripgain_Login tripgainLogin = new Tripgain_Login(driver);
            tripgainLogin.enterUserName(userName1);
            tripgainLogin.enterPasswordName(password1);
            tripgainLogin.clickButton();
            Thread.sleep(2000);
            tripgainLogin.verifyHomePageIsDisplayed(Log, screenShots);

            // Search flights on Search Screen
            Tripgain_homepage tripgainhomepage = new Tripgain_homepage(driver);
            Tripgain_resultspage tripgain_resultspage = new Tripgain_resultspage(driver);
            Tripgain_Bookingpage Tripgain_Bookingpage=new Tripgain_Bookingpage(driver);

            tripgainhomepage.searchFlightsOnHomePage(origin, destination, fromDate, fromMonthYear, travelClass, adults, Log, screenShots);

            // Validate flight Details
            tripgain_resultspage.waitTillFlightDetailsLoaded();
            tripgain_resultspage.getFlightCount(Log, screenShots);
            tripgain_resultspage.getListOfAirlinesOnResultScreen(Log);

            // Select First Flight
            tripgain_resultspage.selectFlightBasedOnIndex(selectFlightIndex);
            tripgain_resultspage.clickOnContinueBasedOnFareType(fareType,reason);

            // Validate Booking Screen is Displayed
            Tripgain_Bookingpage.validateBookingScreenIsDisplayed(Log,screenShots);

        } catch (Exception e) {
            String errorMessage = "Exception occurred: " + e.toString();
            Log.ReportEvent("FAIL", errorMessage);
            screenShots.takeScreenShot();
            e.printStackTrace();  // You already have this, good for console logs
            Assert.fail(errorMessage);
        }
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void launchApplication(String browser, Method method, Object[] testDataObjects) {
        // Get test data passed from DataProvider
        @SuppressWarnings("unchecked")
        Map<String, String> testData = (Map<String, String>) testDataObjects[0];
        excelDataThread.set(testData);  // Set it early!

        String url = (testData != null && testData.get("URL") != null) ? testData.get("URL") : "https://defaulturl.com";

        extantManager = new ExtantManager();
        extantManager.setUpExtentReporter(browser);
        className = this.getClass().getSimpleName();
        String testName = className + "_" + number;
        extantManager.createTest(testName);
        test = ExtantManager.getTest();
        extent = extantManager.getReport();
        test.log(Status.INFO, "Execution Started Successfully");
        driver = launchBrowser(browser, url);
        Log = new Log(driver, test);
        screenShots = new ScreenShots(driver, test);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            extantManager.flushReport();
        }
    }
}



