package testClassOrange;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.BeforeClass;
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
	@BeforeClass
	public void setup() {
		// if (br.equals("chrome")) {
		// System.setProperty("webdriver.chrome.driver",
		// readOrange.getPathChr());
		// System.setProperty("webdriver.chrome.driver",
		// System.getProperty("user.dir")+ "//Drivers//chromedriver.exe");
		// driver = new ChromeDriver();

		// }
		// else if (br.equals("firefox")) {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(urlOrange);
	}
	// else if (br.equals("ie")) {
	// System.setProperty("webdriver.ie.driver", readOrange.getPathIE());
	// driver = new InternetExplorerDriver();
	// }
	// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	// }

	public static String randomString() {
		String randomstring = RandomStringUtils.randomAlphabetic(8);
		return randomstring;
	}

	public static String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(4);
		return randomNumber;
	}

}
