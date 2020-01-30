package openSourceOrange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;

public class VerifyDDSorted {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		
		WebElement element=driver.findElement(By.id("animals"));
		Select se=new Select(element);
		List originalList=new ArrayList();
		List tempList=new ArrayList();
		
		
		List <WebElement> options=se.getOptions();
		
		
		for(WebElement e:options)
		{
			originalList.add(e.getText());
			tempList.add(e.getText());
		}
		System.out.println(originalList);
		
		
	//	tempList=originalList;
		System.out.println("Before sorting"+originalList);		
		Collections.sort(tempList);	
		System.out.println("After sorting"+tempList);
		
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals(tempList, originalList);

		softAssert.assertAll();
		
		driver.close();
//		if(originalList==tempList)
//		{
//			Assert.assertTrue(true);
//			//System.out.println("DDL sorted");
//		}
//		
//		else{
//			Assert.assertTrue(false);
//			//System.out.println("DDL not sorted");
//		}
		
	}

}
