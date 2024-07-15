package companyname.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import companyname.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver;
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> ordersProductNames;
	
	@FindBy(css="ul li button.btn-primary:last-of-type")
	WebElement checkoutEle;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyOrderHistory(String productName) {
		Boolean match=ordersProductNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	

	
}

//List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cart h3"));
// Boolean match=cartProducts.stream().anyMatch(cartProduct ->
// cartProduct.getText().equalsIgnoreCase(productName));
// Assert.assertTrue(match);


