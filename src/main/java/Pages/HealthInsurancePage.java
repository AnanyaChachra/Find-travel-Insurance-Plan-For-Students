package Pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HealthInsurancePage {

	 WebDriver driver;
	    public HealthInsurancePage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void extractHealthInsuranceMenu(String healthUrl) throws IOException, InterruptedException {

	        driver.get(healthUrl);
	        Thread.sleep(4000);

	        List<WebElement> menuItems = driver.findElements(By.xpath("//div[contains(@class,'leftCol')]/descendant::p"));
	        List<String> list = new ArrayList<>();

	        System.out.println("Health Insurance Plans Are:");

	        for (WebElement item : menuItems) {
	            String text = item.getText().trim();
	            if (!text.isEmpty()) {
	                list.add(text);
	                System.out.println("-" + text);
	            }
	        }

	        System.out.println("Total plans : " + list.size());

	    }
	
}
