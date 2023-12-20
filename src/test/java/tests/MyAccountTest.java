package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistrationPage;

public class MyAccountTest extends TestBase{
	HomePage homePage;
	UserRegistrationPage userRegistrationPage;
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	String oldPassword = "test123";
	String newPassword = "test321";
	String firstName = "Asma";
	String lastName = "Rjili";
	String email = "rasma@gmail.com";

	@Test(priority=1)
	public void userCanRegisterSuccefully() {
		homePage= new HomePage(driver);
		homePage.openRegistrationPage();
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.userRegistration(firstName, lastName, email, oldPassword);
		System.out.println(userRegistrationPage.succesMessage.getText());
		Assert.assertTrue(userRegistrationPage.succesMessage.getText().contains("Your registration completed"));
	}
	
	
	@Test(priority=2)
	 public void registeredUserCanLogin() {
		homePage.openLoginPage(); 
		loginPage = new LoginPage(driver);
		loginPage.userLogin(email, oldPassword);
		Assert.assertTrue(loginPage.logoutLink.getText().contains("Log out"));
	 }
	
	@Test(priority=3)
	public void registeredUserChangePassword() {
		myAccountPage = new MyAccountPage(driver);
		loginPage.openMyAccountPage();
		myAccountPage.openChangePasswordPage();
		myAccountPage.changePassword(oldPassword, newPassword);
		Assert.assertTrue(myAccountPage.resultChangePassword.getText().contains("Password was changed"));
		myAccountPage.closeNotification();
	}
	
	@Test(priority=4)
	public void registeredUserCanLogout() throws InterruptedException{
		Thread.sleep(3000);
		loginPage.userLogout();
	}
	
	


}
