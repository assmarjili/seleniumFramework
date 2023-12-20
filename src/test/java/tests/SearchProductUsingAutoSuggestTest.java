package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductUsingAutoSuggestTest extends TestBase{

	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	ProductDetailsPage productDetailsPage ; 

	@Test
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

}
