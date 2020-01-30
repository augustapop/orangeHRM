package POMOrange;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import orangeHRM.orangeHRM.CommonActionOrange;
import orangeHRM.orangeHRM.ReadConfigOrange;

public class EmployeeListOrange extends CommonActionOrange{

	public WebDriver driver;
	public static ReadConfigOrange readOrange;
	public static String urlOrange;
	public static String userOr;
	public static String passwordOR;
	public static String urlEmployeeListOrange;
//	public CommonActionOrange commonActions;
	public LoginPageOrange login;
	public final int WAIT_UNTIL_MAX_TIME = 60; // sec
	public WebDriverWait wait;

	@FindBy(how = How.XPATH, using = "//input[@id='calFromDate']")
	WebElement txtFromDate;
	@FindBy(how = How.XPATH, using = "//input[@id='calToDate']")
	WebElement txtTo;
	@FindBy(how = How.XPATH, using = "//div[@id=@id='leaveList_chkSearchFilter_checkboxgroup']")
	WebElement checkBox;
	@FindBy(how=How.ID,using="resultTable")
	WebElement table;
	@FindBy(how=How.XPATH,using="//table//thead//tr") WebElement row; 
	//int rowNo;
	@FindBy(how=How.XPATH,using="//th[@class='checkbox-col']") WebElement col;
	//int colNo;
	
	public boolean findTableElement(WebElement table, String getValue) {
		try {
			String current_value;

			if (!isExistingElement(table)) {
				System.out.println("table does not exist");
				return false;
			}
			List<WebElement> allRows = table.findElements(By.tagName("tr"));
			for (WebElement row : allRows) {
				highlightElement(row);
				List<WebElement> columns = row.findElements(By.tagName("td"));
				for (WebElement column : columns) {
					highlightElement(column);
					current_value = column.getText();
					if (current_value.equals(getValue)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in findTableElement: " + e.getMessage());
		}
		return false;
	}	
	
	
    public String getTableCellValue(WebElement table, int rowNo, int colNo) throws InterruptedException {
	if (isExistingElement(table)){
	    WebElement tableBody = table.findElement(By.tagName("tbody"));
	    List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
	    WebElement row = rows.get(rowNo);
	    List<WebElement> cols = row.findElements(By.tagName("td"));
	    WebElement col = cols.get(colNo);
	    highlightElement(col);
	    //System.out.println("Cell value is: " + col.getText().toString());
	    return col.getText();
	} else
	    System.err.println("Table not found.");
	return null;
    }
    
	public EmployeeListOrange(WebDriver driver) {
		super(driver, readOrange);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//commonActions.waitUntilAjaxCallsAreDone(400);
	}

	public void goOrangePage() {
		driver.get(urlEmployeeListOrange);
	}
	
	
	public void scrollInViewForElement(WebElement element) {
		int heightPos = element.getLocation().getY();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "window.scrollTo(0, " + heightPos + "-(window.innerHeight / 2));";
		js.executeScript(command);
	}
	
	
	
	
	public void highlightElement(WebElement txtFromDate) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", txtFromDate,
				"color: red; border: 2px solid red;");

		Thread.sleep(300);

		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", txtFromDate, "");

	}

	public boolean isExistingElement(WebElement element) {
		boolean exist = false;
		try {
			// wait(element, 3);
			exist = element.isDisplayed();
			if (exist) {
				scrollInViewForElement(element);
				highlightElement(element);
			}
		} catch (Exception e) {
			return false;
		}
		return exist;
	}

	public void clickElement(WebElement element) {
		waitUntilAjaxCallsAreDone(20000);
		waitUntilIsClickable(element, WAIT_UNTIL_MAX_TIME);
		if (isExistingElement(element)) {
			if (readOrange.getPathFF().equals("firefox")) {
				element.click();
			} else
				element.click();
		}
		waitUntilAjaxCallsAreDone(20000);
	}

	public void waitUntilIsClickable(WebElement element, int timer) {
		wait = new WebDriverWait(driver, timer);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilAjaxCallsAreDone(long maxTime) {
		long currentTime = System.currentTimeMillis();
		long repeatUntilTime = currentTime + maxTime;
		boolean runningAjax = true;

		while (runningAjax && (currentTime < repeatUntilTime)) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			boolean ajaxFinished = (Boolean) jsExecutor.executeScript("return jQuery.active == 0");
			if (ajaxFinished) {
				runningAjax = false;
			} else {
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			currentTime = System.currentTimeMillis();
		}
	}
}
