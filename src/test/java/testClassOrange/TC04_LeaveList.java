package testClassOrange;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import POMOrange.LeavePageOrange;

public class TC04_LeaveList extends BaseClassOrange {

	@Test
	public void goToLeaveList() {
		LeavePageOrange leavePage = new LeavePageOrange(driver);
		leavePage.goToLeavePage();
		driver.manage().window().maximize();
		List<WebElement> col = driver.findElements(By.xpath("//*[@id='resultTable']/thead/tr/th"));
		int noCol = col.size();
		System.out.println("Nr de coloane" + noCol);
		String textCol = col.toString();
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='resultTable']/thead/tr"));
		for (int i = 0; i < noCol; i++) {
			for (WebElement row : rows) {

				if (textCol.contains("Pending")) {

					driver.findElement(By.className("select_action quotaSelect")).click();
				}
			}
		}
	}
}
