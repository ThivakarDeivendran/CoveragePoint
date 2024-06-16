package stepdefinitions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.AfterAll;
import mainPackage.AllureUtil;
import mainPackage.ReusableMethods;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.cucumber.java.BeforeStep;

public class TestHooks extends ReusableMethods {
	
	private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);
	
	@BeforeStep
	public static void beforeStep() {
		System.out.println("This will run before every Step");
	}
	
	@AfterStep
	public static void afterStep(Scenario scenario) throws IOException {
		logger.error("This will run after every Step");
		if (scenario.isFailed()) {
			TakesScreenshot screenshotTaker=(TakesScreenshot) driver;
			byte[] screenshot =screenshotTaker.getScreenshotAs(OutputType.BYTES);
			// Attach the screenshot to the Allure report
			Allure.addAttachment("Screenshot on failure", new ByteArrayInputStream(screenshot));
		}
	}
	
	@After("@Feature")
	public static void afterFeature(Scenario scenario) throws IOException {
		System.out.println("This will run after the Feature");
		String scenaioName = scenario.getName();
		if (scenario.isFailed()) {
			File errorLog = new File("error.log");
			if (errorLog.exists()) {
				String content = new String(Files.readAllBytes(errorLog.toPath()));
				if (!content.isEmpty()) {

					// scenario.fail("Test failed due to logged errors. See erron, log for details.");
					// Attach report to screenshot here
				}
				logger.info("The scenario:: " + scenaioName + " has failed.");
			} else {

				logger.info("The scenario: " + scenaioName + " has passed.");
			}
		}
	}
	
	
	@AfterAll
	public static void teardown() throws Exception{
		logger.info("Generating allure report with trend");
		AllureUtil allureUtil = new AllureUtil();
		allureUtil.runAllureCommand("allure generate --clean");
		logger.info("copy history from allure-report to allure_results");
		File sourceDir = new File("allure-report/history");
		File targetDir = new File("allure-results/history");
		FileUtils.copyDirectory(sourceDir, targetDir);
		logger.info("Generating Allure report with trend");
		allureUtil.runAllureCommand("allure serve");
	}

}
