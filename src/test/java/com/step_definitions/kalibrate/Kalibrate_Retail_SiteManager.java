package com.step_definitions.kalibrate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Random;
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


public class Kalibrate_Retail_SiteManager 
{
	
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
		


	@When("^Search & Add Own Site Competitor sites <Own Site Comp Code> to OwnSite Market$")
	public void searchAddOwnSiteCompetitorSitesOwnSiteCompCodeToOwnSiteMarket(DataTable arg1) throws Throwable 
	{
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
	
		WebElement search_CompSite = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_Own_Sites,"present");
		search_CompSite.click();
		
		  WebElement search_Box = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_LocalMarket_Search_Text_Box,"visible");
		    search_Box.clear();
		   	    //List<String> a = sitecode.get(i);
		 //  search_Box.sendKeys(sitecode.get(i););
		    List<String> a = sitecode.get(i);
		    CharSequence[] cs = a.toArray(new CharSequence[a.size()]);
	    
		 //  CharSequence code  =  a.chars
	
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
		BrowserHelper.customSleep(2000);  
	    
	} // Local Market own site


	//Lead And Follow Setup
	@Then("^Assign \"([^\"]*)\" as \"([^\"]*)\" LEAD Sites$")
	public void assignAsLEADSites(String leadsiteimportcode, String leadtype) throws Throwable 
	
	{
    // Write code here that turns the phrase above into concrete actions
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_tab_Sync,"present");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_form_Sync,"present");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Relationship_form,"present");
		if(leadtype.equalsIgnoreCase("ProductGroup"))
		{
			WebElement productGroupbutton = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Relationship_ProductGroup,"present");
			productGroupbutton.click();
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Form,"present");
			WebElement ownproductgroup = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_OwnProductGroup_dropdown,"present");
			Select ownproductgroupselected = new Select(ownproductgroup);
			List<WebElement> ownpgoptions = ownproductgroupselected.getOptions();
			Random rand = new Random();
			for(WebElement select : ownpgoptions){
				List<WebElement> options = ownproductgroupselected.getOptions();
				System.out.println(options.size());
				int list = rand.nextInt(options.size());
				options.get(list).click();
			}
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Search,"present");
			WebElement search = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Search_Drop,"present");
			search.click();
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Search_popup,"present");
			WebElement searchsite = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Search_Text,"present");
			searchsite.clear();
			searchsite.sendKeys(leadsiteimportcode);
			WebElement searchButton = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Search_button,"present");
			searchButton.click();
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Search_result,"present");

			WebElement search_result = 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Search_Select_Site,"present");
			search_result.click();
		    BrowserHelper.customSleep(1000);
			
			WebElement relationproductgroup = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Relation_ProductGroup,"present");
			Select relationproductgroupselected = new Select(relationproductgroup);
			Random rand1 = new Random();

			List<WebElement> relatepgoptions = relationproductgroupselected.getOptions();
			for(WebElement selectrelateoption : relatepgoptions){
				List<WebElement> relatedoptions = relationproductgroupselected.getOptions();
				System.out.println(relatedoptions.size());
				int list1 = rand1.nextInt(relatedoptions.size());
				relatedoptions.get(list1).click();
			}
			BrowserHelper.customSleep(1000);

			WebElement offset = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Offset,"present");
			offset.clear();
			offset.sendKeys("0.5");

			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Save_Sync,"present");
			WebElement save = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_group_Save,"clickable");
			save.click();
			BrowserHelper.customSleep(500);

		}
		else if(leadtype.equalsIgnoreCase("Product"))
		{
				WebElement productbutton= BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Relationship_Product,"present");
				productbutton.click();
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Form,"present");
				WebElement ownproduct = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_OwnProduct_dropdown,"present");
				Select ownproductselected = new Select(ownproduct);
				Random rand = new Random();

				List<WebElement> ownproductoptions = ownproductselected.getOptions();
				for(WebElement selectoption : ownproductoptions){
					List<WebElement> ownoptions = ownproductselected.getOptions();
					System.out.println(ownoptions.size());
					int list = rand.nextInt(ownoptions.size());
					ownoptions.get(list).click();
				}


				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Search,"present");
				WebElement search = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Search_Drop,"present");
				search.click();
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Search_popup,"present");
				WebElement searchsite = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Search_Text,"present");
				searchsite.clear();
				searchsite.sendKeys(leadsiteimportcode);
				WebElement searchButton = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Search_button,"present");
				searchButton.click();
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Search_result,"present");
				WebElement search_result = 	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Search_Select_Site,"present");
				search_result.click();
				BrowserHelper.customSleep(2000);

				WebElement relationproduct = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Relation_Product,"present");
				Select relationproductselected = new Select(relationproduct);
				List<WebElement> relatedproductoptions = relationproductselected.getOptions();
				for(WebElement selectoption : relatedproductoptions){
					List<WebElement> relateoptions = relationproductselected.getOptions();
					System.out.println(relateoptions.size());
					int list = rand.nextInt(relateoptions.size());
					relateoptions.get(list).click();
				}

				BrowserHelper.customSleep(1000);


				WebElement offset = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Offset,"present");
				offset.clear();
				offset.sendKeys("0.5");

				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Save_Sync,"present");
				WebElement save = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_siteManagerWidget_Lead_Follow_Product_Save,"present");
				save.click();
				BrowserHelper.customSleep(500);

			}
			else
			{
				reporterHelper.log("Incorrect Lead Follow relationship Type");
			}
	//if 
}//lead follw



} //Retail Site Manager

