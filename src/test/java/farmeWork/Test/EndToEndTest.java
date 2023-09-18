package farmeWork.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import frameWork.Code.CartPage;
import frameWork.Code.CheckOutPage;
import frameWork.Code.LandingPage;
import frameWork.Code.ProductCatalogue;
import frameWork.Code.ConfirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndTest {

	
	// select or.apache.maven.archetype == for maven project
	
	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		LandingPage landingPage = new LandingPage(driver);
		// hp.loginMethod("RahulJain@Gmail.com", "@Jaikr7566");
		// # we will use select cart product
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("RahulJain@Gmail.com", "@Jaikr7566");

		// SelectProduct sp = new SelectProduct(driver);
		List<WebElement> products = productCatalogue.getProductsList();
		// i twill give the product list

		CartPage cartPage = productCatalogue.goToCartPage();

	

		// CartPage cp = new CartPage(driver);
		// # we can call this method from reuse code method

		Boolean match = cartPage.VerifyProductDisplay(productName);

		Assert.assertTrue(match);
		// # Always put validation on the test method

		 cartPage.goToCheckout();
		 CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.selectCountry("india");
		checkOutPage.submitOrder();
		// # Here we are getting error
		ConfirmationPage confirm = new ConfirmationPage(driver);
		String text = confirm.getConfirmationPage();

		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();
	}

}
