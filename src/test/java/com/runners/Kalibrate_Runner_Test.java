package com.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/kalibrate/features",
        glue = "com.step_definitions.kalibrate",

        monochrome = true,

//		tags = {"@kalibrate", "@perf", "@pricing", "~@wip", "~@ignore"},
//		tags = {"@kalibrate", "@perf", "@pricing", "@comppricesurvery", "~@wip", "~@ignore"},
		tags = {"@kalibrate", "@newperf", "@pricing", "~@ignore"},
//		tags = {"@kalibrate", "@marketpricing", "@gpc"},
		
//		tags = {"@kalibrate", "@positive", "@login", "~@wip", "~@ignore"},

        		
        snippets = SnippetType.CAMELCASE,
        plugin = {
       			"pretty",
       			"html:target/cucumber",
       			"json:target/cucumber/cucumber.json",
       			"junit:target/cucumber/cucumber.xml"
        }
)


public class Kalibrate_Runner_Test {
	
}

