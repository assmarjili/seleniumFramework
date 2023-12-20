package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductReviewPage extends PageBase{

	public ProductReviewPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "AddProductReview_Title")
	WebElement reviewTitleTxt;

	@FindBy(id = "AddProductReview_ReviewText")
	WebElement reviewText;

	@FindBy(id = "addproductrating_2")
	WebElement ratingRdoBtn2;

	@FindBy(name = "add-review")
	WebElement submitReviewBtn;

	@FindBy(css = "div.result")
	public  WebElement reviewNotification;


	public void addProductReview(String reviewTitle, String reviewMessage) {
		setTextElementText(reviewTitleTxt, reviewTitle);
		setTextElementText(reviewText, reviewMessage);
		clickButton(ratingRdoBtn2);
		clickButton(submitReviewBtn);
	}
}
