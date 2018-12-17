package TimeUse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import GlobalProperties.GlobalSetup;
import GlobalProperties.GlobalFunctions;
import GlobalProperties.GlobalObjects;
import NavigateToPages.NavigationFunctions;

public class TimeUseFunctions extends GlobalSetup{
	
	public List<String> tuData = new ArrayList<String>();
	private GlobalObjects globalObj = new GlobalObjects(driver);
	private NavigationFunctions navigate = new NavigationFunctions();
	private GlobalFunctions global = new GlobalFunctions();
	private TimeUseObjects tuObj = new TimeUseObjects(driver);
	
	WebElement dateSelection;
	WebElement previousDateBtn;
	
	public TimeUseFunctions(WebElement dateSelection, WebElement previousDateBtn) {
		this.dateSelection = dateSelection;
		this.previousDateBtn = previousDateBtn;
	}
	
	public void GetTimeUseReportForAllUsers() throws InterruptedException, IOException {
		
		// Navigate to Time Use page
		test.log(LogStatus.INFO, "Go to Timesheet page [ /v2/index.php?page=time_use_report ]");
		navigate.GoToTimeUsePage();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("/v2/index.php?page=time_use_report"));

		// Filter All Users
		test.log(LogStatus.INFO, "Filter all active users");
		global.SelectAllUsers();
		Assert.assertTrue(globalObj.userDropdownSelectAllActive().size() > 0);

		// Click Date Selection 
		test.log(LogStatus.INFO, "Click ["+ dateSelection.getText() +"] button");
		dateSelection.click();		
		
		// Click Previous Date Selection 
		test.log(LogStatus.INFO, "Click [Previous " + dateSelection.getText() + "] 3 times");
		previousDateBtn.click();
		
		// Get all total time worked
		test.log(LogStatus.INFO, "Get all total time worked for the past 3 " + dateSelection.getText() + "s");
		for (int i = 1; i <= 3; i++) {

			Thread.sleep(3500);
			this.addWorklogtoArray();
			previousDateBtn.click();
			Thread.sleep(1500);
		}
		report.endTest(test);
	}
	
	public void GetTimeUseReportForSingleUser() throws InterruptedException, IOException {
		
		// Navigate to Time Use page
		test.log(LogStatus.INFO, "Go to Timesheet page [ /v2/index.php?page=time_use_report ]");
		navigate.GoToTimeUsePage();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("/v2/index.php?page=time_use_report"));

		// Filter Specific User
		global.SelectAllUsers();
		global.SelectSingleUser();
		test.log(LogStatus.INFO, "Filter first user: \"" + globalObj.selectedUserOnDropdown().getText() +"\" on user dropdown");
		
		// Click Date Selection 
		test.log(LogStatus.INFO, "Click ["+ dateSelection.getText() +"] button");
		dateSelection.click();
				
		// Click Previous Date Selection 
		test.log(LogStatus.INFO, "Click [Previous " + dateSelection.getText() + "] 3 times");
		previousDateBtn.click();
				
		// Get all total time worked
		test.log(LogStatus.INFO, "Get total time worked for the past 3 " + dateSelection.getText() + "s");

		for (int i = 1; i <= 3; i++) {
			Thread.sleep(2500);
			this.addWorklogtoArray();
			previousDateBtn.click();
			Thread.sleep(1500);
		}
		report.endTest(test);
	}
	
	public void addWorklogtoArray() throws IOException {
		
	// Initialize and declare time use objects
	List<WebElement> tuLogs = tuObj.totalTimeLogged();	
	List<String> notNull = new ArrayList<String>();
	String getDate = globalObj.dateValue().getText();
	String getWeeklyDate = globalObj.weekValue().getText();
	String getMonthDate = globalObj.monthValue().getText();
	
	// Add worklog to array list
		for(WebElement log: tuLogs) {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			try {
				if(!log.getText().equals("0h 0m")) {
					if(log.getText().substring(0,2).equals("0h")) {
							
						tuData.add(log.getText().substring(3));
						notNull.add(log.getText());
							
					} else {
							
						tuData.add(log.getText());
						notNull.add(log.getText());
						
					}
				}
			} catch(Exception e) {}
		}
			
		if(notNull.size() == 0) {
			this.takeScreenshot();
			test.log(LogStatus.INFO, "[" + getDate + getWeeklyDate + getMonthDate + "] " + "Found [" + notNull.size() +"] worklog " + test.addScreenCapture(imagePath()));
		} else {
			this.takeScreenshot();
			test.log(LogStatus.INFO, "[" + getDate + getWeeklyDate + getMonthDate + "] " + "Found [" + notNull.size() +"] worklogs "+ notNull + test.addScreenCapture(imagePath()));
		}	
	}
		
}
