package companyname.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import companyname.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

//	@FindBy(xpath = "(//input[@type='text'])[2]")
//	WebElement cardNumber;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement inputCountry;

	@FindBy(css = ".ta-item")
	List<WebElement> Country_list;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	

	By waiter = By.cssSelector("input[control-id='ControlID-32']");

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//PageFactory.initElements(driver, null);
	}

	public void selectCardCountry(String countryName) {
		//waitForElementVisible(waiter);
//		cardNumber.sendKeys(debitCardNumber);
		inputCountry.sendKeys(countryName);
		for (WebElement option : Country_list) {
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
		}

	}
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);

		
	}

}

//
//
////checkout
////wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[control-id='ControlID-32']"))));
//driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("123");
//
////My way drop down
//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("IND");
//List<WebElement> Country_list= driver.findElements(By.cssSelector(".ta-item"));
//for(WebElement option:Country_list) {
//	if(option.getText().equalsIgnoreCase("India")) {
//		option.click();
//		break;
//	}
//}
//
////Rohit way- Action class
//
////Actions a=new Actions(driver);
////a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
////wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//driver.findElement(By.cssSelector(".action__submit")).click();
//String msg= driver.findElement(By.cssSelector(".hero-primary")).getText();
//Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));



//driver.findElement(By.cssSelector(".action__submit")).click();
//String msg= driver.findElement(By.cssSelector(".hero-primary")).getText();
//Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));