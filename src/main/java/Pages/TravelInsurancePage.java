package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TravelInsurancePage {

	WebDriver driver;
	By clickTravelInsurance = By.xpath("/html/body/main/div[2]/section/div[7]/a/div[1]/div/i");
	public TravelInsurancePage(WebDriver driver) {
		this.driver=driver;
	}
	public WebElement clickOnTravelInsurance() {
		WebElement TravelInsurance= driver.findElement(clickTravelInsurance);
		return TravelInsurance;
	}
	
	
	
}
