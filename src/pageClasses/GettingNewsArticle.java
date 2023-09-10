package pageClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseClasses.BaseClass;
import util.Report;
import util.Screenshot;
import util.WriteExcelData;

public class GettingNewsArticle extends BaseClass {
	
	//Page Object Model (POM) is a design pattern in Selenium that creates an object repository for storing web elements. 
	//It helps reduce code duplication and improves test case maintenance.
	
	By newsTitlePath = By.xpath("//a[@data-automation-id='newsItemTitle']");
	By headingList = By.xpath("//div[@class='e_a_37591358 q_a_37591358']");
	By heading = By.xpath("//div[@role='heading']");
	@Test
	public void getHeadings() throws IOException {
	//This method displays the name of the headings of each featured news present in Be.Cognizant home page
		try {
			int i = 1;
	// getting all the news heading titles
			List<WebElement> list = driver.findElements(newsTitlePath);
			System.out.println("\n.......News Heading.......\n");
			for (WebElement news : list) {
				try {
	//It will display the headings of featured news
					System.out.println(i + ". " + news.getText());
					System.out.println();
	//By using tooltip it will display the title of the news when the pointer hovers over a news
					System.out.println("-Tooltip- \n"+news.getAttribute("title"));
					System.out.println("-------------------------------");
					System.out.println();
					i++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//Displaying the message when the testCase is passed in HTML report
			Report.reportPass("Headings are passed successfully");
		}
		catch(Exception e) {
			//Displaying the message when the testCase is failed in HTML report
			Report.reportFail("Headings are failed to collect");
		}
	}

	@Test
	public void getNewsArticle() throws InterruptedException, IOException {

		List<WebElement> list = driver.findElements(headingList);
		List<String> MainHeading = new ArrayList<String>();
		Thread.sleep(3000);
		try {
			for (int i = 1; i <= list.size(); i++) {
				String alldata = "";
				WebElement eachElement = driver
						.findElement(By.xpath("//div[@class='e_a_37591358 q_a_37591358'][" + i + "]"));
				// opening the news one by one
				eachElement.click();
				Thread.sleep(3000);
				String headings = driver.findElement(heading).getText();
				MainHeading.add(headings);
				String heading = headings.replaceAll(":", "_");
				System.out.println("Main Heading :" + heading + "\n");

				File textFile = new File(System.getProperty("user.dir") + "\\NewsText\\" + heading + ".txt");
				List<WebElement> paragraph = driver.findElements(By.xpath("//div/p"));
				// printing in the console
				for (WebElement newspara : paragraph) {

					String eachPara = newspara.getText();
					System.out.println(eachPara);
					alldata = alldata + "\n" + eachPara;

					try {
						FileWriter file = new FileWriter(textFile);
						file.write(alldata); // writing the artcile to text file
						file.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Created New updated File");
				Thread.sleep(2000);
				Screenshot.takeScreenshotTwo(driver, heading);
				driver.navigate().back();
			}
			WriteExcelData.saveData(MainHeading);
			Report.reportPass("News data fetched successfully");
		}
		catch(Exception e) {
			Report.reportFail("News data unable to fetch.");
		}

		
		
	}
}
