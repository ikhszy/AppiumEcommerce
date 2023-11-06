package org.appiumFramework.ecommerce;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class E2E extends BaseClass {
	
	@Test (priority = 1, description = "Purchase single, first item")
	public void E2E001() throws InterruptedException {
		
		// login first
		lgnPage.loginEmailInput("johnjones@home.com");
		lgnPage.loginPassInput("password");
		lgnPage.signInClick();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		if(Toast.getText().equals("Welcome John") == false) {
			Assert.fail("Welcome ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
		
		// click the first item
		prodlistPage.clickItemByIndex(1);
		
		// Assert we enter the correct page
		Assert.assertTrue(driver.findElement(AppiumBy.xpath("//*[contains(@text, 'Product Info')]")).isDisplayed());
		
		// add item to favorite
		prodInfoPage.favClick();
		
		// get the selected item price
		Double prodPrice = prodInfoPage.prodPrice();
		
		// Assert toast
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast1 = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		if(Toast1.getText().equals("Added to favorite") == false) {
			Assert.fail("Favorite ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
		
		// Add item to cart
		Thread.sleep(2000);
		prodInfoPage.cartClick();
		
		// Assert toast
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast2 = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		if(Toast2.getText().equals("Added to cart") == false) {
			Assert.fail("Add cart ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
		
		// go to cart page
		prodlistPage.cartClick();
		
		// assert the product appeared
		if(!cartPage.assertCartPage()) {
			Assert.fail("Item not added");
		} else {
			Assert.assertTrue(true);
		}
		
		// compare price
		Double crtPrice = cartPage.itemPriceByIndex(0);
		System.out.println(prodPrice);
		System.out.println(crtPrice);
		if(prodPrice % crtPrice != 0) {
			Assert.fail("price doesn't match");
		} else {
			Assert.assertTrue(true);
		}
		
		// enter the payment page
		cartPage.buyClick();
		
		// assert entering correct page
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//*[contains(@text, 'Credit Card')]")));
		Assert.assertTrue(driver.findElement(AppiumBy.xpath("//*[contains(@text, 'Credit Card')]")).isDisplayed());
		
		// fill the credit card information
		pyPage.cardNameType("John Jones");
		pyPage.cardNumberType("4111111111111111");
		pyPage.cardMonthType();
		pyPage.cardYearType();
		pyPage.cardCvcType("295");
		pyPage.cardAddressType("Location unknown");
		
		pyPage.cardPay();
		
		// Assert the toast
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		WebElement Toast3 = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		if(Toast3.getText().equals("Payment successful") == false) {
			Assert.fail("Payment ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
}
