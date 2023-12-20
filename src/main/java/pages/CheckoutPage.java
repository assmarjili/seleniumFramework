package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends PageBase{

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="button.button-1.checkout-as-guest-button")
	WebElement guestBtn;

	@FindBy(id= "BillingNewAddress_FirstName")
	WebElement fnTxt;

	@FindBy(id= "BillingNewAddress_LastName")
	WebElement lnTxt;

	@FindBy(id= "BillingNewAddress_Email")
	WebElement emailTxt;

	@FindBy(id= "BillingNewAddress_StateProvinceId")
	public WebElement countryList;


	@FindBy(id= "BillingNewAddress_City")
	WebElement cityTxt;

	@FindBy(id= "BillingNewAddress_Address1")
	WebElement adressTxt;

	@FindBy(id= "BillingNewAddress_ZipPostalCode")
	WebElement postalCodeTxt;

	@FindBy(id= "BillingNewAddress_PhoneNumber")
	WebElement phoneTxt;

	@FindBy(id= "//*[@id=\"billing-buttons-container\"]/button[4]")
	WebElement continueBtn;

	@FindBy(id= "shippingoption_1")
	WebElement shippingoptionrdo;

	@FindBy(xpath = "//*[@id=\"shipping-method-buttons-container\"]/button")
	WebElement continueShippingBtn;

	@FindBy(xpath = "//*[@id=\"payment-method-buttons-container\"]/button")
	WebElement continuePaymentBtn;

	@FindBy(xpath = "//*[@id=\"payment-info-buttons-container\"]/button")
	WebElement continueInfoBtn;

	@FindBy(css= "a.product-name")
	public WebElement productName;

	@FindBy(xpath = "//*[@id=\"confirm-order-buttons-container\"]/button")
	WebElement confirmBtn;

	@FindBy(tagName = "h1")
	public WebElement thankYouLbl;

	@FindBy(css= "div.title")
	WebElement successMessage;

	@FindBy(linkText = "Click here for order details.")
	WebElement orderDetailsLink;

	public void registeredUserCheckoutProduct(String countryName, String adress, String postalCode, String phone, String city, String productName) throws InterruptedException {
		select = new Select(countryList);
		select.selectByVisibleText(countryName);
		setTextElementText(cityTxt, city);
		setTextElementText(adressTxt, adress);
		setTextElementText(postalCodeTxt, postalCode);
		setTextElementText(phoneTxt, phone);
		clickButton(continueBtn);
		clickButton(shippingoptionrdo);
		clickButton(continueShippingBtn);
		Thread.sleep(1000);
		clickButton(continuePaymentBtn);
		Thread.sleep(1000);
		clickButton(continueInfoBtn);
	}

	public void confirmOrder() throws InterruptedException {
		Thread.sleep(1000);
		clickButton(confirmBtn);
	}

	public void viewOrderDetails() {
		clickButton(orderDetailsLink);
	}

	public void checkoutProduct(String firstName, String lastName, String email, String countryName, String adress, String postalCode, String phone, String city, String productName) throws InterruptedException {
		setTextElementText(fnTxt, firstName);
		setTextElementText(lnTxt, lastName);
		setTextElementText(emailTxt, email);
		select = new Select(countryList);
		select.selectByVisibleText(countryName);
		setTextElementText(cityTxt, city);
		setTextElementText(adressTxt, adress);
		setTextElementText(postalCodeTxt, postalCode);
		setTextElementText(phoneTxt, phone);
		clickButton(continueBtn);
		clickButton(shippingoptionrdo);
		clickButton(continueShippingBtn);
		Thread.sleep(2000);
		clickButton(continuePaymentBtn);
		Thread.sleep(2000);		
		clickButton(continueInfoBtn);
	}
}
