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

package LoginPage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTestBase extends TDWeb_001{
	
	// Calling the method from the TDWeb_001 

		@BeforeTest
		public void propCalling() throws IOException
		{
			
			IntializingProperties();
		}
		
	// Calling the method from TDWeb_001 to launch the application on various browser	
		@Test (priority = 1, description="Navigates to the time doctor page")

		public void browserCalling() throws IOException, InterruptedException
		{
			TDLoginPage();
		}
		
		
	// Calling the method from TDWeb_001 to Verify the Title of the Page	
		@Test (priority = 2, description="Verification of Title of the Page")

		public void titleVerifying() throws IOException, InterruptedException
		{
			VerifyTitle();
		}
		
		
	// Calling the method from TDWeb_001 to Enter valid credentials
		@Test (priority = 3, description="Entering Valid Credentials")

		public void credentialsValid() throws IOException, InterruptedException
		{
			ValidCredentials();
		}
	
	}