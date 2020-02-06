package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class helper {
	
	//Screenshots, alerts, frames multiple windows,Sync issues
	
	//here I've implemented screenshots to be captured when called in a test case and to store them in the proper folder
	public static String captureScreenshot(WebDriver driver)
	{
		
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String screenshotPath=System.getProperty("user.dir")+"/Screenshots/HRMTest"+ getCurrentDateTime() +".png";
	try {
		FileHandler.copy(src, new File(screenshotPath));
		
		System.out.println("Screenshot captured");
		
	} catch (IOException e) {
		
		System.out.println("Unable to capture screenshot "+e.getMessage());
	}
	
	return screenshotPath;
			
	}

	//This method will attach the current date and time to the screenshot thats been taken
	public static String getCurrentDateTime()
	{
		DateFormat customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		
		Date currentDate=new Date();
		
		return customFormat.format(currentDate);
	}

	
}