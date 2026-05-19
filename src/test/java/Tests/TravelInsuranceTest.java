package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ObjectImplementation.ObjectReader;
import Pages.TravelInsurancePage;
import base.BaseClass;

public class TravelInsuranceTest {
	WebDriver driver;
	BaseClass bc;
	ObjectReader or;
	TravelInsurancePage ti;

	@BeforeTest
	public void Setup() throws IOException {
		System.out.println("Enter the browser you need to use from the following options: \n1. chrome \n2. edge \n3. firefox");
		Scanner sc =new Scanner(System.in);
		int browserNumber=sc.nextInt();
		
		bc= new BaseClass();
		or= new ObjectReader();
		
		
		driver=bc.getBrowser(browserNumber);
		
		driver.get(or.getBaseUrl());
		sc.close();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		
		
	}
	
	
	@Test
	public void navigateToTravelInsurance() throws InterruptedException {
		ti=new TravelInsurancePage(driver);
		ti.clickOnTravelInsurance().click();
		Thread.sleep(3000);

	}
	@Test(dependsOnMethods= {"navigateToTravelInsurance"})
	public void chooseDestination() throws InterruptedException {
		ti=new TravelInsurancePage(driver);
		ti.chooseDestination().click();
		Thread.sleep(3000);
	}
	@Test(dependsOnMethods= {"chooseDestination"})
	public void chooseStartDate() throws InterruptedException {
		ti=new TravelInsurancePage(driver);
		ti.chooseDate().click();
		Thread.sleep(3000);
		ti.chooseStartDate().click();
		Thread.sleep(3000);
		ti.chooseEndDate().click();
		Thread.sleep(3000);
		ti.done().click();
		Thread.sleep(3000);
		
	}
	@Test(dependsOnMethods= {"chooseStartDate"})
	public void addTravellers() throws InterruptedException {
		ti=new TravelInsurancePage(driver);
		//ti.addTravellers().click();
		//Thread.sleep(3000);
		ti.selectTwo().click();
		Thread.sleep(3000);
	}
	@Test(dependsOnMethods= {"addTravellers"})
	public void selectAge() throws InterruptedException {
		ti=new TravelInsurancePage(driver);
		ti.firstAge().click();
		ti.selctFirstAge().click();
		ti.secondAge().click();
		ti.selctSecondAge().click();
		ti.healthCondition().click();
		//ti.submitAge().click();
		//Thread.sleep(3000);
		ti.done2().click();
		Thread.sleep(3000);

		
	}
	
	@Test(dependsOnMethods= {"selectAge"})
	public void explorePages() throws InterruptedException {
		ti = new TravelInsurancePage(driver);
		ti.explorePlans().click();
		Thread.sleep(3000);
	}
	
	
	@AfterTest
	public void tearDown() {
		if(driver!=null)
		driver.quit();
		
	}
}
