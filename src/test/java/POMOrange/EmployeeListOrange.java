package POMOrange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import orangeHRM.orangeHRM.CommonActionOrange;
import orangeHRM.orangeHRM.ReadConfigOrange;

public class EmployeeListOrange extends CommonActionOrange {

	public WebDriver driver;
	public static ReadConfigOrange readOrange;
	public static String urlOrange;
	public static String userOr;
	public static String passwordOR;
	public static String urlEmployeeListOrange;
	// public CommonActionOrange commonActions;
	public LoginPageOrange login;
	public final int WAIT_UNTIL_MAX_TIME = 60; // sec
	public WebDriverWait wait;

	@FindBy(how = How.XPATH, using = "//input[@id='calFromDate']")
	WebElement txtFromDate;
	@FindBy(how = How.XPATH, using = "//input[@id='calToDate']")
	WebElement txtTo;
	@FindBy(how = How.XPATH, using = "//div[@id=@id='leaveList_chkSearchFilter_checkboxgroup']")
	WebElement checkBox;
	@FindBy(how = How.ID, using = "resultTable")
	WebElement table;
	@FindBy(how = How.XPATH, using = "//table//thead//tr")
	WebElement row;
	// int rowNo;
	@FindBy(how = How.XPATH, using = "//th[@class='checkbox-col']")
	WebElement col;
	// int colNo;

	public EmployeeListOrange(WebDriver driver) {
		super(driver, readOrange);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// commonActions.waitUntilAjaxCallsAreDone(400);
	}

	public void goOrangePage() {
		driver.get(urlEmployeeListOrange);
	}

}
