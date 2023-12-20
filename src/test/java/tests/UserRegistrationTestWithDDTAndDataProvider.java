package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndDataProvider extends TestBase{
	HomePage homePage;
	UserRegistrationPage userRegistrationPage;
	LoginPage loginPage;
	
	@DataProvider(name= "testData")
	public static Object[][] userData(){
		return new Object[][] {
			{"Assma", "Rjili", "rjiliasma@gmail.com", "assmarj"},
			{"Eya", "Rjili", "rjilieya@gmail.com", "eyarjili"}
		};
	}

	@Test(priority=1, alwaysRun=true, dataProvider = "testData")
	public void userCanRegisterSuccefully(String fName, String lName, String email, String password) {
		homePage= new HomePage(driver);
		homePage.openRegistrationPage();
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.userRegistration(fName, lName, email, password);
		System.out.println(userRegistrationPage.succesMessage.getText());
		Assert.assertTrue(userRegistrationPage.succesMessage.getText().contains("Your registration completed"));
		homePage.openLoginPage(); 
		loginPage = new LoginPage(driver);
		loginPage.userLogin(email, password);
		Assert.assertTrue(loginPage.logoutLink.getText().contains("Log out"));
		loginPage.userLogout(); 
	}
	
}
