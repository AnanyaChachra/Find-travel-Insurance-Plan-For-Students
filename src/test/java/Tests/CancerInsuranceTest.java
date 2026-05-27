package Tests;
 
import java.io.File;

import java.io.IOException;

import java.time.Duration;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;
 
import ObjectImplementation.ObjectReader;

import Pages.CancerInsurancePage;

import Utilities.WaitClass;

import base.BaseClass;
 
public class CancerInsuranceTest {
 
    public WebDriver driver;

    BaseClass bc;

    ObjectReader or;

    CancerInsurancePage can;

    WebDriverWait wait;

    WaitClass w;
 
    @BeforeTest

    public void Setup() throws IOException {
 
        bc = new BaseClass();

        or = new ObjectReader();
       w = new WaitClass();
 
        driver = bc.getBrowser(1);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
 
        driver.manage().window().maximize();
 
 
        can = new CancerInsurancePage(driver);
 
 
        driver.get(or.getBaseUrl());

    }
 
    @Test(priority = 0)

    // Test Case: Validate navigation to "All Products"

    public void getAllProductsTest() throws InterruptedException {
 
        can.getAllProducts();

        //Thread.sleep(3000);

        w.waitingForThePageToLoad();
 
        boolean isVisible = wait.until(

                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='More Products']")))

                .isDisplayed();
 
        // Assertion: Verify "More Products" section is visible

        Assert.assertTrue(isVisible, "'More Products' section is not visible");

    }
 
    @Test(dependsOnMethods = "getAllProductsTest")

    // Test Case: Validate navigation to Cancer Insurance page

    public void getCancerInsuranceTest() throws InterruptedException {
 
        can.getCancerInsurance();

        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();
 
        // Assertion: Verify URL contains "cancer"

        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("cancer"),

                "Navigation to Cancer Insurance page failed");

    }
 
    @Test(dependsOnMethods = "getCancerInsuranceTest")

    // Test Case: Validate gender selection

    public void setGenderTest() throws InterruptedException {
 
        boolean gender = can.setGender();

        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();
 
        // Assertion: Verify gender is selected

        Assert.assertTrue(gender, "Gender selection failed");

    }
 
    @Test(dependsOnMethods = "setGenderTest")

    // Test Case: Validate person selection

    public void setPersonTest() throws InterruptedException {
 
        boolean person = can.setPerson();

        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();
 
        // Assertion: Verify person selection

        Assert.assertTrue(person, "Gender selection failed");

    }
 
    @Test(dependsOnMethods = { "setGenderTest", "setPersonTest" })

    // Test Case: Validate Continue button after selection

    public void clickContinueTest() throws InterruptedException {
 
        boolean continueFirst = can.clickContinue();

        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();
 
        // Assertion: Verify navigation to next step

        Assert.assertTrue(continueFirst, "Gender selection failed");

    }
 
    @Test(dependsOnMethods = "clickContinueTest")

    // Test Case: Validate age selection

    public void setAgeTest() throws InterruptedException {
 
        String age = can.setAge();
 
        // Assertion: Verify selected age

        Assert.assertEquals(age, "22 Years");
 
        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();

    }
 
    @Test(dependsOnMethods = "setAgeTest")

    // Test Case: Validate Continue after age selection

    public void clickContinueAfterAgeTest() throws InterruptedException {
 
        boolean continueSecond = can.clickContinueAfterAge();

        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();
 
        // Assertion: Verify navigation after age

        Assert.assertTrue(continueSecond, "Age selection failed");

    }
 
    @Test(dependsOnMethods = "clickContinueAfterAgeTest")

    // Test Case: Validate city selection

    public void setCityTest() throws InterruptedException {
 
        boolean city = can.setCity();
 
        // Assertion: Verify city selection

        Assert.assertTrue(city, "city selection failed");
 
        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();

    }
 
    @Test(dependsOnMethods = "setCityTest")

    // Test Case: Validate username and phone number entry

    public void setUsernameAndPasswordTest() throws InterruptedException {
 
        String[] users = can.setUsernameAndPassword();
 
        // Assertion: Verify username

        Assert.assertEquals(users[0], "ABCDEF GHIJK");
 
        // Assertion: Verify phone number

        Assert.assertEquals(users[1], "98768560");
 
        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();

    }
 
    @Test(dependsOnMethods = "setUsernameAndPasswordTest")

    // Test Case: Validate final continue action

    public void clickContinueFinalTest() throws InterruptedException {
 
        can.clickContinueFinal();

        //Thread.sleep(3000);

        w.waitingForTheElementToLoad();

    }
 
    @Test(dependsOnMethods = "setUsernameAndPasswordTest")

    // Test Case: Validate screenshot capture

    public void takeScreenshotMessageTest() throws InterruptedException, IOException {
 
        can.takeScreenshotMessage();

        //Thread.sleep(3000);

        w.waitingForThePageToLoad();
 
        String path = System.getProperty("user.dir")

                + "/screenshots/InvalidNumberMessage.png";
 
        File file = new File(path);
 
        // Assertion: Verify screenshot file exists

        Assert.assertTrue(file.exists(), "Screenshot not created");

    }
 
    @AfterTest

    public void tearDown() {
 
        driver.quit();

    }

}
 