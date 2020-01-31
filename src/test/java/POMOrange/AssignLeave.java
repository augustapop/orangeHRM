package POMOrange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssignLeave {

	public WebDriver driver;
	public static String urlAssignLeave="https://opensource-demo.orangehrmlive.com/index.php/leave/assignLeave";
	public WebDriverWait wait;
	
	@FindBy(how=How.XPATH,using="//input[@id='assignleave_txtEmployee_empName']") WebElement employeeName;
	@FindBy(how=How.XPATH,using="") WebElement ddlLeaveType;
	
	
	
}
