package EditTimePage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditTimePageObjects {
	
	WebDriver driver;
	
	public EditTimePageObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='td-view-switch']/a[1]")
	WebElement timelineViewBtn;
	
	@FindBy(xpath="//div[contains(@class, 'td-timezone-btn-dashboard')]/button[contains(@class, 'dropdown-toggle')]")
	WebElement timezoneDropdown;
	
	@FindBy(xpath="//span[contains(text(),'Time Worked:')]/following-sibling::b")
	WebElement totalTimeWorked;
	
	@FindAll({
		@FindBy(xpath="//div[contains(@id, 'users-select-container') and contains(@class,'btnSelectUser')]")
	})
	private List<WebElement> userDropdownToggle;
	
	@FindBy(xpath="//ul[contains(@class, 'multiselect-container')]/li/a/label[contains(@title, '(Myself)')]")
	WebElement userMyself;
	
	@FindBy(xpath="//button[contains(@class,'tdBtnPrev')]")
	WebElement previousDayBtn;
	
	public WebElement timelineViewBtn() {
		return timelineViewBtn;
	}
	
	public WebElement timezoneDropdown() {
		return timezoneDropdown;
	}
	
	public WebElement totalTimeWorked() {
		return totalTimeWorked;
	}
	
	public WebElement userMyself() {
		return userMyself;
	}
	
	public List<WebElement> userDropdownToggle(){
		return userDropdownToggle;
	}
	
	public WebElement previousDayBtn() {
		return previousDayBtn;
	}
	
}
