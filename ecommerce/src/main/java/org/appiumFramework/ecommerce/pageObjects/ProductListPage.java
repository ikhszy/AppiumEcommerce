package org.appiumFramework.ecommerce.pageObjects;

import java.util.List;

import org.appiumFramework.ecommerce.utils.AndroidGestures;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductListPage extends AndroidGestures {
	
	AndroidDriver driver;
	
	public ProductListPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/search_edit_text")
	private WebElement searchBar;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/home_category_rv")
	private WebElement categoryScroll;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/category_item_name")
	private List<WebElement> categories;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/home_product_title")
	private List<WebElement> productsTitle;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/homeFragment")
	private WebElement homeButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/cartFragment")
	private WebElement cartButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/favoriteFragment")
	private WebElement favButton;
	
	@AndroidFindBy(id="com.mustafaunlu.ecommerce:id/profileFragment")
	private WebElement profileButton;
	
	public void searchByText(String search) {
		searchBar.sendKeys(search);
		driver.hideKeyboard();
	}
	
	public void searchClear() {
		searchBar.clear();
	}
	
	public void searchCategory(String categoryName) {
		scrollCategoryToText(categoryScroll.getAttribute("resource-id"),categoryName);
	}
	
	public void clickCategoryByText(String categoryName) {
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getText().equals(categoryName)) {
				categories.get(i).click();
			}
		}
	}
	
	public void clickItemByIndex(int index) {
		productsTitle.get(index).click();
	}
	
	public void homeClick() {
		homeButton.click();
	}
	
	public void cartClick() {
		cartButton.click();
	}
	
	public void favClick() {
		favButton.click();
	}
	
	public void profClick() {
		profileButton.click();
	}
}
