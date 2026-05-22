package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ObjectImplementation.ObjectReader;
import Pages.CancerInsurancePage;
import base.BaseClass;

public class CancerInsuranceTest {
	WebDriver driver;
	BaseClass bc;
	ObjectReader or;
	CancerInsurancePage can;
	
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
		
		driver.manage().window().maximize();
		
		can=new CancerInsurancePage(driver);
		
		driver.get(or.getBaseUrl());
		sc.close();
		
		
	}
//	
	
	@Test(priority = 0)
	//Navigation to Car Insurance
	public void getAllProductsTest() throws InterruptedException {
		//String expectedURL=or.getCarInsuranceLink();
		
		can.getAllProducts();
		
		//Applying explicit wait to wait for the webpage to load
		//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='brand-ambassador-policybazaar']")));
	    
		//Thread.sleep(3000);
//		String actualURL=driver.getCurrentUrl();
//		System.out.println(expectedURL +"\n" + actualURL);
//		
//		//verifying whether the correct page has opened or not
//		Assert.assertTrue(actualURL.startsWith(expectedURL));
//		
		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods="getAllProductsTest")
	public void getCancerInsuranceTest() throws InterruptedException {
		can.getCancerInsurance();
		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods="getCancerInsuranceTest")
	public void setGenderTest() throws InterruptedException {
		can.setGender();
		Thread.sleep(3000);
	}
	
	
	@Test(dependsOnMethods="setGenderTest")
	public void setPersonTest() throws InterruptedException {
		can.setPerson();
		Thread.sleep(3000);
	}
	
	
	@Test(dependsOnMethods={"setGenderTest","setPersonTest"})
	public void clickContinueTest() throws InterruptedException {
		can.clickContinue();
		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods="clickContinueTest")
	public void setAgeTest() throws InterruptedException {
		can.setAge();
		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods="setAgeTest")
	public void clickContinueAfterAgeTest() throws InterruptedException {
		can.clickContinueAfterAge();
		Thread.sleep(3000);
	}
	
	@Test(dependsOnMethods="clickContinueAfterAgeTest")
	public void setCityTest() throws InterruptedException {
		can.setCity();
		Thread.sleep(3000);
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
}