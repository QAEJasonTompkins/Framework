package Pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utilities.BrowserFactory;
import Utilities.ExcelData;
import Utilities.helper;

public class BaseClass {
	 
	public WebDriver driver;
	public ExcelData excel;
	public ExtentReports report;
	public ExtentTest logger;
	
	//before the browser opens and the test runs here im setting up HTML reports to be made while test execution is occurring
	@BeforeSuite
	public void setUpSuite()
	{
		excel=new ExcelData();
		
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/HRMLogin+"+helper.getCurrentDateTime()+".html"));
		report=new ExtentReports();
		report.attachReporter(extent);
		

	}
	
	//WebDriver is retrieving the methods from our BrowserFactory class to open the browser and navigate to the QA environment
	@BeforeClass
	public void setup() {
		driver=BrowserFactory.startApplication(driver, "Chrome", "https://opensource-demo.orangehrmlive.com/");
	}

	//After all test steps have been completed the driver will close
	@AfterClass
	public void tearDown() {
		BrowserFactory.closeBrowser(driver);

	}
	
	//Here after each test step a screenshot will be taken and given either a pass fail result in html report
	@AfterMethod
	public void tearDownMethod(ITestResult result)
	{
	
		if(result.getStatus()==ITestResult.FAILURE)
		{
			helper.captureScreenshot(driver);
			try {
				logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(helper.captureScreenshot(driver)).build());
			} catch (Exception e) {
				
			}
		}
		else
		{
			try {
				logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(helper.captureScreenshot(driver)).build());
			} catch (IOException e) {
				
			}
		}
		
		//using the flush write all test logs to the proper file
		report.flush();
	}

}
