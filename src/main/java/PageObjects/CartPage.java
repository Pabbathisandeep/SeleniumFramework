package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']//h3")
	List<WebElement> productNames;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOut;

	public boolean verifyProductInCart(String productName) {
		boolean match = productNames.stream()
				.anyMatch(productItem -> productItem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage clickCheckout() {
		checkOut.click();
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		return checkoutPage;
	}

}
