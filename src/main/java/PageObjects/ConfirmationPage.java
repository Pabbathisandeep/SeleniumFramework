package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName = "h1")
	WebElement successMessage;
	
	By successMessage2 = By.tagName("h1");
	
	public  String getSuccessMessage() {
		waitForElementTobeAppear(successMessage2);
		String message = successMessage.getText();
		return message;
	}

}
