package companyname.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import companyname.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//PageFactory.initElements(driver, null);
	}
	@FindBy(css=".hero-primary")
	WebElement confirmationMsg;
	
	
	public String getConfirmationMessage() {
		System.out.println("Inside Confirm");
		return confirmationMsg.getText();
	}
	
}
