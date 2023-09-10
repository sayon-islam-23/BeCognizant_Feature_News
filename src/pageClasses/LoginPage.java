package pageClasses;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import util.Report;
import util.Screenshot;

public class LoginPage extends BaseClass {
	
	//Page Object Model (POM) is a design pattern in Selenium that creates an object repository for storing web elements. 
	//It helps reduce code duplication and improves test case maintenance.
	
	By emailPath = By.xpath("//input[@placeholder='Email, phone, or Skype']");
	By nextPath = By.xpath("//input[@value='Next']");
	By emailError = By.xpath("//div[@class='col-md-24 error ext-error']");
	By passWordpath = By.xpath("//input[@placeholder='Password']");
	By passwordError = By.xpath("//div[@id='passwordError']");
	By yessPath = By.xpath("//input[@value='Yes']");
	By SignPath = By.xpath("//input[@value='Sign in']");
	
	@Test
	public void inputEmail() throws Exception {
		// getting email from the getEmail() method.
		String email = getEmail();
		Thread.sleep(2000);
		// sending the password
		driver.findElement(emailPath).sendKeys(email);
		Thread.sleep(1000);
		// taking screenshot
		Screenshot.takeSnapShotOne(driver, System.getProperty("user.dir") + "\\Screenshots\\screenshot_2.png");
		driver.findElement(nextPath).click();
		Thread.sleep(1000);
		try {
			System.out.println(driver.findElement(emailError).getText());
			Thread.sleep(1000);
			// report failed.
			Report.reportFail("Email id is not provided");
			driver.quit();
			System.out.println("Driver Closed");
		} catch (Exception e) {
			System.out.println("Email Id Provided");
			//report passed.
			Report.reportPass("Email id provided successfully");
		}
	}

	@Test
	public void inputPassword() throws Exception {
		// getting password from the getPassword() method.
		String password = getPassword();
		Thread.sleep(2000);
		// sending the password
		driver.findElement(passWordpath).sendKeys(password);
		Thread.sleep(1000);
		// taking screenshot
		Screenshot.takeSnapShotOne(driver, System.getProperty("user.dir") + "\\Screenshots\\screenshot_3.png");
		driver.findElement(SignPath).click();
		// waiting for the mobile authentication
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		try {
			System.out.println(driver.findElement(passwordError).getText());
			Thread.sleep(1000);
			// report failed.
			Report.reportFail("Password is not provided");
			driver.quit();
			System.out.println("Driver Closed");
		} catch (Exception e) {
			driver.findElement(yessPath).click();
			System.out.println("Password Provided");
			// report passed
			Report.reportPass("Password provided successfully");
		}
	}

	public static String getEmail() throws Exception {
		//getting email from the email.properties file.
		// creating fileReader instance to read the file.
		FileReader fr = new FileReader(System.getProperty("user.dir") + "\\Resources\\email.properties");
		// creating an instance of the properties file.
		Properties p = new Properties();
		// loading the data from the properties file.
		p.load(fr);
//		Report.setupExtentReports();
		// ending the email.
		String email = p.getProperty("email");
		Thread.sleep(1000);
		return email;
	}

	public static String getPassword() throws Exception {
		//getting email from the password.properties file.
		// creating fileReader instance to read the file.
		FileReader fr = new FileReader(System.getProperty("user.dir") + "\\Resources\\password.properties");
		// creating an instance of the properties file.
		Properties p = new Properties();
		// loading the data from the properties file.
		p.load(fr);
//		Report.setupExtentReports();
		// ending the password.
		String password = p.getProperty("password");
		Thread.sleep(1000);
		return password;
	}

}
