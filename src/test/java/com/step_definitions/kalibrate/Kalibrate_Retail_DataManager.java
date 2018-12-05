package com.step_definitions.kalibrate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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


public class Kalibrate_Retail_DataManager {
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
	
	@When("^Add all options as default options in Data Manager$")
	public void addAllOptionsAsDefaultOptionsInDataManager() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//Sync DTA Site Manager with Close button & DTA Name in title 
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_sync,"present");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_title,"visible");
		
		
		WebElement config_spannar = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Configuration_Spannar,"visible");
		config_spannar.click();
		
		WebElement default_option = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Default_Config ,"visible");
		default_option.click();
		List<WebElement> List = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Default_Config_List  ,"present");
		 List<WebElement> dataManager_Config_CheckBox =BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Default_Config_Check_Box ,"present");
		 List<WebElement> dataManager_Config_CheckBox1 =BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Default_Config_Check_Box_1 ,"present");

		    reporterHelper.log("No of Data Manager Configuration available" + dataManager_Config_CheckBox.size());

		    for(int i=0; i<dataManager_Config_CheckBox.size(); i++){
			    reporterHelper.log("DTA_Config_CheckBox.get(i)" + dataManager_Config_CheckBox.get(i));
			    String isChecked; 
			    WebElement box = dataManager_Config_CheckBox.get(i);
			    WebElement box1 = dataManager_Config_CheckBox1.get(i);
			    isChecked = box.getAttribute("checked");
			    
			    reporterHelper.log("Box Checked" + box.getAttribute("checked"));
			    reporterHelper.log("DTA_Config_CheckBox.get(i)" + box.getAttribute("class"));
			    
			    String confclass = box.getAttribute("class");
			    reporterHelper.log(confclass);

/*
Check box status is verified with class name , Selected options do not have ng-not-empty class name 
After identifying check box status , based on class  name , click on parent level i.e. div instead of //div//input 
http://grokbase.com/t/gg/webdriver/1398r3xqrk/finding-checkbox-by-isselected-not-working-in-html-5-app-looking-for-suggestions
Running this approach will in fact toggle the check box; .isSelected() in java/Selenium2 apparently always returns false [at least with the java/selenium/Firefox versions I tested it with].
The selection of the proper check box isn't where the problem lies -- rather, it is in distinguishing correctly the initial state to needlessly avoid re-clicking an already checked box.
Resolution --https://stackoverflow.com/questions/25396301/isselected-method-for-checkbox-always-returns-false

*/			    
			    if (confclass.contains("ng-valid ng-empty"))
			    {
			    	BrowserHelper.customSleep(500);
			    	box1.click();
					    reporterHelper.log("Configs added now");
			    }
			    else
			    {
				reporterHelper.log("Configs already added");
	    		BrowserHelper.customSleep(1000);
			    }
		    }
		    
			WebElement apply = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_Default_Config_Apply_Button ,"visible");
			apply.click();
		
		    BrowserHelper.customSleep(500);
		
	}


