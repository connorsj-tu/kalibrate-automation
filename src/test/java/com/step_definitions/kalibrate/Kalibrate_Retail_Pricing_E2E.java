package com.step_definitions.kalibrate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.common.helpers.BrowserHelper;
import com.common.helpers.DatabaseHelper;
import com.common.helpers.KalibrateHelper;

import com.common.helpers.ReporterHelper;
import com.common.page_objects.Kalibrate_Generic_LocatorLibrary;


import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Kalibrate_Retail_Pricing_E2E {
	// Class variables
	private static boolean performSync = true;
	
	private ReporterHelper reporterHelper = Kalibrate_Hooks.reporterHelper;
	private BrowserHelper browserHelper = Kalibrate_Hooks.browserHelper;
	private DatabaseHelper databaseHelper = Kalibrate_Hooks.databaseHelper;
	private KalibrateHelper kalibrateHelper = Kalibrate_Hooks.kalibrateHelper;
	
	private Kalibrate_Generic_LocatorLibrary kalibrateLocatorLibrary = Kalibrate_Hooks.kalibrateLocatorLibrary;
	
	private ResultSet sqlDbResultSet = Kalibrate_Hooks.kalibrateDbResultSet;
	private String applicationName = Kalibrate_Hooks.applicationName;
	
	private WebDriver driver = Kalibrate_Hooks.driver;
	
	@Given("^Search & select for \"([^\"]*)\" ownsite$")
	public void searchSelectForOwnsite(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Sync,"present");
		
		WebElement searchType =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchTypeDropDownButton,"clickable");
		searchType.click();
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchTypeOwnSite,"clickable");
		
		WebElement selectOwnSiteType =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchTypeOwnSite,"clickable");
		selectOwnSiteType.click();
		
		WebElement OwnsitesTab =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSiteTab,"visible");
	    reporterHelper.log("Own Sites  selected for Search Type");
	    
	    WebElement SiteImportCode  =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput,"present");
	    SiteImportCode.clear();
	    SiteImportCode.sendKeys(arg1);
	    WebElement SearchButton =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton,"present");
	    SearchButton.click();
		BrowserHelper.customSleep(1000);
		WebElement Searchresultsync =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSite_1_Result,"visible");
	    reporterHelper.log("Own Site Listed in search result");
	    reporterHelper.takeScreenshot(driver,"OwnSite Search");
	    
	    BrowserHelper.customSleep(1000);

		WebElement OwnSite_1 =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSite_1_Result,"visible");
		OwnSite_1.click();
		BrowserHelper.customSleep(1000);
		
	}

	@When("^Pull \"([^\"]*)\" widget for selected site to \"([^\"]*)\"$")
	public void pullWidgetForSelectedSiteTo(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Sync,"present");

		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSite_1_Result,"visible");
		    Actions builder;
		    builder = new Actions(driver);
			WebElement OwnSitesHovericons =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSiteshovericon,"visible");
		// Move the mouse to the earlier identified menu option - Hovering Menu options
			    reporterHelper.log("Hoverdone");
			    builder.moveToElement(OwnSitesHovericons).perform();
				BrowserHelper.customSleep(1000);
			    reporterHelper.log("Hoverdone");
				
		//Selecting sub menus    
			   Actions builder2;
			    Action dragAndDrop;
					  builder2 = new Actions(driver);
					  WebElement requiredWidget;
					  requiredWidget=BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon,"present");

		//Required Widgets 
						switch (arg1)
						{
						case "Pricing":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesPricing,"visible");
					       reporterHelper.log("Own Site Pricing Widget");
					       reporterHelper.log(requiredWidget.getAttribute("src"));	
					       reporterHelper.log(requiredWidget.getAttribute("class"));

					       break;     
						}
						case "Site Manager":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesSiteManager,"visible");
					       reporterHelper.log("Own Site Site Manager Widget");
					       reporterHelper.log(requiredWidget.getAttribute("src"));	
					       reporterHelper.log(requiredWidget.getAttribute("class"));

					       break;     
						}
						
						case "Intel":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesIntel,"visible");
					       reporterHelper.log("Own Site Intel Widget");
					       reporterHelper.log(requiredWidget.getAttribute("src"));	
					       reporterHelper.log(requiredWidget.getAttribute("class"));
					       break;  
					       
						}
						
						case "Notes":
			
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesNotes,"visible");
						       reporterHelper.log("Own Site Notes Widget");
							reporterHelper.log(requiredWidget.getAttribute("src"));	
							reporterHelper.log(requiredWidget.getAttribute("class"));

							break;     
						}
						case "Site Strategy":
			
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesStrategy,"visible");
					       reporterHelper.log("Own Site Site  Strategy Widget");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}
						case "Pump Price Update":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesPumpPriceUpdate,"visible");
					       reporterHelper.log("Own Site Pump Price Update Widget");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}

						case "Event Manager":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesEventManager,"visible");
					       reporterHelper.log("Own Site Pump Price Update Widget");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}

						
						case "Report Viewer":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesReportViewer,"visible");
					       reporterHelper.log("Own Site Pump Price Update Widget");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}

						case "Site Metrics":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesSiteMetrics,"visible");
					       reporterHelper.log("Own Site Pump Price Update Widget");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}
						case "Site Planner":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSitesSitePlanner,"visible");
					       reporterHelper.log("Own Site Pump Price Update Widget");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}
						
						case "MultiPricing":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchResults_FooterMultiPricingIcon,"visible");
						   reporterHelper.log("DTA Site Manager Widgets");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}
						
						case "Market Pricing":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FooterWidgets_MarketPricing,"visible");
						   reporterHelper.log("DTA Site Manager Widgets");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}
						case "Market Strategy":
							
						{
							requiredWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FooterWidgets_MarketStrategy,"visible");
						   reporterHelper.log("DTA Site Manager Widgets");
						   reporterHelper.log(requiredWidget.getAttribute("src"));	
						   reporterHelper.log(requiredWidget.getAttribute("class"));
						   break;     
						}
						}	
						WebElement containerSelected; 
						containerSelected= BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer1,"present");

						switch (arg2)
						{
						//Container 
						case "Container1":
						{
						 containerSelected = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer1,"present");
						break;
						}
						case "Container2":
						{
						 containerSelected = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer2,"present");
						break;
						}
						case "Container3":
						{
						 containerSelected = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer3,"present");
						break;
						}
						
						}
		// Drag & drop available widgets 
		// Added 	moveByOffset(1,1) as objects disappears quickly 			
				    dragAndDrop = builder2.clickAndHold(requiredWidget).moveByOffset(1,1)
						   .moveToElement(containerSelected)
			    	      .release(containerSelected)	         
			    	      .build();
		    
				       dragAndDrop.perform();
				    reporterHelper.log(arg1 +"Widgets Opened for selected Own Sites");  
					BrowserHelper.customSleep(1000);
	
	}
	
	
	
	@Then("^Verify widgets are loaded without any errors$")
	public void verifyWidgetsAreLoadedWithoutAnyErrors() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		reporterHelper.takeScreenshot(driver, "Widget Pulled");
		
		kalibrateHelper.syncAllPresentWidgets();
		browserHelper.checkForConsoleErrors();
		
	}


	/// %%%%%% OPEN OPTIONS FROM SITE MANAGER , ADMINISTRATOR, REPORT VIEWER , DATA MANAGER %%%%%%%% \\\ 
	
	
	@Then("^Open \"([^\"]*)\" option in \"([^\"]*)\" widget$")
	public void openOptionInWidget(String widgetoption, String mainwidget) throws Throwable {	

	//Syncing on Kalibrate
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainers,"visible");
	
    reporterHelper.log("Kalibrate Workspace Synced ");
    
    switch(mainwidget) {
	case "Data Manager":
		
		WebElement dmwidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_sync,"present");
		dmwidget.click();
    	reporterHelper.log("Data Manager Clicked");

		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_title,"visible");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_config_SelectedItems,"present");
		BrowserHelper.customSleep(200);
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_config_SelectedItems,"present");
		BrowserHelper.customSleep(2000);
		reporterHelper.log("Data Manager Default Options");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Selected_Options_Container,"present");
		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Selected_Options_List,"present");
		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Selected_Options_List_title,"present");
		//Note: Boolean to identify if required option is available in Data Manager Default Options
		boolean flag = false;
		
   		List <WebElement> option = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Selected_Options_List_title,"present");
   		option.size();
        reporterHelper.log("Total Default options " + option.size());
        System.out.println(option.getClass());
        System.out.println(option.get(0));

      
        List<String> strings = new ArrayList<String>();

        for(WebElement e : option)
        {            	
        	strings.add(e.getText());
        	System.out.println(strings.add(e.getText()));
        	System.out.println(e.getText());

        		if(e.getText().equalsIgnoreCase(widgetoption))
        		{
                   	//Options is listed in Default Option
                			reporterHelper.log(widgetoption + "Option IS AVAILABLE as default");
                			reporterHelper.log("Select Option from Default go to option ");
                			flag = true;
                			break;
                			
                 }
                else
                {
                	
                	//Select Option from Config and select it
        			reporterHelper.log(widgetoption + "Option Not avavible as default");
        			reporterHelper.log("Select Option from Config and select it");
        			flag = false;
                }
        }
        
		if (flag) //When Option is listed in Default
		{
   		List <WebElement> defaultoption = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Selected_Options_List_title,"present");
   		defaultoption.size();
        reporterHelper.log("Total Default options " + option.size());
        List<String> title = new ArrayList<String>();
        for(WebElement text : option){
        	title.add(text.getText());
        	System.out.println(strings.add(text.getText()));
        	System.out.println(text.getText());

        		if(text.getText().equalsIgnoreCase(widgetoption))
        		{
        			reporterHelper.log("Identifed " + widgetoption + "in Default Options");
            		BrowserHelper.customSleep(2000);

            		text.click();
                    reporterHelper.log("Clicked" + widgetoption );
            		BrowserHelper.customSleep(2000);
                	break;
                			
                 }
        }
		}
           
    		else //When Option is not available in Default , select from Config  
    		{
    			reporterHelper.log("\tAdding Options from Data Manager Configuration Menu");
    			WebElement config_spannar = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Configuration_Spannar,"visible");
    			config_spannar.click();
        		BrowserHelper.customSleep(500);
        		
    			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_config_AllItems,"visible");
    			List <WebElement> allOptions =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_config_AllItems_Name,"visible");
    			allOptions.size();
    			 for (int x = 0 ; x <option.size(); x++ )
    	            {
    	           		WebElement option1 = allOptions.get(x);
    	           		option1.getText();
    	                reporterHelper.log("Option: " + x + option1.getText());
    	                if( option1.getText().equalsIgnoreCase(widgetoption))
    	                {
    	                    reporterHelper.log("Identifed " + widgetoption + "in Config Options");
    	            		BrowserHelper.customSleep(500);
    	            		option1.click();
    	                    reporterHelper.log("Clicked" + widgetoption );
    	            		BrowserHelper.customSleep(500);
    	                }
    	            }
 	       	     			 
    			config_spannar.click();
        		BrowserHelper.customSleep(500);
           		List <WebElement> defaultoption2 = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Selected_Options_List_title,"present");
           		defaultoption2.size();
                reporterHelper.log("Total Default options " + defaultoption2.size());
                List<String> title2 = new ArrayList<String>();
                for(WebElement text2 : defaultoption2){
                	title2.add(text2.getText());
                	System.out.println(title2.add(text2.getText()));
                	System.out.println(text2.getText());
       
                		if(text2.getText().equalsIgnoreCase(widgetoption))
                		{
                			reporterHelper.log("Identifed " + widgetoption + "in Default Options");
                    		BrowserHelper.customSleep(2000);

                    		text2.click();
                            reporterHelper.log("Clicked" + widgetoption );
                    		BrowserHelper.customSleep(2000);
                        	break;
                        			
                         }
                }
        		
			break;

        }
		BrowserHelper.customSleep(2000);
        reporterHelper.log("DATA MANAGER");

		break;

            
	case "Site Manager":
		
		
		WebElement smwidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Sync,"present");
    	reporterHelper.log("Data Manager Clicked");

		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_title,"visible");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_availabletabs,"present");
		BrowserHelper.customSleep(200);
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_availabletabs,"present");
		BrowserHelper.customSleep(2000);
		reporterHelper.log("Site  Manager Default Options");

		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_availabletabs_heading,"present");
		//Note: Boolean to identify if required option is available in Site Manager Default Options
		boolean smflag = false;
		
   		List <WebElement> smoption = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_availabletabs_heading,"present");
   		smoption.size();
        reporterHelper.log("Total Default options/tabs " + smoption.size());
        System.out.println(smoption.getClass());
        System.out.println(smoption.get(0));

      
        List<String> stringstab = new ArrayList<String>();

        for(WebElement e : smoption)
        {            	
        	stringstab.add(e.getText());
        	System.out.println(stringstab.add(e.getText()));
        	System.out.println(e.getText());

        		if(e.getText().equalsIgnoreCase(widgetoption))
        		{
                   	//Options is listed in Default Option/Tabsabs
                			reporterHelper.log(widgetoption + "Option IS available as default");
                			reporterHelper.log("Select Option from Default go to option ");
                			smflag = true;
                			break;
                			
                 }
                else
                {
                	
                	//Select Option from Config and select it
        			reporterHelper.log(widgetoption + "Option Not avavible as default");
        			reporterHelper.log("Select Option from Config and select it");
        			smflag = false;
                }
        }
        
		if (smflag) //When Option is listed in Default
		{
   		List <WebElement> defaultoption = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_availabletabs_heading,"present");
   		defaultoption.size();
        reporterHelper.log("Total Default options " + defaultoption.size());
        List<String> title = new ArrayList<String>();
    for(WebElement tabtext : defaultoption){
        	title.add(tabtext.getText());
//        	String heading = null;
		//	title.add(defaultoption.size(), heading);
        	System.out.println(stringstab.add(tabtext.getText()));
        	System.out.println(tabtext.getText());
        	//WebElement text1 = tabtext;
        		if(tabtext.getText().equalsIgnoreCase(widgetoption))
        		{
        			reporterHelper.log("Identifed " + widgetoption + "in Default Options");
            		BrowserHelper.customSleep(2000);
            		Actions action = new Actions(driver);

            		//Focus to element
            		action.moveToElement(tabtext).perform();

            		// To click on the element
            		action.moveToElement(tabtext).click().perform();
            		BrowserHelper.customSleep(1000);

                    reporterHelper.log("Clicked" + widgetoption );
            		BrowserHelper.customSleep(2000);
                	break;
                			
                 }
        }
		}
           
    		else //When Option is not available in Default , select from Config  
    		{
    			reporterHelper.log("\tAdding Options from Data Manager Configuration Menu");
    			WebElement SM_config_spannar = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Configuration_Spannar,"visible");
    			SM_config_spannar.click();
        		BrowserHelper.customSleep(500);
        		
    			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Default_Config,"visible");
    			List <WebElement> sm_allOptions =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Default_Config_List,"visible");
    			sm_allOptions.size();
    			 for (int x = 0 ; x <smoption.size(); x++ )
    	            {
    	           		WebElement option2 = sm_allOptions.get(x);
    	           		option2.getText();
    	                reporterHelper.log("Option: " + x + option2.getText());
    	                if( option2.getText().equalsIgnoreCase(widgetoption))
    	                {
    	                    reporterHelper.log("Identifed " + widgetoption + "in Config Options");
    	            		BrowserHelper.customSleep(500);
    	            		option2.click();
    	                    reporterHelper.log("Clicked" + widgetoption );
    	            		BrowserHelper.customSleep(500);
    	                }
    	            }
 	       	     			 
    			 SM_config_spannar.click();
        		BrowserHelper.customSleep(500);
           		List <WebElement> defaultoption2 = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_availabletabs_heading,"present");
           		defaultoption2.size();
                reporterHelper.log("Total Default options " + defaultoption2.size());
                List<String> title2 = new ArrayList<String>();
                for(WebElement text2 : defaultoption2){
                	title2.add(text2.getText());
                	System.out.println(title2.add(text2.getText()));
                	System.out.println(text2.getText());
       
                		if(text2.getText().equalsIgnoreCase(widgetoption))
                		{
                			reporterHelper.log("Identifed " + widgetoption + "in Default Options");
                    		BrowserHelper.customSleep(2000);

                    		Actions action = new Actions(driver);

                    		//Focus to element
                    		action.moveToElement(text2).perform();

                    		// To click on the element
                    		action.moveToElement(text2).click().perform();
                    		BrowserHelper.customSleep(1000);

                            reporterHelper.log("Clicked" + widgetoption );
                    		BrowserHelper.customSleep(2000);
                        	break;
                        			
                         }
                }
        		
			break;

        }
		BrowserHelper.customSleep(2000);
        reporterHelper.log("Site Manger Options");
		
		
		
	
		break;


		///Administrator
		
		
