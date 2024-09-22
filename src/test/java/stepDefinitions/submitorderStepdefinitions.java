package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PageObjects.CartPage;
import PageObjects.CheckOutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testComponents.BaseTest;

public class submitorderStepdefinitions extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogPage productCatalogPage;
	public CartPage cartPage;
	public static ConfirmationPage confirmationPage;
	@Given("I landed on E commerce page")
	public void I_landed_on_E_commerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogPage = landingPage.logintoApplication(username, password);
	}
	
	@When("^I add product (.+) to the cart$")
	public void add_product_to_cart(String productname) {
		List<WebElement> products = productCatalogPage.getProductList();
		productCatalogPage.addToCart("productName");
		
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_cart(String productName) {
		cartPage = productCatalogPage.goToCart();
		boolean match = cartPage.verifyProductInCart("productName");
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.clickCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.placeOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public static void message_displayed_confirmation_page(String expectedMessage) {
		String actualMessage = confirmationPage.getSuccessMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

}
