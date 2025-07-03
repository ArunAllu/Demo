package com.tripgain.collectionofpages;

import com.tripgain.common.Log;
import com.tripgain.common.ScreenShots;
import com.tripgain.common.TestExecutionNotifier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Tripgain_ApprovalScreen {
	WebDriver driver;

	public Tripgain_ApprovalScreen(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	//Method to Click on View Details Button.
	public void clickOnViewDetailsButton() throws InterruptedException {
		driver.findElement(By.xpath("(//button[text()='Details'])[1]")).click();
		Thread.sleep(2000);
	}

	//Method to getFlightDetailsAndValidate
	public void validateFlightDetails()
	{
		try{


		}catch(Exception e){
			e.printStackTrace();
	}
	}


}
