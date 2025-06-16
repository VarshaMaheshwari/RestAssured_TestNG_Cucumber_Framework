package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin ={"pretty:target/cucumber-reports/cucumber-pretty.txt", "html:target/cucumber-reports/raw-cucumber-html-report.html", "json:target/report.json", "json:target/cucumber-reports/CucumberTestReport.json"},
        features = {"./src/test/resources/featurefiles/"}, glue = {"stepDefinitions"})
public class TestRunner extends AbstractTestNGCucumberTests {


}