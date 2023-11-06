package org.appiumFramework.ecommerce;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LoginPageTest extends BaseClass {
	
	@Test (priority = 1, description = "Login with empty email")
	public void LGN001() {
		
		// filling the form
		lgnPage.loginPassInput(vrb.getPassword());
		lgnPage.signInClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		if(Toast.getText().equals("Please not blanks!") == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test (priority = 2, description = "Login with empty password")
	public void LGN002() {
		// filling the form
		lgnPage.loginEmailInput(vrb.getEmail());
		lgnPage.loginPassClear();
		lgnPage.signInClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		if(Toast.getText().equals("Please not blanks!") == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test (priority = 3, description = "Login with user not found")
	public void LGN003() {
		// filling the form
		lgnPage.loginEmailClear();
		lgnPage.loginPassClear();
		lgnPage.loginEmailInput(vrb.getEmail());
		lgnPage.loginPassInput(vrb.getPassword());
		lgnPage.signInClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		String toastMsg = "There is no user record corresponding to this identifier. The user may have been deleted.";
		if(Toast.getText().equals(toastMsg) == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test (priority = 4, description = "Login with incorrect email format")
	public void LGN004() {
		// filling the form
		lgnPage.loginEmailClear();
		lgnPage.loginPassClear();
		lgnPage.loginEmailInput(vrb.getFirstname());
		lgnPage.loginPassInput(vrb.getEmail());
		lgnPage.signInClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		String toastMsg = "The email address is badly formatted.";
		if(Toast.getText().equals(toastMsg) == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
}
