package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class EmailFriendTest extends TestBase{
	
	HomePage homePage ; 
	UserRegistrationPage userRegistrationPage ; 
	LoginPage loginPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	ProductDetailsPage productDetailsPage ;
	EmailPage emailPage ; 
	private String email="rjilimas@gmail.com";
	
	@Test(priority=1, alwaysRun=true)
	public void userCanRegisterSuccefully() {
		homePage= new HomePage(driver);
		homePage.openRegistrationPage();
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.userRegistration("Assma", "Rjili", email, "assmarj");
		System.out.println(userRegistrationPage.succesMessage.getText());
		Assert.assertTrue(userRegistrationPage.succesMessage.getText().contains("Your registration completed"));
	}

	@Test(priority=2)

	public void registeredUserCanLogin() {
		homePage.openLoginPage(); 
		loginPage = new LoginPage(driver);
		loginPage.userLogin(email, "assmarj");
		Assert.assertTrue(loginPage.logoutLink.getText().contains("Log out"));
	 }

	@Test(priority=3)
	public void userCanSearchWithAutoSuggest() {
		try {
			searchPage= new SearchPage(driver);
			searchPage.productSearchUsingAutoSuggest("mac");
			productDetailsPage = new ProductDetailsPage(driver);
			Assert.assertEquals(productDetailsPage.productNamebreadCrumb.getText(), productName);

		} catch (Exception e) {
			System.out.println("error occured " + e.getMessage());		
		}
	}
	
	@Test(priority=4)
	public void registeredUserCanSendProductToFriend() {
		productDetailsPage = new ProductDetailsPage(driver);
		productDetailsPage.openSendEmail();
		emailPage = new EmailPage(driver);
		emailPage.sendEmailToFriend("test@gmail.com", "check this product");
		Assert.assertTrue(emailPage.messageNotification.getText().contains("Your message has been sent."));
	}

	@Test(priority=5)
	public void registeredUserCanLogout() {
		loginPage.userLogout();
	}
	
}
