package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.name("firstname");
	private By lastName = By.name("lastname");
	private By email = By.name("email");
	private By telephone = By.name("telephone");
	private By password = By.name("password");
	private By confirmpassword = By.name("confirm");
	
	private By subscribeYes= By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo= By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@class='btn btn-primary']");
	private By SuccessMessage = By.cssSelector("div#content h1");
	private By logoutLink= By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	 public RegisterPage(WebDriver driver)
	 {
		 this.driver = driver;
		 eleUtil= new ElementUtil(this.driver);
	 }
	 
	 
	 
	 public boolean userRegistration(String firstName, String lastName, String email, String telephone, String password,String confirmpassword,String subscribe)
	 
	 {
		 eleUtil.waitForVisibilityOfElement(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstName);
		 //eleUtil.doSendKeys(this.firstName, firstName);
		 eleUtil.doSendKeys(this.lastName, lastName);
		 eleUtil.doSendKeys(this.email, email);
		 eleUtil.doSendKeys(this.telephone, telephone);
		 eleUtil.doSendKeys(this.password, password);
		 eleUtil.doSendKeys(this.confirmpassword, confirmpassword);
		 //eleUtil.doSendKeys(this.subscribeYes, subscribe);
		 
		 if(subscribe.equalsIgnoreCase("yes"))
		 {
			 eleUtil.doClick(subscribeYes);
		 }
		 else
		 {
			 eleUtil.doClick(subscribeNo);
		 }
		 
		 eleUtil.doClick(agreeCheckBox);
		 eleUtil.doClick(continueButton);
		 
		 String successMesg = eleUtil.waitForVisibilityOfElement(SuccessMessage, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		 System.out.println(successMesg);
		 
		 if(successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSAGE))
		 {
			 eleUtil.doClick(logoutLink);
			 eleUtil.doClick(registerLink);
			 return true;
		 }
		 else 
		 {
			 return false;
		 }
		 
	 }
	 }
	 
	 	

	
	


