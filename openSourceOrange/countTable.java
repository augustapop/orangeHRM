package openSourceOrange;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class countTable {

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
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("viewAdminModule");
		driver.findElement(By.xpath("//a[@id='menu_admin_UserManagement']")).click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("UserManagement");
		driver.get(("https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers"));
		//driver.findElement(By.id("menu_admin_viewSystemUsers")).click();
		
		System.out.println("I can start to count rows");
		
		 int rowcount = driver.findElements(By.xpath("//table[@id='resultTable']//tbody//tr")).size();
		 System.out.println("rowCount:" + rowcount);
		 
		 int statuscount=0;
		 for(int i=1;i<=rowcount;i++)
		 {
			 String status=driver.findElement(By.xpath("//table[@id='resultTable']//tbody//tr[" + i + "]/td[5]")).getText();
			 System.out.println("Status for each lien it is : " + status);
			 if(status.equals("Enabled"))
			 {
				 statuscount=statuscount+1;				 
			 }			 
		 }
		 System.out.println("Count for 'Enabled' Status are: " + statuscount);
		 
	}

}
