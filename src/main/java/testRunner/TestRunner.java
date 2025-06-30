package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(plugin ={"pretty:target/cucumber-reports/cucumber-pretty.txt", "html:target/cucumber-reports/raw-cucumber-html-report.html", "json:target/report.json", "json:target/cucumber-reports/CucumberTestReport.json"},
        features = {"./src/test/resources/featurefiles/"}, glue = {"stepDefinitions"})
public class TestRunner extends AbstractTestNGCucumberTests {

    //Below code allows to run scenarios in parallel threads.

    @Override
   @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();

    }
    /*
    * Note:  /* Cucumber iteself doesnt support parallel execution within a single JVM instance for steps that share state,
    unless you isolate step definitions carefully (Stateless or Threadlocal)*/
    /* Avoid using static variables in your step definitions*/
    /* Ensure your drivers and setup are thread-safe*/


}