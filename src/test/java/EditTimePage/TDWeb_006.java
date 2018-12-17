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
package EditTimePage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;




import com.google.common.io.Files;

import LoginPage.TDWeb_001;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;





public class TDWeb_006 {
	public static Properties Config=null;
	public static Properties LoginOR=null;
	public static WebDriver tdweb = null;
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
				"/Users/payalchoksey/git/payalchoksey/TDAutomationScript_4/src/test/resources/Reports/EditTimePage/EditTimePage1.html", true);
		if(Config.getProperty("BrowserName").equals("Chrome"))
		{
			test = report.startTest("TD Login Page - In Chrome", "Opening the Application");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//Source//chromedriver");
			tdweb = new ChromeDriver();
			tdweb.get(Config.getProperty("LoginURL"));
			tdweb.manage().window().fullscreen();
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
	
	// Login with credentials
	public void login(String email, String password) throws IOException
	{
		test = report.startTest("Time Doctor Login Page - Normal Login ", "Login");
		test.log(LogStatus.INFO, "Login Page - Login"); 
		tdweb.findElement(By.cssSelector("#email")).sendKeys(email);
		test.log(LogStatus.INFO, "Enter valid email address");
		tdweb.findElement(By.cssSelector("#password")).sendKeys(password);
		test.log(LogStatus.INFO, "Enter valid password");
		tdweb.findElement(By.cssSelector("#password")).sendKeys(Keys.ENTER);
		test.log(LogStatus.PASS, "Login sucessfully");
		test.log(LogStatus.PASS, "Screenshot captured after login with valid credentials");
		report.endTest(test);
		report.flush();
	}
	
	// Steps.
			// 1. Navigate to Edit Time Page from left navigation bar.
	public void editnavigation() throws IOException
	{
		test = report.startTest("Navigating to Edit Time Page from Left Navigation bar ", "Edit Time Page");
		test.log(LogStatus.INFO, "Navigating to Edit Time Page from Left Navigation bar"); 
		tdweb.findElement(By.xpath("//*[@id='mCSB_1_container']/ul[2]/li[2]/a")).click();
		currentURL=tdweb.getCurrentUrl();
		System.out.println(currentURL);
		if(currentURL.equals(Config.getProperty("EditURL")))
			{
			test.log(LogStatus.FAIL, "Edit Time Page Error");	
			
				test.log(LogStatus.INFO, "Screenshot captured for Edit Time Page");

			}
		else
			{
			test.log(LogStatus.PASS, "Edit Time Page has Sucessfully Opened");	
				
			}
		report.endTest(test);
		report.flush();
	}
	
	// Verify current user selected in drop down is Login user.
	public void droplistuser()
	{
		test = report.startTest("Verification of current user selected in drop down with Login User", "Current User selected with Login User");
		test.log(LogStatus.INFO, "Current user selected in drop down with Login User"); 
		assertTrue((tdweb.findElements(By.cssSelector("#users-select-container > .btn-group > button[type=\"button\"].dropdown-toggle.btn")).size() != 0));
		tdweb.findElement(By.cssSelector("#users-select-container > .btn-group > button[type=\"button\"].dropdown-toggle.btn > b.caret")).click();
        assertTrue((tdweb.findElements(By.cssSelector("label.radio")).size() != 0));
        tdweb.findElement(By.cssSelector("#users-select-container > .btn-group > button[type=\"button\"].dropdown-toggle.btn > b.caret")).click();
        test.log(LogStatus.PASS, "Current user selected in drop down with Login User"); 
        report.endTest(test);
		report.flush();
     
    }
// Verifying the Cancel button on Add Time
	public void AddtimeCancel() throws InterruptedException
	{
		test = report.startTest("Verification of Cancel button on Add Time popup", "Add Time - Cancel Button");
		tdweb.findElement(By.cssSelector("button.td-button")).click();
		Thread.sleep(2000L);
		test.log(LogStatus.INFO, "Clicking on Cancel button"); 
		tdweb.findElement(By.cssSelector("a.td-button.td-button-white.td-button-has-icon-cancel > span")).click();		
		test.log(LogStatus.PASS, "Addtime has been cancelled sucessfully"); 
		report.endTest(test);
		report.flush();
		
	}
	
// Verifying the Save button with no data
	public void AddtimeinvalidSave() throws InterruptedException
	{
		test = report.startTest("Verification of Save button on Add Time popup", "Add Time - Save Button without entering any data");
		tdweb.findElement(By.cssSelector("button.td-button")).click();
		test.log(LogStatus.INFO, "Clicking on Save button"); 
		Thread.sleep(2000L);
		tdweb.findElement(By.cssSelector(".row > div:nth-of-type(1) > a.td-button.pull-right.td-button-has-icon-save")).click();
		String time = tdweb.findElement(By.cssSelector(".td-alert")).getText();
		System.out.println(time);
			if (time.equals("Please enter a task name."))
			{
				System.out.println("Correct Validation message is displayed");
			}
      
			else
			{
				System.out.println("Incorrect Validation message is displayed");
			}
		
		
			tdweb.findElement(By.cssSelector("a.td-button.td-button-white.td-button-has-icon-cancel > span")).click();
			report.endTest(test);
			report.flush();

	}
	
//Entering wrong project name
	public void invalidprojectname() throws InterruptedException
	{
		test = report.startTest("Entering wrong Project Name", "Add Time - Wrong project name validation");
		tdweb.findElement(By.cssSelector("button.td-button")).click();
		test.log(LogStatus.INFO, "Verfying the wrong project name"); 
		tdweb.findElement(By.xpath("//*[@id='content-container']/div/div[1]/div[2]/div[2]/button"));
		Thread.sleep(2000L);

		tdweb.findElement(By.cssSelector("#td-project-dropdown-input")).sendKeys("j");
		tdweb.findElement(By.cssSelector(".row > div:nth-of-type(1) > a.td-button.pull-right.td-button-has-icon-save")).click();
	    assertEquals(tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(1) > .td-alert")).getText(), "You can only add new projects in Projects and Permanent tasks, choose one from the list or leave the field empty.");
	    test.log(LogStatus.PASS, "Correct validation message is displayed after entering wrong project name"); 
	    tdweb.findElement(By.cssSelector("a.td-button.td-button-white.td-button-has-icon-cancel > span")).click();		
		report.endTest(test);
		report.flush();
	}
	// Entering all details for Manual Edit Time Correctly
	public void manualedittime() throws InterruptedException
	{
		test = report.startTest("Manual Edit time", "Add Time - Entering Valid Data");
		tdweb.findElement(By.cssSelector("button.td-button")).click();
		Thread.sleep(5000L);
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).click();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).click();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).clear();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).sendKeys("new test");
		 test.log(LogStatus.PASS, "Task Name has been entered correctly"); 
		tdweb.findElement(By.cssSelector(".form-group > div:nth-of-type(1) > input[type=\"text\"].form-control.ui-timepicker-input")).click();
		tdweb.findElement(By.cssSelector(".ui-timepicker-list > .ui-timepicker-am:nth-of-type(2)")).click();
		tdweb.findElement(By.cssSelector(".form-group > div:nth-of-type(3) > input[type=\"text\"].form-control.ui-timepicker-input")).click();
		tdweb.findElement(By.cssSelector(".form-group > .ui-timepicker-wrapper:nth-of-type(7) > .ui-timepicker-list > .ui-timepicker-am:nth-of-type(4)")).click();
		tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(5) > textarea.form-control")).click();
		tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(5) > textarea.form-control")).click();
		tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(5) > textarea.form-control")).clear();
		tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(5) > textarea.form-control")).sendKeys("reason");
		tdweb.findElement(By.cssSelector(".row > div:nth-of-type(1) > a.td-button.pull-right.td-button-has-icon-save")).click();
		test.log(LogStatus.PASS, "Sucessfully entered the manual time");
		report.endTest(test);
		report.flush();

	}
	
	// Editing the recorded time from Add Time
	public void manualtimedelete()
	{
		test = report.startTest("Manual Edit time - Delete", "Deleting Manual Time");
		tdweb.findElement(By.cssSelector("button.td-button")).click();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).click();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).click();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).clear();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).sendKeys("new test");
		tdweb.findElement(By.cssSelector(".form-group > div:nth-of-type(1) > input[type=\"text\"].form-control.ui-timepicker-input")).click();
		tdweb.findElement(By.cssSelector(".ui-timepicker-list > .ui-timepicker-am:nth-of-type(2)")).click();
		tdweb.findElement(By.cssSelector(".form-group > div:nth-of-type(3) > input[type=\"text\"].form-control.ui-timepicker-input")).click();
		tdweb.findElement(By.cssSelector(".form-group > .ui-timepicker-wrapper:nth-of-type(7) > .ui-timepicker-list > .ui-timepicker-am:nth-of-type(4)")).click();
		tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(5) > textarea.form-control")).click();
		tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(5) > textarea.form-control")).click();
		tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(5) > textarea.form-control")).clear();
		tdweb.findElement(By.cssSelector(".task-details-scroll > .form-group:nth-of-type(5) > textarea.form-control")).sendKeys("reason");
		tdweb.findElement(By.cssSelector(".row > div:nth-of-type(1) > a.td-button.pull-right.td-button-has-icon-save")).click();
		test.log(LogStatus.PASS, "Sucessfully deleted the manual time");
		report.endTest(test);
		report.flush();

	}
	
	// Verifying the date button
	public void datebutton()
	{
		test = report.startTest("Date", "Selecting the date");
		tdweb.findElement(By.cssSelector(".btn-group.date-range-item.pull-right.dcal > button[type=\"button\"].btn.tdBtn.tdBtnPrev.btn-default > .btnPrevious")).click();
		tdweb.findElement(By.cssSelector(".btn-group.date-range-item.pull-right.dcal > button[type=\"button\"].btn.tdBtn.tdBtnNext.btn-default")).click();
		tdweb.findElement(By.cssSelector(".btn-group.date-range-item.pull-right.dcal > button[type=\"button\"].btn.tdBtn.tdBtnPrev.btn-default")).click();
		tdweb.findElement(By.cssSelector(".btn-group.date-range-item.pull-right.dcal > button[type=\"button\"].btn.tdBtn.tdBtnNext.btn-default")).click();
		tdweb.findElement(By.cssSelector(".btn-group.date-range-item.pull-right.dcal > button[type=\"button\"].btn.tdBtn.tdBtnNext.btn-default")).click();
		tdweb.findElement(By.cssSelector(".btn-group.date-range-item.pull-right.dcal > button[type=\"button\"].btn.tdBtn.tdBtnPrev.btn-default > .btnPrevious")).click();
		test.log(LogStatus.PASS, "Sucessfully selected the date");
		report.endTest(test);
		report.flush();
	}
	//Verifying  List view
	public void listview() throws InterruptedException
	{
		test = report.startTest("List View", "Clicking on List View");
		Thread.sleep(2000L);
		tdweb.findElement(By.cssSelector(".td-view-switch > a.td-view-button:nth-of-type(1) > span > b")).click();
		tdweb.findElement(By.cssSelector("button.td-button")).click();
		Thread.sleep(2000L);
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).click();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).click();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).clear();
		tdweb.findElement(By.cssSelector(".form-group.td-type-ahead > input[type=\"text\"].form-control")).sendKeys("Project");
		tdweb.findElement(By.cssSelector(".row > div:nth-of-type(1) > a.td-button.pull-right.td-button-has-icon-save")).click();
		tdweb.findElement(By.cssSelector("#td-bar-0 > .td-bar-inner")).click();
		Thread.sleep(2000L);
		tdweb.findElement(By.cssSelector("a.td-button.td-button-white.td-button-has-icon-delete > span")).click();
		tdweb.findElement(By.cssSelector(".modal.fade.delete-modal > .modal-dialog > .modal-content > .modal-footer > a.td-button.td-button-white.td-button-has-icon-delete.pull-right")).click();
		tdweb.findElement(By.cssSelector(".td-view-switch > a.td-view-button:nth-of-type(2) > span")).click();

		test.log(LogStatus.PASS, "Sucessfully selecting the list view");
		report.endTest(test);
		report.flush();
	}
	
   public void oldtimepagetonewtimepage() throws InterruptedException
   {
	   test = report.startTest("Old Time Page", "Selecting the old time page > New Time Page");
	   tdweb.findElement(By.cssSelector("a.switch-edit-time-link")).click();
	   Thread.sleep(2000L);
	   tdweb.findElement(By.cssSelector(".form-group.feedback-modal-actions > a.td-button:nth-of-type(1)")).click();
	   tdweb.findElement(By.cssSelector("a.switch-edit-time-link")).click();
	   tdweb.findElement(By.cssSelector(".form-group > a.td-button.td-button-white")).click();
	   tdweb.findElement(By.cssSelector("a.switch-edit-time-link")).click();
	   tdweb.findElement(By.cssSelector("a.td-button.td-button-white")).click();
	   tdweb.findElement(By.cssSelector("a.switch-edit-time-link")).click();
	   tdweb.findElement(By.cssSelector(".form-group.feedback-modal-actions > a.td-button:nth-of-type(1)")).click();
	   test.log(LogStatus.PASS, "Sucessfully directed to old page to new page");
	   report.endTest(test);
	   report.flush();
	   
	  
      
   }
	
	
	
}