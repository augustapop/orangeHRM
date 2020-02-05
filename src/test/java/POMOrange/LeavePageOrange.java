package POMOrange;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import orangeHRM.orangeHRM.CommonActionOrange;
import orangeHRM.orangeHRM.ReadConfigOrange;

public class LeavePageOrange {
	// https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList
	WebDriver driver;

	public LeavePageOrange(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}


	@FindBy(how = How.ID, using = "calFromDate")
	WebElement txtFromDate;
	@FindBy(how = How.ID, using = "calToDate")
	WebElement txtToDate;
	@FindBy(how = How.XPATH, using = "checkbox_group label_first")
	WebElement checkAll;
	// xpath://label[contains(text(),"All")]
	// label[contains(text(),"Cancelled")]
	@FindBy(how = How.ID, using = "leaveList_txtEmployee_empName")
	WebElement leaveList_txtEmployee_empName;
	@FindBy(how = How.ID, using = "leaveList_cmbSubunit")
	WebElement leaveListSubUnit;
	@FindBy(how = How.ID, using = "leaveList_cmbWithTerminated")
	WebElement subUnitcheck;
	
	 String urlLeavePage = " https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList";
	
	public void goToLeavePage()
	{
		driver.get(urlLeavePage);
	}

	
	public void getTable()
	{
		
	}
	
	
	// Add Leave EntitElement
	// https://opensource-demo.orangehrmlive.com/index.php/leave/addLeaveEntitlement?savedsearch=1
	// ;pentru windows appeare

//
//			// *[@id='vContactsForm']/table/tbody/tr[4]/td[2]/a
//			// *[@id='vContactsForm']/table/tbody/tr[5]/td[2]/a
//			// *[@id='vContactsForm']/table/tbody/tr[6]/td[2]/a
//			// *[@id='vContactsForm']/table/tbody/tr[7]/td[2]/a
//
//			// Method-1:
//			String before_xpath = "//*[@id='vContactsForm']/table/tbody/tr[";
//			String after_xpath = "]/td[2]/a";
//
//			for (int i = 4; i <= 7; i++) {
//				String name = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
//				System.out.println(name);
//				if (name.contains("test2 test2")) { // i=6
//					// *[@id='vContactsForm']/table/tbody/tr[6]/td[1]/input
//					driver.findElement(By.xpath("//*[@id='vContactsForm']/table/tbody/tr[" + i + "]/td[1]/input")).click();
//				}
//			}
//
//			// Method-2:
//			driver.findElement(By
//					.xpath("//a[contains(text(),'test2 test2')]/parent::td//preceding-sibling::td//input[@name='contact_id']"))
//					.click();
//			driver.findElement(By
//					.xpath("//a[contains(text(),'ui uiii')]/parent::td//preceding-sibling::td//input[@name='contact_id']"))
//					.click();
//
//		}
//
//	}


}
