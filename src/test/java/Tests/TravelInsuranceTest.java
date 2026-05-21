package Tests;
 
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import ObjectImplementation.ObjectReader;
import Pages.TravelInsurancePage;
import base.BaseClass;
 
public class TravelInsuranceTest {
 
    WebDriver driver;
    BaseClass bc;
    ObjectReader or;
    TravelInsurancePage ti;
    List<String> extractedList;
 
    @BeforeTest
    public void Setup() throws IOException {
        System.out.println("Enter browser:\n1.Chrome\n2.Edge\n3.Firefox");
 
        Scanner sc = new Scanner(System.in);
        int browserNumber = sc.nextInt();
 
        bc = new BaseClass();
        or = new ObjectReader();
 
        driver = bc.getBrowser(browserNumber);
        driver.get(or.getBaseUrl());
 
        sc.close();
 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
 
        ti = new TravelInsurancePage(driver);
    }
 
    @Test
    public void navigateToTravelInsurance() throws InterruptedException {
 
        ti.clickOnTravelInsurance().click();
        Thread.sleep(3000);
 
        String expectedUrl = "https://travel.policybazaar.com/?newpq=1&utm_term=newjourney&utm_content=newpq";
 
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl,
                "URL mismatch after navigation");
 
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("travel"),
                "Title does not contain 'travel'");
    }
 
    @Test(dependsOnMethods = {"navigateToTravelInsurance"})
    public void chooseDestination() throws InterruptedException {
 
        WebElement destination = ti.chooseDestination();
 
        Assert.assertTrue(destination.isDisplayed(), "Destination not visible");
 
        destination.click();
        Thread.sleep(3000);
 
        Assert.assertTrue(destination.isEnabled(), "Destination not enabled");
    }
 
    @Test(dependsOnMethods = {"chooseDestination"})
    public void chooseStartDate() throws InterruptedException {
 
        ti.chooseDate().click();
        Thread.sleep(2000);
 
        Assert.assertTrue(ti.chooseStartDate().isDisplayed(), "Start date not visible");
        Assert.assertTrue(ti.chooseEndDate().isDisplayed(), "End date not visible");
 
        ti.chooseStartDate().click();
        ti.chooseEndDate().click();
 
        ti.done().click();
        Thread.sleep(3000);
 
        Assert.assertTrue(driver.getCurrentUrl().contains("travel"),
                "Date selection failed");
    }
 
    @Test(dependsOnMethods = {"chooseStartDate"})
    public void addTravellers() throws InterruptedException {
 
        WebElement travellers = ti.selectTwo();
 
        Assert.assertTrue(travellers.isDisplayed(), "Traveller option not visible");
 
        travellers.click();
        Thread.sleep(3000);
 
        Assert.assertTrue(travellers.isEnabled(), "Traveller option not enabled");
    }
 
    @Test(dependsOnMethods = {"addTravellers"})
    public void selectAge() throws InterruptedException {
 
        ti.firstAge().click();
        Assert.assertTrue(ti.selctFirstAge().isDisplayed(), "First age dropdown failed");
        ti.selctFirstAge().click();
 
        ti.secondAge().click();
        Assert.assertTrue(ti.selctSecondAge().isDisplayed(), "Second age dropdown failed");
        ti.selctSecondAge().click();
 
        Assert.assertTrue(ti.healthCondition().isDisplayed(), "Health option not visible");
        ti.healthCondition().click();
 
        ti.done2().click();
        Thread.sleep(3000);
 
        Assert.assertTrue(driver.getCurrentUrl().contains("travel"),
                "Age selection failed");
    }
 
    @Test(dependsOnMethods = {"selectAge"})
    public void explorePages() throws InterruptedException {
 
        Assert.assertTrue(ti.explorePlans().isDisplayed(), "Explore button not visible");
 
        ti.explorePlans().click();
        Thread.sleep(3000);
 
        Assert.assertTrue(driver.getCurrentUrl().contains("travel"),
                "Explore plans navigation failed");
    }
 
    @Test(dependsOnMethods = "explorePages")
    public void extractionOfFilters() {
 
        extractedList = ti.extractFilters();
 
        Assert.assertNotNull(extractedList, "List is null");
        Assert.assertTrue(extractedList.size() > 0, "No filters extracted");
 
        System.out.println(extractedList.size() + " items extracted");
    }
 
    @Test(dependsOnMethods = "extractionOfFilters")
    public void displayOfExtractedItem() {
 
        if (extractedList == null || extractedList.size() == 0) {
            extractedList = ti.extractFilters();
        }
 
        Assert.assertFalse(extractedList.isEmpty(), "List is empty");
 
        ti.displayFilters(extractedList);
 
        System.out.println("Filters displayed successfully");
    }
 
    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
 