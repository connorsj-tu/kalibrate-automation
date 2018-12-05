package com.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/pricenetweb/features",
        glue = "com.pricenetweb.step_definitions",
     	tags = {"@login", "~@ignore"},
//        tags = {"@PNWEB_SITE-SURVEY", "~@ignore"},
        		
//		tags = {"@develop", "~@ignore", "~@sql"},
//     	tags = {"@develop", "~@ignore", "@login"},
//		tags = {"@develop", "~@ignore", "@login", "@PN_LOGON-CREDS_05"},
        snippets = SnippetType.CAMELCASE,
        plugin = {
    			"pretty",
    			"html:target/cucumber",
    			"json:target/cucumber/cucumber.json",
    			"junit:target/cucumber/cucumber.xml"
        }
)


public class PriceNetWeb_Runner_Test_SKIP {}

