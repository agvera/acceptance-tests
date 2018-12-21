package Timesheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import GlobalProperties.GlobalSetup;
import GlobalProperties.GlobalFunctions;
import GlobalProperties.GlobalObjects;
import NavigateToPages.NavigationFunctions;

public class TimesheetFunctions extends GlobalSetup{
	
	public List<String> tsData = new ArrayList<String>();
	private GlobalObjects globalObj = new GlobalObjects(driver);
	private GlobalFunctions global = new GlobalFunctions();
	private NavigationFunctions navigate = new NavigationFunctions();
	private TimesheetObjects tsObj = new TimesheetObjects(driver);
	
	WebElement dateSelection;
	WebElement previousDateBtn;
	
	public TimesheetFunctions(WebElement dateSelection, WebElement previousDateBtn) {
		this.dateSelection = dateSelection;
		this.previousDateBtn = previousDateBtn;
	}
	
	public void GetTimesheetReportForAllUsers() throws IOException, InterruptedException {
		
		// Navigate to Timesheet page
		test.log(LogStatus.INFO, "Go to Timesheet page [ /v2/index.php?page=timesheet ]");
		navigate.GoToTimesheetPage();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("/v2/index.php?page=timesheet"));
		
		// Select company timezone
		global.SelectCompanyTimezone();
		
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
			
			Thread.sleep(5000);
			this.waitForTimesheetData();
			this.addWorklogtoArray();
			previousDateBtn.click();
			
		}
		report.endTest(test);
	}
	
	
	public void GetTimesheetReportForSingleUser() throws InterruptedException, IOException {
		
		// Navigate to Timesheet page
		test.log(LogStatus.INFO, "Go to Timesheet page [ /v2/index.php?page=timesheet ]");
		navigate.GoToTimesheetPage();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("/v2/index.php?page=timesheet"));
		
		// Select company timezone
		global.SelectCompanyTimezone();
		
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
			
			Thread.sleep(5000);
			this.waitForTimesheetData();
			
			String getDate = globalObj.dateValue().getText();
			String getWeeklyDate = globalObj.weekValue().getText();
			String getMonthDate = globalObj.monthValue().getText();
			
			List<String> notNull = new ArrayList<String>();
			WebElement log = tsObj.totaltimeLoggedSingleUser();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// Add worklog to array
			try {
				if(!log.getText().equals("0h 0m")) {
					if(log.getText().substring(0,2).equals("0h")) {
						
						tsData.add(log.getText().substring(3));
						notNull.add(log.getText());
						
					} else {
						
						tsData.add(log.getText());
						notNull.add(log.getText());
						
					}
				}
			} catch(Exception e) {}
			
			if(notNull.size() == 0) {
				this.takeScreenshot();
				test.log(LogStatus.INFO, "[" + getDate + getWeeklyDate + getMonthDate + "] " + "Found [" + notNull.size() +"] worklog " + test.addScreenCapture(imagePath()));
			} else {
				this.takeScreenshot();
				test.log(LogStatus.INFO, "[" + getDate + getWeeklyDate + getMonthDate + "] " + "Found [" + notNull.size() +"] worklogs "+ notNull + test.addScreenCapture(imagePath()));
			}
			
			previousDateBtn.click();
		}
		report.endTest(test);
	}			
		
	public void addWorklogtoArray() throws IOException {
		
	// Initialize and declare timesheet objects
		List<WebElement> tsLogs = tsObj.totalTimeLogged();
		List<String> notNull = new ArrayList<String>();
		String getDate = globalObj.dateValue().getText();
		String getWeeklyDate = globalObj.weekValue().getText();
		String getMonthDate = globalObj.monthValue().getText();
			
	// Add worklog to array list
		for(WebElement log: tsLogs) {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
			try {
					
				if(!log.getText().equals("0h 0m")) {
					if(log.getText().substring(0,2).equals("0h")) {
							
						tsData.add(log.getText().substring(3));
						notNull.add(log.getText());
							
					} else {
							
						tsData.add(log.getText());
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
		
	public Boolean waitForTimesheetData() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(globalObj.loadingAnimation().size() > 0) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(globalObj.loadingAnimation().toString())));
			wait.until(ExpectedConditions.invisibilityOfAllElements(globalObj.loadingAnimation()));
			return true;
		}
		return true;
	}
}