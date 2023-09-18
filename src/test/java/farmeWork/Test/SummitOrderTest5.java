
package farmeWork.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

public class SummitOrderTest5 extends BaseTest {
	// Json se data reterive kese kare g

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchasee" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException

	{
		// we have to give the data information to the ethod what data we are sending
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);

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
	public Object[][] getData() throws IOException
// object will accept any kind of data becuse it is parent class of all object
	{
// for file we need to create .json extension
		// String filePath =
		// "/FrameWork1/src/test/java/frameWork/Data/PurchaseOrder.json\"),StandardCharsets.UTF_8";
		List<HashMap<String, String>> data = getJsonDataToMap(
				"C:\\eclipse\\FrameWork1\\src\\test\\java\\frameWork\\Data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
		// return type should be two dimaensonal array

	}
// we  have created utilities to get data from json ( we have made data reader class)
}// List<HashMap<String, String>> data = getJsonDataToMap(filePath);