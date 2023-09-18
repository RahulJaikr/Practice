package frameWork.Code;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameWork.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent
{
   ChromeDriver driver ;
   String productName = "ZARA COAT 3";
   
   @FindBy(css = ".totalRow button")
   WebElement checkoutfile;
   
   @FindBy(css = ".cartSection h3")
   private List<WebElement> cartProducts;
   
	public CartPage(ChromeDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver , this);
	}
	  public Boolean VerifyProductDisplay(String productName)
	  
	  {
		  Boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		  return match; 
		  
		  //# That cartProducts do we are applying stream and we are making sure if that productName is equal to productName  
	  }
	  
	 // public CheckOutPage goToCheckout()
	  public void goToCheckout()
	  {
		  checkoutfile.click();
		//return new CheckOutPage(driver);
		 
		  
	  }
}
