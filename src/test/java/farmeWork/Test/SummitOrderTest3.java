
package farmeWork.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import farmeWork.TestComponent.BaseTest;
import frameWork.AbstractComponent.OrderPage;
import frameWork.Code.CartPage;
import frameWork.Code.CheckOutPage;
import frameWork.Code.ConfirmationPage;
import frameWork.Code.ProductCatalogue;

public class SummitOrderTest3 extends BaseTest {
	// object array se data reterive kese kare g

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// for removing the para meter we use hasMap to provide the value
		// data provider also allow hasmap to send data
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("passwords"));

		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("productName"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));

		Assert.assertTrue(match);
		cartPage.goToCheckout();

		CheckOutPage checkOutPage = new CheckOutPage(driver);

		checkOutPage.selectCountry("india");

		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationPage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("RahulJain@Gmail.com", "@Jaikr7566");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// create a hasmap object
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "RahulJain@Gmail.com");
		map.put("passwords", "@Jaikr7566");
		map.put("productName", "ZARA COAT 3");
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "ShreyaJaikr@gmail.com");
		map1.put("passwords", "Jaikr7566");
		map1.put("productName", "ADIDAS ORIGINAL");
		return new Object[][] { { map }, { map1 } };

	}

}