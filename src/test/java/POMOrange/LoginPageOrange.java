package POMOrange;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageOrange {
	
public	WebDriver ldriver;
	
	public LoginPageOrange(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(how=How.NAME,using="txtUsername") WebElement txtUserName;
	@FindBy(how=How.NAME,using="txtPassword") WebElement txtPassword;
	@FindBy(how=How.NAME,using="Submit") WebElement btnLogin;
	
	public void setLoginPageOR(String username,String pasword)
	{
		txtUserName.sendKeys(username);
		txtPassword.sendKeys(pasword);
		btnLogin.click();
	}
}
