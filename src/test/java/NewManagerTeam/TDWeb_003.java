package NewManagerTeam;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import LoginPage.TDWeb_001;

public class TDWeb_003 extends LoginPage.TDWeb_001{
	public static Properties NewManageUser=null;
	public static Properties NewManageTeam=null;
	ExtentReports report;
	ExtentTest test;
	
	// Landing on New Manager User Page
	public void manageteamlink() throws IOException
	
	{	
		report = new ExtentReports(
				"/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/Reports/newmanageteamreport.html", true);
		
		NewManageUser= new Properties();
		FileInputStream newmuser =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//Source//NewManagerUser.properties");
		NewManageUser.load(newmuser);
		
		NewManageTeam= new Properties();
	    FileInputStream newmteam =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//Source//NewManagerTeam.properties");
		NewManageTeam.load(newmteam);
		
		test = report.startTest("Manage Team Page");
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		tdweb.get(NewManageTeam.getProperty("URLNewMTeam"));
		test.log(LogStatus.INFO, "Open New Manage Team Page");
		String actual = tdweb.getCurrentUrl();
		test.log(LogStatus.INFO, "Actual Title returned :: " + actual);
		String expected = "https://login.timedoctortest.com/apps/manage-teams";
		test.log(LogStatus.INFO, "Expected Title returned:: "+ expected);
		Assert.assertEquals(actual,expected);
		test.log(LogStatus.PASS, "Manage User Page Opened Sucessfully");	
		File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
		Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/manageuserlink.jpg"));
		test.log(LogStatus.INFO, "Screenshot Captured");
		
		report.endTest(test);
		report.flush();
	
	}

