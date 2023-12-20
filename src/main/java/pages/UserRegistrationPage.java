package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends PageBase{

	public UserRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="gender-female")
	WebElement genderRdoBtn;
	
	@FindBy(id="FirstName")
	WebElement firstNameTxtBox;

	@FindBy(id="LastName")
	WebElement lastNameTxtBox;

	@FindBy(id="Email")
	WebElement emailTxtBox;

	@FindBy(id="Password")
	WebElement passwordTxtBox;

	@FindBy(id="ConfirmPassword")
	WebElement confirmPasswordTxtBox;
	
	@FindBy(id="register-button")
	WebElement registerBtn;
	
	@FindBy(css="div.result")
	public WebElement succesMessage;
	

	public void userRegistration(String firstName, String LastName,String email, String password) {
		//genderRdoBtn.click();
		clickButton(genderRdoBtn);
		//firstNameTxtBox.sendKeys(firstName);
		setTextElementText(firstNameTxtBox, firstName);
		setTextElementText(lastNameTxtBox, LastName);
		setTextElementText(emailTxtBox, email);
		setTextElementText(passwordTxtBox, password);
		setTextElementText(confirmPasswordTxtBox, password);
		clickButton(registerBtn);
	}
	
}


