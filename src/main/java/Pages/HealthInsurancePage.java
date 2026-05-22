package Pages;


import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HealthInsurancePage {

	 WebDriver driver;
	 List<WebElement> menuItems;
	 public HealthInsurancePage(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Navigation to health insurance page
	    public boolean navigateToHealthInsurance(String healthUrl) throws InterruptedException {
	        driver.get(healthUrl);
	        Thread.sleep(4000);
	        String currentUrl = driver.getCurrentUrl();
	        return currentUrl.contains("health.policybazaar.com");
	    }

	    //Checking visibility of menu items
	    public boolean menuItemsVisible() {
	        menuItems = driver.findElements(By.xpath("//div[contains(@class,'leftCol')]/descendant::p"));
	        return menuItems.size() > 0;
	    }

	    //Extracting and storing menu items in list
	    public List<String> extractHealthInsuranceMenu() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	        // Wait until tab items are visible
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//ul[contains(@class,'_topPlansWrapper__tabContainer__tabItem')]/li")
	        ));
	        
	        List<WebElement> tabs = driver.findElements(
	            By.xpath("//ul[contains(@class,'_topPlansWrapper__tabContainer__tabItem')]/li")
	        );
	        
	        List<String> list = new ArrayList<>();
	        for (WebElement tab : tabs) {
	            String text = tab.getText().trim();
	            if (!text.isEmpty()) {
	                list.add(text);
	            }
	        }
	        return list;
	    }
	    
	    //Extracting items for each type of plan
	    public Map<String, List<String>> extractAllTabPlans()throws InterruptedException {
	        Map<String, List<String>> allPlans = new HashMap<>();
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        List<WebElement> tabs = driver.findElements(By.xpath("//ul[contains(@class,'tabContainer__tabItem')]/li"));
	        for (WebElement tab : tabs) {
	            String tabName = tab.getText().trim();
	            tab.click();
	            Thread.sleep(3000);
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//b[contains(@class,'_loader')]")));
	            List<WebElement> planElements = driver.findElements(By.xpath("//div[contains(@class,'leftCol__row1')]/p[1]"));
	            
	            List<String> plans = new ArrayList<>();
	            for (WebElement plan : planElements) {
	                String name = plan.getText().trim();
	                if (!name.isEmpty()) {
	                    plans.add(name);
	                }
	            }
	            allPlans.put(tabName, plans);
	            System.out.println(tabName + ": " + plans);
	        }
	        return allPlans;
	    }

	    //Displaying extracted items
	    //Display them
	    public void displayMenuItems(List<String> list) {
	        System.out.println("Health Insurance Plans Are:");
	        for (String item : list) {
	            System.out.println("- " + item);
	        }
	        System.out.println("Total plans: " + list.size());
	    }
	    
	
}

