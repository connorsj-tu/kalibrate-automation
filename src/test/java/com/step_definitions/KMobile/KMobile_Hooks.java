package com.step_definitions.KMobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.common.helpers.BrowserHelper;
import com.common.helpers.Utils;
import com.common.hooks.Hooks;
import com.common.page_objects.KMobile_Generic_LocatorLibrary;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class KMobile_Hooks extends Hooks {

	private static boolean beforeAllHookExecuted = false;
	private static boolean dbExtendedEventsRequired = false;

//	static AndroidDriver<?> driver;
	
	public KMobile_Hooks() {
//		super();
		
		
		
		System.out.println("In KMobile_Hooks constructor");
		
		applicationName = "KMOBILE_WEB";
		
		switch(Hooks.applicationVersionSimplified) {
    	case "2.5.5":
    		kMobileLocatorLibrary = new KMobile_Generic_LocatorLibrary();
			break;
		default :
    		System.out.println("Unsupported version as derived from pninstalllog: " + Hooks.applicationVersionSimplified);
			System.exit(0);
    	}
		
	}
	

	@Before (order=1)
	public void beforeScenarioHook(Scenario scenario) {
		
		testName = scenario.getName();
		
		setupDeviceDriver();

		if(connectToDatabase) 
			createDBConnectionJDBC();

		instantiateHelpers();

		if(connectToDatabase)
			tidyDB();

//		launchApplication();
//		launchDeviceApplication();
		
		reporterHelper.log("Scenario Tags:" + scenario.getSourceTagNames().toString());

	}

	
	// The following is used as a workaround to have a Global beforeAll and afterAll Hook
	// as global hooks are not supported by cucumber
	
	@Before (order=2) // note order defines order hooks will run with lowest number first
    public void beforeAll() {
        if(!beforeAllHookExecuted) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    System.out.println("afterAll hook executed");
                	if(dbExtendedEventsRequired)
                		databaseHelper.createDBConnectionAndExecuteSQLStringNoResultSet("ALTER EVENT SESSION [User_SQLDeadlocks] ON SERVER STATE = STOP;");
                }
            });
            System.out.println("beforeAll hook executed");

            // TODO - Add check of config item CONNECT_TO_DATABASE also 
            if(dbExtendedEventsRequired = Boolean.parseBoolean(Utils.getProperty("EXECUTE_DB_ENTENDED_EVENTS"))) 
            		databaseHelper.createDBConnectionAndExecuteSQLStringNoResultSet("ALTER EVENT SESSION [User_SQLDeadlocks] ON SERVER STATE = START;");
            beforeAllHookExecuted = true;
        }
    }

	
	@After (order=1)
	public void afterScenarioCloseKalibrate() {
		
		reporterHelper.log("\nIn @After hook method afterScenarioCloseKalibrate(), closing Kalibrate if necessary;");
		
		// If Kalibrate is still open...
		
		reporterHelper.log("\tChecking if browser is still open...");
		
		if(!(driver==null)) { 
			if( (!(driver==null)) || !driver.toString().contains("null"))
			{
				reporterHelper.log("\tBrowser is still open.  Checking if Kalibrate is open...");
		
				if(BrowserHelper.isElementPresent(appiumDriver, By.xpath("//body[@data-ng-app='Kalibrate']"))) {
					
					reporterHelper.log("\tConfirmed Kalibrate is open...");
					
					if(BrowserHelper.isElementPresent(appiumDriver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox)) {
						reporterHelper.log("\tKalibrate login page indetified, no need to logout");
					}
					else if(BrowserHelper.isElementPresent(appiumDriver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader)) {
						
						WebElement workspaceMenuElement = BrowserHelper.syncOnElement(appiumDriver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader, "present");
						
						reporterHelper.log("\nChecking if DEFAULT workspace is currently selected");
						if(!workspaceMenuElement.getText().equalsIgnoreCase("DEFAULT")) {
						
							reporterHelper.log("\tDEFAULT workspace is not selected, therefore selecting");
							if(!BrowserHelper.isElementPresent(appiumDriver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuExpanded)) {
								reporterHelper.log("\tExpanding workspace menu");
								BrowserHelper.syncOnElement(appiumDriver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader, "present").click();
								BrowserHelper.customSleep(500);
							}
							
							reporterHelper.log("\tClicking DEFAULT");
					        BrowserHelper.syncOnElement(appiumDriver, By.xpath("//div[contains(@class, 'workspace-text')][contains(., 'DEFAULT')]"), "present").click();
					        BrowserHelper.customSleep(1 * 1000);
				        
					        workspaceMenuElement.click();
					        BrowserHelper.customSleep(1 * 1000);
					        
						}			
						
						// Logout of Kalibrate
				        BrowserHelper.syncOnElement(appiumDriver, kalibrateLocatorLibrary.kalibrate_main_HamburgerIcon, "present").click();
				        BrowserHelper.customSleep(1 * 1000);
				        BrowserHelper.syncOnElement(appiumDriver, kalibrateLocatorLibrary.kalibrate_main_MainMenuLogoutItem, "present").click();
				        
				        BrowserHelper.customSleep(1 * 1000);
		
						
					} else {
						reporterHelper.error("\n\tKalibrate open but unable to identify state to enable logout");
					}
				        
					driver.close();
					driver.quit();
				}
			}
		}
		
		// Kill any chromedriver processes in memory
		reporterHelper.runSystemCommand("taskkill /im chromedriver.exe /f");
		
	}

	
	
	@After(order=2)
	public void afterScenarioCloseObjects() {
	
		reporterHelper.log("\nIn @After hook method afterScenarioCloseObjects(), closing down objects/connections where necessary;");

		// Tidy Database
		if(connectToDatabase)
			tidyDB();
		
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
        
        try {
			// Close driver if it has not been closed already
//			if ( !(((RemoteWebDriver)driver).getSessionId() == null)) {
			if ( !(driver.toString().contains("null")) ) {	
				reporterHelper.log("\n\tBrowser driver still active, closing browser");
				driver.close();
				driver.quit();
			}
        } catch (Exception ignore) {}
		
		
		
		reporterHelper.setOutputFolderPassStatus();
		
		// Save test output files to report folder
		reporterHelper.saveTestOutputFiles();
		
		// Save updateable config file
//		saveUpdatableConfig();
	}
	
	

}