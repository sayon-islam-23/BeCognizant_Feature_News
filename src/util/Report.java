package util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseClasses.BaseClass;

import com.aventstack.extentreports.ExtentTest;
public class Report extends BaseClass{

	public static ExtentReports extent;
    public static ExtentTest logger;
    
    public static void setupExtentReports() {
    	
    //creating an instance of the ExtentHtmlReporter class.
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//Outputs//extent-report.html");
    //creating a new extent report.
    extent = new ExtentReports();
    // it will use the htmlReporter instance to create a new Extent Report as HTML.
    extent.attachReporter(htmlReporter);
    // create test cases with this heading and description.
    logger= extent.createTest("Feature News", "Test Description");
    }
    
    public static  void reportFail(String report) {
    	// when a test failed
        logger.log(Status.FAIL, report);
    }
    public static  void reportPass(String report) {
    	// when a test passed.
        logger.log(Status.PASS, report);
    }
    public  static void endReport() {
    	// closing the report.
        extent.flush();
    }
}
