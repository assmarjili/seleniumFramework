package steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import tests.TestBase;

public class End2EndTests extends TestBase {

	SearchPage searchPage;
	ProductDetailsPage productDetailsPage;
	ShoppingCartPage shoppingCartPage;
	CheckoutPage checkoutPage;
	OrderDetailsPage orderDetailsPage;
	String productName = "Apple MacBook Pro 13-inch";

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() {
		Assert.assertTrue(driver.getCurrentUrl().contains("demo.nopcommerce.com"));
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String productName) throws InterruptedException {
		searchPage = new SearchPage(driver);
		searchPage.productSearchUsingAutoSuggest(productName);
		productDetailsPage = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetailsPage.productNamebreadCrumb.getText().contains(productName));
	}

	@When("^choose to buy Two items$")
	public void choose_to_buy_Two_items() throws InterruptedException {
		shoppingCartPage = new ShoppingCartPage(driver);
		productDetailsPage.addToCart();
		driver.navigate().to("http://demo.nopcommerce.com/" + "cart");
	}

	@When("^moves to checkout cart and enter personal details on checkout page and place the order$")
	public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() throws InterruptedException {
		checkoutPage = new CheckoutPage(driver);
		shoppingCartPage.openCheckoutPageAsGuest();
		checkoutPage.checkoutProduct("test", "user", "Egypt"
				, "testuser1@test.com", "test address", "123456", "32445566677", "Cairo", productName);
		Assert.assertTrue(checkoutPage.productName.isDisplayed());
		Assert.assertTrue(checkoutPage.productName.getText().contains(productName));
		checkoutPage.confirmOrder();
		Assert.assertTrue(checkoutPage.thankYouLbl.isDisplayed());
		
	}

	@Then("^he can view the order and download the invoice$")
	public void he_can_view_the_order_and_download_the_invoice() throws InterruptedException {
		orderDetailsPage = new OrderDetailsPage(driver);
		checkoutPage.viewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderDetailsPage.downloadPDFInvoice();
		Thread.sleep(3000);
		orderDetailsPage.printOrderDetails();
	}

}
