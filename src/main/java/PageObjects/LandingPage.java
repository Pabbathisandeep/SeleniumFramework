package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail; 
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMessageEle;
	
	public ProductCatalogPage logintoApplication(String Email, String Password) {
		userEmail.sendKeys(Email);
		passwordEle.sendKeys(Password);
		submit.click();
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		return productCatalogPage;
	}
	
	public String getErrorMessage() {
		waitForWebElementTobeAppear(errorMessageEle);
		return errorMessageEle.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
