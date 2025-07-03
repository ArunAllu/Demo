package com.tripgain.testscripts;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.tripgain.common.EmailUtils;
import com.tripgain.common.ExtantManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;

public class BaseClass{

      
		WebDriver driver;
		public WebDriver launchBrowser(String browser,String url)
	{
		   if (browser.equalsIgnoreCase("chrome")) {
	            // Set the path to the ChromeDriver executable (optional if already set in system PATH)
	            driver = new ChromeDriver();

	        } else if (browser.equalsIgnoreCase("firefox")) {
	            // Set the path to the GeckoDriver executable (optional if already set in system PATH)
	            driver = new FirefoxDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            // Set the path to the EdgeDriver executable (optional if already set in system PATH)
	            driver = new EdgeDriver();
	        } else {
	            throw new IllegalArgumentException("Unsupported browser: " + browser);
	        }
		    driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			return driver;
	    }

	@AfterSuite
	public void afterSuite() {
		ExtantManager extantManager = new ExtantManager();
		String reportPath = extantManager.getReportFilePath();

		if (reportPath != null) {
			String[] bccRecipients = {
					"sudheer@tripgain.com","ranga@tripgain.com","ashutosh@tripgain.com"
			};

			try {
				EmailUtils.sendReportByEmail(reportPath, bccRecipients);
			} catch (Exception e) {
				System.err.println("❌ Failed to send email: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("❌ Report not generated. Skipping email.");
		}
	}




//		@AfterClass
//		public void tearDown()
//		{
//			ExtantManager extantManager=new ExtantManager();
//			extantManager.finalizeExtentReport();
//			extantManager.flushReport();
//			}
	
}
