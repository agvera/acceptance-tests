package TimelogComparison;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import GlobalProperties.GlobalSetup;
import GlobalProperties.GlobalObjects;
import LoginPage.LoginTest;
import TimeUse.TimeUseFunctions;
import Timesheet.TimesheetFunctions;

public class CompareMonthlyReportsOfAllUsers extends GlobalSetup{
	
	@Test
	public void CompareWorkLogByDay() throws InterruptedException, IOException {
		
		LoginTest login = new LoginTest();
		login.LoginWithValidCredentials();
		
		GlobalObjects dateSelection = new GlobalObjects(driver);
		
		// Initialize test reporting
		test = report.startTest("Timesheet Report Page ", "Get time log of all users on Timesheet report page from previous days");
		TimesheetFunctions tsFunc = new TimesheetFunctions(dateSelection.filterMonthBtn(), dateSelection.previousMonthBtn());
		tsFunc.GetTimesheetReportForAllUsers();
				
		test = report.startTest("Time Use Report Page ", "Get time log of all users on Time Use report page from previous days");
		TimeUseFunctions tuFunc = new TimeUseFunctions(dateSelection.filterMonthBtn(), dateSelection.previousMonthBtn());
		tuFunc.GetTimeUseReportForAllUsers();
		
		// Initialize test reporting
		test = report.startTest("Monthly Timelogs Comparison", "Compare timelogs from Timesheet and Time Use report pages");
		if(tsFunc.tsData.isEmpty() && tuFunc.tuData.isEmpty()) {
			test.log(LogStatus.PASS, "PASS: There are no time logs for all Timesheet and Time use pages");
		} else {
			// compare worklogs
			Assert.assertTrue(tsFunc.tsData.containsAll(tuFunc.tuData));
			Assert.assertTrue(tuFunc.tuData.containsAll(tsFunc.tsData));
			test.log(LogStatus.INFO, "Assert that logs from Timesheet and Time Use pages correctly match");
			test.log(LogStatus.PASS, "PASS: Logs from Timesheet and Time Use pages match");
		} 
	}	
}
