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

package LoginnLogoutPage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LogintoLogoutTestBase extends TDWeb_005{
	
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
		
		
	// Calling the method from TDWeb_001 to Verify the Title of the Page	
		@Test (priority = 2, description="Verification of Title of the Login Page")

		public void titleVerifying() throws IOException, InterruptedException
		{
			VerifyTitle();
		}
		
		
	// Calling the method from TDWeb_001 to Enter details in the Registration page - Test it my self
	
	
		@Test (priority = 3, description="Verfication of all fields present on Login Page")

		public void loginData() throws InterruptedException, IOException
		
		{
			LoginVerfication();

	}
		
		// Verification for forgot password functionality
		@Test (priority = 4, description="Verfication of all fields present on Login Page")

		public void forgotPassword() throws InterruptedException, IOException
		
		{
			Forgotpassword();

	}
	// Checking for different language 
		@Test (priority = 5, description="Login page for different language")

		public void langaugeverification() throws InterruptedException, IOException
		
		{
			language();

	}
	// Login with google account	
		@Test (priority = 6, description="Login page with google account")

		public void logingoogle() throws InterruptedException, IOException
		
		{
		//	googlelogin();

	}
	//Login with normal login from the document
		@Parameters({"email","password"})
		@Test (priority = 7, description="Login page")

		public void credentials(String email, String password) throws InterruptedException, IOException
		
		{
			login(email,password);

	}
}
	
		
		
	
