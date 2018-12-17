package NavigateToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationObjects {
	
	WebDriver driver;
	
	public NavigationObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Company Section
	@FindBy(xpath="//div[contains(@class, 'company-section')]")
	WebElement companySectionToggle;
	
	@FindBy(xpath="//a[contains(text(),'Company Settings')]")
	WebElement companySettingsLink;
	
	public WebElement companySectionToggle() {
		return companySectionToggle;
	}
	
	public WebElement companySettingsLink() {
		return companySettingsLink;
	}
	
	// Main Navi
	
	@FindBy(xpath="//ul/li/a[contains(text(), 'Edit Time')]")
	WebElement editTimeLink;
	
	@FindBy(xpath="//span[contains(text(), 'Reports')]")
	WebElement reportsLink;
	
	@FindBy(xpath="//span[contains(text(), 'Reports')]/following-sibling::ul/li/a[contains(text(),'Timesheet')]")
	WebElement reportsTimesheetLink;
	
	@FindBy(xpath="//span[contains(text(), 'Reports')]/following-sibling::ul/li/a[contains(text(),'Time Use')]")
	WebElement reportsTimeUseLink;
	
	@FindBy(xpath="//span[contains(text(), 'Reports')]/following-sibling::ul/li/a[contains(text(),'Timeline')]")
	WebElement reportsTimelineLink;
	
	public WebElement editTimeLink(){
		return editTimeLink;
	}
	
	public WebElement reportsLink(){
		return reportsLink;
	}
	
	public WebElement reportsTimesheetLink(){
		return reportsTimesheetLink;
	}
	
	public WebElement reportsTimeUseLink(){
		return reportsTimeUseLink;
	}
	
	public WebElement reportsTimelineLink(){
		return reportsTimelineLink;
	}
	
	
}
