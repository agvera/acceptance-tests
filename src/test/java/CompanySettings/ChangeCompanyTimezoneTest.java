package CompanySettings;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import GlobalProperties.GlobalSetup;
import GlobalProperties.GlobalObjects;
import NavigateToPages.NavigationFunctions;

public class ChangeCompanyTimezoneTest extends GlobalSetup{
	
	private GlobalObjects globalObj = new GlobalObjects(driver);

	public void ChangeCompanyTimezone() throws IOException, InterruptedException {
		
		// Initialize test reporting
		test = report.startTest("Company Settings Page", "Change company timezone on company settings page");
		
		// Go to Company Settings page
		test.log(LogStatus.INFO, "Go to Company Settings page [ /v2/index.php?page=company_settings ]");
		NavigationFunctions navigate = new NavigationFunctions();
		navigate.GoToCompanySettings();
		Assert.assertTrue(driver.getCurrentUrl().contains("/v2/index.php?page=company_settings"));
		
		// Get current time-zone
		
		this.takeScreenshot();
		String currentTimezone = globalObj.timezoneDropdownToggle().getText();
		test.log(LogStatus.INFO, "Original timezone: " + currentTimezone + test.addScreenCapture(imagePath()));
		
		// Open time-zone drop-down menu
		globalObj.timezoneDropdownToggle().click();
		
		// Select random time-zone from drop-down list
		int selectRandom = new Random().nextInt(globalObj.timezonesList().size() + 1);
		globalObj.selectTimezone().get(selectRandom).click();
		
		CompanySettingsObjects csObj = new CompanySettingsObjects(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		// Click confirm timezone change button
		wait.until(ExpectedConditions.elementToBeClickable(csObj.confirmTimezoneChange())).click();
		
		// Get new time-zone
		Thread.sleep(1000);
		this.takeScreenshot();
		String newTimezone = globalObj.timezoneDropdownToggle().getText();
		test.log(LogStatus.INFO, "New timezone: " + newTimezone + test.addScreenCapture(imagePath()));
		
		// Save Company Settings
		Thread.sleep(1000);
		csObj.saveSettings().click();
		waitForSuccessMessage();
		
		// End of test
		report.endTest(test);
		
	}
	
	public void waitForSuccessMessage() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			CompanySettingsObjects csObj = new CompanySettingsObjects(driver);
			wait.until(ExpectedConditions.visibilityOf(csObj.successMessage()));
			wait.until(ExpectedConditions.invisibilityOf(csObj.successMessage()));
		} catch(Exception e) {}
	}
	
}
