package frameWork.Code;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameWork.AbstractComponent.AbstractComponent;

public class LandingPage  extends AbstractComponent
{

	ChromeDriver driver ;
	
	//first we need to create consturctor toinitilize the driver which we have provide in the main

	public LandingPage(ChromeDriver driver) 
	{
		super(driver);
		this.driver=driver;
		//it will invoke cureent class local variable so it will get a knowledeg about the driver.\
		// if we need not invoke the local variable so driver scope will reamin to this constructor
		
		PageFactory.initElements(driver , this);
		// this this refer to crrent class variable and this driver will initilize all the element
	}
	
	// # findby don't have knwolwde about thr driver so we are giving knowdlge by initelement
	//findby = driver.findelement
	// so we do it in 
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(id="login")
	WebElement summit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
By errorMessages	= (By.cssSelector("[class*='flyInOut']"));
	
	public ProductCatalogue loginApplication(String email , String password )
	// if we need to call method so we have given here parameter 
	// so with the class object .method name then need to give argument then it will call
	{
		
		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		summit.click();
		
		//# after submit we are landing on next page That means Productcatalogue page so this submit click will take next page 
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);	
		return productCatalogue; 
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");

	}
	

	public String getErrorMessage()
	{
		waitForElementToAppear(errorMessages);
		return errorMessage.getText();
		 
	}
	
	
	
	
	
}
