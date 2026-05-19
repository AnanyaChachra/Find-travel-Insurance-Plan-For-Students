package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ObjectImplementation.ObjectReader;
import Pages.CarInsurancePage;
import base.BaseClass;

public class CarInsuranceTest {
	WebDriver driver;
	BaseClass bc;
	ObjectReader or;
	CarInsurancePage car;
	
//	@Test
//	public void debug() {
//		System.out.println(System.getProperty("user.dir")+"/object repository/object.properties");
//	}

	@BeforeTest
	public void Setup() throws IOException {
		System.out.println("Enter the browser you need to use from the following options: \n1. chrome \n2. edge \n3. firefox");
		Scanner sc =new Scanner(System.in);
		int browserNumber=sc.nextInt();
		
		bc= new BaseClass();
		or= new ObjectReader();
		
		driver=bc.getBrowser(browserNumber);
		
		car=new CarInsurancePage(driver);
		
		driver.get(or.getBaseUrl());
		sc.close();
		
		
	}
//	
	
	@Test
	//Navigation to Car Insurance
	public void navigateToCarInsurancePageTest() throws InterruptedException {
		String expectedURL=or.getCarInsuranceLink();
		car.navigateToCarInsurance();
		
		//Applying explicit wait to wait for the webpage to load
		//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='brand-ambassador-policybazaar']")));
	    
		//Thread.sleep(3000);
		String actualURL=driver.getCurrentUrl();
		System.out.println(expectedURL +"\n" + actualURL);
		
		//verifying whether the correct page has opened or not
		Assert.assertTrue(actualURL.startsWith(expectedURL));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
}