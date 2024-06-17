package mainPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class PageObjectClass {
	public static WebDriver driver;
	public String customerReview;
	public String userBrand;
	public String userProduct;
	public PageObjectClass(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath="//a[text()='Fashion']")
	private WebElement fashionlink;

	public WebElement getFashionlink() {
		return fashionlink;
	}
	@FindBy(xpath="//span[contains(text(),'Men')]/parent::a")
	private WebElement menFashionLink;

	public WebElement getMenFashionLink() {
		return menFashionLink;
	}
	
	@FindBy(xpath="//span[text()='Avg. Customer Review']")
	private WebElement customerReviewAverageText;

	public WebElement getCustomerReviewAverageText() {
		return customerReviewAverageText;
	}
	
	public void setCustomerReview(String value) {
		this.customerReview=value;
	}
	public WebElement getCustomerReview() {
		return driver.findElement(By.xpath("//div[contains(@aria-label,'"+customerReview+" Stars')]/parent::a"));
	}
	
	@FindBy(xpath="//span[text()='Price']")
	private WebElement priceText;
	
	public WebElement getPriceText() {
		return priceText;
	}

	@FindBy(xpath="//div[@id='priceRefinements']//input[@name='low-price']")
	private WebElement minimumPriceFilter;
	
	public WebElement getMinimumPriceFilter() {
		return minimumPriceFilter;
	}

	@FindBy(xpath="//div[@id='priceRefinements']//input[@name='high-price']")
	private WebElement maximumPriceFilter;
	
	public WebElement getMaximumPriceFilter() {
		return maximumPriceFilter;
	}
	
	@FindBy(xpath="//input[@class='a-button-input']")
	private WebElement goButton;
	
	public WebElement getGoButton() {
		return goButton;
	}
	
	@FindBy(xpath="//span[text()='Brands']")
	private WebElement brandText;
	
	public WebElement getBrandText() {
		return brandText;
	}
	
	public void setBrand(String value) {
		this.userBrand=value;
	}
	public WebElement getBrand() {
		return driver.findElement(By.xpath("//span[text()='"+userBrand+"']/parent::a"));
	}
	
	@FindBy(xpath="//div[contains(@class,'result-list')]/div[contains(@class,'small')]")
	private List<WebElement> listOfProducts;
	
	public List<WebElement> getListOfProducts() {
		return listOfProducts;
	}
	
	public void setUserProduct(String value) {
		this.userProduct=value;
	}
	public WebElement getUserProduct() {
		return driver.findElement(By.xpath("//div[contains(@class,'result-list')]/div[@data-cel-widget='search_result_"+userProduct+"']//h2/a"));
	}
	
	@FindBy(xpath="//input[contains(@id,'add-to-cart')]")
	private WebElement addToCartButton;
	
	public WebElement getAddToCartButton() {
		return addToCartButton;
	}
	
	@FindBy(xpath="//span[contains(@id,'gtc')]//a[normalize-space(text())='Go to Cart']")
	private WebElement goToCartButton;
	
	public WebElement getGoToCartButton() {
		return goToCartButton;
	}
	
	@FindBy(xpath="//span[normalize-space(text())='Cart']/../preceding-sibling::div/span[@id]")
	private WebElement cartCount;
	
	public WebElement getCartCount() {
		return cartCount;
	}
	
}
