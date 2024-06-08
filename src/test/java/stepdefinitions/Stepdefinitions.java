package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import io.cucumber.java.en.*;
import mainPackage.PageObjectClass;
import mainPackage.ReusableMethods;


public class Stepdefinitions extends ReusableMethods{
	
	public static WebDriver driver;
	public static PageObjectClass pageObject;
	public SoftAssert softAssert= new SoftAssert();
	
	@Given("User launch the browser")
	public void user_launch_the_browser() throws Exception {
		driver=launchBrowserMethod(readPropertyFile("Browser"));
		pageObject=new PageObjectClass(driver);
		maximizeWindowMethod();
		staticWaitMethod(3000);  
		deleteAllCookiesMethod();
	}

	@And("User Navigate to Amazon Application")
	public void user_navigate_to_amazon_application() throws Exception{
		getBrowserMethod(readPropertyFile("URL"));
		staticWaitMethod(3000);    
	}

	@And("User verify the Amazon Application title")
	public void user_verify_the_amazon_application_title() throws Exception {
		staticWaitMethod(3000);  
		softAssert.assertEquals(getTitleMethod(), readPropertyFile("Title"));
	}

@When("User click the Fashion Module in Amazon Main Page")
public void user_click_the_fashoin_module_in_amazon_main_page() throws Exception{
	staticWaitMethod(3000);  
	clickMethod(pageObject.getFashionlink());
	System.out.println("Testing    : " +driver.getTitle());
}

	@Then("User Observe that Mens Clothing subModule Link displays")
	public void user_observe_that_mens_clothing_sub_module_link_displays() throws Exception{
	    
	}

}
