package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TravelInsurancePage {

	WebDriver driver;
	By clickTravelInsurance = By.xpath("//i[@class=\"icon-bg homeIconsBg icon-bg-new ti\"]");
	public TravelInsurancePage(WebDriver driver) {
		this.driver=driver;
	}
	public WebElement clickOnTravelInsurance() {
		WebElement TravelInsurance= driver.findElement(clickTravelInsurance);
		return TravelInsurance;
	}
	
	
	
}
