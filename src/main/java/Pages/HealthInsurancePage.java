package Pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


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
	        return currentUrl.contains("health-insurance");
	    }

	    //Checking visibility of menu items
	    public boolean menuItemsVisible() {
	        menuItems = driver.findElements(By.xpath("//div[contains(@class,'leftCol')]/descendant::p"));
	        return menuItems.size() > 0;
	    }

	    //Extracting and storing menu items in list
	    public List<String> extractHealthInsuranceMenu() {
	        List<String> list = new ArrayList<>();

	        for (WebElement item : menuItems) {
	            String text = item.getText().trim();
	            if (!text.isEmpty()) {
	                list.add(text);
	            }
	        }
	        return list;
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

