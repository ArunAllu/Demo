package com.tripgain.common;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

public class Log {
    WebDriver driver;
    ExtentTest test;

    public Log(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
    public String WARNINGFontColorPrefix = "<span style='font-weight:bold;'><font color='red';font-size:16px; line-height:20px>";
	public String WARNINGFontColorSuffix = "</font></span>";
	public String InfoFontColorPrefix = "<span style='font-weight:bold;'><font color='gold';font-size:16px; line-height:20px>";
	public String InfoFontColorSuffix = "</font></span>";
	public String SuccessFontColorPrefix = "<span style='font-weight:bold;'><font color='green';font-size:16px; line-height:20px>";
	public String SuccessFontColorSuffix = "</font></span>";
	public String INFOFontColorPrefix = "<span><font color='blue';font-size:16px; line-height:20px>";
	public String INFOFontColorSuffix = "</font></span>";
	public String FAILFontColorPrefix = "<span><font color='red';font-size:16px; line-height:20px>";
	public String FAILFontColorSuffix = "</font></span>";
	public void ReportEvent(String sStatus, String sDec) {

		if (sStatus.equalsIgnoreCase("pass")) {
			test.log(Status.PASS, SuccessFontColorPrefix + sDec + SuccessFontColorSuffix);
		}
		if (sStatus.equalsIgnoreCase("fail")) {
			test.log(Status.FAIL, WARNINGFontColorPrefix + sDec + WARNINGFontColorSuffix);
		}
		if ((sStatus.equalsIgnoreCase("info")) || (sStatus.equalsIgnoreCase(""))) {
			test.log(Status.INFO, INFOFontColorPrefix + sDec + INFOFontColorSuffix);
		}
//		if ((sStatus.equalsIgnoreCase("FAIL"))) {
//			test.log(Status.FAIL, FAILFontColorPrefix + sDec + FAILFontColorSuffix);
//		}
	}

	public void ReportEvent(String sStatus, Markup sDec) {

		if (sStatus.equalsIgnoreCase("pass")) {
		
			test.log(Status.PASS, SuccessFontColorPrefix + sDec + SuccessFontColorSuffix);
		}
		if (sStatus.equalsIgnoreCase("fail")) {
			test.log(Status.FAIL, WARNINGFontColorPrefix + sDec + WARNINGFontColorSuffix);
		}
		if ((sStatus.equalsIgnoreCase("info")) || (sStatus.equalsIgnoreCase(""))) {
			test.log(Status.INFO, INFOFontColorPrefix + sDec + INFOFontColorSuffix);
		}
		if ((sStatus.equalsIgnoreCase("FAIL"))) {
			test.log(Status.FAIL, FAILFontColorPrefix + sDec + FAILFontColorSuffix);
		}
	}
}
