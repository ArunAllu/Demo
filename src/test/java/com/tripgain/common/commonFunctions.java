package com.tripgain.common;

import com.aventstack.extentreports.ExtentTest;
import com.tripgain.testscripts.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class commonFunctions {
    public WebDriver driver;
    public ExtentTest test;

    public commonFunctions(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    //Method to Click on Element by Using JavaScript Executer
    public void WebClickUsingJS(String sEleXpath) {

        WebElement element = driver.findElement(By.xpath(sEleXpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

    }

    //Method to Clear Existing Data and Enter Data
    public void WebEdit(String sEleXpath, String sEleValue) throws Exception {
        WebClickUsingJS(sEleXpath);
        driver.findElement(By.xpath(sEleXpath)).sendKeys(sEleValue);
    }

    //Method to Click on Fields
    public void WebClick(String sEleXpath) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.xpath(sEleXpath)).click();
    }

}
