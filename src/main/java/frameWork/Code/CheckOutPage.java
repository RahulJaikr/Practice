package frameWork.Code;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameWork.AbstractComponent.AbstractComponent;

import org.openqa.selenium.JavascriptExecutor;

public class CheckOutPage extends AbstractComponent
{
	ChromeDriver driver;
	String Country = "India";

	public CheckOutPage(ChromeDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;

//	@FindBy (css = "ta-results")
//	WebElement visible;

	// By result = By.cssSelector(".ta-results") ;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By result = By.cssSelector(".ta-results");

	@FindBy ( xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement summit;

	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(result);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() throws InterruptedException

	{

		scrollBy();
		waitForElements(By.cssSelector(".action__submit"));
		System.out.println(driver);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.cssSelector(".action__submit"));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
		
		 submit.click();
		 
		 return new ConfirmationPage(driver);
	}

}