case "Administrator":
		
		WebElement administrotorwidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Sync,"present");
		administrotorwidget.click();
    	reporterHelper.log("Administrator Clicked");

		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_title,"visible");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Selected_Options_Container,"present");
		BrowserHelper.customSleep(200);
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Selected_Options_Container,"present");
		BrowserHelper.customSleep(2000);
		reporterHelper.log("Administrator Default Options");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Selected_Options_Container,"present");
		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Selected_Options_List,"present");
		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Selected_Options_List_title,"present");
		//Note: Boolean to identify if required option is available in Administrator Default Options
		boolean adminflag = false;
		
   		List <WebElement> adminoption = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Selected_Options_List_title,"present");
   		adminoption.size();
        reporterHelper.log("Total Default options " + adminoption.size());
        System.out.println(adminoption.getClass());
        System.out.println(adminoption.get(0));

      
        List<String> adminstrings = new ArrayList<String>();

        for(WebElement e : adminoption)
        {            	
        	adminstrings.add(e.getText());
        	System.out.println(adminstrings.add(e.getText()));
        	System.out.println(e.getText());

        		if(e.getText().equalsIgnoreCase(widgetoption))
        		{
                   	//Options is listed in Default Option
                			reporterHelper.log(widgetoption + "Option IS AVAILABLE as default");
                			reporterHelper.log("Select Option from Default go to option ");
                			flag = true;
                			break;
                			
                 }
                else
                {
                	
                	//Select Option from Config and select it
        			reporterHelper.log(widgetoption + "Option Not avavible as default");
        			reporterHelper.log("Select Option from Config and select it");
        			flag = false;
                }
        }
        
		if (adminflag) //When Option is listed in Default
		{
   		List <WebElement> admindefaultoption = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Selected_Options_List_title,"present");
   		admindefaultoption.size();
        reporterHelper.log("Total Default options " + adminoption.size());
        List<String> title = new ArrayList<String>();
        for(WebElement text : adminoption){
        	title.add(text.getText());
        	System.out.println(adminstrings.add(text.getText()));
        	System.out.println(text.getText());

        		if(text.getText().equalsIgnoreCase(widgetoption))
        		{
        			reporterHelper.log("Identifed " + widgetoption + "in Default Options");
            		BrowserHelper.customSleep(2000);

            		text.click();
                    reporterHelper.log("Clicked" + widgetoption );
            		BrowserHelper.customSleep(2000);
                	break;
                			
                 }
        }
		}
           
    		else //When Option is not available in Default , select from Config  
    		{
    			reporterHelper.log("\tAdding Options from Administrator Configuration Menu");
    			WebElement config_spannar = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Configuration_Spannar,"visible");
    			config_spannar.click();
        		BrowserHelper.customSleep(500);
        		
    			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_config_AllItems_Name,"visible");
    			List <WebElement> allOptions =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_config_AllItems_Name,"visible");
    			allOptions.size();
    			 for (int x = 0 ; x <adminoption.size(); x++ )
    	            {
    	           		WebElement option1 = allOptions.get(x);
    	           		option1.getText();
    	                reporterHelper.log("Option: " + x + option1.getText());
    	                if( option1.getText().equalsIgnoreCase(widgetoption))
    	                {
    	                    reporterHelper.log("Identifed " + widgetoption + "in Config Options");
    	            		BrowserHelper.customSleep(500);
    	            		option1.click();
    	                    reporterHelper.log("Clicked" + widgetoption );
    	            		BrowserHelper.customSleep(500);
    	                }
    	            }
 	       	     			 
    			config_spannar.click();
        		BrowserHelper.customSleep(500);
           		List <WebElement> defaultoption2 = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Selected_Options_List_title,"present");
           		defaultoption2.size();
                reporterHelper.log("Total Default options " + defaultoption2.size());
                List<String> title2 = new ArrayList<String>();
                for(WebElement text2 : defaultoption2){
                	title2.add(text2.getText());
                	System.out.println(title2.add(text2.getText()));
                	System.out.println(text2.getText());
       
                		if(text2.getText().equalsIgnoreCase(widgetoption))
                		{
                			reporterHelper.log("Identifed " + widgetoption + "in Default Options");
                    		BrowserHelper.customSleep(2000);

                    		text2.click();
                            reporterHelper.log("Clicked" + widgetoption );
                    		BrowserHelper.customSleep(2000);
                        	break;
                        			
                         }
                }
        		
			break;

        }
		BrowserHelper.customSleep(2000);
        reporterHelper.log("Administrator");

		break;


