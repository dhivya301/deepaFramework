package objectPage;

import org.dom4j.DocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.BasePage;

public class pageObject extends BasePage{
	@FindBy(id="username")
	private WebElement user;
	
	@FindBy(id="password")
	private WebElement pass;
	
	@FindBy(id="login")
private WebElement login;
	
	public pageObject() {
		PageFactory.initElements(driver.get(),this);
		
	}
	
	public void Login() throws DocumentException
	{
		
		String username=readXml("uname");
		user.sendKeys(username);
		String password=readXml("pass");
		pass.sendKeys(password);
		login.click();
		
	}
	
	

}
