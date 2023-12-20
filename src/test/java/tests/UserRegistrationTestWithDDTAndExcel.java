package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndExcel extends TestBase{
	HomePage homePage;
	UserRegistrationPage userRegistrationPage;
	LoginPage loginPage;
	
	@DataProvider(name="excelData")
	public Object[][] userRegistrationData() throws IOException{
		ExcelReader ER= new ExcelReader();
		return ER.getExcelData();
	}

	@Test(priority=1, alwaysRun=true,dataProvider = "excelData")
	public void userCanRegisterSuccefully(String fName, String lName, String email, String password) {
		homePage= new HomePage(driver);
		homePage.openRegistrationPage();
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.userRegistration(fName, lName, email, password);
		System.out.println(userRegistrationPage.succesMessage.getText());
		Assert.assertTrue(userRegistrationPage.succesMessage.getText().contains("Your registration completed"));
		
		homePage.openLoginPage(); 
		loginPage = new LoginPage(driver);
		loginPage.userLogin(email,password);
		Assert.assertTrue(loginPage.logoutLink.getText().contains("Log out"));
	
		loginPage.userLogout();

	}	
}
