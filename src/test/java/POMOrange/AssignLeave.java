package POMOrange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import orangeHRM.orangeHRM.CommonActionOrange;

public class AssignLeave {

	
	public WebDriver driver;
	public static String urlAssignLeave="https://opensource-demo.orangehrmlive.com/index.php/leave/assignLeave";
	public WebDriverWait wait;
	
	@FindBy(how=How.XPATH,using="//input[@id='assignleave_txtEmployee_empName']") WebElement employeeName;
	@FindBy(how=How.XPATH,using="//select[@id='assignleave_txtLeaveType']") WebElement ddlLeaveType;
	@FindBy(how=How.XPATH,using="//input[@id='assignleave_txtFromDate']") WebElement txtFromDate;
	@FindBy(how=How.XPATH,using="//input[@id='assignleave_txtToDate']") WebElement txtToDate;
	@FindBy(how=How.ID,using="assignBtn") WebElement assignBtn;
	
	
	public AssignLeave(WebDriver driver)
	{
		driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToAssignLeave()
	{
		driver.get(urlAssignLeave);
	}
	
}
