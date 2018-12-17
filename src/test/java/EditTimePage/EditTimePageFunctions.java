package EditTimePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.LogStatus;

import GlobalProperties.GlobalSetup;
import GlobalProperties.GlobalFunctions;
import GlobalProperties.GlobalObjects;
import NavigateToPages.NavigationFunctions;

public class EditTimePageFunctions extends GlobalSetup{

	private EditTimePageObjects etObj = new EditTimePageObjects(driver);
	private GlobalFunctions global = new GlobalFunctions();
	private GlobalObjects globalObj = new GlobalObjects(driver);
	
	public List<String> etData = new ArrayList<String>();
	
	public void GetTotalTimeWorked() throws IOException, InterruptedException {
		
		// Initialize test reporting
		test = report.startTest("Edit Time Page", "Get total time worked of specific user");
		
		test.log(LogStatus.INFO, "Go to New Timesheet page [ /#/edit-time ]");
		NavigationFunctions navigate = new NavigationFunctions();
		navigate.GoToNewEditTimePage();
		
		this.SelectTimelineView();
		
		global.SelectCompanyTimezone();
		
		this.SelectMyself();
		
		etObj.previousDayBtn.click();
		test.log(LogStatus.INFO, "Click [Previous Day] 3 times");
		
		for(int i = 1; i <=3; i++) {
			Thread.sleep(5000);
			this.addWorklogtoArray();
			etObj.previousDayBtn.click();
		}
		
	}
	
	public void SelectMyself() {
		
		if(etObj.userDropdownToggle().size() > 0) {
			etObj.userDropdownToggle().get(0).click();
			etObj.userMyself().click();
			test.log(LogStatus.INFO, "Filter user: " + etObj.userMyself().getAttribute("title"));
		}
		
	}
	
	public void SelectTimelineView() {
		etObj.timelineViewBtn.click();
		test.log(LogStatus.INFO, "Select Timeline View");
	}
	
	public void addWorklogtoArray() throws IOException {
		
		String log = etObj.totalTimeWorked().getText();
		String getDate = globalObj.dateValue().getText();
		List<String> notNull = new ArrayList<String>();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			if(!log.equals("0h 0m")) {
				if(log.substring(0,2).equals("0h")) {
					
					etData.add(log.substring(3));
					notNull.add(log);
					
				} else {
					
					etData.add(log);
					notNull.add(log);
					
				}
			}
		} catch(Exception e) {}
		
		if(notNull.size() == 0) {
			this.takeScreenshot();
			test.log(LogStatus.INFO, "[" + getDate + "] " + "Found [" + notNull.size() +"] worklog " + test.addScreenCapture(imagePath()));
		} else {
			this.takeScreenshot();
			test.log(LogStatus.INFO, "[" + getDate + "] " + "Found [" + notNull.size() +"] worklogs "+ notNull + test.addScreenCapture(imagePath()));
		}
		
	}
	
}
