package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountryDropdown;
	
	@FindBy(xpath="//button[normalize-space()='India']")
	WebElement DropdownItem;
	
	@FindBy(xpath="//a[normalize-space()='Place Order']")
	WebElement PlaceOrderButton;
	
	By dropdownResults = By.xpath("//section[contains(@class, 'ta-results')]");
	
	
	public void selectCountry(String countryName) {
		Actions ac = new Actions(driver);
		ac.sendKeys(selectCountryDropdown, countryName).build().perform();
		waitForElementTobeAppear(dropdownResults); 
		DropdownItem.click();
	}
	
	public ConfirmationPage placeOrder() {
		PlaceOrderButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
