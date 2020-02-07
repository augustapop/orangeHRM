package testClassOrange;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import POMOrange.LoginPageOrange;
import orangeHRM.orangeHRM.ReadConfigOrange;

public class BaseClassOrange {

	protected WebDriver driver;
	ReadConfigOrange readOrange = new ReadConfigOrange();
	public String urlOrange = readOrange.getURLOrange();
	public String getUserNameOrange = readOrange.getUserNameOrange();
	public String getPasswordOrange = readOrange.getPasswordOrange();
	public String getpathChOrange = readOrange.getPathChr();
	public String getpathFFOrange = readOrange.getPathFF();
	public String getpathIEOrange = readOrange.getPathIE();
	public String urlLeaveListOrange = readOrange.getURLLeaveList();
	public String urlEmployeeListOrange = "https://opensource-demo.orangehrmlive.com/index.php/pim/viewEmployeeList";
	// readOrange.urlEmployeeListOrange();
	@FindBy(how = How.ID, using = "resultTable")
	WebElement table;

	// @Parameters({ "browser" })
	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(urlOrange);
		LoginPageOrange lp = new LoginPageOrange(driver);

		lp.setLoginPageOR(getUserNameOrange, getPasswordOrange);
		if (driver.getPageSource().contains("HRM")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	

}
