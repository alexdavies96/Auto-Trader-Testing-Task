package Auto_Trader_Website_Testing.AutoTrader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

	public static WebDriver getwebdriver(String browser) {
		switch (browser.toLowerCase()){
		default :
				return new ChromeDriver();
		case "safari":
				return new SafariDriver();
		}
	}
}