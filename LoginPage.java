package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	//using FindBy to quickly and easily create PageObjects
	@FindBy(name= "txtUsername") WebElement uName;
	
	@FindBy(name= "txtPassword") WebElement pWord;
	
	@FindBy(xpath= "//input[@value='LOGIN']") WebElement loginButton;
	
	
	//added a throws here to catch an exception when trying to login execution moved to quickly
	public void loginToHRM(String username, String password) throws InterruptedException
	{
		
		
		uName.sendKeys(username);
		pWord.sendKeys(password);
		loginButton.click();
		
	}
	public void setUsername(String uname)
	{
		uName.sendKeys(uname);
	}
	public void setPassword(String pwd)
	{
		pWord.sendKeys(pwd);
	}
	public void clickSubmit()
	{
		loginButton.click();
	}
}