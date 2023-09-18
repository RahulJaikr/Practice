package frameWork.AbstractComponent;

import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

import frameWork.Code.CartPage;
import org.openqa.selenium.JavascriptExecutor;
	
// here we can use our reuseability code we don't need to write again and again in every class.
// we can extends this class to every class
	
// we will create singal method of that class and give parameter as WebElement
	// and evevry class we need to give the super keyword;
public class AbstractComponent 
	{

	
// if we extend as parent class so we can use super keyword
// it will use to invok parent class consutuctor
// it will initilize the driver of parent class
	ChromeDriver driver;
	
		
	public AbstractComponent(ChromeDriver driver) 
	{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

	//	By findBy= it is called by loctoer we are not using element by the driver.
		@FindBy(css = "[routerlink*='cart']")
		WebElement cartHeader;
		
	     @FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	     WebElement orderHeader;
		//button[@routerlink='/dashboard/myorders']
	     
		public void waitForElements(By findBy)
	{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	     
		public void waitForElementToAppear(By findBy)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

		}
		

		public void waitForElementDisappear(WebElement eles) throws InterruptedException
		{
			// Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.invisibilityOf(eles));
		}
		
		// we have to put thoese method in one class which icon is vivible in evety page
		public CartPage goToCartPage() 
		
		{
			cartHeader.click();
			CartPage cartPage = new CartPage(driver);
			return cartPage;
			
		}
		
		public OrderPage goToOrderPage()
		{
			orderHeader.click();
			OrderPage orderPage = new OrderPage(driver);
			return orderPage;
		}
		
		public void scrollBy()
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1049, 618)");
		}

//		public void waitForElemente(By findBy) {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	//
//		}
	//
//		public void waitForElements(By findBy) {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	//
//		}
	//
//		public void waitForElementss(By findBy) {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//			wait.until(ExpectedConditions
//					.visibilityOfElementLocated(By.xpath("//div/a[contains(@Class,'ng-star-inserted')]")));
	//
//		}

		

	}


