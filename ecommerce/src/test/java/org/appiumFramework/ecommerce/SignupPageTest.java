package org.appiumFramework.ecommerce;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class SignupPageTest  extends BaseClass {


	@Test (priority = 1, description = "Signup with empty first name")
	public void SGN001() {
		
		// enter the signup page
		lgnPage.signUpClick();
		
		// filling the form
		sgnPage.signupLastName(vrb.getLastname());
		sgnPage.signupPhone(vrb.getPhone());
		sgnPage.signupEmail(vrb.getEmail());
		sgnPage.signupPassword(vrb.getPassword());
		
		sgnPage.createAccountClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		String toastMsg = "Please do not blank fields!";
		if(Toast.getText().equals(toastMsg) == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test (priority = 2, description = "Signup with empty last name")
	public void SGN002() {
		
		// fill the form
		sgnPage.signupFirstName(vrb.getFirstname());
		sgnPage.signupLastNameClear();
		
		sgnPage.createAccountClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		String toastMsg = "Please do not blank fields!";
		if(Toast.getText().equals(toastMsg) == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test (priority = 3, description = "Signup with empty email")
	public void SGN003() {
		
		// fill the form
		sgnPage.signupLastName(vrb.getLastname());
		sgnPage.signupEmailClear();
		
		sgnPage.createAccountClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		String toastMsg = "Please do not blank fields!";
		if(Toast.getText().equals(toastMsg) == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test (priority = 4, description = "Signup with empty password")
	public void SGN004() {
		
		// fill the form
		sgnPage.signupEmail(vrb.getEmail());
		sgnPage.signupPasswordClear();
		
		sgnPage.createAccountClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		String toastMsg = "Please do not blank fields!";
		if(Toast.getText().equals(toastMsg) == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test (priority = 5, description = "Signup with invalid email format")
	public void SGN005() {
		// fill the form
		sgnPage.signupPassword(vrb.getPassword());
		sgnPage.signupEmailClear();
		sgnPage.signupEmail(vrb.getFirstname());
		
		sgnPage.createAccountClick();
		
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
	
	@Test (priority = 6, description = "Signup with invalid password format")
	public void SGN006() {
		
		// fill the form
		sgnPage.signupEmailClear();
		sgnPage.signupEmail(vrb.getEmail());
		sgnPage.signupPasswordClear();
		sgnPage.signupPassword("x");
		
		sgnPage.createAccountClick();
		
		// Assert toast
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]")));
		
		WebElement Toast = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.Toast[1]"));
		String toastMsg = "The given password is invalid. [ Password should be at least 6 characters ]";
		if(Toast.getText().equals(toastMsg) == false) {
			Assert.fail("ToastMessage is wrong");
		} else {
			Assert.assertTrue(true);
		}
	}
		
}
