package farmeWork.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import farmeWork.TestComponent.BaseTest;
import frameWork.Code.CartPage;
import frameWork.Code.ProductCatalogue;



public class ErrorValidations extends BaseTest
{
	
	@Test(groups = {"ErrorHandling"})
	public void sumitOrder() throws IOException, InterruptedException

	{
		String productName = "ZARA COAT 3";
		//LandingPage landingPage = lunchApplication();
		landingPage.loginApplication("RahulJain@Gmail.com", "@Jaikmr7566");
	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("RahulJain@Gmail.com", "@Jaikr7566");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

		

	}
	

}
