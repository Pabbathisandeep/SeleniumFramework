package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalonetest {

	public static void main(String[] args) {
		String ProductName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("sandy1234@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Virat@18");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		prod.findElement(By.xpath("//button[normalize-space()='Add To Cart']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
		boolean match = productNames.stream().anyMatch(productItem-> productItem.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		Actions ac = new Actions(driver);
		ac.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
//		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class, 'ta-results')]")));
		
		driver.findElement(By.xpath("//button[normalize-space()='India']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		String actualText = driver.findElement(By.xpath("//h1")).getText();
		Assert.assertEquals(actualText, "THANKYOU FOR THE ORDER.");
		driver.quit();
	}

}
