package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: Design open cart login page")
@Story("US 101: Login page features")
@Feature("F50: Feature login Page")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	
	@Description("login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login page URL test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void loginPageURLTest()
	{
		String actURL = loginpage.getLoginPageURL();
		
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Verify the password link test ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotPwdLinkExistTest()
	
	{
		
	Assert.assertTrue(loginpage.isForgotPwdLinkExist());
		
	}
	
	
	@Description("Verify the application logo test ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void appLogoExistTest()
	{
		Assert.assertTrue(loginpage.isLogoExist());
	}
	
	
	@Description("User is able to login with credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest()
	{
	   accPage= loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	   Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
}
