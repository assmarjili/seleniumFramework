package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetailsPage extends PageBase{

	public OrderDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "a.button-2.print-order-button")
    private WebElement printInvoiceLnk ;
	
    @FindBy(css = "a.button-2.pdf-invoice-button")
    private WebElement pdfInvoiceLink;


    public void printOrderDetails() {
    	clickButton(printInvoiceLnk);
    }
    
    public void downloadPDFInvoice() {
    	clickButton(pdfInvoiceLink);
    }
}
