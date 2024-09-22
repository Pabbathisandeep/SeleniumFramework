package tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.CartPage;
import PageObjects.ProductCatalogPage;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidation extends BaseTest {
	String ProductName = "ZARA COAT 3";
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginValidation() {
		String Email = "sandy1234@gmail.com";
		String password = "Virat@1812354";
		landingPage.logintoApplication(Email, password);
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test
	public void productValidation() {
		ProductCatalogPage productCatalogPage = landingPage.logintoApplication("sandy1234@gmail.com", "Virat@18");
		List<WebElement> products = productCatalogPage.getProductList();
		productCatalogPage.addToCart(ProductName);
		CartPage cartPage = productCatalogPage.goToCart();
		boolean match = cartPage.verifyProductInCart(ProductName);
		Assert.assertTrue(match);
	}
}
