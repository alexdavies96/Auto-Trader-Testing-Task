package Auto_Trader_Website_Testing.AutoTrader;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import AutomatedTesting.AutomatedTesting.spreadSheetReader;
import AutomatedTesting.AutomatedTesting.webDriverFactory;
import junit.framework.Assert;

public class Test1 {
	private WebDriver webdriver;
	private static ExtentReports report;
	private static String fileName = "MyReport" + ".html";
	spreadSheetReader reader = new spreadSheetReader(System.getProperty("user.dir") + File.separatorChar + "WorkbookTest.xlsx");
	ArrayList<String> row = reader.readRow(1, "Sheet1");


		@BeforeClass
		public static void init() {
			report = new ExtentReports();
			String filePath = System.getProperty("user.dir") + File.separatorChar + fileName;
			report.attachReporter(new ExtentHtmlReporter(filePath));
		}

		@Before
		public void setUp() {
			//System.getProperty("webdriver.chrome.driver","/Users/Alex-Rowan/Documents/GitHub/Testing/AutomatedTesting");
			//((WebDriver) webdriver).manage().window().maximize();
			webdriver = webDriverFactory.getwebdriver(row.get(3));
		}
		
		public static String take(WebDriver webdriver, String fileName) throws IOException{
			File scrFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
			String pathname = System.getProperty("user.dir") + File.separatorChar + fileName + ".jpg";
			FileUtils.copyFile(scrFile,  new File(pathname));
			System.out.println("File Saved at: " + pathname);
			return pathname;
		}
		
		
		@Test
		public void googleTest() throws IOException{
			try {
				for (String cell : row) {
					 System.out.println(cell);
				}
				
				webdriver.navigate().to("http://thedemosite.co.uk");
				WebElement AddUser = webdriver.findElement(By.cssSelector("body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(6)"));
				AddUser.click();
				WebElement UserName = webdriver.findElement(By.name("username"));
				UserName.sendKeys(row.get(0));
				WebElement Pass = webdriver.findElement(By.name("password"));
				Pass.sendKeys(row.get(1));
				WebElement save = webdriver.findElement(By.name("FormsButton2"));
				save.click();
				WebElement Login = webdriver.findElement(By.cssSelector("body > table > tbody > tr > td.auto-style1 > form > div > center > table > tbody > tr > td:nth-child(2) > small > a"));
				Login.click();
				WebElement UserName2 = webdriver.findElement(By.name("username"));
				UserName2.sendKeys(row.get(0));
				WebElement Pass2 = webdriver.findElement(By.name("password"));
				Pass2.sendKeys(row.get(1));
				WebElement Go = webdriver.findElement(By.name("FormsButton2"));
				Go.click();
				WebElement Succesful = webdriver.findElement(By.cssSelector("body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b"));
				assertTrue("Succesful", Succesful.getText().equals(row.get(2)));
	        }
	        catch(Exception e) {
	            System.out.println("Element Not Found");
	            Test1.take(webdriver, "ScreenShot");
	            Assert.fail();
	        }
		}
		
		@After
		public void tearDown() {	
			webdriver.quit();
		}
		
		@AfterClass	
		public static void cleanUp() {	report.flush();	  }
	}
