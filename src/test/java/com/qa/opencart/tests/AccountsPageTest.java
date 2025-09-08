package com.qa.opencart.tests;



import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.SearchResultsPage;


public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp()
	{
		//accPage = new AccountsPage(driver);
		//call by reference
		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@Test 
	public void accPageTitleTest()
	{
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest()
	{
		
		Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest()
	{
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExistTest()
	{
		Assert.assertTrue(accPage.isSearchFieldExist());
	}
	
	@Test
	public void accPageHeardersCountTest()
	{
		List<String> accountPageHeadersList = accPage.getAccountHeaders();
		System.out.println(accountPageHeadersList);
		Assert.assertEquals(accountPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);	
		
	}
	
	@Test
	public void accoutnPageHeaderList()
	{
		List<String> accountPageHeaderList= accPage.getAccountHeaders();
		System.out.println(accountPageHeaderList);
		Assert.assertEquals(accountPageHeaderList, AppConstants.ACCOUNT_PAGES_HEADERS_LIST);
	}
	
	
	
	@Test
	public void searchtest()
	{
		searchResultsPage=accPage.doSearch("MacBook");
		productinfopage=searchResultsPage.selectProduct("MacBook");
		String actProductHeader= productinfopage.getProductHeaderName();
		Assert.assertEquals(actProductHeader, "MacBook");
		
	}
}