case "Report Viewer":
{
	
	reporterHelper.log("\tAdding Reports in Report Viewer widget");

	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_Sync,"visible");
	WebElement reportsButton =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_ReportsIcon,"present");
	reportsButton.click();
	
	BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_Pricing_Reports_tab,"present");
	
	List <WebElement> reportslist = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_Pricing_Reports_list,"present");
	reportslist.size();
    reporterHelper.log("Total Default options " + reportslist.size());
    System.out.println(reportslist.getClass());
    System.out.println(reportslist.get(0));

  
    List<String> reportstrings = new ArrayList<String>();

    for(WebElement reportname : reportslist)
    {            	
    	reportstrings.add(reportname.getText());
    	System.out.println(reportstrings.add(reportname.getText()));
    	System.out.println(reportname.getText());

    		if(reportname.getText().equalsIgnoreCase(widgetoption))
    		{
               	//Options is listed in Default Option
            			reporterHelper.log(widgetoption + "Option IS AVAILABLE as report");
            			reportname.click();
            			reporterHelper.log(widgetoption + "report Opened");

            			break;
            			
             }
            else
            {
          	
            	//Select Option from Config and select it
    			reporterHelper.log(widgetoption + "Report Not avavible ");
    			reporterHelper.log("Check Report name");
            }
    

    }
	reportsButton.click();
	BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_report_container,"present");

	
} //report viewer
		

		
		
}//Switch Widgets 
    
	BrowserHelper.customSleep(1000);
    reporterHelper.log("Option opened for selected widget");


} 
	
	
	///%%%%%% SETUP NEW OWN SITE %%
	
	

