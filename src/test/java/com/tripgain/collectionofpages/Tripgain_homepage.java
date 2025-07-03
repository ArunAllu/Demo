package com.tripgain.collectionofpages;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;

import com.tripgain.common.TestExecutionNotifier;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tripgain.common.Log;
import com.tripgain.common.ScreenShots;

public class Tripgain_homepage {

	WebDriver driver;
	

	public Tripgain_homepage(WebDriver driver) {

        PageFactory.initElements(driver, this);
		this.driver=driver;
	}



	@FindBy(xpath = "//input[@id='TGFl-origin']")
	WebElement fromLocation;

	@FindBy(xpath = "//div[@class='airport-focused airport-option']")
	WebElement enterFromLocation;

	@FindBy(xpath = "//input[@id='TGFl-destination']")
	WebElement toLocation;

	@FindBy(xpath = "//input[@id='TGFl-destination']/parent::div/parent::div/parent::div/following-sibling::div//div[@class='airport-focused airport-option']")
	WebElement enterToLocation;

	@FindBy(xpath = "//div[@class='MuiGrid2-root MuiGrid2-direction-xs-row css-uzfmmu']")
	WebElement searchButton;

	@FindBy(xpath = "//input[contains(@class,'tg-fsonwarddate')]")
	WebElement datePickerInput;

	@FindBy(xpath = "//div[@class='react-datepicker-wrapper']//input[contains(@class ,'DayPickerInput input react')]")
	WebElement monthAndYearCaption;

	@FindBy(xpath = "//button[@class='react-datepicker__navigation react-datepicker__navigation--next']")
	WebElement nextButton;


	// Method to Set From location
	public void setFromLocation(String fromLocations) {
		TestExecutionNotifier.showExecutionPopup();
		fromLocation.sendKeys(fromLocations); 
	}

	// Method to Click On From Suggestion
	public void clickOnFromSuggestion() throws AWTException {
		enterFromLocation.click();
		/*Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		 */
	}
	

	// Method to Set To location
	public void setToLocation(String toLocations) {
		TestExecutionNotifier.showExecutionPopup();
		toLocation.sendKeys(toLocations);
	}




