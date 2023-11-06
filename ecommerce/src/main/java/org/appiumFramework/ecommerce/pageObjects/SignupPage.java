package org.appiumFramework.ecommerce.pageObjects;

import org.appiumFramework.ecommerce.utils.AndroidGestures;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignupPage extends AndroidGestures {
	
	AndroidDriver driver;
	
	public SignupPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/name")
	private WebElement firstNameText;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/surname")
	private WebElement lastNameText;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/phone")
	private WebElement phoneText;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/email")
	private WebElement emailText;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/password")
	private WebElement passwordText;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/btn_create_account")
	private WebElement createButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/goToLogin")
	private WebElement loginButton;
	
	public void signupFirstName(String fname) {
		firstNameText.sendKeys(fname);
		driver.hideKeyboard();
	}
	
	public void signupFirstNameClear() {
		firstNameText.clear();
	}
	
	public void signupLastName(String lname) {
		lastNameText.sendKeys(lname);
		driver.hideKeyboard();
	}
	
	public void signupLastNameClear() {
		lastNameText.clear();
	}
	
	public void signupPhone(String phone) {
		phoneText.sendKeys(phone);
		driver.hideKeyboard();
	}
	
	public void signupPhoneClear() {
		phoneText.clear();
	}
	
	public void signupEmail(String email) {
		emailText.sendKeys(email);
		driver.hideKeyboard();
	}
	
	public void signupEmailClear() {
		emailText.clear();
	}
	
	public void signupPassword(String pass) {
		passwordText.sendKeys(pass);
		driver.hideKeyboard();
	}
	
	public void signupPasswordClear() {
		passwordText.clear();
	}
	
	public void clearAllInput() {
		firstNameText.clear();
		lastNameText.clear();
		phoneText.clear();
		emailText.clear();
		passwordText.clear();
	}
	
	public void createAccountClick() {
		createButton.click();
	}
	
	public void loginClick() {
		loginButton.click();
	}

}
