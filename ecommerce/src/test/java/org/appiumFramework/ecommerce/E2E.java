package org.appiumFramework.ecommerce;

import java.time.Duration;
import java.util.ArrayList;

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
	
	@Test (priority = 2, description = "purchase multiple items from category")
	public void E2E002() throws InterruptedException {
		
		// back to cart page
		Thread.sleep(3000);
		driver.navigate().back();
		
		// remove the item
		cartPage.removeSingleItem();
		
		// Assert the toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		if(Toast.getText().equals("Product deleted from cart.") == false) {
			Assert.fail("Payment ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
		
		// back to product list page
		prodlistPage.homeClick();
		
		// find specific category
		prodlistPage.searchCategory("Tops");
		prodlistPage.clickCategoryByText("Tops");
		
		// add multiple items
		prodlistPage.scrollDownToText("Women Sweaters Wool");
		
		int size = prodlistPage.productsImg.size();
		ArrayList<Double> prodprices = new ArrayList<Double>();
		System.out.println("Total items: " + size);
		
		for(int i = 0; i < size-1; i++) {
			prodlistPage.clickItemByIndex(i);
			prodprices.add(prodInfoPage.prodPrice());
			prodInfoPage.cartClick();
			driver.navigate().back();
		}
		
		// enter the cart page
		prodlistPage.cartClick();
		
		System.out.println("Prices: " + prodprices);
		
		// verify number of items
		if(size - 1 != cartPage.itemsTotal()) {
			Assert.fail("total items didn't match");
		} else {
			Assert.assertTrue(true);
		}
		
		// Add more items to all products
		int q = 5;
		cartPage.increaseAllItemQuantity(q);
		
		// verify the total price is correct after increment
		Double dTotal = 0.0;
		for(int i = 0; i < prodprices.size(); i++) {
			dTotal = dTotal + (prodprices.get(i) * (q + 1));
		}
		System.out.println("total price from sum: " + dTotal);
		System.out.println("total price from page: " + cartPage.getTotalPrice());
		if(dTotal % cartPage.getTotalPrice() != 0) {
			Assert.fail("total price didn't match");
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
	
	@Test (priority = 3, description = "search and purchase item")
	public void E2E003() throws InterruptedException {
		
		// back to cart page
		Thread.sleep(3000);
		driver.navigate().back();
		
		// remove all items
		cartPage.removeAllItem();
		
		// back to list page and search
		prodlistPage.homeClick();
		prodlistPage.searchItem("oil");
		
		// click the first item
		prodlistPage.clickItemByIndex(1);
		
		// Assert we enter the correct page
		Assert.assertTrue(driver.findElement(AppiumBy.xpath("//*[contains(@text, 'Product Info')]")).isDisplayed());
		
		// add item to favorite
		prodInfoPage.favClick();
		
		// get the selected item price
		Double prodPrice = prodInfoPage.prodPrice();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