@Then("^User creates a new Own Site with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\",  \"([^\"]*)\", \"([^\"]*)\"$")
public void userCreatesANewOwnSiteWithAndAnd(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	WebElement siteManagerSync =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Sync,"present");
    reporterHelper.log("Site Manager Synced");
    WebElement siteManagerCreateCopylink =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_CreateCopylink,"clickable");
    siteManagerCreateCopylink.click();
    
 
    
	WebElement siteTypeList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_SiteType_List,"present");
		 Select dropdown= new Select(siteTypeList);
		
	 dropdown.selectByVisibleText("Own Site");
	 
	 WebElement SiteName =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailSiteName,"visible");
	 SiteName.sendKeys(arg1);

	 WebElement SiteImportcode =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailImportCode,"visible");
	 SiteImportcode.sendKeys(arg2);
	 	 
 
	 WebElement brandList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailSiteBrandList,"visible");
	 Select brandSelected= new Select(brandList);
	 brandSelected.selectByVisibleText(arg3);
	 reporterHelper.log("Brand Selected for new own site");

	 WebElement areaList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailSiteAreaList,"visible");
	 Select areaSelected= new Select(areaList);
	 areaSelected.selectByVisibleText(arg4);
	 reporterHelper.log("Brand Selected for new own site");

	 
	 WebElement networkList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailSiteNetworkList,"visible");
	 Select networkSelected= new Select(networkList);
	 networkSelected.selectByVisibleText(arg5);
	 reporterHelper.log("Brand Selected for new own site");

	 
	 WebElement createOwnSite=  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Create_Site_Button,"clickable");
	 createOwnSite.click();
	  
     reporterHelper.log("New Retail own Site Created successfully");
     reporterHelper.takeScreenshot(driver,"Own Site created");

     BrowserHelper.customSleep(1000);
//Closing Site Manager Widget after creating Own Site          
     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_CloseWidgetIcon,"visible").click();
     BrowserHelper.customSleep(3000);
} //CrEATE oWN sITES

@When("^Assign Products <Product>$")
public void assignProductsProduct(DataTable arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Sync,"present");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_tab_Sync,"visible");
	//Reading DataTable list to String int i
    List<String> productstring = arg1.asList(String.class);
	for(int i=0; i<productstring.size();i++)
	  {
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Sync,"present");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_tab_Sync,"visible");
    WebElement globalproductlist =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_list_Products,"visible");
	 Select productSelected= new Select(globalproductlist);
	 String product = productstring.get(i);
	 productSelected.selectByVisibleText(product);
	 reporterHelper.log("OwnProduct Selected");
	 
	 	    WebElement addnew =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_Add_New,"visible");

	    addnew.click();
		BrowserHelper.customSleep(1000);

	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProducts_Sync,"visible");
	    WebElement product_Save  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProducts_Save,"visible");

	    product_Save.click();
	    
		 reporterHelper.log("OwnProduct" + arg1 + "Added");
			BrowserHelper.customSleep(2000);

}

}



@When("^\"([^\"]*)\" Products <Product>$")
public void productsProduct(String arg1, DataTable arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    // E,K,V must be a scalar (String, Integer, Date, enum etc)
    
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_tab_Sync,"visible");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_default_list,"visible");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_defaultContainer,"visible");
    List<String> productstring = arg2.asList(String.class);
	
    //Reading DataTable list to String int j and match with Assigned Own Product 

	  for(int j=0; j<productstring.size();j++)
	  {
    
		  	List <WebElement> ownproductlist =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProduct_list_form,"visible");
		  	ownproductlist.size();
		  	System.out.println(ownproductlist.size());
		  	ownproductlist.iterator();		
    
    // Getting the Assigned Products
		  	for (int i = 0 ; i < ownproductlist.size(); i++)
		  	
		  	{
    	
		  		WebElement ownproduct = ownproductlist.get(i);
		  		String productname = ownproduct.getText();
 
		    	reporterHelper.log("OwnProduct Get Text");
		    	System.out.println(ownproduct.getTagName());
				BrowserHelper.customSleep(500);
		    	System.out.println(productname);
		    	String product = productstring.get(j);
		    	reporterHelper.log("OwnProduct to check");
		    	System.out.println(product);

		    	// Compare Own Product with Product selected
		    	if(productname.equalsIgnoreCase(product)) 
		    	{
		    		ownproductlist.get(i).click();
		    		
		  // Assigned Own Product is opened , Active flag to activates Own Product
		    		
		    		WebElement ownproduct_form = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProducts_form,"visible");
		    	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProducts_Active_property,"present");

		            WebElement ownproduct_active_box =     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProducts_Active_checkbox,"present");
			        String classname = ownproduct_active_box.getText();
			        reporterHelper.log(classname);
			        String activeboxclass = ownproduct_active_box.getAttribute("class");
			        reporterHelper.log(activeboxclass );
		    /*
		    Check box status is verified with class name , Selected options do not have ng-not-empty class name 
		    After identifying check box status , based on class  name , click on parent level i.e. div instead of //div//input 
		    http://grokbase.com/t/gg/webdriver/1398r3xqrk/finding-checkbox-by-isselected-not-working-in-html-5-app-looking-for-suggestions
		    Running this approach will in fact toggle the check box; .isSelected() in java/Selenium2 apparently always returns false [at least with the java/selenium/Firefox versions I tested it with].
		    The selection of the proper check box isn't where the problem lies -- rather, it is in distinguishing correctly the initial state to needlessly avoid re-clicking an already checked box.
		    Resolution --https://stackoverflow.com/questions/25396301/isselected-method-for-checkbox-always-returns-false

		    */			    
			        switch(arg1) {
			    	case "Activate":
			    	//Activate Own Product
			        if (activeboxclass.contains("ng-valid ng-empty"))
			        {
				        	BrowserHelper.customSleep(500);
				        	//ownproduct_active_box.click();
				        	//if (!ownproduct_active_box.isSelected())
				        	//{
				        	//Actions act = new Actions(driver);
				        	//act.moveToElement(ownproduct_active_box).click().build().perform();
				        	
				        	JavascriptExecutor js = (JavascriptExecutor) driver;        
				        	js.executeScript("arguments[0].click();", ownproduct_active_box  );
				        	
				        	BrowserHelper.customSleep(500);
	
			        	//}
				        	reporterHelper.log("OwnProduct Activated");
			        }
			        else
			        {
					    	reporterHelper.log("OwnProduct already added");
					    	BrowserHelper.customSleep(1000);
			        }
			        break;
			    	case "Deactivate":
			    		
			    		if (activeboxclass.contains("ng-valid ng-not-empty"))
			    	    {
			    	    	BrowserHelper.customSleep(500);
			    	    	JavascriptExecutor js = (JavascriptExecutor) driver;        
				        	js.executeScript("arguments[0].click();", ownproduct_active_box  );			  
				        	reporterHelper.log("OwnProduct is Inactive now");
			    	    }
			    	    else
			    	    {
				    		reporterHelper.log("OwnProduct already Inactive");
				    		BrowserHelper.customSleep(1000);
			    	    }
	
			    		
			    	break;	
			        }
			        
			        
			    	BrowserHelper.customSleep(1000);
			    	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProducts_form,"visible");
			    		   
			        WebElement ownproduct_Save  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProducts_Update_Save,"clickable");
			        
			        ownproduct_Save.click();
			    	BrowserHelper.customSleep(1000);
			    	
			      //  break;
			    	
			    	WebElement product_navigator = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_title_Sync,"visible");
			    	product_navigator.click();
			    	BrowserHelper.customSleep(3000);

			        

			        reporterHelper.log("PRODUCT TAB CLICKED");

			    	BrowserHelper.customSleep(500);
			        break;
			    	}

		   // When Product is not assigned	
		   else
			    	{
			   		 reporterHelper.log(arg2 + "is not assigned, Please assign ownproduct");
			    	}
			    	
    } // OWN pRODUCST
	  }
} // step close

