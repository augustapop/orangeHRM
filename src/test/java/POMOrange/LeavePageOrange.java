package POMOrange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LeavePageOrange {

	//https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList
	WebDriver driver;
	
	@FindBy(how=How.ID,using="calFromDate") WebElement txtFromDate;
	@FindBy(how=How.ID,using="calToDate") WebElement txtToDate;
	@FindBy(how=How.XPATH,using="checkbox_group label_first") WebElement checkAll;
	//xpath://label[contains(text(),"All")]
	//label[contains(text(),"Cancelled")]
	@FindBy(how=How.ID,using="leaveList_txtEmployee_empName") WebElement leaveList_txtEmployee_empName;
	@FindBy(how=How.ID,using="leaveList_cmbSubunit") WebElement leaveListSubUnit;
	@FindBy(how=How.ID,using="leaveList_cmbWithTerminated") WebElement subUnitcheck;
	
	//Add Leave Entitlement
	//https://opensource-demo.orangehrmlive.com/index.php/leave/addLeaveEntitlement?savedsearch=1 ;pentru windows appeare
	
	
}
