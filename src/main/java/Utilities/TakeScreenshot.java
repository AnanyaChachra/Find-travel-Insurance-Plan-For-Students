package Utilities;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.common.io.Files;

public class TakeScreenshot {

    public WebDriver driver;
     
    public TakeScreenshot(WebDriver driver) {
        this.driver = driver;
    }
 
    public String takeScreenshot(String name) throws IOException {
        // Defines target/screenshots/ directory
        String dirPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "screenshots" + File.separator;

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();   
        }

        TakesScreenshot obj = (TakesScreenshot) driver;
        File sourceImage = obj.getScreenshotAs(OutputType.FILE);

        String absolutePath = dirPath + name + ".png";
        File destinationFile = new File(absolutePath);

        Files.copy(sourceImage, destinationFile);

        System.out.println("Screenshot saved at: " + absolutePath);

        // Crucial for Spark Report: Since spark.html is in 'target/', 
        // the relative path to the image is simply 'screenshots/name.png'
        return "screenshots/" + name + ".png";
    }
}