package com.tripgain.collectionofpages;
import com.tripgain.common.TestExecutionNotifier;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tripgain.common.Log;
import com.tripgain.common.ScreenShots;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tripgain.collectionofpages.BaggageDetails.getBaggageDetailsManual;

public class Tripgain_resultspage {

	WebDriver driver;

	public Tripgain_resultspage(WebDriver driver) {

		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	//Method to click On Currency DropDown
	public void clickOnCurrencyDropDown() throws InterruptedException {

		driver.findElement(By.xpath("//label[text()='Currency']/parent::div//div[contains(@class,'tg-select__indicators')]")).click();
	}

	//Method to select Currency DropDown Values
	public void selectCurrencyDropDownValues(String currencyCode) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		WebElement currencyValue = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[text()='" + currencyCode + "']")
		));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// 🛠️ FIXED: "argument" → "arguments"
		js.executeScript("arguments[0].scrollIntoView(true);", currencyValue);
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[text()='" + currencyCode + "']")).click();
//		wait.until(ExpectedConditions.elementToBeClickable(currencyValue)).click();
	}

	//Method to click On Check In Baggage
	public void clickOnCheckInBaggage() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Show Flights With Check-in Baggage']/parent::div/parent::li//input")).click();
	}

	//Method to click on Refundable Fare
	public void clickOnRefundableFare() throws InterruptedException {
		Thread.sleep(3000);
		WebElement refundableFair=driver.findElement(By.className("tg-flRefundablefare"));
		Actions move=new Actions(driver);
		move.moveToElement(refundableFair).perform();
		refundableFair.click();
		Thread.sleep(4000);
	}

	//Method to click on AirLine Stops
	public void clickOnStops(String stops) {
		driver.findElement(By.xpath("//*[contains(@class,'filter-stops')]//button[text()='" + stops + "']")).click();
	}

	//Method to click ONWARD DEPART TIME
	public void selectOnWardDepartTime(String time) throws InterruptedException {
		driver.findElement(By.xpath("//legend[text()='ONWARD DEPART TIME']//parent::div//small[text()='" + time + "']")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD DEPART TIME
	public void selectOnWardDepartTimeZeroToSix() throws InterruptedException {
		driver.findElement(By.xpath("//*[contains(@class,'tg-dep-time-06')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD DEPART TIME
	public void selectOnWardDepartTimeSixToTwelve() throws InterruptedException {
		driver.findElement(By.xpath("//*[contains(@class,'tg-dep-time-12')]")).click();
		Thread.sleep(5000);
	}


	//Method to click ONWARD DEPART TIME
	public void selectOnWardDepartTimeTwelveToEighteen() throws InterruptedException {
		driver.findElement(By.xpath("//*[contains(@class,'tg-dep-time-18')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD DEPART TIME
	public void selectOnWardDepartTimeEighteenToTwentyFour() throws InterruptedException {
		driver.findElement(By.xpath("//*[contains(@class,'tg-dep-time-24')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectOnWardArrivalTime(String time) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//legend[text()='ONWARD ARRIVAL TIME']//parent::div//small[text()='" + time + "']")).click();
		Thread.sleep(5000);

	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectOnWardArrivalTimeZeroToSix() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-arr-time-06')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectOnWardArrivalTimeSixToTwelve() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-arr-time-12')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectOnWardArrivalTimeTwelveToEighteen() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-arr-time-18')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectOnWardArrivalTimeEighteenToTwentyFour() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-arr-time-24')]")).click();
		Thread.sleep(5000);
	}


	//Method to click ONWARD ARRIVAL TIME
	public void selectReturnDepartTimeZeroToSix() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-returnDTdeptime-06')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectReturnDepartTimeSixToTwelve() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-returnDTdeptime-12')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectReturnDepartTimeTwelveToEighteen() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-returnDTdeptime-18')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectReturnDepartTimeEighteenToTwentyFour() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-returnDTadeptime-24')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectReturnArrivalTimeZeroToSix() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-returnATarrtime-06')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectReturnArrivalTimeSixToTwelve() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'returnATarrtime-12')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectReturnArrivalTimeTwelveToEighteen() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-returnATarrtime-18')]")).click();
		Thread.sleep(5000);
	}

	//Method to click ONWARD ARRIVAL TIME
	public void selectReturnArrivalTimeEighteenToTwentyFour() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-returnATarrtime-24')]")).click();
		Thread.sleep(5000);
	}

	//Method to click on SME / Corporate Fare
	public void clickOnSmeAndCorporateFareOnly() throws InterruptedException {
		driver.findElement(By.xpath("//legend[text()='SME / Corporate Fare']/parent::div//input")).click();
		Thread.sleep(1000);
	}

	//Method to Click On InPolicy
	public void clickOnPolicy() {
		driver.findElement(By.xpath("//span[text()='Show In Policy Only']/parent::div/parent::li//input")).click();
	}

	//Method for AIRLINES
	public void airLines(String airlines) {
		driver.findElement(By.xpath("//legend[text()='AIRLINES']//parent::div//span[text()='" + airlines + "']//parent::div/parent::li//input")).click();
	}

	//Method for Clear Filter
	public void clickOnClearFilter() {
		driver.findElement(By.className("clear-filters")).click();
	}


	//Method to Validate Airline List
	public void validateAirLinesList(String airlinename) {
		String airline = driver.findElement(By.xpath("(//div[@class='owf-carrier-info']//p)[1]")).getText();

		Assert.assertEquals(airlinename, airline);


		List<WebElement> airlineName = driver.findElements(By.xpath("//div[@class='mb-60']//div[@class='owf-carrier-info']"));

		for (WebElement name : airlineName) {

			String names = name.getText();
			System.out.println(names);

		}


	}

	//Method to get Number Of Airlines in the Result Page
	public void getNumberOfAirLines() {
		List<WebElement> airlineName = driver.findElements(By.xpath("//div[@class='mb-60']//div[@class='owf-carrier-info']"));
		int total = airlineName.size();
		System.out.println(total);
	}


	//Method to Select Horizontal Date on Home Page and Validate.
	public void selectHorizontalDate(String dayAbbreviation, int day, String monthAbbreviation, Log Log, ScreenShots ScreenShots) throws TimeoutException, ParseException, InterruptedException {

		try {
			String suffix = getDaySuffix(day);
			String formattedDate = String.format("%s, %d%s %s", dayAbbreviation, day, suffix, monthAbbreviation);

			System.out.println("Looking for date: " + formattedDate);
			System.out.println("------------------------------------");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); // Wait up to 10 seconds

			try {
				// Check if the date is visible initially
				WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + formattedDate + "']")));
				dateElement.click(); // Click the date element
			} catch (TimeoutException e) {
				// If the element is not found, keep navigating until the date is found
				while (true) {
					try {
						// Click the next arrow to go to the next month/section
						WebElement nextArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='ChevronRightIcon']")));
						nextArrow.click();

						// Check if the date element appears
						WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + formattedDate + "']")));
						dateElement.click(); // Click the date element
						break; // Break the loop once the date is found and clicked
					} catch (TimeoutException ex) {
						// If the next arrow is not clickable or the date is not found, keep trying
						System.out.println("Date not found in the current view. Trying next...");
					}
				}
			}

			// Clean the ordinal suffix (st, nd, rd, th)
			String cleanedDate = formattedDate.replaceAll("(\\d+)(st|nd|rd|th)", "$1");

			// Parse the date
			SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM", Locale.ENGLISH);
			Date date = inputFormat.parse(cleanedDate);

			// Set year to 2025
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.YEAR, 2025);

			// Get day with suffix
			int days = cal.get(Calendar.DAY_OF_MONTH);
			String dayWithSuffix = getDayWithSuffix(days);

			// Format final date
			SimpleDateFormat monthFormat = new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH);
			String formattedMonthYear = monthFormat.format(cal.getTime());

			String finalDate = dayWithSuffix + "-" + formattedMonthYear;
			System.out.println("Date as been Selected" + finalDate); // Output: 19th-Apr-2025

			Thread.sleep(3000);
			String selectedDate = driver.findElement(By.xpath("//input[contains(@class,'tg-fsonwarddate')]")).getAttribute("value");
			System.out.println(selectedDate); // Output: 19th-Apr-2025

			Assert.assertEquals(finalDate, selectedDate, "Date had been Modification is Successful");
			System.out.println("Date as been Selected" + " " + finalDate + " and Validated on Home page" + " " + selectedDate); // Output: 19th-Apr-2025

			if (finalDate.contentEquals(selectedDate)) {
				Log.ReportEvent("PASS", "User Selected date from Horizontal date picker is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "User Selected date from Horizontal date picker is UnSuccessful");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "User Selected date from Horizontal date picker is UnSuccessful");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}

	}


	private String getDaySuffix(int day) {
		if (day >= 11 && day <= 13) return "th";
		switch (day % 10) {
			case 1:
				return "st";
			case 2:
				return "nd";
			case 3:
				return "rd";
			default:
				return "th";
		}
	}

	// Method to get day with suffix (st, nd, rd, th)
	private static String getDayWithSuffix(int day) {
		if (day >= 11 && day <= 13) {
			return day + "th";
		}
		switch (day % 10) {
			case 1:
				return day + "st";
			case 2:
				return day + "nd";
			case 3:
				return day + "rd";
			default:
				return day + "th";
		}
	}


	//Method to Select flight based on Index
	public void selectFlightBasedOnIndex(int index) throws InterruptedException {
		TestExecutionNotifier.showExecutionPopup();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[contains(@class,'tg-flighselect-btn')])["+index+"]")).click();
		Thread.sleep(2000);

	}

	//Method to Validate data after selection of Flight on Result Screen
	public void validateDataAfterSelectingFlight(Log Log, ScreenShots ScreenShots, int departureIndex, int arrivalIndex, int priceIndex, String reason) {
		try {
			TestExecutionNotifier.showExecutionPopup();
			String departureTime = driver.findElement(By.xpath("(//h6[@data-tgdeptime])[" + departureIndex + "]")).getText();
			String arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgarrivaltime])[" + arrivalIndex + "]")).getText();

			// Concatenate with dash
			String finalTime = departureTime + "-" + arrivalTime;
			System.out.println("----------------------------------------------------------"); // Output: 16:00-02:15
			System.out.println("Final Time Range: " + finalTime);
			System.out.println("----------------------------------------------------------"); // Output: 16:00-02:15

			String price = driver.findElement(By.xpath("(//span[@data-tgprice])[" + priceIndex + "]")).getText();
			String priceOnly = price.split(" ")[1];
			String onlyPrice = priceOnly.split("\n")[0];
			String bookingPrice = driver.findElement(By.xpath("//h6[@data-tgfullfare]")).getText();
			String bookingPriceOnly = bookingPrice.split(" ")[1];
			String finalBookingPriceOnly = bookingPriceOnly.split("\n")[0];


			String timingsOnly = driver.findElement(By.xpath("//p[@data-tgfulltime]")).getText();
			// Remove all whitespace characters (spaces, tabs, etc.)
			String arrivalAndDeparture = timingsOnly.replaceAll("\\s+", "");


			System.out.println("Arrival and Departure time " + arrivalAndDeparture); // Output: 16:00-02:15
			System.out.println("----------------------------------------------------------"); // Output: 16:00-02:15      
			System.out.println(onlyPrice);
			System.out.println(finalBookingPriceOnly);

			String fromLocation = driver.findElement(By.xpath("(//div[contains(@class,'tg-select__single-value')])[1]")).getText();
			int start = fromLocation.indexOf('(');
			int end = fromLocation.indexOf(')');

			String fromLocationCode = null;
			if (start != -1 && end != -1) {
				fromLocationCode = fromLocation.substring(start + 1, end);
				System.out.println(fromLocationCode);  // Output: BLR
			}

			String ToLocation = driver.findElement(By.xpath("(//div[contains(@class,'tg-select__single-value')])[2]")).getText();
			int start1 = ToLocation.indexOf('(');
			int end1 = ToLocation.indexOf(')');

			String toLocationCode = null;
			if (start1 != -1 && end1 != -1) {
				toLocationCode = ToLocation.substring(start1 + 1, end1);
				System.out.println(toLocationCode);  // Output: BLR
			}
			String codes = fromLocationCode + "-" + toLocationCode;
			String fromAndToCode = driver.findElement(By.xpath("//p[@data-tgfullsector]")).getText();
			String cleanedTimeRange = fromAndToCode.replaceAll("\\s+", "");

			if (codes.contentEquals(cleanedTimeRange)) {
				ValidateActualAndExpectedValuesForFlights(priceOnly, finalBookingPriceOnly, "Price Details in Search Screen and Bottom Bar", Log);
				ValidateActualAndExpectedValuesForFlights(finalTime, arrivalAndDeparture, "Arrival and Departing Details in Search Screen and Booking Bar", Log);
				clickOnContinueBookingFlightPopup();
				if (driver.findElement(By.xpath("//h2[@id='alert-dialog-title']")).isDisplayed()) {
					selectReasonForSelection(reason);
					clickOnProceedBooking();
				}

			} else {
				clickOnContinueBookingFlightPopup();
				String origenCode = driver.findElement(By.xpath("(//h2[text()='Airport Change']/parent::div//strong)[1]")).getText();
				String destinationCode = driver.findElement(By.xpath("(//h2[text()='Airport Change']/parent::div//strong)[2]")).getText();
				ValidateActualAndExpectedValuesForFlights(origenCode, fromLocationCode, "Origin Details in Search Screen and Bottom Bar", Log);
				ValidateActualAndExpectedValuesForFlights(destinationCode, toLocationCode, "Destination Details in Search Screen and Bottom Bar", Log);
				clickOnYesContinueOnContinuePopup();
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights Price, Arrival and Departure Time is displaying Same" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}

	}

	// Method to Validate Actual and Expected Data with Messages for Both Pass and Fail
	public void ValidateActualAndExpectedValuesForFlights(String actual, String expected, String message, Log log) {
		try {
			if (actual.contentEquals(expected)) {
				log.ReportEvent("PASS", String.format("%s | Actual: '%s', Expected: '%s' - Values match.", message, actual, expected));
			} else {
				log.ReportEvent("FAIL", String.format("%s | Actual: '%s', Expected: '%s' - Values do not match.", message, actual, expected));
				Assert.fail("Validation Failed: " + message);
			}
		} catch (Exception e) {
			log.ReportEvent("FAIL", String.format("%s | Actual: '%s', Expected: '%s' - Exception during comparison.", message, actual, expected));
			e.printStackTrace();
			Assert.fail("Exception during validation: " + message);
		}
	}

	// Method to Validate Actual and Expected Data with Messages for Both Pass and Fail
	public void ValidateActualAndExpectedValuesForFlightFairs(String actual, String expected,String expected1,String message, Log log) {
		try {
			if (actual.contentEquals(expected)||actual.contentEquals(expected1)) {
				log.ReportEvent("PASS", String.format("%s | Actual: '%s', Expected: '%s' - Values match.", message, actual, expected));
			} else {
				log.ReportEvent("FAIL", String.format("%s | Actual: '%s', Expected: '%s' - Values do not match.", message, actual, expected));
				Assert.fail("Validation Failed: " + message);
			}
		} catch (Exception e) {
			log.ReportEvent("FAIL", String.format("%s | Actual: '%s', Expected: '%s' - Exception during comparison.", message, actual, expected));
			e.printStackTrace();
			Assert.fail("Exception during validation: " + message);
		}
	}



	//Method to Click on Cancel Button On Popup
	public void cancelButtonPoPup() {
		driver.findElement(By.xpath("//button[text()='No, Cancel']")).click();
	}


	//Method to Validate flights on Result Page
	public void validateFlightsResults(Log Log, ScreenShots ScreenShots) {
		try {
			TestExecutionNotifier.showExecutionPopup();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='oneway-header']/following-sibling::div")));
			WebElement flights = driver.findElement(By.xpath("//div[@class='oneway-header']/following-sibling::div"));

			if (flights.isDisplayed()) {
				Log.ReportEvent("PASS", "Flights are displayed based on User Search is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search Please Change Filter");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search Please Change Filter");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}
	}

	//Method to Verify Horizontal date picker is Displayed on Home Page.
	public void verifyHorizontalDatePickerIsDisplayed(Log Log, ScreenShots ScreenShots) {
		try {
			WebElement horizontalDatePicker = driver.findElement(By.xpath("//div[@class='main-container']/child::div[@justify='center']"));
			if (horizontalDatePicker.isDisplayed()) {
				Log.ReportEvent("PASS", "Horizontal date picker displayed is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Horizontal date picker is Not displayed");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Horizontal date picker is Not displayed");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}

	}

	//Method to Verify Default Currency Drop down is Selected on Home Page.
	public void verifyDefaultCurrencyIsSelected(Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(4000);
			WebElement defaultCurrencyDropdownSelected = driver.findElement(By.xpath("//div[text()='INR']"));
			if (defaultCurrencyDropdownSelected.isDisplayed()) {
				Log.ReportEvent("PASS", "Default Currency INR is Selected on Currency Dropdown is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Default Currency INR is Not Selected on Currency Dropdown");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Default Currency INR is Not Selected on Currency Dropdown");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}

	//Method to validate Refundable fare filter
	public void validateRefundableFareFlights(Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);
			WebElement fare = driver.findElement(By.className(("tg-fare-refundableinfo")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fare);
			((JavascriptExecutor) driver).executeScript(
					"window.scrollTo({ top: arguments[0].getBoundingClientRect().top + window.scrollY - 100, behavior: 'smooth' });",
					fare);
			Thread.sleep(5000);
			List<WebElement> refundableFareDatas = driver.findElements(By.className("tg-fare-refundableinfo"));
			for (WebElement refundableFareData : refundableFareDatas) {
				String text = refundableFareData.getText();
				System.out.println(text); // Log each amenities string
				// Must NOT contain "non-refundable", and must contain "refundable"
				ValidateActualAndExpectedValuesForFlights(text, "Refundable","Refundable Fare data", Log);
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception while validating refundable fare data: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Method to validate both Refundable and NonRefundable fare after unselecting
	public void validateUnCheckingRefundableFareFlights(Log Log, ScreenShots ScreenShots) {
			try {
				Thread.sleep(5000);
				WebElement fare = driver.findElement(By.className(("tg-fare-refundableinfo")));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fare);
				((JavascriptExecutor) driver).executeScript(
						"window.scrollTo({ top: arguments[0].getBoundingClientRect().top + window.scrollY - 100, behavior: 'smooth' });",
						fare);
				Thread.sleep(5000);
				List<WebElement> refundableFareDatas = driver.findElements(By.className("tg-fare-refundableinfo"));
				for (WebElement refundableFareData : refundableFareDatas) {
					String text = refundableFareData.getText();
					System.out.println(text); // Log each amenities string
					// Must NOT contain "non-refundable", and must contain "refundable"
					ValidateActualAndExpectedValuesForFlightFairs(text, "Refundable", "Non-refundable", "Refundable Fare data", Log);
				}
			} catch (Exception e) {
				Log.ReportEvent("FAIL", "Exception while validating refundable fare data: " + e.getMessage());
				ScreenShots.takeScreenShot1();
				e.printStackTrace();
				Assert.fail();
			}

	}


	//Method to validate flights stops on Result Screen
	public void validateFlightsStopsOnResultScreen(String numberOfStops, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(6000);
			List<WebElement> flightStops = driver.findElements(By.className("tg-stops"));
			boolean stops = true;
			if (flightStops.size() > 0) {
				for (WebElement flightStop : flightStops) {
					String stop = flightStop.getText();
					if (stop.contentEquals(numberOfStops)) {
						System.out.println(stop);

					} else {
						stops = false;
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched");
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				}
				if (stops == true) {
					Log.ReportEvent("PASS", "Flights displayed based on User Searched is Successful");
					ScreenShots.takeScreenShot1();
				}
			} else {
				Log.ReportEvent("FAIL", "No Flights are displayed based on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}

	}

	//Method to validate flights departure time on result screen
	public void validateFlightsDepartureTimeOnResultScreen(int flightStartHour, int flightStartMinute, int flightEndHour, int flightEndMinute, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);

			List<String> flightsDepartureData = new ArrayList<>();
			List<WebElement> airlineDepartureCount = driver.findElements(By.className("tg-deptime"));

			if (airlineDepartureCount.size() == 0) {
				Log.ReportEvent("FAIL", "No Flights are Available on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

			LocalTime startTime = LocalTime.of(flightStartHour, flightStartMinute);
			LocalTime endTime = LocalTime.of(flightEndHour, flightEndMinute);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

			for (WebElement airlineDeparture : airlineDepartureCount) {
				Thread.sleep(1000);
				String departureText = airlineDeparture.getText().trim();
				System.out.println(departureText);
				flightsDepartureData.add(departureText);
				LocalTime timeToCheck = LocalTime.parse(departureText, formatter);

				if (flightStartHour != 18) {
					if (timeToCheck.isBefore(startTime) || !timeToCheck.isBefore(endTime)) {
						Log.ReportEvent("FAIL", "Flight at " + timeToCheck + " is outside user selected range: " + startTime + " to " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail("Invalid flight time: " + timeToCheck);
					}
				} else {
					// For evening time range (e.g., 18:00 to 23:59)
					if (timeToCheck.isBefore(startTime)) {
						Log.ReportEvent("FAIL", "Flight at " + timeToCheck + " is before the evening start time: " + startTime);
						ScreenShots.takeScreenShot1();
						Assert.fail("Invalid evening flight time: " + timeToCheck);
					}
				}
			}

			// If loop completes without failure, log PASS
			Log.ReportEvent("PASS", "All flights displayed are within the user selected range: " + startTime + " to " + endTime);
			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception while validating flight times: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Method to Validate Stops is Selected on Result Screen
	public void validateStopsSelected(Log Log, ScreenShots ScreenShots, String... stops) {
		try {
			for (String stop : stops) {
				Thread.sleep(3000);
				WebElement selected = driver.findElement(By.xpath("//*[contains(@class,'filter-stops')]//button[text()='" + stop + "' and contains(@class,'selected-filter')]"));
				if (selected.isDisplayed()) {
					Log.ReportEvent("PASS", "" + stop + " " + "Stop are Selected on Result Screen");
				} else {
					Log.ReportEvent("FAIL", "" + stop + " " + "Stop are Not Selected on Result Screen");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			}
			ScreenShots.takeScreenShot1();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Stop are Not Selected on Result Screen");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to Validate Stops is Not Selected on Result Screen
	public void validateStopsNotSelected(Log Log, ScreenShots ScreenShots, String... stops) {
		try {
			for (String stop : stops) {
				Thread.sleep(3000);
				WebElement selected = driver.findElement(By.xpath("//div[@class='filter-stops']//button[text()='" + stop + "' and not(contains(@class,'selected-filter'))]"));
				if (selected.isDisplayed()) {
					Log.ReportEvent("PASS", "" + stop + " " + "Stop are Not Selected on Result Screen");
				} else {
					Log.ReportEvent("FAIL", "" + stop + " " + "Stop are Selected on Result Screen");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			}
			ScreenShots.takeScreenShot1();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Stop are Selected on Result Screen");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}

	//Method to Validate Departure Time is Selected on Result Screen
	public void validateDepartureTimeIsSelected(Log Log, ScreenShots ScreenShots, String... departureTimes) {
		try {
			for (String departureTime : departureTimes) {
				Thread.sleep(3000);
				WebElement selected = driver.findElement(By.xpath("//legend[text()='ONWARD DEPART TIME']/parent::div//small[text()='" + departureTime + "']/parent::div[@class='selected-filter']"));
				if (selected.isDisplayed()) {
					Log.ReportEvent("PASS", "" + departureTime + " " + "Departure Time is Selected on Result Screen");
				} else {
					Log.ReportEvent("FAIL", "" + departureTime + " " + "Departure Time is Not Selected on Result Screen");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			}
			ScreenShots.takeScreenShot1();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Departure Time is Not Selected on Result Screen");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}

	//Method to Validate Departure Time is Not Selected on Result Screen
	public void validateDepartureTimeIsNotSelected(Log Log, ScreenShots ScreenShots, String... departureTimes) {
		try {
			for (String departureTime : departureTimes) {
				Thread.sleep(3000);
				WebElement selected = driver.findElement(By.className("tg-dep-time-12 false"));
				if (selected.isDisplayed()) {
					Log.ReportEvent("PASS", "" + departureTime + " " + "Departure Time is Not Selected on Result Screen");
				} else {
					Log.ReportEvent("FAIL", "" + departureTime + " " + "Departure Time is Selected on Result Screen");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			}
			ScreenShots.takeScreenShot1();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Departure Time is Selected on Result Screen");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}


	//Method To Adjust Minimum Slider Value on Slide Bar(Low to High Price)
	public double[] adjustMinimumSliderValue(WebDriver driver, double targetValue) throws InterruptedException {
		// Wait for the slider to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate the first thumb input element (Thumb 0)
		WebElement sliderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-index='0']//input[@type='range']")));

		Thread.sleep(3000);

		// Get the minimum and maximum values from the slider's input element
		double minValue = Double.parseDouble(sliderInput.getAttribute("aria-valuemin"));
		double maxValue = Double.parseDouble(sliderInput.getAttribute("aria-valuemax"));

		// Print min and max values for debugging
		System.out.println("Min Value: " + minValue);
		System.out.println("Max Value: " + maxValue);

		// Calculate the percentage for the target value
		double percentage = Math.max(0, Math.min(1, (targetValue - minValue) / (maxValue - minValue)));

		// Print the target value and calculated percentage
		System.out.println("Target Value: " + targetValue);
		System.out.println("Calculated Percentage: " + percentage);

		// Find the slider's track (the element that shows the background of the slider)
		WebElement sliderTrack = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-track')]"));
		int trackWidth = sliderTrack.getSize().getWidth();  // Width of the slider track

		// Print track width for debugging
		System.out.println("Track Width: " + trackWidth);

		// Calculate the target offset based on the percentage
		int targetOffset = (int) (trackWidth * percentage);

		// Print the calculated target offset
		System.out.println("Target Offset: " + targetOffset);

		// Get the current position of the first thumb (we can get this by finding the thumb's current position in percentage)
		WebElement thumb = driver.findElement(By.xpath("//span[@data-index='0']//input[@type='range']"));
		int currentOffset = thumb.getLocation().getX();  // Current thumb position

		// Print the current offset for debugging
		System.out.println("Current Offset: " + currentOffset);

		// Calculate the difference between the target offset and the current offset
		int offsetDifference = targetOffset - currentOffset;

		// Print the offset difference for debugging
		System.out.println("Offset Difference: " + offsetDifference);

		// Move the first slider thumb to the target position
		new Actions(driver).dragAndDropBy(thumb, offsetDifference, 0).perform();

		// Print the result for debugging purposes
		System.out.println("First Slider moved to: " + targetValue);
		return new double[]{minValue, maxValue};
	}

	//Method to Slide SlideBar from Left to Right
	public double[] adjustMinimumSliderValueByPercentage(WebDriver driver, double percentageValue) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate the first thumb input element (Thumb 0)
		WebElement sliderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-index='0']//input[@type='range']")));

		Thread.sleep(2000);

		// Get the min and max values
		double minValue = Double.parseDouble(sliderInput.getAttribute("aria-valuemin"));
		double maxValue = Double.parseDouble(sliderInput.getAttribute("aria-valuemax"));

		// Calculate target value based on percentage
		double targetValue = minValue + ((maxValue - minValue) * percentageValue / 100.0);

		// Clamp target value within range
		targetValue = Math.max(minValue, Math.min(maxValue, targetValue));

		System.out.println("Min Value: " + minValue);
		System.out.println("Max Value: " + maxValue);
		System.out.println("Percentage Passed: " + percentageValue);
		System.out.println("Calculated Target Value: " + targetValue);

		// Calculate target offset percentage (normalized)
		double targetPercentage = (targetValue - minValue) / (maxValue - minValue);

		// Get slider track width
		WebElement sliderTrack = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-track')]"));
		int trackWidth = sliderTrack.getSize().getWidth();
		System.out.println("Slider Track Width: " + trackWidth);

		// Calculate offset in pixels
		int targetOffset = (int) (trackWidth * targetPercentage);
		System.out.println("Target Offset (pixels): " + targetOffset);

		// Get current thumb
		WebElement thumb = driver.findElement(By.xpath("//span[@data-index='0']//input[@type='range']"));
		int currentOffset = thumb.getLocation().getX();
		System.out.println("Current Offset (pixels): " + currentOffset);

		int offsetDifference = targetOffset - currentOffset;
		System.out.println("Offset Difference: " + offsetDifference);

		// Move slider
		new Actions(driver).dragAndDropBy(thumb, offsetDifference, 0).perform();

		System.out.println("Slider moved to value corresponding to " + percentageValue + "%");

		return new double[]{minValue, maxValue};
	}


	//Method To Adjust Maximum Slider Value on Slide Bar(High to Low Price)
	public double[] adjustMaximumSliderValue(WebDriver driver, double targetValue) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate the second thumb input (data-index='1')
		WebElement sliderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@data-index='1']//input[@type='range']")));

		Thread.sleep(1000);

		double minValue = Double.parseDouble(sliderInput.getAttribute("aria-valuemin"));
		double maxValue = Double.parseDouble(sliderInput.getAttribute("aria-valuemax"));

		System.out.println("Min: " + minValue + ", Max: " + maxValue);

		// Calculate percentage for target value within range
		double percentage = Math.max(0, Math.min(1, (targetValue - minValue) / (maxValue - minValue)));
		System.out.println("Target Value: " + targetValue + ", Percentage: " + percentage);

		// Get slider track position and width
		WebElement sliderTrack = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-track')]"));
		Point trackLocation = sliderTrack.getLocation();
		int trackStartX = trackLocation.getX();
		int trackWidth = sliderTrack.getSize().getWidth();

		// Target X position based on percentage of track
		int targetX = (int) (trackStartX + (percentage * trackWidth));

		// Get current thumb (second) position
		WebElement thumb = driver.findElement(By.xpath("//span[@data-index='1']//input[@type='range']"));
		Point thumbLocation = thumb.getLocation();
		int thumbX = thumbLocation.getX();

		// Offset = target - current
		int moveBy = targetX - thumbX;

		System.out.println("Moving Thumb 2 by offset: " + moveBy);

		// Move the second thumb
		Actions actions = new Actions(driver);
		actions.clickAndHold(thumb).moveByOffset(moveBy, 0).release().perform();

		System.out.println("Thumb 2 moved to target value: " + targetValue);
		return new double[]{minValue, maxValue};
	}

	// Method to adjust the maximum slider value by percentage from right (High to Low Price)
	public double[] adjustMaximumSliderValueByPercentage(WebDriver driver, double percentageValue) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate the second thumb (Thumb 1)
		WebElement sliderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@data-index='1']//input[@type='range']")));

		Thread.sleep(1000);

		double minValue = Double.parseDouble(sliderInput.getAttribute("aria-valuemin"));
		double maxValue = Double.parseDouble(sliderInput.getAttribute("aria-valuemax"));

		// Calculate the target value by reducing the given percentage from max
		double targetValue = maxValue - ((maxValue - minValue) * percentageValue / 100.0);
		targetValue = Math.max(minValue, Math.min(maxValue, targetValue));

		System.out.println("Min: " + minValue + ", Max: " + maxValue);
		System.out.println("Percentage to reduce: " + percentageValue + "%, Target Value: " + targetValue);

		// Calculate percentage position of the targetValue on slider
		double targetPercentage = (targetValue - minValue) / (maxValue - minValue);

		// Get slider track position and width
		WebElement sliderTrack = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-track')]"));
		Point trackLocation = sliderTrack.getLocation();
		int trackStartX = trackLocation.getX();
		int trackWidth = sliderTrack.getSize().getWidth();

		// Calculate the target X coordinate
		int targetX = (int) (trackStartX + (targetPercentage * trackWidth));

		// Get current thumb (Thumb 1) position
		WebElement thumb = driver.findElement(By.xpath("//span[@data-index='1']//input[@type='range']"));
		Point thumbLocation = thumb.getLocation();
		int thumbX = thumbLocation.getX();

		// Calculate movement required
		int moveBy = targetX - thumbX;

		System.out.println("Moving Thumb 1 (max) by offset: " + moveBy);

		// Perform the move
		Actions actions = new Actions(driver);
		actions.clickAndHold(thumb).moveByOffset(moveBy, 0).release().perform();

		System.out.println("Thumb 1 moved to value corresponding to " + percentageValue + "% from max");

		return new double[]{minValue, maxValue};
	}


	//Method to validate Check In Baggage Functionality
	public void validateCheckInBaggageFlightsOnResultScreen(Log Log, ScreenShots ScreenShots) {
		try {
			List<WebElement> checkInBaggage = driver.findElements(By.xpath("//span[text()='Check-in baggage:']//strong"));
			for (WebElement element : checkInBaggage) {
				String checkInBaggageText = element.getText();
				if (checkInBaggageText.contains("0 PC") || checkInBaggageText.contains("0 KG")) {
					System.out.println("Fail,Check-in baggage is not available");

				} else {
					System.out.println("Pass,Check-in baggage is available");
				}
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Check In Baggage flights are Not displayed on Result Screen");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method validate Default And Latest Price Range Values On SlideBar
	public void validateDefaultAndLatestPriceRangeValuesOnSlideBar(Log Log, ScreenShots ScreenShots, String minValue, String maxValue, String newMinValue, String newMaxValue) {
		try {
			if (minValue.equals(newMinValue) && maxValue.equals(newMaxValue)) {
				Log.ReportEvent("PASS", " Default price range and Latest price range is similar after clearing filter.");
			} else {
				Log.ReportEvent("FAIL", " Default price range changed after applying 'Clear Filter'. "
						+ "Expected Min: " + minValue + ", Actual Min: " + newMinValue + " | "
						+ "Expected Max: " + maxValue + ", Actual Max: " + newMaxValue);
				Assert.fail();

			}

			ScreenShots.takeScreenShot1();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", " Exception during validation: Default price and Latest price  " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to get Default Min And Max Value
	public String[] getDefaultMinAndMaxValue() {
		String minPriceValue = driver.findElement(By.xpath("//input[@data-index='1']")).getAttribute("aria-valuemin");
		String maxPriceValue = driver.findElement(By.xpath("//input[@data-index='1']")).getAttribute("aria-valuemax");
		return new String[]{minPriceValue, maxPriceValue};

	}

	//Method to Select Airlines on Result Screen
	public void clickAirlineCheckboxes(String... airlineNames) {
		for (String airlineName : airlineNames) {
			try {
				WebElement airline = driver.findElement(By.xpath("//*[text()='AIRLINES']"));
				Actions move = new Actions(driver);
				move.moveToElement(airline).perform();
				Thread.sleep(1000);
				String name=airlineName.trim();
				driver.findElement(By.xpath("//*[normalize-space(text())='"+name+"']/parent::div/parent::li//input")).click();
				System.out.println("Clicked checkbox for: " + airlineName);
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Could not find/click checkbox for: " + airlineName);
				e.printStackTrace();
				Assert.fail();

			}
		}

	}

	//Method to Validate Airline List on Result Screen
	public void validateAirLinesListDisplayedBasedOnUserSearch(Log Log, ScreenShots ScreenShots, String... airlineNames) {
		try {
			// Get all airlines displayed on the result page
			List<WebElement> airlinesList = driver.findElements(By.className("tg-flightcarrier"));
			List<String> displayedAirlines = new ArrayList<>();
			for (WebElement airlines : airlinesList) {
				String airline = airlines.getText().trim();
				displayedAirlines.add(airline);
			}

			// Flag to track overall result
			boolean allMatch = true;

			// Check each airline passed to the method
			for (String expectedAirline : airlineNames) {
				if (displayedAirlines.contains(expectedAirline.trim())) {
					Log.ReportEvent("PASS", "Expected airline is showing: " + expectedAirline);
				} else {
					Log.ReportEvent("FAIL", "Expected airline NOT found: " + expectedAirline);
					allMatch = false;
				}
			}

			if (allMatch) {
				Log.ReportEvent("PASS", "All Selected Airlines are Correctly Shown in the Results Screen.");
			} else {
				Log.ReportEvent("FAIL", "All Selected Airlines are Not Shown in the Results Screen.");
				Assert.fail();

			}
			ScreenShots.takeScreenShot1();
			// Print all displayed airlines
			System.out.println("Displayed Airlines:");
			displayedAirlines.forEach(System.out::println);

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception Occurred While Validating Airlines List: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to Validate Airlines are UnSelected on Result Screen
	public void validateAirlinesAreUnSelectedOnResultScreen(Log Log, ScreenShots ScreenShots, String... airlineNames) {
		try {
			Thread.sleep(2000);
			for (String airline : airlineNames) {
				Thread.sleep(500);
				String names=airline.trim();
				boolean value = driver.findElement(By.xpath("//*[normalize-space(text())='" + names + "']/parent::div//parent::li//*[not(contains(@class,'Mui-checked')) and contains(@class,'MuiTouchRipple-root')]")).isDisplayed();
				if (value == true) {
					Log.ReportEvent("PASS", "All selected airlines are Unselected successfully");
					ScreenShots.takeScreenShot1();
				} else {
					Log.ReportEvent("FAIL", "All selected airlines are Not Unselected successfully");
					Assert.fail();
					ScreenShots.takeScreenShot1();
				}
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "All selected airlines are Not Unselected successfully" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to Validate Airlines are Selected on Result Screen
	public void validateAirlinesAreSelectedOnResultScreen(Log Log, ScreenShots ScreenShots, String... airlineNames) {
		try {
			Thread.sleep(2000);
			for (String airline : airlineNames) {
				Thread.sleep(500);
				boolean value = driver.findElement(By.xpath("//span[normalize-space(text())='" + airline + "']/parent::div//parent::li//span[contains(@class,'Mui-checked')]")).isDisplayed();
				if (value == true) {
					Log.ReportEvent("PASS", "Required Airlines are Selected successfully");
				} else {
					Log.ReportEvent("FAIL", "Required Airlines are Not Selected successfully");
					Assert.fail();

				}
			}
			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Required Airlines are Not Selected successfully" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to Validate No Flights are Displayed on Result Screen
	public void validateNoFlightsAreDisplayedOnResultScreen(Log Log, ScreenShots ScreenShots) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='AirplaneIcon']")));
			boolean value = driver.findElement(By.xpath("//*[@data-testid='AirplaneIcon']")).isDisplayed();
			if (value = true) {
				Log.ReportEvent("PASS", "No Airlines are Dispayed Please Modify the Search");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Airlines are Dispayed");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Airlines are Dispayed" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to validate Policy Filter
	public void validatePolicyFilter(Log Log, ScreenShots ScreenShots) {
		try {
			String policy = driver.findElement(By.xpath("(//div[@class='mb-60']//div[text()='In Policy'])[1]")).getText();
			if (policy.equals("In Policy")) {
				Log.ReportEvent("PASS", "In Policy Flights are displayed");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "In Policy Flights are Not displayed");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}

			List<WebElement> inPolicyFlights = driver.findElements(By.xpath("//div[@class='mb-60']//div[text()='In Policy']"));

			for (WebElement policyFlights : inPolicyFlights) {
				String values = policyFlights.getText();
				System.out.println(values);
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception while checking In Policy Flights: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}

	//Method to Get Text from Currency DropDown
	public String getTextFromCurrencyDropDown() throws InterruptedException {
		Thread.sleep(2000);
		String selectedDropDownValue = driver.findElement(By.xpath("//label[text()='Currency']/parent::div//div[contains(@class,'tg-select undefined__single-value')]")).getText();
		return selectedDropDownValue;

	}

	//Method to Validate Selected Currency is Displayed on Result Screen
	public void validateCurrencyOnResultScreen(String currencyValue, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(6000);
			List<WebElement> currencyTexts = driver.findElements(By.className("tg-other-price"));
			for (WebElement currencyText : currencyTexts) {
				System.out.println(currencyText.getText());
				currencyText.getText().contains(currencyValue);
			}
			String currencyData = currencyTexts.get(0).getText();
			String currencyCode = currencyData.substring(0, 3);
			System.out.println(currencyCode);
			if (currencyValue.contentEquals(currencyCode)) {
				Log.ReportEvent("PASS", "Currencies are Displayed Based on User Search " + "" + currencyCode + " " + "is Successful");
				ScreenShots.takeScreenShot1();

			} else {
				Log.ReportEvent("FAIL", "Currencies are Not Displayed Based on User" + " " + currencyCode);
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Currencies are Not Displayed Based on User Search" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to get the Price From Continue Popup
	public String getPriceValueFromContinuePopup() throws InterruptedException {
		if (driver.findElement(By.xpath("//div[text()='INR']")).isDisplayed()) {
			Thread.sleep(2000);
			String priceValue = driver.findElement(By.xpath("//h6[@data-tgfullfare]")).getText();
			return priceValue;

		} else {
			String priceValue = driver.findElement(By.xpath("//h6[@data-tgfullfare]")).getText();
			String cleanedText = priceValue.replaceAll("\\s*\\(.*?\\)", "");
			return cleanedText;
		}

	}

	//Method to get Other Country Price Value
	public String getOtherCountryPriceValue() {
		String priceValue = driver.findElement(By.xpath("(//span[contains(@class,'other-currency-price')])[1]")).getText();
		System.out.println(priceValue);
		return priceValue;
	}

	//div[@data-tgflighttodetails]/following-sibling::div//button[text()='Continue']
	//Method to Click on Continue Button on Continue Flight Booking Popup
	public void clickOnContinueBookingFlightPopup() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='bottom-container-1']//button[text()='Continue']")).click();
		Thread.sleep(1000);
	}

	//Method to Click on Continue Button on Continue Flight Booking Popup
	public void clickOnYesContinueOnContinuePopup() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Yes, Continue']")).click();
		Thread.sleep(3000);
	}

	//Method to Validate Flights Image and Name is Displayed
	public void validateFlightImageAndNameIsDisplayed() {
		driver.findElement(By.className("tg-flightimg")).isDisplayed();
	}

	//Method to Validate Flights Departure Time is Displayed
	public void validateFlightDepartureTimeIsDisplayed() {
		driver.findElement(By.className("tg-deptime")).isDisplayed();
	}

	//Method to Validate Flights Duration Time is Displayed
	public void validateFlightDurationTimeIsDisplayed() {
		driver.findElement(By.className("tg-totalduration")).isDisplayed();
	}

	//Method to Validate Flights Arrival Time is Displayed
	public void validateFlightArrivalTimeIsDisplayed() {
		driver.findElement(By.className("tg-arrtime")).isDisplayed();
	}

	//Method to Validate Flights Price is Displayed
	public void validateFlightPriceIsDisplayed() {
		driver.findElement(By.className("tg-price")).isDisplayed();
	}

	//Method to Validate Flights Footer Details is Displayed
	public void validateFlightFooterDetailsIsDisplayed() {
		driver.findElement(By.className("owf-amenities")).isDisplayed();
	}

	// Method to get FlightCount
	public void getFlightCount(Log Log, ScreenShots ScreenShots) {
		try {
			String flightCount = driver.findElement(By.className("tg-flCount")).getText();
			Log.ReportEvent("PASS", "Total Flights Count \"" + flightCount + "\"");
			ScreenShots.takeScreenShot();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Error Occurred While getting Flight Count: " + e.getMessage());
			ScreenShots.takeScreenShot();
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void getFlightCountForDomestic(Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(2000);
			String flightCount = driver.findElement(By.id("onward_flight_count")).getText();
			String returnFlightCount = driver.findElement(By.id("return_flight_count")).getText();
			Log.ReportEvent("PASS", "Total Onward Departing Flights Count \"" + flightCount + "\"");
			Log.ReportEvent("PASS", "Total Return Departing Flights Count \"" + returnFlightCount + "\"");

			ScreenShots.takeScreenShot();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Error Occurred While getting Flight Count: " + e.getMessage());
			ScreenShots.takeScreenShot();
			e.printStackTrace();
			Assert.fail();
		}
	}


	//Method to Wait until flight details had displayed.
	public void waitTillFlightDetailsLoaded() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

			// First, wait for either flights or "no flights" icon to appear
			wait.until(driver ->
					driver.findElements(By.xpath("(//*[contains(@class,'tg-flightcarrier')])[1]")).size() > 0 ||
							driver.findElements(By.xpath("//*[@data-testid='AirplaneIcon']")).size() > 0
			);

			// Check if the "no flights" icon is displayed
			if (!driver.findElements(By.xpath("//*[@data-testid='AirplaneIcon']")).isEmpty()) {
				throw new RuntimeException("No flights found. Failing the script.");
			}

			System.out.println("Flights loaded successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed while waiting for flight details.", e);
		}
	}

	//Method to Validate Flight Details on Result Screen
	public void validateFlightDetailsOnResultScreen(Log Log, ScreenShots ScreenShots) {
		try {
			TestExecutionNotifier.showExecutionPopup();
			validateFlightImageAndNameIsDisplayed();
			validateFlightDepartureTimeIsDisplayed();
			validateFlightDurationTimeIsDisplayed();
			validateFlightArrivalTimeIsDisplayed();
			validateFlightPriceIsDisplayed();
			validateFlightFooterDetailsIsDisplayed();
			Log.ReportEvent("PASS", "Flights Basic Details are Displayed on Result Screen");
			ScreenShots.takeScreenShot();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights Basic Details are Not Displayed on Result Screen" + e.getMessage());
			ScreenShots.takeScreenShot();
			e.printStackTrace();
			Assert.fail();

		}
	}


	public void moveSlider(String direction, int valueToScroll) {
		WebElement slider = driver.findElement(By.cssSelector("input[data-index='0']")); // Left thumb

		int min = Integer.parseInt(slider.getAttribute("min"));
		int max = Integer.parseInt(slider.getAttribute("max"));
		int currentVal = Integer.parseInt(slider.getAttribute("value"));

		// Determine target value
		int targetValue = direction.equalsIgnoreCase("minimum")
				? Math.min(currentVal + valueToScroll, max)
				: Math.max(currentVal - valueToScroll, min);

		if (targetValue == currentVal) {
			return; // No movement needed
		}

		// Locate slider track and get width
		WebElement sliderTrack = driver.findElement(By.className("MuiSlider-track"));
		int sliderWidth = sliderTrack.getSize().getWidth();

		// Calculate offset in pixels
		double valueRange = max - min;
		double delta = targetValue - currentVal;
		double valueRatio = delta / valueRange;
		int xOffset = (int) Math.round(valueRatio * sliderWidth);

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider);

		// Drag the slider
		Actions move = new Actions(driver);
		move.clickAndHold(slider)
				.moveByOffset(xOffset, 0)
				.release()
				.perform();
	}

	public void slideLeft(int valueToScroll) {
		WebElement slider = driver.findElement(By.cssSelector("input[data-index='1']"));

		int min = Integer.parseInt(slider.getAttribute("min"));
		int max = Integer.parseInt(slider.getAttribute("max"));
		int currentVal = Integer.parseInt(slider.getAttribute("value"));

		int targetValue = Math.max(currentVal - valueToScroll, min);
		if (targetValue == currentVal) return;

		WebElement sliderTrack = driver.findElement(By.className("MuiSlider-track"));
		int sliderWidth = sliderTrack.getSize().getWidth();

		double valueRange = max - min;
		double delta = targetValue - currentVal;
		double ratio = delta / valueRange;
		int xOffset = (int) Math.round(ratio * sliderWidth);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider);

		Actions move = new Actions(driver);
		move.clickAndHold(slider)
				.moveByOffset(xOffset, 0)
				.release()
				.perform();
	}

	//Method to Click on Airline Filter on Result Screen
	public void clickOnAirlineFilterOnResultScreen() throws InterruptedException {
		driver.findElement(By.className("tg-sort-carrier")).click();
		Thread.sleep(3000);
	}

	//Method to Click on Departing time Filter on Result Screen
	public void clickOnDepartingTimeFilterOnResultScreen() throws InterruptedException {
		driver.findElement(By.className("tg-sort-deptime")).click();
		Thread.sleep(3000);
	}

	//Method to Click on Duration Filter on Result Screen
	public void clickOnDurationFilterOnResultScreen() throws InterruptedException {
		driver.findElement(By.className("tg-sort-duration")).click();
		Thread.sleep(3000);
	}

	//Method to Click on Arrival Time Filter on Result Screen
	public void clickOnArrivalTimeFilterOnResultScreen() throws InterruptedException {
		driver.findElement(By.className("tg-sort-arrtime")).click();
		Thread.sleep(3000);
	}

	//Method to Click on Price Filter on Result Screen
	public void clickOnPriceFilterOnResultScreen() throws InterruptedException {
		driver.findElement(By.className("tg-sort-price")).click();
		Thread.sleep(3000);
	}

	//Method to Check Airline Sort Functionality on Result Screen
	public void validateAirlineSortFunctionalityOnResultScreen(Log Log, ScreenShots ScreenShots, String order) {
		try {
			Thread.sleep(2000);
			ArrayList<String> flightsAirlineData = new ArrayList();
			List<WebElement> airlineArrivalCount = driver.findElements(By.className("tg-flightcarrier"));
			System.out.println(airlineArrivalCount.size());
			for (WebElement airLineNames : airlineArrivalCount) {
				String airlineText = airLineNames.getText();
				flightsAirlineData.add(airlineText);
				System.out.println(airlineText);
			}

			boolean isSorted = isSortedAlphabeticallyAirlines(flightsAirlineData, order);
			System.out.println("Is the list sorted in ascending order? " + isSorted);
			if (isSorted == true) {
				Log.ReportEvent("PASS", "Airlines are Sorted in Order is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Airlines are Not Sorted in order is Successful");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Airlines are Not Sorted in order is Successful");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}


	//Method to Check Airlines Sorted in Ascending or Descending
	public static boolean isSortedAlphabeticallyAirlines(ArrayList<String> list, String order) {
		for (int i = 0; i < list.size() - 1; i++) {
			String airlineText1 = extractFirstWord(list.get(i));
			String airlineText2 = extractFirstWord(list.get(i + 1));
			if (order.contentEquals("Ascending")) {
				if (airlineText1.compareToIgnoreCase(airlineText2) > 0) {
					return false;  // not sorted
				}
			} else if (order.contentEquals("Descending")) {
				if (airlineText1.compareToIgnoreCase(airlineText2) < 0) {
					return false;  // not sorted
				}
			}
		}
		return true;
	}

	// Extracts the first word before a space or parenthesis
	public static String extractFirstWord(String str) {
		str = str.trim();
		int endIndex = str.indexOf(' ');
		if (endIndex == -1 || str.indexOf('(') < endIndex && str.indexOf('(') != -1) {
			endIndex = str.indexOf('(');
		}
		return (endIndex != -1) ? str.substring(0, endIndex).trim() : str;
	}


	//Method to Check Departure Time is Sorting In Result Screen
	public void validateDepartureTimeSortFunctionalityOnResultScreen(Log Log, ScreenShots ScreenShots, String order) {
		try {
			ArrayList<String> flightsDepartureData = new ArrayList();
			List<WebElement> airlineDepartureCount = driver.findElements(By.className("tg-deptime"));
			System.out.println(airlineDepartureCount.size());
			for (WebElement airlineDepartureTime : airlineDepartureCount) {
				String airlineDepartureText = airlineDepartureTime.getText();
				flightsDepartureData.add(airlineDepartureText);
				System.out.println(airlineDepartureText);
			}
			System.out.println(flightsDepartureData);

			// Convert time strings to minutes
			List<Integer> timesInMinutes = new ArrayList<>();
			for (String time : flightsDepartureData) {
				String[] parts = time.split(":");
				int minutes = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
				timesInMinutes.add(minutes);
			}
			System.out.println(timesInMinutes);

			// Check if times are in ascending order
			boolean isDepartureSorted = true;
			for (int i = 0; i < timesInMinutes.size() - 1; i++) {
				if (order.contentEquals("Ascending")) {
					if (timesInMinutes.get(i) < timesInMinutes.get(i + 1)) {
						isDepartureSorted = false;
						break;
					}
				} else if (order.contentEquals("Descending")) {
					if (timesInMinutes.get(i) > timesInMinutes.get(i + 1)) {
						isDepartureSorted = false;
						break;
					}
				}

			}
			if (isDepartureSorted = true) {
				Log.ReportEvent("PASS", "Flights Departure Time is Sorted Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("PASS", "Flights Departure Time is Not Sorted");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.ReportEvent("FAIL", "Flights Departure Time is Not Sorted");
			ScreenShots.takeScreenShot1();
			Assert.fail();
		}
	}


	//Method to Check Duration Time is Sorting In Result Screen
	public void validateDurationTimeSortFunctionalityOnResultScreen(Log Log, ScreenShots ScreenShots, String order) {
		try {
			ArrayList<String> flightsDurationData = new ArrayList();
			List<WebElement> airlineDurationCount = driver.findElements(By.className("tg-totalduration"));
			System.out.println(airlineDurationCount.size());

			for (WebElement airlineDurationList : airlineDurationCount) {
				String airlineDurationText = airlineDurationList.getText();
				System.out.println(airlineDurationText);
				flightsDurationData.add(airlineDurationText);
			}
			System.out.println(flightsDurationData);

			// Convert duration strings like "1h 0m" to total minutes
			List<Integer> timesInMinutes = new ArrayList<>();
			for (String duration : flightsDurationData) {
				int totalMinutes = 0;
				if (duration.contains("h")) {
					String[] parts = duration.split("h");
					totalMinutes += Integer.parseInt(parts[0].trim()) * 60;
					if (parts.length > 1 && parts[1].contains("m")) {
						totalMinutes += Integer.parseInt(parts[1].replace("m", "").trim());
					}
				} else if (duration.contains("m")) {
					totalMinutes += Integer.parseInt(duration.replace("m", "").trim());
				}
				timesInMinutes.add(totalMinutes);
				System.out.println("Converted to minutes: " + totalMinutes);
			}

			System.out.println(timesInMinutes);


			// Check if times are in ascending order
			boolean isDurationSorted = true;
			for (int i = 0; i < timesInMinutes.size() - 1; i++) {
				if (order.contentEquals("Ascending")) {
					if (timesInMinutes.get(i) < timesInMinutes.get(i + 1)) {
						isDurationSorted = false;
						break;
					}
				} else if (order.contentEquals("Descending")) {
					if (timesInMinutes.get(i) > timesInMinutes.get(i + 1)) {
						isDurationSorted = false;
						break;
					}
				}

			}
			if (isDurationSorted = true) {
				Log.ReportEvent("PASS", "Flights Duration Time is Sorted Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("PASS", "Flights Duration Time is Not Sorted");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.ReportEvent("FAIL", "Flights Duration Time is Not Sorted");
			ScreenShots.takeScreenShot1();
			Assert.fail();
		}
	}


	//Method to Check Arrival Time is Sorting In Result Screen
	public void validateArrivalTimeSortFunctionalityOnResultScreen(Log Log, ScreenShots ScreenShots, String order) {
		try {
			ArrayList<String> flightsArrivalData = new ArrayList();
			List<WebElement> airlineArrivalCount = driver.findElements(By.className("tg-arrtime"));
			for (WebElement airlineArrivalList : airlineArrivalCount) {
				String airlineArrivalText = airlineArrivalList.getText();
				System.out.println(airlineArrivalText);
				flightsArrivalData.add(airlineArrivalText);
			}
			System.out.println(flightsArrivalData);

			// Convert time strings to minutes
			List<Integer> timesInMinutes = new ArrayList<>();
			for (String time : flightsArrivalData) {
				String[] parts = time.split(":");
				int minutes = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
				timesInMinutes.add(minutes);
			}

			System.out.println(timesInMinutes);


			// Check if times are in ascending order
			boolean isArrivalSorted = true;
			for (int i = 0; i < timesInMinutes.size() - 1; i++) {
				if (order.contentEquals("Ascending")) {
					if (timesInMinutes.get(i) < timesInMinutes.get(i + 1)) {
						isArrivalSorted = false;
						break;
					}
				} else if (order.contentEquals("Descending")) {
					if (timesInMinutes.get(i) > timesInMinutes.get(i + 1)) {
						isArrivalSorted = false;
						break;
					}
				}

			}
			if (isArrivalSorted = true) {
				Log.ReportEvent("PASS", "Flights Arrival Time is Sorted Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("PASS", "Flights Arrival Time is Not Sorted");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.ReportEvent("FAIL", "Flights Arrival Time is Not Sorted");
			ScreenShots.takeScreenShot1();
			Assert.fail();
		}
	}


	//Method to Check Price is Sorting In Result Screen
	public void validatePriceSortFunctionalityOnResultScreen(Log Log, ScreenShots ScreenShots, String order) {
		try {
			ArrayList<Integer> flightsPriceData = new ArrayList();
			List<WebElement> airlinePrices = driver.findElements(By.className("tg-price"));
			for (WebElement airlinePrice : airlinePrices) {
				String price = airlinePrice.getText();
				String priceList = price.substring(2);
				String pricedata = priceList.replace(",", "");
				flightsPriceData.add(Integer.parseInt(pricedata));
			}
			System.out.println(flightsPriceData);

			// Check if times are in ascending order
			boolean isPriceSorted = true;
			for (int i = 0; i < flightsPriceData.size() - 1; i++) {
				if (order.contentEquals("Ascending")) {
					if (flightsPriceData.get(i) > flightsPriceData.get(i + 1)) {
						isPriceSorted = false;
						break;
					}
				} else if (order.contentEquals("Descending")) {
					if (flightsPriceData.get(i) < flightsPriceData.get(i + 1)) {
						isPriceSorted = false;
						break;
					}
				}

			}
			if (isPriceSorted = true) {
				Log.ReportEvent("PASS", "Flights Price is Sorted Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("PASS", "Flights Price is Not Sorted");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.ReportEvent("FAIL", "Flights Price is Not Sorted");
			ScreenShots.takeScreenShot1();
			Assert.fail();
		}
	}

	//Method to Validate Price Range on the Price Filter
	public void verifyPriceRangeValuesOnResultScreen(Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(3000);

			// Get current slider values
			int min = Integer.parseInt(driver.findElement(By.xpath("//input[@data-index='0']")).getAttribute("aria-valuenow"));
			int max = Integer.parseInt(driver.findElement(By.xpath("//input[@data-index='1']")).getAttribute("aria-valuenow"));

			System.out.println("Slider Min: " + min);
			System.out.println("Slider Max: " + max);

			// Get all price elements
			List<WebElement> priceElements = driver.findElements(By.xpath(
					"//*[@data-tgprice]"
			));

			boolean allPricesInRange = true;

			for (WebElement priceElement : priceElements) {
				String rawPrice = priceElement.getText(); // e.g., "₹ 52,354" or "INR 45,678"
				String cleanPrice = rawPrice.replaceAll("[^0-9]", ""); // Keep digits only
				try {
					int price = Integer.parseInt(cleanPrice);
					if (price >= min && price <= max) {
						System.out.println("Price within range: ₹" + price);
					} else {
						System.out.println("❌ Price out of range: ₹" + price);
						allPricesInRange = false;
					}
				} catch (NumberFormatException e) {
					System.out.println("⚠️ Skipping invalid price: " + rawPrice);
					allPricesInRange = false;
				}
			}

			if (allPricesInRange) {
				Log.ReportEvent("PASS", "✅ All flight prices are within the range ₹" + min + " - ₹" + max);
			} else {
				Log.ReportEvent("FAIL", "❌ Some flight prices are outside the range ₹" + min + " - ₹" + max);
				Assert.fail("Some prices are out of range.");
			}

			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception during price validation: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}


	//Method to validate flights Arrival time on result screen
	public void validateFlightsArrivalTimeOnResultScreen(int flightStartHour, int flightStartMinute, int flightEndHour, int flightEndMinute, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);

			List<String> flightsArrivalData = new ArrayList<>();
			List<WebElement> airlineArrivalCount = driver.findElements(By.className("tg-arrtime"));

			if (airlineArrivalCount.size() == 0) {
				Log.ReportEvent("FAIL", "No Flights are Available on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

			LocalTime startTime = LocalTime.of(flightStartHour, flightStartMinute);
			LocalTime endTime = LocalTime.of(flightEndHour, flightEndMinute);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

			for (WebElement airlineArrival : airlineArrivalCount) {
				Thread.sleep(1000);
				String arrivalText = airlineArrival.getText().trim();
				System.out.println(arrivalText);
				flightsArrivalData.add(arrivalText);

				LocalTime timeToCheck = LocalTime.parse(arrivalText, formatter);

				if (flightStartHour != 18) {
					if (timeToCheck.isBefore(startTime) || !timeToCheck.isBefore(endTime)) {
						Log.ReportEvent("FAIL", "Flight at " + timeToCheck + " is outside user selected range: " + startTime + " to " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail("Invalid flight time: " + timeToCheck);
					}
				} else {
					// For evening time range (e.g., 18:00 to 23:59)
					if (timeToCheck.isBefore(startTime)) {
						Log.ReportEvent("FAIL", "Flight at " + timeToCheck + " is before the evening start time: " + startTime);
						ScreenShots.takeScreenShot1();
						Assert.fail("Invalid evening flight time: " + timeToCheck);
					}
				}
			}

			// If loop completes without failure, log PASS
			Log.ReportEvent("PASS", "All flights displayed are within the user selected range: " + startTime + " to " + endTime);
			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception while validating flight times: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	// Method to Get List of Airlines on Result Screen
	public ArrayList<String> getListOfAirlinesOnResultScreen(Log Log) {
		ArrayList<String> names = new ArrayList<>();

		try {
			List<WebElement> airlineList = driver.findElements(By.xpath("//*[contains(@class,'tg-airline-name')]//span"));

			if (airlineList.isEmpty()) {
				Log.ReportEvent("FAIL", "No airline elements found on the result screen");
				System.out.println("No airline elements found");
				Assert.fail("Airline list is empty");
			}

			for (WebElement airlineText : airlineList) {
				String name = airlineText.getText();

				if (name == null || name.trim().isEmpty()) {
					Log.ReportEvent("FAIL", "Airline name is null or empty");
					System.out.println("Getting null or empty value for airline name");
					Assert.fail("Airline name is null or empty");
				}

				names.add(name);
				Log.ReportEvent("INFO", "Airline Name: " + name);
				System.out.println(name);
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception occurred while fetching airline names: " + e.getMessage());
			e.printStackTrace();
			Assert.fail("Exception in getListOfAirlinesOnResultScreen: " + e.getMessage());
		}

		return names;
	}



	//Method to Validate InPolicy Flights
	public void validatePolicy(Log Log, ScreenShots ScreenShots, String expectedValue) {
		try {
			// Retrieve all policy element
			List<WebElement> policies = driver.findElements(By.xpath("//div[@data-tginploicy]"));
			if (policies.size() > 0) {
				// Iterate through each policy element
				for (WebElement policy : policies) {
					String policyText = policy.getText().trim();
					System.out.println("Policy Text: " + policyText);
					ValidateActualAndExpectedValuesForFlights(policyText, expectedValue, "Flights displaying inPolicy text", Log);
				}
				// Optional: Print each policy text separately to console
				for (WebElement policy : policies) {
					String policyText = policy.getText().trim();
					System.out.println("Policy: " + policyText);
				}
				// Take a screenshot for documentation
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "❌Policy Details are Mismatching");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
		} catch (Exception e) {
			// Handle any exceptions that occur during the validation
			Log.ReportEvent("FAIL", "❌ Exception while checking policies: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}


	//Method to Validate OutOff Policy Flights
	public void validateOutOffPolicyFlights(Log Log, ScreenShots ScreenShots, String expectedValue) {
		try {
			// Retrieve all policy element
			List<WebElement> policies = driver.findElements(By.xpath("//div[@data-tgundefinedoutpolicy]"));
			if (policies.size() > 0) {
				// Iterate through each policy element
				for (WebElement policy : policies) {
					String policyText = policy.getText().trim();
					System.out.println("Policy Text: " + policyText);
					ValidateActualAndExpectedValuesForFlights(policyText, expectedValue, "Flights displaying OutOffPolicy text", Log);
				}
				// Optional: Print each policy text separately to console
				for (WebElement policy : policies) {
					String policyText = policy.getText().trim();
					System.out.println("Policy: " + policyText);
				}
				// Take a screenshot for documentation
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "❌ Policy Details are Mismatching");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}


		} catch (Exception e) {
			// Handle any exceptions that occur during the validation
			Log.ReportEvent("FAIL", "❌ Exception while checking policies: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Method to Validate OutOFF Policy Suggestion Messages
	public void validateOutOffPolicySuggestionMessage(Log Log, ScreenShots ScreenShots, String expectedValue) {
		try {
			// Retrieve all policy element
			List<WebElement> policies = driver.findElements(By.xpath("//div[@data-tgundefinedoutpolicy]"));

			if (policies.size() > 0) {
				// Iterate through each policy element
				for (WebElement policyMessage : policies) {
					String policyText = policyMessage.getAttribute("aria-label").trim();
					System.out.println("Policy Message: " + policyText);
					ValidateActualAndExpectedValuesForFlights(policyText, expectedValue, "Flights displaying Policy text message", Log);
				}
				// Take a screenshot for documentation
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "❌ Policy Details are Mismatching");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

		} catch (Exception e) {
			// Handle any exceptions that occur during the validation
			Log.ReportEvent("FAIL", "❌ Exception while checking policies Details Message: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	// Method to validate SME/Corporate Fare
	public void validateSMEOrCorporateFareOnResultScreen(Log log, ScreenShots screenShots) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
			// Get total flights before clicking SME checkbox
			Thread.sleep(3000);
			WebElement flightCountElementBefore = getFlightCountElement();
			Thread.sleep(3000);
			String totalFlightsFoundBefore = flightCountElementBefore.getText();
			System.out.println("Flights before SME filter: " + totalFlightsFoundBefore);
			js.executeScript("arguments[0].scrollIntoView(true);", flightCountElementBefore);
			log.ReportEvent("INFO", "Total Flights Found Before Clicking SME CheckBox: " + totalFlightsFoundBefore);
			screenShots.takeScreenShot1();

			// Click SME filter checkbox
			clickOnSmeAndCorporateFareOnly();
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
			Thread.sleep(2000); // Optional - can remove if no flakiness

			// Get total flights after applying filter
			WebElement flightCountElementAfter = getFlightCountElement();
			String totalFlightsFoundAfter = flightCountElementAfter.getText();
			System.out.println("Flights after SME filter: " + totalFlightsFoundAfter);
			js.executeScript("arguments[0].scrollIntoView(true);", flightCountElementAfter);
			log.ReportEvent("INFO", "Total Flights Found After Clicking SME CheckBox: " + totalFlightsFoundAfter);
			screenShots.takeScreenShot1();

			// Optional: assert flight count changed
			Assert.assertNotEquals(totalFlightsFoundBefore, totalFlightsFoundAfter, "Flight count did not change after applying SME filter.");

			// Select a flight (index 1)
			selectFlightBasedOnIndex(1);

			// Wait for fare element
			WebElement fareElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//h6[@data-tgfaretype]")
			));
			js.executeScript("arguments[0].scrollIntoView(true);", fareElement);
			String fareText = fareElement.getText();
			log.ReportEvent("INFO", "Fare Element Text: " + fareText);

			// Validate fare type
			if (fareText.equalsIgnoreCase("SME fare") || fareText.equalsIgnoreCase("corporate fare")) {
				log.ReportEvent("PASS", "SME/Corporate Fare Only flights are displayed");
				screenShots.takeScreenShot1();
			} else {
				log.ReportEvent("FAIL", "SME/Corporate Fare Only flights are not displayed");
				screenShots.takeScreenShot1();
				Assert.fail("Displayed fare is not SME or Corporate");
			}

		} catch (TimeoutException e) {
			log.ReportEvent("FAIL", "Timeout: The fare element was not visible within the specified time.");
			screenShots.takeScreenShot1();
			Assert.fail("Timeout occurred: " + e.getMessage());
		} catch (NoSuchElementException e) {
			log.ReportEvent("FAIL", "Element not found: " + e.getMessage());
			screenShots.takeScreenShot1();
			Assert.fail("Element not found: " + e.getMessage());
		} catch (InterruptedException e) {
			log.ReportEvent("FAIL", "Thread interrupted: " + e.getMessage());
			screenShots.takeScreenShot1();
			Assert.fail("Interrupted: " + e.getMessage());
		} catch (Exception e) {
			log.ReportEvent("FAIL", "Unexpected error: " + e.getMessage());
			screenShots.takeScreenShot1();
			Assert.fail("Unexpected error: " + e.getMessage());
		}
	}

	// Helper to get flight count element
	private WebElement getFlightCountElement() {
		return driver.findElement(By.xpath("//span[@id='flight_count']"));
	}


	//Method to validate Unselecting SME Fare CheckBox
	public void validateUnSelectingSMEFare(Log log, ScreenShots screenShots) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			// Scroll up to refresh page view
			js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");

			// Get flight count after SME filter applied
			WebElement flightCountElementBefore = wait.until(ExpectedConditions.visibilityOf(getFlightCountElement()));
			String flightsAfterSMEChecked = flightCountElementBefore.getText();
			js.executeScript("arguments[0].scrollIntoView(true);", flightCountElementBefore);
			log.ReportEvent("INFO", "Flights Found After SME Filter Applied: " + flightsAfterSMEChecked);
			screenShots.takeScreenShot1();

			//Function again calling close the details of fights
			js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
			selectFlightBasedOnIndex(1);
			// Uncheck SME checkbox
			clickOnSmeAndCorporateFareOnly();
			Thread.sleep(3000);
			// Wait for checkbox to become clickable and check state
			WebElement smeCheckBox = driver.findElement(By.xpath("//legend[text()='SME / Corporate Fare']//parent::div//input"));


			if (!smeCheckBox.isSelected()) {
				log.ReportEvent("PASS", "SME check box is unselected");
				screenShots.takeScreenShot1();
			} else {
				log.ReportEvent("FAIL", "SME check box is still selected");
				screenShots.takeScreenShot1();
				Assert.fail("Expected SME checkbox to be unselected");
			}
			// Get flight count after SME checkbox is unselected
			js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
			WebElement flightCountElementAfter = wait.until(ExpectedConditions.visibilityOf(getFlightCountElement()));
			String flightsAfterSMEUnchecked = flightCountElementAfter.getText();
			js.executeScript("arguments[0].scrollIntoView(true);", flightCountElementAfter);
			log.ReportEvent("INFO", "Flights Found After SME Filter Removed: " + flightsAfterSMEUnchecked);
			screenShots.takeScreenShot1();

			// Validate flight count changed
			Assert.assertNotEquals(flightsAfterSMEChecked, flightsAfterSMEUnchecked, "Flight count did not change after unchecking SME");

		} catch (Exception e) {
			log.ReportEvent("FAIL", "Exception while validating SME checkbox unselection: " + e.getMessage());
			screenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail("Exception occurred during SME checkbox unselection validation: " + e.getMessage());
		}
	}

	//Method to Click on Swap Button
	public void clickOnSwapButton() throws InterruptedException {
		driver.findElement(By.xpath("//*[@data-testid='SwapHorizIcon']")).click();
		Thread.sleep(800);
	}

	//Validate All Dates In Results
	public void validateAllDatesInResults(Log Log, ScreenShots ScreenShots) {
		try {
			TestExecutionNotifier.showExecutionPopup();
			// Retrieve the user-selected date from the input field
			WebElement dateInput = driver.findElement(By.xpath("//input[contains(@class,'tg-fsonwarddate')]"));
			String userSelectedDate = dateInput.getAttribute("value");
			System.out.println("User selected date: " + userSelectedDate);

			// Remove ordinal suffixes from the day (e.g., "7th" becomes "7")
			String cleanedDate = userSelectedDate.replaceAll("(\\d+)(st|nd|rd|th)", "$1");

			// Define the input and output date formats
			SimpleDateFormat inputFormat = new SimpleDateFormat("d-MMM-yyyy");
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			// Parse and format the cleaned date
			String formattedUserDate = outputFormat.format(inputFormat.parse(cleanedDate));
			System.out.println("Formatted user date: " + formattedUserDate);

			// Find all displayed flight dates
			List<WebElement> flightDates = driver.findElements(By.className("tg-depdate"));

			// Initialize a flag to track if any date matches
			boolean dateFound = false;

			// Iterate through each flight date and compare with the selected date
			for (WebElement flightDate : flightDates) {
				String flightDateValue = flightDate.getAttribute("data-tgdepdate");

				if (flightDateValue.equals(formattedUserDate)) {
					dateFound = true;
					System.out.println("user Entered date: " + formattedUserDate);
					System.out.println("Flight date Found On The Result: " + flightDateValue);// Exit the loop once a match is found
				}
			}

			// Log the result once in the report
			if (dateFound) {
				Log.ReportEvent("PASS", "Flights are displayed based on user search dates. User entered: " + userSelectedDate + ", Found: " + formattedUserDate);
			} else {
				Log.ReportEvent("FAIL", "Flights are not displayed based on user search dates. User entered: " + userSelectedDate + ", Searched: " + formattedUserDate);
			}


			// Capture a screenshot of the current state
			ScreenShots.takeScreenShot();

		} catch (Exception e) {
			// Handle exceptions and log the error
			Log.ReportEvent("ERROR", "An error occurred during date validation: " + e.getMessage());
			ScreenShots.takeScreenShot();
			e.printStackTrace();
			Assert.fail();
		}
	}


	// Method to validate SME/Corporate Fare
	public void validateSME(Log log, ScreenShots screenShots) {
		try {
			Thread.sleep(2000);

			WebElement fareElement = driver.findElement(
					By.xpath("//h6[@data-tgfaretype]")
			);
			String fareText = fareElement.getText();
			log.ReportEvent("INFO", "Fare Element Text: " + fareText);

			// Validate fare type
			if (fareText.equalsIgnoreCase("SME fare") || fareText.equalsIgnoreCase("corporate fare")) {
				log.ReportEvent("PASS", "SME/Corporate Fare Only flights are displayed");
				screenShots.takeScreenShot1();
			} else {
				log.ReportEvent("FAIL", "SME/Corporate Fare Only flights are not displayed");
				screenShots.takeScreenShot1();
				Assert.fail("Displayed fare is not SME or Corporate");
			}

		} catch (TimeoutException e) {
			log.ReportEvent("FAIL", "Timeout: The fare element was not visible within the specified time.");
			screenShots.takeScreenShot1();
			Assert.fail("Timeout occurred: " + e.getMessage());
		} catch (NoSuchElementException e) {
			log.ReportEvent("FAIL", "Element not found: " + e.getMessage());
			screenShots.takeScreenShot1();
			Assert.fail("Element not found: " + e.getMessage());
		} catch (InterruptedException e) {
			log.ReportEvent("FAIL", "Thread interrupted: " + e.getMessage());
			screenShots.takeScreenShot1();
			Assert.fail("Interrupted: " + e.getMessage());
		} catch (Exception e) {
			log.ReportEvent("FAIL", "Unexpected error: " + e.getMessage());
			screenShots.takeScreenShot1();
			Assert.fail("Unexpected error: " + e.getMessage());
		}
	}

	//Method to Move Particular Element
	public void moveToParticularElement(String data) {
		Actions move = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//legend[text()='" + data + "']"));
		move.moveToElement(element).perform();
	}


	//Method to Select Reason for Selection
	public void selectReasonForSelection(String reason) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='" + reason + "']")).click();
	}

	//Method to Select Reason for Selection
	public void clickOnProceedBooking() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Proceed to Booking']")).click();
	}

	//Method to Flights Details
	public String[] getFlightDetailsForOneWay() {
		String originAndDestination = driver.findElement(By.xpath("//p[@data-tgfullsector]")).getText();
		// Split the string on the hyphen
		String[] airports = originAndDestination.split("-");

		// Access the two airport codes
		String origin = airports[0];
		String destination = airports[1];
		// Print them
		System.out.println("Origin: " + origin);
		System.out.println("Destination: " + destination);

		String arrivalAndDeparture = driver.findElement(By.xpath("//p[@data-tgfulltime]")).getText();
		// Split the string on the hyphen
		String[] timings = arrivalAndDeparture.split("-");

		// Access the two airport codes
		String departure = timings[0];
		String arrival = timings[1];
		// Print them
		System.out.println("Origin: " + arrival);
		System.out.println("Destination: " + departure);

		String price = driver.findElement(By.xpath("//h6[@data-tgfullfare]")).getText();

		return new String[]{origin, destination, departure, arrival, price};
	}

	//Method to Validate Flight Details on Booking Screen
	public void validateFlightDetailsOnBookingScreen(String stopsText, String date, String origin, String destination, String departure, String arrival, String price, Log log, ScreenShots screenShots) throws InterruptedException {
		Thread.sleep(6000);
		try {
			if (stopsText.contentEquals("1 stops")) {
				String originData = driver.findElement(By.xpath("(//h6[@data-tgflorigin])[1]")).getAttribute("data-tgflorigin");
				String[] originCode = originData.split("-");
				// Get the airport code (right part) and trim any whitespace
				String originAirportCode = originCode[1].trim();
				System.out.println("Airport Code: " + originAirportCode);

				String destinationData = driver.findElement(By.xpath("(//h6[@data-tgfldestination])[2]")).getAttribute("data-tgfldestination");
				String[] destinationCode = destinationData.split("-");
				// Get the airport code (right part) and trim any whitespace
				String destinationAirportCode = destinationCode[1].trim();
				System.out.println("Airport Code: " + destinationAirportCode);

				String dateDetails = driver.findElement(By.xpath("//h6[contains(@class,'flight-title')]")).getText();
				System.out.println(dateDetails);
				String[] dateData = dateDetails.split(":");

				// Get the right-hand part and trim it
				String dateValue = dateData[1].trim();

				System.out.println("Date: " + date);
				String priceValue = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();


				String depTimeData = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[1]")).getText();
				String arrTimeData = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[2]")).getText();
				String originValue = origin.trim();
				String destinationValue = destination.trim();
				String departureValue = departure.trim();
				String arrivalValue = arrival.trim();
				String dateValues = date.trim();

				ValidateActualAndExpectedValuesForFlights(originAirportCode, originValue, "Origin Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(destinationAirportCode, destinationValue, "Destination Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(depTimeData, departureValue, "Departing Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(arrTimeData, arrivalValue, "Arrival Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(dateValue, dateValues, "Date Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(priceValue, price, "Price Details in Search Screen and Booking Screen", log);
			} else if (stopsText.contentEquals("Nonstop")) {
				String originData = driver.findElement(By.xpath("//h6[@data-tgflorigin]")).getAttribute("data-tgflorigin");
				String[] originCode = originData.split("-");
				// Get the airport code (right part) and trim any whitespace
				String originAirportCode = originCode[1].trim();
				System.out.println("Airport Code: " + originAirportCode);

				String destinationData = driver.findElement(By.xpath("//h6[@data-tgfldestination]")).getAttribute("data-tgfldestination");
				String[] destinationCode = destinationData.split("-");
				// Get the airport code (right part) and trim any whitespace
				String destinationAirportCode = destinationCode[1].trim();
				System.out.println("Airport Code: " + destinationAirportCode);

				String dateDetails = driver.findElement(By.xpath("//h6[contains(@class,'flight-title')]")).getText();
				System.out.println(dateDetails);
				String[] dateData = dateDetails.split(":");

				// Get the right-hand part and trim it
				String dateValue = dateData[1].trim();

				System.out.println("Date: " + date);
				String priceValue = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();


				String depTimeData = driver.findElement(By.xpath("//h6[@data-tgfldeptime]")).getText();
				String arrTimeData = driver.findElement(By.xpath("//h6[@data-tgflarrtime]")).getText();
				String originValue = origin.trim();
				String destinationValue = destination.trim();
				String departureValue = departure.trim();
				String arrivalValue = arrival.trim();
				String dateValues = date.trim();

				ValidateActualAndExpectedValuesForFlights(originAirportCode, originValue, "Origin Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(destinationAirportCode, destinationValue, "Destination Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(depTimeData, departureValue, "Departing Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(arrTimeData, arrivalValue, "Arrival Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(dateValue, dateValues, "Date Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(priceValue, price, "Price Details in Search Screen and Booking Screen", log);

			} else if (stopsText.contentEquals("2 stops")) {
				String originData = driver.findElement(By.xpath("(//h6[@data-tgflorigin])[1]")).getAttribute("data-tgflorigin");
				String[] originCode = originData.split("-");
				// Get the airport code (right part) and trim any whitespace
				String originAirportCode = originCode[1].trim();
				System.out.println("Airport Code: " + originAirportCode);

				String destinationData = driver.findElement(By.xpath("(//h6[@data-tgfldestination])[last()]")).getAttribute("data-tgfldestination");
				String[] destinationCode = destinationData.split("-");
				// Get the airport code (right part) and trim any whitespace
				String destinationAirportCode = destinationCode[1].trim();
				System.out.println("Airport Code: " + destinationAirportCode);

				String dateDetails = driver.findElement(By.xpath("//h6[contains(@class,'flight-title')]")).getText();
				System.out.println(dateDetails);
				String[] dateData = dateDetails.split(":");

				// Get the right-hand part and trim it
				String dateValue = dateData[1].trim();

				System.out.println("Date: " + date);
				String priceValue = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();


				String depTimeData = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[1]")).getText();
				String arrTimeData = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[last()]")).getText();
				String originValue = origin.trim();
				String destinationValue = destination.trim();
				String departureValue = departure.trim();
				String arrivalValue = arrival.trim();
				String dateValues = date.trim();

				ValidateActualAndExpectedValuesForFlights(originAirportCode, originValue, "Origin Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(destinationAirportCode, destinationValue, "Destination Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(depTimeData, departureValue, "Departing Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(arrTimeData, arrivalValue, "Arrival Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(dateValue, dateValues, "Date Details in Search Screen and Booking Screen", log);
				ValidateActualAndExpectedValuesForFlights(priceValue, price, "Price Details in Search Screen and Booking Screen", log);

			}
		} catch (Exception e) {
			log.ReportEvent("FAIL", "Flights Details Validated on Booking Screen is UnSuccessful");
			screenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}


	//Method to get the Stops Based on Index
	public String getStopsBasedOnIndex(String stopsIndex) {
		String stops = driver.findElement(By.xpath("(//span[@data-tgnumstops])[" + stopsIndex + "]")).getText();
		return stops;
	}

	//Method to get SelectedDate
	public String getSelectedDate() {
		String date = driver.findElement(By.xpath("(//span[@data-tgdepdate])[1]")).getText();
		return date;
	}

	//Method to Select LayOver Airlines on Result Screen
	public void clickOnLayOverAirlineCheckboxes(String... airlineNames) {
		for (String airlineName : airlineNames) {
			try {
				WebElement airline = driver.findElement(By.xpath("//legend[text()='LAYOVER AIRPORTS']"));
				Actions move = new Actions(driver);
				move.moveToElement(airline).perform();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//legend[text()='LAYOVER AIRPORTS']/parent::div//span[normalize-space(text())='" + airlineName + "']/parent::div/parent::li//input")).click();
				System.out.println("Clicked checkbox for: " + airlineName);
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Could not find/click checkbox for: " + airlineName);
				e.printStackTrace();
				Assert.fail();

			}
		}

	}

	//Method to getStops Text Based On Index
	public String getStopsText(int index) {
		String stopsText = driver.findElement(By.xpath("(//*[@class='tg-stops'])[" + index + "]")).getText();
		return stopsText;
	}
	//Method to getClasses Text Based On Index
	public String getClassText() {
		String classText = driver.findElement(By.className("capitalize")).getText();
		return classText;
	}

	//Method to click on Continue Button based on Fare
	public String[] clickOnContinueBasedOnFareType(String fareType, String reason) throws InterruptedException {
		Thread.sleep(5000);
		TestExecutionNotifier.showExecutionPopup();
		String price = null;
		String fare=null;
		try {
			price = null;
			fare=null;
			List<WebElement> fareElements = driver.findElements(By.xpath("//div[@data-tgflfaretype='" + fareType + "']"));

			if (!fareElements.isEmpty() && fareElements.get(0).isDisplayed()) {
				Thread.sleep(2000);
				WebElement continueButton = driver.findElement(By.xpath(
						"//div[@data-tgflfaretype='" + fareType + "']/parent::div/following-sibling::div//button[text()='Continue']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
				((JavascriptExecutor) driver).executeScript(
						"window.scrollTo({ top: arguments[0].getBoundingClientRect().top + window.scrollY - 100, behavior: 'smooth' });",
						continueButton);
				Thread.sleep(3000);
				price = driver.findElement(By.xpath("//div[@data-tgflfaretype='" + fareType + "']/parent::div//div[@data-tgflfare]")).getText();
				continueButton.click();
                fare=fareType;
				if (isElementPresent(By.xpath("//h2[text()='Airport Change']"))) {
					clickOnYesContinue();
				}

				if (isElementPresent(By.xpath("//h2[text()='Reason for Selection']"))) {
					clickOnSelectRegionPopup(reason);
					clickOnProceedBooking();
				}
			} else {
				Thread.sleep(2000);
				WebElement continueButton=driver.findElement(By.xpath("(//button[text()='Continue'])[1]"));
				((JavascriptExecutor) driver).executeScript(
						"window.scrollTo({ top: arguments[0].getBoundingClientRect().top + window.scrollY - 100, behavior: 'smooth' });",
						continueButton);
				Thread.sleep(3000);
				fare = driver.findElement(By.xpath("(//*[@data-tgflfaretype])[1]")).getText();
				price = driver.findElement(By.xpath("(//div[@data-tgflfare])[1]")).getText();
				continueButton.click();
				if (isElementPresent(By.xpath("//h2[text()='Airport Change']"))) {
					clickOnYesContinue();
				}

				if (isElementPresent(By.xpath("//h2[text()='Reason for Selection']"))) {
					clickOnSelectRegionPopup(reason);
					clickOnProceedBooking();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[]{price, fare};
	}

	//Method to click on Continue Button based on Fare
	public String[] clickOnSelectBasedOnFareType(String fareType, String reason) throws InterruptedException {
		Thread.sleep(5000);
		TestExecutionNotifier.showExecutionPopup();
		String price = null;
		String fare=null;
		try {
			price = null;
			fare=null;
			List<WebElement> fareElements = driver.findElements(By.xpath("//div[@data-tgflfaretype='" + fareType + "']"));

			if (!fareElements.isEmpty() && fareElements.get(0).isDisplayed()) {
				Thread.sleep(2000);
				WebElement continueButton = driver.findElement(By.xpath(
						"//div[@data-tgflfaretype='" + fareType + "']/parent::div/following-sibling::div//button[text()='Select']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
				((JavascriptExecutor) driver).executeScript(
						"window.scrollTo({ top: arguments[0].getBoundingClientRect().top + window.scrollY - 100, behavior: 'smooth' });",
						continueButton);
				Thread.sleep(3000);
				price = driver.findElement(By.xpath("//div[@data-tgflfaretype='" + fareType + "']/parent::div//div[@data-tgflfare]")).getText();
				continueButton.click();
				fare=fareType;
				if (isElementPresent(By.xpath("//h2[text()='Airport Change']"))) {
					clickOnYesContinue();
				}

				if (isElementPresent(By.xpath("//h2[text()='Reason for Selection']"))) {
					clickOnSelectRegionPopup(reason);
					clickOnProceedBooking();
				}
			} else {
				Thread.sleep(2000);
				WebElement continueButton=driver.findElement(By.xpath("(//button[text()='Select'])[1]"));
				((JavascriptExecutor) driver).executeScript(
						"window.scrollTo({ top: arguments[0].getBoundingClientRect().top + window.scrollY - 100, behavior: 'smooth' });",
						continueButton);
				Thread.sleep(3000);
				fare = driver.findElement(By.xpath("(//*[@data-tgflfaretype])[1]")).getText();
				price = driver.findElement(By.xpath("(//div[@data-tgflfare])[1]")).getText();
				continueButton.click();
				if (isElementPresent(By.xpath("//h2[text()='Airport Change']"))) {
					clickOnYesContinue();
				}

				if (isElementPresent(By.xpath("//h2[text()='Reason for Selection']"))) {
					clickOnSelectRegionPopup(reason);
					clickOnProceedBooking();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[]{price, fare};
	}

	//Method to click on Yes Continue Button
	public void clickOnYesContinue() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Yes, Continue']")).click();

	}

	//Method to click on Yes Continue Button
	public void clickOnContinue() throws InterruptedException {
		Thread.sleep(20000);
		driver.findElement(By.xpath("//*[contains(@class,'tg-bar-continue-btn')]")).click();
		Thread.sleep(1000);

	}

	public boolean isElementPresent(By locator) {
		return !driver.findElements(locator).isEmpty() &&
				driver.findElements(locator).get(0).isDisplayed();
	}

	//Method to Click on Select Reasons Popup
	public void clickOnSelectRegionPopup(String reason) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='" + reason + "']")).click();
		Thread.sleep(1000);
	}

	//Method to Validate Flight details
	public String[] getFlightDetails(String stops, Log Log, ScreenShots ScreenShots) {
		String Origin = null;
		String Destination = null;
		String departingTime = null;
		String arrivalTime = null;
		String departDate = null;
		String arrivalDate = null;
		TestExecutionNotifier.showExecutionPopup();
		try {
			if (stops.contentEquals("Nonstop")) {
				Origin = driver.findElement(By.className("tg-fromorigin")).getAttribute("data-tgfloriginairport");
				Destination = driver.findElement(By.className("tg-fromdestination")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.className("tg-fromdeptime")).getText();
				arrivalTime = driver.findElement(By.className("tg-fromarrtime")).getText();
				departDate = driver.findElement(By.className("tg-fromdepdate")).getText();
				arrivalDate = driver.findElement(By.className("tg-fromarrdate")).getText();
			} else if (stops.contentEquals("1 stops")) {
				Origin = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromorigin')])[1]")).getAttribute("data-tgfloriginairport");
				Destination = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromdestination')])[2]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromdeptime')])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromarrtime')])[2]")).getText();
				departDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromdepdate')])[1]")).getText();
				arrivalDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromarrdate')])[2]")).getText();
			} else if (stops.contentEquals("2 stops")) {
				Origin = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromorigin')])[1]")).getAttribute("data-tgfloriginairport");
				Destination = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromdestination')])[last()]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromdeptime')])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromarrtime')])[last()]")).getText();
				departDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromdepdate')])[1]")).getText();
				arrivalDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fromarrdate')])[last()]")).getText();
			} else {
				Log.ReportEvent("FAIL", "Unable to get data from Search Screen");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Unable to get data from Search Screen");
			e.printStackTrace();
			Assert.fail();

		}
		return new String[]{Origin, Destination, departingTime, arrivalTime, departDate, arrivalDate};
	}

	//Method to Validate Flights Details in Booking Screen
	public void validateFlightDetailsInBookingScreen(Log Log, ScreenShots ScreenShots, String price,String fare, String stops,String classes,String Origin,String Destination,String departingTime,String arrivalTime,String departDate,String arrivalDate) {
		try {
			String fareType=fare+" "+"Fare";
			TestExecutionNotifier.showExecutionPopup();
			WebElement data=driver.findElement(By.xpath("//h6[@data-tgflight]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", data);
			Thread.sleep(2000);
			if (stops.contentEquals("Nonstop")) {
				Thread.sleep(2000);
				Origin = driver.findElement(By.className("tg-fborigin")).getAttribute("data-tgdepartfloriginairport");
				Destination = driver.findElement(By.className("tg-fbdestination")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.className("tg-fb-deptime")).getText();
				arrivalTime = driver.findElement(By.className("tg-fbarrtime")).getText();
				departDate = driver.findElement(By.className("tg-fbdepdate")).getText();
				arrivalDate = driver.findElement(By.className("tg-fbarrdate")).getText();
				classes = driver.findElement(By.className("tg-fbcabinclass")).getText();
				fare = driver.findElement(By.className("flt-booking-faretype")).getText();

				price = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();

				ValidateActualAndExpectedValuesForFlights(Origin, Origin, "Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(Destination, Destination, "Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departDate, departDate, "Depart Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalDate, arrivalDate, "Arrival Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);
//				ValidateActualAndExpectedValuesForFlights(classes, classes, "Cabin Class Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fare, fareType, "Fare Details in Search Screen and Booking Screen", Log);
				ScreenShots.takeScreenShot1();


			} else if (stops.contentEquals("1 stops")) {
				Thread.sleep(2000);
				Origin = driver.findElement(By.xpath("(//*[contains(@class,'tg-fborigin')])[1]")).getAttribute("data-tgdepartfloriginairport");
				Destination = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdestination')])[2]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fb-deptime')])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbarrtime')])[2]")).getText();
				departDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepdate')])[1]")).getText();
				arrivalDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbarrdate')])[2]")).getText();
				fare = driver.findElement(By.className("flt-booking-faretype")).getText();

				price = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();

				ValidateActualAndExpectedValuesForFlights(Origin, Origin, "Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(Destination, Destination, "Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departDate, departDate, "Depart Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalDate, arrivalDate, "Arrival Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);
//				validateCabinClassesOnBookingScreen(classes,Log,ScreenShots);
				ValidateActualAndExpectedValuesForFlights(fare, fareType, "Fare Details in Search Screen and Booking Screen", Log);
				ScreenShots.takeScreenShot1();


			}  else if (stops.contentEquals("2 stops")) {
				Thread.sleep(2000);
				Origin = driver.findElement(By.xpath("(//*[contains(@class,'tg-fborigin')])[1]")).getAttribute("data-tgdepartfloriginairport");
				Destination = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdestination')])[last()]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fb-deptime')])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbarrtime')])[last()]")).getText();
				departDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepdate')])[1]")).getText();
				arrivalDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbarrdate')])[last()]")).getText();
				fare = driver.findElement(By.className("flt-booking-faretype")).getText();

				price = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();

				ValidateActualAndExpectedValuesForFlights(Origin, Origin, "Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(Destination, Destination, "Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departDate, departDate, "Depart Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalDate, arrivalDate, "Arrival Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);
//				validateCabinClassesOnBookingScreen(classes,Log,ScreenShots);
				ValidateActualAndExpectedValuesForFlights(fare, fareType, "Fare Details in Search Screen and Booking Screen", Log);
				ScreenShots.takeScreenShot1();
			}else{

				Log.ReportEvent("FAIL", "Validation is Mismatching");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "An error occurred during date validation: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Method to Validate Classes on Flights
	public void validateCabinClasses(String classes,Log Log, ScreenShots ScreenShots)
	{
		try{
			List<WebElement> classesNames=driver.findElements(By.xpath("//p[@data-tgflcabinclass]"));
			for(WebElement className:classesNames)
			{
				String cabinClassName=className.getText();
				ValidateActualAndExpectedValuesForFlights(cabinClassName, classes, "Cabin Classes in Dropdown ans Searched flights", Log);
			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Getting different Cabin Classes" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();		}
	}

	//Method to Validate Classes on Flights on Booking Screen
	public void validateCabinClassesOnBookingScreen(String classes,Log Log, ScreenShots ScreenShots)
	{
		try{
			List<WebElement> classesNames=driver.findElements(By.xpath("//span[@data-tgdepartflcabinclass]"));
			for(WebElement className:classesNames)
			{
				String cabinClassName=className.getText();
				ValidateActualAndExpectedValuesForFlights(cabinClassName, classes, "Cabin Classes in Search Screen and Booking Screen", Log);
			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Getting different Cabin Classes" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();		}
	}





	//Method to Get Layover Airline Names
	public ArrayList getLayoverAirlines()
	{
			ArrayList layoverAirportNames = new ArrayList();
			List<WebElement>names=driver.findElements(By.xpath("//*[contains(@class,'tg-airport-name')]//span"));
          for(WebElement name:names)
		  {
			  String layoverNames=name.getText();
			  layoverAirportNames.add(layoverNames);
		  }
         return layoverAirportNames;
		}

	//Method to Validate Layover AirLines
	public void validateLayoverAirlines(Log Log, ScreenShots ScreenShots, String... layoverNames)
	{
		try{
			for(String name:layoverNames)
			{
				int start = name.indexOf('(');
				int end = name.indexOf(')');
				String code = "";
				if (start != -1 && end != -1 && start < end) {
					code = name.substring(start + 1, end);
				}
				System.out.println("Airport code: " + code);
				ArrayList data=new ArrayList<>();
				List<WebElement>layoverAirlineNames=driver.findElements(By.className("tg-layovercity"));
				for(WebElement names:layoverAirlineNames)
				{
					String layoverData=names.getText();
					int start1 = layoverData.indexOf('(');
					int end1 = layoverData.indexOf(')');
					String code1 = "";
					if (start1 != -1 && end1 != -1 && start1 < end1) {
						code1 = layoverData.substring(start1 + 1, end1);
					}
					System.out.println("Airport code: " + code1);
					data.add(code1);
					if (data.contains(code)) {
						Log.ReportEvent("PASS", "LayOver AirLine Data is Displayed"+ code);
						// Proceed with your script logic
					} else {
						Log.ReportEvent("FAIL", "LayOver AirLine Data is Not Displayed");
						ScreenShots.takeScreenShot1();
						Assert.fail();
						// You can throw an error or handle accordingly
					}
				}

			}
		}catch(Exception e)
		{
			Log.ReportEvent("FAIL", "LayOver AirLine Data is Not Displayed");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Method to Select LayOver Airlines on Result Screen
	public void clickLayOverAirlineCheckboxes(String... airlineNames) {
		for (String airlineName : airlineNames) {
			try {
				WebElement airline = driver.findElement(By.xpath("//*[text()='LAYOVER AIRPORTS']"));
				Actions move = new Actions(driver);
				move.moveToElement(airline).perform();
				Thread.sleep(1000);
				String name=airlineName.trim();
				driver.findElement(By.xpath("//*[normalize-space(text())='"+name+"']/parent::div/parent::li//input")).click();
				System.out.println("Clicked checkbox for: " + airlineName);
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Could not find/click checkbox for: " + airlineName);
				e.printStackTrace();
				Assert.fail();

			}
		}
	}

	//Method to Validate Flight details
	public String[] getFlightDetailsOnBookingScreen(String stops, Log Log, ScreenShots ScreenShots) {
		String Origin = null;
		String Destination = null;
		String departingTime = null;
		String arrivalTime = null;
		String departDate = null;
		String arrivalDate = null;
		String price=null;
		TestExecutionNotifier.showExecutionPopup();
		try {
			if (stops.contentEquals("Nonstop")) {
				Origin = driver.findElement(By.className("tg-fborigin")).getAttribute("data-tgdepartfloriginairport");
				Destination = driver.findElement(By.className("tg-fbdestination")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.className("tg-fb-deptime")).getText();
				arrivalTime = driver.findElement(By.className("tg-fbarrtime")).getText();
				departDate = driver.findElement(By.className("tg-fbdepdate")).getText();
				arrivalDate = driver.findElement(By.className("tg-fbarrdate")).getText();
				price = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();

			} else if (stops.contentEquals("1 stops")) {
				Origin = driver.findElement(By.xpath("(//*[contains(@class,'tg-fborigin')])[1]")).getAttribute("data-tgdepartfloriginairport");
				Destination = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdestination')])[2]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fb-deptime')])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbarrtime')])[2]")).getText();
				departDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepdate')])[1]")).getText();
				arrivalDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbarrdate')])[2]")).getText();
				price = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();

			} else if (stops.contentEquals("2 stops")) {
				Origin = driver.findElement(By.xpath("(//*[contains(@class,'tg-fborigin')])[1]")).getAttribute("data-tgdepartfloriginairport");
				Destination = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdestination')])[last()]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fb-deptime')])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbarrtime')])[last()]")).getText();
				departDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepdate')])[1]")).getText();
				arrivalDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbarrdate')])[last()]")).getText();
				price = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();

			} else {
				Log.ReportEvent("FAIL", "Unable to get data from Search Screen");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Unable to get data from Search Screen");
			e.printStackTrace();
			Assert.fail();
		}
		return new String[]{Origin, Destination, departingTime, arrivalTime, departDate, arrivalDate,price};
	}


	//Method to generate random numbers
	public static String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rng = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(characters.charAt(rng.nextInt(characters.length())));
		}
		return sb.toString();
	}

//Method to Enter Adult Details.
public void enterAdultDetailsForDomestic(String[] title,int adults,Log Log, ScreenShots ScreenShots) throws InterruptedException {
try{
	if(adults>1)
	{
		for (int i = 0; i < title.length; i++) {
			String firstName = generateRandomString(5);
			String lastName = generateRandomString(5);
			String first="Appu"+firstName;
			String last="Kumar"+lastName;

			String titleNames = title[i];
			int xpathIndex = i + 2;

			WebElement titleDropDown = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbpaxtitile')])["+xpathIndex+"]"));
			titleDropDown.click();
			driver.findElement(By.xpath("//li[@data-value='"+titleNames+"']")).click();
			Thread.sleep(1000);

			// Assuming your input fields have ids like firstname1, lastname1, firstname2, lastname2, etc.
			WebElement firstNameField = driver.findElement(By.xpath("(//input[@name='firstname'])["+xpathIndex+"]"));
			firstNameField.clear();
			firstNameField.sendKeys(first);
			WebElement lastNameField = driver.findElement(By.xpath("(//input[@name='lastname'])["+xpathIndex+"]"));
			lastNameField.clear();
			lastNameField.sendKeys(last);
		}
		ScreenShots.takeScreenShot1();

	}else {
		System.out.println("One Adult had been Selected");
	}
}catch(Exception e)
{
	Log.ReportEvent("FAIL", "Enter Adult Details is UnSuccessful");
	e.printStackTrace();
	ScreenShots.takeScreenShot1();
	Assert.fail();
  }
	}
	public static String generateRandomDigits(int length) {
		String digits = "0123456789";
		Random rng = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(digits.charAt(rng.nextInt(digits.length())));
		}
		return sb.toString();
	}

	//Method to Enter Adult Details.
	public void enterAdultDetailsForInterNational(String[] title,int adults,Log Log, ScreenShots ScreenShots) throws InterruptedException {
		try{
			if(adults>1)
			{
				for (int i = 0; i < title.length; i++) {
					String firstName = generateRandomString(5);
					String lastName = generateRandomString(5);
					String first="Appu"+firstName;
					String last="Kumar"+lastName;

					String titleNames = title[i];
					int xpathIndex = i + 2;

					WebElement titleDropDown = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbpaxtitile')])["+xpathIndex+"]"));
					titleDropDown.click();
					driver.findElement(By.xpath("//li[@data-value='"+titleNames+"']")).click();
					Thread.sleep(1000);

					// Assuming your input fields have ids like firstname1, lastname1, firstname2, lastname2, etc.
					WebElement firstNameField = driver.findElement(By.xpath("(//input[@name='firstname'])["+xpathIndex+"]"));
					firstNameField.clear();
					firstNameField.sendKeys(first);
					WebElement lastNameField = driver.findElement(By.xpath("(//input[@name='lastname'])["+xpathIndex+"]"));
					lastNameField.clear();
					lastNameField.sendKeys(last);

					WebElement dateInput = driver.findElement(By.xpath("(//input[@id='date'])["+xpathIndex+"]"));
					dateInput.sendKeys("12-12-2000");

					WebElement passportNumber = driver.findElement(By.xpath("(//input[@name='passportnumber'])["+xpathIndex+"]"));
					passportNumber.clear();
					String randomNumber = generateRandomDigits(6);  // e.g., "483920"
					System.out.println("Random Number: " + randomNumber);
					passportNumber.sendKeys(randomNumber);

					WebElement expireDateInput = driver.findElement(By.xpath("(//input[@name='passportexpirydate'])["+xpathIndex+"]"));
					expireDateInput.sendKeys("12-12-2040");

					WebElement IssuedDateInput = driver.findElement(By.xpath("(//input[@name='passportissuedate'])["+xpathIndex+"]"));
					IssuedDateInput.sendKeys("12-06-2025");
				}
			}else{
				System.out.println("One Adult had been Selected");
			}
			ScreenShots.takeScreenShot1();
		}catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Enter Adult Details is UnSuccessful");
			e.printStackTrace();
			ScreenShots.takeScreenShot1();
			Assert.fail();
		}
	}

	//Method to Select the Pick Seat
	public int selectSeatFormPickSeat(Log log, ScreenShots screenShots) throws InterruptedException {
		Set<String> selectedSeats = new HashSet<>();
		int totalPrice = 0; // To accumulate seat prices

		// Get all "Pick Seat" buttons
		List<WebElement> pickSeatButtons = driver.findElements(By.xpath("//button[contains(text(), 'Pick Seat')]"));

		System.out.println("Total Pick Seat buttons found: " + pickSeatButtons.size());

		for (int i = 0; i < pickSeatButtons.size(); i++) {
			// Re-locate buttons each time to avoid stale elements
			pickSeatButtons = driver.findElements(By.xpath("//button[contains(text(), 'Pick Seat')]"));

			WebElement button = pickSeatButtons.get(i);
			button.click();

			// Wait for seat UI to load
			Thread.sleep(1000);

			// Get all available, open, non-selected seats
			List<WebElement> availableSeats = driver.findElements(By.cssSelector(".seat.seat-open.non-empty-seat"));

			boolean seatPicked = false;

			for (WebElement seat : availableSeats) {
				String seatLabel = seat.getAttribute("aria-label").trim();

				if (!selectedSeats.contains(seatLabel)) {
					seat.click();
					selectedSeats.add(seatLabel);
					System.out.println("Passenger " + (i + 1) + " selected seat: " + seatLabel);

					try {
						// Find the element that holds the tg-selected-seat-price attribute
						WebElement priceElement = driver.findElement(By.xpath("//*[@tg-selected-seat-price]"));

						// Extract the value from the attribute
						String priceValue = priceElement.getAttribute("tg-selected-seat-price"); // e.g., "1838.0"
						System.out.println("Seat price from attribute: " + priceValue);

						// Optional: compare visible price vs attribute value
						String visiblePriceText = priceElement.getText().replaceAll("[^0-9.]", ""); // e.g., "1838.0"
						System.out.println("Seat price from visible text: " + visiblePriceText);

						// Parse both values
						double attrPrice = Double.parseDouble(priceValue);
						double visiblePrice = visiblePriceText.isEmpty() ? 0 : Double.parseDouble(visiblePriceText);

						// Log mismatch if any
						if (Math.round(attrPrice) != Math.round(visiblePrice)) {
							System.out.println("⚠ Price mismatch: Attribute = " + attrPrice + ", Visible = " + visiblePrice);
						}

						// Add to total (rounded to int rupees)
						totalPrice += (int) Math.round(attrPrice);

						log.ReportEvent("INFO", "Select Pick Seat is "+ seatLabel);
						screenShots.takeScreenShot1();

					} catch (Exception e) {
						System.out.println("❌ Failed to extract seat price: " + e.getMessage());
						log.ReportEvent("FAIL", "Failed to extract seat price:  "+ e.getMessage());
                        Assert.fail();
					}

					seatPicked = true;
					break;
				}
			}

			if (!seatPicked) {
				System.out.println("No available unique seat found for passenger #" + (i + 1));
			}

			Thread.sleep(1000); // Wait after selecting

			// Attempt to close the seat dialog
			try {
				WebElement closeButton = driver.findElement(By.xpath("//button[text()='Continue']")); // Adjust if needed
				closeButton.click();
			} catch (Exception e) {
				System.out.println("⚠ Close button not found.");
				log.ReportEvent("FAIL", "Close button not found. "+ e.getMessage());
				Assert.fail();
			}

			Thread.sleep(1000); // delay before next iteration
		}

		System.out.println("✅ Total price for all selected seats: ₹" + totalPrice);
		return totalPrice;
	}

	public void selectMealAndBaggageForEachPassengerOnBookingScreen(Log log, ScreenShots screenShots) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			List<WebElement> mealDropdowns = driver.findElements(By.className("tg-fbowmeal"));
			List<WebElement> baggageDropdowns = driver.findElements(By.className("tg-fbowbaggage"));

			int passengerCount = Math.min(mealDropdowns.size(), baggageDropdowns.size());
			System.out.println("Found " + passengerCount + " passengers with meal & baggage options");

			for (int i = 0; i < passengerCount; i++) {
				System.out.println("Passenger #" + (i + 1));

				// --- MEAL SELECTION ---
				try {
					if (i >= mealDropdowns.size()) {
						log.ReportEvent("INFO", "Meal dropdown index out of bounds for passenger #" + (i + 1));
					} else {
						WebElement mealDropdown = mealDropdowns.get(i);
						wait.until(ExpectedConditions.elementToBeClickable(mealDropdown)).click();

						WebElement mealContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//div[contains(@class,'MuiPopover-paper')]")));
						List<WebElement> mealOptions = mealContainer.findElements(By.xpath(".//li[@role='option']"));

						if (!mealOptions.isEmpty()) {
							WebElement optionToSelect;

							// Select 2nd if available, else click only available one
							if (mealOptions.size() > 1) {
								optionToSelect = mealOptions.get(1);
							} else {
								optionToSelect = mealOptions.get(0); // e.g. "Meal Preference"
							}

							String optionText = optionToSelect.getText().trim();
							if (optionToSelect.isEnabled()) {
								Thread.sleep(3000);
								optionToSelect.click();
								System.out.println("  Selected meal option: " + optionText);
								log.ReportEvent("PASS", "Meal option selected: " + optionText);
							} else {
								log.ReportEvent("INFO", "Meal option present but not enabled for passenger #" + (i + 1));
							}
						} else {
							log.ReportEvent("INFO", "No meal options available for passenger #" + (i + 1));
						}
					}
				} catch (Exception e) {
					log.ReportEvent("INFO", "Skipping meal selection for passenger #" + (i + 1) + ": " + e.getMessage());
				}
				screenShots.takeScreenShot1();
				Thread.sleep(3000);

				// --- BAGGAGE SELECTION ---
				try {
					if (i >= baggageDropdowns.size()) {
						log.ReportEvent("INFO", "Baggage dropdown index out of bounds for passenger #" + (i + 1));
					} else {
						WebElement baggageDropdown = baggageDropdowns.get(i);
						wait.until(ExpectedConditions.elementToBeClickable(baggageDropdown)).click();

						List<WebElement> baggageContainers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
								By.xpath("//div[contains(@class,'MuiPopover-paper')]")));
						WebElement latestContainer = baggageContainers.get(baggageContainers.size() - 1);
						List<WebElement> baggageOptions = latestContainer.findElements(By.xpath(".//li[@role='option']"));

						if (!baggageOptions.isEmpty()) {
							WebElement optionToSelect;

							if (baggageOptions.size() > 1) {
								optionToSelect = baggageOptions.get(1);
							} else {
								optionToSelect = baggageOptions.get(0); // e.g. "Baggage Preference"
							}

							String optionText = optionToSelect.getText().trim();
							if (optionToSelect.isEnabled()) {
								Thread.sleep(3000);
								optionToSelect.click();
								System.out.println("  Selected baggage option: " + optionText);
								log.ReportEvent("PASS", "Baggage option selected: " + optionText);
							} else {
								log.ReportEvent("INFO", "Baggage option present but not enabled for passenger #" + (i + 1));
							}
						} else {
							log.ReportEvent("INFO", "No baggage options available for passenger #" + (i + 1));
						}
					}
				} catch (StaleElementReferenceException staleEx) {
					log.ReportEvent("INFO", "Stale element during baggage selection for passenger #" + (i + 1) + ": " + staleEx.getMessage());
				} catch (Exception e) {
					log.ReportEvent("INFO", "Skipping baggage selection for passenger #" + (i + 1) + ": " + e.getMessage());
				}
				screenShots.takeScreenShot1();
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			log.ReportEvent("FAIL", "Unexpected error in meal and baggage selection: " + e.getMessage());
			screenShots.takeScreenShot1();
		}
	}





	public int getPriceSum(String xpath) {
		List<WebElement> priceElements = driver.findElements(By.xpath(xpath));
		int sum = 0;

		for (WebElement price : priceElements) {
			String priceText = price.getText(); // Example: ₹ 850
			try {
				int value = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
				sum += value;
			} catch (NumberFormatException e) {
				System.out.println("Skipping invalid price text: " + priceText);
			}
		}
		return sum;
	}

	//Method to get Meal and Baggage Deatils
	public void getAndValidateMealBaggagePriceDetails(int seatPrice, Log log, ScreenShots screenShots) {
		try {
			int totalMealPrice = 0;
			int totalBaggagePrice = 0;

			// Check for meal price elements
			List<WebElement> mealPriceElements = driver.findElements(By.xpath("//*[contains(@class,'tg-fbowmeal')]//span[@class='price']"));
			if (!mealPriceElements.isEmpty()) {
				totalMealPrice = getPriceSum("//*[contains(@class,'tg-fbowmeal')]//span[@class='price']");
			} else {
				log.ReportEvent("Info", "No meal price elements found, setting meal price to 0");
			}

			// Check for baggage price elements
			List<WebElement> baggagePriceElements = driver.findElements(By.xpath("//*[contains(@class,'tg-fbowbaggage')]//span[@class='price']"));
			if (!baggagePriceElements.isEmpty()) {
				totalBaggagePrice = getPriceSum("//*[contains(@class,'tg-fbowbaggage')]//span[@class='price']");
			} else {
				log.ReportEvent("Info", "No baggage price elements found, setting baggage price to 0");
			}

			int grandTotal = totalMealPrice + totalBaggagePrice + seatPrice;

			String priceText = driver.findElement(By.xpath("//span[text()='Total']/parent::div//h6")).getText();
			int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
			int fullPrice = grandTotal + price;

			log.ReportEvent("Info", "Total price: " + price);
			log.ReportEvent("Info", "Meal price: " + totalMealPrice);
			log.ReportEvent("Info", "Baggage price: " + totalBaggagePrice);
			log.ReportEvent("Info", "Seat price: " + seatPrice);
			log.ReportEvent("Info", "Grand Total price: " + fullPrice);

			String totalPrice = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();
			int extractedPrice = extractInrAmount(totalPrice);

			ValidateActualAndExpectedValuesForFlightsDetails(fullPrice, extractedPrice, "Pack Seat, Meal, Baggage Price and Total Price", log);
			screenShots.takeScreenShot1();

		} catch (Exception e) {
			log.ReportEvent("FAIL", "Price Details are Mismatching: " + e.getMessage());
			screenShots.takeScreenShot1();
			Assert.fail();
		}
	}


	public int extractInrAmount(String input) {
		// Pattern to match the INR amount (e.g., ₹ 25,998)
		Pattern pattern = Pattern.compile("₹\\s?([\\d,]+)");
		Matcher matcher = pattern.matcher(input);

		if (matcher.find()) {
			String inrValue = matcher.group(1); // Get only the numeric part
			String numeric = inrValue.replaceAll(",", ""); // Remove commas
			return Integer.parseInt(numeric);
		} else {
			throw new IllegalArgumentException("No INR value found in input: " + input);
		}
	}


	// Method to Validate Actual and Expected Integer Data with Messages for Both Pass and Fail
	public void ValidateActualAndExpectedValuesForFlightsDetails(int actual, int expected, String message, Log log) {
		try {
			if (actual == expected) {
				log.ReportEvent("PASS", String.format("%s | Actual: '%d', Expected: '%d' - Values match.", message, actual, expected));
			} else {
				log.ReportEvent("FAIL", String.format("%s | Actual: '%d', Expected: '%d' - Values do not match.", message, actual, expected));
				Assert.fail("Validation Failed: " + message);
			}
		} catch (Exception e) {
			log.ReportEvent("FAIL", String.format("%s | Actual: '%d', Expected: '%d' - Exception during comparison.", message, actual, expected));
			e.printStackTrace();
			Assert.fail("Exception during validation: " + message);
		}
	}

	//Method to select Department dropdown
	public void selectDepartment()
	{
		driver.findElement(By.xpath("//*[contains(@class,'tg-projectdepartmentid')]//div[contains(@class,'tg-select__control')]")).click();
		driver.findElement(By.xpath("//div[@role='option']")).click();
	}

	//Method to select Project dropdown
	public void selectProject()
	{
		driver.findElement(By.className("tg-projectcostcenterid")).click();
		driver.findElement(By.xpath("//div[@role='option']")).click();
	}

	//Method to select CostCenter dropdown
	public void selectCostcenter()
	{
		driver.findElement(By.className("tg-projectid")).click();
		driver.findElement(By.xpath("//div[@role='option']")).click();
	}

	//Method to Click On AirLine
	public String clickOnParticularAirline(String[] names) {
		String selectedFlight = null;

		for (String flightName : names) {
			try {
				// XPath tries to locate the checkbox element for the given flight name
				WebElement checkboxLabel = driver.findElement(By.xpath(
						"//span[contains(@class,'tg-airline')]//following::span[contains(text(),'" + flightName + "')]/preceding::span[contains(@class,'tg-airline')][1]"));

				// Click the checkbox if found
				checkboxLabel.click();
				selectedFlight = flightName;
				System.out.println("✅ Selected flight: " + flightName);
				break;  // Exit after selecting the first available flight
			} catch (NoSuchElementException e) {
				System.out.println("⚠️ Flight not found: " + flightName + ", trying next...");
			} catch (Exception e) {
				System.out.println("❌ Error while trying to click flight: " + flightName + " → " + e.getMessage());
			}
		}
		// If none of the flights were found
		if (selectedFlight == null) {
			System.out.println("❌ None of the given flights were found: " + Arrays.toString(names));
			Assert.fail("No matching airline found from the list: " + Arrays.toString(names));
		}

		return selectedFlight;
	}


	//Method to Validate Check in Baggage and Cabin Baggage Details
	public void validateAllBaggageDetailsForAllAirline(String flightName, Log log, ScreenShots screenShots) {
		List<WebElement> fareElements;
		List<WebElement> cabinBags;
		List<WebElement> checkinBags;

		try {
			fareElements = driver.findElements(By.className("tg-fare-type"));
			cabinBags = driver.findElements(By.className("tg-fare-cabinbag"));
			checkinBags = driver.findElements(By.className("tg-fare-checkinbag"));
		} catch (Exception e) {
			log.ReportEvent("FAIL", "Failed to locate UI elements: " + e.getMessage());
			return;
		}

		if (fareElements.isEmpty() || cabinBags.isEmpty() || checkinBags.isEmpty()) {
			log.ReportEvent("INFO", "No fare or baggage details found on UI.");
			return;
		}

		int totalFares = Math.min(fareElements.size(), Math.min(cabinBags.size(), checkinBags.size()));

		log.ReportEvent("INFO", "Starting baggage validation for " + totalFares + " fares under airline: " + flightName);

		for (int i = 0; i < totalFares; i++) {
			try {
				WebElement fareElement = fareElements.get(i);
				String fareName = fareElement.getAttribute("data-tgflfaretype");

				// Fallback to text if attribute is missing
				if (fareName == null || fareName.trim().isEmpty()) {
					fareName = fareElement.getText().trim();
				}

				if (fareName == null || fareName.isEmpty()) {
					log.ReportEvent("WARN", "Skipping fare at index " + i + " due to missing fare name.");
					continue;
				}
				String cabinBaggage = cabinBags.get(i).getText().trim();
				String checkinBaggage = checkinBags.get(i).getText().trim();

				BaggageDetails expected = getBaggageDetailsManual(flightName, fareName);
				BaggageDetails actual = new BaggageDetails(cabinBaggage, checkinBaggage);

				log.ReportEvent("INFO", "Fare: " + fareName);
				log.ReportEvent("INFO", "Expected → " + expected);
				log.ReportEvent("INFO", "Actual   → " + actual);

				if (expected == null || actual == null) {
					log.ReportEvent("FAIL", "Missing baggage data for fare: " + fareName);
					continue;
				}
				boolean isCabinMatch = expected.cabinBaggage.equalsIgnoreCase(actual.cabinBaggage);
				boolean isCheckinMatch = expected.checkinBaggage.equalsIgnoreCase(actual.checkinBaggage);

				if (isCabinMatch && isCheckinMatch) {
					log.ReportEvent("PASS", "✅ Baggage validation passed for fare: " + fareName);
				} else {
					log.ReportEvent("FAIL", "❌ Baggage validation failed for fare: " + fareName);
					if (!isCabinMatch) {
						log.ReportEvent("FAIL", "Cabin mismatch: Expected = " + expected.cabinBaggage + ", Found = " + actual.cabinBaggage);
					}
					if (!isCheckinMatch) {
						log.ReportEvent("FAIL", "Check-in mismatch: Expected = " + expected.checkinBaggage + ", Found = " + actual.checkinBaggage);
					}
				}

			} catch (Exception e) {
				log.ReportEvent("FAIL", "Exception during validation at index " + i + ": " + e.getMessage());
			}
		}
	}

	//Method to getErrorMessageText
	public String getErrorMessage()
	{
		String errorMessage=driver.findElement(By.xpath("//span[@id='client-snackbar']")).getText();
		return errorMessage;
	}

	//Method to Validate Title Error Message
	public void validateTittleErrorMessage(String errorMessage,Log log)
	{
		String message=getErrorMessage();
	    ValidateActualAndExpectedValuesForFlights(message, errorMessage, "Tittle Error Message", log);
	}

	//Method to Validate First Name Error Message
	public void validateFirstNameErrorMessage(String errorMessage,Log log)
	{
		String message=getErrorMessage();
		ValidateActualAndExpectedValuesForFlights(message, errorMessage, "FirstName Error Message", log);
	}
	//Method to Validate Title Error Message
	public void webClear(String xpath)
	{
		WebElement fieldName = driver.findElement(By.xpath(xpath));
		fieldName.click();
		fieldName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		fieldName.sendKeys(Keys.DELETE); // This ensures full clearing
		fieldName.sendKeys(Keys.TAB);
	}

	//Method to Select Title DropDown
	public void selectTitleDropDown(String titleNames,int titleIndex) throws InterruptedException {
		WebElement titleDropDown = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbpaxtitile')])["+titleIndex+"]"));
		titleDropDown.click();
		driver.findElement(By.xpath("//li[@data-value='"+titleNames+"']")).click();
		Thread.sleep(1000);
	}

	//Method to Validate Last Name Error Message
	public void validateLastNameErrorMessage(String errorMessage,Log log)
	{
		String message=getErrorMessage();
		ValidateActualAndExpectedValuesForFlights(message, errorMessage, "LastName Error Message", log);
	}

	//Method to Enter FirstName
	public void enterFirstName(String firstName,int FirstNameIndex)
	{
		WebElement firstNameField = driver.findElement(By.xpath("(//input[@name='firstname'])["+FirstNameIndex+"]"));
		firstNameField.sendKeys(firstName);
	}

	//Method to Validate Last Name Error Message
	public void validatePhoneNumberErrorMessage(String errorMessage,Log log)
	{
		String message=getErrorMessage();
		ValidateActualAndExpectedValuesForFlights(message, errorMessage, "Phone Number Error Message", log);
	}
	//Method to Validate Last Name Error Message
	public void validateEmailErrorMessage(String errorMessage,Log log)
	{
		String message=getErrorMessage();
		ValidateActualAndExpectedValuesForFlights(message, errorMessage, "Email Error Message", log);
	}
}





