package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.asserts.SoftAssert;
import io.cucumber.java.en.*;
import mainPackage.PageObjectClass;
import mainPackage.ReusableMethods;
import io.qameta.allure.Step;

public class Stepdefinitions extends ReusableMethods{

	private Shared_State sharedState;
	public static WebDriver driver;
	public static PageObjectClass pageObject;
	public SoftAssert softAssert= new SoftAssert();

	public Stepdefinitions() {}

	private static final Logger logger = LoggerFactory.getLogger(Stepdefinitions.class);
	@Step
	@Given("User launch the browser")
	public void user_launch_the_browser() throws Exception {
		driver=launchBrowserMethod(readPropertyFile("Browser"));
		pageObject=new PageObjectClass(driver);
		maximizeWindowMethod();
		staticWaitMethod(3000);  
		deleteAllCookiesMethod();
	}

	@Step
	@And("User Navigate to Amazon Application")
	public void user_navigate_to_amazon_application() throws Exception{
		getBrowserMethod(readPropertyFile("URL"));
		staticWaitMethod(3000);   
		takesScreenShotMethod("Application_Main_Page");
	}

	@Step
	@And("User verify the Amazon Application title")
	public void user_verify_the_amazon_application_title() throws Exception {
		staticWaitMethod(3000);  
		softAssert.assertEquals(getTitleMethod(), readPropertyFile("Title"));
	}

	@Step
	@When("User click the Fashion Module in Amazon Main Page")
	public void user_click_the_fashoin_module_in_amazon_main_page() throws Exception{
		staticWaitMethod(3000);  
		clickMethod(pageObject.getFashionlink());
		staticWaitMethod(3000); 
		takesScreenShotMethod("Amazon_Fashion_Module");
		softAssert.assertTrue(containsMethod(getTitleMethod(), readPropertyFile("UserFilter")),"User filter not working properly");
	}

	@Step
	@Then("User Observe that Mens Clothing subModule Link displays")
	public void user_observe_that_mens_clothing_sub_module_link_displays() throws Exception{
		staticWaitMethod(3000); 
		clickMethod(pageObject.getMenFashionLink());
		staticWaitMethod(3000);
		takesScreenShotMethod("Men_Fashion_Sub_Module");
		softAssert.assertTrue(containsMethod(getTitleMethod(), readPropertyFile("UserSubCardModule")),"User Sub Module Filter not working properly");

	}

	@Step
	@And("User Click Average Customer Review")
	public void user_click_average_customer_review() {
		staticWaitMethod(3000);
		executeScriptScrollToElement(pageObject.getCustomerReviewAverageText());
		staticWaitMethod(3000);
		pageObject.setCustomerReview(readPropertyFile("CustomerReview"));
		clickMethod(pageObject.getCustomerReview());
		staticWaitMethod(5000);	
	}

	@Step
	@And("user select the price Filters")
	public void user_select_the_price_filters() throws IOException {
		softAssert.assertEquals(driver.getTitle(), readPropertyFile("DefaultTitle"));
		executeScriptScrollToElement(pageObject.getPriceText());
		staticWaitMethod(2000);
		excuteScriptSetAttributeMethod(pageObject.getMinimumPriceFilter(),readPropertyFile("MinimumPrice"),"value");
		staticWaitMethod(3000);
		System.out.println(" User Minimum Price   :    " +getAttributeMethod(pageObject.getMinimumPriceFilter(),"value"));
		excuteScriptSetAttributeMethod(pageObject.getMaximumPriceFilter(),readPropertyFile("MaximumPrice"),"value");
		staticWaitMethod(3000);
		System.out.println(" User Maximum Price   :    " + getAttributeMethod(pageObject.getMaximumPriceFilter(),"value"));
		staticWaitMethod(3000);
		clickMethod(pageObject.getGoButton());
		takesScreenShotMethod("Filter_price");

	}

	@Step
	@And("user select the brands {string},{string}")
	public void user_select_the_brands(String brandValue, String brandValue2) throws IOException {
		staticWaitMethod(3000);
		executeScriptScrollToElement(pageObject.getBrandText());
		staticWaitMethod(2000);
		pageObject.setBrand(brandValue);
		clickMethod(pageObject.getBrand());
		takesScreenShotMethod(brandValue+"_brand");
		staticWaitMethod(2000);
		pageObject.setBrand(brandValue2);
		clickMethod(pageObject.getBrand());
		takesScreenShotMethod(brandValue2+"_brand");

	}

	@Step
	@Given("User select first page and print the count of the products")
	public void user_select_first_page_and_print_the_count_of_the_products() throws IOException {
		staticWaitMethod(5000);
		System.out.println("No of Products :  " + countMultipleElements(pageObject.getListOfProducts()));
		logger.info("Total no of products display  " + countMultipleElements(pageObject.getListOfProducts()));	 
		takesScreenShotMethod("Filter_Results");
	}

	@Step
	@And("User click the {string} product")
	public void user_click_the_product(String value) {
		staticWaitMethod(3000);
		pageObject.setUserProduct(value);
		clickMethod(pageObject.getUserProduct());
	}
	@Step
	@Then("User observe that selected product displays in addToCart Module")
	public void user_observe_that_selected_product_displays_in_add_to_cart_module() throws IOException {
		staticWaitMethod(5000); 
		switchWindowMethod();
		softAssert.assertEquals(getTextMethod(pageObject.getCartCount()), readPropertyFile("IntialCartValue"));
		staticWaitMethod(2000); 
		takesScreenShotMethod("User_Selected_Product");
		clickMethod(pageObject.getAddToCartButton());
		System.out.println(driver.getTitle());

	}
	@Step
	@Then("User validate the number of Cart increased by {int}")
	public void user_validate_the_number_of_cart_increased_by(int value) throws IOException {
		staticWaitMethod(3000);
		clickMethod(pageObject.getGoToCartButton());
		takesScreenShotMethod("Cart_Value_Increase");
		softAssert.assertEquals(getTextMethod(pageObject.getCartCount()), Integer.toString(value));
		softAssert.assertAll();   
	}
	@Then("User close the browser")
	public void user_close_the_browser() {
		staticWaitMethod(3000);
		browserQuitMethod();
	}
}
