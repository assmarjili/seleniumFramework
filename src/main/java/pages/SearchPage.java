package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends PageBase{

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="small-searchterms")
	WebElement searchTextBox ; 

	@FindBy(css="button.button-1.search-box-button")
	WebElement searchBtn ; 

	@FindBy(id="ui-id-1")
	List<WebElement> productList ; 

	@FindBy(linkText = "Apple MacBook Pro 13-inch")
	WebElement productTitle; 

	
	public void productSearch(String productName) {
		setTextElementText(searchTextBox, productName);
		clickButton(searchBtn);
	}
	
	public void openProductDetailsPage() {
		clickButton(productTitle);
	}
	
	public void  productSearchUsingAutoSuggest(String searchTxt) {
		setTextElementText(searchTextBox, searchTxt);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(productList));
		
		productList.get(0).click();
	}

}
