
// FEACHING THE FEATURE NEWS FROM BE COGNIZANT

package baseClasses;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import util.Report;
import util.Screenshot;

public class BaseClass {

	public static WebDriver driver;

	@Test
	@BeforeTest
	public void setBrowser() throws InvalidFormatException, IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your Input => 1. Google Chrome 2. Microsoft Edge");
		int n = scan.nextInt();
		System.out.println();
		scan.close();
		if (n == 1) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("Tests are opening in Google Chrome");
		} else if (n == 2) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			System.out.println("Tests are opening in Microsoft Edge");
		} else {
			System.out.println("Wrong Browser Selected");
		}
	}

	@Test
	public void openBeCognizant() throws Exception {
		try {
			// creating fileReader instance to read the file.
			FileReader fr = new FileReader(System.getProperty("user.dir") + "\\Resources\\config.properties");
			// creating an instance of the properties file.
			Properties p = new Properties();
			// loading the data from the properties file.
			p.load(fr);
			// creating the Extent Report
			Report.setupExtentReports();
			String url = p.getProperty("url");
			driver.get(url);
			System.out.println("---------------------------------------------");
			System.out.println();
			System.out.println("One Cognizant Portal Opens");
			System.out.println();
			System.out.println("---------------------------------------------");
			Screenshot.takeSnapShotOne(driver, System.getProperty("user.dir") + "\\Screenshots\\screenshot_1.png");
			Thread.sleep(1000);
			Report.reportPass("Be Cognizant opening successfully");
		} catch (Exception e) {
			System.out.println("Be Cognizant is not opening");
			Report.reportFail("Be Cognizant is not opening");
		}
	}

	@Test
	@AfterTest
	public void closeBrowser() {
		System.out.println("----------------------------");
		System.out.println("Closing the Browser");
		System.out.println("----------------------------");
		Report.endReport();
		driver.quit();
	}
}
