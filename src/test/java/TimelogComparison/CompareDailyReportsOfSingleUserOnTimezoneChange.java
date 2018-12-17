package TimelogComparison;

import java.io.IOException;

import org.testng.annotations.Test;

import GlobalProperties.GlobalSetup;
import CompanySettings.ChangeCompanyTimezoneTest;
import LoginPage.LoginTest;

public class CompareDailyReportsOfSingleUserOnTimezoneChange extends GlobalSetup{
	
	
	@Test
	public void CompareWorkLogByDay() throws InterruptedException, IOException {
		
		LoginTest login = new LoginTest();
		login.LoginWithValidCredentials();
		
		ChangeCompanyTimezoneTest change = new ChangeCompanyTimezoneTest();
		change.ChangeCompanyTimezone();
		
		CompareDailyReportsOfSingleUser compareReports = new CompareDailyReportsOfSingleUser();
		compareReports.CompareWorkLogByDay();
		
	}	
	
}
