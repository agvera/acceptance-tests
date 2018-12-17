package Timeline;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimelineReportObjects {
	WebDriver driver;
	
	public TimelineReportObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindAll({
	@FindBy(xpath="//div[contains(@class, 'hoursDisplayBox')]/strong/span")
	})
	private List<WebElement> totalTimeLogged;
	
	public List<WebElement> totalTimeLogged(){
		return totalTimeLogged;
	}
}
