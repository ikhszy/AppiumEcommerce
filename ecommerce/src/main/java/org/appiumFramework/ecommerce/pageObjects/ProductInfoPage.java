package org.appiumFramework.ecommerce.pageObjects;

import org.appiumFramework.ecommerce.utils.AndroidGestures;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductInfoPage extends AndroidGestures {
	
	AndroidDriver driver;
	
	public ProductInfoPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(className="android.widget.ImageButton")
	private WebElement backArrow;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/detail_item_img")
	private WebElement prodImage;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/detail_product_title")
	private WebElement prodTitle;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/detail_product_description")
	private WebElement prodDescription;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/detail_product_price")
	private WebElement prodPrice;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/detail_product_rating_txt")
	private WebElement prodRating;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/btn_add_to_cart")
	private WebElement addToCartButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/btn_add_to_fav")
	private WebElement addToFavouriteButton;

	public void backToProductList() {
		backArrow.click();
	}
	
	public void cartClick() {
		addToCartButton.click();
	}
	
	public void favClick() {
		addToFavouriteButton.click();
	}
	
	public String prodTitle() {
		String title = prodTitle.getText();
		return title;
	}
	
	public Double prodPrice() {
		String price = prodPrice.getText().substring(0, prodPrice.getText().length() - 3);
		Double dPrice = Double.valueOf(price);
		return dPrice;
	}
}
