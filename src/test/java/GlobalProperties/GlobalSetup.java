package GlobalProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GlobalSetup {
	
	public static WebDriver driver;
	public static Properties testProperty = new Properties();
	FileInputStream globalProperties;
	public static ExtentTest test;
	public static ExtentReports report;
	
	@BeforeTest
	public void SuiteSetup() throws IOException {
		
		globalProperties = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Source/Config.properties");
		testProperty.load(globalProperties);
		
		// If browser is set to Google Chrome		
		if(testProperty.getProperty("BrowserName").equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/Source/chromedriver");
			driver = new ChromeDriver();
		}
		
		// If browser is set to Mozilla Firefox		
		if(testProperty.getProperty("BrowserName").equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/Source/geckodriver");
			driver = new FirefoxDriver();
		}
		
		// Open base URL
		driver.get(testProperty.getProperty("BaseURL"));
		
		// Adjust screen size to full screen	
		// driver.manage().window().fullscreen();
		
		// Set implicit wait
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		// Set Report directory
		report = new ExtentReports(System.getProperty("user.dir") + "/src/test/resources/Reports/"+ this.getClass().getSimpleName() +"/" + this.getClass().getSimpleName() + "Report.html", true);
		
	}
		
	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		if(result.getStatus() == ITestResult.FAILURE){
			this.takeScreenshot();
			test.log(LogStatus.FAIL, "Failing Test Case: " + result.getName() + test.addScreenCapture(imagePath()));
			test.log(LogStatus.FAIL, "ERROR: "+result.getThrowable());
		} else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName() + test.addScreenCapture(imagePath()));
		}
		report.endTest(test);
	}
	
	@AfterTest
	public void SuiteTeardown() {
		report.flush();
		driver.quit();
	}
	
	public void takeScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(imagePath()));
	}
	
	public String imagePath() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmsss");
	    Date now = new Date();
	    String timeStamp = sdfDate.format(now);
		String imgDir = System.getProperty("user.dir") + "/src/test/resources/Reports/"+ this.getClass().getSimpleName() + timeStamp + ".png";
		return imgDir;
	}
	
}
