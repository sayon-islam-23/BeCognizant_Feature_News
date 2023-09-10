package pageClasses;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import util.Report;
import util.Screenshot;

public class BeCognizantPortal extends BaseClass {
	
	//Page Object Model (POM) is a design pattern in Selenium that creates an object repository for storing web elements. 
	//It helps reduce code duplication and improves test case maintenance.
	
	By user = By.xpath("//div[@class='nvEhuDKzSmREBsqaIaOhu']");
	By userNamePath = By.id("mectrl_currentAccount_primary");
	By emailPath = By.id("mectrl_currentAccount_secondary");
	
	@Test
	public void getUserDetails() throws Exception {
		//This method displays the name of the headings of each featured news present in Be.Cognizant home page
		try {
			Thread.sleep(1000);
			//Locating the icon present in the top right corner of Be.Cognizant page
			driver.findElement(user).click();
			Thread.sleep(1000);
			
			//Stores the name of the user logged into the Be Cognizant page
			String name = driver.findElement(userNamePath).getText();
			
			System.out.println("Name : " + name);
			String email = driver.findElement(emailPath).getText();
			//Stores the email of the user logged into the Be Cognizant page
			
			System.out.println("Email : " + email);
			// taking screenshot of the home page
			
			Screenshot.takeSnapShotOne(driver, System.getProperty("user.dir")+"\\Screenshots\\screenshot_4.png");
			Report.reportPass("Be Cognizant is opening");
			//Displaying the message when the testCase is passed in HTML report
		} catch (Exception e) {
			
			//Displaying the message when the testCase is failed in HTML report
			Report.reportFail("Be Cognizant is not opening");
		}
	}
}
