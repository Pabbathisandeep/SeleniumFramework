package tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckOutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.OrdersPage;
import PageObjects.ProductCatalogPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String ProductName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
		String expectedMessage = "THANKYOU FOR THE ORDER.";
		ProductCatalogPage productCatalogPage = landingPage.logintoApplication(input.get("Email"),
				input.get("password"));
		List<WebElement> products = productCatalogPage.getProductList();
		productCatalogPage.addToCart(input.get("productName"));
		CartPage cartPage = productCatalogPage.goToCart();
		boolean match = cartPage.verifyProductInCart(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.clickCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		String actualMessage = confirmationPage.getSuccessMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void verifyOrder() {
		landingPage.logintoApplication("sandy1234@gmail.com", "Virat@18");
		OrdersPage ordersPage = landingPage.goToOrders();
		Assert.assertTrue(ordersPage.verifyProductInOrders(ProductName));
	}
	


	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("Email", "sandy1234@gmail.com");
//		map.put("password", "Virat@18");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map.put("Email", "shetty@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("productName", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\purchaseorder.json");
		return new Object[][] { {data.get(0) }, { data.get(1) } };
	}

}