@When("^\"([^\"]*)\" <Product Groups> Product Group$")
public void productGroupsProductGroup(String arg1, DataTable arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	
	
	
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_tab_Sync,"visible");
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_title_Sync,"visible");

	  List<String> productGrpstring = arg2.asList(String.class);
		
	    //Reading DataTable list to String int j and match with Assigned Own Product 

		  for(int j=0; j< productGrpstring.size();j++)
		  {
	    
			  	List <WebElement> ownproductGrplist =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_list_form, "visible");
			  	ownproductGrplist.size();
			  	System.out.println(ownproductGrplist.size());
			  	ownproductGrplist.iterator();		
	    
	    // Getting the Assigned Products
			  	for (int i = 0 ; i < ownproductGrplist.size(); i++)
			  	
			  	{
	    	
			  		WebElement ownproductGrp = ownproductGrplist.get(i);
			  		String productGrpname = ownproductGrp.getText();
	 
			    	reporterHelper.log("OwnProduct Get Text");
			    	System.out.println(ownproductGrp.getTagName());
					BrowserHelper.customSleep(500);
			    	System.out.println(productGrpname);
			    	String product = productGrpstring.get(j);
			    	reporterHelper.log("OwnProduct to check");
			    	System.out.println(product);

			    	// Compare Own Product with Product selected
			    	if(productGrpname.equalsIgnoreCase(product)) 
			    	{
			    		ownproductGrplist.get(i).click();
			    		
			  // Assigned Own Product is opened , Active flag to activates Own Product
				    	BrowserHelper.customSleep(1000);

			    		WebElement ownproductGrp_form = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_form,"visible");
			    	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Active_property,"present");
				    	BrowserHelper.customSleep(500);

			            WebElement ownproductGrp_active_box =     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Active_checkbox,"present");
				        String classname = ownproductGrp_active_box.getText();
				        reporterHelper.log(classname);
				        String activeboxclass = ownproductGrp_active_box.getAttribute("class");
				        reporterHelper.log(activeboxclass );
			    /*
			    Check box status is verified with class name , Selected options do not have ng-not-empty class name 
			    After identifying check box status , based on class  name , click on parent level i.e. div instead of //div//input 
			    http://grokbase.com/t/gg/webdriver/1398r3xqrk/finding-checkbox-by-isselected-not-working-in-html-5-app-looking-for-suggestions
			    Running this approach will in fact toggle the check box; .isSelected() in java/Selenium2 apparently always returns false [at least with the java/selenium/Firefox versions I tested it with].
			    The selection of the proper check box isn't where the problem lies -- rather, it is in distinguishing correctly the initial state to needlessly avoid re-clicking an already checked box.
			    Resolution --https://stackoverflow.com/questions/25396301/isselected-method-for-checkbox-always-returns-false

			    */			    
				        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Settings_tab,"visible").click();
				    	BrowserHelper.customSleep(500);
				    	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Products_tab,"visible").click();
				    	BrowserHelper.customSleep(500);
				    	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Settings_tab,"visible").click();


				        switch(arg1) {
				    	case "Activate":
				    	//Activate Own Product
				        if (activeboxclass.contains("ng-valid ng-empty"))
				        {
					        	BrowserHelper.customSleep(500);
					        	//ownproduct_active_box.click();
					        	//if (!ownproduct_active_box.isSelected())
					        	//{
					        	//Actions act = new Actions(driver);
					        	//act.moveToElement(ownproduct_active_box).click().build().perform();
					        	
					        	JavascriptExecutor js = (JavascriptExecutor) driver;        
					        	js.executeScript("arguments[0].click();", ownproductGrp_active_box  );
					        	
					        	BrowserHelper.customSleep(500);
		
				        	//}
					        	reporterHelper.log("OwnProduct Group Activated");
				        }
				        else
				        {
						    	reporterHelper.log("OwnProduct already added");
						    	BrowserHelper.customSleep(1000);
				        }
				        break;

				    	case "InActivate":
				    		
				    		//if (activeboxclass.contains("ng-valid ng-not-empty"))
				    	    //{
				    //	    	BrowserHelper.customSleep(500);
				    	    	//ownproductGrp_active_box.click();
				    	    	//JavascriptExecutor js = (JavascriptExecutor) driver;        
					        	//js.executeScript("arguments[0].click();", ownproductGrp_active_box  );			  
					        	reporterHelper.log("OwnProduct Group is Inactive now");
				    	    	BrowserHelper.customSleep(1000);

				    	   /* }//
				    	    else
				    	    {
					    		reporterHelper.log("OwnProduct Group already Inactive");
					    		BrowserHelper.customSleep(1000);
				    	    }
		*/
				    	    	
				    	    	/// TO Remove
				    	    			    	    	
						        WebElement autoprop  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Auto_Implement_property,"visible");

						        
						       WebElement auto  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Auto_Implement_List,"visible");

						       Select dropdown= new Select(auto);
								
						  	 dropdown.selectByVisibleText("Review prices prior to export");

				    		
				    	break;	
				        }
				        
				        
				        
				    	BrowserHelper.customSleep(500);
				    		   
				    	
				    	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Settings_tab,"visible").click();
				    	BrowserHelper.customSleep(500);
				    	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Products_tab,"visible").click();
				    	BrowserHelper.customSleep(500);

				    	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Settings_tab,"visible").click();


				        WebElement ownproductGrp_Save  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Update_Save,"visible");
				        System.out.println("SAVE ELEMENT");

				        System.out.println(ownproductGrp_Save);

				        
				        System.out.println("BEFORE CLCIKING SAVE");

		
					    	BrowserHelper.customSleep(1000);
					    	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_ProductGroups_OwnProductGroup_Update_Save,"clickable");
					 System.exit(0);
					 
					    	//driver.switchTo().defaultContent();
					    	//Actions builder = new Actions(driver);
					    	//Actions(driver).click(ownproductGrp_Save).build().perform();
				        	//js1.executeScript("ownproductGrp_Save.click();" );			  
				        	ownproductGrp_Save.click();
					    	BrowserHelper.customSleep(2000);

					    	ownproductGrp_Save.isEnabled();
				    	
				    	WebElement productGrp_navigator = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_siteManagerWidget_ProductGroups_title_Sync,"visible");
				    	productGrp_navigator.click();
				    	BrowserHelper.customSleep(3000);

				        

				        reporterHelper.log("PRODUCT TAB CLICKED");

				    	BrowserHelper.customSleep(500);
				        break;
				    	}

			   // When Product is not assigned	
			   else
				    	{
				   		 reporterHelper.log(arg2 + "is not assigned, Please assign ownproduct Group");
				    	}
				    	
	    } // OWN pRODUCST Group
		  }

	
} //Activate PG

