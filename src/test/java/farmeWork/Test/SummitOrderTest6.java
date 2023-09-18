
package farmeWork.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class SummitOrderTest6 extends BaseTest {
	// Json se data reterive kese kare g

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException

	{
		// we have to give the data information to the ethod what data we are sending
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("passwrod"));

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

	{

		List<HashMap<String, String>> data = getJsonDataToMap(
				"C:\\eclipse\\FrameWork1\\src\\test\\java\\frameWork\\Data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	public String getScreenshots(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File("C:\\ScreenShot\\ListnersScreenshots" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return "C:\\ScreenShot\\ListnersScreenshots" + testCaseName + ".png";
		// they are asking for file path as a object not a string
		// so we will create file object and give the path

	}

}