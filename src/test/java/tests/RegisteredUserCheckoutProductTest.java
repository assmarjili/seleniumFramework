package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.UserRegistrationPage;

public class RegisteredUserCheckoutProductTest extends TestBase{	

	/*
	 * 1- Register User
	 * 2- Search for product
	 * 3- Add to Cart
	 * 4- Checkout
	 * 5- Logout 
	 */

	HomePage homePage ; 
	UserRegistrationPage userRegistrationPage ; 
	LoginPage loginPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	ProductDetailsPage productDetailsPage ;
	ShoppingCartPage shoppingCartPage ; 
	CheckoutPage checkoutPage ; 
	OrderDetailsPage orderDetailsPage ;


	@Test(priority=1, alwaysRun=true)
	public void userCanRegisterSuccefully() {
		homePage= new HomePage(driver);
		homePage.openRegistrationPage();
		userRegistrationPage = new UserRegistrationPage(driver);
		userRegistrationPage.userRegistration("Assma", "Rjili", "rjiliaa@gmail.com", "assmarj");
		System.out.println(userRegistrationPage.succesMessage.getText());
		Assert.assertTrue(userRegistrationPage.succesMessage.getText().contains("Your registration completed"));
	}

	@Test(priority= 2)
	public void registeredUserCanLogin() {
		homePage.openLoginPage(); 
		loginPage = new LoginPage(driver);
		loginPage.userLogin("rjiliaa@gmail.com", "assmarj");
		Assert.assertTrue(loginPage.logoutLink.getText().contains("Log out"));
	}

	@Test(priority=3)
	public void userCanSearchWithAutoSuggest() throws InterruptedException{
		searchPage= new SearchPage(driver);
		searchPage.productSearchUsingAutoSuggest("mac");
		productDetailsPage = new ProductDetailsPage(driver);
		Assert.assertEquals(productDetailsPage.productNamebreadCrumb.getText(), productName);
	}

	@Test(priority=4)
	public void userCanAddProductToShoppingCart() throws InterruptedException {
		productDetailsPage = new ProductDetailsPage(driver);
		productDetailsPage.addToCart();
		Thread.sleep(3000);
		driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
		shoppingCartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(shoppingCartPage.productName.getText().contains(productName));
	}

	@Test(priority=5)
	public void userCanCheckoutProduct() throws InterruptedException {
		checkoutPage = new CheckoutPage(driver);
		shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.openCheckoutPage();
		Thread.sleep(3000);		
		checkoutPage.registeredUserCheckoutProduct("Afghanistan", "Tunis", "4000", "23215532", "tunis", productName);
		Assert.assertTrue(checkoutPage.productName.isDisplayed());
		Assert.assertTrue(checkoutPage.productName.getText().contains(productName));
		checkoutPage.confirmOrder();
		Assert.assertTrue(checkoutPage.thankYouLbl.isDisplayed());
		checkoutPage.viewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderDetailsPage = new OrderDetailsPage(driver);
		orderDetailsPage.downloadPDFInvoice();
		orderDetailsPage.printOrderDetails();

	}

	@Test(priority=6)
	public void registeredUserCanLogout() {
		loginPage.userLogout();
	}
}