@When("^Assign <Product Groups> Product Group$")
public void assignProductGroupsProductGroup(DataTable arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    // E,K,V must be a scalar (String, Integer, Date, enum etc)
    throw new PendingException();
} // Assign Product Group

@When("^Add Own Pump Price between \"([^\"]*)\" and \"([^\"]*)\"$")
public void addOwnPumpPriceBetweenAnd(String arg1, String arg2) throws Throwable {

	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Title,"visible");
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Refresh,"visible");
	
	WebElement siteName = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Ownsite_name,"visible");
    reporterHelper.log("Own Site Name " + siteName.getText());
    
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Own_Pump_Price,"visible");
	BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Product_Price_All,"visible");

	List <WebElement> pumppriceValue = 		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Product_Price_Input,"visible");
    reporterHelper.log("Own Site & COmp Products " + pumppriceValue.size());

  	for (int i = 0 ; i < pumppriceValue.size(); i++)
	  	
  	{
  			
  		WebElement pricebox = pumppriceValue.get(i);
   		 float minValue = Float.parseFloat(arg1);
   		 float maxValue = Float.parseFloat(arg2);
   		 double random = (Math.random() * (maxValue - minValue)) + minValue; 
   		 pricebox.clear();
   		 System.out.println(random);
		 String s = Double.toString(random);
		 pricebox.sendKeys(s);
		 BrowserHelper.customSleep(500);
		 }

	BrowserHelper.customSleep(2000);

	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Title,"visible");
	WebElement Apply = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Apply_Price,"visible");

	Apply.click();
	BrowserHelper.customSleep(1000);

	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Title,"visible");
	WebElement reSubmit = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary. kalibrate_pumpPriceUpdateWidget_Resumbit_Price,"visible");

	reSubmit.click();
	
	BrowserHelper.customSleep(1000);

}


@When("^Trigger Price Generation in 'Pricing' widget$")
public void triggerPriceGenerationInPricingWidget() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
   	
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Title,"visible");
    reporterHelper.log("Syncing Retail Own SIte  Pricing widget ");

	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_WidgetLinkedIcon,"visible");
    BrowserHelper.customSleep(2000); 

    WebElement Trigger_Price_1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricing_Widget_Generate_Price_Button_1,"visible");
    Trigger_Price_1.click();

    WebElement Trigger_Price_2 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricing_Widget_Generate_Price_Button_2,"visible");
   	Trigger_Price_2.click();
    
    
 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricing_Widget_Successful_Price_Generation_Notification_Message,"visible");
	reporterHelper.log(" Price Generation triggered Successfully");
   
    BrowserHelper.customSleep(3000); 
   
} //Trigger Price generation



@Then("^User creates a new Competitor Site with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\",  \"([^\"]*)\"$")
public void userCreatesANewCompetitorSiteWithAndAnd(String arg1, String arg2, String arg3, String arg4) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    
	WebElement siteManagerSync =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Sync,"present");
    reporterHelper.log("Site Manager Synced");
    WebElement siteManagerCreateCopylink =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_CreateCopylink,"clickable");
    siteManagerCreateCopylink.click();
        
	WebElement siteTypeList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_SiteType_List,"present");
	Select dropdown= new Select(siteTypeList);
		
	dropdown.selectByVisibleText("Competitor Site");
	 
	WebElement SiteName =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailSiteName,"visible");
	SiteName.click();
	SiteName.sendKeys(arg1);

	 WebElement SiteImportcode =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailImportCode,"visible");
	 SiteImportcode.clear();
	 SiteImportcode.sendKeys(arg2);
	 	 

	 
	 WebElement brandList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailSiteBrandList,"visible");
	 Select brandSelected= new Select(brandList);
	 brandSelected.selectByVisibleText(arg3);
	 reporterHelper.log("Brand Selected for new Comp site");

	 WebElement compGroup =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_RetailCompSiteGroup,"visible");
	 Select compGroupSelected= new Select(compGroup);
	 compGroupSelected.selectByVisibleText(arg4);
	 reporterHelper.log("Group  Selected for new Comp site");

	 	 
	 WebElement createOwnSite=  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Create_Site_Button,"clickable");
	 createOwnSite.click();
	  
     reporterHelper.log("New Retail COMP Site Created successfully");
     reporterHelper.takeScreenshot(driver,"Comp Site created");

     BrowserHelper.customSleep(1000);
//Closing Site Manager Widget after creating DTA          
     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_CloseWidgetIcon,"visible").click();
     BrowserHelper.customSleep(3000);

} // Competitor Site Creation



@When("^Assign Products <Product> for Competitor Site$")
public void assignProductsProductForCompetitorSite(DataTable arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//	kalibrate_siteManagerWidget_Products_list_Products
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Sync,"present");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_tab_Sync,"visible");
	//Reading DataTable list to String int i
    List<String> productstring = arg1.asList(String.class);
	for(int i=0; i<productstring.size();i++)
	  {
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Sync,"present");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_tab_Sync,"visible");
    WebElement globalproductlist =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_list_Products,"visible");
	 Select productSelected= new Select(globalproductlist);
	 String product = productstring.get(i);
	 productSelected.selectByVisibleText(product);
	 reporterHelper.log("CompProduct Selected");
	 
	 	    WebElement addnew =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_Add_New,"visible");

	    addnew.click();
		BrowserHelper.customSleep(500);

	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_Sync,"visible");
	    
	    
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_form,"visible");
		   
   	 WebElement compproduct_active_box =     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_Active_checkbox,"present");
	        String classname1 = compproduct_active_box.getText();
	        reporterHelper.log(classname1);
	        String activeboxclass1 = compproduct_active_box.getAttribute("class");
	        reporterHelper.log(activeboxclass1);
	        compproduct_active_box.click();
	    	BrowserHelper.customSleep(500);
	    
	    
	    WebElement product_Save  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_Save,"visible");

	    product_Save.click();
	    
		 reporterHelper.log("Comp Product" + arg1 + "Added");
			BrowserHelper.customSleep(1000);
	  }


} //Assign COmp Products

