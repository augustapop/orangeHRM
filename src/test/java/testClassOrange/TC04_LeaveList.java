package testClassOrange;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
		// String textCol = col.toString();
		List<WebElement> rows = driver
				.findElements(By.xpath("/html/body/div[1]/div[3]/div[2]/div/form/div[3]/table/tbody/tr"));
		System.out.println("Rows number it is:" + rows.size());
		/// html/body/div[1]/div[3]/div[2]/div/form/div[3]/table/tbody/tr[2]/td[1]
		/// html/body/div[1]/div[3]/div[2]/div/form/div[3]/table/tbody/tr[2]/td[6]
		// "html/body/div[1]/div[3]/div[2]/div/form/div[3]/table/tbody/tr[" + (i
		/// + 1) + "]/td[6]"
		//// tr[@class='even']
		for (int i = 0; i < rows.size(); i++) {
			{
				String text = driver.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[6]")).getText();
				System.out.println("Textul aferent este:" + text);
				if (text.contains("Pending")) {
					System.out.println("I am where I want to be");
					// driver.findElement(By.xpath("//*[@id='select_leave_action_2']")).click();
					// Select status=new
					// Select(driver.findElement(By.xpath("//*[@id='select_leave_action_2']")));
					driver.findElement(By.xpath("//*[@id='select_leave_action_" + (i) + "']")).click();
					Select status = new Select(driver.findElement(By.xpath("//*[@id='select_leave_action_" + (i) + "']")));
					status.selectByVisibleText("Cancel");
					driver.findElement(By.name("btnSave")).click();

				}
			}
		}
	}
}
