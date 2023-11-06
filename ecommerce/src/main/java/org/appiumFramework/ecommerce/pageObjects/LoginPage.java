package org.appiumFramework.ecommerce.pageObjects;

import org.appiumFramework.ecommerce.utils.AndroidGestures;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends AndroidGestures {
	
	AndroidDriver driver;
	
	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/username")
	private WebElement emailText;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/password")
	private WebElement passwordText;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/btn_forgot_pw")
	private WebElement forgotButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/btn_sign_in")
	private WebElement signInButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/btn_sign_up")
	private WebElement signUpButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/standard_bottom_sheet")
	private WebElement sheet;
	
	public void loginEmailInput(String email) {
		emailText.sendKeys(email);
		driver.hideKeyboard();
	}
	
	public void loginEmailClear() {
		emailText.clear();
	}
	
	public void loginPassInput(String pass) {
		passwordText.sendKeys(pass);
		driver.hideKeyboard();
	}
	
	public void loginPassClear() {
		passwordText.clear();
	}
	
	public void forgotPasswordClick() {
		forgotButton.click();
	}
	
	public void signInClick() {
		signInButton.click();
	}
	
	public void signUpClick() {
		signUpButton.click();
	}
	
	public boolean sheetVerify() {
		boolean verify = sheet.isDisplayed();
		return verify;
	}

}
