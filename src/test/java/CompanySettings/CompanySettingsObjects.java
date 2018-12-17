package CompanySettings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanySettingsObjects {
	
	WebDriver driver;
		
	public CompanySettingsObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(text(),'Save Settings')]")
	WebElement saveSettings;
	
	@FindBy(xpath="//input[@id='confirm-change-timezone']")
	WebElement confirmTimezoneChange;
	
	@FindBy(xpath="//div[@class='tdSuccess']")
	WebElement successMessage;
	
	
	public WebElement saveSettings() {
		return saveSettings;
	}
	
	public WebElement confirmTimezoneChange(){
		return confirmTimezoneChange;
	}
	
	public WebElement successMessage(){
		return successMessage;
	}
}
