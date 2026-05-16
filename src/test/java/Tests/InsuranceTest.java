package Tests;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ObjectImplementation.ObjectReader;
import base.BaseClass;

public class InsuranceTest {
	WebDriver driver;
	BaseClass bc;
	ObjectReader or;

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
	}
	
	@Test
	public void getUrl() {
		System.out.println("Openend");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
}
