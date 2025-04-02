package pageEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import base.BaseTest;
import pageObject.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents extends BaseTest{
	
	ElementFetch ele = new ElementFetch();
	public void loginButton()
	{
		ele.getWebElements("XPATH", HomePageElements.loginButtonText).isDisplayed();
	}

	public void clickonFeatureOption()
	{
		ele.getWebElements("XPATH", HomePageElements.features).isDisplayed();
		ele.getWebElements("XPATH", HomePageElements.features).click();
		
	}
	public WebElement scrollandfindElement(WebElement element)
	{
		
		JavascriptExecutor js = ((JavascriptExecutor )driver);
		js.executeScript("return arguments[0].scrollIntoView()",element );
		return element;
	}
}
