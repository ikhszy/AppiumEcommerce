package org.appiumFramework.ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.appiumFramework.ecommerce.pageObjects.SplashPage;
import org.appiumFramework.ecommerce.utils.Variables;
import org.appiumFramework.ecommerce.pageObjects.CartPage;
import org.appiumFramework.ecommerce.pageObjects.LoginPage;
import org.appiumFramework.ecommerce.pageObjects.SignupPage;
import org.appiumFramework.ecommerce.pageObjects.ProductListPage;
import org.appiumFramework.ecommerce.pageObjects.ProductInfoPage;
import org.appiumFramework.ecommerce.pageObjects.PaymentPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public SplashPage splPage;
	public SignupPage sgnPage;
	public LoginPage lgnPage;
	public ProductListPage prodlistPage;
	public ProductInfoPage prodInfoPage;
	public CartPage cartPage;
	public PaymentPage pyPage;
	public Faker faker;
	public Variables vrb;
	
	@BeforeClass
	public void ConfigureAppium() throws IOException {
		
		/*
		 * starting the server
		 * can add argument for hybrid app by auto-downloading chrome using this command:
		 * .withArgument(() -> "--allow-insecure","chromedriver_autodownload");
		 * probably need it, but i don't wanna break everything i've done
		 */
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Acer\\git\\localAppiumEcommerce\\ecommerce\\src\\main\\java\\org\\appiumFramework\\ecommerce\\resources\\config.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		int port = Integer.parseInt(prop.getProperty("port"));
		String deviceName = prop.getProperty("AndroidDeviceName");
		
		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Acer\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		service.start();
		
		// starting the apps
		UiAutomator2Options opt = new UiAutomator2Options();
		opt.setChromedriverExecutable("C:\\Users\\Acer\\eclipse-workspace\\appium\\src\\test\\java\\resources\\chromedriver.exe");
		opt.setDeviceName(deviceName);
		opt.setApp("C:\\Users\\Acer\\git\\localAppiumEcommerce\\ecommerce\\src\\test\\java\\org\\appiumFramework\\ecommerce\\resource\\app-debug.apk");
		opt.setPlatformName("Android");
		
		// declare driver
		driver = new AndroidDriver(new URL("http://" + ipAddress + ":" + prop.getProperty("port")), opt);
		driver.setSetting("enforceXPath1",true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// initialize page class and faker
		faker = new Faker();
		splPage = new SplashPage(driver);
		sgnPage = new SignupPage(driver);
		lgnPage = new LoginPage(driver);
		prodlistPage = new ProductListPage(driver);
		prodInfoPage = new ProductInfoPage(driver);
		cartPage = new CartPage(driver);
		pyPage = new PaymentPage(driver);
		vrb = new Variables();
		
		// set the data from faker first
		vrb.setEmail(faker.internet().emailAddress());
		vrb.setFirstname(faker.name().firstName());
		vrb.setLastname(faker.name().lastName());
		vrb.setPhone(faker.phoneNumber().cellPhone());
		vrb.setPassword(faker.internet().password());
	}
	
	@AfterClass
	// stop driver and service
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
