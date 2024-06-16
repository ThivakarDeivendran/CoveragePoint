package stepdefinitions;

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
	
	
	public Stepdefinitions(Shared_State state) {
		this.sharedState=state;
	}
	
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
		softAssert.assertTrue(containsMethod(getTitleMethod(), readPropertyFile("UserFilter")),"User filter not working properly");
	}

	@Step
	@Then("User Observe that Mens Clothing subModule Link displays")
	public void user_observe_that_mens_clothing_sub_module_link_displays() throws Exception{
		staticWaitMethod(3000); 
		clickMethod(pageObject.getMenFashionLink());
		staticWaitMethod(3000);
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
	public void user_select_the_price_filters() {
	    softAssert.assertEquals(driver.getTitle(), readPropertyFile("DefaultTitle"));
	    executeScriptScrollToElement(pageObject.getPriceText());
	    staticWaitMethod(2000);
	    excuteScriptSetAttributeMethod(pageObject.getMinimumPriceFilter(),readPropertyFile("MinimumPrice"),"value");
	    staticWaitMethod(3000);
	    System.out.println(" User Minimum Price   :    " +getAttributeMethod(pageObject.getMinimumPriceFilter(),"value"));
	    excuteScriptSetAttributeMethod(pageObject.getMaximumPriceFilter(),readPropertyFile("MaximumPrice"),"value");
	    staticWaitMethod(3000);
	    System.out.println(" User Maximum Price   :    " + getAttributeMethod(pageObject.getMinimumPriceFilter(),"value"));
	    staticWaitMethod(3000);
	    clickMethod(pageObject.getGoButton());
	   
	}
	
	@Step
	@And("user select the brands {string},{string}")
	public void user_select_the_brands(String brandValue, String brandValue2) {
		staticWaitMethod(3000);
		executeScriptScrollToElement(pageObject.getBrandText());
		staticWaitMethod(2000);
		pageObject.setBrand(brandValue);
		clickMethod(pageObject.getBrand());
		staticWaitMethod(2000);
		pageObject.setBrand(brandValue2);
		clickMethod(pageObject.getBrand());
		 softAssert.assertAll();
	}


}
