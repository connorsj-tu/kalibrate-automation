package com.step_definitions.pricenet;

import com.common.helpers.Utils;
import com.common.hooks.Hooks;
import com.common.page_objects.PriceNet_Generic_LocatorLibrary;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class PriceNet_Hooks extends Hooks{

		private static boolean beforeAllHookExecuted = false;
		private static boolean dbExtendedEventsRequired = false;


		public PriceNet_Hooks() {
			
			System.out.println("In PriceNet_Hooks() constructor");
			
			applicationName = "PRICENET";
			switch(Hooks.applicationVersionSimplified.toLowerCase()) {
	    	case "2.9":
	    		priceNetLocatorLibrary = new PriceNet_Generic_LocatorLibrary();
				break;
			default :
	    		System.out.println("Unsupported version as derived from pninstalllog: " + Hooks.applicationVersionSimplified);
				System.exit(0);
	    	}
			
		}
		
		
		@Before (order=1)
		public void beforeScenarioHook(Scenario scenario) {
			
			testName = scenario.getName();
			
			setupBrowserDriver();

			if(connectToDatabase) 
				createDBConnectionJDBC();

			instantiateHelpers();

			if(connectToDatabase)
				tidyDB();

			launchApplication();
			
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

	            if(dbExtendedEventsRequired = Boolean.parseBoolean(Utils.getProperty("EXECUTE_DB_ENTENDED_EVENTS"))) 
	            		databaseHelper.createDBConnectionAndExecuteSQLStringNoResultSet("ALTER EVENT SESSION [User_SQLDeadlocks] ON SERVER STATE = START;");
	            beforeAllHookExecuted = true;
	        }
	    }

		
		@After (order=1)
		public void afterScenarioCloseKalibrate() {
			
			reporterHelper.log("\nIn @After hook method afterScenarioCloseKalibrate(), closing Kalibrate if necessary;");
			
		
			
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
//				if ( !(((RemoteWebDriver)driver).getSessionId() == null)) {
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
//			saveUpdatableConfig();
		}
		
	}