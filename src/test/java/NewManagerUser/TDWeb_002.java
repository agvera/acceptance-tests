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
* change description : Added all the standards and modified the code adding new testcases
* Modified By : Payal Choksey
*/
package NewManagerUser;

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

public class TDWeb_002 extends LoginPage.TDWeb_001{
	public static Properties NewManageUser=null;
	ExtentReports report;
	ExtentTest test;
	
	// Landing on New Manager User Page
	public void manageUserLink() throws IOException
	
	{	
		report = new ExtentReports("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/Reports/newmanageruserreport.html", true);
		NewManageUser= new Properties();
		FileInputStream newmuser =new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//Source//NewManagerUser.properties");
		NewManageUser.load(newmuser);
		test = report.startTest("Manage User Page");
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		tdweb.get(NewManageUser.getProperty("URLNewMUser"));
		test.log(LogStatus.INFO, "Open New Manage User Page");
		String actualResult = tdweb.getCurrentUrl();
		test.log(LogStatus.INFO, "Actual Title returned :: " + actualResult);
		String expectedResult = "https://login.timedoctortest.com/apps/manage-users";
		test.log(LogStatus.INFO, "Expected Title returned:: "+ expectedResult);
		Assert.assertEquals(actualResult,expectedResult);
		test.log(LogStatus.PASS, "Manage User Page Opened Sucessfully");	
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("NewMUserbutton"))).click();
		File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
		Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/manageuserlink.jpg"));
		test.log(LogStatus.INFO, "Screenshot Captured");
		report.endTest(test);
		report.flush();
	}
	
	
	// Checking the Invitation Button
	public void invitationButton() throws IOException
	{
	test = report.startTest("Invitation");
	tdweb.findElement(By.cssSelector(NewManageUser.getProperty("invite"))).click();
	assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("invitation"))).getText(), "Invitations");
    test.log(LogStatus.PASS, "Invitation Page Opened");
    File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
	Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/invitationpage.jpg"));
	tdweb.navigate().back();
    assertTrue((tdweb.findElements(By.cssSelector(NewManageUser.getProperty("manageuserbutton"))).size() != 0));
    report.endTest(test);
    report.flush();
	
	}
	// To check all the buttons and links on the page
	public void verifyButtonsNLinks() throws IOException
	{
		test = report.startTest("Verifying buttons and links"); 
		assertTrue((tdweb.findElements(By.cssSelector(NewManageUser.getProperty("manageuserbutton"))).size() != 0));
		String smusers = "Manage Users", mteams = "Manage Teams", invite = "Invite Users" , filters = "Filters";
		Assert.assertEquals("Manage Users", smusers);
		if(smusers =="Manage Users")
			{
			test.log(LogStatus.PASS, "Manage User Button Found");
			}
		else
		{
			 test.log(LogStatus.FAIL, "No Manage User Button Found");
		}
		 Assert.assertEquals("Manage Teams", mteams);
		 if(mteams =="Manage Teams")
		 {
			 test.log(LogStatus.PASS, "Manage Team Button Found");
		 }
		 else
		 {
			 test.log(LogStatus.FAIL, "No Manage Team Button Found");
		 }
		 Assert.assertEquals("Invite Users", invite);
		 if(invite =="Invite Users")
		 {
			test.log(LogStatus.PASS, "Invite User Button Found");
		 }
		 else
		 {
			 test.log(LogStatus.FAIL, "No Invite User Button Found");
		 }
		 Assert.assertEquals("Filters", filters);
		 if(filters =="Filters")
		 {
			    test.log(LogStatus.PASS, "Filters Button Found");
		 }
		 else
		 {
			    test.log(LogStatus.FAIL, "No Filters Button Found");
		 }
		
		 assertTrue((tdweb.findElements(By.cssSelector("button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-text-primary")).size() != 0));
         assertTrue((tdweb.findElements(By.cssSelector("#search")).size() != 0));
         assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("UserLevel"))).getText(), "User Level");
         assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Teams"))).getText(), "Teams");
         assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("ManualandMobileTime"))).getText(), "Manual and Mobile Time");
         assertTrue((tdweb.findElements(By.cssSelector(NewManageUser.getProperty("button"))).size() != 0));
         assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Screenshots"))).getText(), "Screenshots");
         assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Status"))).getText(), "Status");
         assertTrue((tdweb.findElements(By.cssSelector(".glyphicon")).size() != 0));
         File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
     	 Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/Links.jpg"));
     	 test.log(LogStatus.INFO, "Verfiying all Links and buttons");
     	 test.log(LogStatus.INFO, "Screen shot Captured");
		 test.log(LogStatus.PASS, "All Links and Buttons ");
		 report.endTest(test);
		 report.flush();
		
	}

	// Checking for New User Default Setting
	
	public void newUserDefualtSetting() throws InterruptedException, IOException
	{
		test = report.startTest("New User Default Setting"); 
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Newdefaultsettinglink"))).click();
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Cancelbutton"))).click();
		Thread.sleep(5000L);
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Newdefaultsettinglink"))).click();
		Thread.sleep(5000L);
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Closebutton"))).click();
		Thread.sleep(5000L);
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Newdefaultsettinglink"))).click();
		Thread.sleep(5000L);
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Toggle1"))).click();
	    assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("EnableScreenshots"))).getText(), "Enable Screenshots");
	    tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Toggle2"))).click();
	    assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("EnableWebcamShots"))).getText(), "Enable Webcam Shots");
	    tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Droplist"))).click();
	    File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
		Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/defaultsetting1.jpg"));
	    // Display the list of the drop list
	    Select oSelect = new Select(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Droplist"))));
	    	List <WebElement> elementCount = oSelect.getOptions();
	    	int iSize = elementCount.size();

	    	for(int i =0; i<iSize ; i++){
	    		String sValue = elementCount.get(i).getText();
	    		test.log(LogStatus.INFO, "Show “Are you still working?” Pop-Up values are :: "  +sValue);
	    		
	    		}
	    	tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Droplist"))).click();
	      assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Working"))).getText(), "Show “Are you still working?” Pop-Up");
	      tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Toggle4"))).click();
	      assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("ManualTime"))).getText(), "Enable Manual Time");
	      tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Toggle5"))).click();
	      assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("PoorTime"))).getText(), "Show “Poor Time Use” Pop-Up");
	      assertTrue((tdweb.findElements(By.cssSelector(NewManageUser.getProperty("Droplistselect"))).size() != 0));
	      assertEquals(tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Closebutton"))).getText(), "");
	      tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Toggle1"))).click();
	      tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Toggle2"))).click();
	      tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Toggle4"))).click();
	      tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Toggle5"))).click();
	      tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Save"))).click();
	      // checking for the validation message
	      		String defaultsettings =  tdweb.findElement(By.cssSelector(".alert")).getText();
	        		
	        		if (defaultsettings.equals("Settings Updated Successfully!"))
	        		{
	        			test.log(LogStatus.PASS, "Default Setting Saved Successfully");
	        			 File scrFile1 = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
	        				Files.copy(scrFile1, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/defaultsetting2.jpg"));
	        		}
	        		else
	        		{
	        			test.log(LogStatus.FAIL, "Default Setting  didn't save Successfully");
	        		}
	 
	        		report.endTest(test);
	        		report.flush();

	}
	// checking the usersetting page. At the moment due to issue the code is not completed https://globalworkforce.jira.com/browse/USERS-52
	public void userSettingPage() throws IOException
	{
		test = report.startTest("User Setting Page"); 
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("Name"))).click();
		tdweb.findElement(By.cssSelector("#last_name")).click();
		tdweb.findElement(By.cssSelector("#last_name")).click();
		tdweb.findElement(By.cssSelector("#last_name")).clear();
		tdweb.findElement(By.cssSelector("#last_name")).sendKeys("Choksey");
		tdweb.findElement(By.cssSelector(NewManageUser.getProperty("UserdetailsSave"))).click();
		assertEquals(tdweb.findElement(By.cssSelector(".alert")).getText(), "Bad Request");	
		String alert = "Bad Request";
		if(alert == "Bad Request")
		{
			test.log(LogStatus.FAIL, "Unable to save the changes");
			 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/usersetting1.jpg"));
				test.log(LogStatus.INFO, "Screenshot Captured");
		}
		else
		{
			test.log(LogStatus.PASS, "Changes are saved!");
			 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/usersetting2.jpg"));
				test.log(LogStatus.INFO, "Screenshot Captured");
				report.endTest(test);
        		report.flush();
		}
		

	}
	//Checking for the filterscreen
	public void filtersScreen() throws IOException
	{
		test = report.startTest("Filters");  
		test.log(LogStatus.INFO, "Verifying the filters functionality");
		
		tdweb.findElement(By.cssSelector(".td-filter-panel__button > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-medium")).click();
		tdweb.findElement(By.cssSelector(".td-filter-panel__reset > div:nth-of-type(1) > .td-checkbox.td-checkbox--blue > label.td-checkbox__label")).click();
		tdweb.findElement(By.cssSelector(".td-filter-panel__reset > div:nth-of-type(1) > .td-checkbox.td-checkbox--blue > label.td-checkbox__label")).click();
		tdweb.findElement(By.cssSelector(".td-filter-panel__reset > div:nth-of-type(2) > a[href=\"#\"]")).click();
	     assertEquals(tdweb.findElement(By.cssSelector(".td-checkbox.minus > label.td-checkbox__label")).getText(), "2 Filters");
	     String alert = "2 Filters";
			if(alert == "2 Filters")
			{
				test.log(LogStatus.PASS, "Reset works correctly");
				 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
					Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/filter1.jpg"));
					test.log(LogStatus.INFO, "Screenshot Captured");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reset didn't work correctly");
				 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
					Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/filter2.jpg"));
					test.log(LogStatus.INFO, "Screenshot Captured");
			}
			tdweb.findElement(By.cssSelector(".td-checkbox.minus > label.td-checkbox__label")).click();
			tdweb.findElement(By.cssSelector(".td-filter-panel__reset > div:nth-of-type(1) > .td-checkbox.td-checkbox--blue > label.td-checkbox__label")).click();
			tdweb.findElement(By.cssSelector(".panel-body > div:nth-of-type(1) > div > h4.td-title--small-uppercase.td-util__ellipsis")).click();
			tdweb.findElement(By.cssSelector(".panel-body > div:nth-of-type(2) > div > h4.td-title--small-uppercase.td-util__ellipsis")).click();
			tdweb.findElement(By.cssSelector(".panel-body > div:nth-of-type(3) > div > h4.td-title--small-uppercase.td-util__ellipsis")).click();
			tdweb.findElement(By.cssSelector(".panel-body > div:nth-of-type(4) > div > h4.td-title--small-uppercase.td-util__ellipsis")).click();
			tdweb.findElement(By.cssSelector(".panel-body > div:nth-of-type(5) > div > h4.td-title--small-uppercase.td-util__ellipsis")).click();
	        assertTrue((tdweb.findElements(By.cssSelector(".panel-body > div:nth-of-type(1) > div > h4.td-title--small-uppercase.td-util__ellipsis")).size() != 0));
	        assertTrue((tdweb.findElements(By.cssSelector(".panel-body > div:nth-of-type(2) > div > h4.td-title--small-uppercase.td-util__ellipsis")).size() != 0));
	        assertTrue((tdweb.findElements(By.cssSelector(".panel-body > div:nth-of-type(3) > div > h4.td-title--small-uppercase.td-util__ellipsis")).size() != 0));
	        assertTrue((tdweb.findElements(By.cssSelector(".panel-body > div:nth-of-type(4) > div > h4.td-title--small-uppercase.td-util__ellipsis")).size() != 0));
	        assertTrue((tdweb.findElements(By.cssSelector(".panel-body > div:nth-of-type(5) > div > h4.td-title--small-uppercase.td-util__ellipsis")).size() != 0));
	     test.log(LogStatus.PASS, "All fiters are working fine");
	     report.endTest(test);
 		report.flush();

	}
	public void mainScreen() throws IOException
	{
		test = report.startTest("Main Screen"); 
		tdweb.findElement(By.cssSelector(".full_name > .td-checkbox.td-checkbox--blue > label.td-checkbox__label")).click();
		tdweb.findElement(By.cssSelector(".full_name > .td-checkbox.td-checkbox--blue > label.td-checkbox__label")).click();
		tdweb.findElement(By.cssSelector(".full_name > .td-checkbox.td-checkbox--blue > label.td-checkbox__label")).click();
		tdweb.findElement(By.cssSelector(".level > .bulk-actions > .td-status__dropdown > select")).click();
		tdweb.findElement(By.cssSelector(".level > .bulk-actions > .td-status__dropdown > select")).click();
		tdweb.findElement(By.cssSelector(".level > .bulk-actions > .td-status__dropdown > select")).sendKeys("admin");
		tdweb.findElement(By.cssSelector(".td-modal-footer > .td-button-row > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-medium")).click();
		tdweb.findElement(By.cssSelector(".td-status__dropdown.td-status__dropdown--admin > select")).click();
		tdweb.findElement(By.cssSelector(".td-status__dropdown.td-status__dropdown--admin > select")).click();
		tdweb.findElement(By.cssSelector(".td-status__dropdown.td-status__dropdown--admin > select")).sendKeys("employee");
		tdweb.findElement(By.cssSelector(".td-modal-footer > .td-button-row > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-medium")).click();
		tdweb.findElement(By.cssSelector(".td-status__dropdown.td-status__dropdown--employee > select")).click();
		tdweb.findElement(By.cssSelector(".td-status__dropdown.td-status__dropdown--employee > select")).click();
        Select oSelect1 = new Select(tdweb.findElement(By.cssSelector(".td-status__dropdown.td-status__dropdown--employee > select")));
    	List <WebElement> elementCount = oSelect1.getOptions();
    	int iSize = elementCount.size();

    	for(int i =0; i<iSize ; i++){
    		String sValue1 = elementCount.get(i).getText();
    		System.out.println("User Level Drop menu :"+sValue1);
    		test.log(LogStatus.INFO, "User Level Drop menu :: "  +sValue1);
    		}
    	tdweb.findElement(By.cssSelector(".manual_time > .bulk-actions > .td-button-group > button[type=\"button\"].td-button.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-smaller")).click();
    	tdweb.findElement(By.cssSelector(".td-modal-footer > .td-button-row > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-medium > .td-icon-cancel.td-button__icon")).click();
    	tdweb.findElement(By.cssSelector(".td-full-height-table > div:nth-of-type(3) > div > div:nth-of-type(1) > div:nth-of-type(2) > div > div:nth-of-type(9) > div > .bulk-actions > .td-button-group > button[type=\"button\"].td-button.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-smaller")).click();
    	tdweb.findElement(By.cssSelector(".td-modal-footer > .td-button-row > button[type=\"button\"].td-button.td-button--with-icon.td-button--icon-position-left.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-medium")).click();
    	tdweb.findElement(By.cssSelector(".manual_time > .bulk-actions > .td-button-group > button[type=\"button\"].td-button.td-button--theme-light.td-button--box-default.td-button--kind-default.td-button--size-smaller")).click();
    	tdweb.findElement(By.cssSelector("button[type=\"button\"].td-button.save")).click();
       
        assertEquals(tdweb.findElement(By.cssSelector(".alert")).getText(), "Bad Request");	
        String alert1 = "Bad Request";
		if(alert1 == "Bad Request")
		{
			test.log(LogStatus.FAIL, "Error message - Bad Request - is displayed");
			
		
			 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/mainscreen1.jpg"));
				test.log(LogStatus.INFO, "Screenshot Captured");
		}
		else
		{
			test.log(LogStatus.PASS, "Sucessfully Saved");
			 File scrFile = ((TakesScreenshot)tdweb).getScreenshotAs(OutputType.FILE); // gets the screen shot in bytes 0101010
				Files.copy(scrFile, new File("/Users/payalchoksey/Desktop/Time Doctor/TDAutomationScript_4/src/test/resources/Screenshot/mainscreen2.jpg"));
				test.log(LogStatus.INFO, "Screenshot Captured");
		}
		report.endTest(test);
		report.flush();
        
    		}
	
	public void manageTeamCancel()
	{
		test = report.startTest("Manage Team - Cancel"); 
		tdweb.findElement(By.cssSelector("a[href=\"/apps/manage-teams\"]")).click();
			String actual = tdweb.getCurrentUrl();
			test.log(LogStatus.INFO, "Actual Title returned :: " + actual);
			String expected = "https://login.timedoctorstaging.com/apps/manage-teams";
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
	
	
	}

	

