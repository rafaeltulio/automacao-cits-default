package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
			features = { "src/test/resources/features/" }, 
			glue = { "steps" }, 
			tags = { "@tpService" }, 
			plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.xml", "json:target/cucumber-pt.json",
		"rerun:target/cucumber-reports/rerun.txt" }, 
			monochrome = true, 
			snippets = SnippetType.CAMELCASE, 
			dryRun = false, 
			strict = false)

public class RunCukeTest {

}