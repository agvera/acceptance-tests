package TimeUse;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimeUseObjects {
	
	WebDriver driver;
	
	public TimeUseObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class, 'pageWrapper') and contains(@class, 'time-use-report')]")
	WebElement timeUseContentWrapper;
	
	@FindAll({
		@FindBy(xpath="//span[@id='TotalTimeWorked']")
	})
	private List<WebElement> totalTimeLogged;
	
	public WebElement timeUseContentWrapper(){
		return timeUseContentWrapper;
	}
	
	public List<WebElement> totalTimeLogged(){
		return totalTimeLogged;
	}
}
