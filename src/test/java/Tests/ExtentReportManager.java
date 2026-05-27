package Tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utilities.TakeScreenshot;
import base.BaseClass;



public class ExtentReportManager implements ITestListener
{
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    TakeScreenshot ts;
 
    public void onStart(ITestContext context)
    {

    	if (extent == null) 
    	{

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
    public void onTestSuccess(ITestResult result)
    {
        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test case Passed: " + result.getName());
    }
 
    @Override
    public void onTestFailure(ITestResult result) {
        
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test case Failed: " + result.getName());
        test.log(Status.FAIL, "Test case failed cause is: " + result.getThrowable());

        try {
            // Ensure driver is initialized
            if (BaseClass.driver != null) {

                TakeScreenshot ts = new TakeScreenshot(BaseClass.driver);
                String path = ts.takeScreenshot(result.getName());

                test.addScreenCaptureFromPath(path);

            } else {
                test.log(Status.WARNING, "Driver is null. Screenshot not captured.");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Exception while capturing screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void onTestSkipped(ITestResult result)
    {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "test case skipped: " + result.getName());
    }
 
    public void onFinish(ITestContext context)
    {
        extent.flush(); 
    }
}
