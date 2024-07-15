package companyname.Project_SeleniumFrameworkTests;

import java.io.IOException;
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
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import companyname.TestComponents.Basetest;
import companyname.TestComponents.Retry;
import companyname.pageobjects.CartPage;
import companyname.pageobjects.CheckOutPage;
import companyname.pageobjects.ConfirmationPage;
import companyname.pageobjects.LandingPage;
import companyname.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends Basetest{

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException {
		
		String productName="ZARA COAT 3";
		landingPage.loginApplication("aarif@gmaily.com", "Yasmi@786");
		AssertJUnit.assertEquals("Incorrect email or password.", landingPage.getLogInErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		String productName="ZARA COAT 3";
		ProductCatalogue productcatalogue=landingPage.loginApplication("aarif@gmail.com", "Yasmin@786");
		
		List<WebElement> products= productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartPage=productcatalogue.openCart();
		
		Boolean match=cartPage.verifyProductDisplay("Zara Coat 33");
		Assert.assertFalse(match);
	}

}
