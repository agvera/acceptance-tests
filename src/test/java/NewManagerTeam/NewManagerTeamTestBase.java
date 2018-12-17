package NewManagerTeam;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import LoginPage.TDWeb_001;

public class NewManagerTeamTestBase extends TDWeb_003{
	
	
	@BeforeTest
	public void Login() throws InterruptedException, IOException
	{
		LoginPage.TDWeb_001 a = new LoginPage.TDWeb_001();
		a.IntializingProperties();
	}
	@Test (priority = 1)
	public void login() throws InterruptedException, IOException
	{
		TDLoginPage();
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	}
   @Test (priority = 2)
	public void managinguserlinks() throws InterruptedException, IOException
	{
	   tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		manageteamlink();
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	
	}
   @Test (priority = 3)
  	public void manageteam() throws InterruptedException, IOException
  	{
	   tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
  		manageteampage();
  		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
  	
  	}
	
	@Test (priority = 4)
	public void cancelmanageteam() throws InterruptedException, IOException
	{
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		manageteamcancel();
	}
	@Test (priority = 5)
	public void savemanageteam() throws InterruptedException, IOException
	{
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		manageteamsave();
		
	}
	
	@Test (priority = 6)
	public void deletemanageteam() throws InterruptedException, IOException
	{
		
		tdweb.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		manageteamdelete();
	}
	}
