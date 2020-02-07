package testClassOrange;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import POMOrange.AssignLeave;

public class TC03_AssignLeave extends BaseClassOrange {

	@Test(enabled = true)
	public void AssignedLeave() {
		AssignLeave assLeave = new AssignLeave(driver);
		assLeave.goToAssignLeave();
		System.out.println("I am on leave page");
		assLeave.fillAssignLeavePage("Hannah Flores", "Vacation US", "2020-02-10", "2020-02-15");

		if (driver.getPageSource().contains("Balance")) {
			driver.findElement(By.partialLinkText("Balance")).click();			
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//div[@id='multiperiod_balance']//input[@id='closeButton']")).click();
	}
}