	public void manageteampage()
	{
		test = report.startTest("Manage Team - Verifying the buttons and links"); 
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("ManageTeambtn"))).click();
		
		String actual = tdweb.getCurrentUrl();
		test.log(LogStatus.INFO, "Actual Title returned :: " + actual);
		String expected = "https://login.timedoctortest.com/apps/manage-teams";
		test.log(LogStatus.INFO, "Expected Title returned:: "+ expected);
		Assert.assertEquals(actual,expected);
		test.log(LogStatus.PASS, "Manage Team Page Opened Sucessfully");	
		
		
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("Manageuser"))).click();
		test.log(LogStatus.PASS, "Sucessfully clicked on Manage User Page");	
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("ManageTeambtn"))).click();
		test.log(LogStatus.PASS, "Sucessfully clicked on Manage Team Page");
		// Filter expand and collapse
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("filter"))).click();
		
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("filter"))).click();
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("filter"))).click();
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("filter"))).click();
		test.log(LogStatus.PASS, "Sucessfully clicked on Filter Button");
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("Redaddbtn"))).click();
		test.log(LogStatus.PASS, "Sucessfully clicked on Add Button");
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("Cancelbtn"))).click();
		test.log(LogStatus.PASS, "Sucessfully clicked on Cancel Button");
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("plusadd"))).click();
		test.log(LogStatus.PASS, "Sucessfully clicked on Add ( + ) Button");
		tdweb.findElement(By.cssSelector(NewManageTeam.getProperty("Cancelbtn"))).click();
		test.log(LogStatus.PASS, "Sucessfully clicked on Cancel Button");
		tdweb.findElement(By.cssSelector("#search")).click();
		
        assertTrue((tdweb.findElements(By.cssSelector(NewManageTeam.getProperty("Manageuser"))).size() != 0));
        assertTrue((tdweb.findElements(By.cssSelector(NewManageTeam.getProperty("ManageTeambtn"))).size() != 0));
        assertTrue((tdweb.findElements(By.cssSelector(NewManageTeam.getProperty("filter"))).size() != 0));
        assertTrue((tdweb.findElements(By.cssSelector("#search")).size() != 0));
        String TD = "Team Directory" , addteam= "Add Team";
        Assert.assertEquals("Team Directory", TD);
		 if(TD =="Team Directory")
		 {    
			 test.log(LogStatus.PASS, "Team Directory Text Found");
		 }
		 else
		 {
			    test.log(LogStatus.FAIL, "Team Directory Text is not Found");
		 }
		 Assert.assertEquals("Add Team", addteam);
		 if(addteam =="Add Team")
		 {
			    test.log(LogStatus.PASS, "Add Team Text Found");
		 }
		 else
		 {
			    test.log(LogStatus.FAIL, "Add Team Text is not Found");
		 }
       
        assertTrue((tdweb.findElements(By.cssSelector(NewManageTeam.getProperty("Redaddbtn"))).size() != 0));
        assertTrue((tdweb.findElements(By.cssSelector(NewManageTeam.getProperty("plusadd"))).size() != 0));
        
		
        test.log(LogStatus.INFO, "Checking for all buttons and links on the page");
		report.endTest(test);
   		report.flush();
	}
	
	
	public void manageteamcancel()
	{
		test = report.startTest("Manage Team - Verifying Cancel button"); 
		tdweb.findElement(By.cssSelector("a[href=\"/apps/manage-teams\"]")).click();
		String actual = tdweb.getCurrentUrl();
		test.log(LogStatus.INFO, "Actual Title returned :: " + actual);
		String expected = "https://login.timedoctortest.com/apps/manage-teams";
		test.log(LogStatus.INFO, "Expected Title returned:: "+ expected);
		Assert.assertEquals(actual,expected);
		test.log(LogStatus.PASS, "Manage Team Page Opened Sucessfully");	
		tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-btn-add-team")).click();
	    test.log(LogStatus.PASS, "Team Details screen opened sucessfully");	
	    tdweb.findElement(By.cssSelector(".td-button-row > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-medium")).click();
	    tdweb.findElement(By.cssSelector(".td-card--team__add-icon")).click();
	    tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light > .td-icon-cancel.td-button__icon")).click();
	    test.log(LogStatus.INFO, "Clicked on Cancel button");
	    test.log(LogStatus.PASS, "Closed Team Details Screen");
	    report.endTest(test);
   		report.flush();
	}
	
	public void manageteamsave() throws IOException
	{
			test = report.startTest("Manage Team - Save"); 
			tdweb.findElement(By.cssSelector("a[href=\"/apps/manage-teams\"]")).click();
			tdweb.findElement(By.cssSelector(".td-icon.td-icon-plus")).click();
	        test.log(LogStatus.INFO, "( + ) - Add Team Button Clicked");
	        tdweb.findElement(By.cssSelector("input[name=\"name\"]")).click();
	        tdweb.findElement(By.cssSelector("input[name=\"name\"]")).click();
	        tdweb.findElement(By.cssSelector("input[name=\"name\"]")).clear();
	        tdweb.findElement(By.cssSelector("input[name=\"name\"]")).sendKeys("Testing6");
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-smaller")).click();
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-text-primary")).click();
	        tdweb.findElement(By.cssSelector(".td-confirmation-dialog__buttons > .td-button-row > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-medium > .td-icon-cancel.td-button__icon")).click();
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-text-primary")).click();
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.save")).click();
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-smaller")).click();
	        
	       
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-smaller")).click();
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-smaller")).click();
	        tdweb.findElement(By.cssSelector("button[type=\"submit\"]")).click();
	      
	        test.log(LogStatus.INFO, "Clicked on Saved button");
	      
	        assertEquals(tdweb.findElement(By.cssSelector(".td-alert")).getText(), "Team created successfully!");
	        String alert3 = "Team created successfully!";
	        if(alert3 == "Team created successfully!")
			{
				test.log(LogStatus.PASS, "Team is successfully created");
				
			
				 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
					Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/Manageteam1.jpg"));
					test.log(LogStatus.INFO, "Screenshot Captured");
			}
			else
			{
				test.log(LogStatus.FAIL, "Team is not created");
				 test.log(LogStatus.INFO, "Clicked on Add Team - Red Button");
				 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
					Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/Manageteam2.jpg"));
					test.log(LogStatus.INFO, "Screenshot Captured");
			}
	        test.log(LogStatus.INFO, "Clicked on Add Team Button - Red ");
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-btn-add-team")).click();
	        tdweb.findElement(By.cssSelector("input[name=\"name\"]")).click();
	        tdweb.findElement(By.cssSelector("input[name=\"name\"]")).click();
	        tdweb.findElement(By.cssSelector("input[name=\"name\"]")).clear();
	        tdweb.findElement(By.cssSelector("input[name=\"name\"]")).sendKeys("testing7");
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-smaller")).click();
	        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--theme-default")).click();
	        tdweb.findElement(By.cssSelector("button[type=\"submit\"]")).click();
	        test.log(LogStatus.INFO, "Clicked on Saved button");
	        assertEquals(tdweb.findElement(By.cssSelector(".td-alert")).getText(), "Team created successfully!");
	        String alert4 = "Team created successfully!";
	        if(alert4 == "Team created successfully!")
			{
				test.log(LogStatus.PASS, "Team is successfully created");
				
			
				 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
					Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/Manageteam1.jpg"));
					test.log(LogStatus.INFO, "Screenshot Captured");
			}
			else
			{
				test.log(LogStatus.FAIL, "Team is not created");
				 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
					Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/Manageteam2.jpg"));
					test.log(LogStatus.INFO, "Screenshot Captured");
			}

        
        report.endTest(test);
		report.flush();
	}
	
	
	public void manageteamdelete() throws IOException
	{
		test = report.startTest("Manage Team - Delete"); 
		tdweb.findElement(By.cssSelector(".td-panel-body > .td-card.td-card--team.colors:nth-of-type(2) > .td-card__inner > .td-card__content > .td-card__members-count")).click();

		tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light-danger-text")).click();
        assertEquals(tdweb.findElement(By.cssSelector(".td-modal-title")).getText(), "Trying to delete team?");
        test.log(LogStatus.INFO, "Clicking on Delete button");
        String alert4 = "Trying to delete team?!";
        if(alert4 == "Trying to delete team?!")
		{
			test.log(LogStatus.PASS, "Popup Displayed sucessfully");
			
		
			 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/manageteam3.jpg"));
				test.log(LogStatus.INFO, "Screenshot Captured");
		}
		else
		{
			test.log(LogStatus.FAIL, "Popup didn't display");
			 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/Manageteam4.jpg"));
				test.log(LogStatus.INFO, "Screenshot Captured");
		}
        tdweb.findElement(By.cssSelector(".td-modal-footer > .td-button-row > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-medium")).click();
        tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light-danger-text")).click();
        tdweb.findElement(By.cssSelector(".td-modal-footer > .td-button-row > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light-danger-text.td-button--box-default.td-button--kind-default.td-button--size-medium")).click();
     //   assertEquals(driver.findElement(By.cssSelector(".td-alert")).getText(), "Team created successfully!");
     //   test.log(LogStatus.FAIL, "Team is deleted sucessfully");
        test.log(LogStatus.INFO, "Clicking on Delete button");
        String alert5 = "Team created successfully!";
        
        if(alert5 == tdweb.findElement(By.cssSelector(".td-alert")).getText())
   //     if(alert5 == "Team created successfully!")
		{
			
        	test.log(LogStatus.PASS, "Team is deleted sucessfully");
			
		
			 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/manageteam4.jpg"));
				test.log(LogStatus.INFO, "Screenshot Captured");
		}
		else
		{
			test.log(LogStatus.FAIL, "Invalid error message is displayed");
			 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/Manageteam5.jpg"));
				test.log(LogStatus.INFO, "Screenshot Captured");
		}
        report.endTest(test);
		report.flush();

	}
	}

	

