package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ObjectImplementation.ObjectReader;
import Pages.TravelInsurancePage;
import Utilities.WaitClass;
import base.BaseClass;
 
public class VerifyFilters_TravelInsurance {
 
    public WebDriver driver;
    BaseClass bc;
    ObjectReader or;
    TravelInsurancePage ti;
    List<String> extractedList;
    WaitClass wait;

    @BeforeTest
    public void Setup() throws IOException {
 
        bc = new BaseClass();
        or = new ObjectReader();
        wait = new WaitClass();
 
        // Launch selected browser
        driver = bc.getBrowser(1);
 
        // Navigate to base URL
        driver.get(or.getBaseUrl());

        // Configure implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
 
        // Maximize browser window
        driver.manage().window().maximize();
 
        // Initialize page object
        ti = new TravelInsurancePage(driver);
    }

    @Test
    public void navigateToTravelInsurance() throws InterruptedException {
 
        // Click on travel insurance link
        ti.clickOnTravelInsurance().click();
        wait.waitingForThePageToLoad();
 
        // Expected URL after navigation
        String expectedUrl = "https://travel.policybazaar.com/?newpq=1&utm_term=newjourney&utm_content=newpq";
 
        // Validate URL
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl,
                "URL mismatch after navigation");
 
        // Validate page title contains keyword
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("travel"),
                "Title does not contain 'travel'");
    }
 
    @Test(dependsOnMethods = {"navigateToTravelInsurance"})
    public void chooseDestination() throws InterruptedException {
 
        // Locate destination field
        WebElement destination = ti.chooseDestination();
 
        // Verify element is visible
        Assert.assertTrue(destination.isDisplayed(), "Destination not visible");
        // Click destination
        if(!destination.isSelected())
        	destination.click();
        wait.waitingForTheElementToLoad();
        
        // Verify element is enabled
        Assert.assertTrue(destination.isEnabled(), "Destination not enabled");
    }

    @Test(dependsOnMethods = {"chooseDestination"})
    public void chooseStartDate() throws InterruptedException {
 
        // Open date picker
        ti.chooseDate().click();
        wait.waitingForTheElementToLoad();
 
        // Validate date elements availability
        Assert.assertTrue(ti.chooseStartDate().isDisplayed(), "Start date not visible");
        Assert.assertTrue(ti.chooseEndDate().isDisplayed(), "End date not visible");
 
        // Select start and end dates
        ti.chooseStartDate().click();
        ti.chooseEndDate().click();
 
        // Confirm selection
        ti.done().click();
        wait.waitingForTheElementToLoad();
 
        // Validate URL still contains travel keyword
        Assert.assertTrue(driver.getCurrentUrl().contains("travel"),
                "Date selection failed");
    }

    @Test(dependsOnMethods = {"chooseStartDate"})
    public void addTravellers() throws InterruptedException {
 
        // Select two travellers option
        WebElement travellers = ti.selectTwo();
 
        // Validate visibility
        Assert.assertTrue(travellers.isDisplayed(), "Traveller option not visible");
 
        // Click option
        travellers.click();
        wait.waitingForTheElementToLoad();
 
        // Validate usability
        Assert.assertTrue(travellers.isEnabled(), "Traveller option not enabled");
    }

    @Test(dependsOnMethods = {"addTravellers"})
    public void selectAge() throws InterruptedException {
 
        // Select first traveller age
        ti.firstAge().click();
        Assert.assertTrue(ti.selctFirstAge().isDisplayed(), "First age dropdown failed");
        ti.selctFirstAge().click();
 
        // Select second traveller age
        ti.secondAge().click();
        Assert.assertTrue(ti.selctSecondAge().isDisplayed(), "Second age dropdown failed");
        ti.selctSecondAge().click();
 
        // Select health condition
        Assert.assertTrue(ti.healthCondition().isDisplayed(), "Health option not visible");
        ti.healthCondition().click();
 
        // Confirm selections
        ti.done2().click();
        wait.waitingForTheElementToLoad();
 
        // Validate navigation success
        Assert.assertTrue(driver.getCurrentUrl().contains("travel"),
                "Age selection failed");
    }

    @Test(dependsOnMethods = {"selectAge"})
    public void explorePages() throws InterruptedException {
 
        // Verify explore button visibility
        Assert.assertTrue(ti.explorePlans().isDisplayed(), "Explore button not visible");
 
        // Click explore plans
        ti.explorePlans().click();
        wait.waitingForThePageToLoad();
 
        // Validate navigation
        Assert.assertTrue(driver.getCurrentUrl().contains("travel"),
                "Explore plans navigation failed");
    }

    @Test(dependsOnMethods = "explorePages")
    public void extractionOfFilters() {
 
        // Extract filter values
        extractedList = ti.extractFilters();
 
        // Validate list
        Assert.assertNotNull(extractedList, "List is null");
        Assert.assertTrue(extractedList.size() > 0, "No filters extracted");
 
        // Print number of items
        System.out.println(extractedList.size() + " items extracted");
    }

    @Test(dependsOnMethods = "extractionOfFilters")
    public void displayOfExtractedItem() {
 
        // Re-fetch list if empty
        if (extractedList == null || extractedList.size() == 0) {
            extractedList = ti.extractFilters();
        }
 
        // Ensure list is not empty
        Assert.assertFalse(extractedList.isEmpty(), "List is empty");
 
        // Display filters
        ti.displayFilters(extractedList);
 
        System.out.println("Filters displayed successfully");
    }
    

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
 