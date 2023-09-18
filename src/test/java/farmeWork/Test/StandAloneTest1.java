package farmeWork.Test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest1 
{
	public static void main(String[] args) throws InterruptedException 
	{
	
	String productName = "ZARA COAT 3";
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	
	driver.get("https://rahulshettyacademy.com/client");
	
	driver.findElement(By.id("userEmail")).sendKeys("RahulJain@Gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("@Jaikr7566");
	driver.findElement(By.id("login")).click();
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	// it will give the product so we can select all the product 
	
	WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b"))
			.getText().equals(productName)).findFirst().orElse(null);
	// we are finding product name inside the product that' swhy we have product.find element
	//need to check first one
	
	// now we can do the prod . find element so onlyit will search for product
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	//.card-body button:last-of-type  == it will select last type option
	
	//Wait until toast is displayed then we willl perfrom all operation
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	// we have to wait all the element disapper then we can click on cart page otherwise it will through error.
	 
//	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	//# Need to verify product added into the cart is (verify that)
	
	
List<WebElement> cartProducts	= driver.findElements(By.cssSelector(".cartSection h3"));
//cartProducts.stream().filter(s->s.getText().equalsIgnoreCase(productName));

Boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
//it will return boolean value so we can store in varavle
// so if match the product it will return true

Assert.assertTrue(match);
	
driver.findElement(By.cssSelector(".totalRow button")).click();

// now we have to click on country dropdown
Actions a = new Actions(driver);
a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
// we have to choese send keys type here for one we can select element and send we can send value


wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
// we have give some implicit wait to wait load the option of country so we can select value

driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

//now we have to smmit yhe code
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("window.scrollBy(1049, 618)");
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
WebElement place = driver.findElement(By.cssSelector(".action__submit"));
a.moveToElement(place).click().build().perform();


	
	
	
	
}

	
}