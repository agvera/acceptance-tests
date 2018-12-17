package Timesheet;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimesheetObjects {
	
	WebDriver driver;
	
	public TimesheetObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table[contains(@class, 'timesheet-headers-fixed')]/tbody/tr/th")
	WebElement timesheetTableDate;
	
	@FindBy(xpath="//td[@class='timesheet-name']/b")
	WebElement timesheetName;
	
	@FindBy(xpath="//div[contains(@class, 'pageWrapper') and contains(@class, 'timesheet-report')]")
	WebElement timesheetContentWrapper;

	@FindBy(xpath="//div[contains(@class,'bar-wrapper')]//b")
	WebElement totaltimeLoggedSingleUser;
	
	@FindAll({
		@FindBy(xpath="//td[@class='timesheet-total-time']")
	})
	public List<WebElement> totalTimeLogged;

	@FindAll({
		@FindBy(xpath="//div/div[@class='no-results']")
	})
	public List<WebElement> noData;
	
	
	public WebElement totaltimeLoggedSingleUser(){
		return totaltimeLoggedSingleUser;
	}
	
	public List<WebElement> totalTimeLogged(){
		return totalTimeLogged;
	}
	
	public List<WebElement> noData(){
		return noData;
	}
	
	public WebElement timesheetTableDate(){
		return timesheetTableDate;
	}
	
	public WebElement timesheetContentWrapper(){
		return timesheetContentWrapper;
	}
	
	public WebElement timesheetName(){
		return timesheetName;
	}
	
	
}
