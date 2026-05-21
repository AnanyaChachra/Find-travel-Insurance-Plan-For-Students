package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarInsurancePage {
	
	WebDriver driver;
	
	public CarInsurancePage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Per page Repository
	//opening car insurance page
	By carInsurance=By.xpath("//div[@class=\"prd-block car\"]/a");

	
	//opening the car Insurance link
	public void navigateToCarInsurance() {
		WebElement element=driver.findElement(carInsurance);
		element.click();
		
	}
	
	//Proceeding to get insurance for a new car without entering a car number
	public void clickOnBrandNew() {
		
	}
	
	
	
}
