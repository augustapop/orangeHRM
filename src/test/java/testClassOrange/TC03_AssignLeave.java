package testClassOrange;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import POMOrange.AssignLeave;

public class TC03_AssignLeave extends BaseClassOrange {
	
	//public BrowserDriverFactory browserDriver;
	
	@Test(enabled = true)
	public void AssignedLeave() {
		AssignLeave assLeave = new AssignLeave(driver);
		assLeave.goToAssignLeave();
		System.out.println("I am on leave page");
		assLeave.fillAssignLeavePage("Linda Anderson", "Vacation US", "2020-02-10", "2020-02-15");
		// Linda Anderson
		// Hannah Flores
		if (driver.getPageSource().contains(readOrange.balanceAdnotation())) {
			driver.findElement(By.partialLinkText(readOrange.balanceAdnotation())).click();
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
