package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTest extends TestBase{
	HomePage homePage;
	UserRegistrationPage userRegistrationPage;
	LoginPage loginPage;

	@Test(priority=1, alwaysRun=true)
	public void userCanRegisterSuccefully() {
		homePage= new HomePage(driver);
		homePage.openRegistrationPage();
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.userRegistration("Assma", "Rjili", "rjiliasm@gmail.com", "assmarj");
		System.out.println(userRegistrationPage.succesMessage.getText());
		Assert.assertTrue(userRegistrationPage.succesMessage.getText().contains("Your registration completed"));
	}
	
	
	@Test(dependsOnMethods= {"userCanRegisterSuccefully"})
	 public void registeredUserCanLogin() {
		homePage.openLoginPage(); 
		loginPage = new LoginPage(driver);
		loginPage.userLogin("rjiliasma@gmail.com", "assmarj");
		Assert.assertTrue(loginPage.logoutLink.getText().contains("Log out"));
	 }
	
	@Test(dependsOnMethods= {"registeredUserCanLogin"})
	public void registeredUserCanLogout() {
		loginPage.userLogout();
	}
	
}
