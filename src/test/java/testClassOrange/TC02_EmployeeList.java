package testClassOrange;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import POMOrange.EmployeeListOrange;
import POMOrange.LoginPageOrange;


public class TC02_EmployeeList extends BaseClassOrange {  

	@Test
	public void setUPEmployeeTes() {
		// driver.get(urlOrange);
		LoginPageOrange lp = new LoginPageOrange(driver);
		lp.setLoginPageOR(getUserNameOrange, getPasswordOrange);
		if (driver.getPageSource().contains("HRM")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement admin = driver.findElement(By.className("firstLevelMenu"));
		admin.click();
		driver.manage().window().maximize();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EmployeeListOrange emplList = new EmployeeListOrange(driver);
		List<WebElement> allRows = driver.findElements(By.xpath("//table//tbody//tr"));
		for (WebElement row : allRows) {
			try {
				emplList.highlightElement(row);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

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
			if(current_value.contains("john"))
			{
				driver.findElement(By.partialLinkText("john")).click();
			break;	
			}
			System.out.println("Text for each row it is  : " + current_value);		
		}

		int rowb = allRows.size();
		// int number= emplList.TotaltGridRows(table);
		System.out.println("Number of row " + rowb);
		// getTableCellValue(table, rowNo, colNo);
		// EmployeeListOrange emplList = new EmployeeListOrange(driver);
		// driver.get(urlEmployeeListOrange);
		// driver.close();
	}
}
