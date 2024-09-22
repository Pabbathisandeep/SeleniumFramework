package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> products;
	
	public boolean verifyProductInOrders(String productName) {
		boolean match = products.stream()
				.anyMatch(productItem -> productItem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
