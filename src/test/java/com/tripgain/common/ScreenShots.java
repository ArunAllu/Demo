package com.tripgain.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.tripgain.testscripts.BaseClass;

public class
ScreenShots{
    WebDriver driver;
    ExtentTest test;
	public ScreenShots(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	//Method to Take Screenshot as base64
	public void takeScreenShot() {
	  	   try {
	            // Capture screenshot as Base64 string instead of file
	            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

	            // Log screenshot directly in the report without saving to a file
	            test.log(Status.INFO,
	                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//	             test.log(Status.INFO, "Screenshot captured", MediaEntityBuilder.createScreenCaptureFromPath(base64Screenshot).build());

	        } catch (Exception e) {
	            test.log(Status.FAIL, "#TAKE SCREENSHOT" + driver.getTitle() + " * UNABLE TO CAPTURE SCREENSHOT* ");
	            e.printStackTrace();
	        }
	    
	}
	 public void takeScreenShot1() {  
	              	       
	            try {

	                String sProjectName = "TripGain";
	                Date d = new Date();
	                String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
	                File classpathRoot = new File(System.getProperty("user.dir"));

	                File app = new File("src//test//resources//Reports//screenshots//");
	                String filePath = app.toString() + "//" + screenshotFile;

	                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	                try {
	                    // String relative = new File(base).toURI().relativize(new
	                    // File(path).toURI()).getPath();
	                    FileUtils.copyFile(scrFile, new File(filePath));
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }

	                File appScreenshot = new File("screenshots//");
	                String filePathScreenshot = appScreenshot.toString() + "//" + screenshotFile;// SA

//					test.addScreenCaptureFromPath(filePathScreenshot);
	                //test.log(Status.INFO, "#TAKE SCREENSHOT" + driver.getTitle() + " * SCREENSHOT CAPTURED * ");
	                test.log(Status.INFO, "Screenshot captured", MediaEntityBuilder.createScreenCaptureFromPath(filePathScreenshot).build());
	            } catch (Exception e) {
	                test.log(Status.FAIL, "#TAKE SCREENSHOT" + driver.getTitle() + " * UNABLE TO CAPTURE SCREENSHOT* ");
	            }
	        }
	 
	 
}
	    

