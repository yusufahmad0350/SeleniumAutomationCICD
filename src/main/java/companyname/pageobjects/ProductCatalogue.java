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

import companyname.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

//	List<WebElement> list_of_products= driver.findElements(By.cssSelector(".mb-3"));
//	
//	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	
	// Page factory
	@FindBy(css=".mb-3")
	List<WebElement> list_of_products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	
	By productsby= By.cssSelector(".mb-3");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
			
	
	public List<WebElement> getProductList() {
		
		waitForElementVisible(productsby);
		
		return list_of_products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod= getProductList().stream().filter(product-> 
		product .findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
	public void addProductToCart(String productName) {
		
		WebElement prod= getProductByName(productName);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementVisible(toastMessage);
		waitForElementToDissapear(spinner);
	}


	
}
