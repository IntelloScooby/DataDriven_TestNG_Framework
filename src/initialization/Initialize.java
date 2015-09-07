package initialization;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelOperations;

public class Initialize {

	public static Properties config = new Properties();
	public static Properties object = new Properties();
	public static ExcelOperations excel = null;
	public static WebDriver driver = null;
	public static Logger appLog = Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public static void init() throws Exception{
		
		if (driver==null){
			
			excel = new ExcelOperations(System.getProperty("user.dir")+"//src//properties//TestData.xlsx");
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//properties//config.properties");			
			config.load(fis);
			appLog.debug("Config file loaded successfully");
			
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//properties//object.properties");
			object.load(fis);
			appLog.debug("Object file loaded successfully");
			
			//Determining the browser
			String browserProperty = config.getProperty("browser");			
			switch (browserProperty){
			
			case "firefox":
				driver = new FirefoxDriver();
				break;
				
			case "chrome":
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//chromedriver.exe");
				driver = new ChromeDriver();
				break;
	
			default:			
				throw new Exception ("Invalid browser name in config.properties file");
			
			}
			
			//set implicit wait time
			driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
			appLog.debug("Implicit wait time set successfully");
			
		}
		
	}
	
	
	@AfterSuite
	public void close (){
		driver.quit();
		appLog.debug("WebDriver closed successfully");
	}
	
	
}
