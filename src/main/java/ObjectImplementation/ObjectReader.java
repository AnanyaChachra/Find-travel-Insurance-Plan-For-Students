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
		String path =System.getProperty("user.dir") + "/object repository/object.properties";
		
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
	
	public void getCarImage() {
		String xpath=pro.getProperty("policyBazaar.carInsurance.carimage");
	}
}
