package Utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class TakeScreenshot {

	WebDriver driver;
	 
    public TakeScreenshot(WebDriver driver) {
        this.driver = driver;
    }
 
    public void takeScreenshot(String name) throws IOException {
 
        TakesScreenshot obj = (TakesScreenshot) driver;
 
        File sourceImage = obj.getScreenshotAs(OutputType.FILE);
 
        File destinationFile = new File("./screenshots/" + name  + ".png");
 
        Files.copy(sourceImage, destinationFile);
 
        System.out.println("Screenshot saved at: " + destinationFile.getAbsolutePath());
    }
}
