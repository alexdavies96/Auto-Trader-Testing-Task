package Auto_Trader_Website_Testing.AutoTrader;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import AutomatedTesting.AutomatedTesting.webDriverFactory;
import junit.framework.Assert;

public class AutoTrader {

	private WebDriver webdriver;
	private static ExtentReports report;
	private static String fileName = "ShoppingReport" + ".html";
	SpreadSheetReader reader = new SpreadSheetReader(System.getProperty("user.dir") + File.separatorChar + "Auto_Trader_Data.xlsx");
	ArrayList<String> row = reader.readRow(1, "Sheet1");


	@BeforeClass
	public static void init() {
		report = new ExtentReports();
		String filePath = System.getProperty("user.dir") + File.separatorChar + fileName;
		report.attachReporter(new ExtentHtmlReporter(filePath));
	}
	
	@Before
	public void setUp() {
		webdriver = webDriverFactory.getwebdriver(row.get(3));
	}
	
	public static String takeScreenShot(WebDriver webdriver, String fileName) throws IOException {
		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		String pathname = System.getProperty("user.dir") + File.separatorChar + fileName + ".jpg";
		FileUtils.copyFile(scrFile,  new File(pathname));
		System.out.println("File Saved at: " + pathname);
		return pathname;
	}
	
	@Test	//Testing if we can add a user
	public void Test1() throws IOException {
		try {
			webdriver.navigate().to("https://www.autotrader.co.uk/");
			WebElement Sign = webdriver.findElement(By.cssSelector("#js-header-nav > ul > li.header__nav-listing.header__nav-my-at > div > a"));
			Sign.click();
			WebElement SignUp = webdriver.findElement(By.cssSelector("#js-social__signup-tab"));
			SignUp.click();
			WebElement Email = webdriver.findElement(By.cssSelector("#email"));
			Email.sendKeys(row.get(0));
			WebElement Pass = webdriver.findElement(By.cssSelector("#password"));
			Pass.sendKeys(row.get(1));
			WebElement Go = webdriver.findElement(By.cssSelector("#social--signup--submit"));
			Go.click();
			String URLAfter = webdriver.getPageSource();
			String URLFailed = "https://www.autotrader.co.uk/secure/signin/guid/61120fc0-8927-4eaa-b764-e402f0d44f59/regMsg/UNSUCCESSFUL_REGISTRATION?after-signin-url=%2F&signup=true";
			if (URLFailed.equals(URLAfter)) {
				fail();
			}
			else 
			{
				assertTrue("Pass", true);
			}
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot1");
			Assert.fail();
		}
	}
	
	@Test	//Testing if we can sign in with the user we added
	public void Test2() throws IOException {
		try {
			webdriver.navigate().to("https://www.autotrader.co.uk/");
			WebElement Sign = webdriver.findElement(By.cssSelector("#js-header-nav > ul > li.header__nav-listing.header__nav-my-at > div > a"));
			Sign.click();
			WebElement Email = webdriver.findElement(By.cssSelector("#signin-email"));
			Email.sendKeys(row.get(0));
			WebElement Pass = webdriver.findElement(By.cssSelector("#signin-password"));
			Pass.sendKeys(row.get(1));
			WebElement Go = webdriver.findElement(By.cssSelector("#signInSubmit"));
			Go.click();
			String URLAfter = webdriver.getPageSource();
			String URLFailed = "https://www.autotrader.co.uk/secure/signin/status/failed_login?after-signin-url=%2F";
			if (URLFailed.equals(URLAfter)) {
				fail();
			}
			else 
			{
				assertTrue("Pass", true);
			}
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot2");
			Assert.fail();
		}
	}
	
	@Test	//Searching for a car 
	public void Test3() throws IOException {
		try {
			webdriver.navigate().to("https://www.autotrader.co.uk/");
			Actions action = new Actions(webdriver);
			WebElement search = webdriver.findElement(By.cssSelector("#js-header-nav > ul > li:nth-child(1) > a"));
			action.moveToElement(search).perform();
			WebElement locator = webdriver.findElement(By.cssSelector("#top-nav__buying > li:nth-child(1)"));
			action.moveToElement(locator).click().perform();
//			locator.click();
//			WebElement Postcode = webdriver.findElement(By.cssSelector("#postcode"));
//			Postcode.sendKeys(row.get(4));
//			WebElement MaxPrice = webdriver.findElement(By.cssSelector("#searchVehiclesPriceTo"));
//			Select dropdown2 = new Select(MaxPrice);
// 			dropdown2.selectByValue(row.get(8));
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot3");
			Assert.fail();
		}
	}
	
	@Test   // sell a car 
	public void Test4() throws IOException {
		try {
			webdriver.navigate().to("https://www.autotrader.co.uk/");
			WebElement Postcode = webdriver.findElement(By.cssSelector("#js-sell-module > form > input:nth-child(1)"));
			Postcode.sendKeys(row.get(2));
			WebElement Mileage = webdriver.findElement(By.cssSelector("#js-sell-module > form > input.c-form__input.no-spinner"));
			Mileage.sendKeys(row.get(8));
			WebElement Go = webdriver.findElement(By.cssSelector("#js-sell-module > form > button"));
			Go.click();
			WebElement Next = webdriver.findElement(By.cssSelector("#submitPage"));
			if (Next.isDisplayed()) {
				assertTrue("pass", true);
			}
			else
			{
				fail();
			}
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot4");
			Assert.fail();
		}
	}

	@Test		//Buy a car
	public void Test5() throws IOException {
		try {
			webdriver.navigate().to("https://www.autotrader.co.uk/");
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot5");
			Assert.fail();
		}
	}

	@Test      //apply for car finance
	public void Test6() throws IOException {
		try {
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot6");
			Assert.fail();
		}
	}
	
	@Test		//Find a car dealer
	public void Test7() throws IOException {
		try {
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot7");
			Assert.fail();
		}
	}
	
	@Test		//part exchange your car
	public void Test8() throws IOException {
		try {
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot8");
			Assert.fail();
		}
	}
	
	@Test		//apply for inshurance
	public void Test9() throws IOException {
		try {
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot9");
			Assert.fail();
		}
	}
	
	@Test		//check car history
	public void Test10() throws IOException {
		try {
			
		} catch(Exception e) {
			System.out.println("Element Not Found");
			AutoTrader.takeScreenShot(webdriver, "ScreenShot10");
			Assert.fail();
		}
	}
	
	public void dragAndDrop(WebElement dragable, WebElement dropable,int dropableOffsetX, int dropableOffsetY) {
	    Actions builder = new Actions(webdriver);
	    int offsetX = dropable.getLocation().x + dropableOffsetX - dragable.getLocation().x;
	    int offsetY = dropable.getLocation().y + dropableOffsetY - dragable.getLocation().y;
	    builder.clickAndHold(dragable).moveByOffset(offsetX, offsetY).release().perform();
	}
	
	@After
	public void tearDown() {
		webdriver.quit();
	}
	
	@AfterClass
	public static void cleanUp() {
		report.flush();
	}
}

