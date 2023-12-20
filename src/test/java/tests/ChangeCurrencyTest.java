package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class ChangeCurrencyTest extends TestBase{
	
	HomePage homePage ; 
	ProductDetailsPage productDetailsPage ; 
	String productName = "Apple MacBook Pro 13-inch"; 
	SearchPage searchPage ; 
	
	@Test(priority=1)
	public void userCanChangeCurrency() {
		homePage = new HomePage(driver);
		homePage.changeCurrency();
	}
	
	@Test(priority=2)
	public void userCanSearchWithAutoSuggest() {
		try {
			searchPage= new SearchPage(driver);
			searchPage.productSearchUsingAutoSuggest("mac");
			productDetailsPage = new ProductDetailsPage(driver);
			Assert.assertEquals(productDetailsPage.productNamebreadCrumb.getText(), productName);
			Assert.assertTrue(productDetailsPage.productPricelbl.getText().contains("€"));
		} catch (Exception e) {
			System.out.println("error occured " + e.getMessage());		
		}
	}



}
