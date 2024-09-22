package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AbstractComponents;

public class ProductCatalogPage extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.xpath("//button[normalize-space()='Add To Cart']");
	By toastNotification = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementTobeAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println(prod.getText());
		return prod;
	}
	
	public void addToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementTobeDisAppear(toastNotification);
	}
	
	public void goToCartPage() {
		goToCart();
	}
}
