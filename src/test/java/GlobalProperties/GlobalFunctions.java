package GlobalProperties;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class GlobalFunctions extends GlobalSetup{

	private GlobalObjects globalObj = new GlobalObjects(driver);
	
	// Timezone functions
	
	public void SelectCompanyTimezone() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(globalObj.timezoneDropdownToggle)).click();
		globalObj.companyTimezone().click();
		this.takeScreenshot();
		test.log(LogStatus.INFO, "Select company timezone " + globalObj.timezoneDropdownToggle().getText() + test.addScreenCapture(imagePath()));
	}
	
	// Users Dropdown Functions
	
	public void SelectAllUsers() {
		// Filter Date Range = Day, Set to previous day, Select all users
		Boolean selectAll = globalObj.userDropdownBase.getText().contains("All");
		
			if(selectAll.equals(false)) {
				globalObj.userDropdownBase().click();
				globalObj.userDropdownSelectAll().click();
				globalObj.userDropdownBase().click();
			}
	}
	
	public void SelectSingleUser() throws InterruptedException {
		globalObj.userDropdownBase().click();
		globalObj.userDropdownSelectAll().click();
		globalObj.userDropdownSelectFirst().click();
		globalObj.userDropdownBase().click();
	}
	
	public void filterPreviousDay() {
		globalObj.filterDayBtn().click();	
		globalObj.previousDayBtn().click();
	}
	
	public void filterPreviousWeek() {
		globalObj.filterWeekBtn().click();
		globalObj.previousWeekBtn().click();
	}
	
	public void filterPreviousMonth() {
		globalObj.filterMonthBtn().click();
		globalObj.previousMonthBtn().click();
	}
	
	
	public void filterTimelinePreviousDay() {
		globalObj.previousDayBtn().click();
	}
	
	// Loading animation functions
	
	public void waitForLoadingToStart() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					
					
					if(globalObj.loadingAnimation().size() > 0) {
						return true;
					} else {
						return false;
					}
					
				}
			});  
	}
	
	public void waitForLoadingToFinish() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					
					if(globalObj.loadingAnimation().size() == 0) {
						return true;
					} else {
						return false;
					}
					
				}
			});  
	}
	
}
