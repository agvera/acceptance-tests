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
package RegistrationPage;
import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

import CaptureScreenShot.UtilityRegistration;
import LoginPage.TDWeb_001;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TDWeb_004  {
	
	public static Properties Config=null;
	public static Properties LoginOR=null;
	public static WebDriver tdweb = null;
	public static String browserName = "Firefox";
	public static String currentURL; 
	public static String verifyLogIn;
	public static Properties RegOR=null;
	ExtentTest test;
	ExtentReports report;
	
	// Declaring the Property files from where the value or objects will be fetched
	public void IntializingProperties() throws IOException
	{
		Config= new Properties();
		FileInputStream config =new FileInputStream(System.getProperty("user.dir")+"/src//test//resources//Source//Config.properties");
		Config.load(config);
		
		LoginOR= new Properties();
		FileInputStream loginor =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//Source//LoginPage.properties");
		LoginOR.load(loginor);
		
		RegOR= new Properties();
		FileInputStream regor =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//Source//RegistrationPage.properties");
		RegOR.load(regor);
	}
	
	// Calling the application in various browser Chrome, Safari and Firefox. Note only chrome is implemented at the moment
	public void TDRegistrationPage() throws InterruptedException, IOException
	{
		
	
		report = new ExtentReports(System.getProperty("user.dir")+"/src//test//resources//Reports//Registration//RegistrationReport.html", true);
		
		if(Config.getProperty("BrowserName").equals("Chrome"))
		{
			test = report.startTest("TD Registration Page - In Chrome", "Opening the Application");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//Source//chromedriver");
			tdweb = new ChromeDriver();
			tdweb.manage().window().fullscreen();
			test.log(LogStatus.INFO, "Chrome Browser Launched Successfully");
			UtilityRegistration.capturescreenshot(tdweb, "BrowserOpened");
			tdweb.get(Config.getProperty("RegistrationURLStaging"));
			UtilityRegistration.capturescreenshot(tdweb, "ApplicationOpened");
			tdweb.findElement(By.id("question1")).click();
			tdweb.findElement(By.xpath(RegOR.getProperty("Link1"))).click();
			tdweb.findElement(By.xpath(RegOR.getProperty("Link2"))).click();
			tdweb.findElement(By.xpath(RegOR.getProperty("Link3"))).click();
			
			test.log(LogStatus.INFO, "Time Doctor Application Opened in Chrome");
			test.log(LogStatus.PASS, "Application opened sucessfully in Chrome");
			report.endTest(test);
			report.flush();
		}
		else if(Config.getProperty("BrowserName").equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","//Users//payalchoksey//Downloads//geckodriver3");
			WebDriver tdweb = new FirefoxDriver();
			tdweb.manage().window().fullscreen();
		//	test.log(LogStatus.INFO, "Firefox Browser Launched Successfully");
		//	UtilityRegistration.capturescreenshot(tdweb, "BrowserOpened");
			tdweb.get("https://www.timedoctorstaging.com");
			//tdweb.get(Config.getProperty("RegistrationURLStaging"));
			UtilityRegistration.capturescreenshot(tdweb, "ApplicationOpened");
			tdweb.findElement(By.id("question1")).click();
			tdweb.findElement(By.xpath(RegOR.getProperty("Link1"))).click();
			tdweb.findElement(By.xpath(RegOR.getProperty("Link2"))).click();
			tdweb.findElement(By.xpath(RegOR.getProperty("Link3"))).click();
			
		//	test.log(LogStatus.INFO, "Time Doctor Application Opened in Firefox");
		//	test.log(LogStatus.PASS, "Application opened sucessfully in Firefox");
		//	report.endTest(test);
		//	report.flush(); 
			
			/*WebDriver driver1= new FirefoxDriver();
			driver1.manage().window().fullscreen();
			driver1.get("https://www.timedoctorstaging.com");*/
		}
		else if(Config.getProperty("BrowserName").equals("Safari"))
		{

			System.out.println(" Safari to be implemented");
			
		}
		else if(Config.getProperty("BrowserName").equals("IE"))
		{

			System.out.println(" Safari to be implemented");
			
		}
		else
		{
			System.out.println(" Browser not compatible");
		}
	}
	
	// Verifying the Title of the page displayed
	public void VerifyTitle() throws InterruptedException, IOException
		{
			test = report.startTest("TD Title of Page", "Verifying the Title of the Page");
			String actualTitle = tdweb.getTitle();
			test.log(LogStatus.INFO, "Actual Title of Page returned :: " + actualTitle);
			String expectedTitle = "Smart Employee Time Tracking Software with Screenshots | Time Doctor";
			test.log(LogStatus.INFO, "Expected Title of Page returned :: "+ expectedTitle);
			Assert.assertEquals(actualTitle,expectedTitle);
			UtilityRegistration.capturescreenshot(tdweb, "TitleofPage");
			test.log(LogStatus.PASS, "Title of the Page is valid");	
			tdweb.manage().window().fullscreen();
			Thread.sleep(3000L);
			report.endTest(test);
			report.flush();
		}
	
	// Entering the valid credentials on Registration Page
	public void RegistrationDetails () throws InterruptedException, IOException
	
		{
			String registrationType = "TestmySelf";
			String localEmail = "payal+";
			String domainEmail = "@staff.com";
        
        // Different ways to Register TestmySelf, Invite Team, Unique link
		
		  if (registrationType.equals("TestmySelf"))
		  {	
			  	test = report.startTest("TD Registration Page", "Entering the details on Registration Page");
			  	test.log(LogStatus.INFO, "Testing for Test My Self Option");
			  	tdweb.findElement(By.id(RegOR.getProperty("Self"))).click();
			  	tdweb.findElement(By.id(RegOR.getProperty("Continue"))).click();
			  	tdweb.findElement(By.id(RegOR.getProperty("Name"))).clear();
			  	tdweb.findElement(By.id(RegOR.getProperty("Name"))).sendKeys("PayalSelf");
			    test.log(LogStatus.INFO, "Entered the name as  :: " + "PayalSelf");
			    tdweb.findElement(By.id(RegOR.getProperty("Email"))).clear();
			    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
		        Date now = new Date();
		        String strDate = sdfDate.format(now);
		        tdweb.findElement(By.id(RegOR.getProperty("Email"))).sendKeys(localEmail+strDate+domainEmail);
			    test.log(LogStatus.INFO, "Entered the email as  :: " + localEmail+strDate+domainEmail);
			    System.out.println(localEmail+strDate+domainEmail);
			    tdweb.findElement(By.id(RegOR.getProperty("Password"))).clear();
			    tdweb.findElement(By.id(RegOR.getProperty("Password"))).sendKeys("payal123");
			    test.log(LogStatus.INFO, "Entered the password as  :: " + "payal123");
			    System.out.println("payal123");
			    // This the data.txt file again saved in Structure 
			    File newfile = new File(System.getProperty("user.dir")+"/src//test//resources//Source/data.txt");
			    newfile.createNewFile();
			 // This the data.txt file again saved in Structure 
			    FileWriter write = new FileWriter (System.getProperty("user.dir")+"/src//test//resources//Source/data.txt");
			    BufferedWriter out = new BufferedWriter(write);
			    out.write(localEmail+strDate+domainEmail);
			    out.newLine();
			    out.write("payal123");
			    out.newLine();
			    out.flush();
			    UtilityRegistration.capturescreenshot(tdweb, "TestmyselfDetails");
			    tdweb.findElement(By.id(RegOR.getProperty("GetStarted"))).click();
			    Thread.sleep(5000L);
			    test.log(LogStatus.PASS, "Details are entered");
			    report.endTest(test);
		   		report.flush();
		   		currentURL=tdweb.getCurrentUrl();
			    if (currentURL.equals("https://www.timedoctorstaging.com/"))
			    {
			    	test = report.startTest("TD DashBoard URL", "Checking the Dashboard URL is opened correctly ");
			    	assertEquals(tdweb.findElement(By.cssSelector(".tooltip-inner")).getText(), "A user with that email already exists!");
			    	test.log(LogStatus.INFO, "A user with that email already exists!");	
			    	test.log(LogStatus.FAIL, "User has entered invalid details");	
			    	test.log(LogStatus.FAIL, " User is unable to navigate to Dashboard Page - Wrong data entered");	
			    	UtilityRegistration.capturescreenshot(tdweb, "Invaliddashboardopened");
					test.log(LogStatus.INFO, "Screenshot captured after user entering invalid data");
					report.endTest(test);
			   		report.flush();
				}
			    else if (currentURL.equals("https://login.timedoctorstaging.com/#/dashboard-company"))
			    {
			    	test = report.startTest("TD DashBoard URL", "Checking the Dashboard URL is opened correctly ");
			    	test.log(LogStatus.PASS, "User has entered valid details");	
			    	test.log(LogStatus.PASS, "Dashboard page has opened successfully"+currentURL);	
			    	UtilityRegistration.capturescreenshot(tdweb, "ValidRegDetails");
					test.log(LogStatus.INFO, "Screenshot captured after user entering valid data");
					Thread.sleep(10000L);
					String homepage = tdweb.getWindowHandle();
					System.out.println(homepage);
					UtilityRegistration.capturescreenshot(tdweb, "popuplogin");
					tdweb.findElement(By.cssSelector("#discount-popup-close")).click();
					test.log(LogStatus.PASS, "Popup is sucessfully closed");
					Thread.sleep(2000L);
					
					tdweb.findElement(By.xpath(RegOR.getProperty("Link4"))).click();
					Thread.sleep(2000L);
					tdweb.findElement(By.xpath(RegOR.getProperty("Link5"))).click();
					Thread.sleep(2000L);
					tdweb.findElement(By.xpath(RegOR.getProperty("Link6"))).click();
					Thread.sleep(5000L);
					test.log(LogStatus.PASS, "All popup closed");
					tdweb.findElement(By.xpath("//*[@id='mCSB_1_container']/ul[1]/li/div")).click();
					UtilityRegistration.capturescreenshot(tdweb, "logout");
					tdweb.findElement(By.xpath(RegOR.getProperty("Link7"))).click();
					test.log(LogStatus.PASS, "User has sucessfully logged out from the application");
			    	report.endTest(test);
			   		report.flush();
			    }
			    else
			    {
			    	test = report.startTest("TD DashBoard URL", "Checking the Dashboard URL is opened correctly ");
			    	test.log(LogStatus.FAIL, "Please check the connectivity");	
			    	test.log(LogStatus.INFO, "OOPS!! please check your connectivity");
			    	UtilityRegistration.capturescreenshot(tdweb, "WrongDashboard");
			    	System.out.println("Please check you connectivity");
			    	report.endTest(test);
			   		report.flush();
			    }
		  }
			 else if (registrationType.equals("InviteTeam"))
			    {
			    	test = report.startTest("TD Registration Page", "Entering the details on Registration Page");
				  	test.log(LogStatus.INFO, "Testing for Invite Team");
				  	tdweb.findElement(By.xpath(RegOR.getProperty("MyTeam"))).click();
				  	tdweb.findElement(By.id(RegOR.getProperty("Continue"))).click();
				  	tdweb.findElement(By.id(RegOR.getProperty("Name"))).clear();
				  	tdweb.findElement(By.id(RegOR.getProperty("Name"))).sendKeys("PayalTeam");
				    test.log(LogStatus.INFO, "Entered the name as  :: " + "PayalTeam");
				    tdweb.findElement(By.id(RegOR.getProperty("Email"))).clear();
				    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
			        Date now = new Date();
			        String strDate = sdfDate.format(now);
			        tdweb.findElement(By.id(RegOR.getProperty("Email"))).sendKeys(localEmail+strDate+domainEmail);
				    test.log(LogStatus.INFO, "Entered the email as  :: " + localEmail+strDate+domainEmail);
				    tdweb.findElement(By.id(RegOR.getProperty("Password"))).clear();
				    tdweb.findElement(By.id(RegOR.getProperty("Password"))).sendKeys("payal123");
				    test.log(LogStatus.INFO, "Entered the password as  :: " + "payal123");
				    UtilityRegistration.capturescreenshot(tdweb, "InviteTeam");
				    tdweb.findElement(By.id(RegOR.getProperty("GetStarted"))).click();
				    Thread.sleep(5000L);
				    test.log(LogStatus.PASS, "Details are entered");
				    report.endTest(test);
			   		report.flush();
				    currentURL=tdweb.getCurrentUrl();
				    if (currentURL.equals("https://www.timedoctorteststaging.com/"))
				    {
				    	test = report.startTest("TD Registration Page", "Entering the details on Registration Page");
					  	test.log(LogStatus.FAIL, "Invalid Registration information are entered");
				    	System.out.println("Enter the valid credentials");
				    	UtilityRegistration.capturescreenshot(tdweb, "wrongcredentials");
				    	report.endTest(test);
				   		report.flush();
				    }
				    else if (currentURL.equals("https://login.timedoctorstaging.com/#/dashboard-company"))
				    {
				    	test = report.startTest("TD DashBoard URL", "Checking the Dashboard URL is opened correctly ");
				    	test.log(LogStatus.PASS, "User has entered valid details");	
				    	test.log(LogStatus.PASS, "Dashboard page has opened successfully"+currentURL);	
				    	UtilityRegistration.capturescreenshot(tdweb, "ValidRegDetails");
						test.log(LogStatus.INFO, "Screenshot captured after user entering valid data");
						Thread.sleep(10000L);
						String homepage = tdweb.getWindowHandle();
						System.out.println(homepage);
						tdweb.findElement(By.cssSelector("#discount-popup-close")).click();
						test.log(LogStatus.PASS, "Popup is sucessfully closed");
						Thread.sleep(2000L);
						tdweb.findElement(By.xpath(RegOR.getProperty("Link4"))).click();
						Thread.sleep(2000L);
						tdweb.findElement(By.xpath(RegOR.getProperty("Link5"))).click();
						Thread.sleep(2000L);
						tdweb.findElement(By.xpath(RegOR.getProperty("Link6"))).click();
						Thread.sleep(5000L);
						tdweb.findElement(By.xpath("//*[@id='mCSB_1_container']/ul[1]/li/div")).click();
						tdweb.findElement(By.xpath("//*[@id='company-list']/li/a")).click();
						test.log(LogStatus.PASS, "User has sucessfully logged out from the application");
				    	report.endTest(test);
				   		report.flush();
				    }
				    else
				    {
				    	System.out.println("Please check you connectivity");
				    }
			    }
			  else if (registrationType.equals("InviteLink"))
			    {
			    	test = report.startTest("TD Registration Page", "Get started with Time Doctor in less than 60 seconds!");
				  	test.log(LogStatus.INFO, "Inviting user the the unique link");
				  	tdweb.navigate().to("https://login.timedoctortest.com/v2/setup/activatev3/company_code.php?company_link=f6hy1z");
				  	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
			        Date now = new Date();
			        String strDate = sdfDate.format(now);
			        tdweb.findElement(By.xpath("//*[@id='emailAddress']")).sendKeys(localEmail+strDate+domainEmail);
			        test.log(LogStatus.INFO, "Entered the email as  :: " + localEmail+strDate+domainEmail);
			        tdweb.findElement(By.xpath("//*[@id='firstName']")).sendKeys("Payalfirst");
			        test.log(LogStatus.INFO, "Entered the FirstName as  :: " + "PayalTestLink");
			        tdweb.findElement(By.xpath("//*[@id='lastName']")).sendKeys("Payallast");
			        test.log(LogStatus.INFO, "Entered the LastName as  :: " + "Payallast");
			        tdweb.findElement(By.xpath("//*[@id='password']")).sendKeys("Payal$123");
			        test.log(LogStatus.INFO, "Entered the Password as  :: " + "Payal$123");
			        tdweb.findElement(By.xpath("//*[@id='passwd-form']/div[6]/button")).click();
				    UtilityRegistration.capturescreenshot(tdweb, "Invitebylink");
				    tdweb.navigate().to("https://www.timedoctortest.com/");
				    tdweb.findElement(By.xpath("//*[@id='main-content']/section[1]/div/header/div/a/span[1]")).click();
				    tdweb.findElement(By.xpath("//*[@id='mCSB_1_container']/ul[1]/li/div")).click();
					tdweb.findElement(By.xpath("//*[@id='company-list']/li/a")).click();
					test.log(LogStatus.PASS, "User has sucessfully logged out from the application");
					UtilityRegistration.capturescreenshot(tdweb, "Invitebylinklogout");
				    Thread.sleep(5000L);
				    test.log(LogStatus.PASS, "Details are entered");
				    report.endTest(test);
			   		report.flush();
			   	}
				 else
				    {
				    	System.out.println("Please check you connectivity");
				    	test = report.startTest("TD Registration Page", "Entering the details on Registration Page");
				    	test.log(LogStatus.FAIL, "No such Registration found");
				    	System.out.println("No such resgistration process found");
				    	report.endTest(test);
				    	report.flush();
				    }
		}
		  
	// Login with new credentials 
	public void loginwithnewcredentials() throws IOException
			{
				test = report.startTest("Time Doctor Login Page - Normal Login ", "Login");
				test.log(LogStatus.INFO, "Login Page - Login"); 
				 FileInputStream fstream = new FileInputStream(System.getProperty("user.dir")+"/src//test//resources//Source/data.txt");
		         // Use DataInputStream to read binary NOT text.
				 BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		         String strLine;
		         int count = 0;
		         count++;
		         while((strLine = br.readLine())!= null)
		         {
		             //Enter userName
		             tdweb.findElement(By.cssSelector("#email")).sendKeys(strLine);
		             System.out.println(strLine);
		             strLine = br.readLine();
		             count++;
		             test.log(LogStatus.INFO, "Email address entered"); 
		             //Enter Password
		             tdweb.findElement(By.cssSelector("#password")).sendKeys(strLine);
		             System.out.println(strLine);
		             test.log(LogStatus.INFO, "password entered"); 
		             tdweb.findElement(By.xpath("//*[@id='signinFormButton']")).click();
		             test.log(LogStatus.PASS, "User sucessfully logged in ");
		     		 tdweb.findElement(By.cssSelector(".company-section > h3")).click();
		     		 tdweb.findElement(By.cssSelector("a[href^=\"/logout\"] > span")).click();
		     		
		         }
		       
		         br.close();
		         report.endTest(test);
		         report.flush();
		         tdweb.close();
}
}
// Ending of Login Page 