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
 
    public String takeScreenshot(String name) throws IOException {
 

    	File dir = new File(System.getProperty("user.dir") + "/screenshots");
    	if (!dir.exists()) {
    		dir.mkdir();
    	}

    	
    	TakesScreenshot obj = (TakesScreenshot) driver;
 
        File sourceImage = obj.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") +"/screenshots/" + name  + ".png";
        File destinationFile = new File(path);
 
        Files.copy(sourceImage, destinationFile);
 
        System.out.println("Screenshot saved at: " + destinationFile.getAbsolutePath());
        
        return path;
    }
}
