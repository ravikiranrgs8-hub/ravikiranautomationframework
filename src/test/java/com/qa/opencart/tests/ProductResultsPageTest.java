package com.qa.opencart.tests;

import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultsPageTest extends BaseTest{
	
	int i[]= {1,2,3,4,5};
	
	@BeforeClass
	public void productInfoSetUp()
	{
		accPage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	/*
	 * @DataProvider public Object[][] getSearchData() { return new Object[][] {
	 * {"MacBook","MacBook",5},{"MacBook","MacBook Pro",4},{"MacBook","MacBook Air"
	 * ,4}, {"samsung","Samsung SyncMaster 941BW",1} }; }
	 */
	
	
	@DataProvider
	public Object[][] getSearchExcelTestData()
	{
		Object SearchData[][] = null;
		try {
			SearchData= ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SearchData;
	}
	
	@Test(dataProvider  = "getSearchExcelTestData")
	public void productImagestest(String searchKey,String productName,String imageCount)
	{
		searchResultsPage=accPage.doSearch(searchKey);
		productinfopage=searchResultsPage.selectProduct(productName);
		//int actproductImages= productinfopage.getProductImagesCount();//TDD
	//Assert.assertEquals(String.valueOf(productinfopage.getProductImagesCount(), imageCount);	
	}
	
	
	//Brand: Apple
		//Product Code: Product 16
		//Reward Points: 600
		//Availability: In Stock
		

	@Test
	public void productInfoTest()
	{
		searchResultsPage=accPage.doSearch("MacBook");
		productinfopage= searchResultsPage.selectProduct("MacBook");
		Map<String, String> productDetailsMap = productinfopage.getProductDetails();
		//list of model details [1]
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 16");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "600");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		
		
		//List of two prices[2]
		softAssert.assertEquals(productDetailsMap.get("price"), "$602.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"), "$500.00");
		
		softAssert.assertAll();
		
	}
}