/* MOVED TO E2E .JAVA
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
//            	String heading = null;
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

			
			
	}//Switch
		BrowserHelper.customSleep(1000);
        reporterHelper.log("Option opened for selected widget");


	}//Open Option Function
	
	*/
	
	
	
	
	@Then("^Create a new Sales Breakdown <Sales BreakDown>$")
	public void createANewSalesBreakdownSalesBreakDown(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		reporterHelper.log("Creating a new QA Sales Breakdown");
		 List<List<String>> salestype = arg1.raw();
			int n = salestype.size();
			for(int i=0; i<n ; i++)
		{
			List<String> a = salestype.get(i);
			CharSequence[] cs = a.toArray(new CharSequence[a.size()]);
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_Add_new_Textbox ,"visible").sendKeys("QA");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_Add_new_Button ,"clickable").click();
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_new_form_save ,"visible");
			WebElement QA_Sales_BreakDown_Name = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_new_name ,"visible");
			QA_Sales_BreakDown_Name.clear();
			salestype.get(0);
			 System.out.println("Gayathri" + cs);
			 System.out.println("Gayathri" + cs);
			 QA_Sales_BreakDown_Name.sendKeys(cs);
			 WebElement QA_Sales_BreakDown_Code = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_new_importCode ,"visible");
			 QA_Sales_BreakDown_Code.clear();
			 QA_Sales_BreakDown_Code.sendKeys(cs);
		
		   
		WebElement QA_Sales_BreakDown_Aggregation = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_new_include_agg ,"visible");
		QA_Sales_BreakDown_Aggregation.click();
		
		WebElement select_payment_type = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_new_Payment_Type ,"visible");
		Select sales_select_payment_type= new Select(select_payment_type);
		sales_select_payment_type.selectByVisibleText("All");
		
		WebElement select_payment_offset = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_new_Payment_Offset ,"visible");
        Select sales_select_payment_offset = new Select(select_payment_offset);
		sales_select_payment_offset.selectByVisibleText("None");
			 
		WebElement save1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_new_form_save ,"visible");
		save1.click();
	
		WebElement banner = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close ,"visible");

		boolean status = banner.isDisplayed();
		if (status)
				{
		
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close,"visible");
				reporterHelper.takeScreenshot(driver, "Sales Type Created");
	       		WebElement bannar_close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_close,"clickable");
	       		bannar_close.click();
	            reporterHelper.log("Success Bannar Closed");
	    		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close,"present");
	
	    		reporterHelper.log(bannar_close.getText());
	    		System.out.println(status);

	    		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close)) 
	    				{
	    		
	            		reporterHelper.log("Bannar Closed already");
	    	       		WebElement sync = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_SalesTypes_sync,"clickable");
	    				sync.click();
	    	            reporterHelper.log("Sales Types Sync");
	
						}
					}
    	else
    	{         
    		reporterHelper.log("Cannot Create a new Sales Type , Please refer to Screen shot for error");
            reporterHelper.takeScreenshot(driver, "Error for new Sales Type");

    	}
	}
			
	}

	@Then("^Create a new Cost types <Cost Breakdown>$")
	public void createANewCostTypesCostBreakdown(DataTable CostTypes) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		 reporterHelper.log("Creating  new Cost  Breakdown Types");
		 List<List<String>> costbreak = CostTypes.raw();
			int n = costbreak.size();
			for(int i=0; i<n ; i++)
		{
			List<String> a = costbreak.get(i);
			CharSequence[] cs = a.toArray(new CharSequence[a.size()]);

			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync ,"visible");
					
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_Add_new_Textbox ,"visible").sendKeys("QA");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_Add_new_Button ,"clickable").click();
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_form_sync ,"visible");
			WebElement cost_break_1_Name = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_name ,"visible");
			cost_break_1_Name.clear();
			cost_break_1_Name.sendKeys(cs);
			WebElement cost_break_1_Import_Code = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_importCode ,"visible");
			cost_break_1_Import_Code.clear();
			cost_break_1_Import_Code.sendKeys(cs);
			WebElement cost_break_1_PricingWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_Pricing_Widget ,"visible");
			cost_break_1_PricingWidget.click();
			WebElement cost_break_1_Validate = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_Validate ,"visible");
			cost_break_1_Validate.click();
			WebElement save1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_form_save ,"visible");
			save1.click();
			WebElement banner1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close ,"visible");

			boolean status1 = banner1.isDisplayed();
			if (status1)
			{
			BrowserHelper.customSleep(500);

			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close,"visible");
	   		WebElement bannar_close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_close,"clickable");
	   		bannar_close.click();
	        reporterHelper.log("Success Bannar Closed");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close,"present");

			BrowserHelper.customSleep(500);
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync,"present");


			if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close)) 
					{
			
	        		reporterHelper.log("Bannar Closed already");
	    			BrowserHelper.customSleep(500);

		       		WebElement cost_sync_1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync,"clickable");
		       		cost_sync_1.click();
		            reporterHelper.log("Cost Types Sync");

					}
				}
			else
			{         
				reporterHelper.log("Cannot Create a new Sales Type , Please refer to Screen shot for error");
		        reporterHelper.takeScreenshot(driver, "Error for Cost Type");
		
			}
	}
}

	@Then("^Create a new aggregated Cost type$")
	public void createANewAggregatedCostType(DataTable AggCost) throws Throwable 
	{
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		 reporterHelper.log("Creating  new Aggregated Cost  Breakdown Type");
		 
			for (Map<String, String> data : AggCost.asMaps(String.class, String.class)) 
			
			{
				
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync ,"visible");
					
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_Add_new_Textbox ,"visible").sendKeys("QA");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_Add_new_Button ,"clickable").click();
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_form_sync ,"visible");
			WebElement cost_break_1_Name = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_name ,"visible");
			cost_break_1_Name.clear();
			cost_break_1_Name.sendKeys(data.get("AggregateCostName"));
			WebElement cost_break_1_Import_Code = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_importCode ,"visible");
			cost_break_1_Import_Code.clear();
			cost_break_1_Import_Code.sendKeys(data.get("AggregateCostName"));
			WebElement cost_break_1_PricingWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_Pricing_Widget ,"visible");
			cost_break_1_PricingWidget.click();
			WebElement cost_break_1_Validate = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_Validate ,"visible");
			cost_break_1_Validate.click();
			
			reporterHelper.log("Creating QA_Aggregate_Cost Formula" );

			WebElement QA_Aggregate_Cost_Aggregation = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_AggregatedCost ,"visible");
			QA_Aggregate_Cost_Aggregation.click();
			WebElement QA_Aggregate_Cost_Aggregation_Formula = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_form_AggregatedCost_Formula ,"visible");
			QA_Aggregate_Cost_Aggregation_Formula.clear();
			QA_Aggregate_Cost_Aggregation_Formula.sendKeys(data.get("AggregatedCostFormula"));

			//Make as Total cost
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync ,"visible");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_form_sync ,"visible");
			WebElement QA_Aggregate_Cost_Total_Cost = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_form_TotalCost ,"visible");
			QA_Aggregate_Cost_Total_Cost.click();
						
			WebElement save1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_new_form_save ,"visible");
			save1.click();
			WebElement banner1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close ,"visible");

			boolean status1 = banner1.isDisplayed();
			if (status1)
			{

			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close,"visible");
	   		WebElement bannar_close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_close,"clickable");
	   		bannar_close.click();
	        reporterHelper.log("Success Bannar Closed");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close,"present");

			BrowserHelper.customSleep(500);

			if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close)) 
					{
			
	        		reporterHelper.log("Bannar Closed already");
		       		WebElement cost_sync_1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync,"clickable");
		       		cost_sync_1.click();
		            reporterHelper.log("Cost Types Sync");

					}
				}
			else
			{         
				reporterHelper.log("Cannot Create a new Sales Type , Please refer to Screen shot for error");
		        reporterHelper.takeScreenshot(driver, "Error for Cost Type");
		
			}
	}
			}
	
	
	@Then("^Flag the Cost types to display in Pricing widget$")
	public void flagTheCostTypesToDisplayInPricingWidget() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync ,"visible");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync ,"visible").click();

		WebElement selectall = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_SelectAll_checkbox ,"clickable");
		selectall.click();
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_CurrentSelection ,"visible").click();
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_MultipleCostType_Save ,"visible");
		
		WebElement select_option = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_MultipleCostType_DisplayOnPricingWidget ,"visible");

		 Select multiple_cost_types_pricing= new Select(select_option);
			
		 multiple_cost_types_pricing.selectByVisibleText("Yes");
		 
			WebElement multiple_save = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_MultipleCostType_Save ,"clickable");
			multiple_save.click();
			WebElement banner = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close ,"visible");

			boolean status = banner.isDisplayed();
			if (status)
			{
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close,"visible");
       		WebElement bannar_close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_close,"clickable");
       		bannar_close.click();
            reporterHelper.log("Success Bannar Closed");
    		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close,"present");
    		BrowserHelper.customSleep(500);


    		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close)) 
    				{
    		
            		reporterHelper.log("Bannar Closed already");
    	       		WebElement sync = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CostTypes_sync,"clickable");
    				sync.click();
    	            reporterHelper.log("Cost Types Sync");

					}
				}
			else
			{         
				reporterHelper.log("Cannot Create a new Sales Type , Please refer to Screen shot for error");
		        reporterHelper.takeScreenshot(driver, "Error for Cost Type");
		
			}
	}
	@Then("^Create new custom data visible in Pricing widget$")
	public void createNewCustomDataVisibleInPricingWidget(DataTable customData) throws Throwable 
	{
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		reporterHelper.log("Creating Custom Data");
		
		for (Map<String, String> data : customData.asMaps(String.class, String.class)) 
			
		{
				
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_sync,"visible");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_Add_new_Textbox ,"visible").sendKeys("QA");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_Add_new_Button ,"clickable").click();
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_new_form_sync ,"visible");
		WebElement Custom_QA_Cost_Name = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_new_name ,"visible");
		Custom_QA_Cost_Name.clear();
		Custom_QA_Cost_Name.sendKeys(data.get("CustomData"));
		WebElement Custom_QA_Cost_code = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_new_importCode ,"visible");
		Custom_QA_Cost_code.clear();
		Custom_QA_Cost_code.sendKeys(data.get("CustomData"));
		WebElement Custom_QA_Cost_formula = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_new_formula ,"visible");
		Custom_QA_Cost_formula.clear();
		Custom_QA_Cost_formula.sendKeys(data.get("CustomDataFormula"));	
		
		WebElement Custom_QA_Cost_in_pricing = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_new_Pricing_Widget ,"visible");
		Custom_QA_Cost_in_pricing.click();
		
		if (BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_new_form_save))
		{
			WebElement save1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_new_form_save ,"visible");
			save1.click();
			reporterHelper.log("Custom Data Cost Created");
		    reporterHelper.takeScreenshot(driver, (data.get("CustomData")) + "New Custom Data Cost Created");
		}
		else
		{
			reporterHelper.log("Cannot Create a new Custom Data Price Type , Save button disabled, Refer to Screenshot");
		    reporterHelper.takeScreenshot(driver, "Error for new Custom Data Cost");
		}
		
		WebElement banner_cost = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close ,"visible");

		boolean status = banner_cost.isDisplayed();
		if (status)
		{
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close,"visible");
		reporterHelper.takeScreenshot(driver, "Custom Data Cost Created");
   		WebElement banner_cost_close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_close,"clickable");
   		banner_cost_close.click();
		BrowserHelper.customSleep(500);
   		reporterHelper.log("Success Bannar Closed");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close,"present");

		reporterHelper.log(banner_cost_close.getText());
		System.out.println(status);
		BrowserHelper.customSleep(500);
		BrowserHelper.customSleep(500);

		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close)) 
				{
			BrowserHelper.customSleep(500);

        		reporterHelper.log("Bannar Closed already");
        		BrowserHelper.customSleep(500);

	       		WebElement sync_c = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CustomData_sync,"clickable");
	       		sync_c.click();
	            reporterHelper.log("Custom Data Created");

				}
			}
		else
		{         
			reporterHelper.log("Cannot Create a new Custom Data Type , Please refer to Screen shot for error");
		    reporterHelper.takeScreenshot(driver, "Error for new Custom Data Cost");
		
		}

	
	
		}
	
		
	
	}

	@Then("^Create new data sources visible in Pricing & Map widget <Data Source>$")
	public void createNewDataSourcesVisibleInPricingMapWidgetDataSource(DataTable csource) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    reporterHelper.log("Adding Competitor Data Source");
		
		 reporterHelper.log("Creating  new Cost  Breakdown Types");
		 List<List<String>> compsource = csource.raw();
			int n = compsource.size();
			for(int i=0; i<n ; i++)
		{
			List<String> a = compsource.get(i);
			CharSequence[] cs = a.toArray(new CharSequence[a.size()]);

	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_sync ,"visible");

				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_sync ,"visible");
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_Add_new_Textbox ,"visible").sendKeys("test");
				WebElement addNew = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_Add_new_Button ,"visible");
				addNew.click();

				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_new_form ,"visible");
		
	            System.out.println("Adding Source" + cs);
	            WebElement competitorDataSoruce = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_new_form_name ,"visible");
	            competitorDataSoruce.clear();
	            competitorDataSoruce.sendKeys(cs);
	    		WebElement displayMap = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_new_form_map ,"visible");
	    		displayMap.click();
	    		WebElement displayPricing = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_new_form_pricing ,"visible");
	    		displayPricing.click();

	    		WebElement sourceSave = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_save ,"visible");
	    		sourceSave.click();
	    		
	    		WebElement banner = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close ,"visible");

	    		boolean status = banner.isDisplayed();
	    		if (status)
	    		{
		
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_before_close,"visible");
	       		WebElement bannar_close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_close,"clickable");
	       		bannar_close.click();
	    		BrowserHelper.customSleep(1000);

	            reporterHelper.log("Success Bannar Closed");
	    		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close,"present");

	    		BrowserHelper.customSleep(1000);
	    		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_notification_banner_success_sync_after_close)) 
	    				{
	    		
	            		reporterHelper.log("Bannar Closed already");
	    	       		WebElement source_sync = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_DataSource_sync,"clickable");
	    	       		source_sync.click();
	    	            reporterHelper.log("Data Source Sync");

						}
					}
				else
				{         
					reporterHelper.log("Cannot Create a new Sales Type , Please refer to Screen shot for error");
			        reporterHelper.takeScreenshot(driver, "Error for Data Source");
			
				}
			}
            reporterHelper.log("Data Source Sync");

	}
	
}