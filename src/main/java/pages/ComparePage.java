package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComparePage extends PageBase{

	public ComparePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "/html/body/div[6]/div[3]/div/div[2]/div/div[2]/a")
	private WebElement clearListLink;
	
	@FindBy(css="div.no-data")
	public WebElement noDataLbl;
	
	@FindBy(css="table.compare-products-table")
	public WebElement compareTable;
	
	@FindBy(tagName = "tr")
	private List<WebElement> allRows;
	
	@FindBy(tagName = "td")
	private List<WebElement> allCol;
	
	@FindBy(linkText = "Apple MacBook Pro 13-inch")
	public WebElement firstProductName;
	
	@FindBy(linkText = "Asus N551JK-XO076H Laptop")
	public WebElement secondProductName;

	
	public void clearCompareList() {
		clickButton(clearListLink);
	}
	
	public void compareProducts() {
		for (WebElement row : allRows) {
			System.out.println(row.getText() + "\t");
			for (WebElement col : allCol) {
				System.out.println(col.getText() + "\t");
			}
			
		}
	}
	
	
	

}
