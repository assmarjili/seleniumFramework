package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class AddProductToShoppingCartTest extends TestBase{
	
	SearchPage searchPage;
	ProductDetailsPage productDetailsPage;
	ShoppingCartPage shoppingCartPage ; 
	String productName = "Apple MacBook Pro 13-inch";
	
	@Test(priority=1)
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
	@Test(priority=2)
	public void userCanAddProductToShoppingCart() throws InterruptedException {
		productDetailsPage = new ProductDetailsPage(driver);
		productDetailsPage.addToCart();
		Thread.sleep(3000);
		driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
		shoppingCartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(shoppingCartPage.productName.getText().contains(productName));
	}
	
	@Test(priority=3)
	public void userCanRemoveProductFromShoppingCart() {
		shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage.removeProductFromCart();
	}




}
