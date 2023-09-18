
package farmeWork.Test;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import farmeWork.TestComponent.BaseTest;
import frameWork.Code.ProductCatalogue;
import frameWork.AbstractComponent.OrderPage;
import frameWork.Code.CartPage;
import frameWork.Code.CheckOutPage;
import frameWork.Code.LandingPage;
import frameWork.Code.ConfirmationPage;

public class SummitOrderTest2 extends BaseTest 
{
	

		String productName = "ZARA COAT 3";

		@Test
		public void submitOrder() throws InterruptedException, IOException
  {
			
		ProductCatalogue productCatalogue = landingPage.loginApplication("RahulJain@Gmail.com", "@Jaikr7566");

		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);

		Assert.assertTrue(match);
		cartPage.goToCheckout();

		CheckOutPage checkOutPage = new CheckOutPage(driver);
		
		checkOutPage.selectCountry("india");
	
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
    	String confirmMessage =	confirmationPage.getConfirmationPage();
	
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		}
		
		
		@Test (dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest()
		{
			ProductCatalogue productCatalogue = landingPage.loginApplication("RahulJain@Gmail.com", "@Jaikr7566");
			OrderPage orderPage  =productCatalogue.goToOrderPage();
			Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		}

	}
