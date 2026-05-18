package ObjectImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class ObjectReader {

	Properties pro;
	FileInputStream file;
	
	public ObjectReader() throws IOException {
		pro=new Properties();
		String path ="C:\\Users\\2487453\\eclipse-workspace\\Find-travel-Insurance-Plan-For-Students\\object repository\\object.properties";
		//"C:\Users\2487453\eclipse-workspace\Find-travel-Insurance-Plan-For-Students\object repository\object.properties"
		file= new FileInputStream(path);
		pro.load(file);
	}
	
	public String getBaseUrl() {
		String mainLink=pro.getProperty("BaseUrl");
		return mainLink;
	}
	
	public String getTravelInsuranceLink() {
		String travelLink=pro.getProperty("policyBazaar.travelLink");
		return travelLink;
	}
	
	public String getCarInsuranceLink() {
		String carLink=pro.getProperty("policyBazaar.carLink");
		return carLink;
	}
	
	public String getHealthInsuranceLink() {
		String healthLink=pro.getProperty("policyBazaar.HealthLink");
		return healthLink;
	}
}
