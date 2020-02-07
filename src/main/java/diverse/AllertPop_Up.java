package diverse;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AllertPop_Up {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.popuptest.com/goodpopups.html");

		// driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		// driver.findElement(By.name("login")).sendKeys("ioana.pop");
		// driver.findElement(By.name("passwd")).sendKeys("Davide1970!");
		// driver.findElement(By.name("proceed")).click(); // click on Go btn

		driver.findElement(By.xpath("html/body/table[2]/tbody/tr/td/font/b/a[3]")).click();

		Thread.sleep(2000);

		Set<String> handler = driver.getWindowHandles();

		Iterator<String> it = handler.iterator();

		String parentWindowId = it.next();
		System.out.println("parent window id:" + parentWindowId);

		String childWindowId = it.next();
		System.out.println("Child window id:" + childWindowId);

		driver.switchTo().window(childWindowId);

		Thread.sleep(2000);

		System.out.println("child window pop up title" + driver.getTitle());

		driver.close();

		driver.switchTo().window(parentWindowId);

		Thread.sleep(2000);

		System.out.println("parent window title" + driver.getTitle());

	}

}
