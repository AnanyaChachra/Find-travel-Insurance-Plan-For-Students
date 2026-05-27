package Utilities;
 
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;

import org.testng.ITestListener;

import org.testng.ITestResult;
 
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import Utilities.TakeScreenshot;
 
public class ExtentReportManager implements ITestListener {

    public static ExtentSparkReporter sparkReporter;

    public static ExtentReports extent;

    public static ExtentTest test;
 
    public void onStart(ITestContext context) {

        if (extent == null) {

            sparkReporter = new ExtentSparkReporter("target/spark.html");

            sparkReporter.config().setDocumentTitle("Report");

            sparkReporter.config().setReportName("testing");

            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("comp name", "localhost");

            extent.setSystemInfo("Tester name", "vineela");

            extent.setSystemInfo("os", "windows11");

            extent.setSystemInfo("Browser name", "chrome");

        }

    }
 
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName());

        test.log(Status.PASS, "Test case Passed: " + result.getName());

    }
 
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());

        test.log(Status.FAIL, "Test case Failed: " + result.getName());

        test.log(Status.FAIL, "Test case failed cause is: " + result.getThrowable());
 
        try {

            // 1. Fetch the active driver instance from the current test class instance

            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

            if (driver != null) {

                // 2. Initialize TakeScreenshot with the active driver

                TakeScreenshot ts = new TakeScreenshot(driver);

                // 3. Capture screenshot and get the relative path

                String path = ts.takeScreenshot(result.getName());

                // 4. Attach screenshot to Extent Report

                test.addScreenCaptureFromPath(path, "Failure Screenshot");

            } else {

                System.out.println("Driver is null. Cannot take screenshot.");

            }

        } catch (Exception e) {

            System.out.println("Failed to capture screenshot: " + e.getMessage());

            e.printStackTrace();

        }

    }
 
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());

        test.log(Status.SKIP, "test case skipped: " + result.getName());

    }
 
    public void onFinish(ITestContext context) {

        if (extent != null) {

            extent.flush(); 

        }

    }

}
 