@When("^\"([^\"]*)\" Products <Product> for Competitor Site$")
public void productsProductForCompetitorSite(String arg1, DataTable arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_tab_Sync,"visible");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_default_list,"visible");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_defaultContainer,"visible");
    List<String> productstring = arg2.asList(String.class);
	
    //Reading DataTable list to String int j and match with Assigned Own Product 

	  for(int j=0; j<productstring.size();j++)
	  {
    
		  	List <WebElement> ownproductlist =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_OwnProduct_list_form,"visible");
		  	ownproductlist.size();
		  	System.out.println(ownproductlist.size());
		  	ownproductlist.iterator();		
    
    // Getting the Assigned Products
		  	for (int i = 0 ; i < ownproductlist.size(); i++)
		  	
		  	{
    	
		  		WebElement ownproduct = ownproductlist.get(i);
		  		String productname = ownproduct.getText();
 
		    	reporterHelper.log("COMPProduct Get Text");
		    	System.out.println(ownproduct.getTagName());
				BrowserHelper.customSleep(500);
		    	System.out.println(productname);
		    	String product = productstring.get(j);
		    	reporterHelper.log("COMPProduct to check");
		    	System.out.println(product);

		    	// Compare Own Product with Product selected
		    	if(productname.equalsIgnoreCase(product)) 
		    	{
		    		ownproductlist.get(i).click();
		    		
		  // Assigned Own Product is opened , Active flag to activates Own Product
		    		
		    		WebElement ownproduct_form = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_form,"visible");
		    	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_Active_property,"present");

		            WebElement ownproduct_active_box =     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_Active_checkbox,"present");
			        String classname = ownproduct_active_box.getText();
			        reporterHelper.log(classname);
			        String activeboxclass = ownproduct_active_box.getAttribute("class");
			        reporterHelper.log(activeboxclass );
		    /*
		    Check box status is verified with class name , Selected options do not have ng-not-empty class name 
		    After identifying check box status , based on class  name , click on parent level i.e. div instead of //div//input 
		    http://grokbase.com/t/gg/webdriver/1398r3xqrk/finding-checkbox-by-isselected-not-working-in-html-5-app-looking-for-suggestions
		    Running this approach will in fact toggle the check box; .isSelected() in java/Selenium2 apparently always returns false [at least with the java/selenium/Firefox versions I tested it with].
		    The selection of the proper check box isn't where the problem lies -- rather, it is in distinguishing correctly the initial state to needlessly avoid re-clicking an already checked box.
		    Resolution --https://stackoverflow.com/questions/25396301/isselected-method-for-checkbox-always-returns-false

		    */			    
			        switch(arg1) {
			    	case "Activate":
			    	//Activate Own Product
			        if (activeboxclass.contains("ng-valid ng-empty"))
			        {
				        	BrowserHelper.customSleep(500);
				        	ownproduct_active_box.click();
				        	//if (!ownproduct_active_box.isSelected())
				        	//{
				        	//Actions act = new Actions(driver);
				        	//act.moveToElement(ownproduct_active_box).click().build().perform();
				        	
				        	//JavascriptExecutor js = (JavascriptExecutor) driver;        
				        	//js.executeScript("arguments[0].click();", ownproduct_active_box  );
				        	
				        	BrowserHelper.customSleep(500);
	
			        	//}
				        	reporterHelper.log("CompProduct Activated");
			        }
			        else
			        {
					    	reporterHelper.log("CompProduct already added");
					    	BrowserHelper.customSleep(1000);
			        }
			        break;
			    	case "Deactivate":
			    		
			    		if (activeboxclass.contains("ng-valid ng-not-empty"))
			    	    {
			    	    	BrowserHelper.customSleep(500);
			    	    	JavascriptExecutor js = (JavascriptExecutor) driver;        
				        	js.executeScript("arguments[0].click();", ownproduct_active_box  );			  
				        	reporterHelper.log("CompProduct is Inactive now");
			    	    }
			    	    else
			    	    {
				    		reporterHelper.log("CompProduct already Inactive");
				    		BrowserHelper.customSleep(1000);
			    	    }
	
			    		
			    	break;	
			        }
			        
			        
			    	BrowserHelper.customSleep(1000);
			    	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_form,"visible");
			    		   
			    	 WebElement compproduct_active_box =     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_Active_checkbox,"present");
				        String classname1 = ownproduct_active_box.getText();
				        reporterHelper.log(classname);
				        String activeboxclass1 = ownproduct_active_box.getAttribute("class");
				        reporterHelper.log(activeboxclass1);
				        compproduct_active_box.click();
				    	BrowserHelper.customSleep(500);
 
			        WebElement ownproduct_Save  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_CompProducts_Update_Save,"clickable");
			        
			        ownproduct_Save.click();
			    	BrowserHelper.customSleep(1000);
			    	
			      //  break;
			    	
			    	WebElement product_navigator = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Products_title_Sync,"visible");
			    	product_navigator.click();
			    	BrowserHelper.customSleep(3000);

			        

			        reporterHelper.log("PRODUCT TAB CLICKED");

			    	BrowserHelper.customSleep(500);
			        break;
			    	}

		   // When Product is not assigned	
		   else
			    	{
			   		 reporterHelper.log(arg2 + "is not assigned, Please assign Compproduct");
			    	}
			    	
    } // Comp pRODUCST
	  }    
 
  
    
    
} //Active COmp Products



@When("^Search & Add Competitor sites <Comp Site Code> to OwnSite Market$")
public void searchAddCompetitorSitesCompSiteCodeToOwnSiteMarket(DataTable arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    // E,K,V must be a scalar (String, Integer, Date, enum etc)
    
    
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_tab_Sync,"present");
    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_title_Sync,"present");

    reporterHelper.log("Selecting Comp  Sites");
    

	
			List<List<String>> sitecode = arg1.raw();
			int n = sitecode.size();
			for(int i=0; i<n ; i++)
			{
				sitecode.get(0);
				WebElement selecttype = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_selection_box,"clickable");
				Select selectedtype= new Select(selecttype);
				selectedtype.selectByVisibleText("Competitor Site");
				
				
				
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_SearchType,"clickable").click();
			
			WebElement search_CompSite = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_Competitor_Sites,"present");
			search_CompSite.click();
			
				  WebElement search_Box = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_Search_Text_Box,"visible");
				    search_Box.clear();

				    List<String> a = sitecode.get(i);
				    CharSequence[] cs = a.toArray(new CharSequence[a.size()]);
			    
						
				   	search_Box.sendKeys(cs);
				   	WebElement search = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_Search_Button,"clickable");
				    search.click();
					BrowserHelper.customSleep(500);  
				    WebElement selectall = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_Search_SelectAll,"present");
				    //selectall.click();
					BrowserHelper.customSleep(500);  
				    WebElement addSelected = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_Search_Add_Selected,"present");
				    addSelected.click();
					BrowserHelper.customSleep(500);  
				    reporterHelper.log(cs + "Comp Site added to Local Market Own Site");
				    BrowserHelper.customSleep(2000);
			
			}
			BrowserHelper.customSleep(1000);  
			    
    
} //Local Market Comp site

