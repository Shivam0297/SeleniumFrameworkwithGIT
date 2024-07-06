package utils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
// if there are any test failed then we are going to take retry test and take screenshots of failed Test case
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.BaseTest;
//ITestListener is used 
//Retry the test cases when failed using IAnnotationTransformer
public class SuiteListener implements ITestListener , IAnnotationTransformer{
	//To take screenshot of failed test case
	public void onTestFailure(ITestResult result) 
	{	
		//saving same file using user.dir i.e in same directory
		String filename= System.getProperty("user.dir"+File.separator+"screenshots"+File.separator+result.getMethod().getMethodName());
		//Take screenshot and save as a file format in file using BaseTest.java driver
		File f1= ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
		//align both filename as file . copy the taken screenshot to the filename as .png
		try {
			FileUtils.copyFile(f1,new File(filename+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void transform(
			ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);//setting the retry for failed cases from RetryAnalyzer class
	}
}
