package com.tripgain.collectionofpages;

import com.tripgain.common.Log;
import com.tripgain.common.ScreenShots;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tripgain_RoundTripResultScreen {
	WebDriver driver;

	public Tripgain_RoundTripResultScreen(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	//Method to Validate Flights Details Displayed On Result Screen for International
	public void verifyFlightsDetailsOnResultScreenForInternational(Log Log, ScreenShots ScreenShots) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@class,'tg-intlonward-flightcarrier')])[1]")));
			Thread.sleep(3000);
			WebElement priceDetails = driver.findElement(By.xpath("(//*[contains(@class,'tg-intlonward-price')])[1]"));
			WebElement FlightTitleDetails = driver.findElement(By.xpath("(//*[contains(@class,'tg-intlonward-flightcarrier')])[1]"));
			WebElement departureDetails = driver.findElement(By.xpath("(//*[contains(@class,'tg-intlonward-deptime')])[1]"));
			WebElement arrivalDetails = driver.findElement(By.xpath("(//*[contains(@class,'tg-intlonward-arrtime')])[1]"));
			WebElement durationDetails = driver.findElement(By.xpath("(//*[contains(@class,'tg-intonwardl-duration')])[1]"));
			Thread.sleep(3000);
			if (priceDetails.isDisplayed() && FlightTitleDetails.isDisplayed() && departureDetails.isDisplayed() && arrivalDetails.isDisplayed() && durationDetails.isDisplayed()) {
				Log.ReportEvent("PASS", "Flights Details are displayed in Result Screen Successful");
				ScreenShots.takeScreenShot();
			} else {
				Log.ReportEvent("FAIL", "Flights Details Not displayed in Result Screen");
				ScreenShots.takeScreenShot();
				Assert.fail();
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (driver.findElement(By.xpath("//*[@data-testid='AirplaneIcon']")).isDisplayed()) {
				Log.ReportEvent("FAIL", "Flights are Not displayed in Result Screen");
				ScreenShots.takeScreenShot();
				Assert.fail();
			}
		}

	}

	//Method to Select flight based on Index
	public void clickOnSelectFlightBasedOnIndex(int index) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[text()='Select'])[" + index + "]")).click();
		Thread.sleep(3000);

	}

	//Method to validate flights stops on Result Screen for InterNational
	public void validateFlightsStopsOnResultScreen(String numberOfStops, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);
			ArrayList flightsData = new ArrayList();
			List<WebElement> airlineStopsList = driver.findElements(By.className("tg-intlonward-stops"));
			airlineStopsList.size();
			if (airlineStopsList.size() > 0) {
				for (WebElement airlineStops : airlineStopsList) {
					String airlineStopsText = airlineStops.getText();
					System.out.println(airlineStopsText);
					flightsData.add(airlineStopsText);
					ValidateActualAndExpectedValuesForFlights(numberOfStops, airlineStopsText, "Stops Searched based on User", Log);
				}
			} else {
				Log.ReportEvent("FAIL", "No Flights are displayed on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
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

	//Method to Validate Selected Currency is Displayed on Result Screen
	public void validateCurrencyOnResultScreen(String currencyValue, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(6000);
			List<WebElement> currencyTexts = driver.findElements(By.className("tg-intlonward-othercurrency"));
			for (WebElement currencyText : currencyTexts) {
				System.out.println(currencyText.getText());
				currencyText.getText().contains(currencyValue);
			}
			String currencyData = currencyTexts.get(0).getText();
			String currencyCode = currencyData.substring(0, 3);
			System.out.println(currencyCode);
			System.out.println(currencyValue);

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

	//Method to Click on Filters on Result Screen
	public void clickOnFiltersOnResultScreen(String filterName) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//small[text()='" + filterName + "']")).click();
	}

	//Method to Validate AirLine Filter for Local to International and International to International
	public void validateAirlineFilterForInternationalToLocalAndInternationalToInternationalFlights(Log Log, ScreenShots ScreenShots, String order) {
		try {
			int index = 1;
			ArrayList flightsData = new ArrayList();
			int airlineCount = driver.findElements(By.xpath("//small[@data-tgflcarriername]")).size();
			for (int i = 0; i < airlineCount; i++) {
				WebElement airlineList = driver.findElement(By.xpath("(//small[@data-tgflcarriername])[" + index + "]"));
				index = index + 2;
				String airlineText = airlineList.getText();
				System.out.println(airlineText);
				flightsData.add(airlineText);
				if (index > airlineCount) {
					break;
				}
			}
			System.out.println(flightsData);
			boolean isSorted = isSortedAlphabeticallyAirlines(flightsData, order);
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
			int index = 1;
			ArrayList<String> flightsDepartureData = new ArrayList();
			int airlineDepartureCount = driver.findElements(By.xpath("//h6[@data-tgfldeptime]")).size();
			for (int i = 0; i < airlineDepartureCount; i++) {
				WebElement airlineDepartureList = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[" + index + "]"));
				String airlineDepartureText = airlineDepartureList.getText();
				System.out.println(airlineDepartureText);
				flightsDepartureData.add(airlineDepartureText);
				index = index + 2;
				if (index > airlineDepartureCount) {
					break;
				}
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

	//Method to Check Arrival Time is Sorting In Result Screen
	public void validateArrivalTimeSortFunctionalityOnResultScreen(Log Log, ScreenShots ScreenShots, String order) {
		try {
			int index = 1;
			ArrayList<String> flightsArrivalData = new ArrayList();
			int airlineArrivalCount = driver.findElements(By.xpath("//h6[@data-tgflarrtime]")).size();
			for (int i = 0; i < airlineArrivalCount; i++) {
				WebElement airlineArrivalList = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[" + index + "]"));
				index = index + 2;
				String airlineArrivalText = airlineArrivalList.getText();
				System.out.println(airlineArrivalText);
				flightsArrivalData.add(airlineArrivalText);
				if (index > airlineArrivalCount) {
					break;
				}
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


	//Method to Check Duration Time is Sorting In Result Screen
	public void validateDurationTimeSortFunctionalityOnResultScreen(Log Log, ScreenShots ScreenShots, String order) {
		try {
			int index = 1;
			ArrayList<String> flightsDurationData = new ArrayList();
			int airlineDurationCount = driver.findElements(By.xpath("//h6[@data-tgfltotaljduration]")).size();
			for (int i = 0; i < airlineDurationCount; i++) {
				WebElement airlineDurationList = driver.findElement(By.xpath("(//h6[@data-tgfltotaljduration])[" + index + "]"));
				index = index + 2;
				String airlineDurationText = airlineDurationList.getText();
				System.out.println(airlineDurationText);
				flightsDurationData.add(airlineDurationText);
				if (index > airlineDurationCount) {
					break;
				}
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


	//Method to Check Price is Sorting In Result Screen
	public void validatePriceSortFunctionalityOnResultScreen(Log Log, ScreenShots ScreenShots, String order) {
		try {
			ArrayList<Integer> flightsPriceData = new ArrayList();
			List<WebElement> airlinePrices = driver.findElements(By.xpath("//h6[@data-tgflprice]"));
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

	//Method to get Other Country Price Value
	public String getOtherCountryPriceValue(int index) {
		String priceValue = driver.findElement(By.xpath("(//*[contains(@class,'tg-intlonward-othercurrency')])[" + index + "]")).getText();
		System.out.println(priceValue);
		return priceValue;
	}

	//Method to get Indian Country Price Value
	public String getIndianCountryPriceValue(int index) {
		String priceValue = driver.findElement(By.xpath("(//*[contains(@class,'tg-intlonward-price')])[" + index + "]")).getText();
		System.out.println(priceValue);
		return priceValue;
	}

	//Method to Click on Continue Button on Continue Flight Booking Popup
	public void clickOnContinueBookingFlightPopup() throws InterruptedException {
		driver.findElement(By.xpath("//div[contains(@class,'bottom-container')]//button[text()='Continue']")).click();
		Thread.sleep(1000);
	}

	//Method to Click on Select Reasons Popup
	public void clickOnSelectRegionPopup(String reason) throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='" + reason + "']")).click();
		Thread.sleep(1000);
	}

	//Method to Click on Proceed Booking
	public void clickOnProceedBooking() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Proceed to Booking']")).click();
		Thread.sleep(1000);
	}

	//Method to get the Price From Continue Popup
	public String getPriceValueFromContinuePopup() {
		String priceValue = driver.findElement(By.xpath("//div[contains(@class,'bottom-container')]//button[contains(@class,'btn-link')]/parent::div//h6")).getText();
		return priceValue;
	}

	//Method to Validate data after selection of Flight
	public void validateDataAfterSelectingFlight(Log Log, ScreenShots ScreenShots, String priceValue) {
		try {
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

			String fromCode = driver.findElement(By.xpath("((//div[@class='bb-flight-details'])[1]//h6)[1]//small")).getText();
			String toCode = driver.findElement(By.xpath("((//div[@class='bb-flight-details'])[1]//h6)[3]//small")).getText();

			String returnFromCode = driver.findElement(By.xpath("((//div[@class='bb-flight-details'])[2]//h6)[1]//small")).getText();
			String returnToCode = driver.findElement(By.xpath("((//div[@class='bb-flight-details'])[2]//h6)[3]//small")).getText();

			String priceValueOnPopup = getPriceValueFromContinuePopup();
			Assert.assertEquals(priceValue, priceValueOnPopup);
			if (fromCode.contentEquals(fromLocationCode) && toCode.contentEquals(toLocationCode) && returnFromCode.contentEquals(toLocationCode) && returnToCode.contentEquals(fromLocationCode)) {
				Log.ReportEvent("PASS", "Flights From and To Locations is displaying Same");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Flights From and To Locations are Not displaying Same");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights From and To Locations are Not displaying Same" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();


		}

	}

	//Method to validate flights stops on Result Screen for InterNational By UnSelecting Filter
	public void validateFlightsStopsOnResultScreenByUnSelectingFilter(Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);
			String zeroStops = "Nonstop";
			String oneStop = "1 stops";
			String twoPlusStops = "2 stops";
			ArrayList flightsData = new ArrayList();
			List<WebElement> airlineCount = driver.findElements(By.className("tg-intlonward-stops"));
			if (airlineCount.size() == 0) {
				Log.ReportEvent("FAIL", "No Flights are Available on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
			boolean value = true;
			for (WebElement airlineStops : airlineCount) {
				String airlineStopsText = airlineStops.getText();
				System.out.println(airlineStopsText);
				flightsData.add(airlineStopsText);

				if (zeroStops.contentEquals(airlineStopsText) || oneStop.contentEquals(airlineStopsText) || twoPlusStops.contentEquals(airlineStopsText)) {
					System.out.println("Stops as Been Displayed Based On User Search is Successful" + airlineStopsText);
					value = true;
				} else {
					System.out.println("Stops as Been Not Displayed Based On User Search is Successful" + airlineStopsText);
					value = false;
					Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search" + airlineStopsText);
					Assert.fail();
				}
			}

			if (value = true) {
				Log.ReportEvent("PASS", "Flights displayed based on User Search For Stops is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}

	}

	//Method to validate flights departure time on result screen
	public void validateFlightsDepartureTimeOnResultScreen(int flightStartTime, int flightStartTime1, int flightEndTime, int flightEndTime1, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);
			ArrayList<String> flightsDepartureData = new ArrayList();
			int airlineDepartureCount = driver.findElements(By.className("tg-intlonward-deptime")).size();
			if (airlineDepartureCount == 0) {
				Log.ReportEvent("FAIL", "No Flights are Available on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
			for (int i = 0; i < airlineDepartureCount; i++) {
				Thread.sleep(1000);
				WebElement airlineDepartureList = driver.findElement(By.className("tg-intlonward-deptime"));
				String airlineDepartureText = airlineDepartureList.getText();
				System.out.println(airlineDepartureText);
				flightsDepartureData.add(airlineDepartureText);
				// Define the range
				LocalTime startTime = LocalTime.of(flightStartTime, flightStartTime1);  // 00:00
				LocalTime endTime = LocalTime.of(flightEndTime, flightEndTime1);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				LocalTime timeToCheck = LocalTime.parse(airlineDepartureText);
				if (!(flightStartTime == 18)) {
					// Check if timeToCheck is between startTime and endTime
					if (!timeToCheck.isBefore(startTime) && timeToCheck.isBefore(endTime)) {

						System.out.println("Flights displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime + " " + "is Successful");

					} else {
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				} else {
					// Check if timeToCheck is between startTime and endTime
					if (!timeToCheck.isBefore(startTime) && !timeToCheck.isBefore(endTime)) {

						System.out.println("Flights displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime + " " + "is Successful");
					} else {
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				}
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}


	//Method to validate flights Arrival time on result screen
	public void validateFlightsArrivalTimeOnResultScreen(int flightStartTime, int flightStartTime1, int flightEndTime, int flightEndTime1, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);
			ArrayList<String> flightsArrivalData = new ArrayList();
			int airlineArrivalCount = driver.findElements(By.className("tg-intlonward-arrtime")).size();
			if (airlineArrivalCount == 0) {
				Log.ReportEvent("FAIL", "No Flights are Available on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
			for (int i = 0; i < airlineArrivalCount; i++) {
				Thread.sleep(500);
				WebElement airlineArrivalList = driver.findElement(By.className("tg-intlonward-arrtime"));
				String airlineArrivalText = airlineArrivalList.getText();
				System.out.println(airlineArrivalText);
				flightsArrivalData.add(airlineArrivalText);

				// Define the range
				LocalTime startTime = LocalTime.of(flightStartTime, flightStartTime1);  // 00:00
				LocalTime endTime = LocalTime.of(flightEndTime, flightEndTime1);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				LocalTime timeToCheck = LocalTime.parse(airlineArrivalText);
				if (!(flightStartTime == 18)) {
					// Check if timeToCheck is between startTime and endTime
					if (!timeToCheck.isBefore(startTime) && timeToCheck.isBefore(endTime)) {

						System.out.println("Flights displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime + " " + "is Successful");

					} else {
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				} else {
					// Check if timeToCheck is between startTime and endTime
					if (!timeToCheck.isBefore(startTime) && !timeToCheck.isBefore(endTime)) {

						System.out.println("Flights displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime + " " + "is Successful");
					} else {
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				}
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}

	//Method to validate flights Return Departure time on result screen
	public void validateReturnDepartureTimeOnResultScreen(int flightStartTime, int flightStartTime1, int flightEndTime, int flightEndTime1, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);
			ArrayList<String> flightsReturnDepartureData = new ArrayList();
			int airlineCount = driver.findElements(By.className("tg-intlreturn-deptime")).size();
			if (airlineCount == 0) {
				Log.ReportEvent("FAIL", "No Flights are Available on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
			for (int i = 0; i < airlineCount; i++) {
				Thread.sleep(1000);
				WebElement airlineReturnDepartureTimeList = driver.findElement(By.className("tg-intlreturn-deptime"));
				String airlineReturnDepartureText = airlineReturnDepartureTimeList.getText();
				System.out.println(airlineReturnDepartureText);
				flightsReturnDepartureData.add(airlineReturnDepartureText);

				// Define the range
				LocalTime startTime = LocalTime.of(flightStartTime, flightStartTime1);  // 00:00
				LocalTime endTime = LocalTime.of(flightEndTime, flightEndTime1);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				LocalTime timeToCheck = LocalTime.parse(airlineReturnDepartureText);
				if (!(flightStartTime == 18)) {
					// Check if timeToCheck is between startTime and endTime
					if (!timeToCheck.isBefore(startTime) && timeToCheck.isBefore(endTime)) {

						System.out.println("Flights displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime + " " + "is Successful");

					} else {
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				} else {
					// Check if timeToCheck is between startTime and endTime
					if (!timeToCheck.isBefore(startTime) && !timeToCheck.isBefore(endTime)) {

						System.out.println("Flights displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime + " " + "is Successful");
					} else {
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				}
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}

	// Method to Select Return Depart TIME
	public void selectReturnDepartTime(String time) {
		try {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//legend[text()='RETURN DEPART TIME']//parent::div//small[text()='" + time + "']")).click();
			Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println("Failed to click on RETURN DEPART TIME: " + e.getMessage());
			e.printStackTrace();
		}
	}

	//method to Validate Onward Depart Time is Selected
	public void validateReturnDepartureTimeIsSelected(Log Log, ScreenShots ScreenShots, String... returnDepartureTimes) {
		try {
			for (String departureTime : returnDepartureTimes) {
				Thread.sleep(3000);
				WebElement selected = driver.findElement(By.xpath("(//div[contains(@class,'MuiGrid2-root') and contains(@class,'css-1gnvfsz')])[2]//div[contains(@class,'frc-deptime')]"));
				if (selected.isDisplayed()) {
					Log.ReportEvent("PASS", "" + departureTime + " " + "Return Departure Time is Selected on Result Screen");
				} else {
					Log.ReportEvent("FAIL", "" + departureTime + " " + "Return Departure Time is Not Selected on Result Screen");
					ScreenShots.takeScreenShot1();
				}
			}
			ScreenShots.takeScreenShot1();
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Return Departure Time is Not Selected on Result Screen");
			ScreenShots.takeScreenShot1();
		}

	}

	// Method to Select Return Arrival TIME
	public void selectReturnArrivalTime(String time) {
		try {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//legend[text()='RETURN ARRIVAL TIME']//parent::div//small[text()='" + time + "']")).click();
			Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println("Failed to click on RETURN DEPART TIME: " + e.getMessage());
			e.printStackTrace();
		}
	}


	//Method to validate flights Return Arrival time on result screen
	public void validateReturnArrivalTimeOnResultScreen(int flightStartTime, int flightStartTime1, int flightEndTime, int flightEndTime1, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);
			ArrayList<String> flightsReturnDepartureData = new ArrayList();
			int airlineCount = driver.findElements(By.className("tg-intlreturn-arrtime")).size();
			if (airlineCount == 0) {
				Log.ReportEvent("FAIL", "No Flights are Available on User Search");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
			for (int i = 0; i < airlineCount; i++) {
				Thread.sleep(1000);
				WebElement airlineReturnArrivalTimeList = driver.findElement(By.className("tg-intlreturn-arrtime"));
				String airlineReturnArrivalText = airlineReturnArrivalTimeList.getText();
				System.out.println(airlineReturnArrivalText);
				flightsReturnDepartureData.add(airlineReturnArrivalText);

				// Define the range
				LocalTime startTime = LocalTime.of(flightStartTime, flightStartTime1);  // 00:00
				LocalTime endTime = LocalTime.of(flightEndTime, flightEndTime1);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				LocalTime timeToCheck = LocalTime.parse(airlineReturnArrivalText);
				if (!(flightStartTime == 18)) {
					// Check if timeToCheck is between startTime and endTime
					if (!timeToCheck.isBefore(startTime) && timeToCheck.isBefore(endTime)) {

						System.out.println("Flights displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime + " " + "is Successful");

					} else {
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				} else {
					// Check if timeToCheck is between startTime and endTime
					if (!timeToCheck.isBefore(startTime) && !timeToCheck.isBefore(endTime)) {

						System.out.println("Flights displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime + " " + "is Successful");
					} else {
						Log.ReportEvent("FAIL", "Flights are Not displayed based on User Searched between Start Time " + " " + startTime + " " + "and End Time" + " " + endTime);
						ScreenShots.takeScreenShot1();
						Assert.fail();
					}
				}
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to Validate data after selection of Flight on Result Screen
	public void validateDataAfterSelectingFlight(Log Log, ScreenShots ScreenShots, String departureIndex, String returnDepartureIndex, String arrivalIndex, String returnArrivalIndex, String priceIndex) {
		try {
			String departingDepartureTime = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[" + departureIndex + "]")).getText();
			String returnDepartingDepartureTime = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[" + returnDepartureIndex + "]")).getText();
			String arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[" + arrivalIndex + "]")).getText();
			String returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[" + returnArrivalIndex + "]")).getText();

			String price = driver.findElement(By.xpath("(//h6[@data-tgflprice])[" + priceIndex + "]")).getText();
			String bookingPrice = driver.findElement(By.xpath("//div[contains(@class,'bottom-container')]//h6[@data-tgflprice]")).getText();


			String departingDepartureTimePopup = driver.findElement(By.xpath("//span[@data-tgfldeptime]")).getText();
			String arrivalTimePopup = driver.findElement(By.xpath("//span[@data-tgflarrtime]")).getText();
			String returnDepartingDepartureTimePopup = driver.findElement(By.xpath("//span[@data-tgfltodeptime]")).getText();
			String returnArrivalTimePopup = driver.findElement(By.xpath("//span[@data-tgfltoarrtime]")).getText();
			String departingFrom = driver.findElement(By.xpath("//small[@data-tgflorigin]")).getText();
			String departingTo = driver.findElement(By.xpath("//small[@data-tgfldestination]")).getText();
			String returnFrom = driver.findElement(By.xpath("//small[@data-tgfltoorigin]")).getText();
			String returnTo = driver.findElement(By.xpath("//small[@data-tgfltodestination]")).getText();

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

			if (fromLocationCode.contentEquals(departingFrom) && toLocationCode.contentEquals(departingTo) && fromLocationCode.contentEquals(returnTo) && toLocationCode.contentEquals(returnFrom)) {
				if (departingDepartureTime.contains(departingDepartureTimePopup) && returnDepartingDepartureTime.contentEquals(returnDepartingDepartureTimePopup) && arrivalTime.contentEquals(arrivalTimePopup) && returnArrivalTime.contentEquals(returnArrivalTimePopup) && price.contentEquals(bookingPrice)) {
					Log.ReportEvent("PASS", "Flights Price, Arrival and Departure Time is displaying Same");
					ScreenShots.takeScreenShot1();
				} else {
					Log.ReportEvent("FAIL", "Flights Price, Arrival and Departure Time is displaying are Not Same");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			} else {
				clickOnContinueBookingFlightPopup();
				String origenCode = driver.findElement(By.xpath("(//h2[text()='Airport Change']/parent::div//strong)[1]")).getText();
				String destinationCode = driver.findElement(By.xpath("(//h2[text()='Airport Change']/parent::div//strong)[2]")).getText();

				if (origenCode.contains(fromLocationCode) && destinationCode.contentEquals(toLocationCode)) {
					Log.ReportEvent("PASS", "Flights Price, Arrival and Departure Time is displaying Same");
					ScreenShots.takeScreenShot1();
					cancelButtonPoPup();
				} else {
					Log.ReportEvent("FAIL", "Flights Price, Arrival and Departure Time is displaying are Not Same");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights Price, Arrival and Departure Time is displaying Same" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}

	}

	//Method to Click on Cancel Button On Popup
	public void cancelButtonPoPup() {
		driver.findElement(By.xpath("//button[text()='No, Cancel']")).click();
	}



	//Method to validate flights OnWord Departure Time on Result Screen for InterNational By UnSelecting Filter
	public void validateFlightsOnWordDepartureTimeOnResultScreenByUnSelectingFilter(Log Log, ScreenShots ScreenShots, String time) {
		try {
			Thread.sleep(5000);
			boolean isSelected = driver.findElement(By.xpath("//legend[text()='ONWARD DEPART TIME']//parent::div//small[text()='" + time + "']/parent::div[not(@class='selected-filter')]")).isDisplayed();
			int index = 1;
			int departureTime = driver.findElements(By.xpath("//h6[@data-tgfldeptime]")).size();
			for (int i = 0; i < departureTime; i++) {
				String departureTimings = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[" + index + "]")).getText();
				System.out.println(departureTimings);
				index = index + 2;
				if (index > departureTime) {
					break;
				}
			}
			if (isSelected = true) {
				Log.ReportEvent("PASS", "OnWard Departure Time is deSelected is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "OnWard Departure Time is Selected");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "OnWard Departure Time is Selected");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}
	}

	//Method to validate flights OnWord Arrival Time on Result Screen for InterNational By UnSelecting Filter
	public void validateFlightsOnWordArrivalTimeOnResultScreenByUnSelectingFilter(Log Log, ScreenShots ScreenShots, String time) {
		try {
			Thread.sleep(5000);
			boolean isSelected = driver.findElement(By.xpath("//legend[text()='ONWARD ARRIVAL TIME']//parent::div//small[text()='" + time + "']/parent::div[not(@class='selected-filter')]")).isDisplayed();
			int index = 1;
			int arrivalTime = driver.findElements(By.xpath("//h6[@data-tgflarrtime]")).size();
			for (int i = 0; i < arrivalTime; i++) {
				String arrivalTimings = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[" + index + "]")).getText();
				System.out.println(arrivalTimings);
				index = index + 2;
				if (index > arrivalTime) {
					break;
				}
			}
			if (isSelected = true) {
				Log.ReportEvent("PASS", "OnWard Arrival Time is deSelected is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "OnWard Arrival Time is Selected");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "OnWard Arrival Time is Selected");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}
	}


	//Method to validate flights Return Departure Time on Result Screen for InterNational By UnSelecting Filter
	public void validateFlightsReturnDepartureTimeOnResultScreenByUnSelectingFilter(Log Log, ScreenShots ScreenShots, String time) {
		try {
			Thread.sleep(5000);
			boolean isSelected = driver.findElement(By.xpath("//legend[text()='RETURN DEPART TIME']//parent::div//small[text()='" + time + "']/parent::div[not(@class='selected-filter')]")).isDisplayed();
			int index = 2;
			int returnDepartureTime = driver.findElements(By.xpath("//h6[@data-tgfldeptime]")).size();
			for (int i = 0; i < returnDepartureTime; i++) {
				String departureTimings = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[" + index + "]")).getText();
				System.out.println(departureTimings);
				index = index + 2;
				if (index > returnDepartureTime) {
					break;
				}
			}
			if (isSelected = true) {
				Log.ReportEvent("PASS", "Return Departure Time is deSelected is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Return Departure Time is Selected");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Return Departure Time is Selected");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}
	}

	//Method to validate flights Return Arrival Time on Result Screen for InterNational By UnSelecting Filter
	public void validateFlightsReturnArrivalTimeOnResultScreenByUnSelectingFilter(Log Log, ScreenShots ScreenShots, String time) {
		try {
			Thread.sleep(5000);
			boolean isSelected = driver.findElement(By.xpath("//legend[text()='RETURN ARRIVAL TIME']//parent::div//small[text()='" + time + "']/parent::div[not(@class='selected-filter')]")).isDisplayed();
			int index = 2;
			int returnArrivalTime = driver.findElements(By.xpath("//h6[@data-tgflarrtime]")).size();
			for (int i = 0; i < returnArrivalTime; i++) {
				String returnArrivalTimings = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[" + index + "]")).getText();
				System.out.println(returnArrivalTimings);
				index = index + 2;
				if (index > returnArrivalTime) {
					break;
				}
			}
			if (isSelected = true) {
				Log.ReportEvent("PASS", "Return Arrival Time is deSelected is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Return Arrival Time is Selected");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Return Arrival Time is Selected");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}
	}

	//Method to Get List of AirLines on Result Screen
	public ArrayList getListOfAirlinesOnResultScreen() {
		List<WebElement> airlineList = driver.findElements(By.xpath("//legend[text()='AIRLINES']/parent::div//li//div/span"));
		airlineList.size();
		ArrayList names = new ArrayList();

		for (WebElement airlineText : airlineList) {
			String name = airlineText.getText();
			names.add(name);
			System.out.println(name);
		}
		return names;
	}

	// Method to Validate Airline List on Result Screen
	public void validateAirLinesListDisplayedBasedOnUserSearch(Log Log, ScreenShots ScreenShots, String... airlineNames) {
		try {
			// Get all airlines displayed on the result page
			List<WebElement> airlinesList = driver.findElements(By.className("tg-intlonward-flightcarrier"));
			List<String> displayedAirlines = new ArrayList<>();

			for (WebElement airlineElement : airlinesList) {
				String airline = airlineElement.getText().trim();
				String airlineText = airline.split("\\(")[0].trim(); // Remove text inside parentheses
				displayedAirlines.add(airlineText);
			}

			// Log all displayed airlines
			System.out.println("Displayed Airlines:");
			displayedAirlines.forEach(System.out::println);

			// Check if any expected airline is present in displayedAirlines
			boolean anyMatch = Arrays.stream(airlineNames)
					.map(String::trim)
					.anyMatch(displayedAirlines::contains);

			if (anyMatch) {
				Log.ReportEvent("PASS", "At least one of the expected airlines is shown in the results.");
			} else {
				Log.ReportEvent("FAIL", "None of the expected airlines were found in the results.");
				Assert.fail("Expected airline(s) not found.");
			}

			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception Occurred While Validating Airlines List: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail("Exception in airline validation.");
		}
	}


	//Method to Validate InPolicy and OutOfPolicy
	public void validatePolicy(Log Log, ScreenShots ScreenShots,String expectedValue) {
		try {
			// Retrieve all policy elements
			List<WebElement> policies = driver.findElements(By.xpath("//div[@data-tginploicy]"));
			if (policies.size()>0)
			{
				for (WebElement policy : policies) {
					String policyText = policy.getText().trim();
					System.out.println("Policy Text: " + policyText);
					ValidateActualAndExpectedValuesForFlights(policyText, expectedValue, "Flights displaying InPolicy text", Log);
				}

				// Optional: Print each policy text separately to console
				for (WebElement policy : policies) {
					String policyText = policy.getText().trim();
					System.out.println("Policy: " + policyText);
				}
				// Take a screenshot for documentation
				ScreenShots.takeScreenShot1();
			}else{
				Log.ReportEvent("FAIL", "❌ Policy Details are mismatching");
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

	//Method to Validate flights on Result Page
	public void validateFlightsResults(Log Log,ScreenShots ScreenShots)
	{
		try {
			Thread.sleep(15000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='oneway-header']/following-sibling::div")));
			WebElement flights=driver.findElement(By.xpath("//div[@class='oneway-header']/following-sibling::div"));

			if(flights.isDisplayed())
			{
				Log.ReportEvent("PASS", "Flights are displayed based on User Search is Successful");
				ScreenShots.takeScreenShot1();
			}
			else {
				Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search Please Change Filter");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e){
			Log.ReportEvent("FAIL", "Flights are Not displayed based on User Search Please Change Filter");
			ScreenShots.takeScreenShot1();
			Assert.fail();
			e.printStackTrace();
		}
	}

	//Method to verify Flights Details On ResultScreen For Local to Local
	public void verifyFlightsDetailsOnResultScreenForLocal(int index, Log log, ScreenShots screenShots) throws InterruptedException {
		Thread.sleep(5000);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Get user-selected locations
			String selectedFromLocation = driver.findElement(By.xpath("(//div[contains(@class, 'tg-select__input-container')]//parent::div/div)[1]")).getText();
			String selectedToLocation = driver.findElement(By.xpath("(//div[contains(@class, 'app-async-select_container')]//div[contains(@class, 'tg-select__single-value')])[2]")).getText();

			// Extract airport codes
			String expectedFromCode = extractAirportCode(selectedFromLocation);
			String expectedToCode = extractAirportCode(selectedToLocation);

			// Wait for fromStops element and get text
			WebElement fromStopsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//div[@class='carreir-info'])[1]/span[2]")));

			String fromStops = fromStopsElement.getText().trim();
			if (fromStops.isEmpty()) {
				fromStops = fromStopsElement.getAttribute("innerText").trim();
				Assert.fail();
			}

			System.out.println("From Stops: " + fromStops);

			// Onward flight card and details
			WebElement onwardFlightCard = driver.findElement(By.xpath("(//div[@class='round-trip-from-results']//div[contains(@class, 'round-trip-card')])[" + index + "]"));
			WebElement onwardDepartureTitle = onwardFlightCard.findElement(By.xpath(".//div[@class='frc-deptime']//h3"));
			WebElement onwardArrivalTitle = onwardFlightCard.findElement(By.xpath(".//div[@class='frc-arrtime']//h3"));
			WebElement onwardJourneyTime = onwardFlightCard.findElement(By.xpath(".//div[@class='frc-journeytime']//h5"));
			WebElement onwardPrice = onwardFlightCard.findElement(By.xpath(".//div[@class='frc-price']//h3"));
			WebElement onwardCarrierInfo = onwardFlightCard.findElement(By.xpath(".//div[@class='carreir-info']"));

			WebElement viewOnwardButton = onwardFlightCard.findElement(By.xpath(".//button[text()='View Flight']"));
			js.executeScript("arguments[0].scrollIntoView(true);", viewOnwardButton);
			js.executeScript("arguments[0].click();", viewOnwardButton);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));

			String fromResult, toResult, actualFromCode, actualToCode;

			if (fromStops.toLowerCase().contains("nonstop")) {
				fromResult = driver.findElement(By.xpath("//div[@role='dialog']//div[contains(@class,'css-x553i7')]/h6[1]")).getText();
				toResult = driver.findElement(By.xpath("(//h6[contains(@class,'MuiTypography-subtitle1') and contains(@class,'bold')])[4]")).getText();
			} else if (fromStops.toLowerCase().contains("1stops")) {
				fromResult = driver.findElement(By.xpath("//div[@role='dialog']//div[contains(@class,'css-x553i7')]/h6[1]")).getText();
				toResult = driver.findElement(By.xpath("((//div[contains(@class, 'flight-info') and contains(@class, 'MuiGrid2-container')])[2]//h6)[4]")).getText();
			} else if (fromStops.toLowerCase().contains("2stops")) {
				fromResult = driver.findElement(By.xpath("//div[@role='dialog']//div[contains(@class,'css-x553i7')]/h6[1]")).getText();
				toResult = driver.findElement(By.xpath("((//div[contains(@class, 'flight-info') and contains(@class, 'MuiGrid2-container')])[3]//h6)[4]")).getText();
			} else {
				throw new RuntimeException("Unknown stop type in onward flight: " + fromStops);
			}

			actualFromCode = extractAirportCode(fromResult);
			actualToCode = extractAirportCode(toResult);

			Thread.sleep(3000);
			WebElement closeButton1 = driver.findElement(By.xpath("//button[text()='Close']"));
			js.executeScript("arguments[0].click();", closeButton1);
			Thread.sleep(3000);

			// Return flight
			WebElement returnFlightCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//div[@class='round-trip-to-results']//div[contains(@class, 'round-trip-card')])[" + index + "]")));
			WebElement returnDepartureTitle = returnFlightCard.findElement(By.xpath(".//div[@class='frc-deptime']//h3"));
			WebElement returnArrivalTitle = returnFlightCard.findElement(By.xpath(".//div[@class='frc-arrtime']//h3"));
			WebElement returnJourneyTime = returnFlightCard.findElement(By.xpath(".//div[@class='frc-journeytime']//h5"));
			WebElement returnPrice = returnFlightCard.findElement(By.xpath(".//div[@class='frc-price']//h3"));
			WebElement returnCarrierInfo = returnFlightCard.findElement(By.xpath(".//div[@class='carreir-info']"));

			WebElement viewReturnButton = returnFlightCard.findElement(By.xpath(".//button[text()='View Flight']"));
			js.executeScript("arguments[0].scrollIntoView(true);", viewReturnButton);
			js.executeScript("arguments[0].click();", viewReturnButton);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));

			String returnFromResult, returnToResult, returnFromCode, returnToCode;
			String returnStops = driver.findElement(By.xpath("((//div[@class='round-trip-to-results']//div[@class='carreir-info'])[1]//span)[2]")).getText();
			if (returnStops.isEmpty()) {
				returnStops = driver.findElement(By.xpath("((//div[@class='round-trip-to-results']//div[@class='carreir-info'])[1]//span)[2]")).getAttribute("innerText").trim();
			}
			System.out.println("Return Stops: " + returnStops);

			if (returnStops.toLowerCase().contains("nonstop")) {
				returnFromResult = driver.findElement(By.xpath("//div[@role='dialog']//div[contains(@class,'css-x553i7')]/h6[1]")).getText();
				returnToResult = driver.findElement(By.xpath("(//h6[contains(@class,'MuiTypography-subtitle1') and contains(@class,'bold')])[4]")).getText();
			} else if (returnStops.toLowerCase().contains("1stops")) {
				returnFromResult = driver.findElement(By.xpath("//div[@role='dialog']//div[contains(@class,'css-x553i7')]/h6[1]")).getText();
				returnToResult = driver.findElement(By.xpath("((//div[contains(@class, 'flight-info') and contains(@class, 'MuiGrid2-container')])[2]//h6)[4]")).getText();
			} else if (returnStops.toLowerCase().contains("2stops")) {
				returnFromResult = driver.findElement(By.xpath("//div[@role='dialog']//div[contains(@class,'css-x553i7')]/h6[1]")).getText();
				returnToResult = driver.findElement(By.xpath("((//div[contains(@class, 'flight-info') and contains(@class, 'MuiGrid2-container')])[3]//h6)[4]")).getText();
			} else {
				throw new RuntimeException("Unknown stop type in return flight: " + returnStops);
			}

			returnFromCode = extractAirportCode(returnFromResult);
			returnToCode = extractAirportCode(returnToResult);

			Thread.sleep(3000);
			WebElement closeButton2 = driver.findElement(By.xpath("//button[text()='Close']"));
			js.executeScript("arguments[0].click();", closeButton2);

			// Validate visibility and correctness
			boolean onwardVisible = isElementVisible(js, onwardFlightCard, onwardDepartureTitle, onwardArrivalTitle, onwardJourneyTime, onwardPrice, onwardCarrierInfo);
			boolean returnVisible = isElementVisible(js, returnFlightCard, returnDepartureTitle, returnArrivalTitle, returnJourneyTime, returnPrice, returnCarrierInfo);

			if (onwardVisible && returnVisible) {
				boolean locationMatch = actualFromCode.equals(expectedFromCode) &&
						actualToCode.equals(expectedToCode) &&
						returnFromCode.equals(expectedToCode) &&
						returnToCode.equals(expectedFromCode);

				if (locationMatch) {
					log.ReportEvent("PASS", "Flight details visible and correctly displayed.\n" +
							"Expected FROM: " + expectedFromCode + " (" + selectedFromLocation + "), TO: " + expectedToCode + " (" + selectedToLocation + ")\n" +
							"Actual FROM: " + actualFromCode + " (" + fromResult + "), TO: " + actualToCode + " (" + toResult + ")\n" +
							"Return FROM: " + returnFromCode + " (" + returnFromResult + "), TO: " + returnToCode + " (" + returnToResult + ")");
					screenShots.takeScreenShot1();
				} else {
					log.ReportEvent("FAIL", "Mismatch in flight locations.\n" +
							"Expected FROM: " + expectedFromCode + " (" + selectedFromLocation + "), TO: " + expectedToCode + " (" + selectedToLocation + ")\n" +
							"Actual FROM: " + actualFromCode + " (" + fromResult + "), TO: " + actualToCode + " (" + toResult + ")\n" +
							"Return FROM: " + returnFromCode + " (" + returnFromResult + "), TO: " + returnToCode + " (" + returnToResult + ")");
					screenShots.takeScreenShot1();
					Assert.fail("Flight details mismatch.");
				}
			} else {
				log.ReportEvent("FAIL", "One or more flight detail elements are not visible.");
				screenShots.takeScreenShot1();
				Assert.fail("Flight detail elements missing.");
			}

		} catch (Exception e) {
			log.ReportEvent("FAIL", "Exception occurred while verifying flight details: " + e.getMessage());
			screenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail("Exception: " + e.getMessage());
		}
	}

	// Helper method to extract airport code from location string
	private String extractAirportCode(String locationText) {
		Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(locationText);
		return matcher.find() ? matcher.group(1) : locationText;
	}

	// Helper method to check element visibility
	private boolean isElementVisible(JavascriptExecutor js, WebElement... elements) {
		for (WebElement element : elements) {
			boolean visible = (Boolean) js.executeScript(
					"return arguments[0]?.offsetWidth > 0 && arguments[0]?.offsetHeight > 0;", element);
			if (!visible) return false;
		}
		return true;
	}

	//Method to get total Flight Count From And To Before Applying Filter
	public void totalFlightCountFromAndToBeforeApplyingFilter(Log log,ScreenShots screenShots) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
		// Corrected XPath expressions
		String totalCountFrom = driver.findElement(By.xpath("//span[@id='onward_flight_count']")).getText();
		String totalCountTo = driver.findElement(By.xpath("//span[@id='return_flight_count']")).getText();

		// Improved log message formatting
		log.ReportEvent("INFO", "Total Flights Found Before Applying Filter: From = "
				+ totalCountFrom + ", To = " + totalCountTo);
		screenShots.takeScreenShot1();
	}

	//Method to get total Flight Count From And To After Applying Filter
	public void totalFlightCountFromAndToAfterApplyingFilter(Log log,ScreenShots screenShots) throws InterruptedException {
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
		// Corrected XPath expressions
		String totalCountFrom = driver.findElement(By.xpath("//span[@id='onward_flight_count']")).getText();
		String totalCountTo = driver.findElement(By.xpath("//span[@id='return_flight_count']")).getText();

		// Improved log message formatting
		log.ReportEvent("INFO", "Total Flights Found After Applying Filter: From = "
				+ totalCountFrom + ", To = " + totalCountTo);
		screenShots.takeScreenShot1();
	}


	//Method to validate Sme Filter
	public void validateSmeFilter(int index, Log log, ScreenShots screenshots) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		List<String> validFares = Arrays.asList("sme fare", "corporate fare");

		String fromFlightCardXpath = "//div[@class='round-trip-from-results']//div[contains(@class, 'round-trip-card')]";
		String returnFlightCardXpath = "//div[@class='round-trip-to-results']//div[contains(@class, 'round-trip-card')]";

		boolean fromResult = validateFlightCardFares(fromFlightCardXpath, index, validFares, log, screenshots, wait, "From Flight Card");
		boolean returnResult = validateFlightCardFares(returnFlightCardXpath, index, validFares, log, screenshots, wait, "Return Flight Card");

		if (fromResult && returnResult) {
			log.ReportEvent("PASS", "SME filter validation passed for both From and Return flight cards.");
		} else {
			log.ReportEvent("FAIL", "SME filter validation failed.");
		}
	}

	private boolean validateFlightCardFares(String flightCardXpath, int index, List<String> validFares,
											Log log, ScreenShots screenshots, WebDriverWait wait, String cardName) {
		try {
			// Wait and click "View Flight" button for specified card and index
			WebElement viewFlightBtn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("(" + flightCardXpath + "//button[text()='View Flight'])[" + index + "]")));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewFlightBtn);

			try {
				viewFlightBtn.click();
			} catch (Exception e) {
				// Fallback to JS click
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewFlightBtn);
			}

			// Wait for fare elements to appear
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h6[@data-tgfaretype]")));

			List<WebElement> fares = driver.findElements(By.xpath("//h6[@data-tgfaretype]"));

			// Validate each fare text
			for (WebElement fare : fares) {
				String fareText = fare.getText().trim().toLowerCase();
				if (!validFares.contains(fareText)) {
					log.ReportEvent("FAIL", cardName + ": Unexpected fare found: '" + fareText + "'");
					screenshots.takeScreenShot1();
					closePopup();
					return false;
				}
			}

			log.ReportEvent("PASS", cardName + ": Only SME/Corporate fares are displayed.");
			screenshots.takeScreenShot1();
			closePopup();
			return true;

		} catch (Exception e) {
			log.ReportEvent("ERROR", cardName + ": Error during validation - " + e.getMessage());
			screenshots.takeScreenShot1();
			e.printStackTrace();
			return false;
		}
	}

	private void closePopup() {
		try {
			WebElement closeBtn = driver.findElement(By.xpath("//button[text()='Close']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
		} catch (Exception e) {
			// Log or ignore if close button not found; popup might already be closed
		}
	}


	//Method to Validate InPolicy and OutOfPolicy
	public void validatePolicyDetails(Log Log, ScreenShots ScreenShots, String expectedValue) {
		try {
			Thread.sleep(5000);
			// Retrieve all matching policy elements
			List<WebElement> policies = driver.findElements(By.xpath("//div[@data-tginploicy]"));
			Thread.sleep(5000);
			List<String> mismatchedPolicies = new ArrayList<>();
			boolean allMatch = true;

			// Validate each policy text
			for (WebElement policy : policies) {
				Thread.sleep(500);
				String policyText = policy.getText().trim();
				System.out.println("Policy Text: " + policyText);
				if (!policyText.equalsIgnoreCase(expectedValue)) {
					mismatchedPolicies.add(policyText);
					allMatch = false;
				}
			}

			// Logging and screenshots
			if (allMatch) {
				Log.ReportEvent("PASS", "✅ All displayed policies are '" + expectedValue + "'.");
				// Final screenshot
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "❌ Some policies are not '" + expectedValue + "'. Mismatched: " + mismatchedPolicies);
				ScreenShots.takeScreenShot1();
				Assert.fail("One or more policies do not match expected value.");
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "❌ Exception while checking policies: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail("Exception occurred during validation.");
		}
	}
	//Method to select Flight Based On Index From And Return
	public void selectFlightBasedOnIndexFromAndReturn(int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		try {
			// Construct XPath for the 'From' flight selection
			String fromXPath = "(//div[contains(@class, 'round-trip-from-results')]//div[contains(@class, 'MuiCardContent-root') and contains(@class, 'round-trip-card') and contains(@class, 'css-1qw96cp')])[" + index + "]";
			WebElement fromDiv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(fromXPath)));

			// Perform actions on the 'From' flight element
			fromDiv.click();

			// Construct XPath for the 'Return' flight selection
			String returnXPath = "(//div[contains(@class, 'round-trip-to-results')]//div[contains(@class, 'MuiCardContent-root') and contains(@class, 'round-trip-card') and contains(@class, 'css-1qw96cp')])[" + index + "]";
			WebElement returnDiv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(returnXPath)));

			// Perform actions on the 'Return' flight element
			returnDiv.click();

			// Click the 'Continue' button
			WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
			continueButton.click();

		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (ElementNotInteractableException e) {
			System.out.println("Element not interactable: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
	}

	//Method to validate flights departure time on result screen
	public void validateFlightsDepartureTimeOnResultScreenForLocal(int flightStartHour, int flightStartMinute, int flightEndHour, int flightEndMinute, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);

			List<String> flightsDepartureData = new ArrayList<>();
			List<WebElement> airlineDepartureCount = driver.findElements(By.xpath("//div[@class='round-trip-from-results']//div[@ class='frc-deptime']"));

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

	//Method to round Trip click Return Stops
	public void roundTripClickReturnStops(String... stops) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (String stop : stops) {
			String trimmedStop = stop.trim();
			String xpath = "//legend[text()='RETURN STOPS']//parent::div//button[text()='" + trimmedStop + "']";

			WebElement stopButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

			// Scroll into view
			js.executeScript("arguments[0].scrollIntoView(true);", stopButton);

			// Wait until clickable
			wait.until(ExpectedConditions.elementToBeClickable(stopButton));

			// Click via JavaScript to handle overlays
			js.executeScript("arguments[0].click();", stopButton);
		}

	}

	// Method to validate flight stops on result screen
	public void validateFlightsReturnStopsOnResultScreen(String numberOfStops, Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(5000);

			// Locate the stops element
			WebElement onwardLocalStops = driver.findElement(By.xpath(
					"//div[@class='round-trip-to-results']//div[contains(@class,'carreir-info')]//span[contains(@class,'caption') and contains(@class,'bold')][2]"
			));

			// Extract and trim both values
			String actualStops = onwardLocalStops.getText().trim();
			String expectedStops = numberOfStops.trim();

			System.out.println("Actual Stops Text: '" + actualStops + "'");
			System.out.println("Expected Stops Text: '" + expectedStops + "'");

			// Compare
			if (actualStops.equalsIgnoreCase(expectedStops)) {
				Log.ReportEvent("PASS", "Flight stops match the expected value: '" + expectedStops + "'");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Flight stops do not match. Expected: '" + expectedStops + "', Found: '" + actualStops + "'");
				ScreenShots.takeScreenShot1();
				Assert.fail("Mismatch in number of stops");
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception occurred while validating flight stops: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail("Exception during flight stops validation");
		}
	}

	//Method to Validate Check In Baggage Flights
	public void validateCheckInBaggageFlightsOnResultScreenRoundTrip(Log Log, ScreenShots ScreenShots, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Click the correct flight using index
			String xpath = "(//div[@class='round-trip-from-results']//button[text()='View Flight'])[" + index + "]";
			WebElement viewflightbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

			// Scroll into view before clicking
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", viewflightbutton);
			Thread.sleep(500); // Optional wait for scroll to settle

			try {
				viewflightbutton.click();
			} catch (ElementClickInterceptedException e) {
				js.executeScript("arguments[0].click();", viewflightbutton);
			}

			// Wait for baggage info to load
			List<WebElement> checkInBaggage = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("//span[text()='Check-in baggage:']//strong")));

			boolean baggageAvailable = false;    //if it is  true it will go to if then brk

			// Check each baggage info
			for (WebElement element : checkInBaggage) {
				js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
				Thread.sleep(500);
				String checkInBaggageText = element.getText();

				// Check if baggage is available

				if (!(checkInBaggageText.contains("0PC") || checkInBaggageText.contains("0KG"))) {
					baggageAvailable = true;
					break;
				}
			}

			// Final single log based on result
			if (baggageAvailable) {
				Log.ReportEvent("PASS", "Check In Baggage flights are displayed on Result Screen");
			} else {
				Log.ReportEvent("FAIL", "Check In Baggage flights are Not displayed on Result Screen");
			}

			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception occurred while checking Check In Baggage flights on Result Screen");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
		closeButtonOnResultPage();
	}


	//Method to Validate Return Check in Baggage.
	public void validateReturnCheckInBaggageDetailsInRoundTrip(Log log, ScreenShots screenshots, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

		try {
			Thread.sleep(4000);

			String xpath = "//div[@class='round-trip-to-results']//button[text()='View Flight'])[" + index + "]";

			WebElement viewflightbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewflightbutton);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewflightbutton);

			// Validate baggage info
			WebElement baggageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//span[text()='Check-in baggage:']")));

			String baggageText = baggageElement.getText();
			log.ReportEvent("PASS", "Selected return flight Index: " + index + " with baggage: " + baggageText);
			screenshots.takeScreenShot1();

		} catch (Exception e) {
			log.ReportEvent("FAIL", "Error selecting return flight " + index + ": " + e.getMessage());
			screenshots.takeScreenShot1();
		}
		closeButtonOnResultPage();

	}

	//method to close the button on result screen
	public void closeButtonOnResultPage() {
		driver.findElement(By.xpath("//button[text()='Close']")).click();
	}
	// Method to  round Trip click On Ward  Airline Stops
	public void roundTripClickOnWardStops(String... stops) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (String stop : stops) {
			String trimmedStop = stop.trim();
			String xpath = "//div[@class='filter-stops']//button[text()='" + trimmedStop + "']";

			WebElement stopButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

			// Scroll into view
			js.executeScript("arguments[0].scrollIntoView(true);", stopButton);

			// Wait until clickable
			wait.until(ExpectedConditions.elementToBeClickable(stopButton));

			// JavaScript click to bypass overlay issues
			js.executeScript("arguments[0].click();", stopButton);
		}
	}

	//Method to Click on Refundable Fare
	public void clickRefundableFareRoundTrip() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//legend[text()='REFUNDABLE FARE']/parent::div//input[@type='checkbox']")));

			// Scroll into view (helpful if covered by sticky headers)
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);

			// Use JS to click (bypasses invisible/overlay issues)
			js.executeScript("arguments[0].click();", checkbox);

			System.out.println("Clicked Refundable Fare Checkbox");
		} catch (Exception e) {
			System.out.println("Failed to click Refundable Fare checkbox");
			e.printStackTrace();
		}

	}

	public void validateRefundableFairOnRoundTripForLocal(Log Log, ScreenShots ScreenShots, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			String xpath = "(//div[@class='round-trip-from-results']//button[text()='View Flight'])[" + index + "]";
			WebElement viewflightbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", viewflightbutton);
			Thread.sleep(500);

			try {
				viewflightbutton.click();
			} catch (ElementClickInterceptedException e) {
				js.executeScript("arguments[0].click();", viewflightbutton);
			}

			List<WebElement> refundable = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("//span[text()='Refundable']")));

			boolean refundableAvailable = false;

			for (WebElement element : refundable) {
				js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
				Thread.sleep(500);
				String refundableText = element.getText();
				System.out.println("Refundable text found: " + refundableText);

				if (refundableText.contains("Refundable")) {
					refundableAvailable = true;
					break;
				}
			}

			if (refundableAvailable) {
				Log.ReportEvent("PASS", "Refundable is displayed on Result Screen");
			} else {
				Log.ReportEvent("FAIL", "Refundable is NOT displayed on Result Screen");
			}

			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception occurred while checking Refundable flights on Result Screen");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}

		closeButtonOnResultPage();
	}

	public void validateAirlinesForDomesticFlightsOnRoundTrip(int index, Log Log, ScreenShots ScreenShots, String... airlineCode) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// Click the correct flight using index
			String xpath = "(//div[@class='round-trip-from-results']//button[text()='View Flight'])[" + index + "]";
			WebElement viewflightbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

			// Scroll into view and click
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", viewflightbutton);
			Thread.sleep(500); // Optional wait for scroll to settle

			try {
				viewflightbutton.click(); // Try normal click
			} catch (ElementClickInterceptedException e) {
				js.executeScript("arguments[0].click();", viewflightbutton);
			}

			// Get all displayed airline names from the flight result popup
			List<WebElement> airlineElements = driver.findElements(By.xpath("//div[contains(@class,'flight')]//p"));
			List<String> displayedAirlines = new ArrayList<>();

			for (WebElement element : airlineElements) {
				String airline = element.getText().trim();
				if (!airline.isEmpty()) {
					displayedAirlines.add(airline);
				}
			}

			System.out.println("User passed airlines: " + Arrays.toString(airlineCode));
			System.out.println("Displayed Airlines: " + displayedAirlines);

			boolean allMatch = true;
			for (String expectedAirline : airlineCode) {
				String expectedTrimmed = expectedAirline.trim().toLowerCase();

				boolean found = displayedAirlines.stream()
						.anyMatch(actual -> actual.trim().toLowerCase().equals(expectedTrimmed));

				if (found) {
					Log.ReportEvent("PASS", "Expected airline is showing: " + expectedAirline);
				} else {
					Log.ReportEvent("FAIL", "Expected airline NOT found: " + expectedAirline);
					allMatch = false;
				}
			}

			if (allMatch) {
				Log.ReportEvent("PASS", "All expected airlines are correctly shown in the results.");
			} else {
				Log.ReportEvent("FAIL", "Some expected airlines are missing in the results.");
			}

			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Exception occurred while validating airline codes: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
		}

		// Close the result page
		closeButtonOnResultPage();
	}

	//Method to click ONWARD DEPART TIME
	public void selectOnWardDepartTimeOnRoundTrip(String... times) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (String time : times) {
			String trimmedTime = time.trim();
			String xpath = "//legend[text()='ONWARD DEPART TIME']/parent::div//small[text()='" + trimmedTime + "']";

			WebElement departTime = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", departTime);
			wait.until(ExpectedConditions.elementToBeClickable(departTime));
			js.executeScript("arguments[0].click();", departTime);
		}
	}

	//Method to click check In baggage round trip
	public void clickRoundTripCheckInBaggage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//legend[text()='CHECK-IN BAGGAGE']/parent::div//input[@type='checkbox']")));

			// Scroll into view (helpful if covered by sticky headers)
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);

			// Use JS to click (bypasses invisible/overlay issues)
			js.executeScript("arguments[0].click();", checkbox);

			System.out.println("Clicked CHECK-IN BAGGAGE checkbox");
		} catch (Exception e) {
			System.out.println("Failed to click CHECK-IN BAGGAGE checkbox");
			e.printStackTrace();
		}
	}

	//Method to Validate data after selection of Flight on Result Screen
	public void validateDataAfterSelectingFlight(Log Log, ScreenShots ScreenShots, int departureIndex, int returnDepartureIndex, int arrivalIndex, int returnArrivalIndex, int priceIndex) {
		try {
			String departingDepartureTime = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[" + departureIndex + "]")).getText();
			String returnDepartingDepartureTime = driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[" + returnDepartureIndex + "]")).getText();
			String arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[" + arrivalIndex + "]")).getText();
			String returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[" + returnArrivalIndex + "]")).getText();

			String price = driver.findElement(By.xpath("(//h6[@data-tgflprice])[" + priceIndex + "]")).getText();
			String bookingPrice = driver.findElement(By.xpath("//div[contains(@class,'bottom-container')]//h6[@data-tgflprice]")).getText();


			String departingDepartureTimePopup = driver.findElement(By.xpath("//span[@data-tgfldeptime]")).getText();
			String arrivalTimePopup = driver.findElement(By.xpath("//span[@data-tgflarrtime]")).getText();
			String returnDepartingDepartureTimePopup = driver.findElement(By.xpath("//span[@data-tgfltodeptime]")).getText();
			String returnArrivalTimePopup = driver.findElement(By.xpath("//span[@data-tgfltoarrtime]")).getText();
			String departingFrom = driver.findElement(By.xpath("//small[@data-tgflorigin]")).getText();
			String departingTo = driver.findElement(By.xpath("//small[@data-tgfldestination]")).getText();
			String returnFrom = driver.findElement(By.xpath("//small[@data-tgfltoorigin]")).getText();
			String returnTo = driver.findElement(By.xpath("//small[@data-tgfltodestination]")).getText();

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

			if (fromLocationCode.contentEquals(departingFrom) && toLocationCode.contentEquals(departingTo) && fromLocationCode.contentEquals(returnTo) && toLocationCode.contentEquals(returnFrom)) {
				if (departingDepartureTime.contains(departingDepartureTimePopup) && returnDepartingDepartureTime.contentEquals(returnDepartingDepartureTimePopup) && arrivalTime.contentEquals(arrivalTimePopup) && returnArrivalTime.contentEquals(returnArrivalTimePopup) && price.contentEquals(bookingPrice)) {
					Log.ReportEvent("PASS", "Flights Price, Arrival and Departure Time is displaying Same");
					ScreenShots.takeScreenShot1();
					clickOnContinueBookingFlightPopup();
				} else {
					Log.ReportEvent("FAIL", "Flights Price, Arrival and Departure Time is displaying are Not Same");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			} else {
				clickOnContinueBookingFlightPopup();
				String origenCode = driver.findElement(By.xpath("(//h2[text()='Airport Change']/parent::div//strong)[1]")).getText();
				String destinationCode = driver.findElement(By.xpath("(//h2[text()='Airport Change']/parent::div//strong)[2]")).getText();

				if (origenCode.contains(fromLocationCode) && destinationCode.contentEquals(toLocationCode)) {
					Log.ReportEvent("PASS", "Flights Price, Arrival and Departure Time is displaying Same");
					ScreenShots.takeScreenShot1();
					clickOnYesContinueOnContinuePopup();
				} else {
					Log.ReportEvent("FAIL", "Flights Price, Arrival and Departure Time is displaying are Not Same");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Flights Price, Arrival and Departure Time is displaying Same" + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}

	}
	//Method to Click on Continue Button on Continue Flight Booking Popup
	public void clickOnYesContinueOnContinuePopup() throws InterruptedException
	{
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Yes, Continue']")).click();
		Thread.sleep(3000);
	}

	//Method to get Departing Flight Details
	public String[] getDepartingFlightDetails()
	{
		String departingDepartureTime=driver.findElement(By.xpath("//span[@data-tgfldeptime]")).getText().trim();
		String departingArrivalTime=driver.findElement(By.xpath("//span[@data-tgflarrtime]")).getText().trim();
		String origin=driver.findElement(By.xpath("//small[@data-tgflorigin]")).getText().trim();
		String destination=driver.findElement(By.xpath("//small[@data-tgfldestination]")).getText().trim();


		return new String[] {departingDepartureTime,departingArrivalTime,origin,destination};

	}

	//Method to get Return Flight Details
	public String[] getReturnFlightDetails()
	{
		String arrivalDepartureTime=driver.findElement(By.xpath("//span[@data-tgfltodeptime]")).getText().trim();
		String arrivalArrivalTime=driver.findElement(By.xpath("//span[@data-tgfltoarrtime]")).getText().trim();
		String returnOrigin=driver.findElement(By.xpath("//small[@data-tgfltoorigin]")).getText().trim();
		String returnDestination=driver.findElement(By.xpath("//small[@data-tgfltodestination]")).getText().trim();
		String price=driver.findElement(By.xpath("//div[@data-tgflighttodetails]//following-sibling::div//h6[@data-tgflprice]")).getText().trim();
		return new String[] {arrivalDepartureTime,arrivalArrivalTime,returnOrigin,returnDestination,price};

	}


	//Method to Validate Departing Flight Details on Booking Screen
	public void validateFlightDetailsOnBookingScreenForDepartingDetails(String stopsText,String departingDepartureTime,String departingArrivalTime,String origin,String destination,String date,Log log, ScreenShots screenShots) throws InterruptedException {
		Thread.sleep(8000);
		try{
			if(stopsText.contentEquals("1 stops"))
			{
				String originData=driver.findElement(By.xpath("(//h6[@data-tgflorigin])[1]")).getAttribute("data-tgflorigin");
				String[] originCode = originData.split("-");
				// Get the airport code (right part) and trim any whitespace
				String originAirportCode = originCode[1].trim();
				System.out.println("Airport Code: " + originAirportCode);

				String destinationData=driver.findElement(By.xpath("(//h6[@data-tgfldestination])[2]")).getAttribute("data-tgfldestination");
				String[] destinationCode = destinationData.split("-");
				// Get the airport code (right part) and trim any whitespace
				String destinationAirportCode = destinationCode[1].trim();
				System.out.println("Airport Code: " + destinationAirportCode);

				String dateDetails=driver.findElement(By.xpath("(//h6[contains(@class,'flight-title')])[1]")).getText();
				System.out.println(dateDetails);
				String[] dateData = dateDetails.split("::");

				// Get the right-hand part and trim it
				String dateValue = dateData[1].trim();

				System.out.println("Date: " + dateValue);

				String depTimeData=driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[1]")).getText();
				String arrTimeData=driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[2]")).getText();

				Assert.assertEquals(originAirportCode,origin,"Origin Data in Search Screen and Booking Screen is MisMatching");
				Assert.assertEquals(destinationAirportCode,destination,"Destination Data in Search Screen and Booking Screen is MisMatching");
				Assert.assertEquals(depTimeData,departingDepartureTime,"Departing Data in Search Screen and Booking Screen is MisMatching");
				Assert.assertEquals(arrTimeData,departingArrivalTime,"Departing Arrival Data in Search Screen and Booking Screen is MisMatching");
				Assert.assertEquals(dateValue,date,"Date Data in Search Screen and Booking Screen is MisMatching");

				if(originAirportCode.contentEquals(origin)&&destinationAirportCode.contentEquals(destination)&&depTimeData.contentEquals(departingDepartureTime)&&arrTimeData.contentEquals(departingArrivalTime)&&dateValue.contentEquals(date))
				{
					log.ReportEvent("PASS", "Flights Departing Details Validated on Booking Screen is Successful"+" "+"origin-"+origin+" "+"destination-"+destination);
					screenShots.takeScreenShot1();
				}
				else{
					log.ReportEvent("FAIL", "Flights Departing Details Validated on Booking Screen is UnSuccessful");
					screenShots.takeScreenShot1();
					Assert.fail();
				}

			}

		}catch(Exception e)
		{
			log.ReportEvent("FAIL", "Flights Details Validated on Booking Screen is UnSuccessful");
			screenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

//Method to Validate Departing Flight Details on Booking Screen
public void validateFlightDetailsOnBookingScreenForReturnFlightDetails(String stopsText,String departingDepartureTime,String departingArrivalTime,String origin,String destination,String price,String returnDate,Log log, ScreenShots screenShots) throws InterruptedException {
	Thread.sleep(8000);
	try{
		if(stopsText.contentEquals("1 stops"))
		{
			String originData=driver.findElement(By.xpath("(//h6[@data-tgflorigin])[3]")).getAttribute("data-tgflorigin");
			String[] originCode = originData.split("-");
			// Get the airport code (right part) and trim any whitespace
			String originAirportCode = originCode[1].trim();
			System.out.println("Airport Code: " + originAirportCode);

			String destinationData=driver.findElement(By.xpath("(//h6[@data-tgfldestination])[4]")).getAttribute("data-tgfldestination");
			String[] destinationCode = destinationData.split("-");
			// Get the airport code (right part) and trim any whitespace
			String destinationAirportCode = destinationCode[1].trim();
			System.out.println("Airport Code: " + destinationAirportCode);

			String dateDetails=driver.findElement(By.xpath("(//h6[contains(@class,'flight-title')])[2]")).getText();
			System.out.println(dateDetails);
			String[] dateData = dateDetails.split("::");

			// Get the right-hand part and trim it
			String dateValue = dateData[1].trim();

			System.out.println("Date: " + dateValue);

			String depTimeData=driver.findElement(By.xpath("(//h6[@data-tgfldeptime])[3]")).getText();
			String arrTimeData=driver.findElement(By.xpath("(//h6[@data-tgflarrtime])[4]")).getText();

	        String priceValue=driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();

			Assert.assertEquals(originAirportCode,origin,"Origin Data in Search Screen and Booking Screen is MisMatching");
			Assert.assertEquals(destinationAirportCode,destination,"Destination Data in Search Screen and Booking Screen is MisMatching");
			Assert.assertEquals(depTimeData,departingDepartureTime,"Departing Data in Search Screen and Booking Screen is MisMatching");
			Assert.assertEquals(arrTimeData,departingArrivalTime,"Departing Arrival Data in Search Screen and Booking Screen is MisMatching");
			Assert.assertEquals(dateValue,returnDate,"Date Data in Search Screen and Booking Screen is MisMatching");
			Assert.assertEquals(priceValue,price,"Date Data in Search Screen and Booking Screen is MisMatching");

			if(originAirportCode.contentEquals(origin)&&destinationAirportCode.contentEquals(destination)&&depTimeData.contentEquals(departingDepartureTime)&&arrTimeData.contentEquals(departingArrivalTime)&&priceValue.contentEquals(price)&&dateValue.contentEquals(returnDate))
			{
				log.ReportEvent("PASS", "Flights Return Details Validated on Booking Screen is Successful"+" "+"origin-"+origin+" "+"destination-"+destination);
				screenShots.takeScreenShot1();
			}
			else{
				log.ReportEvent("FAIL", "Flights Return Details Validated on Booking Screen is UnSuccessful");
				screenShots.takeScreenShot1();
				Assert.fail();
			}

		}

	}catch(Exception e)
	{
		log.ReportEvent("FAIL", "Flights Return Details Validated on Booking Screen is UnSuccessful");
		screenShots.takeScreenShot1();
		e.printStackTrace();
		Assert.fail();
	}
}

	//Method to get SelectedDate
	public String getSelectedDate()
	{
		String date=driver.findElement(By.xpath("(//span[@data-tgfldepdate])[1]")).getText().trim();
		return date;
	}

	//Method to get SelectedDate
	public String getReturnSelectedDate()
	{
		String date=driver.findElement(By.xpath("(//span[@data-tgfldepdate])[2]")).getText().trim();
		return date;
	}
	//Method to get the Stops Based on Index
	public String getStopsBasedOnIndex(String stopsIndex)
	{
		String stops=driver.findElement(By.xpath("(//span[@tgflstops])["+stopsIndex+"]")).getText();
		return stops;
	}

	//Method to validate Check In Baggage Functionality
	public void validateCheckInBaggageFlightsOnResultScreen(Log Log,ScreenShots ScreenShots)
	{
		try {
			List<WebElement> checkInBaggage = driver.findElements(By.xpath("//strong[@data-tgflcheckinbaggage]"));
			for(WebElement element : checkInBaggage)
			{
				String checkInBaggageText = element.getText();
				if(checkInBaggageText.contains("0 PC") || checkInBaggageText.contains("0 KG"))
				{
					System.out.println("Fail,Check-in baggage is not available");

				}
				else
				{
					System.out.println("Pass,Check-in baggage is available");
				}
			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Check In Baggage flights are Not displayed on Result Screen");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}


	//Method to Get List of LayOver AirLines on Result Screen
	public ArrayList getListOfLayOverAirlinesOnResultScreen() {
		List<WebElement> airlineList = driver.findElements(By.xpath("//legend[text()='LAYOVER AIRPORTS']/parent::div//li//div/span"));
		airlineList.size();
		ArrayList names = new ArrayList();

		for (WebElement airlineText : airlineList) {
			String name = airlineText.getText();
			names.add(name);
			System.out.println(name);
		}
		return names;
	}

	//Method to Validate LayOver Airlines
	public void validateLayOverAirlines(String layOverLocation,Log log,ScreenShots screenShots)
	{
		try{
                  WebElement layOverAirline=driver.findElement(By.xpath("//div[contains(@class,'change-over-msg')]"));

				  String airlineData=layOverAirline.getText().toString();
				  // Split by comma to isolate the location part
				  String[] parts = airlineData.split(",");
				  String[] subParts = parts[0].split(":");
				  String location = subParts[1].trim();
				  System.out.println("Location: " + location);
				  if(layOverLocation.contains(location))
				  {
					  System.out.println("Layover Airline is Displayed"+location );
				  }
				  else{
					  System.out.println("Layover Airline is Not Displayed"+location );
					  log.ReportEvent("FAIL", "LayOver AirLines are Not Displayed");
					  Assert.fail();
				  }

		}catch(Exception e)
		{
			log.ReportEvent("FAIL", "LayOver AirLines are Not Displayed");
			screenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Validate All From Dates In Result Screen.
	public void validateAllFromDatesInResultScreen(Log Log, ScreenShots ScreenShots) {
		try {
			// Retrieve the user-selected date from the input field
			WebElement dateInput = driver.findElement(By.xpath("(//div[contains(@class, 'react-datepicker__input-container')]//input[@value])[1]"));
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
			List<WebElement> flightDates = driver.findElements(By.xpath("//span[@data-tgfldepdate]"));

			// Initialize a flag to track if any date matches
			boolean dateFound = true;

			// Iterate through each flight date and compare with the selected date
			for (int i=0;i<flightDates.size();i=i+2) {
					WebElement flightDateValues = flightDates.get(i);
				String flightDateValue=flightDateValues.getAttribute("data-tgfldepdate");
				if (!flightDateValue.equals(formattedUserDate)) {
					dateFound = false;
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
			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			// Handle exceptions and log the error
			Log.ReportEvent("ERROR", "An error occurred during date validation: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			Assert.fail();
		}
	}

	//Validate All Return Dates In Result Screen.
	public void validateAllReturnDatesInResultScreen(Log Log, ScreenShots ScreenShots) {
		try {
			// Retrieve the user-selected date from the input field
			WebElement dateInput = driver.findElement(By.xpath("(//div[contains(@class, 'react-datepicker__input-container')]//input[@value])[2]"));
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
			List<WebElement> flightDates = driver.findElements(By.xpath("//span[@data-tgfldepdate]"));

			// Initialize a flag to track if any date matches
			boolean dateFound = true;

			// Iterate through each flight date and compare with the selected date
			for (int i=1;i<flightDates.size();i=i+2) {
				WebElement flightDateValues = flightDates.get(i);
				String flightDateValue=flightDateValues.getAttribute("data-tgfldepdate");
				if (!flightDateValue.equals(formattedUserDate)) {
					dateFound = false;
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
			ScreenShots.takeScreenShot1();

		} catch (Exception e) {
			// Handle exceptions and log the error
			Log.ReportEvent("ERROR", "An error occurred during date validation: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}


	//Method to Click On Close Flight Details Popup
	public void clickOnCloseButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Close']")).click();
	}

	//Method to Click On Flight Info Details
	public void clickOnFlightInfoDetails() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//h6[text()='Flight Info']")).click();
	}

	//Method to Validate Flight details
	public String[] getFlightDetails(String fromStops,String returnStops,Log Log, ScreenShots ScreenShots) {
        String departingOrigin = null;
		String departingDestination = null;
		String departingTime = null;
		String arrivalTime = null;
		String fromDate = null;
		String returnOrigin = null;
		String returnDestination = null;
		String returnDepartingTime = null;
		String returnArrivalTime = null;
		String returnDate = null;
		try {
            if (fromStops.contentEquals("Nonstop")&&returnStops.contentEquals("Nonstop")) {
                departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
                departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[1]")).getAttribute("data-tgfldestinationairport");
                departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
                arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[1]")).getText();
                fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[1]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[1]")).getText();
                returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();

            } else if (fromStops.contentEquals("Nonstop")&&returnStops.contentEquals("1 stops")) {
				departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[1]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[1]")).getText();
				fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[2]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[2]")).getText();
				returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();
			}
			else if (fromStops.contentEquals("1 stops")&&returnStops.contentEquals("Nonstop")) {
				departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[2]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[2]")).getText();
				fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[1]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[1]")).getText();
				returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();

			}
			else if (fromStops.contentEquals("1 stops")&&returnStops.contentEquals("1 stops")) {
				departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[2]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[2]")).getText();
				fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[2]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[2]")).getText();
				returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();

            } else if (fromStops.contentEquals("2 stops")&&returnStops.contentEquals("2 stops")) {
				departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[last()]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[last()]")).getText();
				fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[last()]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[last()]")).getText();
				returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();
            }
			else if (fromStops.contentEquals("2 stops")&&returnStops.contentEquals("1 stops")) {
				departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[last()]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[last()]")).getText();
				fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[2]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[2]")).getText();
				returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();
			}
			else if (fromStops.contentEquals("1 stops")&&returnStops.contentEquals("2 stops")) {
				departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[2]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[2]")).getText();
				fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[last()]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[last()]")).getText();
				returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();
			}
			else if (fromStops.contentEquals("Nonstop")&&returnStops.contentEquals("2 stops")) {
				departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[1]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[1]")).getText();
				fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[last()]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[last()]")).getText();
				returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();
			}
			else if (fromStops.contentEquals("2 stops")&&returnStops.contentEquals("Nonstop")) {
				departingOrigin = driver.findElement(By.xpath("(//*[@id='flightDepart-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//*[@id='flightDepart-destination'])[last()]")).getAttribute("data-tgfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//*[@id='flightDepart-deptime'])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//*[@id='flightDepart-arrtime'])[last()]")).getText();
				fromDate = driver.findElement(By.xpath("(//*[@id='flightDepart-depdate'])[1]")).getText();

				returnOrigin = driver.findElement(By.xpath("(//*[@id='flightReturn-Origin'])[1]")).getAttribute("data-tgfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//*[@id='flightReturn-destination'])[1]")).getAttribute("data-tgfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//*[@id='flightReturn-deptime'])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//*[@id='flightReturn-arrtime'])[1]")).getText();
				returnDate = driver.findElement(By.xpath("(//*[@id='flightReturn-depdate'])[1]")).getText();
			}else{
				Log.ReportEvent("FAIL", "Unable to get data from Search Screen");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}


        } catch (Exception e) {
			Log.ReportEvent("FAIL", "Unable to get data from Search Screen");
			e.printStackTrace();
			Assert.fail();

        }
        return new String[]{departingOrigin, departingDestination, departingTime, arrivalTime, fromDate, returnOrigin, returnDestination, returnDepartingTime, returnArrivalTime, returnDate};

    }

	//Method to Validate Flights Details in Booking Screen
	public void validateFlightDetailsInBookingScreen(Log Log, ScreenShots ScreenShots,String price,String fare,String fromStops,String returnStops,String departingOrigin,String departingDestination,String departingTime,String arrivalTime,String fromDate,String returnOrigin,String returnDestination,String returnDepartingTime,String returnArrivalTime,String returnDate)
	{
		try{
			if (fromStops.contentEquals("Nonstop")&&returnStops.contentEquals("Nonstop")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("//h6[@data-tgdepartflorigin]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("//h6[@data-tgdepartfldestination]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("//h6[@data-tgdepartfldeptime]")).getText();
				arrivalTime = driver.findElement(By.xpath("//h6[@data-tgdepartflarrtime]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();
				Thread.sleep(2000);
				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[1]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[1]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				price = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();


				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);

			} else if (fromStops.contentEquals("Nonstop")&&returnStops.contentEquals("1 stops")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("//h6[@data-tgdepartflorigin]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("//h6[@data-tgdepartfldestination]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("//h6[@data-tgdepartfldeptime]")).getText();
				arrivalTime = driver.findElement(By.xpath("//h6[@data-tgdepartflarrtime]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();

				Thread.sleep(2000);
				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[2]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[2]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);

			}
			else if (fromStops.contentEquals("1 stops")&&returnStops.contentEquals("Nonstop")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("(//h6[@data-tgdepartflorigin])[1]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//h6[@data-tgdepartfldestinationairport])[2]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//h6[@data-tgdepartfldeptime])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgdepartflarrtime])[2]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();
				Thread.sleep(2000);

				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[1]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[1]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);

			}
			else if (fromStops.contentEquals("1 stops")&&returnStops.contentEquals("1 stops")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("(//h6[@data-tgdepartflorigin])[1]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//h6[@data-tgdepartfldestinationairport])[2]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//h6[@data-tgdepartfldeptime])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgdepartflarrtime])[2]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();
				Thread.sleep(2000);
				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[2]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[2]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);

			} else if (fromStops.contentEquals("2 stops")&&returnStops.contentEquals("2 stops")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("(//h6[@data-tgdepartflorigin])[1]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//h6[@data-tgdepartfldestinationairport])[last()]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//h6[@data-tgdepartfldeptime])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgdepartflarrtime])[last()]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();
				Thread.sleep(2000);
				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[last()]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[last()]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);
			}
			else if (fromStops.contentEquals("2 stops")&&returnStops.contentEquals("1 stops")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("(//h6[@data-tgdepartflorigin])[1]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//h6[@data-tgdepartfldestinationairport])[last()]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//h6[@data-tgdepartfldeptime])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgdepartflarrtime])[last()]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();

				Thread.sleep(2000);
				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[2]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[2]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);
			}
			else if (fromStops.contentEquals("1 stops")&&returnStops.contentEquals("2 stops")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("(//h6[@data-tgdepartflorigin])[1]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//h6[@data-tgdepartfldestinationairport])[2]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//h6[@data-tgdepartfldeptime])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgdepartflarrtime])[2]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();

				Thread.sleep(2000);
				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[last()]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[last()]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);
			}
			else if (fromStops.contentEquals("Nonstop")&&returnStops.contentEquals("2 stops")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("//h6[@data-tgdepartflorigin]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("//h6[@data-tgdepartfldestination]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("//h6[@data-tgdepartfldeptime]")).getText();
				arrivalTime = driver.findElement(By.xpath("//h6[@data-tgdepartflarrtime]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();

				Thread.sleep(2000);
				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[last()]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[last()]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);
			}
			else if (fromStops.contentEquals("2 stops")&&returnStops.contentEquals("Nonstop")) {
				Thread.sleep(2000);
				departingOrigin = driver.findElement(By.xpath("(//h6[@data-tgdepartflorigin])[1]")).getAttribute("data-tgdepartfloriginairport");
				departingDestination = driver.findElement(By.xpath("(//h6[@data-tgdepartfldestinationairport])[last()]")).getAttribute("data-tgdepartfldestinationairport");
				departingTime = driver.findElement(By.xpath("(//h6[@data-tgdepartfldeptime])[1]")).getText();
				arrivalTime = driver.findElement(By.xpath("(//h6[@data-tgdepartflarrtime])[last()]")).getText();
				fromDate = driver.findElement(By.xpath("(//small[@data-tgdepartfldepdate])[1]")).getText();

				Thread.sleep(2000);
				returnOrigin = driver.findElement(By.xpath("(//h6[@data-tgreturnflorigin])[1]")).getAttribute("data-tgreturnfloriginairport");
				returnDestination = driver.findElement(By.xpath("(//h6[@data-tgreturnfldestination])[1]")).getAttribute("data-tgreturnfldestinationairport");
				returnDepartingTime = driver.findElement(By.xpath("(//h6[@data-tgreturnfldeptime])[1]")).getText();
				returnArrivalTime = driver.findElement(By.xpath("(//h6[@data-tgreturnflarrtime])[1]")).getText();
				returnDate = driver.findElement(By.xpath("(//small[@data-tgreturnfldepdate])[1]")).getText();

				ValidateActualAndExpectedValuesForFlights(departingOrigin, departingOrigin, "Departing Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingDestination, departingDestination, "Departing Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(departingTime, departingTime, "Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(arrivalTime, arrivalTime, "Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(fromDate, fromDate, "From Date Details in Search Screen and Booking Screen", Log);

				ValidateActualAndExpectedValuesForFlights(returnOrigin, returnOrigin, "Return Origin Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDestination, returnDestination, "Return Destination Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDepartingTime, returnDepartingTime, "Return Departing Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnArrivalTime, returnArrivalTime, "Return Departing Arrival Time Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(returnDate, returnDate, "Return Date Details in Search Screen and Booking Screen", Log);
				ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);
			}
			else{
				Log.ReportEvent("FAIL", "Validation is Mismatching");
				ScreenShots.takeScreenShot1();
				Assert.fail();
			}
		}catch(Exception e)
		{
			Log.ReportEvent("FAIL", "An error occurred during date validation: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}

	}

	//Method to getStops Text Based On Index
	public String getStopsText(int index)
	{
       String stopsText=driver.findElement(By.xpath("(//*[contains(@class,'tg-intlonward-stops')])["+index+"]")).getText();
        return stopsText;
    }

	//Method to getStops Text Based On Index
	public String getReturnStopsText(int index)
	{
		String stopsText=driver.findElement(By.xpath("(//*[contains(@class,'tg-intlreturn-stops')])["+index+"]")).getText();
		return stopsText;
	}

	//Method to click on Continue Button based on Fare
	public String clickOnContinueBasedOnFareType(String fareType, String reason) throws InterruptedException {
        Thread.sleep(2000);
        String price = null;
        try {
            price = null;
            List<WebElement> fareElements = driver.findElements(By.xpath("//div[@data-tgflfaretype='" + fareType + "']"));

            if (!fareElements.isEmpty() && fareElements.get(0).isDisplayed()) {
				Thread.sleep(2000);
				WebElement continueButton = driver.findElement(By.xpath(
						"//div[@data-tgflfaretype='" + fareType + "']/parent::div/following-sibling::div//button[text()='Continue']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
				Thread.sleep(2000);
				continueButton.click();
				price = driver.findElement(By.xpath("//div[@data-tgflfaretype='" + fareType + "']/parent::div//div[@data-tgflfare]")).getText();

                if (isElementPresent(By.xpath("//h2[text()='Airport Change']"))) {
                    clickOnYesContinue();
                }

                if (isElementPresent(By.xpath("//h2[text()='Reason for Selection']"))) {
                    clickOnSelectRegionPopup(reason);
                    clickOnProceedBooking();
                }
            } else {
                driver.findElement(By.xpath("(//button[text()='Continue'])[1]")).click();
                price = driver.findElement(By.xpath("(//div[@data-tgflfare])[1]")).getText();
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
        return price;
    }

	//Method to click on Yes Continue Button
	public void clickOnYesContinue() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Yes, Continue']")).click();

	}
	public boolean isElementPresent(By locator) {
		return !driver.findElements(locator).isEmpty() &&
				driver.findElements(locator).get(0).isDisplayed();
	}

	// Method to collect data from the Departing Flight section (Search Screen)
	public Map<String, List<String>> getDataFromUiForDepartingFlight() {
		Map<String, List<String>> data = new LinkedHashMap<>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Class names to extract; layover included
		List<String> classNames = Arrays.asList(
				"tg-fromorigin",
				"tg-fromdepdate",
				"tg-fromdeptime",
				"tg-fromdestination",
				"tg-fromarrdate",
				"tg-fromarrtime",
				"tg-fromduration",
				"tg-fromcabinclass",
				"tg-intl-owlayovercity"
		);

		for (String className : classNames) {
			List<String> values = new ArrayList<>();

			try {
				// Wait for presence of at least one visible element with this class
				wait.until(driver -> {
					List<WebElement> elements = driver.findElements(By.className(className));
					return !elements.isEmpty() && elements.stream().anyMatch(el -> !el.getText().trim().isEmpty());
				});

				// Fetch all elements after wait
				List<WebElement> elements = driver.findElements(By.className(className));
				for (WebElement el : elements) {
					String text = el.getText().trim();
					if (!text.isEmpty()) {
						values.add(text);
					}
				}
			} catch (TimeoutException e) {
				System.out.println("Timeout waiting for elements with class: " + className);
			} catch (Exception e) {
				System.out.println("Error fetching data for class: " + className + " - " + e.getMessage());
			}

			data.put(className, values);
		}

		System.out.println("Departing Flight UI Data: " + data);
		return data;
	}

	// Method to collect data from the Return Flight section (Search Screen)
	public Map<String, List<String>> getDataFromUiForReturnFlight() {
		Map<String, List<String>> data = new LinkedHashMap<>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Class names to extract; layover included
		List<String> classNames = Arrays.asList(
				"tg-toorigin",
				"tg-todepdate",
				"tg-todeptime",
				"tg-todestination",
				"tg-toarrdate",
				"tg-toarrtime",
				"tg-toduration",
				"tg-tocabinclass",
				"tg-intl-rtlayovercity"
		);

		for (String className : classNames) {
			List<String> values = new ArrayList<>();

			try {
				// Wait for presence of at least one visible element with this class
				wait.until(driver -> {
					List<WebElement> elements = driver.findElements(By.className(className));
					return !elements.isEmpty() && elements.stream().anyMatch(el -> !el.getText().trim().isEmpty());
				});

				// Fetch all elements after wait
				List<WebElement> elements = driver.findElements(By.className(className));
				for (WebElement el : elements) {
					String text = el.getText().trim();
					if (!text.isEmpty()) {
						values.add(text);
					}
				}
			} catch (TimeoutException e) {
				System.out.println("Timeout waiting for elements with class: " + className);
			} catch (Exception e) {
				System.out.println("Error fetching data for class: " + className + " - " + e.getMessage());
			}

			data.put(className, values);
		}

		System.out.println("Return Flight UI Data: " + data);
		return data;
	}



	//Method to get Data from Booking Screen
	public Map<String, List<String>> getDataFromUiForFbDepartingFlight() {
		Map<String, List<String>> data = new LinkedHashMap<>();
		List<String> classNames = Arrays.asList(
				"tg-fbdepartorigin",
				"tg-fbdepartdepdate",
				"tg-fbdepartdeptime",
				"tg-fbdepartdestination",
				"tg-fbdepartarrdate",
				"tg-fbdepartarrtime",
				"tg-fbdepartcabinclass",
				"tg-fbdepartduration",
				"tg-fbdepartlayovercity"
		);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		for (String className : classNames) {
			List<String> values = new ArrayList<>();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
				List<WebElement> elements = driver.findElements(By.className(className));
				for (WebElement el : elements) {
					values.add(el.getText().trim());
				}
			} catch (Exception e) {
				System.out.println("Warning: Could not find elements for class: " + className);
			}
			data.put(className, values);
		}

		return data;
	}

	// Method to get Data from Booking Screen - Return Flight
	public Map<String, List<String>> getDataFromUiForFbReturnFlight() {
		Map<String, List<String>> data = new LinkedHashMap<>();
		List<String> classNames = Arrays.asList(
				"tg-fbreturnorigin",
				"tg-fbreturndepdate",
				"tg-fbreturndeptime",
				"tg-fbreturndestination",
				"tg-fbreturnarrdate",
				"tg-fbreturnarrtime",
				"tg-fbreturncabinclass",
				"tg-fbreturnduration",
				"tg-fbreturnlayovercity"
		);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		for (String className : classNames) {
			List<String> values = new ArrayList<>();
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
				List<WebElement> elements = driver.findElements(By.className(className));
				for (WebElement el : elements) {
					values.add(el.getText().trim());
				}
			} catch (Exception e) {
				System.out.println("Warning: Could not find elements for class: " + className);
			}
			data.put(className, values);
		}

		return data;
	}


	// Method to validate departing flight details between search and booking screens
	public void validateDepartingAndFbDepartingData(Map<String, List<String>> departingData,
													Map<String, List<String>> fbDepartingData,
													Log log, ScreenShots screenShots) {

		// Explicit key mapping for proper comparison
		Map<String, String> departingKeyMap = new LinkedHashMap<>();
		departingKeyMap.put("origin", "tg-fromorigin");
		departingKeyMap.put("depdate", "tg-fromdepdate");
		departingKeyMap.put("deptime", "tg-fromdeptime");
		departingKeyMap.put("destination", "tg-fromdestination");
		departingKeyMap.put("arrdate", "tg-fromarrdate");
		departingKeyMap.put("arrtime", "tg-fromarrtime");
		departingKeyMap.put("cabinclass", "tg-fromcabinclass");
		departingKeyMap.put("duration", "tg-fromduration");
		departingKeyMap.put("layovercity", "tg-intl-owlayovercity"); // Note: actual layover class

		boolean allMatch = true;

		for (String label : departingKeyMap.keySet()) {
			String departingKey = departingKeyMap.get(label);
			String fbDepartingKey = "tg-fbdepart" + label;

			List<String> departingList = departingData.getOrDefault(departingKey, Collections.emptyList());
			List<String> fbDepartingList = fbDepartingData.getOrDefault(fbDepartingKey, Collections.emptyList());

			int max = Math.max(departingList.size(), fbDepartingList.size());

			for (int i = 0; i < max; i++) {
				String val1 = i < departingList.size() ? departingList.get(i) : "<missing>";
				String val2 = i < fbDepartingList.size() ? fbDepartingList.get(i) : "<missing>";

				if (!val1.equals(val2)) {
					allMatch = false;
					log.ReportEvent("FAIL", "Mismatch in '" + label + "' at index " + (i + 1) +
							": Search Screen = '" + val1 + "', Booking Screen = '" + val2 + "'");
				}
			}
		}

		screenShots.takeScreenShot1();

		if (allMatch) {
			log.ReportEvent("PASS", "All departing flight details match between search and booking screens.");
		} else {
			Assert.fail("One or more departing flight details do not match between search and booking screens.");
		}
	}

	// Method to validate return flight details between Search and Booking screens
	public void validateReturnFlightData(Map<String, List<String>> returnData,
										 Map<String, List<String>> fbReturnData,
										 Log log, ScreenShots screenShots) {

		// Mapping between Search and Booking screen class names using a label
		Map<String, String> searchKeyMap = new LinkedHashMap<>();
		searchKeyMap.put("origin", "tg-toorigin");
		searchKeyMap.put("depdate", "tg-todepdate");
		searchKeyMap.put("deptime", "tg-todeptime");
		searchKeyMap.put("destination", "tg-todestination");
		searchKeyMap.put("arrdate", "tg-toarrdate");
		searchKeyMap.put("arrtime", "tg-toarrtime");
		searchKeyMap.put("cabinclass", "tg-tocabinclass");
		searchKeyMap.put("duration", "tg-toduration");
		searchKeyMap.put("layovercity", "tg-intl-rtlayovercity");

		boolean allMatch = true;

		for (String label : searchKeyMap.keySet()) {
			String searchKey = searchKeyMap.get(label);
			String fbKey = "tg-fbreturn" + label;

			List<String> searchList = returnData.getOrDefault(searchKey, Collections.emptyList());
			List<String> bookingList = fbReturnData.getOrDefault(fbKey, Collections.emptyList());

			int maxSize = Math.max(searchList.size(), bookingList.size());

			for (int i = 0; i < maxSize; i++) {
				String searchVal = i < searchList.size() ? searchList.get(i) : "<missing>";
				String bookingVal = i < bookingList.size() ? bookingList.get(i) : "<missing>";

				if (!searchVal.equals(bookingVal)) {
					allMatch = false;
					log.ReportEvent("FAIL", "Mismatch in '" + label + "' at index " + (i + 1) +
							": Search Screen = '" + searchVal + "', Booking Screen = '" + bookingVal + "'");
				}
			}
		}

		screenShots.takeScreenShot1();

		if (allMatch) {
			log.ReportEvent("PASS", "All return flight details match between search and booking screens.");
		} else {
			Assert.fail("Return flight details do not match between search and booking screens.");
		}
	}

	// Method to get data from UI for Returning Flight
	public Map<String, List<String>> getDataFromUiForReturningFlight() {
		Map<String, List<String>> data = new LinkedHashMap<>();

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			// Class names for returning flight (excluding carrier & flight number)
			List<String> classNames = Arrays.asList(
					"tg-toorigin",
					"tg-todepdate",
					"tg-todeptime",
					"tg-todestination",
					"tg-toarrdate",
					"tg-toarrtime",
					"tg-intl-rtlayovercity",
					"tg-tocabinclass",
					"tg-toduration"
			);

			for (String className : classNames) {
				List<String> values = new ArrayList<>();
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
					List<WebElement> elements = driver.findElements(By.className(className));
					for (WebElement el : elements) {
						values.add(el.getText().trim());
					}
				} catch (Exception e) {
					System.out.println("Warning: Could not find elements for class: " + className);
				}
				data.put(className, values);
			}
			System.out.println(data);

		} catch (Exception e) {
			System.out.println("An error occurred while extracting returning flight data from the UI.");
			e.printStackTrace();
		}

		return data;
	}


	//Method to Validate Onward Date
	public void validateOnwardDatesSingleToMultiple() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Get the single full date element attribute "value" (e.g., value="4th-Jul-2025")
			WebElement fullDateElement = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.className("tg-fsonwarddate"))
			);
			String fullDateValue = fullDateElement.getAttribute("value").trim();
			String normalizedFullDate = normalizeToDayMonth(fullDateValue);

			// Get all short date elements (e.g., text like "4th Jul")
			List<WebElement> shortDateElements = wait.until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("tg-intlonward-depdate"))
			);

			for (int i = 1; i < shortDateElements.size(); i++) {
				String shortDateText = shortDateElements.get(i).getText().trim();
				String normalizedShortDate = normalizeToDayMonth(shortDateText + "-2025"); // Append year

				System.out.println("Comparing short date #" + (i + 1) + ": " + normalizedShortDate + " with full date: " + normalizedFullDate);

				if (!normalizedFullDate.equals(normalizedShortDate)) {
					throw new AssertionError("Date mismatch at index " + i + ": fullDate='" + normalizedFullDate + "', shortDate='" + normalizedShortDate + "'");
				}
			}

			System.out.println("All short dates match the full date.");

		} catch (Exception e) {
			throw new RuntimeException("Date validation failed: " + e.getMessage(), e);
		}
	}

	private String normalizeToDayMonth(String inputDate) {
		String cleaned = inputDate.replaceAll("(\\d+)(st|nd|rd|th)", "$1").replace("-", " ").trim();

		DateTimeFormatter parser = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(cleaned, parser);

		return date.format(DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH));
	}

	public void validateReturnDatesSingleToMultiple() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Get single full return date from attribute "value"
			WebElement fullReturnDateElement = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.className("tg-fsreturndate"))
			);
			String fullReturnDateValue = fullReturnDateElement.getAttribute("value").trim();
			String normalizedFullReturnDate = normalizeToDayMonth(fullReturnDateValue);

			// Get multiple short return date elements (text)
			List<WebElement> shortReturnDateElements = wait.until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("tg-intlreturn-depdate"))
			);

			for (int i = 1; i < shortReturnDateElements.size(); i++) {
				String shortReturnDateText = shortReturnDateElements.get(i).getText().trim();
				String normalizedShortReturnDate = normalizeToDayMonth(shortReturnDateText + "-2025"); // append year

				System.out.println("Comparing return short date #" + (i + 1) + ": " + normalizedShortReturnDate
						+ " with full return date: " + normalizedFullReturnDate);

				if (!normalizedFullReturnDate.equals(normalizedShortReturnDate)) {
					throw new AssertionError("Return date mismatch at index " + i + ": fullReturnDate='"
							+ normalizedFullReturnDate + "', shortReturnDate='" + normalizedShortReturnDate + "'");
				}
			}

			System.out.println("All return short dates match the full return date.");

		} catch (Exception e) {
			throw new RuntimeException("Return date validation failed: " + e.getMessage(), e);
		}
	}

	//Method to Slide SlideBar from Left to Right
	public double[] adjustMinimumSliderValueByPercentage(WebDriver driver, double percentageValue) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate the first thumb input element (Thumb 0)
		WebElement sliderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@data-index='0']//input[@type='range'])[1]")));

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
		WebElement sliderTrack = driver.findElement(By.xpath("(//*[contains(@class, 'MuiSlider-track')])[1]"));
		int trackWidth = sliderTrack.getSize().getWidth();
		System.out.println("Slider Track Width: " + trackWidth);

		// Calculate offset in pixels
		int targetOffset = (int) (trackWidth * targetPercentage);
		System.out.println("Target Offset (pixels): " + targetOffset);

		// Get current thumb
		WebElement thumb = driver.findElement(By.xpath("(//*[@data-index='0']//input[@type='range'])[1]"));
		int currentOffset = thumb.getLocation().getX();
		System.out.println("Current Offset (pixels): " + currentOffset);

		int offsetDifference = targetOffset - currentOffset;
		System.out.println("Offset Difference: " + offsetDifference);

		// Move slider
		new Actions(driver).dragAndDropBy(thumb, offsetDifference, 0).perform();

		System.out.println("Slider moved to value corresponding to " + percentageValue + "%");

		return new double[]{minValue, maxValue};
	}

	//Method to Validate Price Range on the Price Filter
	public void verifyPriceRangeValuesOnResultScreen(Log Log, ScreenShots ScreenShots) {
		try {
			Thread.sleep(3000);

			// Get current slider values
			int min = Integer.parseInt(driver.findElement(By.xpath("(//input[@data-index='0'])[1]")).getAttribute("aria-valuenow"));
			int max = Integer.parseInt(driver.findElement(By.xpath("(//input[@data-index='1'])[1]")).getAttribute("aria-valuenow"));

			System.out.println("Slider Min: " + min);
			System.out.println("Slider Max: " + max);

			// Get all price elements
			List<WebElement> priceElements = driver.findElements(By.className(
					"tg-intlonward-price"
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
	// Method to adjust the maximum slider value by percentage from right (High to Low Price)
	public double[] adjustMaximumSliderValueByPercentage(WebDriver driver, double percentageValue) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate the second thumb (Thumb 1)
		WebElement sliderInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//*[@data-index='1']//input[@type='range'])[1]")));

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
		WebElement sliderTrack = driver.findElement(By.xpath("(//*[contains(@class, 'MuiSlider-track')])[1]"));
		Point trackLocation = sliderTrack.getLocation();
		int trackStartX = trackLocation.getX();
		int trackWidth = sliderTrack.getSize().getWidth();

		// Calculate the target X coordinate
		int targetX = (int) (trackStartX + (targetPercentage * trackWidth));

		// Get current thumb (Thumb 1) position
		WebElement thumb = driver.findElement(By.xpath("(//*[@data-index='1']//input[@type='range'])[1]"));
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

	//Method to get Default Min And Max Value
	public String[] getDefaultMinAndMaxValue() {
		String minPriceValue = driver.findElement(By.xpath("(//input[@data-index='1'])[1]")).getAttribute("aria-valuemin");
		String maxPriceValue = driver.findElement(By.xpath("(//input[@data-index='1'])[1]")).getAttribute("aria-valuemax");
		return new String[]{minPriceValue, maxPriceValue};

	}

	//Method to Validate Price and Total Price
	public void validateFareTypeAndPrice(String price,String fareType,Log Log, ScreenShots ScreenShots)
	{
		try{
			String searchScreenFareType=fareType+" "+"Fare";
           List<WebElement>  fareName=driver.findElements(By.className("flt-booking-faretype"));
		   for(WebElement name:fareName)
			{
				ValidateActualAndExpectedValuesForFlights(name.getText(), searchScreenFareType, "Fare Details in Search Screen and Booking Screen", Log);
			}
			price = driver.findElement(By.xpath("//*[text()='Grand Total']/parent::div//h6")).getText();
			ValidateActualAndExpectedValuesForFlights(price, price, "Price Details in Search Screen and Booking Screen", Log);

		}catch (Exception e)
		{
			Log.ReportEvent("FAIL", "An error occurred during date validation: " + e.getMessage());
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();
		}
	}

	// Method to Validate Layover Airlines from both Departing and Returning Flights
	public void validateLayoverAirlines(Log log, ScreenShots screenShots, String... layoverNames) {
		try {
			// List of class names to check
			List<String> layoverClassNames = Arrays.asList("tg-intl-owlayovercity", "tg-intl-rtlayovercity");

			// Extract expected codes from input names
			for (String name : layoverNames) {
				int start = name.indexOf('(');
				int end = name.indexOf(')');
				String expectedCode = "";
				if (start != -1 && end != -1 && start < end) {
					expectedCode = name.substring(start + 1, end);
				}

				boolean codeFound = false;

				for (String className : layoverClassNames) {
					List<WebElement> layoverElements = driver.findElements(By.className(className));

					for (WebElement el : layoverElements) {
						String text = el.getText();
						int startIdx = text.indexOf('(');
						int endIdx = text.indexOf(')');
						if (startIdx != -1 && endIdx != -1 && startIdx < endIdx) {
							String actualCode = text.substring(startIdx + 1, endIdx);
							System.out.println(actualCode);
							if (expectedCode.equalsIgnoreCase(actualCode)) {
								codeFound = true;
								log.ReportEvent("PASS", "Layover airline found: " + expectedCode + " in " + className);
								break;
							}
						}
					}

					if (codeFound) break; // Found in one class, no need to check further
				}

				if (!codeFound) {
					log.ReportEvent("FAIL", "Layover airline NOT found: " + expectedCode);
					screenShots.takeScreenShot1();
					Assert.fail("Missing layover airline code: " + expectedCode);
				}
			}

		} catch (Exception e) {
			log.ReportEvent("FAIL", "Error occurred while validating layover airlines: " + e.getMessage());
			screenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail("Exception occurred during layover airline validation");
		}
	}

	//Method to click on Refundable Fare
	public void clickOnRefundableFare() throws InterruptedException {
		Thread.sleep(2000);
		WebElement refundableFair=driver.findElement(By.xpath("//*[contains(@class,'tg-flrefundablefare')]//input"));
		refundableFair.click();
		Thread.sleep(2000);
	}


	//Method to Validate Flight details from Booking Screen
	public String[] getFlightDetailsFromBookingScreen(Log Log, ScreenShots ScreenShots) {
		String departingOrigin = null;
		String departingDestination = null;
		String departingDate = null;
		String departingTime = null;
		String arrivalDate = null;
		String arrivalTime=null;
		String returnOrigin = null;
		String returnDestination = null;
		String returnDepartingDate = null;
		String returnDepartingTime = null;
		String returnArrivalDate = null;
		String returnArrivalTime=null;
		try {
			departingOrigin = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepartorigin')])[1]")).getText();
			departingDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepartdepdate')])[1]")).getText();
			departingTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepartdeptime ')])[1]")).getText();

			departingDestination = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepartdestination')])[last()]")).getText();
			arrivalDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepartarrdate')])[last()]")).getText();
			arrivalTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbdepartarrtime')])[last()]")).getText();

			returnOrigin = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbreturnorigin')])[1]")).getText();
			returnDepartingDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbreturndepdate')])[1]")).getText();
			returnDepartingTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbreturndeptime')])[1]")).getText();

			returnDestination = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbreturndestination')])[last()]")).getText();
			returnArrivalDate = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbreturnarrdate')])[last()]")).getText();
			returnArrivalTime = driver.findElement(By.xpath("(//*[contains(@class,'tg-fbreturnarrtime')])[last()]")).getText();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Unable to get data from Search Screen");
			e.printStackTrace();
			Assert.fail();

		}
		return new String[]{departingOrigin,departingDate,departingTime,departingDestination,arrivalDate,arrivalTime,returnOrigin,returnDepartingDate,returnDepartingTime,returnDestination,returnArrivalDate,returnArrivalTime};
	}

	//Method to Select Flights
	public void selectFromFlights(int index)
	{
		try{
			driver.findElement(By.xpath("(//*[@data-tgflightcard='from'])["+index+"]")).click();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//Method to Select Flights
	public void selectToFlights(int index)
	{
		try{
			driver.findElement(By.xpath("(//*[@data-tgflightcard='to'])["+index+"]")).click();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void clickOnFromViewFlights(int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By locator = By.xpath("(//*[contains(@class,'round-trip-from-results')]//button)[" + index + "]");

		// Wait until the element is present and visible
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		// Scroll the element into view using JavaScript
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

		// Optionally wait for scroll to settle
		try {
			Thread.sleep(500); // Slight pause after scroll
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		// Click the element using JavaScript (more reliable if normal click fails)
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}



	//Method to Click on View Flights
	public void clickOnToViewFlights(int index)
	{
		driver.findElement(By.xpath("(//*[contains(@class,'round-trip-to-results')]//button)["+index+"]")).click();
	}



}
