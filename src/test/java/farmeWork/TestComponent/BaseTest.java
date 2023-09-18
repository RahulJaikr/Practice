package farmeWork.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import frameWork.Code.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
public class BaseTest {

	public ChromeDriver driver;// #global variable
	public LandingPage landingPage;
	 String productName = "ZARA COAT 3";

	public ChromeDriver initializeDriver() throws IOException 
	
	{

		// properties class
		// we can set value of driver so we can use property file to read the value so
		// we don't need to hard cord it

		Properties prop = new Properties();
		// need to create property class to read the global property
		FileInputStream fis = new FileInputStream("src/main/java/frameWork/resources/GlobalData.properties");
		// we use stream class convert file into stream.
		// give the destination
		// FileInputStream fis = new
		// FileInputStream("C://eclipse//FrameWork2//src//main//java//mainCodePackage//resources//GlobalData.properties");
		prop.load(fis);
		String browsername = prop.getProperty("browser");

		if (browsername.equalsIgnoreCase("chrome")) 
		{
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		}

		else if (browsername.equalsIgnoreCase("firefox")) {
			
		}

		else if (browsername.equalsIgnoreCase("edge")) {
			// edge
			
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

//	
	@BeforeMethod(alwaysRun=true)
	public  LandingPage lunchApplication() throws IOException {

		driver = initializeDriver();
		// we are giving knwoldege of driver property to this driver;
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException

	{
// read json to sring
//	String jsonContetnt = FileUtils.readFileToString
//	(new File(System.getProperty("/FrameWork1/src/test/java/frameWork/Data/PurchaseOrder.json"),StandardCharsets.UTF_8);

		String jsonContetnt = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		// need string to HasMap Jackson databind
		// we are sending the file path as a argument so if we need to call another json so we directly call from method
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContetnt,
		new TypeReference<List<HashMap<String, String>>>() {});

		return data;
	}
	
}
//public WebDriver initializeDriver() {
//	WebDriverManager.chromedriver().setup();
//	driver = new ChromeDriver();
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	driver.manage().window().maximize();
//	return driver;
//}
