package TimelogComparison;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import GlobalProperties.GlobalSetup;
import GlobalProperties.GlobalObjects;
import LoginPage.LoginTest;
import TimeUse.TimeUseFunctions;
import Timeline.TimelineFunctions;
import Timesheet.TimesheetFunctions;

public class CompareDailyReportsOfSingleUser extends GlobalSetup{
	
	@Test(dependsOnMethods="Login")
	public void CompareWorkLogByDay() throws InterruptedException, IOException {
		
		LoginTest login = new LoginTest();
		login.LoginWithValidCredentials();
		
		GlobalObjects dateSelection = new GlobalObjects(driver);
		
		test = report.startTest("Timesheet Report Page ", "Get time log of specific user on Timesheet report page from previous days");
		TimesheetFunctions tsFunc = new TimesheetFunctions(dateSelection.filterDayBtn(), dateSelection.previousDayBtn());
		tsFunc.GetTimesheetReportForSingleUser();
		
		test = report.startTest("Time Use Report Page ", "Get time log of specific users on Time Use report page from previous days");
		TimeUseFunctions tuFunc = new TimeUseFunctions(dateSelection.filterDayBtn(), dateSelection.previousDayBtn());
		tuFunc.GetTimeUseReportForSingleUser();
		
		test = report.startTest("Timeline Report Page ", "Get time log of specific users on Timeline report page from previous days");		
		TimelineFunctions tlFunc = new TimelineFunctions();
		tlFunc.GetTimelineReportForSingleUser();
		
		
		// Initialize test reporting
		test = report.startTest("Daily Timelogs Comparison", "Compare timelogs from Timesheet, Time Use and Timeline Report pages");
		if(tsFunc.tsData.isEmpty() && tuFunc.tuData.isEmpty()) {
			test.log(LogStatus.PASS, "PASS: There are no time logs for all Timesheet, Time use and Timeline Report pages");
		} else {
			// compare worklogs
			Assert.assertTrue(tsFunc.tsData.containsAll(tuFunc.tuData));
			Assert.assertTrue(tsFunc.tsData.containsAll(tlFunc.tlData));
			
			Assert.assertTrue(tuFunc.tuData.containsAll(tsFunc.tsData));
			Assert.assertTrue(tuFunc.tuData.containsAll(tlFunc.tlData));
			
			Assert.assertTrue(tlFunc.tlData.containsAll(tsFunc.tsData));
			Assert.assertTrue(tlFunc.tlData.containsAll(tuFunc.tuData));
			
			test.log(LogStatus.INFO, "Assert that logs from Timesheet, Time Use and Timeline Report pages correctly match");
			test.log(LogStatus.PASS, "PASS: Logs from Timesheet, Time Use and Timeline Reports pages should match");
		} 
	}	
}
