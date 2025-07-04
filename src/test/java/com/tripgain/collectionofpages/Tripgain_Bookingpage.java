package com.tripgain.collectionofpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.tripgain.common.Log;
import com.tripgain.common.ScreenShots;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Tripgain_Bookingpage {
	
	WebDriver driver;
	

	public Tripgain_Bookingpage(WebDriver driver) {

		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void validateBookingScreenIsDisplayed(Log Log, ScreenShots ScreenShots)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			WebElement reviewPage = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(text(), 'Review Your Flight')]"))
			);

			Log.ReportEvent("PASS", "Review Your Flight Page is Displayed");
			ScreenShots.takeScreenShot();

		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Review Your Flight Page is Not Displayed: " + e.getMessage());
			ScreenShots.takeScreenShot();
			Assert.fail("Review Your Flight Page is Not Displayed: " + e.getMessage());
		}
	}
	
	//Method to Validate Price for Other Country Currency
		public void validatePriceForOtherCountryCurrency(String Price,String otherCountryCurrency,Log Log,ScreenShots ScreenShots)
		{
			try {	
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,300)", "");
				Thread.sleep(2000);
				String finalPrice=driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();
				String CurrencySymbol=finalPrice.split(" ")[0];
				String CurrencyValue=finalPrice.split(" ")[1];
				String onlyPrice = CurrencyValue.split("\n")[0];
				String finalPriceValue=CurrencySymbol+" "+onlyPrice;
				String otherCountryPriceValue=driver.findElement(By.xpath("//span[@class='other-currency-price bold']")).getText();
				System.out.println(finalPriceValue);


				ValidateActualAndExpectedValuesForFlights(otherCountryCurrency,otherCountryPriceValue,"Price value for Other Country Currency",Log);
				ValidateActualAndExpectedValuesForFlights(finalPriceValue,Price,"Price value for Indian Currency",Log);

			}catch(Exception e)
			{
				Log.ReportEvent("FAIL", "Grand Final Total Value Price is Not Currect"+ e.getMessage());
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		
		//Method to Validate Price for Indian Currency
		public void validatePriceForIndianCurrency(String Price,Log Log,ScreenShots ScreenShots)
		{
			try {	
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,300)", "");
				Thread.sleep(2000);
				String finalPrice=driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div//h6")).getText();		
				
				if(Price.contentEquals(finalPrice))
				{
					Log.ReportEvent("PASS", "Grand Final Total Price Value is Currect"+ finalPrice);
					ScreenShots.takeScreenShot1();
				}else {
					Log.ReportEvent("FAIL", "Grand Final Total Price Value is Not Currect"+ finalPrice);
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
			}catch(Exception e)
			{
				Log.ReportEvent("FAIL", "Grand Final Total Price Value is Not Currect"+ e.getMessage());
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		
		//Method to Click on Send Approval
		public void clickOnSendApprovalButton() throws InterruptedException
		{
			driver.findElement(By.xpath("//span[text()='Send for Approval']")).click();
			Thread.sleep(1000);
		}
		
		//Method to Validate Send Approval Toast 
		public void validateSendApprovalToastMessage(Log Log,ScreenShots ScreenShots)
		{
			try {
				String approvalToastMessage=driver.findElement(By.xpath("//span[@id='client-snackbar']")).getText();
				if(approvalToastMessage.contentEquals("Your request has been successfully submitted."))
				{
					Log.ReportEvent("PASS", "Send Approval is Successful");
					ScreenShots.takeScreenShot1();
				}else {
					Log.ReportEvent("FAIL", "Send Approval is Not Successful");
					ScreenShots.takeScreenShot1();
					Assert.fail();

				}
				Thread.sleep(3000);
			}catch(Exception e)
			{
				Log.ReportEvent("FAIL", "Send Approval is Not Successful"+ e.getMessage());
				ScreenShots.takeScreenShot1();
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
}
