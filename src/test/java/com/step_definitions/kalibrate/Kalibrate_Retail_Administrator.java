package com.step_definitions.kalibrate;

import com.common.helpers.ReporterHelper;
import com.common.helpers.BrowserHelper;
import com.common.helpers.DatabaseHelper;
import com.common.helpers.KalibrateHelper;
import com.common.page_objects.Kalibrate_241_LocatorLibrary;
import com.common.page_objects.Kalibrate_28_LocatorLibrary;
import com.common.page_objects.Kalibrate_Generic_LocatorLibrary;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Kalibrate_Retail_Administrator {

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
				

	// Create a New User Profile 
	
	
		@Then("^Create new profile \"([^\"]*)\"$")
		public void createNewProfile(String arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
	   	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_Sync, "present");
	   	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles, "visible");

	   	    WebElement textBox = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_AddNew_TextBox, "present");
	   	    textBox.sendKeys(arg1);
	   	    WebElement addNewButton = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_AddNew_button, "present");
	   	    addNewButton.click();
	   	    
	   	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_Profile_form, "present");
	   	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_Profile_form_Save, "present");

	   	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_Profile_form_Application, "present");
  
	   	    List <WebElement> application=  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_Profile_form_Application_box, "present");
	   	    application.size();
            reporterHelper.log( "Application to be selected " +	   	    application.size());
            List <WebElement> application1 = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_Profile_form_Application_box1, "present");

	                
	    		    for(int i=0; i<application.size(); i++){
	    			    reporterHelper.log("Application.get(i)" + application.get(i));
	    			    String isChecked; 
	    			    WebElement box = application.get(i);
	    			    //WebElement box1 = application1.get(i);
	    			    isChecked = box.getAttribute("checked");
	    			    
	    		    			    
	    			    String appclass = box.getAttribute("class");
	    			    reporterHelper.log(appclass);

	    /*
	    Check box status is verified with class name , Selected options do not have ng-not-empty class name 
	    After identifying check box status , based on class  name , click on parent level i.e. div instead of //div//input 
	    http://grokbase.com/t/gg/webdriver/1398r3xqrk/finding-checkbox-by-isselected-not-working-in-html-5-app-looking-for-suggestions
	    Running this approach will in fact toggle the check box; .isSelected() in java/Selenium2 apparently always returns false [at least with the java/selenium/Firefox versions I tested it with].
	    The selection of the proper check box isn't where the problem lies -- rather, it is in distinguishing correctly the initial state to needlessly avoid re-clicking an already checked box.
	    Resolution --https://stackoverflow.com/questions/25396301/isselected-method-for-checkbox-always-returns-false

	    */			    
	    			    if (!appclass.contains("ng-valid ng-not-empty"))
	    			    {
	    			    	BrowserHelper.customSleep(500);
	    			    	box.click();
	    					    reporterHelper.log("Application added now");
	    			    }
	    			    else
	    			    {
	    				reporterHelper.log("Application already added");
	    	    		BrowserHelper.customSleep(1000);
	    			    }
	    		    }
	    		    
	    		    
	    		    
	                
            //kalibrate_adminstratorWidget_UserProfiles_Profile_form_Application_box
            

		
            reporterHelper.log( "Application Selected");

	   	    
	   	    WebElement profileSave =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_UserProfiles_Profile_form_Save, "present");
	   	    profileSave.click();
            reporterHelper.log( arg1 + "New User Profile Created Successfully");

            
            WebElement banner = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_notification_banner_success_sync_before_close ,"visible");

            
            boolean status = banner.isDisplayed();
    		if (status)
    				{
    		
    				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_notification_banner_success_sync_before_close,"visible");
    				reporterHelper.takeScreenshot(driver, "Sales Type Created");
    	       		WebElement bannar_close = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_notification_banner_success_close,"clickable");
    	       		bannar_close.click();
    	            reporterHelper.log("Success Bannar Closed");
    	    		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_notification_banner_success_sync_after_close,"present");
    	
    	    		reporterHelper.log(bannar_close.getText());
    	    		System.out.println(status);

    	    		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_notification_banner_success_sync_after_close)) 
    	    				{
    	    		
    	            		reporterHelper.log("Bannar Closed already");
    	    	       		WebElement sync = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_administratorWidget_title,"clickable");
    	    				sync.click();
    	
    						}
    					}
        	
        	}


		
		
		
		
		
		// Create a new User for profile 
		

		

@Then("^Create new user \"([^\"]*)\" and  \"([^\"]*)\" for profile \"([^\"]*)\"$")
public void createNewUserAndForProfile(String arg1, String arg2, String arg3) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	 
	BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users, "present");
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Sync, "visible");

	    WebElement textBox = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_AddNew_TextBox, "present");
	    textBox.sendKeys(arg1);
	    WebElement addNewButton = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_AddNew_button, "present");
	    addNewButton.click();
	    
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form, "present");
	    BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_Save, "present");

	    WebElement fullName = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_FullName, "present");	
	    fullName.clear();
	    fullName.sendKeys(arg1);
	    
	    WebElement userName = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_LoginName, "present");	
	    userName.clear();
	 	userName.sendKeys(arg1);
	    
	    
		WebElement profileList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_Profile_Select,"present");
	Select profiledropdown= new Select(profileList);
	profiledropdown.selectByVisibleText(arg3);
	    
	WebElement languageList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_Language_Select,"present");
	Select laguagedropdown= new Select(languageList);
	laguagedropdown.selectByVisibleText("English (United States)");
	
	WebElement distanceList =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_DistanceUnit_Select,"present");
	Select distancedropdown= new Select(distanceList);
	distancedropdown.selectByVisibleText("Imperial");
	
	
	    WebElement password = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_Password, "present");	
	    password.clear();
	    password.sendKeys(arg2);
	    
	    WebElement confirmpassword = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_ConfirmPassword, "present");	
	    confirmpassword.clear();
	    confirmpassword.sendKeys(arg2);
		BrowserHelper.customSleep(1000);

	    WebElement userSave =  BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_adminstratorWidget_Users_Users_form_Save, "present");
	    userSave.click();
	    
	    reporterHelper.log( arg1 + "New User  Created Successfully");
	    
	BrowserHelper.customSleep(2000);
}
	

}
