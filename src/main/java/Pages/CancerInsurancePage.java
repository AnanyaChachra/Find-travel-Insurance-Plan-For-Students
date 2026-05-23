package Pages;



import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.TakeScreenshot;

public class CancerInsurancePage {
	
	WebDriver driver;
	TakeScreenshot ts;
	
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
	By sendName=By.cssSelector(".fullWidht.txtName");
	By sendPhoneNum=By.cssSelector("#name5");
	By continueFinal=By.cssSelector("#btnHealthStep4");

	
	
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
		System.out.println("clicked on cancer insurance");
	}
	
	//Selecting gender
	public boolean setGender() {
		WebElement selectGender=driver.findElement(gender);
		
		if(!selectGender.isSelected()) {
			selectGender.click();
			System.out.println("Female selected");
			return true;
		}
		
		return false;
	}
	
	//Selecting whom to take it for
	public boolean setPerson() {
		WebElement person=driver.findElement(whomToBuyFor);
		
		if(!person.isSelected()) {
			person.click();
			System.out.println("Self selected");
			return true;
		}
		
		
		return false;
	}
	
	//proceeding further by clicking on continue
	public boolean clickContinue() {
     
		WebElement continueButton=driver.findElement(continues);
		
		if(continueButton.isEnabled()) {
			continueButton.click();
			System.out.println("Click continue");
			return true;
		}
		
		
		return false;
	}
	
	//Selecting age
	public String setAge() {
	     
		WebElement age=driver.findElement(selectAge);
		Select select=new Select(age);
		
		select.selectByContainsVisibleText("22 Years");
		
		String ageSelected=select.getFirstSelectedOption().getText();
		
		System.out.println("Age selected");
		
		return ageSelected;
	}
	
	//Clicking continue after selecting the age
	public boolean clickContinueAfterAge() {
	     
		WebElement continueButtonAge=driver.findElement(continueAfterAge);
		
		if(continueButtonAge.isEnabled()) {
			continueButtonAge.click();
			System.out.println("Continue selected after age");
			return true;
		}
		
		return false;
	}
	
	//Selecting the city that we need to choose
	public boolean setCity() {

		WebElement city=driver.findElement(selectCity);
		
		if(city.isEnabled()) {
			city.click();
			System.out.println("Pune selected");
			return true;
		}
		
		return false;
	}
	
	//Filling the invalid details
	public String[] setUsernameAndPassword() {
	     
		WebElement username=driver.findElement(sendName);
		WebElement phoneNum=driver.findElement(sendPhoneNum);

		username.sendKeys("ABCDEF GHIJK");
		phoneNum.sendKeys("98768560");

		String[] userInfo=new String[2];

        userInfo[0] = username.getAttribute("value");
        userInfo[1] = phoneNum.getAttribute("value");

		
		System.out.println("Details sent");
		
		return userInfo;
	}
	
	//Click on continue after entering invalid details
	public void clickContinueFinal() {
	     
		WebElement continueButtonFinal=driver.findElement(continueFinal);
		

		Actions action=new Actions(driver);

		action.click(continueButtonFinal).perform();
		
		System.out.println("Continue final");
	}
	
	//Taking ScreenShot after getting the invalid output
	public void takeScreenshotMessage() throws IOException {
		ts=new TakeScreenshot(driver);
		
		ts.takeScreenshot("InvalidNumberMessage");
		System.out.println("Screenshot taken");
		
	}
}
