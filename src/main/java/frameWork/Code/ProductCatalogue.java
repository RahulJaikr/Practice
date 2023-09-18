package frameWork.Code;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameWork.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
	
ChromeDriver driver ;
	String productName = "ZARA COAT 3";
	public ProductCatalogue(ChromeDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver , this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	

	By productsBy = By.cssSelector(".mb-3");
	By addProduct = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(productsBy);
		return products;
		// in this method we are getiing the list of product
	}

	

	public WebElement getProductByName(String productName)
	{
		// here we selecting the produt from list
		// that is why we are using getpriductlist.straem
		// beacuse we need to find produt from the list and this have hte list
		
		WebElement prod = getProductsList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		// we adding product into the cart
		// using that list we are adding into the caer
		WebElement prod = getProductByName(productName);
		//here we get the product name
		prod.findElement(addProduct).click();
		// now we are adding into the cart
		waitForElements(toastContainer);
		waitForElementDisappear(spinner);
	}



	
	
	
	

}
