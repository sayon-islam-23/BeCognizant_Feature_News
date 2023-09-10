package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@Test
public class Screenshot {
	public static void takeScreenshotTwo(WebDriver driver, String fileName) throws IOException {
		
		//it generates a timeStamp string in the format "yyyy.MM.dd.HH.mm.ss" 
		//based on the current date and time. This timeStamp can be used for naming files.
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		//Convert webDriver object into TakeScreenshot and calling the method getScreenshotAs
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//Copies the file into Screenshots folder along with the current timeStamp and saving it in the .png format
		FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\Screenshots\\" + fileName + "_" + timestamp + ".png"));
	}

	@Test
	public static void takeSnapShotOne(WebDriver webdriver, String fileWithPath) throws Exception {

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}
}
