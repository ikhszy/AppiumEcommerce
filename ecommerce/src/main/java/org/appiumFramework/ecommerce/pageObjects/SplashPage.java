package org.appiumFramework.ecommerce.pageObjects;

import org.appiumFramework.ecommerce.utils.AndroidGestures;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SplashPage extends AndroidGestures {
	
	AndroidDriver driver;
	
	public SplashPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/splash_icon")
	private WebElement splashIcon;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/splash_txt")
	private WebElement splashText;
	
	public boolean splashIconTrue() {
		boolean icon = splashIcon.isDisplayed();
		return icon;
	}
	
	public boolean splashTextTrue() {
		boolean text = splashText.getText().equals("Shop Smart, Shop Easy, Shop with Us!");
		return text;
	}

}
