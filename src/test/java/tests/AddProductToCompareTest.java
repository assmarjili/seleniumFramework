package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ComparePage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class AddProductToCompareTest extends TestBase{
	String firstProductName = "Apple MacBook Pro 13-inch";
    String secondProductName = "Asus N551JK-XO076H Laptop"; 

	// 1- Search for product number 1
	// 2- Search for product number 2 
	// 3- Add to compare 
	// 4- Clear list

	ProductDetailsPage productDetailsPage ; 
	HomePage HomePage ; 
	ComparePage comparePage ; 
	SearchPage searchPage ; 
	
	@Test(priority=1)
	public void userCanCompareProducts() throws InterruptedException{
		searchPage= new SearchPage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		comparePage = new ComparePage(driver);
		
		searchPage.productSearchUsingAutoSuggest("mac");
		Assert.assertTrue(productDetailsPage.productNamebreadCrumb.getText().contains(firstProductName));
		productDetailsPage.addProductToCompare();
		
		searchPage.productSearchUsingAutoSuggest("asus");
		Assert.assertTrue(productDetailsPage.productNamebreadCrumb.getText().contains(secondProductName));
		productDetailsPage.addProductToCompare();
		Thread.sleep(1000);
	
		driver.navigate().to("https://demo.nopcommerce.com" + "/compareproducts");
		comparePage.compareProducts();

	}
	
	@Test(priority=2)
	public void userCanClearCompareList(){
		comparePage.clearCompareList();
		Assert.assertTrue(comparePage.noDataLbl.getText().contains("You have no items to compare."));	
	}
}
