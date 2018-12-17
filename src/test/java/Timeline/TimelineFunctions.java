package Timeline;

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

public class TimelineFunctions extends GlobalSetup{
	
	private GlobalFunctions global = new GlobalFunctions();
	private GlobalObjects globalObj = new GlobalObjects(driver);	
	private NavigationFunctions navigate = new NavigationFunctions();
	public List<String> tlData = new ArrayList<String>();
	
	public void GetTimelineReportsDailyLogsForAllUsers() throws InterruptedException, IOException {
	
		// Navigate to Timeline page
		test.log(LogStatus.INFO, "Go to Timeline page [ /v2/index.php?page=timeline_report ]");
		navigate.GoToTimelinePage();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("/v2/index.php?page=timeline_report"));
	
		// Filter All Users
		test.log(LogStatus.INFO, "Filter all active users");
		global.SelectAllUsers();
		Assert.assertTrue(globalObj.userDropdownSelectAllActive().size() > 0);
	
		// Click Previous Date Selection 
		test.log(LogStatus.INFO, "Click [Previous Day] 3 times");
		test.log(LogStatus.INFO, "Get total time worked for the past 3 Days");
		globalObj.previousDayBtn().click();
		
			for (int i = 1; i <= 3; i++) {
				this.waitForTimesheetData();
				this.addWorklogtoArray();
				globalObj.previousDayBtn().click();
			}
			report.endTest(test);
		}
	
	public void GetTimelineReportForSingleUser() throws InterruptedException, IOException {
		
		// Navigate to Timeline page
		test.log(LogStatus.INFO, "Go to Timeline page [ /v2/index.php?page=timeline_report ]");
		navigate.GoToTimelinePage();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("/v2/index.php?page=timeline_report"));
		
		// Filter specific user
		global.SelectAllUsers();
		global.SelectSingleUser();
		test.log(LogStatus.INFO, "Filter first user: \"" + globalObj.selectedUserOnDropdown().getText() +"\" on user dropdown");
		
		// Click Previous Date Selection 
		test.log(LogStatus.INFO, "Click [Previous Day] 3 times");
		test.log(LogStatus.INFO, "Get total time worked for the past 3 Days");
		globalObj.previousDayBtn().click();
		
			for (int i = 1; i <= 3; i++) {
				this.waitForTimesheetData();
				this.addWorklogtoArray();
				globalObj.previousDayBtn().click();
			}
			report.endTest(test);
		}
	
	public void addWorklogtoArray() throws IOException {
		
	// Initialize and declare time use objects
	TimelineReportObjects tlObj = new TimelineReportObjects(driver);
	List<WebElement> tlLogs = tlObj.totalTimeLogged();	
	List<String> notNull = new ArrayList<String>();
	String getDate = globalObj.dateValue().getText();
		
	// Add worklog to array list
		for(WebElement log: tlLogs) {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			try {
				if(!log.getText().equals("0h 0m")) {
					if(log.getText().substring(0,2).equals("0h")) {
								
						tlData.add(log.getText().substring(3));
						notNull.add(log.getText());
								
					} else {
								
						tlData.add(log.getText());
						notNull.add(log.getText());
								
					}
				}
			} catch(Exception e) {}
		}
		if(notNull.size() == 0) {
			this.takeScreenshot();
			test.log(LogStatus.INFO, "[" + getDate + "] " + "Found [" + notNull.size() +"] worklog " + test.addScreenCapture(imagePath()));
		} else {
			this.takeScreenshot();
			test.log(LogStatus.INFO, "[" + getDate + "] " + "Found [" + notNull.size() +"] worklogs "+ notNull + test.addScreenCapture(imagePath()));
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
