
package farmeWork.Test;

import java.io.IOException;
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

public class SummitOrderTest4 extends BaseTest {
	// hashmap se data reterive kese kare g

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(String email, String password, String productName)
			throws InterruptedException, IOException {
		// we have to give the data information to the ethod what data we are sending
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

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
	// we will provdie that informatin to the test where is our data
	public Object[][] getData() throws IOException {
		return new Object[][] { { "RahulJain@Gmail.com", "@Jaikr7566", "ZARA COAT 3" },
				{ "ShreyaJaikr@gmail.com", "Jaikr7566", "ADIDAS ORIGINAL" } };

	}
}