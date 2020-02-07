package diverse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class googleSearch {

	public static void main(String[] args) {
		WebDriver driver;

		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		// dynamic wait
		// driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://www.google.com");

		driver.findElement(By.name("q")).sendKeys("Java");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		List<WebElement> list = driver.findElements(By.id("center_col"));
		//findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbqs_c']"));

		System.out.println("total number of suggestions in search box:::===>" + list.size());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());
			if (list.get(i).getText().contains("java tutorial")) {
				list.get(i).click();
				break;
			}

		}
	}
}
