package Runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src\\main\\Features",
        glue = "StepDefinitions",
        tags = "@regression",
        plugin = {"pretty",
                "html:target/cucumber/cucumber-html-report.html",
                "json:target/cucumber/cucumber-json-report.json",
                "junit:target/cucumber/cucumberXML.xml"}

)


public class TestRunner extends AbstractTestNGCucumberTests {

}
