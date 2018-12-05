package com.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/pricenet/features",
        glue = "com.step_definitions.pricenet",
        tags = {"@positive", "@regression", "~@wip"},

        snippets = SnippetType.CAMELCASE,
        plugin = {
    			"pretty",
    			"html:target/cucumber",
    			"json:target/cucumber/cucumber.json",
    			"junit:target/cucumber/cucumber.xml"
        }
)


public class PriceNet_Runner_Test {}

