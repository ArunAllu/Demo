
package com.tripgain.roundtriptestscripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tripgain.collectionofpages.*;
import com.tripgain.common.*;
import com.tripgain.testscripts.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

@Listeners(TestListener.class)
public class TCRT_01_VerifySearchFunctionalityForRoundTripForInternationalTwoDays extends BaseClass {

	WebDriver driver;    
	ExtentReports extent;
	ExtentTest test;
	String className = "";
	Log Log;  // Declare Log object
	ScreenShots screenShots;  // Declare Log object
	ExtantManager extantManager;
	// ThreadLocal to store Excel data per test thread
	static ThreadLocal<Map<String, String>> excelDataThread = new ThreadLocal<>();

	int number=1;

	@Test(dataProvider = "sheetBasedData", dataProviderClass = DataProviderUtils.class)
	public void myTest(Map<String, String> excelData) throws InterruptedException, IOException {
		System.out.println("Running test with: " + excelData);
		//To get Data from Excel
		try{
			String userName1 = excelData.get("UserName");
			String password1 = excelData.get("Password");
			String[] dates=GenerateDates.GenerateDatesToSelectFlights();
			String fromDate=dates[11];
			String returnDate=dates[0];
			String fromMonthYear=dates[2];
			String returnMonthYear=dates[10];
			number++;
			String origin = excelData.get("Origin");
			String destination = excelData.get("Destination");
			String travelClass = excelData.get("Class");
			int adults = Integer.parseInt(excelData.get("Adults"));
			int flightIndex = Integer.parseInt(excelData.get("FlightIndex"));
			String fairType = excelData.get("FairType");
			String reason = excelData.get("Reason");

			test.log(Status.INFO, "Flight Sector Origin:" +" "+origin+" "+"and Destination:"+" "+destination);

			//Functions to Login TripGain Application
			Tripgain_Login tripgainLogin= new Tripgain_Login(driver);
			tripgainLogin.enterUserName(userName1);
			tripgainLogin.enterPasswordName(password1);
			tripgainLogin.clickButton();
			Thread.sleep(2000);
			tripgainLogin.verifyHomePageIsDisplayed(Log,screenShots);

			//Functions to Search flights on Home Page
			Tripgain_homepage tripgainhomepage = new Tripgain_homepage(driver);
			Tripgain_RoundTripResultScreen tripgain_RoundTripResultScreen=new Tripgain_RoundTripResultScreen(driver);
			Tripgain_resultspage tripgain_resultspage=new Tripgain_resultspage(driver);
			Tripgain_Bookingpage Tripgain_Bookingpage=new Tripgain_Bookingpage(driver);
			tripgainhomepage.searchFlightsOnHomePageForRoundTripForInternational(origin, destination, fromDate, fromMonthYear, returnDate, returnMonthYear,travelClass,adults,Log,screenShots);

			//Functions to Validate flights on Result Screen
			tripgain_RoundTripResultScreen.verifyFlightsDetailsOnResultScreenForInternational(Log,screenShots);

			//Function to Get all Flight Names.
			tripgain_resultspage.getFlightCount(Log, screenShots);
			tripgain_resultspage.getListOfAirlinesOnResultScreen(Log);

			//Function to Select Flight and Continue.
			tripgain_RoundTripResultScreen.clickOnSelectFlightBasedOnIndex(flightIndex);
			tripgain_RoundTripResultScreen.clickOnFlightInfoDetails();
			tripgain_resultspage.clickOnContinueBasedOnFareType(fairType,reason);

			// Validate Booking Screen is Displayed
			Tripgain_Bookingpage.validateBookingScreenIsDisplayed(Log,screenShots);

		}catch (Exception e)
		{
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
