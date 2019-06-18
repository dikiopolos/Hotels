package tests;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HotelReservation {
	WebDriver driver;
	String browserType = "firefox";
	String city = "New York, NY";
	String checkIn = "08/01/2019";
	String checkOut = "08/08/2019";
	String numberAdults = "1";
	String numberChildren = "0";
	String child1Age = "10";
	String child2Age = "<";
	String firstName = "Elizabeth";
	String lastName = "Smith";
	String specialRequest = "Late check-in.";
	String email = "em@testemail.com";
	String phone = "212-555-6789";
	String paymentFirstName = "Elizabeth";
	String paymentLastName = "Smith-Jones";
	String creditCard = "1111222233334444";
	String cvv = "321";
	String MM = "10";
	String YY = "22";
	String zip = "90210";
	

	
	@Test
	public void hotelReservation() {
		
		//Close Popup
		//driver.findElement(By.cssSelector("button[class='cta widget-overlay-close'][aria-label='Close overlay']")).click();
		
		// Primary Search
		driver.findElement(By.id("qf-0q-destination")).clear();
		driver.findElement(By.id("qf-0q-destination")).sendKeys(city);
		driver.findElement(By.id("qf-0q-localised-check-in")).clear();
		driver.findElement(By.id("qf-0q-localised-check-in")).sendKeys(checkIn);
		driver.findElement(By.id("qf-0q-localised-check-out")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(By.id("qf-0q-localised-check-out")).sendKeys(checkOut);
		
		//Room-Occupancy selector
		driver.findElement(By.id("qf-0q-compact-occupancy")).click();
		//driver.findElement(By.cssSelector("input[id='qf-0q-compact-occupancy'][data-more-options='More options']")).click();
		//driver.findElement(By.xpath("//*[@id=\"hds-marquee\"]/div[3]/div[1]/div/form/div[4]/div/select/option[3]")).click();
		//driver.findElement(By.id("qf-0q-room-0-adults")).sendKeys(numberAdults);

		
		// Add children 
		//driver.findElement(By.id("qf-0q-room-0-children")).click();
		//driver.findElement(By.id("qf-0q-room-0-children")).sendKeys(numberChildren);
		
		//driver.findElement(By.id("qf-0q-room-0-child-0-age")).sendKeys(child1Age);
		//driver.findElement(By.id("qf-0q-room-0-child-1-age")).sendKeys(child2Age);
		
		// Submit search
		driver.findElement(By.cssSelector("button[class='cta cta-strong'][type='submit']")).click();
				
		// Select Filter criteria
		driver.findElement(By.id("f-popular-128")).click();
		driver.findElement(By.id("f-star-rating-4")).click();
		driver.findElement(By.id("filter-neighbourhood")).click();
		driver.findElement(By.id("f-nid-1737998")).click();
		
		
		// Analyze results and make selection - Select 1st hotel from filtered result
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"listings\"]/ol/li[2]/article/section/aside/a")).click();
		
		
		// Switch window to results popup
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		
		// Print Hotel name and Rating from results window
		String hotelName = driver.findElement(By.xpath("//*[@id=\"property-header\"]/div/div[1]/h1")).getText();
		String hotelRating = driver.findElement(By.xpath("//*[@id=\"property-header\"]/div/div[1]/span[2]")).getText();
		System.out.println("HOTEL: " + hotelName);
		System.out.println("RATING: " + hotelRating);
		
				
		// Select room
		driver.findElement(By.id("room-1-rateplan-1")).click();
		
		//Capture booking details
		String dailyRate = driver.findElement(By.xpath("//*[@id=\"financial-details-wrapper\"]/section/div[1]/div[2]/div[1]")).getText();
		System.out.println("DAILY RATE: " + dailyRate);
		
		String totalCost = driver.findElement(By.id("financial-details-total-booking")).getText();
		System.out.println("TOTAL COST: " + totalCost);
		
	
		//Complete Form
		driver.findElement(By.id("room-details-room-0-first-name")).sendKeys(firstName);
		driver.findElement(By.id("room-details-room-0-last-name")).sendKeys(lastName);
		driver.findElement(By.id("contact-details-email")).sendKeys(email);
		driver.findElement(By.id("contact-details-phone")).sendKeys(phone);
		driver.findElement(By.id("payment-details-first-name")).clear();
		driver.findElement(By.id("payment-details-first-name")).sendKeys(paymentFirstName);
		driver.findElement(By.id("payment-details-last-name")).clear();
		driver.findElement(By.id("payment-details-last-name")).sendKeys(paymentLastName);
		driver.findElement(By.id("payment-details-card-number")).sendKeys(creditCard);
		driver.findElement(By.id("payment-details-cvv")).sendKeys(cvv);
		driver.findElement(By.id("expiry-month")).sendKeys(MM);
		driver.findElement(By.id("expiry-year")).sendKeys(YY);
		driver.findElement(By.id("billing-details-post-code")).sendKeys(zip);
		
		
		}
			
		
	
	@BeforeMethod
	public void setUp() {
		driver = utilities.DriverFactory.open(browserType);
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		driver.get("https://www.hotels.com/");
	}
	
	@AfterMethod
	public void tearDown() {
//	driver.quit(); 
	}
}
