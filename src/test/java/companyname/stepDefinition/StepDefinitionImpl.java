package companyname.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import companyname.TestComponents.Basetest;
import companyname.pageobjects.CartPage;
import companyname.pageobjects.CheckOutPage;
import companyname.pageobjects.ConfirmationPage;
import companyname.pageobjects.LandingPage;
import companyname.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends Basetest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	@Given("^Log with username (.+) and password (.+)$")
	public void log_with_username_and_password(String username, String password) {
		productCatalogue=landingPage.loginApplication(username, password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void  i_add_product_to_cart(String productName) {
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_ProductName_and_submit_the_order(String productName) {
		CartPage cartPage=productCatalogue.openCart();
		
		Boolean match=cartPage.verifyProductDisplay(productName);
		System.out.println(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckout();
		checkOutPage.selectCardCountry("IND");
		confirmationPage=checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation Page")
	public void message_displayed_Confirmation_Page(String string) {
		String confirmationMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void incorrect_details_message_displayed(String string) {
		AssertJUnit.assertEquals(string, landingPage.getLogInErrorMessage());
		driver.close();
		
	}
	
	

}
