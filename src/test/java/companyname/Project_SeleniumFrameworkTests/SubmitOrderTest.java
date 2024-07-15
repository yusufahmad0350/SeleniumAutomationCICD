package companyname.Project_SeleniumFrameworkTests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import companyname.TestComponents.Basetest;
import companyname.pageobjects.CartPage;
import companyname.pageobjects.CheckOutPage;
import companyname.pageobjects.ConfirmationPage;
import companyname.pageobjects.LandingPage;
import companyname.pageobjects.OrdersPage;
import companyname.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends Basetest{
	
	String productName="ZARA COAT 3";
	String productName2="ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups= "Purchage") 
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException{
		
	//public void submitOrder(String email, String password, String productName) throws IOException {
		
		ProductCatalogue productcatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products= productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productcatalogue.openCart();
		
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckout();
		checkOutPage.selectCardCountry("IND");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		String confirmationMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	//Another Test to check the same item is present in order history page
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productcatalogue=landingPage.loginApplication("yusuf@gmail.com", "Yasmin@786");
		OrdersPage orderPage= productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderHistory("ZARA COAT 3"));
		
		
	}
	

	
	
	
	//Extent Reports
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
//		HashMap<String, String> map=new HashMap<String, String>();
//		map.put("email", "yusuf@gmail.com");
//		map.put("password", "Yasmin@786");
//		map.put("product","ZARA COAT 3" );
//		
//		HashMap<String, String> map1=new HashMap<String, String>();
//		map1.put("email", "danish123@gmail.com");
//		map1.put("password", "Yasmin@786");
//		map1.put("product","ADIDAS ORIGINAL" );
		
		List<HashMap<String,String>> data1= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\companyname\\data\\PurchageOrder.json");
		//List<HashMap<String,String>> data= getJsonDataToMap("D:\\eclipse selenium\\Project_SeleniumFramework\\src\\test\\java\\companyname\\data");
		
		
		
		//return new Object[][] {{map},{map1}};
		return new Object[][] {{data1.get(0)},{data1.get(1)}};
		
	}
	
	
	
	
	
	
	
	
//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"yusuf@gmail.com", "Yasmin@786","ZARA COAT 3"},{"danish123@gmail.com","Yasmin@786","ADIDAS ORIGINAL" }};
//	}
	

}
