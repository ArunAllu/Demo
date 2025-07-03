package com.tripgain.common;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtantManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final Object lock = new Object();
    private static boolean isInitialized = false;
    private static final Set<String> browserNamesSet = new HashSet<>();
    private static String reportFilePath; // 🔑 New field

    public void setUpExtentReporter(String browserName) {
        synchronized (lock) {
            if (!isInitialized) {
                LocalDate today = LocalDate.now();
                LocalTime time = LocalTime.now();

                reportFilePath = System.getProperty("user.dir") + File.separator +
                        "src" + File.separator + "test" + File.separator + "resources" +
                        File.separator + "Reports" + File.separator +
                        "myReport_" + today + "_" + time.toString().replace(".", "").replace(":", "-") + ".html";

                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
                sparkReporter.config().setReportName("TripGain Automation Report");
                sparkReporter.config().setTheme(Theme.STANDARD);

                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
                extent.setSystemInfo("Environment", "QA");
                extent.setSystemInfo("Tester Name", "Arun");
                extent.setSystemInfo("Test URL", "https://v3.tripgain.com/login");

                try {
                    InetAddress localHost = InetAddress.getLocalHost();
                    extent.setSystemInfo("Machine Name", localHost.getHostName());
                } catch (UnknownHostException e) {
                    System.err.println("Unable to retrieve host name: " + e.getMessage());
                }

                extent.setSystemInfo("OS", System.getProperty("os.name"));
                isInitialized = true;
            }
        }
    }

    public String getReportFilePath() {
        return reportFilePath;
    }

    public void flushReport() {
        synchronized (lock) {
            if (extent != null) {
                extent.flush();
            }
        }
    }
    // Other methods remain unchanged...

    public void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public ExtentReports getReport() {
        return extent;
    }
}
