package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	
	public WebDriver getBrowser(int webdriver) {
		WebDriver driver=null;
		
		switch(webdriver) {
		case 1:
			driver= new ChromeDriver();
			break;
			
		case 2:
			driver= new EdgeDriver();
			break;
			
		case 3:
			driver= new FirefoxDriver();
			break;
			
		default:
			System.out.println("Please enter a valid browser");
			break;
		}
		
		return driver;
		
	}
	
	
	
	
}
