package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishlistPage;

public class AddProductToWishListTest extends TestBase{

	SearchPage searchPage;
	ProductDetailsPage productDetailsPage;
	WishlistPage wishlistPage;
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
	public void userCanAddProductToWishlist() throws InterruptedException{
		productDetailsPage = new ProductDetailsPage(driver);
		productDetailsPage.addProductToWishlist();
		driver.navigate().to("https://demo.nopcommerce.com" + "/wishlist");
		wishlistPage = new WishlistPage(driver);
		Assert.assertTrue(wishlistPage.wishlistHeader.isDisplayed());
	}
	
	
	@Test(priority=3)
	public void userCanRemoveProductFromWishlist() throws InterruptedException{
		wishlistPage = new WishlistPage(driver);
		wishlistPage.removeProductFromWishlist();
		Assert.assertTrue(wishlistPage.EmptyCartLbl.getText().contains("The wishlist is empty!"));
	}
}
