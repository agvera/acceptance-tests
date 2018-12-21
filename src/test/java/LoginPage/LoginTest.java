package LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import GlobalProperties.GlobalSetup;

public class LoginTest extends GlobalSetup{
	
	@Test
	public void LoginWithValidCredentials() {
		
		String validEmail = getEmail();
		String validPassword = getPassword();
		
		// Initialize test reporting
		test = report.startTest("Login to Time Doctor ", "Verify that user can login to Time Doctor");
		
		// Get current URL and expected URL
		String currentURL = driver.getCurrentUrl();
		String pageTitle = driver.getTitle();
		String expectedPageTitle = "Login to Time Doctor";
		
		// Verify that user is currently at base URL
		Assert.assertTrue(pageTitle.equals(expectedPageTitle));
		test.log(LogStatus.PASS, "Starting location is correct " + expectedPageTitle);	
		
		LoginObjects loginEl = new LoginObjects(driver);
		
		loginEl.emailField().sendKeys(validEmail);
		loginEl.passwordField().sendKeys(validPassword);
		loginEl.signinBtn().click();
		
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/#/dashboard-company") || driver.getCurrentUrl().contains("/#/dashboard_individual"));
		test.log(LogStatus.PASS, "Successfully redirected to " + currentURL);
		report.endTest(test);
	}
	
	public String getEmail() {
		
		String url = testProperty.getProperty("BaseURL");
		String email = null;
		
		if (url.contains("staging")) {
			email = testProperty.getProperty("StagingEmail");
		} else if (url.contains("test")) {
			email = testProperty.getProperty("TestEmail");
		} else {
			email = testProperty.getProperty("ProdEmail");
		}
		
		return email;
		
	}
	
	public String getPassword() {
		
		String url = testProperty.getProperty("BaseURL");
		String password = null;
		
		if (url.contains("staging")) {
			password = testProperty.getProperty("StagingPassword");
		} else if (url.contains("test")) {
			password = testProperty.getProperty("TestPassword");
		} else {
			password = testProperty.getProperty("ProdPassword");
		}
		
		return password;
	}
	
}
