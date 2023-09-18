
package farmeWork.Test;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameWork.Code.ProductCatalogue;
import frameWork.Code.CartPage;
import frameWork.Code.CheckOutPage;
import frameWork.Code.LandingPage;
import frameWork.Code.ConfirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SummitOrderTest {
	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		// here we give driver as argument so landing class get knowldeg about the
		// driver.

		// here we can call lenadingclass mehod by using argument
		landingPage.goTo();
		// login page url
		ProductCatalogue productCatalogue = landingPage.loginApplication("RahulJain@Gmail.com", "@Jaikr7566");

		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// we are using wait for element appear in the abstract class

		// now we can create object of prduct catalogue page and use method
		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);

		// # Create a object of CartPage class
		// CartPage cartPage = new CartPage(driver);
		CartPage cartPage = productCatalogue.goToCartPage();
		 
		Boolean match = cartPage.VerifyProductDisplay(productName);

		// # All assertions can go inside the test Case.
		// # Page object files should only have the code to perform actions so no
		// assertions sholud be written in your object Page
		//Assert.assertTrue(match);
		
		// we are doing this in above
		// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// it will give the product so we can select all the product

		// we are finding product name inside the product that' swhy we have
		// product.find element
		// need to check first one

		// now we can do the prod . find element so onlyit will search for product

		// .card-body button:last-of-type == it will select last type option

		// Wait until toast is displayed then we willl perfrom all operation

		// we have to wait all the element disapper then we can click on cart page
		// otherwise it will through error.

		

		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// # Need to verify product added into the cart is (verify that)

		// it will return boolean value so we can store in varavle
		// so if match the product it will return true

		Assert.assertTrue(match);
		 cartPage.goToCheckout();
		// CheckOutPage checkOutPage = cartPage.goToCheckout();
		 CheckOutPage checkOutPage = new CheckOutPage(driver);
//		
	checkOutPage.selectCountry("india");
//		
	ConfirmationPage confirmationPage = checkOutPage.submitOrder();
	confirmationPage.getConfirmationPage();
	
		//driver.findElement(By.cssSelector(".totalRow button")).click();

		// now we have to click on country dropdown
		//Actions a = new Actions(driver);
		//a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build()
			//	.perform();
		// we have to choese send keys type here for one we can select element and send
		// we can send value

		// we have give some implicit wait to wait load the option of country so we can
		// select value

//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//
//		// now we have to smmit the code
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(1049, 618)");
//		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
//		WebElement place = driver.findElement(By.cssSelector(".action__submit"));
//		a.moveToElement(place).click().build().perform();

	}

}