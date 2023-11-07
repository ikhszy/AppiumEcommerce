package org.appiumFramework.ecommerce.pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.appiumFramework.ecommerce.utils.AndroidGestures;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidGestures {
	
	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/rv_cart_products")
	private WebElement cartBox;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/cart_item_img_view")
	private List<WebElement> cartImage;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/cart_item_title")
	private List<WebElement> cartName;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/cart_item_price")
	private List<WebElement> cartPrice;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/cart_item_btn_inc")
	private List<WebElement> plusButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/cart_item_btn_dec")
	private List<WebElement> minusButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/cart_item_quantity")
	private List<WebElement> cartQuantity;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/tv_total_amount")
	private WebElement cartTotalAmount;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/btn_buy_now")
	private WebElement buyButton;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement yesDelete;
	
	@AndroidFindBy(id="android:id/button2")
	private WebElement noDelete;
	
	public ArrayList<String> tempString;
	public ArrayList<Double> tempDouble;
	
	public int itemsTotal() {
		int total = cartName.size();
		return total;
	}
	
	public String itemNameByIndex(int index) {
		String name = cartName.get(index).getText();
		return name;
	}
	
	public ArrayList<String> itemNameAll() {
		int size = cartName.size();
		
		for(int i = 0; i < size; i++) {
			tempString.add(cartName.get(i).getText());
		}
		return tempString;
	}
	
	public Double itemPriceByIndex(int index) {
		String priceStr = cartPrice.get(index).getText().substring(0, cartPrice.get(index).getText().length() - 3);
		Double price = Double.valueOf(priceStr);
		return price;
	}
	
	public ArrayList<Double> itemPriceAll() {
		for(int i = 0; i < cartPrice.size(); i++) {
			String priceStr = cartPrice.get(i).getText().substring(0, cartPrice.get(i).getText().length() - 3);
			tempDouble.add(Double.valueOf(priceStr));
			priceStr = "";
		}
		return tempDouble;
	}
	
	public void increaseItemQuantityByIndex(int index, int repeat) throws InterruptedException {
		for(int i = 1; i <= repeat; i++) {
			plusButton.get(index).click();
			Thread.sleep(1000);
		}
	}
	
	public void increaseAllItemQuantity(int repeat) throws InterruptedException {
		for(int i = 0; i < plusButton.size(); i++) {
			for(int j = 1; j <= repeat; j++) {
				plusButton.get(i).click();
				Thread.sleep(1000);
			}
		}
	}
	
	public void decreaseItemQuantityByIndex(int index, int repeat) throws InterruptedException {
		for(int i = 1; i <= repeat; i++) {
			minusButton.get(index).click();
			Thread.sleep(1000);
		}
	}
	
	public void decreaseAllItemQuantity(int repeat) throws InterruptedException {
		for(int i = 0; i < minusButton.size(); i++) {
			for(int j = 1; j <= repeat; j++) {
				minusButton.get(i).click();
				Thread.sleep(1000);
			}
		}
	}
	
	public String quantityValueByIndex(int index) {
		String q = cartQuantity.get(index).getText();
		return q;
	}
	
	public Double getTotalPrice() {
		Double price =  Double.valueOf(cartTotalAmount.getText());
		return price;
	}
	
	public void buyClick() {
		buyButton.click();
	}
	
	public void removeSingleItem() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		longPressAction(cartName.get(0));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.mustafaunlu.ecommerce:id/parentPanel")));
		
		yesDelete.click();
	}
	
	public void removeItemByIndex(int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		longPressAction(cartName.get(index));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.mustafaunlu.ecommerce:id/parentPanel")));
		
		yesDelete.click();
	}
	
	public void removeAllItem() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		for(int i = cartName.size() - 1; i >= 0; i--) {
			longPressAction(cartName.get(i));
			
			wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.mustafaunlu.ecommerce:id/parentPanel")));
			yesDelete.click();
			
			Thread.sleep(1000);
		}
	}
	
	public boolean assertCartPage() {
		boolean verify = cartName.size() > 0;
		return verify;
	}
}
