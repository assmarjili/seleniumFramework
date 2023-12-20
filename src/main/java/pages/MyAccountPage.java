package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends PageBase{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(linkText="Change password")
	public WebElement changePasswordLink;

	@FindBy(id="OldPassword")
	private WebElement oldPasswordTxt;

	@FindBy(id="NewPassword")
	private WebElement newPasswordTxt;

	@FindBy(id="ConfirmNewPassword")
	private WebElement confirmPasswordTxt;

	@FindBy(css="button.button-1.change-password-button")
	private WebElement changePasswordBtn;

	@FindBy(css="p.content")
	public WebElement resultChangePassword;
	
	@FindBy(css="span.close")
	public WebElement closeNotification;
	
	public void openChangePasswordPage() {
		clickButton(changePasswordLink);
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		setTextElementText(oldPasswordTxt, oldPassword);
		setTextElementText(newPasswordTxt, newPassword);
		setTextElementText(confirmPasswordTxt, newPassword);
		clickButton(changePasswordBtn);	
	}
	
	public void closeNotification(){
		clickButton(closeNotification);
	}

}
