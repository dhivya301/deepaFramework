package testPage;

import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Base.BasePage;

import objectPage.pageObject;

public class LoginScript {
	BasePage bp;
	pageObject po;
	
	
	@Test
	public void LaunchApplication()
	{
		bp=new BasePage();
	}

	@Test
	public void LoginToTheApplication() throws DocumentException{
		bp=new BasePage();
		po=new pageObject();
		po.Login();
		String title="Adactin.com - Search Hotel";
		Assert.assertTrue(title.equals(bp.getTitle1()));
	}
	
	@Test
	public void quitTheBrowser(){
		bp.teardown();
	}
}
