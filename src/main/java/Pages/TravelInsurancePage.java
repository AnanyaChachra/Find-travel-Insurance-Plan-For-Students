package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TravelInsurancePage {

	WebDriver driver;

	By clickTravelInsurance = By.xpath("//i[@class=\"icon-bg homeIconsBg icon-bg-new ti\"]");
	By clickOnUSA = By.xpath("//img[@alt='USA_Image']");
	By clickOnDate = By.xpath("//div[@class=\"newPq_duration_wrap__dateCol\"][1]//span");
	By clickOnStartDate = By.xpath("//span[text()='20']");
	By clickOnEndDate = By.xpath("//span[text()='31']");
	By clickOnAddTraveller = By.xpath("//a[text()='Add']");
	By clickOnTwo = By.xpath("//div[@class='memSelectRadioWrapper__radio'][2]");
	By clickOnDropDown1 = By.xpath("//div[@class='inputRow select '][1]");
	By selectAge1 = By.xpath("//label[text()='22 years']");
	By clickOnDropDown2 = By.xpath("//div[@class='inputRow select '][2]");
	By selectAge2 = By.xpath("//label[text()='21 years']");
	By checkhealth = By.xpath("//label[text()='No']");
	By clickOnSubmit = By.xpath("//button[text()='Continue']");
	By clickDone = By.xpath("//*[@id=\"modal-root\"]/section/article/div/div/div[2]/div[3]/div/button");
	By clickDone2 = By.xpath("//*[@id=\"modal-root\"]/section/article/div/div/div/div[3]/div/button");
	By clickOnExplore = By.xpath("//button[@class='travel_main_cta']");
	By clickOnSortBy = By.xpath("//a[text()='Sort by']");

	
	
	
	public TravelInsurancePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement clickOnTravelInsurance() {
		WebElement TravelInsurance= driver.findElement(clickTravelInsurance);
		return TravelInsurance;
	}
	public WebElement chooseDestination() {
		WebElement Destination= driver.findElement(clickOnUSA);
		return Destination;
	}
	public WebElement chooseDate() {
		WebElement Date= driver.findElement(clickOnDate);
		return Date;
	}
	public WebElement chooseStartDate() {
		WebElement StartDate= driver.findElement(clickOnStartDate);
		return StartDate;
	}
	public WebElement chooseEndDate() {
		WebElement EndDate= driver.findElement(clickOnEndDate);
		return EndDate;
	}
	public WebElement addTravellers() {
		WebElement AddTraveller= driver.findElement(clickOnAddTraveller);
		return AddTraveller;
	}
	public WebElement selectTwo() {
		WebElement SelectTwo= driver.findElement(clickOnTwo);
		return SelectTwo;
	}
	public WebElement firstAge() {
		WebElement FirstAge= driver.findElement(clickOnDropDown1);
		return FirstAge;
	}
	public WebElement selctFirstAge() {
		WebElement SelectFirstAge= driver.findElement(selectAge1);
		return SelectFirstAge;
	}
	public WebElement secondAge() {
		WebElement SecondAge= driver.findElement(clickOnDropDown2);
		return SecondAge;
	}
	public WebElement selctSecondAge() {
		WebElement SelectSecondAge= driver.findElement(selectAge2);
		return SelectSecondAge;
	}
	public WebElement healthCondition() {
		WebElement HealthCondition= driver.findElement(checkhealth);
		return HealthCondition;
	}
	public WebElement submitAge() {
		WebElement SubmitAge= driver.findElement(clickOnSubmit);
		return SubmitAge;
	}
	public WebElement done() {
		WebElement Done= driver.findElement(clickDone);
		return Done;
	}
	public WebElement done2() {
		WebElement Done2= driver.findElement(clickDone2);
		return Done2;
	}
	public WebElement explorePlans() {
		WebElement ExplorePlans= driver.findElement(clickOnExplore);
		return ExplorePlans;
	}
	public WebElement sortBy() {
		WebElement SortBy= driver.findElement(clickOnSortBy);
		return SortBy;
	}
	
}
