package POMOrange;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DynamicWebTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize(); // maximize window
	//	driver.manage().deleteAllCookies(); // delete all the cookies

		// dynamic wait
	//	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.freecrm.com/"); // enter URL
		
		driver.findElement(By.xpath("//span[contains(text(),'Log In')]")).click();

		driver.findElement(By.name("email")).sendKeys("naveenk");
		driver.findElement(By.name("password")).sendKeys("test@123");
		
		driver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();

	//	driver.switchTo().frame("mainpanel");
		

		//driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();

		// *[@id='vContactsForm']/table/tbody/tr[4]/td[2]/a
		// *[@id='vContactsForm']/table/tbody/tr[5]/td[2]/a
		// *[@id='vContactsForm']/table/tbody/tr[6]/td[2]/a
		// *[@id='vContactsForm']/table/tbody/tr[7]/td[2]/a

		// Method-1:
//		String before_xpath = "//*[@id='vContactsForm']/table/tbody/tr[";
//		String after_xpath = "]/td[2]/a";
//
//		for (int i = 4; i <= 7; i++) {
//			String name = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
//			System.out.println(name);
//			if (name.contains("test2 test2")) { // i=6
//				// *[@id='vContactsForm']/table/tbody/tr[6]/td[1]/input
//				driver.findElement(By.xpath("//*[@id='vContactsForm']/table/tbody/tr[" + i + "]/td[1]/input")).click();
//			}
//		}
//
//		// Method-2:
//		driver.findElement(By
//				.xpath("//a[contains(text(),'test2 test2')]/parent::td//preceding-sibling::td//input[@name='contact_id']"))
//				.click();
//		driver.findElement(By
//				.xpath("//a[contains(text(),'ui uiii')]/parent::td//preceding-sibling::td//input[@name='contact_id']"))
//				.click();

	}

}
