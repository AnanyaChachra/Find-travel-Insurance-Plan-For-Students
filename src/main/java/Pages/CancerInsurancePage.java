package Pages;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CancerInsurancePage {
	
	WebDriver driver;
	
	public CancerInsurancePage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Per page Repository
	//opening car insurance page
	By allProducts=By.xpath("(//a[normalize-space()='View all products'])[1]");
	By cancerInsurance=By.xpath("//a[@oncontextmenu=\"HandleRightClickProduct(this,'Icon_pg','click_Domestic','health_icon_pg_cancer_insurance_dom', 'https://www.policybazaar.com/health-insurance/cancer-insurance/', 'health_icon_pg_cancer_insurance_dom')\"]");
	By gender=By.cssSelector("label[for='female']");
	By whomToBuyFor=By.xpath("//label[normalize-space()='Self']");
	By continues=By.cssSelector(".button.btnHealthStep1");
	By selectAge=By.xpath("//select[@name='member_age']");
	By continueAfterAge=By.cssSelector(".button.btnHealthStep2");
	By selectCity=By.xpath("//div[@class='radio_pills dis_popular_city']//span[contains(text(),'Pune')]");



	
	//clicking on view all products to fetch all the products 
	
	public void getAllProducts() throws InterruptedException {
		WebElement products=driver.findElement(allProducts);

		Actions action=new Actions(driver);

		action.click(products).perform();
		System.out.println("View all products opened");
	}
	
	
	//Clicking on cancer Insurance
	public void getCancerInsurance() {
    
		WebElement element=driver.findElement(cancerInsurance);
		element.click();
		System.out.println("clicked on brand New");
	}
	
	//Selecting gender
	public void setGender() {
		WebElement selectGender=driver.findElement(gender);
		

		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		//WebElement citySel=wait.until(ExpectedConditions.visibilityOfElementLocated(city));
		
		if(!selectGender.isSelected()) {
			selectGender.click();
		}
		
		System.out.println("Female selected");
	}
	
	//Selecting whom to take it for
	public void setPerson() {
		WebElement person=driver.findElement(whomToBuyFor);
		
		if(!person.isSelected()) {
			person.click();
		}
		
		System.out.println("Self selected");
	}
	
	//proceeding further by clicking on continue
	public void clickContinue() {
     
		WebElement continueButton=driver.findElement(continues);
		
		if(continueButton.isEnabled()) {
			continueButton.click();
		}
		
		System.out.println("Click continue");
	}
	
	//Selecting age
	public void setAge() {
	     
		WebElement age=driver.findElement(selectAge);
		Select select=new Select(age);
		
		select.selectByContainsVisibleText("22 Years");
		
		
		System.out.println("Age selected");
	}
	
	//Clicking continue after selecting the age
	public void clickContinueAfterAge() {
	     
		WebElement continueButtonAge=driver.findElement(continueAfterAge);
		
		if(continueButtonAge.isEnabled()) {
			continueButtonAge.click();
		}
		
		System.out.println("Continue selected after age");
	}
	
	public void setCity() {

		WebElement city=driver.findElement(selectCity);
		
		if(city.isEnabled()) {
			city.click();
		}
		
		System.out.println("Pune selected");
	}
	
	
	
}
