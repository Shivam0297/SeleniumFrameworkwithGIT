package qa.test;

import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import pageEvents.LoginPageEvents;
import utils.ElementFetch;

public class TestCase1 extends BaseTest{
  
  ElementFetch ele =new ElementFetch();
  HomePageEvents homePage = new HomePageEvents();
  LoginPageEvents loginPage =  new LoginPageEvents();
  
  @Test (priority=2)
  public void sampleMethodforUI1() throws InterruptedException
  {
	  //homePage.loginButton();
	  homePage.clickonFeatureOption();
	  Thread.sleep(2000);
	  WebElement googletext= driver.findElement(By.xpath("//h5[text()='Google']"));
	  homePage.scrollandfindElement(googletext);
	  googletext.click();
	  
	  driver.findElement(By.xpath("//h2[text()='Google']")).isDisplayed();
		/*
		 * loginPage.verifyLoginPageLoaded(); loginPage.enterCredentials();
		 */
  }
	/*
	 * @Test(priority=1,dependsOnMethods="sampleMethodforUI1", alwaysRun=true)
	 * public void sampleMethodforUI2() throws InterruptedException {
	 * 
	 * homePage.clickonFeatureOption(); Thread.sleep(2000); WebElement apitext=
	 * driver.findElement(By.xpath("//h5[text()='API']"));
	 * homePage.scrollandfindElement(apitext); System.out.println(apitext);
	 * apitext.click(); System.out.println(apitext);
	 * driver.findElement(By.xpath("//h2[text()='API']")).isDisplayed();
	 * 
	 * }
	 */
}
