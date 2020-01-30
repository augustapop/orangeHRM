package testClassOrange;

import org.testng.annotations.Test;
import POMOrange.LoginPageOrange;
import junit.framework.Assert;

public class TC01_LoginPage extends BaseClassOrange {

	// @Parameters({"browser"})
	@Test
	public void loginTest() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoginPageOrange lp = new LoginPageOrange(driver);

		lp.setLoginPageOR(getUserNameOrange, getPasswordOrange);
		if (driver.getPageSource().contains("HRM")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

}
