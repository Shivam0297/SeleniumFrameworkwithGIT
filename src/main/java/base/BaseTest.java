package base;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;


public class BaseTest {

	public static WebDriver driver;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@BeforeTest
	//Initializing extent report in baseTest
	public void beforeTestMethod() 
	{
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator+"reports"+File.separator+"ShivamExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("HostName", "RHEL8");
		extent.setSystemInfo("UserName", "root");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Automation Tests Results by Shivam");
	}

	
	//Initializing the driver
	//@Parameters("browser") //will pass from TestNG.xml and accepting this parameter
	/*
	 * public void beforeMethod(@Optional String browser, Method testMethod) {
	 * 
	 * logger = extent.createTest(testMethod.getName());//get result log of each
	 * method/each test run in extent report setupDriver(browser);// Initializing
	 * browser according to browser name driver.manage().window().maximize();
	 * //maximize the window driver.get(Constants.url);//get the specific link of
	 * website to test
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); }
	 */
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(@Optional("chrome") String browser, Method testMethod) {
	    logger = extent.createTest(testMethod.getName());
	    
	    if (browser == null || browser.isEmpty()) {
	        browser = "chrome";  // Set a default browser to avoid null issues
	    }
	    
	    setupDriver(browser);
	    driver.manage().window().maximize();
	    driver.get(Constants.url);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}


	@AfterMethod
	//getting/capturing the extent report
	public void afterMethod(ITestResult result) 
	{
		if(result.getStatus() == ITestResult.FAILURE) 
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() +" - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED)); 
		}
		else if(result.getStatus() == ITestResult.SKIP) 
		{
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() +" - Test Case Skipped", ExtentColor.ORANGE));
		}
		else if(result.getStatus() == ITestResult.SUCCESS) 
		{
			logger.log(Status.PASS, MarkupHelper.createLabel (result.getName() +" - Test Case PASS", ExtentColor.GREEN));
		}
		driver.quit();

	}
	@AfterTest
	//flush out the extent report
	public void afterTest()
	{
		extent.flush();
	}

	public void setupDriver(String browser) {

		if(browser.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}
}