package companyname.Project_SeleniumFrameworkTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import companyname.pageobjects.LandingPage;
import companyname.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String productName="ZARA COAT 3";
		String productName2="ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("yusuf@gmail.com", "Yasmin@786");
		
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		List<WebElement> products= productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		

		
		
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cart h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		driver.findElement(By.cssSelector("ul li button.btn-primary:last-of-type")).click();
		
		
		//checkout
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[control-id='ControlID-32']"))));
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("123");
		
		//My way drop down
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("IND");
		List<WebElement> Country_list= driver.findElements(By.cssSelector(".ta-item"));
		for(WebElement option:Country_list) {
			if(option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
	}
		
		//Rohit way- Action class
		
//		Actions a=new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		String msg= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	
		
		
		
		
		

	}

}
