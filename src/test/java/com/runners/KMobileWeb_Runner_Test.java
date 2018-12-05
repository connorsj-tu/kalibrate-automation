package com.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/KMobileWeb/features",
        glue = "com.step_definitions.KMobileWeb",

        monochrome = true,

		tags = {"@kmobileweb", "@login", "~@wip", "~@ignore"},
					
        snippets = SnippetType.CAMELCASE,
        plugin = {
       			"pretty",
       			"html:target/cucumber",
       			"json:target/cucumber/cucumber.json",
       			"junit:target/cucumber/cucumber.xml"
        }
)


public class KMobileWeb_Runner_Test {
}
