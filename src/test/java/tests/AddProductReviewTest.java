package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductReviewPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class AddProductReviewTest extends TestBase{
	/*
	 * 1- User registration
	 * 2- Search for product
	 * 3- Add reivew 
	 * 4- Logout
	 */

	HomePage homePage ; 
	UserRegistrationPage userRegistrationPage ; 
	LoginPage loginPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	ProductDetailsPage productDetailsPage ;
	ProductReviewPage productReviewPage ; 
	EmailPage emailPage ; 


	@Test(priority=1, alwaysRun=true)
	public void userCanRegisterSuccefully() {
		homePage= new HomePage(driver);
		homePage.openRegistrationPage();
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.userRegistration("Assma", "Rjili", "rjiliamsa@gmail.com", "assmarj");
		System.out.println(userRegistrationPage.succesMessage.getText());
		Assert.assertTrue(userRegistrationPage.succesMessage.getText().contains("Your registration completed"));
	}

	@Test(priority=2)

	public void registeredUserCanLogin() {
		homePage.openLoginPage(); 
		loginPage = new LoginPage(driver);
		loginPage.userLogin("rjiliamsa@gmail.com", "assmarj");
		Assert.assertTrue(loginPage.logoutLink.getText().contains("Log out"));
	}

	@Test(priority=3)
	public void userCanSearchWithAutoSuggest() throws InterruptedException{
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
	public void registeredUserCanReviewProduct() {
		productDetailsPage = new ProductDetailsPage(driver);
		productDetailsPage.openAddReviewPage();
		productReviewPage = new ProductReviewPage(driver);
		productReviewPage.addProductReview("new review", "Good Product");
		Assert.assertTrue(productReviewPage.reviewNotification.getText().contains("Product review is successfully added."));
	}

	@Test(priority=5)
	public void registeredUserCanLogout() {
		loginPage.userLogout();
	}


}
