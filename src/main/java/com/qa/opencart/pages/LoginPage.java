package com.qa.opencart.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By Locators: 
	
	 private By userName = By.id("input-email");
	 private By password = By.id("input-password");
	 private By loginbtn = By.xpath("//input[@value='Login']");
	 private By forgotpasswordlink = By.linkText("Forgotten Password");
	 private By logo = By.cssSelector("img[title='naveenopencart']");
	 private By registerlink= By.linkText("Register");
	 private By loginErrorMessage = By.cssSelector(".alert.alert-danger.alert-dismissible");
	 
	 private By defintion = By.id("defination");	
	 
	 //page const....
	 public LoginPage(WebDriver driver)
	 {
		 this.driver = driver;
		 eleUtil= new ElementUtil(this.driver);
	 }
	 
	 @Step("Getting the login page title")
	 //Page actions/methods:
	 public String getLoginPageTitle()
	
	 {
		// eleUtil.waitForTitleIs("Account Login",5);
		 String title= eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		 System.out.println("Login page title: " +title);
		 return title; 
	 }
	
	 @Step("Getting the login page URL")
	 public String getLoginPageURL()
	 {
		 String url=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		 System.out.println("login page url: " + url);
		 return url;
	 }
	 
	 @Step("Checking forgot password link exist")
	 public boolean isForgotPwdLinkExist()
	 {
		 return eleUtil.waitForVisibilityOfElement(forgotpasswordlink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		  
	 }
	 
	 @Step("Checking Logo exist")
	 public boolean isLogoExist()
	 {
		 return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	 }
	 
	 @Step("username is : {0} and the password is : {1}")
	 public AccountsPage doLogin(String username, String pwd)
	 {
		 System.out.println("Credentials are: " + username + ":" + pwd);
		 eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		 eleUtil.doSendKeys(password, pwd);
		 eleUtil.doClick(loginbtn);
		 
		 return new AccountsPage(driver);
		 
	 }
	 
	 @Step("Navigating to the registration page")
	 public RegisterPage navigateToRegisterPage()
	 {
		 eleUtil.waitForVisibilityOfElement(registerlink, AppConstants.SHORT_DEFAULT_WAIT).click();
		 return new RegisterPage(driver);
	 }
	 
	 public boolean doLoginWrongCredentials(String UserName, String pwd)
	 {
		 System.out.println("wrong credentials are: " + userName + ":" +pwd);
		 eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(UserName);
		 eleUtil.doSendKeys(password, pwd);
		 eleUtil.doClick(loginbtn);
		 String errorMessage= eleUtil.doElementGetText(loginErrorMessage);
		 System.out.println(errorMessage);
		 if(errorMessage.contains(AppConstants.LOGIN_ERROR_MESSAGE))
		 {
			 return true;
		 }
		 return false;
		 
	 }

	 
}
