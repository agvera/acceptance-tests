/**
* Project Name : Time Doctor Automation Framework 
* Author : Time Doctor QA Team
* Version : V 1.0 
* Reviewed By : Sandy
* Date of Creation : 08/31/2018
* Modification History : 
* Date of change : 09/03/2018 
* Version : V 1.1 
* changed function :  
* change description : Added all the standards and modified the code
* Modified By : Payal Choksey
*/
package LoginPage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TDWeb_001 {
	public static Properties Config=null;
	public static Properties LoginOR=null;
	public static WebDriver tdweb = null;
	public static String browserName = "Chrome";
	public static String currentURL;
	public static String verifyLogIn;
	public ExtentTest testReport;
	
	
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/Reports/TDLoginPageReports.html");
	ExtentReports extentRep = new ExtentReports();
	
	// Declaring the Property files from where the value or objects will be fetched
	
	public void IntializingProperties() throws IOException
	{
		Config= new Properties();
		FileInputStream config =new FileInputStream(System.getProperty("user.dir")+"/src//test//resources//Source//Config.properties");
		Config.load(config);
		LoginOR= new Properties();
		FileInputStream loginor =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//Source//LoginPage.properties");
		LoginOR.load(loginor);
	}
	
	// Calling the application in various browser Chrome, Safari and Firefox. Note only chrome is implemented at the moment
	
	public void TDLoginPage() throws InterruptedException, IOException
	{
		if(Config.getProperty("BrowserName").equals("Chrome"))
		{
			extentRep.attachReporter(htmlReporter);
			testReport = extentRep.createTest("TD Login Page - In Chrome", "Opening the Application");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//Source//chromedriver");
			tdweb = new ChromeDriver();
			tdweb.manage().window().maximize();
			testReport.log(Status.INFO, "Chrome Browser Launched Successfully");
			tdweb.get(Config.getProperty("LoginURL"));
			testReport.log(Status.INFO, "Time Doctor Application Opened in Chrome");
			testReport.log(Status.PASS, "Application opened sucessfully in Chrome");
			extentRep.flush();
		}
		else if(Config.getProperty("BrowserName").equals("Firefox"))
		{
			System.out.println(" Firefox to be implemented");
		}
		else if(Config.getProperty("BrowserName").equals("Safari"))
		{

			System.out.println(" Safari to be implemented");
			
		}
	}
		public void VerifyTitle() throws InterruptedException, IOException
		{
			
		
		testReport = extentRep.createTest("TD Title of Page", "Verifying the Title of the Page");
		String actualTitle = tdweb.getTitle();
		testReport.log(Status.INFO, "Actual Title of Page returned :: " + actualTitle);
		String expectedTitle = "Login to Time Doctor";
		testReport.log(Status.INFO, "Expected Title of Page returned :: "+ expectedTitle);
		Assert.assertEquals(actualTitle,expectedTitle);
		testReport.log(Status.PASS, "Title of the Page is valid");	
		tdweb.manage().window().fullscreen();
		Thread.sleep(3000L);
		extentRep.attachReporter(htmlReporter);
		}
		public void ValidCredentials() throws InterruptedException, IOException
		{
		testReport = extentRep.createTest("TD Valid Login", "Entering with Valid Data");
		tdweb.findElement(By.xpath(LoginOR.getProperty("Email"))).sendKeys("payal+49test@staff.com");
		tdweb.findElement(By.xpath(LoginOR.getProperty("Password"))).sendKeys("payal123");
		tdweb.findElement(By.xpath(LoginOR.getProperty("Signinbtn"))).click();
		testReport.log(Status.INFO, "Entering the valid credentials");
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		currentURL=tdweb.getCurrentUrl();
		if(currentURL.equals(Config.getProperty("DashboardURL")))
			{
				testReport.log(Status.PASS, "Allowed to accept valid credentials");	
				File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE);
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/vloginpage.jpg"));// copies the image that is bytes to .jpg
				testReport.log(Status.INFO, "Screenshot captured after login with valid credentials");

			}
		else
			{
			//	test.log(Status.FAIL, "Incorrect Credentials Entered");	
				File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/inloginpage.jpg"));	
			}
		extentRep.flush();
		}
	
}	
// Ending of Login Page 