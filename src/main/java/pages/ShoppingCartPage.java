package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends PageBase{

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	

	@FindBy(css = "td.product")
	public WebElement productName;
	
	@FindBy(css = "button.remove-btn")
	WebElement removeBtn;

	@FindBy(id = "updatecart")
	WebElement updateCartBtn;

	@FindBy(id = "itemquantity11234")
	public WebElement quantityTxt;

	@FindBy(css = "td.subtotal")
	public WebElement totalLbl;

	@FindBy(id="checkout")
	WebElement checkoutBtn ;
	
	@FindBy(id="termsofservice")
	WebElement agreeCheckbox; 
	
	
	@FindBy(css="button.button-1.checkout-as-guest-button")
	WebElement guestCheckoutBtn ; 
	
	public void removeProductFromCart() {
		clickButton(removeBtn);
	}
	
	public void updateProductQuantityInCart(String quantity) {
		
		clearElementText(quantityTxt);
		setTextElementText(quantityTxt, quantity);
		clickButton(updateCartBtn);
	}
	
	public void openCheckoutPage() {
		clickButton(agreeCheckbox);
		clickButton(checkoutBtn);
	}
	
	public void openCheckoutPageAsGuest() {
		clickButton(agreeCheckbox);
		clickButton(checkoutBtn);
		clickButton(guestCheckoutBtn);
	}



}
