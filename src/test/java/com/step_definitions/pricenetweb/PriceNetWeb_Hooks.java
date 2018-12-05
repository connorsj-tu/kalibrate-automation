package com.step_definitions.pricenetweb;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.common.helpers.BrowserHelper;
import com.common.hooks.Hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class PriceNetWeb_Hooks extends Hooks{

	public PriceNetWeb_Hooks() {
	
//		super.browserName = "CHROME";
//		super.browserName = "FIREFOX";
		super.browserName = "IE";

		
		applicationName = "PRICENETWEB";	

		super.browserAuthRequired = false;

//		super.useGrid = true;
		super.useGrid = false;
		
	}
	
	@Before
	public void beforeScenarioHook(Scenario scenario) {
		
//		super.scenario = scenario;
		super.testName = scenario.getName();
		
		super.nextTimeHA = true;
		
		System.out.println("Scenario Tags:" + scenario.getSourceTagNames().toString());
		

		loadConfig();
		setupBrowserDriver();
		createDBConnectionJDBC();
//		createDBConnectionJTDS();
		instantiateHelpers();
//		tidyDB();
		launchApplication();

	}

	
	@After
	public void afterScenarioCloseApplication() {
		
		reporterHelper.log("\nIn @After hook method afterScenarioCloseApplication(), closing Application if necessary;");
		
//		// If Kalibrate is still open...
//		reporterHelper.log("\tChecking if Kalibrate is open...");
//		if(BrowserHelper.isElementPresent(By.xpath("//body[@data-ng-app='Kalibrate']"))) {
//			
//			if(BrowserHelper.isElementPresent(LocatorLibrary.kalibrate_login_UsernameTextBox)) {
//				reporterHelper.log("\n\tKalibrate login page indetified, no need to logout");
//			}
//			else if(BrowserHelper.isElementPresent(LocatorLibrary.kalibrate_main_WorkspaceMenuHeader)) {
//				
//				WebElement workspaceMenuElement = BrowserHelper.syncOnElement(LocatorLibrary.kalibrate_main_WorkspaceMenuHeader, "present");
//				
//				reporterHelper.log("\nChecking if DEFAULT workspace is currently selected");
//				if(!workspaceMenuElement.getText().equalsIgnoreCase("DEFAULT")) {
//				
//					reporterHelper.log("\tDEFAULT workspace is not selected, therefore selecting");
//					if(!BrowserHelper.isElementPresent(LocatorLibrary.kalibrate_main_WorkspaceMenuExpanded)) {
//						reporterHelper.log("\tExpanding workspace menu");
//						BrowserHelper.syncOnElement(LocatorLibrary.kalibrate_main_WorkspaceMenuHeader, "present").click();
//						BrowserHelper.customSleep(500);
//					}
//					
//					reporterHelper.log("\tClicking DEFAULT");
//			        BrowserHelper.syncOnElement(By.xpath("//div[contains(@class, 'workspace-text')][contains(., 'DEFAULT')]"), "present").click();
//			        BrowserHelper.customSleep(1 * 1000);
//		        
//			        workspaceMenuElement.click();
//			        BrowserHelper.customSleep(1 * 1000);
//			        
//				}			
//				
//				// Logout of Kalibrate
//		        BrowserHelper.syncOnElement(LocatorLibrary.kalibrate_main_HamburgerIcon, "present").click();
//		        BrowserHelper.customSleep(1 * 1000);
//		        BrowserHelper.syncOnElement(LocatorLibrary.kalibrate_main_MainMenuLogoutItem, "present").click();
//		        
//		        BrowserHelper.customSleep(1 * 1000);
//
//				
//			} else {
//				reporterHelper.error("\n\tKalibrate open but unable to identify state to enable logout");
//			}
//		        
//			driver.close();
//			driver.quit();
//		}

//		driver.close();
//		driver.quit();
		
		afterScenarioCloseObjects();
		
	}

	
	
//	@After
	public void afterScenarioCloseObjects() {
	
		reporterHelper.log("\nIn @After hook method afterScenarioCloseObjects(), closing down objects/connections where necessary;");

		// Tidy Database
//		tidyDB();
		
		// Close SQL DB Objects
        try {
            if (kalibrateDbResultSet != null) {
            	reporterHelper.log("\n\tSQL DB ResultSet still active, closing ResultSet");
            	kalibrateDbResultSet.close();
            }
        } catch (Exception ignore) {}
        
        try {
            if (kalibrateDbStatement != null) {
            	reporterHelper.log("\n\tSQL DB Statement still active, closing Statement");
            	kalibrateDbStatement.close();
            }
        } catch (Exception ignore) {}
        
        try {
    		if (kalibrateDbConn != null) {
    			reporterHelper.log("\n\tSQL DB Connection still active, closing connection");
    			kalibrateDbConn.close();
    		}
        } catch (Exception ignore) {}
        
		// Close driver if it has not been closed already
		if ( !(((RemoteWebDriver)driver).getSessionId() == null)) {
			reporterHelper.log("\n\tBrowser driver still active, closing browser");
			driver.close();
			driver.quit();
		}
		
		
		
		reporterHelper.setOutputFolderPassStatus();
		
		// Save test output files to report folder
		reporterHelper.saveTestOutputFiles();
		
//		saveUpdatableConfig();
	}
	
	
	
}
	
