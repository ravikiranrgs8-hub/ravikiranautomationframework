package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By logoutlink = By.linkText("Logout");
	private By search = By.name("search");
	private By accHeaders= By.cssSelector("div#content >h2");
	private By searchIcon= By.cssSelector("div#search button");
	
	//Page constants
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	 //Page actions/methods:
	 public String getAccPageTitle()
	
	 {
		 String title= eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		 System.out.println("Account page title: " +title);
		 return title; 
	 }
	 
	 public String getAccPageURL()
	 {
		 String url=eleUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		 System.out.println("Account page url: " + url);
		 return url;
	 }
	 
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.waitForVisibilityOfElement(logoutlink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	
	public void logout()
	{
		if(isLogoutLinkExist())
		{
			eleUtil.doClick(logoutlink);
		}
	}

	public boolean isSearchFieldExist()
	{
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public List<String> getAccountHeaders()
	{
		List<WebElement> headerlist=eleUtil.waitForVisibilityOfElements(accHeaders, AppConstants.SHORT_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>();
 		for(WebElement e:headerlist)
		{
			String text = e.getText();
			headersValList.add(text);
			
		}
 		return headersValList;
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);//TDD- Test Driver Development
	}
	
}




