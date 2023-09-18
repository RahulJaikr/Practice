package frameWork.AbstractComponent;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponent
{

	ChromeDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutfile;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;

	public OrderPage(ChromeDriver driver) 
	{
		super(driver);
		this.driver = (ChromeDriver) driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyOrderDisplay(String productName)

	{
		Boolean match = productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}

}
