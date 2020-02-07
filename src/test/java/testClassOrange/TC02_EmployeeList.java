package testClassOrange;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import POMOrange.EmployeeListOrange;

public class TC02_EmployeeList extends BaseClassOrange {

	@Test
	public void setUPEmployeeTes() {
		EmployeeListOrange emplListOrange = new EmployeeListOrange(driver);
		emplListOrange.goOrangePage();
		driver.manage().window().maximize();
		List<WebElement> allRows = EmployeeTable(emplListOrange);
		int rowb = allRows.size();
		System.out.println("Number of row " + rowb);
		// driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/listCustomFields");
		// driver.get("https://opensource-demo.orangehrmlive.com/index.php/leave/assignLeave");
	}

	private List<WebElement> EmployeeTable(EmployeeListOrange emplList) {
		List<WebElement> allRows = driver.findElements(By.xpath("//table//tbody//tr"));
		for (WebElement row : allRows) {
			try {
				emplList.highlightElement(row);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return EmployeeTableRow(emplList, allRows);
	}

	private List<WebElement> EmployeeTableRow(EmployeeListOrange emplList, List<WebElement> allRows) {
		List<WebElement> allColumns = driver.findElements(By.xpath("//table//thead//tr//th"));
		for (WebElement row : allRows) {
			for (WebElement column : allColumns) {
				try {
					emplList.highlightElement(column);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String columnCurentValue = driver.findElement(By.xpath("//table//thead//tr//th")).getText();
			}
			try {
				emplList.highlightElement(row);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String current_value = row.getText();
			if (current_value.contains("john")) {
				driver.findElement(By.partialLinkText("john")).click();
				driver.findElement(By.className("addbutton")).click();
				List<WebElement> ddlUser = driver.findElements(By.id("systemUser_userType"));
				for (WebElement ddl : ddlUser) {
					try {
						emplList.highlightElement(ddl);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String current_ddl = ddl.getText();
					if (current_ddl.contains("Admin")) {
						driver.findElement(By.cssSelector("#systemUser_userType > option:nth-child(1)")).click();
						break;
					}
					System.out.println("DDL value  it is :" + current_ddl);
				}
				driver.findElement(By.className("addbutton")).click();
				break;
			}

			else {
				System.out.println("We are crossing all rows from the table");

			}
			System.out.println("Text for each row it is  : " + current_value);
		}
		return allRows;
	}

}
