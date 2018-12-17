package LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginObjects {

	WebDriver driver;
	
	public LoginObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='email']")
	WebElement emailField;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement passwordField;
	
	@FindBy(xpath="//button[@id='signinFormButton']")
	WebElement signinBtn;
	
	public WebElement emailField(){
		return emailField;
	}
	
	public WebElement passwordField(){
		return passwordField;
	}
	
	public WebElement signinBtn() {
		return signinBtn;
	}
	
}
