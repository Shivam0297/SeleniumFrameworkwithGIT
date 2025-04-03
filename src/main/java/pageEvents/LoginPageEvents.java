package pageEvents;

import org.testng.Assert;

import pageObject.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {

	ElementFetch ele = new ElementFetch();

	/*
	 * public void verifyLoginPageLoaded() {
	 * Assert.assertTrue(ele.getWebElements("XPATH",
	 * LoginPageElements.loginText).isDisplayed(), "Element not found"); }
	 */
	public void enterCredentials()
	{
		ele.getWebElements("XPATH", LoginPageElements.emailAddress).sendKeys("someone@gmail.com");
		ele.getWebElements("XPATH", LoginPageElements.passwordField).sendKeys("12345");
	}
}
