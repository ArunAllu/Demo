package com.tripgain.collectionofpages;

import java.awt.AWTException;
import java.time.Duration;

import com.tripgain.common.Log;
import com.tripgain.common.ScreenShots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Tripgain_registrationpage {

	WebDriver driver;

	public Tripgain_registrationpage(WebDriver driver) {

		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	
	@FindBy(xpath = "(//div[@role=\"combobox\"])[2]")
	WebElement Registrationas;

	public void selectregistrationas(String title) throws AWTException, InterruptedException {
	    new WebDriverWait(driver, Duration.ofSeconds(90))
	        .until(ExpectedConditions.elementToBeClickable(Registrationas));
	    Registrationas.click();

	    Thread.sleep(2000);
	   
	    driver.findElement(By.xpath("//li[text()='"+title+"']")).click();
	   
	   
	}



	@FindBy(xpath="//input[@name=\"first_name\"]")
	WebElement firstname;

	public void enterfirstname() {
	    firstname.sendKeys("Test");
	}

	@FindBy(xpath="//input[@name=\"last_name\"]")
	WebElement lastname;

	public void enterlastname() {
	    lastname.sendKeys("Travel");
	}

	@FindBy(xpath="//input[@name=\"company\"]")
	WebElement companyname;

	public void entercompanyname() {
	    companyname.sendKeys("TripGain");
	}

	@FindBy(xpath="//input[@name=\"contact_email\"]")
	WebElement workemail;

	public void enterworkemail() {
	    workemail.sendKeys("testtravel@tripgain.com");
	}

	@FindBy(xpath="//input[@name=\"mobile_number\"]")
	WebElement mobile;

	public void entermobile() {
	    mobile.sendKeys("9882981289");
	}

	@FindBy(xpath="//input[@name=\"password\"]")
	WebElement password;

	public void enterpassword() {
	    password.sendKeys("Testtravel@tripgain");
	}

	@FindBy(xpath = "//button[text()='Register']")
	WebElement registerationbutton;
	public void clickregisterbutton() {
	     registerationbutton.click();
	     String regtext = registerationbutton.getText();
	     System.out.println(regtext);
	}


	//Method to Click on Submit
	public void submitButton()
	{
		WebElement btn = driver.findElement(By.xpath("//button[@type='submit']"));
		btn.click();
	}

	//Method to Click on Register Button
	public void registerButton()
	{
		driver.findElement(By.xpath("//button[text()='Register']")).click();
	}

	//Method to Enter First Name Text
	public void firstNameField(String firstName)
	{
		WebElement firstNameTextField = driver.findElement(By.xpath("//input[@name='first_name']"));
		firstNameTextField.sendKeys(firstName);
	}

	//Method to Enter Last Name Text
	public void lastNameField(String lastName)
	{
		WebElement lastNameTextField = driver.findElement(By.xpath("//input[@name='last_name']"));
		lastNameTextField.sendKeys(lastName);
	}


	//Method to Enter Company Name
	public void companyField(String companyName)
	{
		WebElement company = driver.findElement(By.xpath("//input[@name='company']"));
		company.sendKeys(companyName);
	}

	//Method to Enter Email Field
	public void workEmailField(String email)
	{
		WebElement workEmail = driver.findElement(By.xpath("//input[@name='contact_email']"));
		workEmail.sendKeys(email);

	}

	//Method to Enter Mobile Number
	public void mobileNumberField(String MobileNumber)
	{
		WebElement number = driver.findElement(By.xpath("//input[@name='mobile_number']"));
		number.sendKeys(MobileNumber);
	}

	//Method to Enter Password Field
	public void passwordField(String password)
	{
		WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
		passwordField.sendKeys(password);
	}


	//Method to Validate Error Message for First Name Test Field
	public void verifyFirstNameErrorMessage(Log Log,ScreenShots ScreenShots) throws InterruptedException
	{
		try {
			WebElement firstNameErrorMessage =driver.findElement(By.xpath("(//div[@class='MuiFormControl-root MuiFormControl-marginNormal MuiFormControl-fullWidth MuiTextField-root css-u8cmif'])[2]/parent::div//p[text()='Please enter First Name']"));
			if(firstNameErrorMessage.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error Message is Displayed for FirstName Field is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error Message is Not Displayed for FirstName Field");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error Message is Not Displayed for FirstName Field");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}

	}


	//Method to Validate Last Name Error Message
	public void verifyLastNameErrorMessage(Log Log,ScreenShots ScreenShots) throws InterruptedException {
		try {
			WebElement lastNameErrorMessage = driver.findElement(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl css-ib0ccs']/parent::div/parent::div//p[text()='Please enter Last Name']"));
			if (lastNameErrorMessage.isDisplayed()) {
				Log.ReportEvent("PASS", "Error Message is Displayed for LastName Field is Successful");
				ScreenShots.takeScreenShot1();
			} else {
				Log.ReportEvent("FAIL", "Error Message is Not Displayed for LastName Field");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		} catch (Exception e) {
			Log.ReportEvent("FAIL", "Error Message is Not Displayed for LastName Field");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to Validate Company Field Error Message
	public void verifyCompanyFieldErrorMessage(Log Log,ScreenShots ScreenShots) throws InterruptedException
	{
		try {
			WebElement CompanyFieldErrorMessage =driver.findElement(By.xpath("(//div[@class='MuiFormControl-root MuiFormControl-marginNormal MuiFormControl-fullWidth MuiTextField-root css-u8cmif'])[5]/parent::div//p[text()='Please enter Company Name']"));
			if(CompanyFieldErrorMessage.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error Message is Displayed for Company Field is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error Message is not Displayed for Company Field");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error Message is not Displayed for Company Field");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}




	}

	//Method to Validate Work Email Error Message
	public void verifyWorkEmailFieldErrorMessage(Log Log,ScreenShots ScreenShots) throws InterruptedException
	{
		try {
			WebElement workEmailFieldErrorMessage =driver.findElement(By.xpath("(//div[@class='MuiFormControl-root MuiFormControl-marginNormal MuiFormControl-fullWidth MuiTextField-root css-u8cmif'])[6]/parent::div//p[text()='Please enter Work Email']"));
			if(workEmailFieldErrorMessage.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error Message is Displayed for Work Email Field is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error Message is Not Displayed for Work Email Field");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error Message is Not Displayed for Work Email Field");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to Validate Mobile Number Error Message
	public void verifyMobileNumberFieldErrorMessage(Log Log,ScreenShots ScreenShots) throws InterruptedException
	{

		try {
			WebElement mobileNumberFieldErrorMessage =driver.findElement(By.xpath("(//div[@class='MuiFormControl-root MuiFormControl-marginNormal MuiFormControl-fullWidth MuiTextField-root css-u8cmif'])[7]/parent::div//p[text()='Please enter Mobile number']"));
			if(mobileNumberFieldErrorMessage.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error Message is Displayed for Mobile Number Field is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error Message is not Displayed for Mobile Number Field");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error Message is not Displayed for Mobile Number Field");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}


	//Method to Validate Password Field Error Message
	public void verifyPasswordFieldErrorMessage(Log Log,ScreenShots ScreenShots) throws InterruptedException
	{
		try {
			WebElement passwordFieldErrorMessage =driver.findElement(By.xpath("(//div[@class='MuiFormControl-root MuiFormControl-marginNormal MuiFormControl-fullWidth MuiTextField-root css-u8cmif'])[8]/parent::div//p[text()='Please enter Password']"));
			if(passwordFieldErrorMessage.isDisplayed())
			{
				Log.ReportEvent("PASS", "Error Message is Displayed for Password Field is Successful");
				ScreenShots.takeScreenShot1();
			}
			else
			{
				Log.ReportEvent("FAIL", "Error Message is Not Displayed for Password Field");
				ScreenShots.takeScreenShot1();
				Assert.fail();

			}
		}
		catch(Exception e)
		{
			Log.ReportEvent("FAIL", "Error Message is Not Displayed for Password Field");
			ScreenShots.takeScreenShot1();
			e.printStackTrace();
			Assert.fail();

		}
	}

	//Method to Validate All Fields Error Message on Register Screen
	public void validateAllFieldsErrorMsgOnRegisterPage(Log Log, ScreenShots ScreenShots) throws InterruptedException
	{
		registerButton();
		Thread.sleep(3000);
		submitButton();
		verifyFirstNameErrorMessage(Log, ScreenShots);
		verifyLastNameErrorMessage(Log, ScreenShots);
		verifyCompanyFieldErrorMessage(Log, ScreenShots);
		verifyMobileNumberFieldErrorMessage(Log, ScreenShots);
		verifyPasswordFieldErrorMessage(Log, ScreenShots);
	}
}


