package testClassOrange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import orangeHRM.orangeHRM.ReadConfigOrange;

public class BrowserDriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;
	
	ReadConfigOrange readOrange=new ReadConfigOrange();
	public String urlOrange = readOrange.getURLOrange();
	public String getUserNameOrange = readOrange.getUserNameOrange();
	public String getPasswordOrange = readOrange.getPasswordOrange();
	public String getpathChOrange = readOrange.getPathChr();
	public String getpathFFOrange = readOrange.getPathFF();
	public String getpathIEOrange = readOrange.getPathIE();
	public String urlLeaveListOrange = readOrange.getURLLeaveList();

	public BrowserDriverFactory(String browser) {
		this.browser = browser.toLowerCase();
	}

	public WebDriver createDriver() {
		// Create driver
		System.out.println("Create driver: " + browser);

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + readOrange.getPathChr());
			driver.set(new ChromeDriver());
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") +readOrange.getPathFF());
			driver.set(new FirefoxDriver());
			break;

		default:
			System.out.println("Do not know how to start: " + browser + ", starting chrome.");
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
			driver.set(new FirefoxDriver());
			break;
		}

		return driver.get();
	}
}
