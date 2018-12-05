package com.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/KMobile/features",
        glue = "com.step_definitions.KMobile",
        
		tags = {"@KMobile", "@login", "~@wip"},
        		
        snippets = SnippetType.CAMELCASE,
        plugin = {
       			"pretty",
       			"html:target/cucumber",
       			"json:target/cucumber/cucumber.json",
       			"junit:target/cucumber/cucumber.xml"
        }
)


public class KMobile_Runner_Test {
}

