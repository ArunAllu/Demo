package com.tripgain.collectionofpages;

import java.time.Duration;

import com.tripgain.common.TestExecutionNotifier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tripgain.common.Log;
import com.tripgain.common.ScreenShots;
import org.testng.Assert;

import static org.testng.Assert.fail;

public class Tripgain_Login {
	WebDriver driver;

	public Tripgain_Login (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	@FindBy(name="username")
	public WebElement tripGainUserName;

	@FindBy(xpath = "//input[@name='password']") 
	public WebElement tripGainPassword;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement button;	


	//Method to Enter UserName
	public void enterUserName(String userName) throws InterruptedException {
		TestExecutionNotifier.showExecutionPopup();
		Thread.sleep(2000);// ADD THIS LINE
		tripGainUserName.sendKeys(userName);
	}

	//Method to Enter Password
	public void enterPasswordName(String password) throws InterruptedException {
		TestExecutionNotifier.showExecutionPopup();
		Thread.sleep(2000);// ADD THIS LINE
// ADD THIS LINE
		tripGainPassword.sendKeys(password);
	}

	//Method to Click on Login Button
	public void clickButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(button));
		button.click();
	}
	
	//Method to Verify Home Page is Displayed
	public void verifyHomePageIsDisplayed(Log Log,ScreenShots ScreenShots) throws InterruptedException {
		try {
			Thread.sleep(4000);
			TestExecutionNotifier.showExecutionPopup(); // ADD THIS LINE
			WebElement homePageLogo=driver.findElement(By.xpath("//img[@alt='TripGain']"));
			if(homePageLogo.isDisplayed())
			{
				Log.ReportEvent("PASS", "Home Page is displayed Successful");
				ScreenShots.takeScreenShot();
				Thread.sleep(3000);
			}
			else
			{
				Log.ReportEvent("FAIL", "Home Page is not displayed");
			    ScreenShots.takeScreenShot();
				Assert.fail();
			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Home Page is not displayed");
		    ScreenShots.takeScreenShot();
			e.printStackTrace();
			Assert.fail();

		}
	
	}

	//Method to Validate Password Error Message
	public void verifyInvalidPasswordErrorMessage(Log Log,ScreenShots ScreenShots) throws InterruptedException {
		try {
			WebElement invalidPasswordErrorMessage =driver.findElement(By.xpath("//div[@aria-describedby='client-snackbar']"));
			if(invalidPasswordErrorMessage.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error message is displayed for invalid password is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error message is not displayed for invalid password");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error message is not displayed for invalid password");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}

}
