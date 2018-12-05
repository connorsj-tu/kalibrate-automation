package com.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/kalibrate/features",
        glue = "com.step_definitions.kalibrate",
        

		tags = {"@Kalibrate_Pricing_Feature_2_10", "@KP_871_Leadandfollow_in_Search", "~@wip"},

        snippets = SnippetType.CAMELCASE,
        plugin = {
       			"pretty",
       			"html:target/cucumber",
       			"json:target/cucumber/cucumber.json",
       			"junit:target/cucumber/cucumber.xml"
        }
)


public class Gayathri_Kalibrate_Runner_Test {
}