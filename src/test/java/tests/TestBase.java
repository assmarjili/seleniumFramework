package tests;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Helper;

public class TestBase extends AbstractTestNGCucumberTests{

	public static WebDriver driver;

	public static String downloadPath = System.getProperty("user.dir") + "/Downloads";

	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}
	
	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		return options;
	}


	@BeforeSuite
	@Parameters({"browser"})
	public void  startDriver(@Optional("chrome") String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver(chromeOption());	
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(firefoxOption());
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.navigate().to("https://demo.nopcommerce.com/");	
	}

	@AfterSuite
	public void close() {
		driver.close();
	}

	@AfterMethod
	public void screenshotFailure(ITestResult result){
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Taking Screenshot");
			Helper.captureScreenshots(driver, result.getName());

		}
	}
}
