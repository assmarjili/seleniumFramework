package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends PageBase{

	public HomePage(WebDriver driver) {
		super(driver);
		jse= (JavascriptExecutor) driver;
		action= new Actions(driver);
	}
	@FindBy(linkText = "Register")
	WebElement registerLink;

	@FindBy(linkText = "Log in")
	WebElement LoginLink;

	@FindBy(linkText = "Contact us")
	WebElement contactUsLink;

	@FindBy(id="customerCurrency")
	WebElement currencydrl;

	@FindBy(xpath = "/html/body/div[6]/div[2]/ul[1]/li[1]/a ")
	WebElement computersMenu;


	@FindBy(xpath  = "/html/body/div[6]/div[2]/ul[1]/li[1]/ul/li[2]/a ")
	WebElement notebooksMenu;

	public void openRegistrationPage() {
		registerLink.click();
	}
	public void openLoginPage() {
		LoginLink.click();
	}

	public void openContactUsPage() {
		scrollBottom();
		clickButton(contactUsLink);
	}

	public void changeCurrency() {
		select = new Select(currencydrl);
		select.selectByVisibleText("Euro");
	}

	public void selectNotebooksMenu() {
		action.moveToElement(computersMenu).moveToElement(notebooksMenu).click().build().perform();
	}

}
