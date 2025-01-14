package testRunnerPackage;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	    features = "src/test/java/featurePackage/", glue = {"stepdefinitions"}, 
	    plugin = {
	        "pretty", 
	        "html:target/cucumber-html-report",
	        "json:target/cucumber.json",
	        "junit:target/cucumber.xml" },
	    monochrome = true, dryRun = false)
public class TestRunnerFile {

}


