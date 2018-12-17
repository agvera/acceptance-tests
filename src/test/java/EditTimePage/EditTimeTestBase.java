/**
* Project Name : Time Doctor Automation Framework 
* Author : Time Doctor QA Team
* Version : V 1.0 
* Reviewed By : Sandy
* Date of Creation : 08/31/2018
* Modification History : 
* Date of change : 09/04/2018 
* Version : V 1.1 
* changed function :  
* change description : Added all the standards and modified the code
* Modified By : Payal Choksey
*/

package EditTimePage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class EditTimeTestBase extends TDWeb_006{
	
	// Calling the method from the TDWeb_001 

		@BeforeTest
		public void propCalling() throws IOException
		{
			
			IntializingProperties();
		}
		
	// Calling the method from TDWeb_001 to launch the application on various browser	
		@Test (priority = 1, description="Navigates to the time doctor Login page")

		public void browserCalling() throws IOException, InterruptedException
		{
			TDLoginPage();
		}
		
		@Parameters({"email","password"})
		@Test (priority = 2, description="Login with Valid credentials")

		public void logindetails(String email, String password) throws IOException, InterruptedException
		{
			login(email,password);
		}
		
		@Test (priority = 3, description="Navigate to Edit Time Page from left navigation bar" )

		public void navedittime() throws IOException
		{
			editnavigation();
		}
		
		@Test (priority = 4, description="Check for user list" )

		public void userdroplist() throws IOException
		{
			droplistuser();
		}
		@Test (priority = 5, description="Checking the Cancel button" )

		public void canceladdtime() throws IOException, InterruptedException
		{
			AddtimeCancel();
		}
		@Test (priority = 9, description="Saving time wih invalid data" )

		public void saveaddtime() throws IOException, InterruptedException
		{
			AddtimeinvalidSave();
		}
	
		@Test (priority = 10, description="Entering wrong project name" )

		public void projectinvalid() throws IOException, InterruptedException
		{
			invalidprojectname();
		}
		

		@Test (priority = 11, description="Saving manual time" )

		public void editmanualtime() throws IOException, InterruptedException
		{
			manualedittime();
		}
		
		@Test (priority = 6, description="Navigate to Edit Time Page from left navigation bar" )

		public void buttondate() throws IOException
		{
			datebutton();
		}
		
		@Test (priority = 7, description="Navigate to Edit Time Page from left navigation bar" )

		public void viewlist() throws IOException, InterruptedException
		{
		//	listview();
			
		}
		@Test (priority = 8, description="Navigate to Edit Time Page from left navigation bar" )

		public void oldtonewtimepage() throws IOException, InterruptedException
		{
			//oldtimepagetonewtimepage();
			
		}
}
	
		
		
	
