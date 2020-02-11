package testClassOrange;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import POMOrange.LoginPageOrange;
import orangeHRM.orangeHRM.ReadConfigOrange;

public class BaseClassOrange {

	
	protected WebDriver driver;
	public ReadConfigOrange readOrange = new ReadConfigOrange();	
	public String balanceAdd=readOrange.balanceAdnotation();
	public String urlEmployeeListOrange = "https://opensource-demo.orangehrmlive.com/index.php/pim/viewEmployeeList";
	// readOrange.urlEmployeeListOrange();
	@FindBy(how = How.ID, using = "resultTable")
	WebElement table;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)

	public void setup(@Optional("chrome") String browser) {

		BrowserDriverFactory factory = new BrowserDriverFactory(browser);
		driver = factory.createDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(factory.urlOrange);
		LoginPageOrange lp = new LoginPageOrange(driver);

		lp.setLoginPageOR(factory.getUserNameOrange,factory.getPasswordOrange);
		if (driver.getPageSource().contains("HRM")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

//	if (browser.equalsIgnoreCase("firefox")) {
//	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
//	driver = new FirefoxDriver();
//} else if (browser.equalsIgnoreCase("chrome")) {
//	// System.setProperty("webdriver.chrome.driver",readOrange.getPathChr());
//	System.setProperty("webdriver.chrome.driver",
//			System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
//	driver = new ChromeDriver();
//} else if (browser.equalsIgnoreCase("ie")) {
//	System.setProperty("webdriver.ie.driver", readOrange.getPathIE());
//	driver = new InternetExplorerDriver();
//}

}
