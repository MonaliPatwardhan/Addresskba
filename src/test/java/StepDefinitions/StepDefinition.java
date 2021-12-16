package StepDefinitions;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
*/
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import cucumber.api.DataTable;

public class StepDefinition {
	WebDriver driver;
	public static String discountText;

	@Before
	public void setUp() {
		/*
		 * System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
		 * FirefoxBinary firefoxBinary = new FirefoxBinary();
		 * firefoxBinary.addCommandLineOptions("--headless"); FirefoxProfile profile=new
		 * FirefoxProfile(); FirefoxOptions firefoxOptions = new FirefoxOptions();
		 * firefoxOptions.setBinary(firefoxBinary); firefoxOptions.setProfile(profile);
		 * driver=new FirefoxDriver(firefoxOptions);
		 */
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		loadURL();
	}

	@Given("^Launch the application$")
	public void loadURL() {
		String baseUrl = "https://webapps.tekstac.com/AddressBook/";
		driver.get(baseUrl);
		final WebElement heading = driver.findElement(By.xpath("/html/body/center/h2"));

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (heading.getText().equals("Address Book"));
			}
		};

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(expectation);
	}

	@When("^address details are added$")
	public void testAddressBook(DataTable addressValues) {

		// Write the code to handle Data Table

		// Please fill the required code
		List<List<String>> data = addressValues.raw();
		driver.findElement(By.id("nickname")).sendKeys(data.get(0).get(0));
		driver.findElement(By.id("contact")).sendKeys(data.get(0).get(1));
		driver.findElement(By.id("company")).sendKeys(data.get(0).get(2));
		driver.findElement(By.id("city")).sendKeys(data.get(0).get(3));
		driver.findElement(By.id("country")).sendKeys(data.get(0).get(4));
		driver.findElement(By.id("type")).sendKeys(data.get(0).get(5));
		driver.findElement(By.id("add")).click();

	}

	@When("^addresses are deleted$")
	public void deleteAddressBook(DataTable addressValues) {
		// Please fill the required code

		driver.findElement(By.id("radio0")).click();
		driver.findElement(By.id("delete")).click();

		List<List<String>> data = addressValues.raw();
		WebElement table = driver.findElement(By.xpath("//*[@id=\"result\"]/table"));

    	  if (table.getText().contains(data.get(0).get(0).replace(" ", "")))
			  System.out.println("Nickname not deleted from table");
			  
		  if (table.getText().contains(data.get(0).get(1).replace(" ", "")))
			  System.out.println("Contactname not deleted from table");
		  
		  if (table.getText().contains(data.get(0).get(2).replace(" ", "")))
			  System.out.println("Company not deleted from table");
		  
		  if (table.getText().contains(data.get(0).get(3).replace(" ", "")))
			  System.out.println("City not deleted from table");
		  
		  if (table.getText().contains(data.get(0).get(4).replace(" ", "")))
			  System.out.println("Country not deleted from table");
		  
		  if (table.getText().contains(data.get(0).get(5).replace(" ", "")))
			  System.out.println("Type not deleted from table");
		  
	}


	@Then("^all addresses should be displayed to the right$")
	public void validateResult(DataTable expectedAddresses) {

		// Please fill the required code
		List<List<String>> data = expectedAddresses.raw();
		
		discountText = driver.findElement(By.xpath("//*[@id=\"result\"]/table/tbody/tr[2]/td[1]")).getText();
		if (discountText.contains(data.get(0).get(0)))
			System.out.println("Nickname added in table");
		else
			System.out.println("Nickname not added in table");
		
		discountText = driver.findElement(By.xpath("//*[@id=\"result\"]/table/tbody/tr[2]/td[2]")).getText();
		if (discountText.contains(data.get(0).get(1)))
			System.out.println("Contactname added in table");
		else
			System.out.println("Contactname not added in table");

		discountText = driver.findElement(By.xpath("//*[@id=\"result\"]/table/tbody/tr[2]/td[3]")).getText();
		if (discountText.contains(data.get(0).get(2)))
			System.out.println("Company added in table");
		else
			System.out.println("Company not added in table");

		discountText = driver.findElement(By.xpath("//*[@id=\"result\"]/table/tbody/tr[2]/td[4]")).getText();
		if (discountText.contains(data.get(0).get(3)))
			System.out.println("City added in table");
		else
			System.out.println("City not added in table");

		discountText = driver.findElement(By.xpath("//*[@id=\"result\"]/table/tbody/tr[2]/td[5]")).getText();
		if (discountText.contains(data.get(0).get(4)))
		
			System.out.println("Country added in table");
		else
			System.out.println("Country not added in table");

		discountText = driver.findElement(By.xpath("//*[@id=\"result\"]/table/tbody/tr[2]/td[6]")).getText();
		if (discountText.contains(data.get(0).get(5)))
			System.out.println("Type added in table");
		else
			System.out.println("Type not added in table");

	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
