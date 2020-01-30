package openSourceOrange;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Calendar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver;

		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.get("https://opensource-demo.orangehrmlive.com/index.php/recruitment/viewCandidates");
		System.out.println("I am on Recruitment - Candidates Page");

		driver.findElement(By.xpath("//img[@class='ui-datepicker-trigger']")).click();

		System.out.println("I am on Calendar - Option Page");

		Select y = new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")));
		y.selectByVisibleText("2020");
		System.out.println("I selected year on Calendar");
		// pentru a selecta peste 6 luni
		for (int i = 6; i >= 1; i--) {
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// luna =iulie;2020
			String month = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).getText();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (month.equals("Jul")) {
				// driver.findElement(By.xpath("//a[contains(text(),'23')]")).click();;
				driver.findElement(By.linkText("23")).click();
				System.out.println("I found date 23 on Calendar");
				break;
			}
		}

		// past date
		for (int i = 6; i >= 1; i--) {
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// luna =ianuarie;2020;selectez butonul din stanga
			String month = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).getText();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (month.equals("January")) {
				// driver.findElement(By.xpath("//a[contains(text(),'23')]")).click();;
				driver.findElement(By.linkText("23")).click();
				System.out.println("I found date 23 on Calendar");
				break;
			}
		
		}}
	}
	// System.out.println("I selected month on Calendar and Pass");

