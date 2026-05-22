package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	    
	    //Displaying items for each type of plan
	    @Test(dependsOnMethods="displayOfExtractedItem")
	    public void extractPlanNamesForEachTab() throws InterruptedException {
	        Map<String, List<String>> allPlans = new HashMap<>();
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        List<WebElement> tabs = driver.findElements(By.xpath("//ul[contains(@class,'tabContainer__tabItem')]/li"));

	        for (int i = 0; i < tabs.size(); i++) {
	        	tabs = driver.findElements(By.xpath("//ul[contains(@class,'tabContainer__tabItem')]/li"));
	            String tabName = tabs.get(i).getText();
	            js.executeScript("arguments[0].scrollIntoView(true);", tabs.get(i));
	            Thread.sleep(1000);
	            js.executeScript("arguments[0].click();", tabs.get(i));
	            Thread.sleep(4000);
	            List<WebElement> planElements = driver.findElements(By.xpath("//div[contains(@class,'leftCol__row1')]/p[1]"));
	            List<String> plans = new ArrayList<>();
	            for (WebElement plan : planElements) {
	                String name = plan.getText().trim();
	                if (!name.isEmpty()) {
	                    plans.add(name);
	                }
	            }
	            allPlans.put(tabName, plans);
	            System.out.println("\nTab: " + tabName);
	            for (String plan : plans) {
	                System.out.println("  - " + plan);
	            }
	        }

	        Assert.assertTrue(allPlans.size() > 0, "No tabs found");
	    }
	
	@Test(dependsOnMethods="extractPlanNamesForEachTab")
	public void tearDown() {
		driver.quit();
	
	}
}