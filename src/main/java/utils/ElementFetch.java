package utils;
import org.openqa.selenium.By;
// Here we are overriding the concepts of find ELement 
//we are using our own 
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class ElementFetch {

	public WebElement getWebElements(String identifierType, String identifierValue)
	{
		switch(identifierType)
		{
		case "XPATH":
			return BaseTest.driver.findElement(By.xpath(identifierValue));

		case "CSS":
			return BaseTest.driver.findElement(By.cssSelector(identifierValue));

		case "ID":
			return BaseTest.driver.findElement(By.id(identifierValue));

		case "NAME":
			return BaseTest.driver.findElement(By.name(identifierValue));
		default:
			return null;

		}
	}
}
