package GlobalProperties;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GlobalObjects {
	
	WebDriver driver;
	
	public GlobalObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// User drop-down
	
	@FindBy(xpath="//button[contains(@class,'selectUser')]")
	WebElement userDropdownBase;
	
	@FindBy(xpath="//a[@class='multiselect-all']/label")
	WebElement userDropdownSelectAll;
	
	@FindBy(xpath="//ul[contains(@class, 'multiselect-container')]/li[3]")
	WebElement userDropdownSelectFirst;
	
	@FindBy(xpath="//span[@class='multiselect-selected-text']")
	WebElement selectedUserOnDropdown;
	
	@FindAll({
		@FindBy(xpath="//ul[contains(@class, 'multiselect-container')]/li[3][@class='active']")
	})
	private List<WebElement> userDropdownFirstUserSelected;
	
	@FindBy(xpath="//button[contains(@class, 'date-val')]")
	WebElement dateValue;
	
	@FindBy(xpath="//button[contains(@class, 'week-val')]")
	WebElement weekValue;
	
	@FindBy(xpath="//button[contains(@class, 'month-val')]")
	WebElement monthValue;
	
	@FindAll({
		@FindBy(xpath="//li[contains(@class, 'multiselect-item') and contains(@class, 'active')]")
	})
	private List<WebElement> userDropdownSelectAllActive;
	
	@FindAll({
		@FindBy(xpath="//ul[contains(@class, 'multiselect-container')]/li[@class='active']")
	})
	private List<WebElement> userDropdownSelectActiveUsers;
	
	public WebElement userDropdownBase(){
		return userDropdownBase;
	}
	
	@FindAll({
		@FindBy(xpath="//div/img[@class='loadingAnimation']")
	})
	private List<WebElement> loadingAnimation;
	
	@FindBy(xpath="//div[contains(@class, 'pageWrapper') and contains(@style, 'opacity: 0.6')]")
	WebElement ContentWrapperLoading;
	
	public WebElement userDropdownSelectAll(){
		return userDropdownSelectAll;
	}
	
	public WebElement userDropdownSelectFirst(){
		return userDropdownSelectFirst;
	}
	
	public WebElement selectedUserOnDropdown(){
		return selectedUserOnDropdown;
	}
	
	public List<WebElement> userDropdownSelectAllActive(){
		return userDropdownSelectAllActive;
	}
	
	public List<WebElement> userDropdownSelectActiveUsers(){
		return userDropdownSelectActiveUsers;
	}
	
	public List<WebElement> userDropdownFirstUserSelected(){
		return userDropdownFirstUserSelected;
	}
	// Day, Week, Month Filter Buttons
	
	@FindBy(xpath="//button[@id='dayBtn']")
	WebElement filterDayBtn;
	
	@FindBy(xpath="//button[@id='weekBtn']")
	WebElement filterWeekBtn;
	
	@FindBy(xpath="//button[@id='monthBtn']")
	WebElement filterMonthBtn;
	
	@FindBy(xpath="//button[@id='prevDayBtn']")
	WebElement previousDayBtn;
	
	@FindBy(xpath="//button[@id='previousWeekBtn']")
	WebElement previousWeekBtn;
	
	@FindBy(xpath="//button[@id='previousMonthBtn']")
	WebElement previousMonthBtn;
	
	public WebElement filterDayBtn(){
		return filterDayBtn;
	}
	
	public WebElement filterWeekBtn(){
		return filterWeekBtn;
	}
	
	public WebElement filterMonthBtn(){
		return filterMonthBtn;
	}
	
	public WebElement previousDayBtn(){
		return previousDayBtn;
	}
	
	public WebElement previousWeekBtn(){
		return previousWeekBtn;
	}
	
	public WebElement previousMonthBtn(){
		return previousMonthBtn;
	}
	
	public WebElement dateValue(){
		return dateValue;
	}
	
	public WebElement weekValue(){
		return weekValue;
	}
	
	public WebElement monthValue(){
		return monthValue;
	}

	public List<WebElement> loadingAnimation(){
		return loadingAnimation;
	}
	
	public WebElement ContentWrapperLoading(){
		return ContentWrapperLoading;
	}
	
	
	// Time-zone drop-down
	
	@FindBy(xpath="//div[contains(@class, 'td-timezone-btn-dashboard')]/button[contains(@class, 'dropdown-toggle')]")
	WebElement timezoneDropdownToggle;
	
	@FindAll({
		@FindBy(xpath="//select[@id='timezone-dropdown']/option")
	})
	private List<WebElement> timezonesList;
	
	@FindAll({
		@FindBy(xpath="//div[contains(@class, 'company-timezone-select')]/div[contains(@class, 'dropdown-menu') and contains(@class, 'open')]/ul/li[not(contains(@class, 'selected'))]")
	})
	private List<WebElement> selectTimezone;
	
	@FindBy(xpath="//li/a[contains(@class, 'companyTimezoneIcon')]")
	WebElement companyTimezone;
	
	public WebElement timezoneDropdownToggle(){
		return timezoneDropdownToggle;
	}
	
	public List<WebElement> timezonesList(){
		return timezonesList;
	}
	
	public List<WebElement> selectTimezone(){
		return selectTimezone;
	}
	
	public WebElement companyTimezone() {
		return companyTimezone;
	}
	
}
