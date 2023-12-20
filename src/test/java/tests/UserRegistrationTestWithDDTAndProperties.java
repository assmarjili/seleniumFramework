package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.LoadProperties;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndProperties extends TestBase{
	HomePage homePage;
	UserRegistrationPage userRegistrationPage;
	LoginPage loginPage;
	
	String fname= LoadProperties.userdata.getProperty("firstname");
	String lname= LoadProperties.userdata.getProperty("lastname");
	String email= LoadProperties.userdata.getProperty("email");
	String password= LoadProperties.userdata.getProperty("password");


	@Test(priority=1, alwaysRun=true)
	public void userCanRegisterSuccefully() {
		homePage= new HomePage(driver);
		homePage.openRegistrationPage();
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.userRegistration(fname,lname,email,password);
		System.out.println(userRegistrationPage.succesMessage.getText());
		Assert.assertTrue(userRegistrationPage.succesMessage.getText().contains("Your registration completed"));
	}
	
	
	@Test(dependsOnMethods= {"userCanRegisterSuccefully"})
	 public void registeredUserCanLogin() {
		homePage.openLoginPage(); 
		loginPage = new LoginPage(driver);
		loginPage.userLogin(email,password);
		Assert.assertTrue(loginPage.logoutLink.getText().contains("Log out"));
	 }
	
	@Test(dependsOnMethods= {"registeredUserCanLogin"})
	public void registeredUserCanLogout() {
		loginPage.userLogout();
	}
	
}
