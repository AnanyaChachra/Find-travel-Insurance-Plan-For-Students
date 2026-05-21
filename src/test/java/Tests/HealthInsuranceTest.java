package Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjectImplementation.ObjectReader;
import Pages.HealthInsurancePage;
import base.BaseClass;

public class HealthInsuranceTest {
	WebDriver driver;
	BaseClass bc;
	ObjectReader or;
	HealthInsurancePage healthPage;
	List<String> extractedList;

	//@BeforeTest
	public void Setup() throws IOException {
		//System.out.println("Enter the browser you need to use from the following options: \n1. chrome \n2. edge \n3. firefox");
		//Scanner sc =new Scanner(System.in);
		//int browserNumber=sc.nextInt();
		
		//bc= new BaseClass();
		or= new ObjectReader();
		
		
		//driver=bc.getBrowser(browserNumber);
		
		driver.get(or.getBaseUrl());
		//sc.close();
		healthPage = new HealthInsurancePage(driver);
		
	}
	
	//Navigate to health insurance page
	@Test(priority=1)
    public void navigateToHeathInsurancePage() throws IOException, InterruptedException {

		driver = new ChromeDriver();
		or= new ObjectReader();
		BaseClass.driver = driver; 
		driver.get(or.getBaseUrl());

		healthPage = new HealthInsurancePage(driver);
		boolean isNavigated = healthPage.navigateToHealthInsurance(or.getHealthInsuranceLink());

	    Assert.assertTrue(isNavigated, "Navigation to health insurance page is failed");

	    System.out.println("Successfully navigated to Health Insurance page");
	}
   
	//Visibility of menu items
	 @Test(dependsOnMethods="navigateToHeathInsurancePage")
	    public void visibilityOfMenuItems() {
	        boolean isVisible = healthPage.menuItemsVisible();
	        Assert.assertTrue(isVisible, "Menu items are not visible on the page");
	        System.out.println("Menu items are visible on the page");
	    }
	 //Extracting of menu items
	    @Test(dependsOnMethods="visibilityOfMenuItems")
	    public void extractionOfMenuItems() {
	        extractedList = healthPage.extractHealthInsuranceMenu();
	        Assert.assertTrue(extractedList.size() > 0, "Failed: List is empty");
	        System.out.println(extractedList.size() + " Items extracted");
	    }
	    
	    //Displaying of extracted items 
	    //Adding them 
	    @Test(dependsOnMethods="extractionOfMenuItems")
	    public void displayOfExtractedItem() {
	        if (extractedList == null || extractedList.size() == 0) {
	            extractedList = healthPage.extractHealthInsuranceMenu();
	        }
	        healthPage.displayMenuItems(extractedList);
	        Assert.assertTrue(extractedList.size() > 0, "No data to display");
	        System.out.println("All items displayed successfully");
	    }
	
	@Test(dependsOnMethods="displayOfExtractedItem")
	public void tearDown() {
		driver.quit();
	
	}
}