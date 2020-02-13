package diverse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebTables {

	public static void main(String[] args) {
		// http://webdatacommons.org/webtables/
		// xpath=.//*[@id='toccontent']/table[1]/tbody/tr[3]/td[2]

		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://webdatacommons.org/webtables/");
		WebElement elemetn = driver.findElement(By.xpath(".//*[@id='toccontent']/table[1]/tbody/tr[3]/td[2]"));
		System.out.println(elemetn.getText());
		//numbers of rows
		List<WebElement> rows=driver.findElements(By.xpath(".//*[@id='toccontent']/table[1]/tbody/tr"));
		int countRows=rows.size();
		System.out.println("Number of rows are : " + countRows);
		
		driver.close();

	}

}
