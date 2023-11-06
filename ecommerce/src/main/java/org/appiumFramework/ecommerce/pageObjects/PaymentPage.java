package org.appiumFramework.ecommerce.pageObjects;

import org.appiumFramework.ecommerce.utils.AndroidGestures;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class PaymentPage extends AndroidGestures {
	
	AndroidDriver driver;
	
	public PaymentPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/et_cardholder_name")
	private WebElement cardName;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/et_credit_card_number")
	private WebElement cardNumber;
	
	@AndroidFindBy(xpath="(//android.widget.ImageButton[@content-desc=\"Show dropdown menu\"])[1]")
	private WebElement cardMonth;
	
	@AndroidFindBy(xpath="(//android.widget.ImageButton[@content-desc=\"Show dropdown menu\"])[2]")
	private WebElement cardYear;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/et_cvc_code")
	private WebElement cardCvc;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/et_address")
	private WebElement cardAddress;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/btn_pay_now")
	private WebElement cardPayButton;
	
	public void cardNameType(String name) {
		cardName.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void cardNumberType(String number) {
		cardNumber.sendKeys(number);
		driver.hideKeyboard();
	}
	
	public void cardMonthType() throws InterruptedException {
		cardMonth.click();
		
		Thread.sleep(1000);
		
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(92, 1252)).perform();
	}
	
	public void cardYearType() throws InterruptedException {
		cardYear.click();
		
		Thread.sleep(1000);
		
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(496, 1143)).perform();
	}
	
	public void cardCvcType(String cvc) {
		cardCvc.sendKeys(cvc);
		driver.hideKeyboard();
	}
	
	public void cardAddressType(String address) {
		cardAddress.sendKeys(address);
		driver.hideKeyboard();
	}
	
	public void cardPay() {
		cardPayButton.click();
	}

}
