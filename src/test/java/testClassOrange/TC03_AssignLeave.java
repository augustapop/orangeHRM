package testClassOrange;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import POMOrange.AssignLeave;

public class TC03_AssignLeave extends BaseClassOrange {

	@Test(enabled = true)
	public void AssignedLeave() {
		System.out.println(" I am ready to start");
		// LoginPageOrange lp = new LoginPageOrange(driver);
		// lp.setLoginPageOR(getUserNameOrange, getPasswordOrange);
		// if (driver.getPageSource().contains("HRM")) {
		// Assert.assertTrue(true);
		// } else {
		// Assert.assertTrue(false);
		// }

		AssignLeave assLeave = new AssignLeave(driver);
		assLeave.goToAssignLeave();
		System.out.println("I am on leave page");
		assLeave.fillAssignLeavePage("Hannah Flores", "Vacation US", "2020-02-10", "2020-02-15");

		if (driver.getPageSource().contains("Balance")) {
			driver.findElement(By.partialLinkText("Balance")).click();
			
		}
	}
}
