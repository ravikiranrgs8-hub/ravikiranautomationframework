package com.qa.opencart.tests;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup()
	{
		registerpage=loginpage.navigateToRegisterPage();
	}

	
	public String getRandomEmailID()
	{
		return "testautomation" + System.currentTimeMillis() + "@opencart.com"; //testautomation10490012839@opencart.com
	}
	/*
	 * @DataProvider public Object[][] getUserRegData() { return new Object[][] {
	 * {"Ravi", "kumar", "kumar2123@gmail.com" ,"8989898989","kumar1234",
	 * "kumar1234", "yes"}, {"Komal", "k", "komalk2123@gmail.com"
	 * ,"8989898989","komalk1234", "komalk1234", "yes"} };
	 * 
	 * }
	 */
	
	
	
	
	
	@DataProvider
	public Object[][] getUserRegTestExcelData()
	{
		Object regData[][] = null;
		try {
			regData = ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return regData;
	}
	
	
	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String email, String telephone,String password,String confirmPassword,String subscribe)
	{
		boolean isRegDone = registerpage.userRegistration(firstName, lastName, getRandomEmailID(), telephone, password, confirmPassword, subscribe);
		Assert.assertTrue(isRegDone);
	}
	
	
}
