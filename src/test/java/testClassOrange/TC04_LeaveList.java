package testClassOrange;

import org.testng.annotations.Test;

import POMOrange.LeavePageOrange;

public class TC04_LeaveList extends BaseClassOrange {

	@Test
	public void goToLeaveList() {
		LeavePageOrange leavePage = new LeavePageOrange(driver);
		leavePage.goToLeavePage();
		driver.manage().window().maximize();
	}
}
