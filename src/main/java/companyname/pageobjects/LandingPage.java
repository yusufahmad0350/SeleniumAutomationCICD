package companyname.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import companyname.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail= driver.findElement(By.id("userEmail"));
	// WebElement password=driver.findElement(By.id("userPassword"));
	// Page factory
	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement userPassword;
	@FindBy(css="#login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProductCatalogue loginApplication(String email, String password ) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatelogue=new ProductCatalogue(driver);
		return productCatelogue;
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getLogInErrorMessage() {
		waitForWebElementVisible(errorMessage);
		return errorMessage.getText();
	}
	
}