//	
//	public PriceNet_Hooks() {
//		
//		super.browserName = "IE";
//		super.applicationName = "PRICENET";
//		
//	}
//	
//	
//	@Before
//	public void beforeScenarioHook(Scenario scenario) {
//		
//		super.testName = scenario.getName();
//		
//		System.out.println("XX:" + scenario.getSourceTagNames().toString());
//		
//		loadConfig();
//		setupBrowserDriver();
//		createDBConnectionJDBC();
//		instantiateHelpers();
//		tidyDB();		
//		launchApplication();
//	}
//		
//    @After  
//    public void embedScreenshot(Scenario scenario) {
//    	
//    	reporterHelper.log("\nIn @After hook method embedScreenshot(Scenario scenario), embedding screenshot if scenario failed");
//    	
//        if (scenario.isFailed()) {  
//            try {  
//                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);  
//                scenario.embed(screenshot, "image/png");  
//            } catch (WebDriverException wde) {  
//                System.err.println(wde.getMessage());  
//            } catch (ClassCastException cce) {  
//                cce.printStackTrace();  
//            }  
//        }  
//    }  	
//	
//	@After
//	public void afterScenario() {
//		reporterHelper.log("\nIn @After hook method afterScenario(), closing down objects/connections where necessary;");
//
//		// Tidy Up WorkSpaces returning to known state - DEFAULT workspace as active
////		tidyWorkspacesDB();
//		
//		// Close SQL DB Objects
//        try {
//            if (kalibrateDbResultSet != null) {
//            	reporterHelper.log("\n\tSQL DB ResultSet still active, closing ResultSet");
//            	kalibrateDbResultSet.close();
//            }
//        } catch (Exception ignore) {}
//        
//        try {
//            if (kalibrateDbStatement != null) {
//            	reporterHelper.log("\n\tSQL DB Statement still active, closing Statement");
//            	kalibrateDbStatement.close();
//            }
//        } catch (Exception ignore) {}
//        
//        try {
//    		if (kalibrateDbConn != null) {
//    			reporterHelper.log("\n\tSQL DB Connection still active, closing connection");
//    			kalibrateDbConn.close();
//    		}
//        } catch (Exception ignore) {}
//        
//		// Close driver if it has not been closed already
//		if ( !(((RemoteWebDriver)driver).getSessionId() == null)) {
//			reporterHelper.log("\n\tBrowser driver still active, closing browser");
//			driver.close();
//			driver.quit();
//		}
//		
//		// Save test output files to report folder
//		reporterHelper.saveTestOutputFiles();
//		
//	}
// 
//	public void launchApplication()  {	
//
//		// build URL from config file
//        String priceNetURL = config.getProperty("PRICENET_URI_PREFIX") + "/" + config.getProperty("PRICENET_BUILD_NAME");
//        reporterHelper.log("Launching browser: " + priceNetURL);
//		
//		// Launch PriceNet Website        
//	    reporterHelper.startTimer("Launch Browser");
//	    driver.get(priceNetURL);
//	    reporterHelper.stopTimer("Launch Browser");
//	}	
//}