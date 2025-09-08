package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	
	public ProductInfoPage selectProduct(String productName)
	{
		//By ProductnameLocator = By.linkText(productName);
		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}
}