// %%%% Export Data from Kalibrate widgets 

@Then("^Export data from \"([^\"]*)\" Widget$")
public void exportDataFromWidget(String widget) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

	switch(widget)
	{
	
	case"Report Viewer":
		{
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_Sync,"visible");
	
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_report_container,"present");
		
		WebElement reportMenu = 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_report_menu_button,"present");
		reportMenu.click();
		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_report_menu_option_list,"present");

		WebElement reportExport = 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_reportViewerWidget_report_menu_option_Export_All,"present");
		reportExport.click();
		
		BrowserHelper.customSleep(2000);  
		break;
		}
	
		
	case "Pricing":
	{
		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Title,"visible");
		
		WebElement saveExport = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_SaveAndExportIcon,"visible");
		saveExport.click();
		
		WebElement exporttocsv = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ExporttoCsv,"visible");
		exporttocsv.click();
		BrowserHelper.customSleep(2000);  
		saveExport.click();
		break;
	}
	
	case "MultiPricing":
	{

		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync,"visible");
		
		WebElement saveExport = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SaveAndExportIcon,"visible");
		saveExport.click();
		
		WebElement exporttocsv = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ExporttoCsv,"visible");
		exporttocsv.click();
		BrowserHelper.customSleep(2000);  
		saveExport.click();

		break;
	}	
	
	case "Intel":
	{

		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_sync,"visible");
		
		WebElement saveExport = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_SaveandExport,"visible");
		saveExport.click();
		
		WebElement exporttocsv = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_ExporttoCsv,"visible");
		exporttocsv.click();
		BrowserHelper.customSleep(2000);  
		saveExport.click();

		break;
		
		
	}
	} //Switch

} //Export from widget


@Then("^Open \"([^\"]*)\" chart in \"([^\"]*)\" of Intel widget$")
public void openChartInOfIntelWidget(String ChartName, String ChartType) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

	//This Procedure will open any chart from Intel Widget , Chart Name SHOULD match
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_sync,"visible");
	WebElement visulize = 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize,"clickable");
	visulize.click();
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_Sync ,"present");
	
	
	switch (ChartType)
	{
	case "Standard Chart":
	{
		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_Sync ,"present");
	    reporterHelper.log("Checking for Stanadr Chart");
	      BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_StandardChart_ChartList, "visible");

	    List <WebElement> standardChartlist =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_StandardChart_Chartname, "visible");
	    
	    
	    standardChartlist.size();
	  	System.out.println(standardChartlist.size());

	  	for (int i = 0 ; i < standardChartlist.size(); i++)
	  	
	  	{
	
	  		WebElement standardchart = standardChartlist.get(i);
	  		
	  		System.out.println(standardChartlist.get(i));
	  		String standardChartName = standardchart.getText();
	    	System.out.println(standardchart.getText());

	    	System.out.println(standardchart.getTagName());
			BrowserHelper.customSleep(500);
	    	System.out.println(ChartName);
	

	    	// Compare Own Product with Product selected
	    	if(standardChartName.equalsIgnoreCase(ChartName)) 
	    	{
	    		standardChartlist.get(i).click();
	    		reporterHelper.log("Standard Chart" + ChartName + "opened successfully");
	    		BrowserHelper.customSleep(2000);  
		    	break;

	    	}
	    	else
	    	{
		    	reporterHelper.log("Standard Chart is not avavible, Check Chart Name");

	    	}
  
	  	}
	  	break;
	}//standard chart 
	
	case "Custom Chart":
	{
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_Sync ,"present");
	    reporterHelper.log("Checking for Custom Chart");

	       
	    
	   WebElement customtab =   BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_CustomChart_Tab, "visible");
	   customtab.click();
	     BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_CustomChart_ChartList, "visible");
	    List <WebElement> customChartlist =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_CustomChart_Chartname, "visible");
    
	    
	    customChartlist.size();
	  	System.out.println(customChartlist.size());
	  //	customChartlist.iterator();		

// Getting the Assigned Products
	  	for (int i = 0 ; i < customChartlist.size(); i++)
	  	
	  	{
	
	  		WebElement customchart = customChartlist.get(i);
	  		String customChartName = customchart.getText();
	    	System.out.println(customchart.getText());

	    	System.out.println(customchart.getTagName());
			BrowserHelper.customSleep(500);
	    	System.out.println(ChartName);
	

	    	// Compare Own Product with Product selected
	    	if(customChartName.equalsIgnoreCase(ChartName)) 
	    	{
	    		customChartlist.get(i).click();
	    		reporterHelper.log("Custom Chart" + ChartName + "opened successfully");
	    		BrowserHelper.customSleep(2000);  
		    	break;

	    	}
	    	else
	    	{
		    	reporterHelper.log("Custom Chart is not avavible, Check Chart Name or create it");

	    	}
  
	  	}
		break;
	}//Custom chart 
	}//switch chart type
	
	visulize.click();
	reporterHelper.log("Caheck if Chart is loaded correctly");
	
	if (BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_Chart_CoreChart  ,"present") != null)
	{
    	reporterHelper.log("CHART  LOADED WITH DATA");

	}
	else
	{
		if (BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_Visualize_Chart_NoChartdisplayed,"present") != null)
		{
	    	reporterHelper.log("CHART DONOT HAVE ANY DATA");

		}
		else
		{
	    	reporterHelper.log("CHART NOT LOADED");

		}
	}
	
}//open chart


@Then("^Hover & Verify for Lead Follow tool tip$")
public void hoverVerifyForLeadFollowToolTip() throws Throwable {
    // Write code here that turns the phrase above into concrete actions

//Sync to Search widget 
BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Sync,"present");
WebElement Searchresultsync =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSite_SiteImage,"visible");
//reporterHelper.takeScreenshot(driver,"OwnSite Search Iamge");
Actions builder;
builder = new Actions(driver);
WebElement SearchResultImage = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSite_1_Result,"present");
if (BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSite_LeadFollowImage))
{
	WebElement SearchResult_LFIMAGE = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_OwnSite_LeadFollowImage,"present");
	BrowserHelper.customSleep(1000);  

	builder.moveToElement(SearchResultImage);
	builder.build();
	builder.perform();

	//reporterHelper.takeScreenshot(driver,"OwnSite Search Iamge");

	BrowserHelper.customSleep(1000);  
	reporterHelper.log(SearchResult_LFIMAGE.getAttribute("title"));
	String LFTooltip = SearchResult_LFIMAGE.getAttribute("title");
	if (LFTooltip.contains("Sites that lead") || LFTooltip.contains("Sites that follow") )

	{
		reporterHelper.log("Lead & FOLLOW Toll Tip is displayed in Kalibrate Search Widget");
	}
	else
	{
		reporterHelper.log("Lead Follow Tool tip is incorrect");
	}
}
else
{
	reporterHelper.log("Selected site donot have LEAD And FOLLOW Relationship");
	
}
}//Lead Follow Tool tip 


} //Retail E2E

