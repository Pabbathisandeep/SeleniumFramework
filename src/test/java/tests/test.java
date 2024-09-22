package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class test {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.querySelector('button').innerText = 'Hi There'");
	
		String[] expectedArray = {"About", "Advertising", "Business", "How Search works"};
		WebElement footer = driver.findElement(By.xpath("//div[@class='KxwPGc AghGtd']"));
		List<WebElement> footerLinks = footer.findElements(By.tagName("a"));
		ArrayList<String> linkNames = new ArrayList<String>();
		for (WebElement link : footerLinks) {
			String linkName = link.getText();
			linkNames.add(linkName);
		}
		for(String expectedValue: expectedArray) {
			Assert.assertTrue(linkNames.contains(expectedValue), "Element '"+expectedValue+"' is not present");
		}

		verifyLink("About", linkNames);
		verifyLink("Advertising", linkNames);
		verifyLink("Business", linkNames);
		verifyLink("How Search works", linkNames);

		String[] expectedArray2 = {"Search", "Maps", "YouTube", "Play", "News", "Gmail", "Meet", "Chat", "Contacts", "Drive", "Calendar", "Translate", "Photos", "Shopping", "Finance", "Docs", "Sheets", "Slides", "Books", "Blogger", "Keep", "Jamboard", "Earth", "Saved", "Arts and Culture", "Google Ads", "Travel", "Forms", "Google Store", "Chrome Web Store"};
		List<String> expectedArray3 = Arrays.asList(expectedArray2);
		driver.findElement(By.xpath("//a[@aria-label='Google apps']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@name='app']")));
		driver.switchTo().frame(1);
		List<WebElement> googleApps = driver.findElements(By.xpath("//div[@aria-label='Google apps']//ul//li//a"));
		ArrayList<String> googleAppsName = new ArrayList<String>();
		for (WebElement app : googleApps) {
			String appName = app.getText();
			googleAppsName.add(appName);
		}
		for(String expectedValue: expectedArray3) {
			Assert.assertTrue(googleAppsName.contains(expectedValue), "Element '"+expectedValue+"' is not present");
		}
		driver.quit();
	}

	public static void verifyLink(String linkname, ArrayList<String> linkNames) {
		if (linkNames.contains(linkname)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
}
