package com.step_definitions.kalibrate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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


public class Kalibrate_DealerPricing_StepDefinitions {

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


	private Kalibrate_StepDefinitions kalibratemain = new Kalibrate_StepDefinitions ();
	
	
	@Then("^User creates a new Dealer Trade Area with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void userCreatesANewDealerTradeAreaWithAnd(String arg1, String arg2) throws Throwable {
		
		WebElement siteManagerSync =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Sync,"present");
	    reporterHelper.log("Site Manager Synced");
	    WebElement siteManagerCreateCopylink =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_CreateCopylink,"clickable");
	    siteManagerCreateCopylink.click();
	    
	    
	    /*WebElement Brandlist = form.findElement(By.xpath("//form/div/div[5]/div[2]/div/select"));
		 System.out.println("Brand");
		 Select dropdown= new Select(Brandlist);
		 dropdown.selectByVisibleText("Kilo");
		 */
	    
		WebElement siteTypeList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_SiteType_List,"present");
			 Select dropdown= new Select(siteTypeList);
			
		 dropdown.selectByVisibleText("Dealer Trade Area");
		 
		 WebElement dtaAreaName =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_DTAAreaname,"visible");
		 dtaAreaName.sendKeys(arg1);
	
		 WebElement dtaImportcode =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_DTAImportCode,"visible");
		 dtaImportcode.sendKeys(arg2);
		 	 
		 WebElement dtaManagerName =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_DTAManagerName,"visible");
		 dtaManagerName.sendKeys("Mr.DTA Manager");
		 
		 WebElement dtaManagerEmail =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_DTAManagerEmail,"visible");
		 dtaManagerEmail.sendKeys("DTAManager@test.com");
		 
		 WebElement dtaManagerPhone =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_DTAManagerPhone,"visible");
		 dtaManagerPhone.sendKeys("0123456789");
		 
		 WebElement createDTA =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Create_Site_Button,"clickable");
		 createDTA.click();
		  
	     reporterHelper.log("Trade Area Created successfully");
	     reporterHelper.takeScreenshot(driver,"DTA Created");
	
	     BrowserHelper.customSleep(1000);
	//Closing Site Manager Widget after creating DTA          
	     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Widget_CloseWidgetIcon,"visible").click();
	     BrowserHelper.customSleep(3000);
	
	}
	
	@Then("^User can search & select DTA \"([^\"]*)\"$")
	public void userCanSearchSelectDTA(String arg1) throws Throwable {
		//Kalibrate_MainPage_StepDefinitions main = new Kalibrate_MainPage_StepDefinitions ();
		BrowserHelper.customSleep(2000);
		kalibratemain.pullWidgetTo("Search","Container1");
		
		//userPullsWidgetToWorkspace("Search","Container1");
		WebElement searchType =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchTypeDropDownButton,"clickable");
		searchType.click();
		WebElement selectDTAType =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SelectDTA,"clickable");
		selectDTAType.click();
		WebElement DTAtab =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTATab,"present");
	    reporterHelper.log("Dealer Trade Area selected for Search Type");
	    
	    WebElement DTAname =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput,"present");
	    DTAname.clear();
	    DTAname.sendKeys(arg1);
	    WebElement DTASearch =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton,"present");
	    DTASearch.click();
		BrowserHelper.customSleep(3000);
		WebElement Searchresultsync =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Search_ResultsCountText_SingleResult,"visible");
	    reporterHelper.log("DTA listed in search result");
	    reporterHelper.takeScreenshot(driver,"DTA Search");
	    
	    
		BrowserHelper.customSleep(1000);
	
		WebElement DTAResult_1 =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTASearch_1_Result,"visible");
		DTAResult_1.click();
		BrowserHelper.customSleep(1000);
		
	    
	
	}
	
	
	@Given("^Pull \"([^\"]*)\" widget for selected DTA$")
	public void pullWidgetForSelectedDTA(String arg1) throws Throwable {
	// Write code here that turns the phrase above into concrete actions
	//Initiate mouse action using Actions class
	    Actions builder;
	    builder = new Actions(driver);
		WebElement container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer2,"present");
	
		WebElement DTAHovericons =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTAhovericon,"visible");
	// Move the mouse to the earlier identified menu option - Hovering Menu options
		    reporterHelper.log("Hoverdone");
		    builder.moveToElement(DTAHovericons).perform();
			BrowserHelper.customSleep(1000);
		    reporterHelper.log("Hoverdone");
	//Selecting sub menus    
		   Actions builder2;
		    Action dragAndDrop;
				  builder2 = new Actions(driver);
				  WebElement requiredDealerWidget;
				  requiredDealerWidget=BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon,"present");
				  //WebElement container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer2,"present");
	/*
	 * Pricing Widget - Container 2
	 * Site Manager - Container 3
	 * Intel - Container 3
	 * Note - Container 1
	 * Strategy - Container 1 
	 */
				  
					switch (arg1)
					{
					case "Pricing":
						
					{
						requiredDealerWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTAPricing,"visible");
				       reporterHelper.log("DTA Pricing Widgets");
				       reporterHelper.log(requiredDealerWidget.getAttribute("src"));	
				       reporterHelper.log(requiredDealerWidget.getAttribute("class"));
					  //  container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer2,"present");
	
				       break;     
					}
					case "Site Manager":
						
					{
					requiredDealerWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTASiteManager,"visible");
				       reporterHelper.log("DTA Site Manager Widgets");
				       reporterHelper.log(requiredDealerWidget.getAttribute("src"));	
				       reporterHelper.log(requiredDealerWidget.getAttribute("class"));
				   	// container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer3,"present");
	
				       break;     
					}
					
					case "Intel":
						
					{
					requiredDealerWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTAIntel,"visible");
				       reporterHelper.log("DTA Site Manager Widgets");
				       reporterHelper.log(requiredDealerWidget.getAttribute("src"));	
				       reporterHelper.log(requiredDealerWidget.getAttribute("class"));
					   container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer3,"present");
				       break;  
				       
					}
					
					case "Notes":
		
					{
						requiredDealerWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTANotes,"visible");
						reporterHelper.log("DTA Site Manager Widgets");
						reporterHelper.log(requiredDealerWidget.getAttribute("src"));	
						reporterHelper.log(requiredDealerWidget.getAttribute("class"));
						container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer1,"present");
	
						break;     
					}
					case "Dealer Trade Strategy":
		
					{
					requiredDealerWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTAStrategy,"visible");
					   reporterHelper.log("DTA Site Manager Widgets");
					   reporterHelper.log(requiredDealerWidget.getAttribute("src"));	
					   reporterHelper.log(requiredDealerWidget.getAttribute("class"));
					   container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer1,"present");
					   break;     
					}
	
					}	
				  
	// Drag & drop available widgets 
	// Added 	moveByOffset(1,1) as objects disappears quickly 			
			    dragAndDrop = builder2.clickAndHold(requiredDealerWidget).moveByOffset(1,1)
					   .moveToElement(container)
		    	      .release(container)	         
		    	      .build();
	    
			       dragAndDrop.perform();
			    reporterHelper.log(arg1 +"Widgets Opened for selected DTA");  
				BrowserHelper.customSleep(1000);
	}
	
	
	
	@When("^Select all DTA Options are Default DTA Site Manager options$")
	public void selectAllDTAOptionsAreDefaultDTASiteManagerOptions() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
	//Sync DTA Site Manager with Close button & DTA Name in title 
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Sync,"present");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DTA_NAME,"visible");
		
		
		WebElement config_spannar = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Configuration_Spannar,"visible");
		config_spannar.click();
		
		WebElement default_option = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Default_Config ,"visible");
		default_option.click();
		List<WebElement> List = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Default_Config_List  ,"present");
	//Check_box is //div//input level 
	//Check box_1 is //div
	//using Check_box xpath, Check box are not checked or not visible to click the same 
	
		 List<WebElement> DTA_Config_CheckBox =BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Default_Config_Check_Box ,"present");
		 List<WebElement> DTA_Config_CheckBox1 =BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Default_Config_Check_Box_1 ,"present");
	
		    
		    reporterHelper.log("No of DTA Configuration available" + DTA_Config_CheckBox.size());
	
		    for(int i=0; i<DTA_Config_CheckBox.size(); i++){
			    reporterHelper.log("DTA_Config_CheckBox.get(i)" + DTA_Config_CheckBox.get(i));
			    String isChecked; 
			    WebElement box = DTA_Config_CheckBox.get(i);
			    WebElement box1 = DTA_Config_CheckBox1.get(i);
			    isChecked = box.getAttribute("checked");
			    
			    reporterHelper.log("Box Checked" + box.getAttribute("checked"));
			    reporterHelper.log("DTA_Config_CheckBox.get(i)" + box.getAttribute("class"));
			    
			    String confclass = box.getAttribute("class");
			    reporterHelper.log(confclass);
			    
	//Check box status is verified with class name , Selected options do not have ng-not-empty class name 
	// After identifying check box status , based on class  name , click on parent level i.e. div instead of //div//input 
	// 		    http://grokbase.com/t/gg/webdriver/1398r3xqrk/finding-checkbox-by-isselected-not-working-in-html-5-app-looking-for-suggestions
	 /*
	Running this approach will in fact toggle the checkbox; .isSelected() in java/Selenium2 apparently always returns false [at least with the java/selenium/Firefox versions I tested it with].
	The selection of the proper checkbox isn't where the problem lies -- rather, it is in distinguishing correctly the initial state to needlessly avoid re-clicking an already checked box.
	*/
	// Resolution --https://stackoverflow.com/questions/25396301/isselected-method-for-checkbox-always-returns-false
			    
			    if (confclass.contains("ng-valid ng-empty"))
			    
			    //if(isChecked != null)
			    {
			    	BrowserHelper.customSleep(500);
			    	box1.click();
					    reporterHelper.log("Configs added now");
			    }
			    else
			    {
				reporterHelper.log("Configs already added");
	    		//box.click();
	    		BrowserHelper.customSleep(1000);
			    }
		    }
		    
	    
			WebElement apply = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Default_Config_Apply_Button ,"visible");
			apply.click();
			WebElement config_spannar1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Configuration_Spannar,"visible");
			config_spannar1.click();
			BrowserHelper.customSleep(2000);
	
	
	}
	
	
	
	
	@When("^Go to Products tab & assign Products$")
	public void goToProductsTabAssignProducts() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	//Sync DTA Site Manager with Close button & DTA Name in title 
		BrowserHelper.customSleep(1000);
	
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Sync,"present");
		WebElement name = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DTA_NAME,"visible");
		name.getText();
	    reporterHelper.log("DTA NAME" + name.getText());
	
		WebElement DTAProducts = 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Products_tab,"clickable");
		DTAProducts.click();
		int i = 1; 
		int j;
				 do
				 {
					 WebElement productList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Products_tab_ProductList,"present");
					 Select dropdown= new Select(productList);
					 List<WebElement> options = dropdown.getOptions(); 
					 BrowserHelper.customSleep(2000);
					 System.out.println(" Available Products" + options.size());
					 j = options.size();
					    BrowserHelper.customSleep(1000);
	
				     dropdown.selectByIndex(i);
				     System.out.println(i);
					    reporterHelper.log("Finding Add New");
					 WebElement addnew = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Products_tab_Product_Add_new,"clickable");
					 addnew.click();
					    reporterHelper.log("Add New Clicked");
					 WebElement active = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Products_tab_Product_Active,"clickable");
					 if ( !active.isSelected() )
					 {
						 active.click();
					 }
					    reporterHelper.log("OwnProduct Activated");
					WebElement saveproduct = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Products_tab_Product_Save,"clickable");
					saveproduct.click();
				    reporterHelper.log("OwnProduct added");
	
					j--;
				    reporterHelper.log("Loop 2" + i);
					System.out.println("i=" + i);
					System.out.println("j=" + j);
				    BrowserHelper.customSleep(2000);
					BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Products_tab,"clickable");
	
	    }while(j!=i);
				 /*
	//Close DTA Site Manager Widget
				 reporterHelper.log("Closing DTA Site Manager ");
	
			    WebElement close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Widget_CloseWidgetIcon,"visible");
			    close.click();
			    BrowserHelper.customSleep(500);
	//Closing Search
			     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"visible").click();
			    
			    BrowserHelper.customSleep(3000);*/
	
	}
	
	@When("^Goto Product groups tab & assign activate groups$")
	public void gotoProductGroupsTabAssignActivateGroups() throws Throwable {
		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Sync,"present");
	
	    reporterHelper.log("Product Groups");
	
		WebElement productgroup = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Product_Groups_tab,"present");
		productgroup.click();
	    reporterHelper.log("Product Groups Opened");
	
	//	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Product_Groups_sync,"visible");
		
		//Inactivate All Products
	    reporterHelper.log("Inactivating All Products ");
	
	    WebElement AllProducts = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Product_Groups_All_Products,"visible");
	    AllProducts.click();
	    reporterHelper.log("All Products Opened");
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Own_Product_Groups_sync,"visible");
	    WebElement active = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Own_Product_Groups_Active,"clickable");
		 if ( active.isSelected() )
		 {
			 active.click();
		 }
	     reporterHelper.log("All Products Inactive");
		    BrowserHelper.customSleep(5000);
		    /*    
		    //Close DTA Site Manager Widget
		     reporterHelper.log("Closing DTA Site Manager ");
	
		    WebElement close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Widget_CloseWidgetIcon,"visible");
		    close.click();
		    BrowserHelper.customSleep(2000);
	
		     BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"visible").click();
		    
		    BrowserHelper.customSleep(10000); */
	
		    
	}
	
	
	@When("^Go to 'Dealer Pricing - Costs' Tab$")
	public void goToDealerPricingCostsTab() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	//Sync DTA Site Manager with Close button & DTA Name in title 
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Sync,"present");
		WebElement DTA_COSTS = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_tab,"present");
		DTA_COSTS.click();
	    reporterHelper.log("DEALER PRICING - COSTS Tab Opened");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Sync,"present");
		BrowserHelper.customSleep(500);
	
	}
	
	@When("^Configure Base Cost as \"([^\"]*)\"$")
	public void configureBaseCostAs(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	//Sync DTA Site Manager Costs tab
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Sync,"present");
	 	WebElement costType =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Cost_Type_Selection,"present");
		Select dropdown= new Select(costType);
		dropdown.selectByVisibleText("Base cost");
	// Identify the BAse Cost for all Products
		List<WebElement> DTA_Product_BOX =BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Product_BOX,"present");
		int x = DTA_Product_BOX.size();
		reporterHelper.log("No of DTA Products" + x);
	   // List<String> each_product =new ArrayList<>();
	    for(int i=0; i<DTA_Product_BOX.size(); i++)
	    {
			DTA_Product_BOX.get(i).clear();
	    	DTA_Product_BOX.get(i).sendKeys(arg1);
		 }
	    //Known issuer UI Validation takes few seconds  , hence giviing 5 secs before saving 
	
	    BrowserHelper.customSleep(5000);
	    WebElement base_Cost_Save =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Save,"present");
	    base_Cost_Save.click();
	    BrowserHelper.customSleep(5000);
	
	}
	
	@When("^Configure Additional Cost as \"([^\"]*)\"$")
	public void configureAdditionalCostAs(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//Sync DTA Site Manager Costs tab
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Sync,"present");
	    WebElement costType =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Cost_Type_Selection,"present");
		 Select dropdown= new Select(costType);
		
		 dropdown.selectByVisibleText("Additional cost");
		 
		 // Identify the BAse Cost for all Products
		    List<WebElement> DTA_Product_BOX =BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Product_BOX,"present");
		    
		    int x = DTA_Product_BOX.size();
		    
		    reporterHelper.log("No of DTA Products" + x);
	
		    //List<String> each_product = new ArrayList<>();
	
		    for(int i=0; i<DTA_Product_BOX.size(); i++){
		    	DTA_Product_BOX.get(i).clear();
	
		    	DTA_Product_BOX.get(i).sendKeys(arg1);
		    }
		    
		    BrowserHelper.customSleep(5000);
		    
		    WebElement additional_Cost_Save =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_COSTS_Save,"present");
		    
		    additional_Cost_Save.click();
		    	    
		    BrowserHelper.customSleep(2000);
		    /*
	//Close DTA Site Manager Widget & Search 
		    reporterHelper.log("Closing DTA Site Manager & Search ");
		    WebElement close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Widget_CloseWidgetIcon,"visible");
		    close.click();
		    BrowserHelper.customSleep(500);
		    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"visible").click();
		    BrowserHelper.customSleep(500);
	*/	    BrowserHelper.customSleep(500);
	
	}
	
	@Given("^Go to 'Dealer Pricing - Add New Sites' Tab$")
	public void goToDealerPricingAddNewSitesTab() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Sync,"present");
		WebElement DTA_Add_new_Sites = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_tab,"present");
		DTA_Add_new_Sites.click();
	    reporterHelper.log("DEALER PRICING - Add New Sites  Tab Opened");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Sync,"present");
		BrowserHelper.customSleep(500);
	}
	
	@Given("^Search & Add Select own sites <Own Site Code> to DTA$")
	public void searchAddSelectOwnSitesOwnSiteCodeToDTA(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		 BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Sync,"present");
		    reporterHelper.log("Selecting Own Sites");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_SearchType,"clickable").click();
		WebElement search_OwnSite = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Own_Sites,"present");
	    search_OwnSite.click();
	    List<List<String>> sitecode = arg1.raw();
		int n = sitecode.size();
		for(int i=0; i<n ; i++)
		{
			sitecode.get(0);
			  WebElement search_Box = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Search_Text_Box,"visible");
			    search_Box.clear();
			    List<String> a = sitecode.get(i);
			    CharSequence[] cs = a.toArray(new CharSequence[a.size()]);
			      System.out.println("Gayathri" + cs);
			      System.out.println("Gayathri" + cs);
	
			   	search_Box.sendKeys(cs);
			   	WebElement search = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Search_Button,"clickable");
			    search.click();
			    WebElement selectall = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Search_SelectAll,"present");
			    selectall.click();
			    WebElement addSelected = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Search_Add_Selected,"present");
			    addSelected.click();
			    reporterHelper.log(cs + "Own Site added to DTA");
			    BrowserHelper.customSleep(2000);
	
		}
	    BrowserHelper.customSleep(1000);  
	}
	
	
	@Given("^Search & Add Select Competitor sites <Comp Site Code> to DTA$")
	public void searchAddSelectCompetitorSitesCompSiteCodeToDTA(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		 BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Sync,"present");
		    reporterHelper.log("Selecting Own Sites");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_SearchType,"clickable").click();
		WebElement search_CompSite = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Competitor_Sites,"present");
		search_CompSite.click();
	 List<List<String>> sitecode = arg1.raw();
		int n = sitecode.size();
		for(int i=0; i<n ; i++)
		{
			sitecode.get(0);
			  WebElement search_Box = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Search_Text_Box,"visible");
			    search_Box.clear();
			   	    //List<String> a = sitecode.get(i);
			 //  search_Box.sendKeys(sitecode.get(i););
			    List<String> a = sitecode.get(i);
			    CharSequence[] cs = a.toArray(new CharSequence[a.size()]);
		    
			 //  CharSequence code  =  a.chars
		
			   	search_Box.sendKeys(cs);
			   	WebElement search = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Search_Button,"clickable");
			    search.click();
			    WebElement selectall = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Search_SelectAll,"present");
			    selectall.click();
			    WebElement addSelected = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Add_new_Sites_Search_Add_Selected,"present");
			    addSelected.click();
			    reporterHelper.log(cs + "Comp Site added to DTA");
			    BrowserHelper.customSleep(2000);
	
		}
	 BrowserHelper.customSleep(1000);  
	 //Close DTA Site Manager Widget & Search Widget
	  //  reporterHelper.log("Closing DTA Site Manager ");
	   // WebElement close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Widget_CloseWidgetIcon,"visible");
	  //  close.click();
	 //   BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"visible").click();
	  //  
	}
	
	
	@When("^Go to 'Dealer Pricing - Pricing' Tab$")
	public void goToDealerPricingPricingTab() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		   // Write code here that turns the phrase above into concrete actions
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Sync,"present");
			WebElement DTA_Pricing = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_tab,"present");
			DTA_Pricing.click();
		    reporterHelper.log("DEALER PRICING - Add New Sites  Tab Opened");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_Sync,"present");
			BrowserHelper.customSleep(2000);
	}
	
	
	@When("^Set NSP Delta \"([^\"]*)\" & Calculation method \"([^\"]*)\"$")
	public void setNSPDeltaCalculationMethod(String arg1, String arg2) throws Throwable {
	
		WebElement DTAname = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DTA_NAME,"visible");
		DTAname.getText();
	    reporterHelper.log("DTA NAME" + DTAname.getText());
	    WebElement NSP_Calc_Method = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_NSP_Method,"visible");
	    Select dropdown= new Select(NSP_Calc_Method);
		switch (arg2)
			{
			case "Average":
			{
				dropdown.selectByVisibleText("Average of Included Sites");
	
		       break;     
			}
			case "Lowest":
			{
				dropdown.selectByVisibleText("Lowest of Included Sites");
		       break;     
			}
		case "Highest":	
			{
				dropdown.selectByVisibleText("Highest of Included Sites");
		       break;     
			}
		case "Median":		
		{
			dropdown.selectByVisibleText("Median of Included Sites");
	       break;     
		}
			}
	    WebElement NSP_Delta = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_NSPDelta,"visible");
	    NSP_Delta.clear();
	
	    NSP_Delta.sendKeys(arg1);
	    
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_Save,"visible").click();
	}
	
	
	@When("^Configure Editable DTW$")
	public void configureEditableDTW() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement DTAname = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DTA_NAME,"visible");
		String DTA =  DTAname.getText();
	    reporterHelper.log("DTA NAME" + DTAname.getText());
		WebElement Export_DTW = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_Export_DTW,"visible");
	    Select dropdown= new Select(Export_DTW);
	    if (DTA.contains("Gross")) 
	    {
	    	dropdown.selectByVisibleText("Gross DTW");
	    }
	    else if (DTA.contains("Net"))
	    {
	    	dropdown.selectByVisibleText("Net DTW");
	    }
	    else
	    {
	    	dropdown.selectByVisibleText("Gross DTW");
	
	    }
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_Save,"visible").click();
	    BrowserHelper.customSleep(1000); 
	
	}
	
	@When("^Configure DTW Pricing Method$")
	public void configureDTWPricingMethod() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement DTAname = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DTA_NAME,"visible");
		String DTA =  DTAname.getText();
		
		WebElement DTW_Pricing_Method = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_DTW_Pricing_Method,"visible");
	    Select dropdown= new Select(DTW_Pricing_Method);
	    if (DTA.contains("Street")) 
	    {
	    	dropdown.selectByVisibleText("Street-back (subtraction)");
	    }
	    else if (DTA.contains("Cost"))
	    {
	    	dropdown.selectByVisibleText("Cost-based (addition)");
	    }
	    else if (DTA.contains("Margin"))
	    {
	    	dropdown.selectByVisibleText("Margin Share");
	    }
	    else
	    {
	    	dropdown.selectByVisibleText("Street-back (subtraction)");
	
	    }
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_Save,"visible").click();
	    BrowserHelper.customSleep(1000); 
	
	}
	
	
	
	@When("^Add DTA Margin value \"([^\"]*)\"$")
	public void addDTAMarginValue(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DTA_NAME,"visible");
		List<WebElement> DTA_Margin = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_DTA_Margin,"visible");
	
	      int x = DTA_Margin.size();
	    
	    reporterHelper.log("No of DTA Products" + x);
	
	    List<String> each_product =new ArrayList<String>();
	
	    for(int i=0; i<DTA_Margin.size(); i++){
	    	DTA_Margin.get(i).clear();
	
	    	DTA_Margin.get(i).sendKeys(arg1);
	    }
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_DP_Pricing_Save,"visible").click();
	
	    BrowserHelper.customSleep(3000); 
	    
	    
	    //Close DTA Site Manager Widget
	 //    reporterHelper.log("Closing DTA Site Manager ");
	//
	 //   WebElement close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Site_Manager_Widget_CloseWidgetIcon,"visible");
	//    close.click();
	
	    // BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"visible").click();
	}
	
	
	@Then("^Trigger Price Generation for selected DTA$")
	public void triggerPriceGenerationForSelectedDTA() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   
	       
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Pricing_Widget_CloseWidgetIcon,"visible");
	    reporterHelper.log("Syncing DTA Pricing widget ");
	
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Pricing_Widget_Sync,"visible");
	    BrowserHelper.customSleep(2000); 
	
	    WebElement DTA_Trigger_Price_1 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Pricing_Widget_Generate_Price_Button_1,"visible");
	    DTA_Trigger_Price_1.click();
	
	    WebElement DTA_Trigger_Price_2 = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Pricing_Widget_Generate_Price_Button_2,"visible");
	    DTA_Trigger_Price_2.click();
	    
	    
	 BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Pricing_Widget_Successful_Price_Generation_Notification_Message,"visible");
	 reporterHelper.log("DTA Price Generation triggered Successfully");
	
	    reporterHelper.takeScreenshot(driver,"DTA Price Generation triggered");
	    
	    BrowserHelper.customSleep(8000); 
	
	    //Closing pricing widget
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTA_Pricing_Widget_CloseWidgetIcon,"visible").click();
	    BrowserHelper.customSleep(3000); 
	
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"visible").click();
	    
	    BrowserHelper.customSleep(3000); 
	
	}
	
	
	
	@Given("^Configure Product level Price generation rule \"([^\"]*)\"$")
	public void configureProductLevelPriceGenerationRule(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//Sync on DTA Pricing Strategy
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategyWidget_DTAName_Sync,"visible");
		//Sync on  Pricing Strategy
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy ,"visible");
	    WebElement strategy_Expand = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Expand ,"present");
	    strategy_Expand.click();
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage  ,"present");
	    //BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel   ,"present");
	    //Product Level Stage
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_table   ,"present");
	
	  
	   List<WebElement> DTA_Own_Product = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_table_Product_Repeat ,"visible");
	
	  int x = DTA_Own_Product.size();
	  
	  reporterHelper.log("No of DTA Products" + x);
	
	  List<String> each_product =new ArrayList<String>();
	
	  for(int i=0; i<DTA_Own_Product.size(); i++){
	
		  	  //Product Rule Set Form 
		  WebElement DTA_Own_Product_box = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_table_Product_Repeat ,"visible");
		  DTA_Own_Product_box.click();
		  	WebElement Select_DTW_RULE_SET =    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_Ruleset ,"clickable");
		   	Select dropdown= new Select(Select_DTW_RULE_SET);
		 	dropdown.selectByVisibleText(arg1);
		 	//Product Rule selected & Save It
		   	WebElement DTW_RULE_Save =    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_Save  ,"clickable");
		   	DTW_RULE_Save.click();
		   	reporterHelper.log("DTA Product level rule DTW rule Saved");
	 
	  }
	  BrowserHelper.customSleep(1000); 
	  
	  	WebElement Add_Stage =    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Add_Stage_button  ,"clickable");
	 	Add_Stage.click();
	}
	
	@Given("^Configure Product level validation rule \"([^\"]*)\"$")
	public void configureProductLevelValidationRule(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategyWidget_DTAName_Sync,"visible");
	    
	    List<WebElement> DTA_Own_Product = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_table_Product_Repeat ,"visible");
	
	    int x = DTA_Own_Product.size();
	    
	    reporterHelper.log("No of DTA Products" + x);
	
	    List<String> each_product =new ArrayList<String>();
	
	    for(int i=0; i<DTA_Own_Product.size(); i++){
	
	  	  	  //Product Rule Set Form 
	  	  WebElement DTA_Own_Product_box = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_table_Product_Repeat ,"visible");
	  	  DTA_Own_Product_box.click();
	  	  	WebElement Select_DTW_RULE_SET =    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_Ruleset ,"clickable");
	  	   	Select dropdown= new Select(Select_DTW_RULE_SET);
	  	 	dropdown.selectByVisibleText(arg1);
	  	 	//Product Rule selected & Save It
	  
	    }
	   	WebElement DTW_RULE_Save =    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_DTAStrategy_Pricing_Strategy_Stage_ProductLevel_Save  ,"clickable");
		   	DTW_RULE_Save.click();
	  	   	reporterHelper.log("DTA Product level rule DTW rule Saved");
	
	}
	
	@Given("^Configure Product Group level \"([^\"]*)\"$")
	public void configureProductGroupLevel(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	
	
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	
	// 2.9 DTW EXPECTATION DELETE DTA KP-274 
	
	
	@When("^Open Deletion & Search for \"([^\"]*)\" in Administrator widget$")
	public void openDeletionSearchForInAdministratorWidget(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Sync,"visible");
		
		WebElement administrator_Deletion = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Deletion,"visible");
		administrator_Deletion.click();
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Deletion_Sync,"visible");
		
		WebElement deletion_Searchtype = 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Deletion_Search_type,"present");
		deletion_Searchtype.click();
		
		WebElement deletion_selectDTA = 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Deletion_SelectDTA,"present");
		deletion_selectDTA.click();
		
		WebElement DTAtab =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminsitratWidget_Deletion_DTATab,"present");
	    reporterHelper.log("Dealer Trade Area selected for Search Type");
	    
	    WebElement DTAname =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Deletion_Input,"present");
	    DTAname.clear();
	    DTAname.sendKeys(arg1);
	    WebElement DTASearch =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Deletion_SearchButton,"present");
	    DTASearch.click();
	    reporterHelper.log("DTA listed in search result");
		BrowserHelper.customSleep(3000);
	
	}
	
	
	
	
	@Then("^Delete DTA \"([^\"]*)\"$")
	public void deleteDTA(String arg1) throws Throwable {
		WebElement selectDTA =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Deletion_SelectedDTA,"visible");
		selectDTA.click();
	    reporterHelper.log("DTA Selected in Deletion Search");
	    reporterHelper.takeScreenshot(driver,"DTA Search");
	    
		WebElement deleteDTA =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Deletion_DeleteSite_Button,"visible");
		deleteDTA.click();
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Deletion_Confirmation_banner,"visible");
	    reporterHelper.takeScreenshot(driver,"DTA Delete Confirmation");
		WebElement deleteYes =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Deletion_Confirmation_Yes,"present");
		deleteYes.click();
		    //No for Delete DTA
		//WebElement deleteNo =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_Deletion_Confirmation_No,"present");
		// deleteNo.click();
		
		WebElement Searchresult =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Search_ResultsCountText_SingleResult,"visible");
		String result = Searchresult.getText();
		boolean isResult = result.contains("No matching");
		if (isResult) 
			{
			reporterHelper.log("Dealer Trade Area deleted");
		    reporterHelper.takeScreenshot(driver,"DTA Deleted");
	
			}
		else
		{
			reporterHelper.log("Something worng DTA not deleted");
			
		}
		
		BrowserHelper.customSleep(3000);
	}
	
	
	@Then("^Verify \"([^\"]*)\" deleted in Search widget$")
	public void verifyDeletedInSearchWidget(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		kalibratemain.pullWidgetTo("Search", "Container2");
		BrowserHelper.customSleep(2000);
		WebElement searchType =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchTypeDropDownButton,"clickable");
		searchType.click();
		WebElement selectDTAType =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SelectDTA,"clickable");
		selectDTAType.click();
		WebElement DTAtab =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_DTATab,"present");
	    reporterHelper.log("Dealer Trade Area selected for Search Type");
	    
	    WebElement DTAname =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput,"present");
	    DTAname.clear();
	    DTAname.sendKeys(arg1);
	    WebElement DTASearch =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton,"present");
	    DTASearch.click();
		BrowserHelper.customSleep(3000);
		WebElement Searchresult =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Search_ResultsNoMatching,"present");
		reporterHelper.log("Dealer Trade Area deleted");
	    reporterHelper.takeScreenshot(driver,"DTA Deleted");
	
		String result = Searchresult.getText();
		boolean isResult = result.contains("No matching");
		if (isResult) 
			{
			reporterHelper.log("Dealer Trade Area deleted");
		    reporterHelper.takeScreenshot(driver,"DTA Deleted");
	
			}
		else
		{
			reporterHelper.log("Something incorrect :  DTA not deleted");
			
		}
		
		
	}




}