	// Method to Click on To Suggestion
	public void clickOnToSuggestion() throws AWTException {
		enterToLocation.click();
		/*	Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		 */
	}	
	
// Method to Search Flights on Home Page, Measure Load Time, and Verify Flights Loaded Correctly
public void searchFlightsOnHomePage(String fromLocations, String toLocations, String day, String MonthandYear, String classes, int Adults, Log Log, ScreenShots ScreenShots) {
	try {
		setFromLocation(fromLocations);
		clickOnFromSuggestion();
		setToLocation(toLocations);
		clickOnToSuggestion();
		selectDate(day, MonthandYear);
		clickOnClassesDropDown();
		selectClasses(classes);
		selectAdults(Adults);
		clickOnDone();

		long startTime = System.currentTimeMillis();

		clickOnSearchButton();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@class,'tg-flightcarrier')])[1]")));

		long endTime = System.currentTimeMillis();
		long loadTimeInSeconds = (endTime - startTime) / 1000;

		Log.ReportEvent("INFO", "Flight search results loaded in " + loadTimeInSeconds + " seconds");
		System.out.println("Flight search results loaded in " + loadTimeInSeconds + " seconds");

		// Safe check for AirplaneIcon presence
		List<WebElement> airplaneIcons = driver.findElements(By.xpath("//*[@data-testid='AirplaneIcon']"));
		if (!airplaneIcons.isEmpty() && airplaneIcons.get(0).isDisplayed()) {
			Log.ReportEvent("FAIL", "AirplaneIcon is displayed, indicating flights did NOT load properly.");
			System.out.println("AirplaneIcon displayed - failing the test.");
			Assert.fail("Flights did NOT load properly - AirplaneIcon displayed.");
		}

	} catch (Exception e) {
		Log.ReportEvent("FAIL", "User Search was Unsuccessful: " + e.getMessage());
		ScreenShots.takeScreenShot();
		e.printStackTrace();
		Assert.fail("Exception in searchFlightsOnHomePage: " + e.getMessage());
	}
}




	//Method to Search Flights on Home Page
	public void searchFlightsOnHomePageForOneWay(String fromLocations,String toLocations,String day, String MonthandYear,String classes,int Adults,Log Log,ScreenShots ScreenShots)
	{
		try {
			setFromLocation(fromLocations);
			clickOnFromSuggestion();
			setToLocation(toLocations);
			clickOnToSuggestion();
			selectDate(day,MonthandYear);
			clickOnClassesDropDown();
			selectClasses(classes);
			selectAdults(Adults);
			clickOnDone();
			clickOnSearchButton();
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "User Search was UnSuccessful");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}
		
	/*
	//Method to Select Date
	public void selectDatePicker(String day, String targetMonthAndYear) throws InterruptedException {
		// Click the input to open the calendar (if needed)
		datePickerInput.click();
		Thread.sleep(500); // Allow time for calendar popup to appear

		String displayedDate = monthAndYearCaption.getText();

		while (!monthAndYearCaption.getText().equals(targetMonthAndYear)) {
			nextButton.click();
			Thread.sleep(300); // Wait for the calendar to update
		}

		WebElement targetDay = driver.findElement(By.xpath("//div[text()='" + day + "']"));
		targetDay.click();
		Thread.sleep(500); // Optional: allow time for any post-click effects
	}
	 */
	// Method to Click on Search Button
	public void clickOnSearchButton()
	{
		searchButton.click();
	}
	
	
	// Method to Click on Search Button
		public void logOutFromApplication(Log Log,ScreenShots ScreenShots) throws InterruptedException
		{
			try {
				driver.findElement(By.xpath("//button[text()='Mr Test Traveller ID']")).click();
				Thread.sleep(2000);	
				driver.findElement(By.xpath("//li[text()='Log out']")).click();
				Thread.sleep(6000);			
				WebElement tripGainLogo=driver.findElement(By.xpath("//img[@alt='TripGain']"));
				if(tripGainLogo.isDisplayed())
				{
					Log.ReportEvent("PASS", "User LogOut was Successful");
					ScreenShots.takeScreenShot1();
				}
				else {
					Log.ReportEvent("FAIL", "User LogOut was UnSuccessful");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			}
			catch(Exception e)
			{
				Log.ReportEvent("FAIL", "User LogOut was UnSuccessful");
				ScreenShots.takeScreenShot1();
				e.printStackTrace();
				Assert.fail();

			}		
		}

	//Method to Select Date By Passing Three Paramenters(Mounth,Year,Date)
	public void selectdate(String mounth1, String Year1, String date1) throws InterruptedException {
		// Explicit wait to ensure the calendar is loaded before interacting with it
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for the calendar element to be visible
		datePickerInput.click();

		Thread.sleep(3000);

		// Get current month-year text
		String mounthyear = driver.findElement(By.xpath("(//div[@class=\"react-datepicker__header \"])[1]")).getText();

		// Extract month and year
		String mounth = mounthyear.split(" ")[0].trim();
		String year = mounthyear.split(" ")[1].trim();

		System.out.println(mounth + " " + year);

		// Loop until the correct month and year are found
		while (!(mounth.equals(mounth1) && year.equals(Year1))) {

			// Click next month button
			driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();

			// Wait for the month-year to be updated and get new value
			mounthyear = driver.findElement(By.xpath("(//div[@class='react-datepicker__header ']/child::h2)[1]")).getText();

			// Extract new month and year
			mounth = mounthyear.split(" ")[0].trim();
			year = mounthyear.split(" ")[1].trim();

			System.out.println(mounth + " " + year); // Print updated month-year for debugging
		}

		// Now, locate the date input and select the desired date (date1)
		// Constructing the XPath dynamically based on the date you want to select
		String dateXpath = "//div[@class='react-datepicker__day react-datepicker__day--" + String.format("%03d", Integer.parseInt(date1)) + "']";

		WebElement dateElement = driver.findElement(By.xpath(dateXpath));

		// Check if the day is available (not disabled) and click on it
		if (!dateElement.getAttribute("aria-disabled").equals("true")) {
			dateElement.click();
		} else {
			System.out.println("The selected date is disabled: " + date1);
			Assert.fail();

		}
	}

	///

	//Method to Click On Date
	public void clickdate()
	{
		datePickerInput.click();
	}

	//Method to Select Date By Passing Two Paramenters(Date and MounthYear)
	public void selectDate(String day, String MonthandYear) throws InterruptedException
	{
		TestExecutionNotifier.showExecutionPopup();
		datePickerInput.click();
		String Date=driver.findElement(By.xpath("(//div[@class='react-datepicker__header ']/child::h2)[1]")).getText();
		if(Date.contentEquals(MonthandYear))
		{
			Thread.sleep(4000);
			driver.findElement(By.xpath("(//div[@class='react-datepicker__month-container'])[1]//div[text()='"+day+"' and @aria-disabled='false']")).click();
			Thread.sleep(4000);
		}else {
			while(!Date.contentEquals(MonthandYear))
			{
				Thread.sleep(500);
				driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();
				if(driver.findElement(By.xpath("(//div[@class='react-datepicker__header ']/child::h2)[1]")).getText().contentEquals(MonthandYear))
				{
					driver.findElement(By.xpath("(//div[@class='react-datepicker__month-container'])[1]//div[text()='"+day+"' and @aria-disabled='false']")).click();
					break;
				}

			}
		}
	}

	//Method to Select Classes 
	public void selectClasses(String classes) throws InterruptedException {
		TestExecutionNotifier.showExecutionPopup();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='traveller-popup']//span[text()='"+classes+"']")).click();
	}

	//Method to Select Adults
	public void selectAdults(int Adults) throws InterruptedException
	{
		TestExecutionNotifier.showExecutionPopup();
		if(Adults==1)
		{
			System.out.println("Adults had been Selected");
		}
		else {
			for(int i=2;i<=Adults;i++)
			{
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@data-testid='PlusIcon']")).click();

			}
		}
	}

	//Method to Click On ClassDropDown
	public void clickOnClassesDropDown()
	{
		driver.findElement(By.xpath("//*[@data-testid='ChevronDownIcon']/preceding-sibling::span[@class='capitalize']")).click();
	}

	//Method Click ON Done Button
	public void clickOnDone()
	{
		driver.findElement(By.xpath("//button[text()='Done']")).click();
	}


	public void adjustSliderToValue(int targetValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

		// Locate the first thumb input element (for the first slider)
		WebElement sliderInput = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//input[@aria-orientation='horizontal' and @data-index='0']")
				));

		// Get min, max, and current values of the slider
		double minValue = Double.parseDouble(sliderInput.getAttribute("min"));
		double maxValue = Double.parseDouble(sliderInput.getAttribute("max"));
		double currentValue = Double.parseDouble(sliderInput.getAttribute("value"));

		System.out.println("Min Value: " + minValue);
		System.out.println("Max Value: " + maxValue);
		System.out.println("Current Value: " + currentValue);
		System.out.println("Target Value: " + targetValue);

		// Make sure the target value is within the min-max range
		if (targetValue < minValue) targetValue = (int) minValue;
		if (targetValue > maxValue) targetValue = (int) maxValue;

		// If the target value is already set, no need to adjust
		if (targetValue == currentValue) {
			System.out.println("Target value is the same as the current value. No adjustment needed.");
			return;
		}

		// Calculate the percentage of the slider to adjust based on target value
		double percentage = (targetValue - minValue) / (maxValue - minValue);

		// Locate the slider thumb's position (the thumb's left style value is what we need)
		WebElement thumb = driver.findElement(By.xpath("//span[@data-index='0']"));

		// Get the width of the slider
		int trackWidth = driver.findElement(By.xpath("//span[@class='MuiSlider-track']")).getSize().getWidth();
		int targetOffset = (int) (trackWidth * percentage);

		// Use JavaScriptExecutor to adjust the thumb's position (move it to the target value)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.left = arguments[1] + '%';", thumb, targetOffset);

		// Optionally: simulate an input event to trigger any associated UI updates
		js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", 
				sliderInput, String.valueOf(targetValue));
	}


	//Method to Check Error Message for From Field
	public void validateErrorMsgForFromField(String toLocations,String day, String MonthandYear,String classes,int Adults,Log Log,ScreenShots ScreenShots)
	{
		try {
			setToLocation(toLocations);
			clickOnToSuggestion();
			selectDate(day,MonthandYear);
			clickOnClassesDropDown();
			selectClasses(classes);
			Thread.sleep(3000);
			selectAdults(Adults);
			Thread.sleep(3000);
			clickOnDone();
			Thread.sleep(3000);
			clickOnSearchButton();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();

		}
	}
	//Method to Verify The From Field Error Message
	public void verifyErrorMsgForFromField(Log Log,ScreenShots ScreenShots) throws InterruptedException
	{
		try {
			WebElement errorMsgForFromField =driver.findElement(By.xpath("//span[@id='client-snackbar']"));
			if(errorMsgForFromField.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error Message is Displayed for From field (Select Origin field) is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error Message is not Displayed for From field (Select Origin field)");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error Message is not Displayed for From field (Select Origin field)");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}
	public void validateerrormsgforemptyTofield(String fromLocations,String day, String MonthandYear,String classes,int Adults,Log Log,ScreenShots ScreenShots)
	{
		try {
			setFromLocation(fromLocations);
			clickOnFromSuggestion();
//				setToLocation(toLocations);
//				clickOnToSuggestion();
			selectDate(day,MonthandYear);
			clickOnClassesDropDown();
			selectClasses(classes);
			Thread.sleep(3000);
			selectAdults(Adults);
			Thread.sleep(3000);
			clickOnDone();
			Thread.sleep(3000);
			clickOnSearchButton();
			Thread.sleep(2000);
			verifyErrorMsgForToField(Log, ScreenShots);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();

		}
	}
	//Method to Verify the To Field Error Message
	public void verifyErrorMsgForToField(Log Log,ScreenShots ScreenShots) throws InterruptedException
	{
		try {
			WebElement errorMsgForToField =driver.findElement(By.xpath("//span[@id='client-snackbar']"));
			if(errorMsgForToField.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error Message is Displayed for Empty To field (Select Origin field) is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error Message is not Displayed for To field (Select Origin field)");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error Message is not Displayed for To field (Select Origin field)");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}
	public void clearcalenderdefaulttext(String fromLocations,String toLocations,String classes,int Adults,Log Log,ScreenShots ScreenShots)
	{
		try {
			setFromLocation(fromLocations);
			clickOnFromSuggestion();
			setToLocation(toLocations);
			clickOnToSuggestion();
			Thread.sleep(3000);

			clearFromDate();
			Thread.sleep(3000);

//				selectDate(day,MonthandYear);
			clickOnClassesDropDown();
			selectClasses(classes);
			Thread.sleep(3000);
			selectAdults(Adults);
			Thread.sleep(3000);
			clickOnDone();
			Thread.sleep(3000);
			clickOnSearchButton();
			Thread.sleep(2000);
//				verifyerrormsgofemptyTofield(Log, ScreenShots);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();

		}
	}
	//Method to Clear From Date
	public void clearFromDate() throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement datePicker=driver.findElement(By.xpath("//input[@class='DayPickerInput input' and not(contains(@placeholder, 'Return Date (Optional)'))]"));
		Thread.sleep(1000);
		datePicker.sendKeys(Keys.CONTROL + "a") ;
		datePicker.sendKeys(Keys.BACK_SPACE);

	}

	//Method to Validate Calendar Error Message
	public void verifyErrorMsgForCalenderField(Log Log,ScreenShots ScreenShots) throws InterruptedException
	{
		try {
			WebElement errorMsgForCalenderField =driver.findElement(By.xpath("//*[text()='Select journey date.']"));
			if(errorMsgForCalenderField.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error Message is Displayed for Calender Field is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error Message is Not Displayed for Calender Field");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error Message is Not Displayed for Calender Field");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}
	//Method to Click Return Date
	public void clickOnReturnDate()
	{
		driver.findElement(By.xpath("//input[@placeholder='Return Date (Optional)']")).click();
	}

	//Method to Select Return Date By Passing Two Paramenters(Date and MounthYear)
	public void selectReturnDate(String returnDate, String returnMonthAndYear) throws InterruptedException
	{
		clickOnReturnDate();
		String Date=driver.findElement(By.xpath("(//div[@class='react-datepicker__header ']/child::h2)[1]")).getText();
		if(Date.contentEquals(returnMonthAndYear))
		{
			driver.findElement(By.xpath("(//div[@class='react-datepicker__month-container'])[1]//div[text()='"+returnDate+"' and @aria-disabled='false']")).click();
			Thread.sleep(4000);
		}else {
			while(!Date.contentEquals(returnMonthAndYear))
			{
				Thread.sleep(500);
				driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click();
				if(driver.findElement(By.xpath("(//div[@class='react-datepicker__header ']/child::h2)[1]")).getText().contentEquals(returnMonthAndYear))
				{
					driver.findElement(By.xpath("//div[text()='"+returnDate+"']")).click();
					break;
				}

			}
		}
	}
	//Method to Search Flights on Home Page
	public void searchFlightsOnHomePageForRoundTripForInternational(String fromLocations,String toLocations,String day, String MonthandYear,String returnDate,String returnMonthAndYear,String classes,int Adults,Log Log,ScreenShots ScreenShots)
	{
		try {
			setFromLocation(fromLocations);
			clickOnFromSuggestion();
			setToLocation(toLocations);
			clickOnToSuggestion();
			selectDate(day,MonthandYear);
			selectReturnDate(returnDate,returnMonthAndYear);
			clickOnClassesDropDown();
			selectClasses(classes);
			selectAdults(Adults);
			clickOnDone();

			long startTime = System.currentTimeMillis();

			clickOnSearchButton();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@class,'tg-intl-departing')])[1]")));

			long endTime = System.currentTimeMillis();
			long loadTimeInSeconds = (endTime - startTime) / 1000;

			Log.ReportEvent("INFO", "Flight search results loaded in " + loadTimeInSeconds + " seconds");
			System.out.println("Flight search results loaded in " + loadTimeInSeconds + " seconds");

			// Safe check for AirplaneIcon presence
			List<WebElement> airplaneIcons = driver.findElements(By.xpath("//*[@data-testid='AirplaneIcon']"));
			if (!airplaneIcons.isEmpty() && airplaneIcons.get(0).isDisplayed()) {
				Log.ReportEvent("FAIL", "AirplaneIcon is displayed, indicating flights did NOT load properly.");
				System.out.println("AirplaneIcon displayed - failing the test.");
				Assert.fail("Flights did NOT load properly - AirplaneIcon displayed.");
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "User Search was Unsuccessful: " + e.getMessage());
			ScreenShots.takeScreenShot();
			e.printStackTrace();
			Assert.fail("Exception in searchFlightsOnHomePage: " + e.getMessage());
		}

	}

	public void searchFlightsOnHomePageForRoundTripForDomestic(String fromLocations, String toLocations, String day,
															   String MonthandYear, String returnDate, String returnMonthAndYear, String classes, int Adults,
															   Log Log, ScreenShots ScreenShots) {

		try {
			setFromLocation(fromLocations);
			clickOnFromSuggestion();
			setToLocation(toLocations);
			clickOnToSuggestion();
			selectDate(day, MonthandYear);
			selectReturnDate(returnDate, returnMonthAndYear);
			clickOnClassesDropDown();
			selectClasses(classes);
			selectAdults(Adults);
			clickOnDone();

			long startTime = System.currentTimeMillis();

			clickOnSearchButton();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tg-sort-owcarrier")));

			long endTime = System.currentTimeMillis();
			long loadTimeInSeconds = (endTime - startTime) / 1000;

			Log.ReportEvent("INFO", "Flight search results loaded in " + loadTimeInSeconds + " seconds");
			System.out.println("Flight search results loaded in " + loadTimeInSeconds + " seconds");

			// Check for actual flight result cards instead of AirplaneIcon
			List<WebElement> flightResults = driver.findElements(By.xpath("//*[contains(@class,'round-trip-card-from')]"));
			if (flightResults.isEmpty()) {
				Log.ReportEvent("FAIL", "No flight results found.");
				ScreenShots.takeScreenShot();
				Assert.fail("Flights did NOT load properly - No flight results found.");
			} else {
				Log.ReportEvent("PASS", "Flight results loaded successfully.");
			}

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "User Search was Unsuccessful: " + e.getMessage());
			ScreenShots.takeScreenShot();
			e.printStackTrace();
			Assert.fail("Exception in searchFlightsOnHomePage: " + e.getMessage());
		}
	}

	//Method to Reduce Adults
	public void reduceAdults(int Adults) throws InterruptedException
	{
			for(int i=1;i<=Adults;i++)
			{
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@data-testid='MinusIcon']")).click();

			}

	}

	//Method to Validate From Date is Selected
	public void validateFromDateIsSelected(String dayStart,String month,String year,Log Log)
	{
		try{
			String fromDate=driver.findElement(By.xpath("//input[@placeholder='Journey Date']")).getAttribute("value");

			// Parse day
			int day = Integer.parseInt(dayStart);
			// Format with suffix
			String dayWithSuffix = getDayWithSuffix(day);
			// Final formatted date
			String mon=month.substring(0,3);
			String formattedDate = dayWithSuffix + "-" + mon+"-"+year;
			System.out.println(formattedDate);

			if(formattedDate.contentEquals(fromDate))
			{
				Log.ReportEvent("PASS", "From Date is Selected");

			}else{
				Log.ReportEvent("FAIL", "From Date is Not Selected");
				Assert.fail();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.ReportEvent("FAIL", "From Date is Not Selected");
			Assert.fail();
		}


	}
	//Method to Validate To Date is Selected
	public void validateToDateIsSelected(String dayReturn,String month,String year,Log Log)
	{
		try{
			String toDate=driver.findElement(By.xpath("//input[@placeholder='Return Date (Optional)']")).getAttribute("value");

			// Parse day
			int day = Integer.parseInt(dayReturn);
			// Format with suffix
			String dayWithSuffix = getDayWithSuffix(day);
			// Final formatted date
			String mon=month.substring(0,3);
			String formattedDate = dayWithSuffix + "-" + mon+"-"+year;
			System.out.println(formattedDate);

			if(formattedDate.contentEquals(toDate))
			{
				Log.ReportEvent("PASS", "To Date is Selected");

			}else{
				Log.ReportEvent("FAIL", "To Date is Not Selected");
				Assert.fail();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.ReportEvent("FAIL", "To Date is Not Selected");
			Assert.fail();
		}
	}

	public static String getDayWithSuffix(int day) {
		if (day >= 11 && day <= 13) {
			return day + "th";
		}
		switch (day % 10) {
			case 1: return day + "st";
			case 2: return day + "nd";
			case 3: return day + "rd";
			default: return day + "th";
		}
	}

	//Method to Click On Profile Hamburger
	public void clickOnProfileHamburger() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@data-testid='MoreVertIcon']/parent::button")).click();
	}

	//Method to Click On Profile
	public void clickOnProfile() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Profile']")).click();
	}

	//Method to get the Grade from Profile Screen
	public void getGradeTextFromProfileScreen(Log Log,ScreenShots ScreenShots) throws InterruptedException {
		Thread.sleep(1000);
		String grade=driver.findElement(By.xpath("//span[text()='Grade']/parent::div//h6")).getText();
		System.out.println(grade);
		Log.ReportEvent("INFO", "User Grade Name: "+" "+grade);
	}

	//Method to Click On Travel Drop Down
	public void clickOnTravelDropDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@data-testid='KeyboardArrowDownIcon']/parent::button[text()='Travel']")).click();
	}

	//Method to Select Travel Options
	public void selectTravelOptionsOnHomeScreen(String travelOption) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='"+travelOption+"']")).click();
		Thread.sleep(1000);

	}


}
