package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class GuestCheckoutProductFromCartTest extends TestBase{
	
	SearchPage searchPage;
	HomePage homePage;
	ProductDetailsPage productDetailsPage;
	ShoppingCartPage shoppingCartPage;
	CheckoutPage checkoutPage;
	OrderDetailsPage orderDetailsPage;
	String productName = "Apple MacBook Pro 13-inch";

	@Test(priority=1)
	public void userCanSearchWithAutoSuggest() throws InterruptedException{
		searchPage= new SearchPage(driver);
		searchPage.productSearchUsingAutoSuggest("macbook");
		productDetailsPage = new ProductDetailsPage(driver);
		Assert.assertEquals(productDetailsPage.productNamebreadCrumb.getText(), productName);
	}

	@Test(priority=2)
	public void userCanAddProductToShoppingCart() throws InterruptedException {
		productDetailsPage = new ProductDetailsPage(driver);
		productDetailsPage.addToCart();
		driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
		shoppingCartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(shoppingCartPage.productName.getText().contains(productName));
	}

	@Test(priority=3)
	public void userCanCheckoutProduct() throws InterruptedException {
		shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.openCheckoutPageAsGuest();
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.checkoutProduct("Asma", "Rjili", "rjiliassma@gmail.com", "Afghanistan", "test adress", "4000", "25654879", "Afghanistan", productName);
		Assert.assertTrue(checkoutPage.productName.isDisplayed());
		Assert.assertTrue(checkoutPage.productName.getText().contains(productName));
		checkoutPage.confirmOrder();
		Assert.assertTrue(checkoutPage.thankYouLbl.isDisplayed());
	}
	
	@Test(priority=4)
	public void userCanViewOrderDetails() {
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.viewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderDetailsPage = new OrderDetailsPage(driver);
		orderDetailsPage.downloadPDFInvoice();
		orderDetailsPage.printOrderDetails();
	}
}
