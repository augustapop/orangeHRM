package POMOrange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import orangeHRM.orangeHRM.CommonActionOrange;

public class AssignLeave {

	
	public WebDriver ldriver;
	public static String urlAssignLeave="https://opensource-demo.orangehrmlive.com/index.php/leave/assignLeave";
	public WebDriverWait wait;
	CommonActionOrange comAct;
	
	@FindBy(how=How.XPATH,using="//input[@id='assignleave_txtEmployee_empName']") WebElement employeeName;
	@FindBy(how=How.XPATH,using="//select[@id='assignleave_txtLeaveType']") WebElement ddlLeaveType;
	@FindBy(how=How.XPATH,using="//input[@id='assignleave_txtFromDate']") WebElement txtFromDate;
	@FindBy(how=How.XPATH,using="//input[@id='assignleave_txtToDate']") WebElement txtToDate;
	@FindBy(how=How.XPATH,using="//*[@id='assignBtn']") WebElement assignBtn;
	
	
	public AssignLeave(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	public void goToAssignLeave()
	{
		ldriver.get(urlAssignLeave);
	}
	
	public void fillAssignLeavePage(String emplName,String leaveType,
			String fromDate,String toDate) throws InterruptedException	
	{
	//	comAct.highlightElement(employeeName);		
		//comAct.setEdit(employeeName, emplName);
		
		
	//	comAct.highlightElement(ddlLeaveType);
	//	comAct.setEdit(ddlLeaveType, leaveType);
		
	//	comAct.highlightElement(txtFromDate);
	//	comAct.setEdit(txtFromDate, fromDate);
		
	//	comAct.highlightElement(txtToDate);
	//	comAct.setEdit(txtToDate, toDate);
		
	//	comAct.highlightElement(assignBtn);
		employeeName.sendKeys(emplName);
		ddlLeaveType.sendKeys(leaveType);
		txtFromDate.clear();
		txtFromDate.sendKeys(fromDate);
		txtToDate.clear();
		txtToDate.sendKeys(toDate);
		Thread.sleep(1000);
		assignBtn.click();
	}	
}
