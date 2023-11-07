package org.appiumFramework.ecommerce.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidGestures {
	
	AndroidDriver driver;
	
	public AndroidGestures(AndroidDriver driver) {
		this.driver = driver;
	}
	
	public void waitForElement (By ele, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}
	
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(), "duration", 2000));
	}
	
	public void scrollMenuAction(String direction) {
		boolean canScrollMore; 
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", direction,
			    "percent", 3.0
			));
		} while(canScrollMore);
	}
	
	public void scrollDownToTextAndClick(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));")).click();
	}
	
	public void scrollDownToText(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
	}
	
	public void swipeElementToDirectionAction(WebElement ele, String direction) {
		((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId",((RemoteWebElement)ele).getId(), 
				"direction", direction, 
				"percent", 0.75));
	}
	
	public void dragElementAction(WebElement ele, int endX, int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", endX,
			    "endY", endY
			));
	}
	
	public static boolean waitForPresence(AndroidDriver driver, Duration timeLimitInSeconds, WebElement ele){
		boolean isElementPresent;
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
			wait.until(ExpectedConditions.visibilityOf(ele));
			isElementPresent = ele.isDisplayed();
			return isElementPresent;	
		}catch(Exception e){
			isElementPresent = false;
			System.out.println(e.getMessage());
			return isElementPresent;
		}
	}
	
	public void ScrollHorizontal(String id, String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."
                + "resourceId(\"" + id + "\"))"
                + ".setAsHorizontalList().scrollIntoView(new UiSelector().textMatches(\"" + text + "\"))"));
	}
	
	public void scrollCategoryToText(String id, String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."
                + "resourceId(\"" + id + "\"))"
                + ".setAsHorizontalList().scrollIntoView(new UiSelector().textMatches(\"" + text + "\"))"));
	}
	
	public void threadSleep(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
