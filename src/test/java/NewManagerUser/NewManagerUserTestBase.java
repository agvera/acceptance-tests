package NewManagerUser;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import LoginPage.TDWeb_001;

public class NewManagerUserTestBase extends TDWeb_002{
	
	
	@BeforeTest
	public void Login() throws InterruptedException, IOException
	{
		TDWeb_001 a = new TDWeb_001();
		a.IntializingProperties();
	}
	@Test (priority =1)
	public void login() throws InterruptedException, IOException
	{
		TDLoginPage();
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		tdweb.manage().window().fullscreen();
		
		 
	}
	
	 @Test (priority = 2)
		public void credentialsValid() throws InterruptedException, IOException
		{
		 	ValidCredentials();
		 	tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			
		}
   @Test (priority = 3)
	public void managingUserLinks() throws InterruptedException, IOException
	{
	   tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		manageUserLink();
		Thread.sleep(5000L);
		
	}

@Test (priority = 4)
	public void buttonsNLinks() throws InterruptedException, IOException
	{
		
		verifyButtonsNLinks();
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
	}
	@Test (priority = 6)
	public void userDetails() throws IOException
	{
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		userSettingPage();
	}
	@Test (priority = 7)
	public void inviteUsers() throws IOException
	{
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		invitationButton();
	}
	@Test (priority = 8)
	public void defaultSettings() throws InterruptedException, IOException
	{
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		newUserDefualtSetting();
	}
	@Test (priority = 9)
	public void filters() throws InterruptedException, IOException
	{
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		filtersScreen();
	}
	@Test (priority = 5)
	public void newManageUserScreen() throws InterruptedException, IOException
	{
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		mainScreen();
	}
	@Test (priority = 10)
	public void cancelManageTeam() throws InterruptedException, IOException
	{
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		manageTeamCancel();
	}
	
	
	}
