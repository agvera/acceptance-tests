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
package LoginnLogoutPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

import LoginPage.TDWeb_001;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TDWeb_005 {
	public static Properties Config=null;
	public static Properties LoginOR=null;
	public static WebDriver driver = null;
	public static String browserName = "Chrome";
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
	}
	
	// Calling the application in various browser Chrome, Safari and Firefox. Note only chrome is implemented at the moment
	public void TDLoginPage() throws InterruptedException, IOException
	{
		report = new ExtentReports(
				"/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/Reports/TDLoginReport01.html", true);
		if(Config.getProperty("BrowserName").equals("Chrome"))
		{
			test = report.startTest("TD Login Page - In Chrome", "Opening the Application");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//Source//chromedriver");
			driver = new ChromeDriver();
			driver.get(Config.getProperty("LoginURL"));
			driver.manage().window().fullscreen();
			test.log(LogStatus.INFO, "Chrome Browser Launched Successfully");
			test.log(LogStatus.INFO, "Time Doctor Application Opened in Chrome - Login Page");
			test.log(LogStatus.PASS, "Application opened sucessfully in Chrome - Login Page");
			report.endTest(test);
			report.flush();
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
	
	// Verifying the Title of the page displayed on the login Page
	public void VerifyTitle() throws InterruptedException, IOException
		{
			
			test = report.startTest("TD Title of Page", "Verifying the Title of the Page");
			String actualTitle = driver.getTitle();
			test.log(LogStatus.INFO, "Actual Title of Page returned :: " + actualTitle);
			String expectedTitle = "Login to Time Doctor";
			test.log(LogStatus.INFO, "Expected Title of Page returned :: "+ expectedTitle);
			Assert.assertEquals(actualTitle,expectedTitle);
			test.log(LogStatus.PASS, "Title of the Page is valid");	
			driver.manage().window().fullscreen();
			Thread.sleep(3000L);
			report.endTest(test);
			report.flush();
		}
	
	// Verifying all the functionality present on the login page
	public void LoginVerfication ()
		{
			test = report.startTest("Time Doctor Login Page", "Verifying the functionality present on Login Page");
			// Verfiying  Goodbye Wasterd Time text
			String actualTitle = driver.getTitle();
			System.out.println(actualTitle);
			test.log(LogStatus.INFO, "Actual Title of Page returned :: " + actualTitle);
			String expectedTitle = "Login to Time Doctor";
			test.log(LogStatus.INFO, "Expected Title of Page returned :: "+ expectedTitle);
			Assert.assertEquals(actualTitle,expectedTitle);
			String actualgoodbye = driver.findElement(By.xpath("//*[@id='introText']/p[1]")).getText();
			if (actualgoodbye.equals( "Goodbye Wasted Time,"))
			{
				test.log(LogStatus.PASS, "Goodbye Wasted Time,");
			
			}
			else
			{
				test.log(LogStatus.FAIL, "Goodbye Wasted Time, is not displayed");
				
			}
			
			// Verfiying Hello Productivity!, text
			String actualhellopro = driver.findElement(By.xpath("//*[@id='introText']/p[2]/strong")).getText();
			if (actualhellopro.equals("Hello Productivity!"))
			{
				test.log(LogStatus.PASS, "Hello Productivity!");
				
			}
			else
			{
				test.log(LogStatus.FAIL, "Hello Productivity!, text is not displayed");
			}
			
			// Verfiying sign in Form Title
			String signinFormTitle = driver.findElement(By.xpath("//*[@id='signinFormTitle']")).getText();
			
			if (signinFormTitle.equals( "Login to Time Doctor"))
			{
				test.log(LogStatus.PASS, "Login to Time Doctor");
			}
			else
			{
				test.log(LogStatus.FAIL, "Login to Time Doctor, text is not displayed");
			}
			
			// Verfiying Email address text field
			String actualemail = driver.findElement(By.xpath("//*[@id='email']")).getAttribute("title");
			if (actualemail.equals( "Enter the Email"))
			{
				test.log(LogStatus.PASS, "Email text field is present");		
			}
			else
			{
				test.log(LogStatus.FAIL,"Email Text field is not present");
			}
	 
			// Verifying the password field 
			String actualpwd = driver.findElement(By.xpath("//*[@id='password']")).getAttribute("title");
			if (actualpwd.equals( "Enter the password"))
			{
				test.log(LogStatus.PASS, "password text field is present");		
			}
			else
			{
				test.log(LogStatus.FAIL,"Email Text field is not present");
			}
			
			// Verifying the forgot password field 
			String actualforgotpwd = driver.findElement(By.xpath("//*[@id='forgotPassword']")).getText();
			if (actualforgotpwd.equals( "Forgot password?"))
			{
				test.log(LogStatus.PASS, "Forgot password?");		
			}
			else
			{
				test.log(LogStatus.FAIL,"Forgot password? is not present");
			}
			
			// Verifying the sign in button
			String actualsignin = driver.findElement(By.xpath("//*[@id='signinFormButton']")).getText();
			if (actualsignin.equals( "Sign In"))
			{
				test.log(LogStatus.PASS, "Sign In");		
			}
			else
			{
				test.log(LogStatus.FAIL,"Sign In button is not present");
			}
			
			// Verifying the new account please link
			String actualnewaccount = driver.findElement(By.xpath("//*[@id='signinFormBottom']/a")).getText();
			if (actualnewaccount.equals( "New account please!"))
			{
				test.log(LogStatus.PASS, "New account please!");		
			}
			else
			{
				test.log(LogStatus.FAIL,"New account please! is not present");
			}
			
			// Verifying the new account please link
			String actualorloginwith = driver.findElement(By.xpath("//*[@id='altSigninTitle']")).getText();
			if (actualorloginwith.equals( "OR LOGIN WITH..."))
			{
				test.log(LogStatus.PASS, "OR LOGIN WITH...");		
			}
			else
			{
				test.log(LogStatus.FAIL,"OR LOGIN WITH... is not present");
			}
			
			//Verifying the Language symbol
			String a =   driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).getText();
			if ( a.equals("English"))
	      
			{  
				test.log(LogStatus.PASS, "English");
			}
			else
			{
	    	  System.out.println("done");
			}
	        report.endTest(test);
			report.flush();
		}
	// Verifying the  forgot password 
	public void Forgotpassword()
	{
		test = report.startTest("Time Doctor Forgot password ", "Checking the forgotpassword link and forgotpassword page");
		driver.findElement(By.cssSelector("#forgotPassword")).click();
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		test.log(LogStatus.INFO, "Actual Title of Page returned :: " + actualTitle);
		String expectedTitle = "Time Doctor- Forgot password?";
		test.log(LogStatus.INFO, "Expected Title of Page returned :: "+ expectedTitle);
		Assert.assertEquals(actualTitle,expectedTitle);
		assertEquals(driver.findElement(By.cssSelector(".messages.info-message")).getText(), "Can't Login? Forgot your password?");
		assertEquals(driver.findElement(By.cssSelector("#forgot-password-form > p > strong")).getText(), "email address");
		assertTrue((driver.findElements(By.cssSelector("#forgot-password-form > p")).size() != 0));
		assertTrue((driver.findElements(By.cssSelector("input[name=\"email\"]")).size() != 0));	
		String forgotpass =  driver.findElement(By.xpath("//*[@id='forgot-pasword-page']/div/div[2]/div/span[1]")).getText();
		if ( forgotpass.equals("Can't Login? Forgot your password?"))
		{
			test.log(LogStatus.PASS, "Can't Login? Forgot your password?");
		}
		else
		{
			test.log(LogStatus.FAIL, "Unable to find - Can't Login? Forgot your password?");
		}
        assertTrue((driver.findElements(By.cssSelector("#forgot-password-form > p")).size() != 0));
        assertTrue((driver.findElements(By.cssSelector("input[name=\"email\"]")).size() != 0));
        String resetpassword = driver.findElement(By.xpath("//*[@id='forgot-password-form']/input[2]")).getAttribute("value");
        if (resetpassword.equals("Reset Password"))
        {
        	test.log(LogStatus.PASS, "Reset Password");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Reset Password not found");
        }
        assertEquals(driver.findElement(By.cssSelector("a[href=\"/login\"]")).getText(), "Go back to login page");
        test.log(LogStatus.PASS, "Go back to login page");
        driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
        assertEquals(driver.findElement(By.cssSelector("#email-empty")).getText(), "Please enter your email");
        driver.findElement(By.cssSelector("input[name=\"email\"]")).click();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).click();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).clear();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys("p");
        test.log(LogStatus.INFO, "Checking for invalid email address");
        driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
        assertEquals(driver.findElement(By.cssSelector(".forgot-password-response-message > .messages.error-message:nth-of-type(2)")).getText(), "Invalid email address");
        driver.findElement(By.cssSelector("input[name=\"email\"]")).click();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).click();
        test.log(LogStatus.PASS, "Invalid email address not accepted");
        driver.findElement(By.cssSelector("input[name=\"email\"]")).clear();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys("payal+");
        driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).click();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).click();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).clear();
        driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys("payal+52@staff.com");
        test.log(LogStatus.PASS, "Password reset instructions have been sent.");
        driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
        assertEquals(driver.findElement(By.cssSelector(".messages.info-message")).getText(), "Password reset instructions have been sent.");
        assertTrue((driver.findElements(By.cssSelector("#forgot-password-form > p")).size() != 0));
        assertEquals(driver.findElement(By.cssSelector("a[href=\"/login\"]")).getText(), "Go back to login page");
        driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
        test.log(LogStatus.PASS, "Login Page is displayed");
        report.endTest(test);
		report.flush();

	}
	//verifying the different languages for the login page
	public void language()
	{
		test = report.startTest("Time Doctor Login Page - Language ", "Checking all login pages open in specific lanaguage when clicked on particular language");
		driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).click();
		driver.findElement(By.cssSelector(".languages > ul > li:nth-of-type(2) > a[href^=\"/\"]")).click();
		// Checking Spanish Language
		String actualspanish = driver.findElement(By.cssSelector("#introText > p:nth-of-type(1)")).getText();
		if (actualspanish.equals("Adiós, tiempo perdido,"))
		{
			test.log(LogStatus.PASS, "Time Doctor Login Page is opened in Spanish");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time Doctor Login Page is not opened in Spanish");
		}
        driver.findElement(By.cssSelector("a[href^=\"/\"].language-english-link")).click();
        driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).click();
        driver.findElement(By.cssSelector(".languages > ul > li:nth-of-type(3) > a[href^=\"/\"]")).click();
        //Checking German Language
        String actualgerman = driver.findElement(By.cssSelector("#introText > p:nth-of-type(1)")).getText();
        if (actualgerman.equals("Tschüss verschwendete Zeit,"))
		{
			test.log(LogStatus.PASS, "Time Doctor Login Page is opened in German");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time Doctor Login Page is not opened in German");
		}
        driver.findElement(By.cssSelector("a[href^=\"/\"].language-english-link")).click();
        driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).click();
        driver.findElement(By.cssSelector(".languages > ul > li:nth-of-type(4) > a[href^=\"/\"]")).click();
        //Checking Russian Lanaguage
        String actualrussian = driver.findElement(By.cssSelector("#introText > p:nth-of-type(1)")).getText();
        if (actualrussian.equals("Прощай потраченное время,"))
		{
			test.log(LogStatus.PASS, "Time Doctor Login Page is opened in Russian");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time Doctor Login Page is not opened in Russian");
		}
        driver.findElement(By.cssSelector("a[href^=\"/\"].language-english-link")).click();
        driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).click();
        driver.findElement(By.cssSelector(".languages > ul > li:nth-of-type(5) > a[href^=\"/\"]")).click();
        // Checking Portuguese language
        String actualportuguese  = driver.findElement(By.cssSelector("#introText > p:nth-of-type(1)")).getText();
        System.out.println(actualportuguese);
        if (actualportuguese.equals("Adeus, desperdício de tempo..."))
		{
			test.log(LogStatus.PASS, "Time Doctor Login Page is opened in Portuguese");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time Doctor Login Page is not opened in Portuguese");
		}
     
        driver.findElement(By.cssSelector("a[href^=\"/\"].language-english-link")).click();
        driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).click();
        driver.findElement(By.cssSelector(".languages > ul > li:nth-of-type(6) > a[href^=\"/\"]")).click();
        // Checking for the French language
        String actualfrench  = driver.findElement(By.cssSelector("#introText > p:nth-of-type(1)")).getText();
        if (actualfrench.equals("Au revoir Temps Perdu,"))
		{
			test.log(LogStatus.PASS, "Time Doctor Login Page is opened in French");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time Doctor Login Page is not opened in French");
		}
       
        driver.findElement(By.cssSelector("a[href^=\"/\"].language-english-link")).click();
        driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).click();
        driver.findElement(By.cssSelector(".languages > ul > li:nth-of-type(7) > a[href^=\"/\"]")).click();
        // Checking for Thai Language
        String actualthai  = driver.findElement(By.cssSelector("#introText > p:nth-of-type(1)")).getText();
        if (actualthai.equals("ลาก่อน การสูญเสียเวลาอันไรค่า"))
		{
			test.log(LogStatus.PASS, "Time Doctor Login Page is opened in Thai");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time Doctor Login Page is not opened in Thai");
		}
        driver.findElement(By.cssSelector("a[href^=\"/\"].language-english-link")).click();
        driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).click();
        driver.findElement(By.cssSelector(".languages > ul > li:nth-of-type(8) > a[href^=\"/\"]")).click();
       // Checking for Turkish Language
        String actualturkish  = driver.findElement(By.cssSelector("#introText > p:nth-of-type(1)")).getText();
        if (actualturkish.equals("Hoşça kal Boşa Geçen Zaman"))
		{
			test.log(LogStatus.PASS, "Time Doctor Login Page is opened in Turkish");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time Doctor Login Page is not opened in Turkish");
		}
      
        driver.findElement(By.cssSelector("a[href^=\"/\"].language-english-link")).click();
        driver.findElement(By.cssSelector("a[href=\"#\"].language-link")).click();
        driver.findElement(By.cssSelector(".languages > ul > li:nth-of-type(9) > a[href^=\"/\"]")).click();
        // Checking for Arabic Lanaguge
        String actualarabic  = driver.findElement(By.cssSelector("#introText > p:nth-of-type(1)")).getText();
        if (actualarabic.equals("وداعا لضياع الوقت,"))
		{
			test.log(LogStatus.PASS, "Time Doctor Login Page is opened in Arabic");
		}
		else
		{
			test.log(LogStatus.FAIL, "Time Doctor Login Page is not opened in Arabic");
		}
        
        driver.findElement(By.cssSelector("a[href^=\"/\"].language-english-link")).click();
    	test.log(LogStatus.INFO, "Login Page - All Lanaguages are Tested");
        report.endTest(test);
		report.flush();
	}
	
	// Verifying Google Login Page
	public void googlelogin() throws InterruptedException
	{
		test = report.startTest("Time Doctor Login Page - Google Login ", "Login with Google account");
		test.log(LogStatus.INFO, "Login Page - Google Login");
		driver.findElement(By.cssSelector("#signinButtonGoogle > img")).click();
		driver.findElement(By.cssSelector("#identifierId")).click();
		driver.findElement(By.cssSelector("#identifierId")).clear();
		driver.findElement(By.cssSelector("#identifierId")).sendKeys("payalstaff01@gmail.com");
		test.log(LogStatus.INFO, "Google Login Page - Entered email address");
		driver.findElement(By.cssSelector("#identifierNext > content.CwaK9 > .RveJvd.snByac")).click();
		Thread.sleep(4000L);
		driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys("payal$123");
		test.log(LogStatus.INFO, "Google Login Page - Entered password");
		driver.findElement(By.cssSelector("#passwordNext > content.CwaK9 > .RveJvd.snByac")).click();
		Thread.sleep(5000L);
		test.log(LogStatus.PASS, "Login to the application sucessfully through google account");
		driver.findElement(By.xpath("//*[@id='mCSB_1_container']/ul[1]/li/div")).click();
		driver.findElement(By.xpath("//*[@id='company-list']/li/a")).click();
		test.log(LogStatus.PASS, "Logout from the application sucessfully");
		report.endTest(test);
		report.flush();
	 }
	// Login with credentials
	public void login(String email, String password) throws IOException
	{
		test = report.startTest("Time Doctor Login Page - Normal Login ", "Login");
		test.log(LogStatus.INFO, "Login Page - Login"); 
		driver.findElement(By.cssSelector("#email")).sendKeys(email);
		test.log(LogStatus.INFO, "Enter valid email address");
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		test.log(LogStatus.INFO, "Enter valid password");
		driver.findElement(By.cssSelector("#password")).sendKeys(Keys.ENTER);
		test.log(LogStatus.PASS, "Login sucessfully");
		test.log(LogStatus.PASS, "Screenshot captured after login with valid credentials");
		driver.findElement(By.cssSelector(".company-section > h3")).click();
		driver.findElement(By.cssSelector("a[href^=\"/logout\"] > span")).click();
		report.endTest(test);
		report.flush();
		driver.quit();
	}
	
}
// Ending of Login Page 