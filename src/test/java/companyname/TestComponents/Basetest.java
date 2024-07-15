package companyname.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import companyname.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		// Properties Class

		Properties prop = new Properties();
		// FileInputStream //fis=new FileInputStream("D:\\eclipse
		// selenium\\Project_SeleniumFramework\\src\\main\\java\\companyname\\resources\\GlobalData.properties");
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//companyname//resources//GlobalData.properties");
		prop.load(fis);
		
		//String browserName = prop.getProperty("browser");
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("--headless");
				
			}
			driver=new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1440,900)); //full screen

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox gecko driver
			System.setProperty("webdriver.gecko.driver", "D://eclipse selenium//geckodriver-v0.34.0-win64//geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to hashmap using Jackson databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.close();
	}

}
