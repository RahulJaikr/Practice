package frameWork.Code;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import frameWork.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent

{
	ChromeDriver driver;
	public ConfirmationPage(ChromeDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver , this);
	}



	@FindBy (css=".hero-primary")
	WebElement confirmationMessage;
	
	//write actions method 
	public String getConfirmationPage()
	{
		
		return confirmationMessage.getText();
		
	}


	

}
