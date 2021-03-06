package com.step_definitions.kalibrate;

import java.sql.ResultSet;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


import com.common.helpers.BrowserHelper;
import com.common.helpers.DatabaseHelper;
import com.common.helpers.KalibrateHelper;
import com.common.helpers.ReporterHelper;
import com.common.helpers.Utils;
import com.common.hooks.Hooks;
import com.common.page_objects.Kalibrate_Generic_LocatorLibrary;
import com.step_definitions.kalibrate.Kalibrate_WidgetStepDefinitions.Widget;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.joda.time.*;
import org.joda.time.format.*;

public class Kalibrate_StepDefinitions {
	
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
	
	// Instance variables
	private String currentWorkspaceName;
	private String nameFilter;
	private String filterCategory;
	private String filteredListItem;
	private String searchWidgetSelectedSiteName;
	private String searchWidgetSelectedImportCode;
	private String intelWidgetNoFilterResultSetCount;
	private String mapWidgetNoFilterSearchResultsMessage;
	private String searchWidgetNoFilterMatchesCount;
	private String searchWidgetFilteredMatchesCount;	

	private String pricingWidgetTitle;

	private WebElement tempWebElement;

	private String workspaceName;

	private Object sitesProcessingPreviousCount;

	private Object sitesProcessingCount;

	private int waitCausedNoChangeCounter;

	private String tempWebElementText;

	private String personaIdentifier;

//	public Kalibrate_241_LocatorLibrary locatorLibrary = new Kalibrate_241_LocatorLibrary();
	
	@Before
	public void initialise() {
		System.out.println("In Kalibrate_StepDefinitions.initialise() method");
	}

	
	@Given("^a '(.*)' is logged in and Kalibrate main page is displayed$")
	public void theUserIsLoggedInAndKalibrateMainPageIsDisplayed(String persona) {
		if(Utils.getProperty("AUTHENTICATION_METHOD").equalsIgnoreCase("FORMS")) {
			userIsOnLoginPage();
			aValidUsernameAndAValidPasswordAreEntered(setPersonaIdentifier(persona));
			theLoginButtonIsClicked("Login Empty Workspace");
		} else {
			callURLWithWindowsAuthCreds(persona);
		}
		loginIsCompleteAndkalibrateMainPageIsDisplayed("Login Empty Workspace");
	}

	private void callURLWithWindowsAuthCreds(String persona) {
		
		Hooks.currentPersona = persona;
		
		driver.get(Hooks.getApplicationURL(applicationName + "_URL"));
		
	}


	@Given("^a '(.*)' logs back in and Kalibrate main page is displayed$")
	public void theUserLogsBackInAndKalibrateMainPageIsDisplayed(String persona) {
		if(Utils.getProperty("AUTHENTICATION_METHOD").equalsIgnoreCase("FORMS")) {
			userIsOnLoginPage();
			aValidUsernameAndAValidPasswordAreEntered(setPersonaIdentifier(persona));
			theLoginButtonIsClicked("Login Standard Workspace");
		}else {
			callURLWithWindowsAuthCreds(persona);
		}
		loginIsCompleteAndkalibrateMainPageIsDisplayed("Login Standard Workspace");
	}
	

	@Given("^the Kalibrate Login page is displayed$")
	public void userIsOnLoginPage() {
		
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "clickable");
        
        reporterHelper.stopTimer("Launch Application: " + applicationName);
        
        reporterHelper.log("Login Page displayed successfully");
        reporterHelper.takeScreenshot(driver, "Login-Initial_State");
        
        reporterHelper.recordSystemUsage();
        
		BrowserHelper.checkForConsoleErrors();

	}
	
	
	@When("^a '(.*)' valid username and valid password are entered$")
	public void aPersonaNameValidUsernameAndValidPasswordAreEntered(String persona) {
		
		setPersonaIdentifier(persona);
		
		// Enter Valid Username
//		String tempUsername = System.getenv("COMPUTERNAME") + "_" + personaIdentifier + "_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER");
		String tempUsername = Hooks.getCredentials(persona, "user");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "clickable").sendKeys(tempUsername);
        reporterHelper.log("Username entered Successfully: " + tempUsername);

        // Enter Valid Password
        String tempPassword = Utils.getProperty("DEFAULT_PASSWORD");
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_PasswordTextBox, "clickable").sendKeys(tempPassword);
        reporterHelper.log("Password entered Successfully: " + tempPassword);
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}

	@When("^a valid username and a valid password are entered$")
	public void aValidUsernameAndAValidPasswordAreEntered(String persona) {
		
//		setPersonaIdentifier(persona);
		
		// Enter Valid Username
//		String tempUsername = System.getenv("COMPUTERNAME") + "_" + personaIdentifier + "_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER");
		String tempUsername = Hooks.getCredentials(persona, "user");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "clickable").sendKeys(tempUsername);
        reporterHelper.log("Username entered Successfully: " + tempUsername);

        // Enter Valid Password
        String tempPassword = Utils.getProperty("DEFAULT_PASSWORD");
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_PasswordTextBox, "clickable").sendKeys(tempPassword);
        reporterHelper.log("Password entered Successfully: " + tempPassword);
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}

	
	private String setPersonaIdentifier(String persona) {
		
		switch(persona) {
    	case "Retail Pricing Analyst":
    		personaIdentifier = "RETAIL_PRICING_ANALYST";
			break;
    	case "​Dealer Pricing Analyst":
    		personaIdentifier = "DEALER_PRICING_ANALYST";
			break;
    	case "​Pricing Manager":
    		personaIdentifier = "PRICING_MANAGER";
			break;
    	case "​Administrative User":
    		personaIdentifier = "ADMINISTRATIVE_USER";
			break;	
    	case "​Site Manager":
    		personaIdentifier = "SITE_MANAGER";
			break;	
		case "​Site Employee":
			personaIdentifier = "SITE_EMPLOYEE";
			break;
	    default:
	        reporterHelper.customFailScript("Unknown persona name in method setPersonaIdentifier: " + persona);
		}
		return personaIdentifier;
	}


	@When("^no username and no password are entered$")
	public void noUsernameAndNoPasswordAreEntered() {

		// Enter Valid Username
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "clickable").clear();
        reporterHelper.log("Username cleared successfully");

        // Clear Password
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_PasswordTextBox, "clickable").clear();
        reporterHelper.log("Password cleared successfully");
	    
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}
	

	@When("^a '(.*)' valid username and no password are entered$")
	public void a_valid_usernameAndNoPasswordAreEntered(String persona) {
		
		setPersonaIdentifier(persona);
		
        // Enter Valid Username
//		String tempUsername = System.getenv("COMPUTERNAME") + "_" + personaIdentifier + "_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER");
		String tempUsername = Hooks.getCredentials(persona, "user");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "clickable").sendKeys(tempUsername);
        reporterHelper.log("Valid Username entered Successfully: " + tempUsername);

        // Clear Password
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_PasswordTextBox, "clickable").clear();
        reporterHelper.log("Password cleared successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}

	@When("^a '(.*)' valid username and an invalid password are entered$")
	public void a_valid_usernameAndAnInvalidPasswordAreEntered(String persona) {
		
		setPersonaIdentifier(persona);
		
        // Enter Valid Username
//		String tempUsername = System.getenv("COMPUTERNAME") + "_" + personaIdentifier + "_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER");
		String tempUsername = Hooks.getCredentials(persona, "user");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "clickable").sendKeys(tempUsername);
        reporterHelper.log("Valid Username entered Successfully: " + tempUsername);

        // Enter an invalid Password
        String tempPassword = "@B4dP4ssw0rd";
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_PasswordTextBox, "clickable").sendKeys(tempPassword);
        reporterHelper.log("Invalid Password entered successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated: " + tempPassword);
	}	
	
	@When("^a '(.*)' no username and valid password are entered$")
	public void noUsernameAndAValidPasswordAreEntered(String persona) {
		
		setPersonaIdentifier(persona);
		
        // Clear Username
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "clickable").clear();
        reporterHelper.log("Username cleared Successfully");

        // Enter Valid Password
        String tempPassword = Utils.getProperty("DEFAULT_PASSWORD");
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_PasswordTextBox, "clickable").sendKeys(tempPassword);
        reporterHelper.log("Valid Password entered successfully: " + tempPassword);
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}	
	
	@When("^the Login button is clicked$")
	public void theLoginButtonIsClicked() {

		theLoginButtonIsClicked("Login");
		
	}

	public void theLoginButtonIsClicked(String timerName) {

		if(timerName == null)
			timerName = "Login";
		
		// Submit the form 
		
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_LoginButton, "clickable");
        reporterHelper.startTimer(timerName);
        
        tempWebElement.click();
        
        reporterHelper.log("\nButton clicked Successfully, syncing with Kalibrate main page containers...");
	}
	
	@Then("^the following error message should be displayed '(.*)'$")
	public void theFollowingErrorMessageShouldBeDisplayed(String expectedErrorMessage) {
		
		BrowserHelper.customSleep(5 * 1000);
		
		reporterHelper.log(String.format("\n\nChecking for error message \"%s\"...", expectedErrorMessage));
		
		// the login error message web element is "present" before it displays the error message. validation
		// against javascript checks is near instantaneous and not an issue, however checking the password
		// against the database is not, and requires a small delay for headless operation
		BrowserHelper.customSleep(100);
		
		String actualErrorMessgae = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_LoginErrorMessage, "visible").getText();

		reporterHelper.log("\n\tExpected error message: " + expectedErrorMessage);
		reporterHelper.log("\tActual error message: " + actualErrorMessgae);
		
		reporterHelper.takeScreenshot(driver, "Login-Error Message");
		
		if(!expectedErrorMessage.equals(actualErrorMessgae))
			reporterHelper.customFailScript("Error message displayed is '" + actualErrorMessgae + "', expecting '" + expectedErrorMessage + "'");
		
	}

	@Then("^Login is complete and Kalibrate main page is displayed$")
	public void loginIsCompleteAndkalibrateMainPageIsDisplayed() {


		loginIsCompleteAndkalibrateMainPageIsDisplayed("Login");

	}

	public void loginIsCompleteAndkalibrateMainPageIsDisplayed(String timerName) {

		BrowserHelper.customSleep(500);
		
		if(timerName == null)
			timerName = "Login";
		reporterHelper.log("Checking main page is dispalyed using kalibrateLocatorLibrary.kalibrate_main_Sync");
		if(performSync)
			BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_main_Sync, "present");
		
		kalibrateHelper.syncAllPresentWidgets();
		
		reporterHelper.stopTimer(timerName);
        reporterHelper.log("Logged in Successfully");
        
//        kalibrateHelper.syncAllPresentWidgets();
		BrowserHelper.checkForConsoleErrors();
		
		reporterHelper.takeScreenshot(driver, "General-" + timerName + "_Initial_State");
	}
	@Then("^Kalibrate main page is displayed$")
	public void kalibrateMainPageIsDisplayed() {
		
		BrowserHelper.customSleep(5 * 1000);
		
		if(performSync)
			BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_main_Sync, "present");
		
        kalibrateHelper.syncAllPresentWidgets();
		BrowserHelper.checkForConsoleErrors();
		
        reporterHelper.takeScreenshot(driver, "General-Kalibrate_Main_Page_Displayed");
	}
	
	@Then("^KalibrateBP main page is displayed$")
	public void kalibrateBPMainPageIsDisplayed() {
		
		if(performSync) 
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_Sync, "present");

		reporterHelper.stopTimer("Login");
        reporterHelper.log("Logged in Successfully");

        kalibrateHelper.syncAllPresentWidgets();
        
        BrowserHelper.checkForConsoleErrors();

        reporterHelper.takeScreenshot(driver, "General-Initial_State");
        
	}

	@Then ("^User is returned to Login Page$") 
	public void userIsReturnedToLoginPage() {
		
		if (Boolean.parseBoolean(Utils.getProperty("LOGOUT_BLUE_SCREEN_BUG_WORKAROUND"))) {
			
			BrowserHelper.customSleep(5 * 1000);
			
			reporterHelper.takeScreenshot(driver, "General-Before_Doing_Refresh_After_Logout");
			
			driver.navigate().refresh();
		
			reporterHelper.log("Refreshed the browser due to LOGOUT_BLUE_SCREEN_BUG_WORKAROUND being set to true in config.properties.  Waiting for 5 seconds...");
			
			BrowserHelper.customSleep(5 * 1000);
			
			reporterHelper.takeScreenshot(driver, "General-After_Doing_Refresh_After_Logout");
		} 

		// Maybe put the following sync step into a else of the if above if the issue persists  
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "visible");
        
		reporterHelper.stopTimer("Click Logout to Login Credentials");

	    reporterHelper.log("Logged out Successfully");
		reporterHelper.log("User returned to Login Page successfully");
		
		BrowserHelper.customSleep(5 * 1000);
		BrowserHelper.checkForConsoleErrors();
	}		
	
	@Given("^the 'Search' Widget is displayed in workspace$")
	public void theSearchWidgetIsDisplayedInWorkspace() {
		userCreatesANewTripleColumnWorkspaceNamed("New_Workspace");
		theCreatedWorkspaceIsDisplayed();
		userAddsSearchWidgetToCreatedWorkspace();
		theWorkspaceIsPopulatedWithWidgets();
	}
	
	@When("^the '(.*)' workspace is selected from workspace menu$")	
	public void theWorkspaceIsSelectedFromWorkspaceMenu(String workSpaceName) {
		
		currentWorkspaceName = workSpaceName;
		
		WebElement workspaceMenuElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader, "clickable");

		// Following Steps are to ensure DEFAULT workspace is displayed prior to selecting target workspace 
		
		reporterHelper.log("\nChecking if DEFAULT workspace is currently selected");
		if(!workspaceMenuElement.getText().equalsIgnoreCase("DEFAULT")) {
		
			reporterHelper.log("\tDEFAULT workspace is not selected, therefore selecting");
			if(!BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuExpanded)) {
				reporterHelper.log("\tExpanding workspace menu");
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader, "present").click();
				BrowserHelper.customSleep(500);
			}
			
			reporterHelper.log("\tClicking DEFAULT");
	        BrowserHelper.syncOnElement(driver, By.xpath("//div[contains(@class, 'workspace-text')][contains(., 'DEFAULT')]"), "present").click();
	        BrowserHelper.customSleep(1 * 1000);
        
	        
	        workspaceMenuElement.click();
	        BrowserHelper.customSleep(1 * 1000);
	        
		}
        
        workspaceMenuElement.click();
        
        tempWebElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[contains(@class, 'workspace-text')][contains(., '" + workSpaceName + "')]"), "visible");

        reporterHelper.startTimer("Select '" + workSpaceName + "' workspace");
        
        tempWebElement.click();
       
        workspaceMenuElement.click();

        BrowserHelper.syncOnElementText(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader, workSpaceName);
        
	}
	
	@Then("^the '(.*)' workspace is displayed$")	
	public void theWorkspaceIsDisplayed(String expectedWorkSpaceName) {
		
		if(performSync )
			kalibrateHelper.syncAllPresentWidgets();
		
		String currentWorkspace = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader, "clickable").getText();
		reporterHelper.log("\nCurrent workspace: " + currentWorkspace);
		
		if(!expectedWorkSpaceName.equals(currentWorkspace))
			reporterHelper.customFailScript("Current Kalibrate Workspace is " + currentWorkspace + ", expecting " + expectedWorkSpaceName+ "1");

		reporterHelper.stopTimer("Select '" + expectedWorkSpaceName + "' workspace");
		reporterHelper.takeScreenshot(driver, "General-" + expectedWorkSpaceName + "_workspace_displayed");
		
//		if(Hooks.applicationVersionSimplified.equals("2.4.1"))
//			configurePricingWidget();
//		else
//			configurePricingWidgetDragMethod();
		
		BrowserHelper.checkForConsoleErrors();
	}


	@Then("widgets are dragged from first site in Search widget in the following configuration:$")
	public void widgetsAreDraggedFromFirstSiteInSearchWidgetInTheFollowingConfiguration(List<Widget> widgets) {
		
		BrowserHelper.customSleep(10 * 1000);
		
		//select first site in search results
		reporterHelper.log("\nSelecting first site in Search widget results");
		// Get element collection for the site rows in Search Widget
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchResults_RowItems, "present");
		
		// Click the first site to populate site details in linked Pricing widget
		elements.get(0).click();
		
		
		for (Widget widget : widgets) {

			dragWidgetIconFromSearchWidgetSelectedSite(widget.widgetName, widget.containerNumber);
		}
	}

	@Then("widgets are dragged from Search widget footer in the following configuration:$")
	public void widgetsAreDraggedFromSearchWidgetFooterInTheFollowingConfiguration(List<Widget> widgets) {
		for (Widget widget : widgets) {

			dragWidgetIconFromWidgetFooter("Search", widget.widgetName, widget.containerNumber);
		}
	}
	
	@Then("the pricing widget is configured selecting all items$")
	public void thePricingWidgetIsConfiguredSelectingAllItems() {
		if(Hooks.applicationVersionSimplified.equals("2.4.1"))
			configurePricingWidget("PRICING_WIDGET_CONFIGURATION_FULL_SET_2.4.1");
		else
			configurePricingWidgetDragMethod("PRICING_WIDGET_CONFIGURATION_FULL_SET_2.8");
	}

	@Then("the pricing widget is configured selecting standard pricing agents items$")
	public void thePricingWidgetIsConfiguredSelectingStandardPricingAgentsItems() {
		if(Hooks.applicationVersionSimplified.equals("2.4.1"))
			configurePricingWidget("PRICING_WIDGET_CONFIGURATION_STANDARD_SET_2.4.1");
		else
			configurePricingWidgetDragMethod("PRICING_WIDGET_CONFIGURATION_STANDARD_SET_2.8");
	}


	private void dragWidgetIconFromWidgetFooter(String widgetIdentifierString, String iconTitle, String targetContainer) {
		
		reporterHelper.log("In dragWidgetIconFromWidgetFooter method");
		
	    // get sourceWidgetNameAttribute
	    String sourceWidgetNameAttribute = "";
	    
    	switch(widgetIdentifierString) {
    	case "Intel":
    		sourceWidgetNameAttribute = "k-intel-widget";
			break;
    	case "Map":
    		sourceWidgetNameAttribute = "k-map-widget";
			break;
    	case "Report Viewer":
    		sourceWidgetNameAttribute = "report-viewer-widget";
			break;
    	case "Pump Price Update":
    		sourceWidgetNameAttribute = "site-mixer-widget";
			break;	
    	case "Site Metrics":
    		sourceWidgetNameAttribute = "site-metrics-widget";
			break;	
		case "Pricing":
    		sourceWidgetNameAttribute = "pricing-widget";
			break;
    	case "Events Manager":
    		sourceWidgetNameAttribute = "manage-events-widget";
			break;		
    	case "Site Manager":
    		sourceWidgetNameAttribute = "manage-sites-widget";
			break;		
    	case "Site Strategy":
    		sourceWidgetNameAttribute = "strategy";
			break;	
    	case "Site Planner":
    		sourceWidgetNameAttribute = "planner";
			break;		
    	case "Notes":
    		sourceWidgetNameAttribute = "notes-widget";
			break;						
		case "Search":
    		sourceWidgetNameAttribute = "adv-search-widget";
			break;
	    default:
	        reporterHelper.customFailScript("Unknown widget name in method dragWidgetIconFromWidgetFooter: " + widgetIdentifierString);
		}
    	
	    // get newWidgetNameAttribute
	    String newWidgetNameAttribute = "";
	    
    	switch(iconTitle) {
    	case "Multi Pricing":
    		newWidgetNameAttribute = "multi-pricing-widget";
			break;
    	case "Intel":
    		newWidgetNameAttribute = "k-intel-widget";
			break;
    	case "Map":
    		newWidgetNameAttribute = "k-map-widget";
			break;
    	case "Report Viewer":
    		newWidgetNameAttribute = "report-viewer-widget";
			break;
    	case "Pump Price Update":
    		newWidgetNameAttribute = "site-mixer-widget";
			break;	
    	case "Site Metrics":
    		newWidgetNameAttribute = "site-metrics-widget";
			break;	
		case "Pricing":
			newWidgetNameAttribute = "pricing-widget";
			break;
    	case "Events Manager":
    		newWidgetNameAttribute = "manage-events-widget";
			break;		
    	case "Site Manager":
    		newWidgetNameAttribute = "manage-sites-widget";
			break;		
    	case "Site Strategy":
    		newWidgetNameAttribute = "strategy";
			break;	
    	case "Site Planner":
    		newWidgetNameAttribute = "planner";
			break;		
    	case "Notes":
    		newWidgetNameAttribute = "notes-widget";
			break;		
    	case "Market Strategy":
    		newWidgetNameAttribute = "market-strategy-widget";
			break;		
		case "Market Pricing":
			newWidgetNameAttribute = "market-pricing-widget";
			break;
        default:
            reporterHelper.customFailScript("Unknown widget icon title in method dragWidgetIconFromWidgetFooter: " + iconTitle);
		}
    	
		// identify which container the source widget is in (and the dragged icon widget will be created in initially)
		tempWebElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[@name='" + sourceWidgetNameAttribute + "']"), "clickable").findElement(By.xpath("ancestor::div[contains(@class, 'container')]"));
		String sourceWidgetContainerID = tempWebElement.getAttribute("id");
		reporterHelper.log("\nSource widget container id: " + sourceWidgetContainerID);
    	
    	WebElement sourceWidgetElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[@name = '" + sourceWidgetNameAttribute + "']"), "present");
    	WebElement sourceWidgetElementFooter = sourceWidgetElement.findElement(By.xpath(".//div[contains(@class, 'widget-footer')]"));
    	WebElement sourceWidgetTargetIcon = sourceWidgetElementFooter.findElement(By.xpath(".//img[contains(@title, '" + iconTitle + "')]"));
    	
    	WebElement targetContainerElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[@id='container" + targetContainer + "']"), "present");
    	
		// Drag desired icon to the same widget as the source widget
		reporterHelper.log("\nDragging Widget footer icon to same container as source widget");
		
		Actions builder = new Actions(driver);

	    // Drag target icon to current widget
	    builder
	    	.dragAndDropBy(sourceWidgetTargetIcon, 1, 1)
	    	.perform();
	    
	    reporterHelper.log("Completed dragging " + widgetIdentifierString + " widget footer icon to same container as source, waiting for 5 seconds..."); BrowserHelper.customSleep(5 * 1000);
	    

	    // If necessary, move created widget to targetContainer
    
	    if(!(sourceWidgetContainerID.split("container")[1].equalsIgnoreCase(targetContainer))) {
	    	reporterHelper.log("Moving new " + iconTitle + " widget to container " + targetContainer + ", waiting for 5 seconds..."); //BrowserHelper.customSleep(5 * 1000);
	    	
	    	// set target widget name attribute identifier for the chosen dragged icon
	    	
	    	WebElement newWidgetElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[@id='" + sourceWidgetContainerID + "']//div[@name = '" + newWidgetNameAttribute + "']"), "present");
	    	WebElement newWidgetElementHead = newWidgetElement.findElement(By.xpath(".//div[contains(@class, 'widget-head')]"));
	    	targetContainerElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[@id='container" + targetContainer + "']"), "present");
	    	
	    	builder.dragAndDrop(newWidgetElementHead, targetContainerElement).perform();
	    	
	    	reporterHelper.log("Finished moving new " + iconTitle + " widget to container");
	    	
	    } else {
	    	reporterHelper.log("New " + iconTitle + " widget already in the correct container");
	    }
		    	
	
	    // Synchronise on the widget being present in the target container
	    BrowserHelper.syncOnElement(driver, By.xpath("//div[@id='container" + targetContainer + "']//div[@name = '" + newWidgetNameAttribute + "']"), "present");
		
	    reporterHelper.log("New " + iconTitle + " widget successfully created");

	    reporterHelper.takeScreenshot(driver, "Main_Page-Widget_" + iconTitle + "_Dragged_From_Widget_Footer_Complete");
	    
	    kalibrateHelper.syncAllPresentWidgets();
	    
	    BrowserHelper.checkForConsoleErrors();
		
	}
	
  
    /**
     * Used to drag widget icon from the selected site in the
     * Search widget.
     * 
     * @param newWidgetIdentifierString is the name of the widget icon 
     * 			that should be dragged. Example is <code>Pricing</code>
     *          
     * @param targetContainer is the container number. Example is <code>2</code> 
     * 
     */
	
	private void dragWidgetIconFromSearchWidgetSelectedSite(String newWidgetIdentifierString, String targetContainer) {

		reporterHelper.log("\n\nAttempting to drag following widget from selected site: " + newWidgetIdentifierString);
		
		// identify which container the search widget is in (and the dragged icon widget will be created in initially)
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget, "clickable").findElement(By.xpath("ancestor::div[contains(@class, 'container')]"));
			String searchWidgetContainerID = tempWebElement.getAttribute("id");
			reporterHelper.log("\nSearch widget container id: " + searchWidgetContainerID);
		
		// select first site in search results
			reporterHelper.log("\nSelecting first site in Search widget results");
			// Get element collection for the site rows in Search Widget
			List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchResults_RowItems, "present");
			
			// Click the first site to populate site details in linked Pricing widget
			elements.get(0).click();			
	
		// Drag desired icon to the same widget as the Search widget
			reporterHelper.log("\nDragging Site hover menu icon to same container as Search widget");
			Actions builder = new Actions(driver);

			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchResults_SelectedItemHoverMenuIcon, "clickable");
			// Move to hover menu out icon 
		    builder.moveToElement(tempWebElement)
		    		.pause(Duration.ofSeconds(2)).perform();
		    
		    tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchResults_SelectedItemHoverMenuContainer, "present").findElement(By.xpath("img[@title='" + newWidgetIdentifierString + "']"));
		    builder.dragAndDropBy(tempWebElement, 1, 1).perform();
		    
		    reporterHelper.log("Completed dragging widget icon to same container as search, waiting for 5 seconds..."); //BrowserHelper.customSleep(5 * 1000);
	    
		    // added to avoid bug where widgets are cloned
		    BrowserHelper.customSleep(5 * 1000);
		

	    // If necessary, move created widget to targetContainer
		    String newWidgetNameAttribute = "";

	    	switch(newWidgetIdentifierString) {
	    	case "Intel":
	    		newWidgetNameAttribute = "k-intel-widget";
				break;
	    	case "Map":
	    		newWidgetNameAttribute = "k-map-widget";
				break;
	    	case "Report Viewer":
	    		newWidgetNameAttribute = "report-viewer-widget";
				break;
	    	case "Pump Price Update":
	    		newWidgetNameAttribute = "site-mixer-widget";
				break;	
	    	case "Site Metrics":
	    		newWidgetNameAttribute = "site-metrics-widget";
				break;	
    		case "Pricing":
	    		newWidgetNameAttribute = "pricing-widget";
				break;
	    	case "Events Manager":
	    		newWidgetNameAttribute = "manage-events-widget";
				break;		
	    	case "Site Manager":
	    		newWidgetNameAttribute = "manage-sites-widget";
				break;		
	    	case "Site Strategy":
	    		newWidgetNameAttribute = "strategy";
				break;	
	    	case "Site Planner":
	    		newWidgetNameAttribute = "planner";
				break;		
	    	case "Notes":
	    		newWidgetNameAttribute = "notes-widget";
				break;						
    		case "Search":
	    		newWidgetNameAttribute = "adv-search-widget";
				break;
    	    default:
    	        reporterHelper.customFailScript("Unknown widget name in method dragWidgetIconFromSearchWidgetSelectedSite: " + newWidgetIdentifierString);
    		}
	    
		    if(!(searchWidgetContainerID.split("container")[1].equalsIgnoreCase(targetContainer))) {
		    	reporterHelper.log("Moving new " + newWidgetIdentifierString + " widget to container " + targetContainer + ", waiting for 5 seconds..."); //BrowserHelper.customSleep(5 * 1000);
	
		    	// set target widget name attribute identifier for the chosen dragged icon
		    	
		    	WebElement newWidgetElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[@id='" + searchWidgetContainerID + "']//div[@name = '" + newWidgetNameAttribute + "']"), "present");
		    	WebElement newWidgetElementHead = newWidgetElement.findElement(By.xpath(".//div[contains(@class, 'widget-head')]"));
		    	WebElement targetContainerElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[@id='container" + targetContainer + "']"), "present");
		    	
		    	builder.dragAndDrop(newWidgetElementHead, targetContainerElement).perform();
		    	
		    	reporterHelper.log("Finished moving new " + newWidgetIdentifierString + " widget to container");
		    	
		    } else {
		    	reporterHelper.log("New " + newWidgetIdentifierString + " widget already in correct contianer");
		    }
		    	
	
	    // Synchronise on the widget being present in the target container
	    BrowserHelper.syncOnElement(driver, By.xpath("//div[@id='container" + targetContainer + "']//div[@name = '" + newWidgetNameAttribute + "']"), "present");
		
	    reporterHelper.log("New " + newWidgetIdentifierString + " widget sucecssfully created");
		
	    reporterHelper.takeScreenshot(driver, "Main_Page-Widget_" + newWidgetIdentifierString + "_Dragged_From_Selected_Site_Complete");
	    
	    kalibrateHelper.syncAllPresentWidgets();
	    
	    BrowserHelper.checkForConsoleErrors();
	    
	}




	private WebElement hoverThenReturnElementFromListByAttribute(By hoverElementBy, By listElementsBy,
			String attributeNameString, String attributeTargetValueString) {
		
        	
        reporterHelper.log("\nMoving to hover element...");
        Actions builder = new Actions(driver);
        builder.moveToElement(BrowserHelper.syncOnElement(driver, hoverElementBy, "clickable")).build().perform();
        builder.pause(Duration.ofSeconds(2));
        
        List<WebElement> listElements = BrowserHelper.syncOnElements(driver, listElementsBy, "present");
		reporterHelper.log("\tNumber of items found: " + listElements.size());
		reporterHelper.log("\tSearching for item that has at attribute of '" + attributeNameString + "' with a value of '" + attributeTargetValueString + "'");
		
		for (int j = 0; j < listElements.size(); j++) {
			
			tempWebElement = listElements.get(j);
			String tempString = tempWebElement.getAttribute(attributeNameString);
			
			reporterHelper.log("\tItem number " + (j+1) + " has the value: " + tempString);
			
			if(tempString.equals(attributeTargetValueString)) {
				reporterHelper.log("\tFound target item in list, moving to element in 5 seconds...");
				
				builder.moveToElement(tempWebElement).pause(Duration.ofSeconds(2)).build().perform();
//				break;
			}
		}
		
		reporterHelper.log("\tthen returning element in 5 seconds...");
		BrowserHelper.customSleep(5 * 1000);
		return tempWebElement;
	}

	@When("^User creates a new triple column Workspace named '(.*)'$")
	public void userCreatesANewTripleColumnWorkspaceNamed(String workspaceNameParam) {
		
		workspaceName = workspaceNameParam;
		
    	// Click workspace menu to expand	
        WebElement workspaceMenuElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuCollapsed, "clickable");

        workspaceMenuElement.click();
        
        reporterHelper.takeScreenshot(driver, "Main_Page-Add_Workspace-Workspace_Menu_Opened");

        
        // Click add workspace menu item
        WebElement addWorkspaceElement = BrowserHelper.syncOnElement(driver, 
        		kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuAddWorkspaceItem, 
        		"clickable");
        addWorkspaceElement.click();
        
        // Give unique name to workspace

        reporterHelper.takeScreenshot(driver, "Main_Page-Add_Workspace-Add_Workspace_Clicked");
        
        try {
        	
        	BrowserHelper.customSleep(5 * 1000);
        	
        	WebElement workspaceNameElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuAddWorkspaceNameInputField, "present");
			
			reporterHelper.log("Clicking on workspace name field...");
			workspaceNameElement.click();
			
			reporterHelper.log("Clearing text in workspace name field...");
			workspaceNameElement.clear();
			
			reporterHelper.log("Entering following text into field using sendKeys: " + workspaceName);
			workspaceNameElement.sendKeys(workspaceName);
			
			reporterHelper.takeScreenshot(driver, "Main_Page-Add_Workspace-Name_Entered");
	        // Click workspace menu to collapse
	        workspaceMenuElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuExpanded, "clickable");
	        workspaceMenuElement.click();
	        
	        reporterHelper.takeScreenshot(driver, "Main_Page-Add_Workspace-Workspace_Menu_Minimised");
	        
		} catch (Exception e) {
			reporterHelper.customFailScript("Issue setting new workspace name: " + e.toString());
		}
        
        kalibrateHelper.closeAllOpenWidgets();
	}




	@When("^User creates a new Workspace named '(.*)'$")
	public void userCreatesAWorkspaceNamed(String workspaceNameParam) {
		
		userCreatesANewTripleColumnWorkspaceNamed(workspaceNameParam);
	}
		
	@When("^User creates a new single column Workspace named '(.*)'$")
	public void userCreatesANewSingleColumnWorkspaceNamed(String workspaceNameParam) {
		
		workspaceName = workspaceNameParam;
		
    	// Click workspace menu to expand	
        WebElement workspaceMenuElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuCollapsed, "clickable");

        workspaceMenuElement.click();
        
        // Click add workspace menu item
        WebElement addWorkspaceElement = BrowserHelper.syncOnElement(driver, 
        		kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuAddWorkspaceItem, 
        		"clickable");
        addWorkspaceElement.click();
        
     // Give unique name to workspace
        try {
        	BrowserHelper.customSleep(5 * 1000);
        	
			WebElement workspaceNameElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuAddWorkspaceNameInputField, "present");
			
			BrowserHelper.customSleep(5 * 1000);
			
			reporterHelper.log("Clicking on workspace name field...");
			workspaceNameElement.click();
			
			reporterHelper.log("Clearing text in workspace name field...");
			workspaceNameElement.clear();
			
			reporterHelper.log("Entering following text into field using sendKeys: " + workspaceName);
			workspaceNameElement.sendKeys(workspaceName);

			
		} catch (Exception e) {
			reporterHelper.customFailScript("Issue setting new workspace name: " + e.toString());
		}
        
        // Set Layout
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuAddWorkspaceLayout1ColumnIcon, "clickable").click();
        
        // Click workspace menu to collapse
        workspaceMenuElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuExpanded, "clickable");
        workspaceMenuElement.click();
        
        kalibrateHelper.closeAllOpenWidgets();
        
	}
	@Then("^The created workspace is displayed$")
	public void theCreatedWorkspaceIsDisplayed() {
		// TODO: Verification steps to check that the created workspace has been displayed
		reporterHelper.log("The created workspace " + workspaceName + " is displayed correctly");

        BrowserHelper.checkForConsoleErrors();
	}

	@When("^User deletes the created Workspace$")
	public void userDeletesTheCreatedWorkspace() {

		reporterHelper.takeScreenshot(driver, "Main_Page-Delete_Workspace-Before");

    	// Click workspace menu to expand
		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuCollapsed))
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuCollapsed, "clickable").click();
		
		reporterHelper.log("\n\nLooking for workspace menu selected row...");
		WebElement selectedWorkspaceHeader = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuSelectedWorspace, "clickable");
		reporterHelper.log("\tIdentified workspace menu selected row");

		reporterHelper.log("\n\nLooking for selected row's Delete button...");
		WebElement deleteButton = selectedWorkspaceHeader.findElement(By.xpath(".//button[contains(text(),'Delete')]"));
		reporterHelper.log("\tIdentified selected row's Delete button");
		
		reporterHelper.log("\n\nLooking for selected row's Settings button...");
		WebElement settingsButton = selectedWorkspaceHeader.findElement(By.xpath(".//img[@title= 'Settings']"));
		reporterHelper.log("\tIdentified selected row's Settings button");

	        
		reporterHelper.takeScreenshot(driver, "Main_Page-Delete_Workspace-Workspace_Menu_Expanded");
		
		if(!deleteButton.isDisplayed()) {
			reporterHelper.log("Delete Worksapce button not displayed, clicking settings...");
			settingsButton.click();
		}
			
		reporterHelper.takeScreenshot(driver, "Main_Page-Delete_Workspace-Settings_Clicked_If_Required");
		
		try {
			reporterHelper.log("About to click button...");
			deleteButton.click();
			reporterHelper.takeScreenshot(driver, "Main_Page-Delete_Workspace-Delete_Button_First_Click");
			deleteButton.click();
			reporterHelper.takeScreenshot(driver, "Main_Page-Delete_Workspace-Delete_Button_Second_Click");
		} catch (Exception e) {
			reporterHelper.customFailScript("Issue performing deleteButton.click(): :" + e.toString());
		}

    	// Click workspace menu to collapse
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuExpanded, "clickable").click();

	}

	@Then("^The created workspace is deleted$")
	public void theCreatedWorkspaceIsDeleted() {
		// TODO: Verification steps to check that created workspace has been removed from the workspace menu
		reporterHelper.log("The created workspace is no longer in the workspace menu");
	}
	

	@When("^User adds search widget to created workspace$")
	public void userAddsSearchWidgetToCreatedWorkspace() {
        // Start of populate workspace
	        
	    	// Click configure widgets menu icon to expand menu
			WebElement configureWidgetsMenuIconElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon, "clickable");
	        configureWidgetsMenuIconElement.click();
	
	        Actions builder;
	        Action dragAndDrop;
	
	        // Start of Add Search Widget to Container 1
	        
        	// Click Tools
        	WebElement configureWidgetsMenuToolsMenuItem = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ToolsWidgetIcon, "clickable");
        	configureWidgetsMenuToolsMenuItem.click();
        
	        // Identify Search Widget
	        WebElement searchDraggableWidgetElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_SearchWidgetIcon, "clickable");

	        // Identify container to drag widget to
	        WebElement widgetContainer1Element = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer1, "clickable");

	        // Create Actions instance and define behaviour
	        builder = new Actions(driver);
	        dragAndDrop = builder.clickAndHold(searchDraggableWidgetElement)
	        .moveToElement(widgetContainer1Element)
	        .release(widgetContainer1Element)	         
	        .build();
	        
	        // Perform action
	        dragAndDrop.perform();
	        
	        // End of Add Search Widget to Container 1
		        
	    	// Click configure widgets menu icon to collapse menu
	        configureWidgetsMenuIconElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon, "clickable");
	        configureWidgetsMenuIconElement.click();

	    // End of populate workspace
        reporterHelper.takeScreenshot(driver, "Main_Page-Search_Widget_Added");    
	}

	@Then("^The workspace is populated with widgets$")
	public void theWorkspaceIsPopulatedWithWidgets() {
		// TODO: Verification steps to check that created workspace has been removed from the workspace menu
		reporterHelper.log("The created workspace is populated with widgets");
	}
	


	@When("^User Logout from Kalibrate$")
	public void userLogoutFromKalibrate() {
	    // Click menu hamburger icon
		
		WebElement menuHamburgerElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_HamburgerIcon, "clickable");
		
	    reporterHelper.log("Menu hamburger icon identified successfully");
	
	    menuHamburgerElement.click();
	
	    reporterHelper.log("Menu hamburger icon clicked successfully");
	    
	    // Click Logout

	    WebElement logoutSettingsMenuOptionElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_MainMenuLogoutItem, "clickable");
	    
	    reporterHelper.log("logout settings menu item identified successfully");
	    
		reporterHelper.startTimer("Click Logout to Login Credentials");
		logoutSettingsMenuOptionElement.click();
	}


	
	@Then("^The browser is closed$")
	public void theBrowserIsClosed() {
	    driver.quit();
	    reporterHelper.log("Broswer closed");
	}


	@When("^Search initiated with no filter$")
	public void searchInitiatedWithNoFilter() {
		
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable");
		
		reporterHelper.startTimer("Search no filter");
		tempWebElement.click();
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
        reporterHelper.stopTimer("Search no filter");

		reporterHelper.startTimer("Search no filter");
		tempWebElement.click();
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
        reporterHelper.stopTimer("Search no filter");

		reporterHelper.startTimer("Search no filter");
		tempWebElement.click();
        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
        reporterHelper.stopTimer("Search no filter");

	}
	
	@When("^Murphy Search widget filter applied with an? '(.*)' filter of '(.*)'$")
	public void murphySearchWidgetFilterApplied(String filterCategoryParam, String filteredListItemParam) throws Throwable {
		
		filterCategory = filterCategoryParam;
		filteredListItem = filteredListItemParam;
		
		// If the remove Filter button is not displayed then click filter button
		if(!BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_RemoveFilterButton)) {
			if(!BrowserHelper.isElementDisplayed(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton)) {
				reporterHelper.log("*************** Filter Icon not present due to bug, switching Search Type to force it to be displayed....");
				searchWidgetSearchTypeApplied("All Sites");
				searchWidgetSearchTypeApplied("Own Sites");
			}
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton, "clickable").click();
		}
		
		// Check that filters are being displayed, if not click the PreDefinedFiltersButton
		reporterHelper.log("*************** Checking whether filters are displyed and remove filter button available in 5 seconds...."); BrowserHelper.customSleep(5 * 1000);
		if(!BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_RemoveFilterButton)) {
			reporterHelper.log("*************** Filters are not displyed therefore clicking filters button in 5 seconds...."); BrowserHelper.customSleep(5 * 1000);
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton, "clickable").click();
		}
		
	
		switch (filterCategory) {
        	case "Sites for review":
	        	// Selected filter type has a checkbox that will be checked by default
	        	reporterHelper.log("Set checkbox accordingly");
	        	tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterListCheckbox, "present");
	        	
	
	        	reporterHelper.log("1Current status of checkbox = " + tempWebElement.isSelected());
	        	
	        	if(filteredListItemParam.replaceAll("[^a-z0-9]", "").equalsIgnoreCase("checked")) {
	        		reporterHelper.log("Checking as filteredListItemParam = " + filteredListItemParam.replaceAll("[^a-z0-9]", ""));
	        		if(!tempWebElement.isSelected())
	        			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterListCheckboxLabel, "clickable").click();
	        	}
	        	else {
	        		reporterHelper.log("Un-checking as filteredListItemParam = " + filteredListItemParam.replaceAll("[^a-z0-9]", ""));
	        		if(tempWebElement.isSelected()) {
	        			reporterHelper.log("2Current status of checkbox = " + tempWebElement.isSelected());
	        			
	        			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterListCheckboxLabel, "clickable").click();
	        		}
	        	}
        		
            break;
        default:
            reporterHelper.customFailScript("Unknown filter category in method murphySearchWidgetFilterApplied: " + filterCategoryParam);
		}
	
		
	// Click search button
		reporterHelper.takeScreenshot(driver, "Search-Filter_Set_Prior_To_Search");
		reporterHelper.log("Click Search Button");
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable");
		
		reporterHelper.startTimer("Search widget search initiated with " + filterCategory + " filter");
		tempWebElement.click();
	}	


	@When("^Search widget Search Type of '(.*)' is applied$")
	public void searchWidgetSearchTypeApplied(String searchTypeParam) throws Throwable {
		

		if(!BrowserHelper.isElementDisplayed(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchTypeDropDownListItems)) {
			reporterHelper.log("Clicking Search Type dropdown icon");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchTypeDropDownButton, "clickable").click();
		}
		
//		BrowserHelper.customSleep(5 * 1000);
		
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchTypeDropDownListItems, "present");

		
		reporterHelper.log("\tNumber of Search Types identified: " + elements.size());
		
		reporterHelper.takeScreenshot(driver, "Search-Before_Selecting_Search_Type_" + searchTypeParam);
		
		boolean searchTypeSelected = false;
		
		for (int i = 0; i < elements.size(); i++) {
			
			tempWebElement = elements.get(i);
			tempWebElementText = elements.get(i).getText();
			
			if(tempWebElementText.equalsIgnoreCase(searchTypeParam)) {
				searchTypeSelected = true;
				tempWebElement.click();
				break;
			}
		}
		
		if(!searchTypeSelected)
			reporterHelper.customFailScript("Unable to find Search Type: " + searchTypeParam);
			
		reporterHelper.takeScreenshot(driver, "Search-After_Selecting_Search_Type_" + searchTypeParam);

//		BrowserHelper.customSleep(5 * 1000);
	}	
	
	//And Search widget filter applied with a 'Sites for review' filter of '<checked>'
	@When("^Search widget filter applied with an? '(.*)' filter of '(.*)'$")
	public void searchWidgetFilterApplied(String filterCategoryParam, String filteredListItemParam) throws Throwable {
		
		// Check if the filter should be derived from config
		if(!filteredListItemParam.equalsIgnoreCase("<checked>") 
				&& !filteredListItemParam.equalsIgnoreCase("<unchecked>") 
				&& filteredListItemParam.contains("<") && filteredListItemParam.contains(">")) {
			reporterHelper.log("\n\n\nfilteredListItemParam = '" + filteredListItemParam + "' therefore deriving from config...");
			filteredListItemParam = Utils.getProperty(filteredListItemParam.replaceAll("<", "").replaceAll(">", ""));
			reporterHelper.log("derived filteredListItemParam = '" + filteredListItemParam + "'\n\n\n");
		}
		
		filterCategory = filterCategoryParam;
		filteredListItem = filteredListItemParam;
		

		
		// Get to default state with linked pricing widget having no site selected
			reporterHelper.log("\nSetting Search Widget to default clean state...");
			
			// if filters are present remove any previous filters
			if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FiltersCountBubble)) {
				
				// If the remove Filter button is not displayed then click filter button
				if(!BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_RemoveFilterButton)) {
					if(!BrowserHelper.isElementDisplayed(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton)) {
						reporterHelper.log("*************** Filter Icon not present due to bug, switching Search Type to force it to be displayed....");
						searchWidgetSearchTypeApplied("All Sites");
						searchWidgetSearchTypeApplied("Own Sites");
					}
					BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton, "clickable").click();
				}
				
				
				// Check that filters are being displayed, if not click the PreDefinedFiltersButton
				reporterHelper.log("*************** Checking whether filters are displyed and remove filter button available in 5 seconds...."); BrowserHelper.customSleep(5 * 1000);
				if(!BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_RemoveFilterButton)) {
					reporterHelper.log("*************** Filters are not displyed therefore clicking filters button in 5 seconds...."); BrowserHelper.customSleep(5 * 1000);
					BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton, "clickable").click();
				}
				
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_RemoveFilterButton, "clickable").click();
				
				
			}
			
//			// do a no filter search
//			reporterHelper.log("\tPerforming a search with no filter to get to known default state");
//			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput, "visible").clear();
//			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
//			
//			reporterHelper.log("\tSearch widget at default clean state, waiting 5 seconds..."); BrowserHelper.customSleep(5 * 1000);
//			
//			// Store the non-filtered number of matches
//			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
//			searchWidgetNoFilterMatchesCount = tempWebElement.getText().split(" ")[5];
//			reporterHelper.log("Search widget non-filtered match count: " + searchWidgetNoFilterMatchesCount);		
			
			// do a filter search based on name filter defined in config file
//			reporterHelper.log("\tPerforming a name search with the filter defined in config parameter DEFAULT_SEARCH_NAME_FILTER");
			reporterHelper.log("\tPerforming a name search with the filter defined in config parameter DEFAULT_SEARCH_NAME_FILTER_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER"));

			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput, "visible").clear();
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput, "visible").sendKeys(Utils.getProperty("DEFAULT_SEARCH_NAME_FILTER_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER")));
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
			
			//sync
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
			
		// Click the filter button
			
			// If the remove Filter button is not displayed then click filter button
			if(!BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_RemoveFilterButton)) {
				if(!BrowserHelper.isElementDisplayed(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton)) {
					reporterHelper.log("*************** Filter Icon not present due to bug, switching Search Type to force it to be displayed....");
					searchWidgetSearchTypeApplied("All Sites");
					searchWidgetSearchTypeApplied("Own Sites");
				}
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton, "clickable").click();
			}

			// Check that filters are being displayed, if not click the PreDefinedFiltersButton
			reporterHelper.log("*************** Checking whether filters are displyed and remove filter button available in 5 seconds...."); BrowserHelper.customSleep(5 * 1000);
			if(!BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_RemoveFilterButton)) {
				reporterHelper.log("*************** Filters are not displyed therefore clicking filters button in 5 seconds...."); BrowserHelper.customSleep(5 * 1000);
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_PreDefinedFiltersButton, "clickable").click();
			}
			
		
			
		// Select the filter category
			reporterHelper.log("Select Filter Category: " + filterCategory);
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterCategorySelect, "visible");
			Select select = new Select(tempWebElement);
			select.selectByVisibleText(filterCategory);
		
			switch (filterCategoryParam) {
			case "Sites for review":
	        	// Selected filter type has a checkbox
	        	reporterHelper.log("Set checkbox accordingly");
	        	tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterListCheckbox, "present");
	        	

	        	if(filteredListItemParam.replaceAll("[^a-z0-9]", "").equalsIgnoreCase("checked")) {
	        		reporterHelper.log("Checking as filteredListItemParam = " + filteredListItemParam.replaceAll("[^a-z0-9]", ""));
	        		if(!tempWebElement.isSelected())
	        			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterListCheckboxLabel, "clickable").click();
	        	}
	        	else {
	        		reporterHelper.log("Un-checking as filteredListItemParam = " + filteredListItemParam.replaceAll("[^a-z0-9]", ""));
	        		if(tempWebElement.isSelected()) {
	        			reporterHelper.log("2Current status of checkbox = " + tempWebElement.isSelected());
	        			
	        			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterListCheckboxLabel, "clickable").click();
	        		}
	        	}

	            break;
	        case "Area":
	        case "Product group":
	        case "Product":
	        case "Network":	        	
	        	// Selected filter type has a select filter list
				reporterHelper.log("Select Filter List Item:" + filteredListItem);
				tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterListSelect, "present");
				select = new Select(tempWebElement);
				select.selectByVisibleText(filteredListItem);
	            break;
	        default:
	            reporterHelper.customFailScript("Unknown filter category in method searchWidgetFilterApplied: " + filterCategoryParam);
			}
		
		// Click search button
			reporterHelper.takeScreenshot(driver, "Search-" + filterCategory + "_Filter_Set_Prior_To_Search");
			reporterHelper.log("Click Search Button");
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable");
			
			BrowserHelper.checkForConsoleErrors();
			
			reporterHelper.startTimer("Search widget search initiated with " + filterCategory + " filter");
			tempWebElement.click();
	}
	        
	@Then("^a filtered set of site results will be displayed in (.*) widget with (\\d+) matche?s?$")
	public void aFilteredSetOfSiteResultsWillBeDisplayedInSearchWidget(String widgetName, String matchesCount) throws Throwable {

		if(performSync) {
		    if (widgetName.equalsIgnoreCase("Search")) {
		    	BrowserHelper.syncOnElement(driver, By.xpath("//div[@name='adv-search-widget']//div[@class='search-results-outer']//span[contains(text(), 'of " + matchesCount + " matches')]"), "visible");
		    	
		    	if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FiltersCountBubble)) {
					reporterHelper.stopTimer(widgetName + " widget search initiated with " + filterCategory + " filter");
				} else {
					reporterHelper.stopTimer(widgetName + " widget search initiated");
				}
	
				
		    } else if (widgetName.equals("Map Search")) {
	//	    	BrowserHelper.syncOnElement(driver, By.xpath("//div[@name='adv-search']//div[@class='search-results-outer']//span[contains(text(), 'of " + matchesCount + " matches')]"), "visible");
		    	BrowserHelper.syncOnElement(driver, By.xpath("//div[@class='adv-search']//span[contains(text(), 'of " + matchesCount + " matches')]"), "visible");
		    	reporterHelper.stopTimer(widgetName + " widget search initiated");
		    	
		    }
		} else {
		    if (widgetName.equalsIgnoreCase("Search")) {
		    
				if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FiltersCountBubble)) {
					reporterHelper.stopTimer(widgetName + " widget search initiated with " + filterCategory + " filter");
				} else {
					reporterHelper.stopTimer(widgetName + " widget search initiated");
				}
	
		    }
		}
		
	    
			
		// Store the filtered number of matched sites string
		searchWidgetFilteredMatchesCount = matchesCount;
		reporterHelper.log("Search widget filtered match count: " + searchWidgetFilteredMatchesCount);
		reporterHelper.takeScreenshot(driver, widgetName + "-Filtered_results");
		
		BrowserHelper.checkForConsoleErrors();
		
	}
	
	@When("^a site is selected from '(.*)' widget results$")
	public void aSiteIsSelectedFromSearchWidgetResults(String widgetName) throws Throwable {
		
    	if(widgetName.equalsIgnoreCase("Search")) {
    		List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchResults_RowItems, "present");
    		tempWebElement = elements.get(0);
    		
    		searchWidgetSelectedSiteName = tempWebElement.getText().split(" - ")[0];
    		searchWidgetSelectedImportCode = tempWebElement.getText().split(" - ")[1];
    		
    		reporterHelper.log("Clicking item in Search Widget site results list with name: " + searchWidgetSelectedSiteName);
    		reporterHelper.log("Clicking item in Search Widget site results list with import code: " + searchWidgetSelectedImportCode);
    		
    		reporterHelper.startTimer("Select site in " + widgetName + " widget");
    		tempWebElement.click();

    	} else {
    		reporterHelper.customFailScript("Widget '" + widgetName + "' is currently unsupported in aSiteIsSelectedFromSearchWidgetResults method");
    	}
    	
    	BrowserHelper.checkForConsoleErrors();

		
	}

	@Then("^the site selected in '(.*)' widget should be displayed in linked '(.*)' widget$")
	public void theSelectedSiteShouldBeDisplayedInWidget(String donorWidgetName, String targetWidgetName) throws Throwable {
		if(performSync) {
	    	if(targetWidgetName.equalsIgnoreCase("Pricing")) {
	    		
	    		// Sync on Pricing Widget Title that includes selected site name 
	    		String xpath = "//div[contains(@class, 'widget-head')]//h4[contains(text(), '" 
	    						+ targetWidgetName 
	    						+ "')][contains(text(), '" 
	    						+ searchWidgetSelectedSiteName 
	    						+ "')]";
	    		
	    		reporterHelper.log("Checking for " 
	    							+ targetWidgetName 
	    							+ " widget with site '" 
	    							+ searchWidgetSelectedSiteName 
	    							+ "' in the widget title using xpath: " 
	    							+ xpath);
	    		
	    		BrowserHelper.syncOnElements(driver, By.xpath(xpath), "present");
	    		
	    		// Sync on Pricing Widget Run Rate
	    		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_RunRate, "present");
	    		
	    	} else if(targetWidgetName.equalsIgnoreCase("Intel")) {
	    		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_ProfitabilityRankingGraphLabel_SiteSelected, "clickable");
	    		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_PumpPriceVsTotalCostLabel_SiteSelected, "clickable");
	    	} else {
	    		reporterHelper.customFailScript("Target widget '" 
	    					+ targetWidgetName 
	    					+ "' is currently unsupported in theSelectedSiteShouldBeDisplayedInWidget method");
	    	}
		}
		reporterHelper.stopTimer("Select site in " + donorWidgetName + " widget");
		
		reporterHelper.takeScreenshot(driver, targetWidgetName + "-Site_Displayed_When_Selected_In_" + donorWidgetName);
		
		reporterHelper.setPassStatus("PASSED");

		BrowserHelper.checkForConsoleErrors();

	}
		
	@When("^Search initiated with a name filter$")
	public void search_initiated_with_a_name_filter() throws Throwable {
//		nameFilter = Utils.getProperty("DEFAULT_SEARCH_NAME_FILTER");
		nameFilter = Utils.getProperty("DEFAULT_SEARCH_NAME_FILTER_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER"));
		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput, "clickable").sendKeys(nameFilter);
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
	}

	
//	When Search widget search initiated with a name filter of 'PLASPOELPOLDER'
	@When("^Search widget search initiated with a name filter of '(.*)'$")
	public void searchWidgetSearchInitiatedWithANameFilterOf(String filterText) throws Throwable {

		if(filterText == null)
			filterText = "";
		
		filterCategory = "name filter of '" + filterText + "'";
		
		// Do a no filter search to get to Client default map view
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput, "visible").clear();
		reporterHelper.log("Just cleared search widget search string field, waiting 2 seconds"); BrowserHelper.customSleep(2 * 1000);
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
		reporterHelper.log("Just clicked search widget search button, waiting 2 seconds"); BrowserHelper.customSleep(2 * 1000);
		
		// Taken out 26/02/2018 as cannot be certain intel widget is present
//		reporterHelper.log("Syncing on seeing result set counter in Intel widget in 2 seconds"); BrowserHelper.customSleep(2 * 1000);
//		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_intelWidget_ResultSetCounter, "present");
		
		// Store the non-filtered number of matches
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
		searchWidgetNoFilterMatchesCount = tempWebElement.getText().split(" ")[5];
	
		reporterHelper.log("Search widget non-filtered match count: " + searchWidgetNoFilterMatchesCount);
		
		// Enter search string and client Search button
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_FilterInput, "clickable").sendKeys(filterText);
		
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable");
		reporterHelper.startTimer("Search widget search initiated with " + filterCategory + " filter");
		tempWebElement.click();
		
	}
	
	@When("^Map widget search initiated with a name filter of '(.*)'$")
	public void mapWidgetSearchInitiatedWithANameFilterOf(String filterText) throws Throwable {
		
		// Click Magnifying Glass to display Search 
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_SearchIcon, "clickable").click();
		
		
		// Do a no filter search to get to Client default map view
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_Search_FilterInput, "visible").clear();
		reporterHelper.log("Just cleared search string field, waiting 2 seconds"); BrowserHelper.customSleep(2 * 1000);
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_Search_SearchButton, "visible").click();
		reporterHelper.log("Just clicked search, waiting 2 seconds"); BrowserHelper.customSleep(2 * 1000);
		
		reporterHelper.log("Syncing on seeing some own site teardrops in 2 seconds"); BrowserHelper.customSleep(2 * 1000);
		BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_OwnSiteTearDropIcon, "present");
		
		// Store the non-filtered number of matches string for client
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_Search_ResultsCountText, "visible");
		mapWidgetNoFilterSearchResultsMessage = tempWebElement.getText();
		
		// Enter search string and client Search button
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_Search_FilterInput, "visible").sendKeys(filterText);
		
		
		reporterHelper.log("WILL START TIMER AND CLICK SEARCH IN 2 SECONDS"); BrowserHelper.customSleep(2 * 1000);
		
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_Search_SearchButton, "visible");
		reporterHelper.startTimer("Map widget search initiated");
		tempWebElement.click();
		
	}

	
	@Then("^map will update to display (\\d+) sites?$")	
	public void mapWillZoomToDisplaySingleSite(String expectedSiteCount) {

		if(performSync) {
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_Search_ResultsCountText_SingleResult, "visible");
			String filteredSearchResultsMessage = tempWebElement.getText();
	
			List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_OwnSiteBubbleIcon, "present");
			
			if(elements.size() != Integer.parseInt(expectedSiteCount))
				reporterHelper.customFailScript("Number of own site in map is " + elements.size() + ", expecting " + Integer.parseInt(expectedSiteCount));
			else
				reporterHelper.log("Confirmed a single own site is displayed: " + filteredSearchResultsMessage);	
		}
		reporterHelper.stopTimer("Map widget search initiated");
		
		
		
	}
	
    
	@When("^the single (.*) site is clicked$")	
	public void theSingleOwnSiteIsClicked(String siteType) {
    	By siteByLocator = null;
    	
    	if(siteType.equalsIgnoreCase("own"))
    		siteByLocator = kalibrateLocatorLibrary.kalibrate_mapWidget_OwnSiteBubbleIcon;
    	else if(siteType.equalsIgnoreCase("own")) // change to other?
    		siteByLocator = kalibrateLocatorLibrary.kalibrate_mapWidget_SiteBubbleIcon;
    	else
			reporterHelper.customFailScript("Site type '" + siteType + "' is currently unsupported in theSingleOwnSiteIsClicked method");
    	
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, siteByLocator, "present");

		if(elements.size() == 1) {
			reporterHelper.log("Clicking on " + siteType + " site bubble");
			
			tempWebElement = BrowserHelper.syncOnElement(driver, siteByLocator, "present");
			
			reporterHelper.startTimer("Pump Price History");
	        new Actions(driver).moveToElement(tempWebElement, 10, 25).click().build().perform();
	        

		} else {
			reporterHelper.customFailScript("Expected single " + siteType + ", however identified " + elements.size());
		}
		
	}
	
	
	@Then("^pump price history information is displayed$")	
	public void pumpPriceHistoryInformaitonIsDisplayed() {
		reporterHelper.log("#### Checking for pump price history1");
		if(performSync) {
			reporterHelper.log("#### Checking for pump price history2");
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_PricingBubble, "visible");
			
		}

		reporterHelper.log("#### Checking for pump price history3");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_mapWidget_PricingBubbleAveragrSiteMargin, "visible");
		reporterHelper.stopTimer("Pump Price History");
		reporterHelper.takeScreenshot(driver, "Map-Pump_Price_History");
	}
	

	@Then("^a filtered set of site results will be displayed matching the name filter$")
	public void aFilteredSetOfSiteResultsWillBeDisplayedMatchingTheNameFilter() throws Throwable {
		// Sync on the Search button present and not disabled
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "visible");
		
		reporterHelper.stopTimer("Search widget search initiated with " + filterCategory + " filter");

		int actualSearchResultsCountInt = 0;
		
		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText)) {
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
		
			String actualSearchResultsMessage = tempWebElement.getText();
			
			reporterHelper.log("\nSearch results string: " + actualSearchResultsMessage);
			
			String[] actualResultsMessageParts = actualSearchResultsMessage.split(" ");
			actualSearchResultsCountInt = Integer.parseInt(actualResultsMessageParts[5]);
		} else {
			actualSearchResultsCountInt = 0;
		}
			
        reporterHelper.takeScreenshot(driver, "Search-Filtered_Results");
        reporterHelper.log("Number of Search widget results: " + actualSearchResultsCountInt);
        
        if(!Kalibrate_Hooks.connectToDatabase) {
        	reporterHelper.log("Database connections are disabled as per config file (CONNECT_TO_DATABASE) therefore DB validation not performed");
        } else {
        	
        	sqlDbResultSet = databaseHelper.executeSQLStringWithResultSet("SELECT * FROM [prc].[OwnSite] WHERE Deleted = 0 AND IsDealerControl = 0 AND Name like N'%" + Utils.getProperty("DEFAULT_SEARCH_NAME_FILTER_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER")) + "%'");
	        
			int rowCount = 0;
	
			try {
				if(sqlDbResultSet.last()){
				    rowCount = sqlDbResultSet.getRow(); 
				    sqlDbResultSet.beforeFirst();
				}
			} catch (Exception e) {
	        	reporterHelper.customFailScript("Exception caught: " + e.toString());
			}
			
			reporterHelper.log("Number of rows in DB Result Set: " + rowCount + "\n");
			
			if(actualSearchResultsCountInt != rowCount)
				reporterHelper.customFailScript("Actual number of search results: " + actualSearchResultsCountInt + " does not match expected number of search results (from db): " + rowCount);
			
        }
        
//        // James added 12/19/2017 for Pricing COnfiguration items test, in attempt to make order of sites consistent
//		// Click search button
//		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
//		BrowserHelper.customSleep(1 * 1000);
//		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
//		BrowserHelper.customSleep(1 * 1000);        
		
	}
	
	@Then("^a filtered set of site results will be displayed$")
	public void a_filtered_set_of_site_results_will_be_displayed() throws Throwable {
		// Sync on the Search buttoin present and not disabled
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "visible");
		
		reporterHelper.stopTimer("Search widget search initiated with " + filterCategory + " filter");

		int actualSearchResultsCountInt = 0;
		
		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText)) {
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
		
			String actualSearchResultsMessage = tempWebElement.getText();
			
			reporterHelper.log("\nSearch results string: " + actualSearchResultsMessage);
			
			String[] actualResultsMessageParts = actualSearchResultsMessage.split(" ");
			actualSearchResultsCountInt = Integer.parseInt(actualResultsMessageParts[5]);
		} else
			// Nothing - actualSearchResultsCountInt = 0;
        
        reporterHelper.takeScreenshot(driver, "Search-Filtered_Results");
        reporterHelper.log("Number of Search widget results: " + actualSearchResultsCountInt);
        
        // James added 12/19/2017 for Pricing COnfiguration items test, in attempt to make order of sites consistent
		// Click search button
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
		BrowserHelper.customSleep(1 * 1000);
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
		BrowserHelper.customSleep(1 * 1000);        
		
	}
	

	
	@When("^in mutli pricing widget set number of sites per page to (.*)$")
	public void inMutliPricingWidgetSetNumberOfSitesPerPageTo(String numberOfSites) {
		
		// Set Number of sites per page
		WebElement settingsIconElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SettingsIcon, "visible");
		settingsIconElement.click();
		
		WebElement sitesPerPageTextBoxElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Settings_SitesPerPageTextBox, "present");
		sitesPerPageTextBoxElement.clear();
		sitesPerPageTextBoxElement.sendKeys(numberOfSites);

		settingsIconElement.click();
		BrowserHelper.customSleep(5 * 1000);
		
	}

	
	@When("^in mutli pricing widget prices are generated using paging with '(.*)' selected$")
	public void inMutliPricingWidgetPricesAreGeneratedUsingPaging(String selectOption) throws Throwable {

		// Ensure widget has has finished loading
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync, "visible");

		// New Do for the paging update
		
		int numberOfMPWidgetPages = 0;
		boolean paginationPresent = false;
		List<WebElement> mpWidgetPageButtons = null;
		
		if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_PaginationNumberedButtons)) {
			mpWidgetPageButtons = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_PaginationNumberedButtons, "visible");
			numberOfMPWidgetPages = mpWidgetPageButtons.size();
			paginationPresent = true;
			
			reporterHelper.log("\n\nPagination pages identified in Multi-Pricing widget: " + numberOfMPWidgetPages);
		} else {
			numberOfMPWidgetPages = 1;
			reporterHelper.log("\n\nPagination pages identified in Multi-Pricing widget: 0");
		}
		
		int sitesForReviewCount = 0;
		
		// Loop for number of pages in MP widget
		for(int currentMPWidgetPage=0; currentMPWidgetPage<numberOfMPWidgetPages; currentMPWidgetPage++ ) {

			if(paginationPresent) {
				BrowserHelper.customSleep(5 * 1000);
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton_NotBusy, "present");
				reporterHelper.log("Clicking page " + (currentMPWidgetPage+1) + " of " + numberOfMPWidgetPages);
				mpWidgetPageButtons.get(currentMPWidgetPage).click();
			}

			// Ensure widget has has finished loading
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync, "visible");
	
	
			// If required, for the current page check site statues and if necessary  
			// wait for sites to process then apply prices any sites in review  
			if (Boolean.parseBoolean(Utils.getProperty("RESET_PRICING_SITES_STATUSES"))) {
	
				int retryCounter = 0;
				boolean exitLoop = false;
				
				int rejectCausedNoChangeCounter = 0;
				sitesForReviewCount = 0;
				int sitesForReviewPreviousCount = 0;
				int maxRetryAttempts = 25;
	
				reporterHelper.takeScreenshot(driver, "Multi-Pricing-Before_Sites_Reset_Statuses_Page_" + currentMPWidgetPage);
				
				reporterHelper.log("\tChecking In Multi-Pricing widget there are 0 sites processing and 0 sites in review for page: " + currentMPWidgetPage);
	
				do {
					retryCounter++;
					if(retryCounter > Integer.parseInt(Utils.getProperty("MAX_SYNC_RETRIES"))/10) {
						reporterHelper.customFailScript("Pre-Batch Gen: Number of retries exceeded when waiting for sites to process, batch may not be running.");
					} else
						reporterHelper.log("1. Starting attempt number " + retryCounter + ". Will stop after: " + Integer.parseInt(Utils.getProperty("MAX_SYNC_RETRIES"))/10);
					
					BrowserHelper.customSleep(10 * 1000);
					
					if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteProcessingIcon)) {
						
						//@@@@
						// get count of number of sites currently in review
	
						sitesProcessingPreviousCount = sitesProcessingCount;
						sitesProcessingCount = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteProcessingIcon, "visible").size();
						reporterHelper.log("Sites processing count: " + sitesProcessingCount);
						
						// compare current number of sites processing to previous number of sites processing, then if it as not changed...
						if(sitesProcessingCount == sitesProcessingPreviousCount) {
							// increment number of times rejected and no decreased in SFR counter and if equals 5 then stop rejected as prices will not reject for some reason
							waitCausedNoChangeCounter++;
							if(waitCausedNoChangeCounter >= maxRetryAttempts) {
								reporterHelper.log("\tSites processing count (" + sitesProcessingCount + ") has not changed despite waiting for " + waitCausedNoChangeCounter + " attempt(s).  Stopping waiting for pricegen");
								exitLoop = true;
								break;
							}
							reporterHelper.log("\tSites processing count has not decreased despite waiitng for " + waitCausedNoChangeCounter + " attempt(s).  Will stop waiting after " + maxRetryAttempts + " attaempts");
						} else // there are a different number of SFR than previous check, will attempt to reject them...
							reporterHelper.log("\tThere are currently a fresh count of sites processing...");
											
						
						//@@@@
						
	//					List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteProcessingIcon, "visible");
	//					reporterHelper.log("\tThere are currently " + elements.size() + " sites processing, waiting for 5 seconds for sites to finish processing");
	//					BrowserHelper.customSleep(5 * 1000);
						
						
						//TODO 18/02/2017 - Need to add similar steps here to cater for sites stuck at pricegen
						
						if (Boolean.parseBoolean(Utils.getProperty("MULTIPRICING_REFRESH_BUG_WORKAROUND"))) {
	
							reporterHelper.log("\t@@@@ Refreshing Multi-Pricing widget due to refresh bug (where SFR icon does not get updated. MULTIPRICING_REFRESH_BUG_WORKAROUND is set to true in config.");
							
							BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
							
							// Ensure widget has has finished loading
							BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync, "visible");
							
							// If necessary select correct page
							if(paginationPresent) {
								BrowserHelper.customSleep(5 * 1000);
								BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton_NotBusy, "present");
								reporterHelper.log("Clicking page " + (currentMPWidgetPage+1) + " of " + numberOfMPWidgetPages);
								mpWidgetPageButtons.get(currentMPWidgetPage).click();
							}
							
							// Ensure widget has has finished loading
							BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync, "visible");
	
							BrowserHelper.customSleep(10 * 1000);
							
						} 
						
					} else if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteAwaitingReviewIcon)) {
						
						// get count of number of sites currently in review
						sitesForReviewPreviousCount = sitesForReviewCount;
						sitesForReviewCount = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteAwaitingReviewIcon, "visible").size();
						reporterHelper.log("Sites for review count: " + sitesForReviewCount);
						
						// compare current number of SFR to previous number of SFR, then if it as not changed...
						if(sitesForReviewCount == sitesForReviewPreviousCount) {
							// increment number of times rejected and no decreased in SFR counter and if equals 5 then stop rejected as prices will not reject for some reason 
							if(rejectCausedNoChangeCounter++ == maxRetryAttempts) {
								reporterHelper.log("\tSites for review count (" + sitesForReviewCount + ") has not decreased despite rejecting prices for " + rejectCausedNoChangeCounter + " attempt(s).  Stopping rejecting sites attempts as they will not reject");
								exitLoop = true;
							}
							reporterHelper.log("\tSites for review count has not decreased despite rejecting prices for " + rejectCausedNoChangeCounter + " attempt(s).  Will stop rejecting after " + maxRetryAttempts + " attaempts");
						} else // there are a different number of SFR than previous check, will attempt to reject them...
							reporterHelper.log("\tThere are currently fresh set of sites in review, clicking Reject button in 15 seconds...");
						
						
						BrowserHelper.customSleep(15 * 1000);
		
	
						// Select All Sites checkbox
						WebElement selectAllCheckBox = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SelectAllSitesCheckbox, "visible");
						selectAllCheckBox.click();
						
						// Check whether the Apply button in enabled, if not click 
						WebElement applyButton = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton, "visible");
						if(applyButton.isEnabled()) {
							reporterHelper.log("Appy Button IS enabled...");
						} else {
							reporterHelper.log("Appy Button IS NOT enabled...");
							selectAllCheckBox.click();
						}
		
						
						// Clicking Apply sites button
						BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_RejectSelectedSitesButton, "visible").click();
						
						// Sync that Reject Prices button click action has completed (button is not disabled)
						BrowserHelper.customSleep(1 * 1000);
						BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton_NotBusy, "present");
						
						reporterHelper.log("Apply button identified as no longer being busy, waiting 5 seconds...");
						BrowserHelper.customSleep(5 * 1000);
						
					} else {
						
						reporterHelper.log("\tIn Multi-Pricing widget there are 0 sites processing and 0 sites in review");
						exitLoop = true;
						
					}
					
					if(paginationPresent) {
						BrowserHelper.customSleep(5 * 1000);
						BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton_NotBusy, "present");
						reporterHelper.log("Clicking page " + (currentMPWidgetPage+1) + " of " + numberOfMPWidgetPages);
						mpWidgetPageButtons.get(currentMPWidgetPage).click();
					}
								
					// Ensure widget has has finished loading
					BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync, "visible");
					
				} while (!exitLoop);
			}
			
			// Waiting for statuses to refresh
//			BrowserHelper.customSleep(240 * 1000);
			BrowserHelper.customSleep(120 * 1000);
	
			reporterHelper.takeScreenshot(driver, "Multi-Pricing-After_Sites_Reset_Statuses_Page_" + currentMPWidgetPage);
			// Select All Sites checkbox
			WebElement selectAllCheckBox = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SelectAllSitesCheckbox, "visible");
			selectAllCheckBox.click();
			
			// Check whether the Apply button in enabled, if not click 
			WebElement applyButton = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton, "visible");
			if(applyButton.isEnabled()) {
				reporterHelper.log("Appy Button IS enabled...");
			} else {
				reporterHelper.log("Appy Button IS NOT enabled...");
				selectAllCheckBox.click();
			}
	
	
			reporterHelper.takeScreenshot(driver, "Multi-Pricing-All_Sites_Selected_Prior_To_PriceGen");
			
			reporterHelper.log("\nChecking whether Generate Prices Panel is expanded...");
			// If the Generate Prices Panel is not displayed then click the show Generate Prices Panel button
			if(!BrowserHelper.isElementDisplayed(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_GeneratePricesButton)) {
				
				reporterHelper.takeScreenshot(driver, "Multi-Pricing-Generate_Prices_Panel_Not_Expanded");
				
				reporterHelper.log("\tGenerate Prices Panel is not expanded, clicking on Generate button...");
				
				tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ShowGeneratePricesPanelButton, "visible");
				tempWebElement.click();
				
				BrowserHelper.customSleep(2 * 1000);
	
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				
				reporterHelper.takeScreenshot(driver, "Multi-Pricing-Generate_Prices_Panel_Expanded");
	
			}
	
		
			// Select the desired option from Select
			reporterHelper.log("\n\nSelecting '" + selectOption + "' from the drop-down");
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_GenerateOptionsSelect, "present");
	//		browserHelper.moveToElement(tempWebElement);
			try {
				Select select = new Select(tempWebElement);
				select.selectByVisibleText(selectOption);
			} catch (Exception e) {
				reporterHelper.customFailScript("Exception caught trying to select from drop-down: " + e.toString());
			}
	
			reporterHelper.takeScreenshot(driver, "Multi-Pricing-Options_Set_Prior_To_Price_Generation");
			
			// Click Generate Prices
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_GeneratePricesButton, "visible");
			reporterHelper.startTimer("Click Generate Prices button in Multi Pricing Widget");
			tempWebElement.click();
	
			// Sync that Generate Prices button click action has completed (button is not disabled)
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_GeneratePricesButton, "visible");
			reporterHelper.stopTimer("Click Generate Prices button in Multi Pricing Widget");
	
			reporterHelper.startTimer("Sites for review present in Multi-Pricing Widget");
			reporterHelper.log("\n\nPrice Generation has begun");
			reporterHelper.takeScreenshot(driver, "Multi-Pricing-Price_Generation_Requested");
	
			
	//		// Wait for 5 seconds then refresh by clicking search in search widget
	//		BrowserHelper.customSleep(5 * 1000);
	//		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "visible").click();
			
	//		// Sync that Site Processing Icons are displayed indicating price generation is running/pending
	//		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteProcessingIcon, "visible");
	//		
			// Check site statues to ensure that price generation has completed
			reporterHelper.log("\n\nWaiting for price generation to complete;");
	
			int retryCounter = 0;
			boolean exitLoop = false;
			
			reporterHelper.log("\tWaiting for Multi-Pricing widget to indicate that there are no more sites processing and > 0 sites in review");
			
			int sitesCount = 0;
			int sitesForReviewPreviousCount = 0;
			int refreshCausedNoChangeCounter = 0;
			int waitingForPriceGenCounter = 0;
			boolean stopWaitingForPriceGen = false;
			int maxRetryAttempts = 25;
			String productsInPriceGen = "";
			
			do {
				// @@@ Do Products In PriceGen Count
				
		        
				try {
			        ResultSet sqlDbResultSet = databaseHelper.executeSQLStringWithResultSet("SELECT	COUNT(DISTINCT opg.OwnSiteUID) SitesWithPriceRequestsToProcess " + 
			        		"FROM		dbo.PNPriceRequest				req " + 
			        		"JOIN		dbo.PNOwnProductGroup			opg " + 
			        		"ON			req.OwnProductGroupUID =		opg.OwnProductGroupUID " + 
			        		"WHERE		req.Processed =					0 " + 
			        		"AND			req.OnHold =					0 " + 
			        		"AND			req.Cancelled =					0 ;");
			
			        sqlDbResultSet.first();
			        productsInPriceGen = sqlDbResultSet.getString(1);
			        
			        reporterHelper.log("%%%% productsInPriceGen %%%%: " + productsInPriceGen);
					
				} catch (Exception e) {
		        	reporterHelper.log("Exception caught: " + e.toString());
				}
				// @@@ End of # Products in PriceGen Count
				
				retryCounter++;
				if(retryCounter > Integer.parseInt(Utils.getProperty("MAX_SYNC_RETRIES"))/10) {
					reporterHelper.customFailScript("Post-Price-Gen: Number of retries exceeded when waiting for sites to process, batch may not be running.");
				} else
					reporterHelper.log("2. Starting attempt number " + retryCounter + ". Will stop after: " + Integer.parseInt(Utils.getProperty("MAX_SYNC_RETRIES"))/10);
	
				// Check to see if Prices are still generating and wait if they are - unless SKIP_WAIT_FOR_PRICEGEN set to true
				if(!Boolean.parseBoolean(Utils.getProperty("SKIP_WAIT_FOR_ALL_SITES_TO_STOP_PRICEGEN")) && !stopWaitingForPriceGen && BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteProcessingIcon)) {
	//				List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteProcessingIcon, "visible");
	//				reporterHelper.log("\t@@ There are currently " + elements.size() + " sites processing, waiting before retry (retryCounter = " + retryCounter + ")");
	
					reporterHelper.log("\t@@ There are currently sites processing, waiting before retry (retryCounter = " + retryCounter + ")");
					
					if(waitingForPriceGenCounter++ >= 9) {
						reporterHelper.log("\t@@ Site(s) still processing after waiting 10 times.  Ignoring remaining sites still processing...");	
						stopWaitingForPriceGen = true;
					}
					
				} else {
					if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteAwaitingReviewIcon)) {
					
						
						// get count of number of sites
						sitesCount = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_RowItems, "visible").size();
						reporterHelper.log("Sites count: " + sitesCount);
	
						// get count of number of sites currently in review
						sitesForReviewCount = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SiteTable_SiteAwaitingReviewIcon, "visible").size();
						reporterHelper.log("Sites for review count: " + sitesForReviewCount);
	
						if(sitesForReviewCount >= (sitesCount - 1)) {
							reporterHelper.log("\tSites for review count (" + sitesForReviewCount + ") is >= to sites count (" + sitesCount + ") minus 1 therefore no need to wait any longer for more SFR's");
							exitLoop = true;
							break;
						} else	if(sitesForReviewCount == sitesForReviewPreviousCount) { // compare current number of SFR to previous number of SFR, then if it as not changed...
							// increment number of times rejected and no decreased in SFR counter and if equals 5 then stop rejected as prices will not reject for some reason
							refreshCausedNoChangeCounter++;
							if(refreshCausedNoChangeCounter == maxRetryAttempts) {
								reporterHelper.log("\tSites for review count (" + sitesForReviewCount + ") has not increased despite waiting for " + refreshCausedNoChangeCounter + " attempt(s).  Stopping waiting for SFR count increase attempts as no further change expected");
								exitLoop = true;
								break;
							} else
								reporterHelper.log("\tSites for review count has not increased despite waiting for prices for " + refreshCausedNoChangeCounter + " attempt(s).  Will stop waiting after " + maxRetryAttempts + " attempts");
						} else // there are a different number of SFR than previous check, will attempt to reject them...
							reporterHelper.log("\tThere are currently an increased number of sites in review, waiting for a further 15 seconds...");
						
						sitesForReviewPreviousCount = sitesForReviewCount;
	
					}
				}
	
	
				
				if (!exitLoop && Boolean.parseBoolean(Utils.getProperty("MULTIPRICING_REFRESH_BUG_WORKAROUND"))) {
	
					reporterHelper.log("\t@@@@@ Refreshing Multi-Pricing widget due to refresh bug (where SFR icon does not get updated. MULTIPRICING_REFRESH_BUG_WORKAROUND is set to true in config.");
					
					BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchButton, "clickable").click();
					
					// Ensure widget has has finished loading
					BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync, "visible");
	
					if(paginationPresent) {
						BrowserHelper.customSleep(5 * 1000);
						BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton_NotBusy, "present");
						reporterHelper.log("Clicking page " + (currentMPWidgetPage+1) + " of " + numberOfMPWidgetPages);
						mpWidgetPageButtons.get(currentMPWidgetPage).click();
					}
					
					// Ensure widget has has finished loading
					BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync, "visible");
					
				} 		
					
				// Waiting for statuses to refresh
				BrowserHelper.customSleep(120 * 1000);
					
			} while (!exitLoop);
			
		}
		
		
		reporterHelper.log("\nPrice Generation is complete");
		reporterHelper.stopTimer("Sites for review present in Multi-Pricing Widget", "Number of sites: " + sitesForReviewCount);
		
		
//		reporterHelper.stopTimer("Sites for review present in Multi-Pricing Widget", "Number of sites: " + sitesForReviewCount);
	}
	
	@Then("^in search widget a number of sites will be in sites for review$")
	public void inSearchWidgetANumberOfSitesWillBeInSitesForReview() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Then("^in multi pricing widget sites for review are processed$")
	public void inMultiPricingWidgetSitesForReviewAreProcessed() throws Throwable {
		
		// Select All Sites checkbox
		WebElement selectAllCheckBox = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SelectAllSitesCheckbox, "visible");
		selectAllCheckBox.click();
		
		// Check whether the Apply button in enabled, if not click 
		WebElement applyButton = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton, "visible");
		if(applyButton.isEnabled()) {
			reporterHelper.log("Appy Button IS enabled...");
		} else {
			reporterHelper.log("Appy Button IS NOT enabled...");
			selectAllCheckBox.click();
		}
		
		reporterHelper.takeScreenshot(driver, "Multi-Pricing-All_Sites_Selected_Prior_To_Apply_Sites");
		
		BrowserHelper.customSleep(1 * 1000);
		
		// click on apply selected button
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton, "visible").click();
		
		reporterHelper.takeScreenshot(driver, "Multi-Pricing-Apply_Sites_Clicked");
		
		// wait for the apply button busy image to go away
		
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton_NotBusy, "present");
//		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_ApplySelectedSitesButton_NotDisabled, "visible");
		
		reporterHelper.takeScreenshot(driver, "Multi-Pricing-Apply_Sites_Button_Not_Busy");
		
		// Wait for 'Site prices applied' span to be displayed and report count
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_SitePricesAppliedIcons, "visible");
		reporterHelper.log("Number of 'Site prices applied' icons identified: " +  elements.size());
		
		reporterHelper.takeScreenshot(driver, "Multi-Pricing-Apply_Sites_Confirmed");
			
	}

	// TODO: Improve this so that widget to be closed can be passed in as widget name
	@Then("^Multi Pricing widget is closed$")
	public void widgetIsClosed() {
		reporterHelper.log("Closing Multi Pricing widget in 5 seconds...");BrowserHelper.customSleep(5 * 1000);
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_CloseWidgetIcon, "present").click();
	}

	@Then("in prices widget sites for review are processed with competitor price surveys submitted during run$")
	public void inPricesWidgetSitesForReviewAreProcessedWithComp() throws Throwable {

		 
		browserHelper.messageBox("Prepare SFR's then click OK");

		if (Boolean.parseBoolean(Utils.getProperty("MULTIPRICING_APPLY_FREEZE_BUG_WORKAROUND"))) {
			reporterHelper.log("Closing Multi-Pricing widget as MULTIPRICING_APPLY_FREEZE_BUG_WORKAROUND is set to true in confg.");
			if(BrowserHelper.isElementDisplayed(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_CloseWidgetIcon)) {
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_CloseWidgetIcon, "present").click();
			}
				
			driver.navigate().refresh();

			// Sleep for 30 seconds to allow browser to refresh and all widgets to load and populate 
			// otherwise widget titles may not yet be populated before syncing widgets
			reporterHelper.log("Waiting for 30 seconds after doing a refresh of browser to workaround MP issue");
			BrowserHelper.customSleep(30 * 1000);
			
			kalibrateHelper.syncAllPresentWidgets();
			
			searchWidgetFilterApplied("Sites for review", "<checked>");
			
		}
		
		
		reporterHelper.takeScreenshot(driver, "Pricing-Before_Clicking_First_SFR");
		
		reporterHelper.log("in inPricesWidgetSitesForReviewAreProcessed. Clicking Search widget results top item in 5 seconds...");BrowserHelper.customSleep(5 * 1000);
		
		
		
		// Get element collection for the site rows in Search Widget
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchResults_RowItems, "present");
		
		// Click the first site to populate site details in linked Pricing widget
		elements.get(0).click();
		reporterHelper.log("top item clicked.  Waiting 5 seconds...");BrowserHelper.customSleep(5 * 1000);
		reporterHelper.takeScreenshot(driver, "Pricing-After_Clicking_First_SFR");

		elements.get(0).click();
		reporterHelper.log("top item clicked.  Waiting 5 seconds...");BrowserHelper.customSleep(5 * 1000);
		reporterHelper.takeScreenshot(driver, "Pricing-After_Clicking_First_SFR_Second_Time");

		kalibrateHelper.syncAllPresentWidgets();
		
		// Sync on RunRate being displayed in Pricing widget
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_RunRate, "present");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Sync, "present");
	
		
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
		String[] numberOfSFRMessageParts = tempWebElement.getText().split(" ");
		
		int actualSitesForReviewCount = Integer.parseInt(numberOfSFRMessageParts[7]);
		
		reporterHelper.log("actualSitesForReviewCount: " + actualSitesForReviewCount);
		
		String timerName = "";

		if (Boolean.parseBoolean(Utils.getProperty("SYNC_PRICING_AGENTS"))) 
			browserHelper.messageBox("Click to start pricing");
		
		
		pricingWidgetTitle = "";
		
		int sitesProcessedCount = 0;
		
		for(int loopCounter = 0; loopCounter < actualSitesForReviewCount; loopCounter++) {
			
			// If required, call db proc PopulateCompetitorPrice to prime Competitor Price Survey data
			// to trigger price generation to see what impact it has on apply SFR timings
			// only do once a number of sites have already been processed as per config  
			if(Boolean.parseBoolean(Utils.getProperty("PRIME_COMPETITOR_PRICE_SURVEY_DATA")) && (loopCounter + 2) == Integer.parseInt(Utils.getProperty("CPS_NUMBER_OF_SFR_TO_PROCESS_BEFORE_DROP"))) {
				reporterHelper.log("Priming Competitor Price Survey data as PRIME_COMPETITOR_PRICE_SURVEY_DATA is set to true in confg.");
				sqlDbResultSet = databaseHelper.executeSQLStringWithResultSet("exec PopulateCompetitorPrice @RecordsToInsert = '" + Utils.getProperty("CPS_RECORDS_TO_INSERT") + "', @OwnSiteBrand = '" + Utils.getProperty("CPS_OWN_SITE_BRAND") + "', @ImportID = '" + Utils.getProperty("CPS_IMPORT_ID") + "';");
			} else
				reporterHelper.log("Not Priming Competitor Price Survey data as PRIME_COMPETITOR_PRICE_SURVEY_DATA is set to false in confg.");

			
			// sync on pricing widget containing some product price input text fields
			reporterHelper.log("Sync on pricing widget containing some product price input text fields");
			BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ProposedPrices_Inputs, "visible");
			BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Sync, "visible");

			
			// Get the site name/details and store in pricingWidgetTitle
			pricingWidgetTitle = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Title, "present").getText();
			reporterHelper.log("\n\nProcessing site: " + pricingWidgetTitle);
			
			reporterHelper.takeScreenshot(driver, "Pricing-Before_Process_Site_" + loopCounter);
			
			// For every 4 SFR's, Apply two, Reject one and Apply one with prices Adjusted
			switch(loopCounter%4) {
				
				case 0:
					
					timerName = "Reject Prices";
					tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_RejectButton, "visible");

					break;
					
				case 2:
					
					timerName = "Apply Prices Adjusted";
					tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ApplyButton, "visible");
					
					// Adjust prices on each product, clicking either up or down one, chosen at random
					List<WebElement> productInputElements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ProposedPrices_Inputs, "present");
					for (int j = 0; j < productInputElements.size(); j++) {
						
						if(new Random().nextBoolean()) {
							reporterHelper.log("Adjusting price UP by one click on product number: " + j); //BrowserHelper.customSleep(1 * 1000);
							productInputElements.get(j).findElement(By.xpath(".//img[@class='input-arrow-up']")).click();
						} else {
							reporterHelper.log("Adjusting price DOWN by one click on product number: " + j);// BrowserHelper.customSleep(1 * 1000);
							productInputElements.get(j).findElement(By.xpath(".//img[@class='input-arrow-down']")).click();
						}
							
					}
					
					break;

				default:

					timerName = "Apply Prices";
					tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ApplyButton, "visible");
				
					break;
			}
			
			// Click the Apply/Reject Button 
			reporterHelper.startTimer(timerName);
			tempWebElement.click();

			// Sync
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ApplyButton, "visible");

			
			// Stop Timer
			reporterHelper.stopTimer(timerName, pricingWidgetTitle);
			
			// Check for error message in Pricing widget
			if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_AnyErrorMessage)) {
				tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_AnyErrorMessage, "present");

				String errorText = tempWebElement.getText();
				reporterHelper.log("errorText.indexOf(\"\\n\"):" + errorText.indexOf("\n"));
				reporterHelper.log("errorText.indexOf(\"\\r\"):" + errorText.indexOf("\r"));

				errorText = errorText.replaceAll("\r", "").replaceAll("\n", "");
				
				reporterHelper.error("\tPRICING FAILED: " + errorText);
				reporterHelper.startTimer("PREVIOUS PRICING FAILED: " + errorText);
				reporterHelper.stopTimer("PREVIOUS PRICING FAILED: " + errorText, pricingWidgetTitle);
				reporterHelper.takeScreenshot(driver, "Pricing-FAILED_" + loopCounter);

				// refresh the pricing widget to overcome refresh error message
				reporterHelper.log("Clicking refresh in Pricing Widget in 1 second"); BrowserHelper.customSleep(1 * 1000);
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ReloadWidgetIcon, "visible").click();
				
				// sync on pricing widget containing some product price input text fields
				reporterHelper.log("Sync on pricing widget containing some product price input text fields");
				BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ProposedPrices_Inputs, "visible");
				BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Sync, "visible");
			
				BrowserHelper.customSleep(5 * 1000);
			}
			
			// if no error then treat as successful site processed
			else {
				sitesProcessedCount++;
			
				if(sitesProcessedCount == Integer.parseInt(Utils.getProperty("MAX_NUMBER_OF_SITES_TO_REVIEW"))) {
					reporterHelper.log("Target number of sites (" + sitesProcessedCount + ") have been processed, as defined by config file - MAX_NUMBER_OF_SITES_TO_REVIEW");
					break;
				}
			}
			
			reporterHelper.log("Site number " + sitesProcessedCount + " has been processed successfully");
			
			reporterHelper.takeScreenshot(driver, "Pricing-After_Process_Site_" + loopCounter);
			
		}
		
		BrowserHelper.checkForConsoleErrors();
	}	

	@Then("^in prices widget sites for review are processed$")
	public void inPricesWidgetSitesForReviewAreProcessed() throws Throwable {

		 
		browserHelper.messageBox("Prepare SFR's then click OK");

		if (Boolean.parseBoolean(Utils.getProperty("MULTIPRICING_APPLY_FREEZE_BUG_WORKAROUND"))) {
			reporterHelper.log("Closing Multi-Pricing widget as MULTIPRICING_APPLY_FREEZE_BUG_WORKAROUND is set to true in confg.");
			if(BrowserHelper.isElementDisplayed(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_CloseWidgetIcon)) {
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_CloseWidgetIcon, "present").click();
			}
				
			driver.navigate().refresh();

			// Sleep for 30 seconds to allow browser to refresh and all widgets to load and populate 
			// otherwise widget titles may not yet be populated before syncing widgets
			reporterHelper.log("Waiting for 30 seconds after doing a refresh of browser to workaround MP issue");
			BrowserHelper.customSleep(30 * 1000);
			
			kalibrateHelper.syncAllPresentWidgets();
			
			searchWidgetFilterApplied("Sites for review", "<checked>");
			
		}
		
		
		reporterHelper.takeScreenshot(driver, "Pricing-Before_Clicking_First_SFR");
		
		reporterHelper.log("in inPricesWidgetSitesForReviewAreProcessed. Clicking Search widget results top item in 5 seconds...");BrowserHelper.customSleep(5 * 1000);
		
		
		
		// Get element collection for the site rows in Search Widget
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_SearchResults_RowItems, "present");
		
		// Click the first site to populate site details in linked Pricing widget
		elements.get(0).click();
		reporterHelper.log("top item clicked.  Waiting 5 seconds...");BrowserHelper.customSleep(5 * 1000);
		reporterHelper.takeScreenshot(driver, "Pricing-After_Clicking_First_SFR");

		elements.get(0).click();
		reporterHelper.log("top item clicked.  Waiting 5 seconds...");BrowserHelper.customSleep(5 * 1000);
		reporterHelper.takeScreenshot(driver, "Pricing-After_Clicking_First_SFR_Second_Time");

		kalibrateHelper.syncAllPresentWidgets();
		
		// Sync on RunRate being displayed in Pricing widget
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_RunRate, "present");
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Sync, "present");
	
		
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
		String[] numberOfSFRMessageParts = tempWebElement.getText().split(" ");
		
		int actualSitesForReviewCount = Integer.parseInt(numberOfSFRMessageParts[7]);
		
		reporterHelper.log("actualSitesForReviewCount: " + actualSitesForReviewCount);
		
		String timerName = "";

		if (Boolean.parseBoolean(Utils.getProperty("SYNC_PRICING_AGENTS"))) 
			browserHelper.messageBox("Click to start pricing");
		
		
		pricingWidgetTitle = "";
		
		int sitesProcessedCount = 0;
		
		for(int loopCounter = 0; loopCounter < actualSitesForReviewCount; loopCounter++) {
			
			// sync on pricing widget containing some product price input text fields
			reporterHelper.log("Sync on pricing widget containing some product price input text fields");
			BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ProposedPrices_Inputs, "visible");
			BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Sync, "visible");

			
			// Get the site name/details and store in pricingWidgetTitle
			pricingWidgetTitle = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Title, "present").getText();
			reporterHelper.log("\n\nProcessing site: " + pricingWidgetTitle);
			
			reporterHelper.takeScreenshot(driver, "Pricing-Before_Process_Site_" + loopCounter);
			
			// For every 4 SFR's, Apply two, Reject one and Apply one with prices Adjusted
			switch(loopCounter%4) {
				
				case 0:
					
					timerName = "Reject Prices";
					tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_RejectButton, "visible");

					break;
					
				case 2:
					
					timerName = "Apply Prices Adjusted";
					tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ApplyButton, "visible");
					
					// Adjust prices on each product, clicking either up or down one, chosen at random
					List<WebElement> productInputElements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ProposedPrices_Inputs, "present");
					for (int j = 0; j < productInputElements.size(); j++) {
						
						if(new Random().nextBoolean()) {
							reporterHelper.log("Adjusting price UP by one click on product number: " + j); //BrowserHelper.customSleep(1 * 1000);
							productInputElements.get(j).findElement(By.xpath(".//img[@class='input-arrow-up']")).click();
						} else {
							reporterHelper.log("Adjusting price DOWN by one click on product number: " + j);// BrowserHelper.customSleep(1 * 1000);
							productInputElements.get(j).findElement(By.xpath(".//img[@class='input-arrow-down']")).click();
						}
							
					}
					
					break;

				default:

					timerName = "Apply Prices";
					tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ApplyButton, "visible");
				
					break;
			}
			
			// Click the Apply/Reject Button 
			reporterHelper.startTimer(timerName);
			tempWebElement.click();

			// Sync
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ApplyButton, "visible");

			
			// Stop Timer
			reporterHelper.stopTimer(timerName, pricingWidgetTitle);
			
			// Check for error message in Pricing widget
			if(BrowserHelper.isElementPresent(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_AnyErrorMessage)) {
				tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_AnyErrorMessage, "present");

				String errorText = tempWebElement.getText();
				reporterHelper.log("errorText.indexOf(\"\\n\"):" + errorText.indexOf("\n"));
				reporterHelper.log("errorText.indexOf(\"\\r\"):" + errorText.indexOf("\r"));

				errorText = errorText.replaceAll("\r", "").replaceAll("\n", "");
				
				reporterHelper.error("\tPRICING FAILED: " + errorText);
				reporterHelper.startTimer("PREVIOUS PRICING FAILED: " + errorText);
				reporterHelper.stopTimer("PREVIOUS PRICING FAILED: " + errorText, pricingWidgetTitle);
				reporterHelper.takeScreenshot(driver, "Pricing-FAILED_" + loopCounter);

				// refresh the pricing widget to overcome refresh error message
				reporterHelper.log("Clicking refresh in Pricing Widget in 1 second"); BrowserHelper.customSleep(1 * 1000);
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ReloadWidgetIcon, "visible").click();
				
				// sync on pricing widget containing some product price input text fields
				reporterHelper.log("Sync on pricing widget containing some product price input text fields");
				BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ProposedPrices_Inputs, "visible");
				BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Sync, "visible");
			
				BrowserHelper.customSleep(5 * 1000);
			}
			
			// if no error then treat as successful site processed
			else {
				sitesProcessedCount++;
			
				if(sitesProcessedCount == Integer.parseInt(Utils.getProperty("MAX_NUMBER_OF_SITES_TO_REVIEW"))) {
					reporterHelper.log("Target number of sites (" + sitesProcessedCount + ") have been processed, as defined by config file - MAX_NUMBER_OF_SITES_TO_REVIEW");
					break;
				}
			}
			
			reporterHelper.log("Site number " + sitesProcessedCount + " has been processed successfully");
			
			reporterHelper.takeScreenshot(driver, "Pricing-After_Process_Site_" + loopCounter);
			
		}
		
		BrowserHelper.checkForConsoleErrors();
	}	
	
	private void configurePricingWidget(String configIdentifier) {
		
		reporterHelper.log("\n\nCONFIGURING THE PRICING WIDGET");

		String desiredPricingItems = Utils.getProperty(configIdentifier);
		List<String> desiredPricingItemsList = Arrays.asList(desiredPricingItems.split("\\s*,\\s*"));

		
		// click on spanner icon
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ConfigurationIcon, "clickable").click();
		
        List<WebElement> listElements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Configuration_PricingItems, "present");
		reporterHelper.log("\tNumber of Pricing Configuration items found: " + listElements.size());

		reporterHelper.takeScreenshot(driver, "Pricing-Before_Configure_Widget");
		
		for (int j = 0; j < listElements.size(); j++) {
			
			tempWebElement = listElements.get(j);
			String tempString = tempWebElement.findElement(By.xpath(".//h4")).getText();
			
			reporterHelper.log("\tPricing Configuration item number " + (j+1) + " has the title: " + tempString);
			
			boolean itemCurrentlySelected = tempWebElement.getAttribute("class").contains("selected");
			
			if(desiredPricingItemsList.contains(tempString)) {
				reporterHelper.log("\tThis configuration item is desired, setting appropriately...");
				if(!itemCurrentlySelected)
					tempWebElement.click();
			} else {
				reporterHelper.log("\tThis configuration item is NOT desired, setting appropriately...");
				if(itemCurrentlySelected)
					tempWebElement.click();
			}
		}
		
		reporterHelper.takeScreenshot(driver, "Pricing-After_Configure_Widget");
		
		// click on spanner icon
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ConfigurationIcon, "clickable").click();
		
	}
	
	private void configurePricingWidgetDragMethod(String configIdentifier) {
		
		reporterHelper.log("\n\nCONFIGURING THE PRICING WIDGET - DRAG METHOD");

		
		
		String desiredPricingItems = Utils.getProperty(configIdentifier);
		List<String> desiredPricingItemsList = Arrays.asList(desiredPricingItems.split("\\s*,\\s*"));

		
		// click on spanner icon
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ConfigurationIcon, "clickable").click();
		
//		browserHelper.messageBox("Click to configure pricing widget");
		
        List<WebElement> listElements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Configuration_PricingItems, "present");
		reporterHelper.log("\tNumber of Pricing Configuration items found: " + listElements.size());
		
		reporterHelper.takeScreenshot(driver, "Pricing-Before_Configure_Widget");
		
		for (int j = 0; j < listElements.size(); j++) {
			
			tempWebElement = listElements.get(j);
			String tempString = tempWebElement.getText();
			
			// remove the selected item count prefix if present
			if(tempString.contains(". "))
				tempString = tempString.split("\\. ")[1];
			
			
			boolean itemCurrentlySelected = !tempWebElement.findElement(By.xpath("./span")).getAttribute("class").contains("ng-hide");
			
			WebElement selectPricingItemsHeader = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Configuration_SelectedItemsHeader, "present");
			WebElement availablePricingItemsHeader = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_Configuration_AvailableItemsHeader, "present");
			
			Actions builder = new Actions(driver);
			Action dragAndDrop = null;
			
			reporterHelper.log("\tPricing Configuration item number " + (j+1) + " has the title: " + tempString);
			
			if(desiredPricingItemsList.contains(tempString)) {
				reporterHelper.log("\tThis configuration item is desired, setting appropriately...");
				
				if(!itemCurrentlySelected) {
					
					BrowserHelper.customSleep(500);
					
					reporterHelper.log("\tIt is not currently selected so dragging to selected pricing items header...");
					dragAndDrop = builder.clickAndHold(tempWebElement)
							.moveToElement(availablePricingItemsHeader)
							.moveToElement(selectPricingItemsHeader)
							.moveByOffset(0, -100)
							.release()
							.build();
					dragAndDrop.perform();
				}
					
			} else {
				reporterHelper.log("\tThis configuration item is NOT desired, setting appropriately...");

				if(itemCurrentlySelected) {
					
					BrowserHelper.customSleep(500);
					
					reporterHelper.log("\tIt is currently selected so dragging to available pricing items header...");
					dragAndDrop = builder.clickAndHold(tempWebElement)
							.moveToElement(availablePricingItemsHeader)
							.moveByOffset(0, 100)
							.release()
							.build();
					dragAndDrop.perform();
				}
			}
		}
		
		// Need following to avoid issue where last item dragged doesn't drop correctly in time
		BrowserHelper.customSleep(1 * 1000);
		
		reporterHelper.takeScreenshot(driver, "After-Before_Configure_Widget");
		
		// click on spanner icon
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_pricingWidget_ConfigurationIcon, "clickable").click();
		
	}	


	@When("^Search initiated with a location filter$")
	public void search_initiated_with_a_location_filter() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}	

	@Then("^a full set of site results will be displayed$")
	public void aFullSsetOfSiteResultsWillBeDisplayed() {

		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_ResultsCountText, "visible");
		String actualSearchResultsMessage = tempWebElement.getText();
		
		reporterHelper.log("\nSearch results string: " + actualSearchResultsMessage);
		
		String[] actualResultsMessageParts = actualSearchResultsMessage.split(" ");
		int actualSearchResultsCountInt = Integer.parseInt(actualResultsMessageParts[5]);
        
        reporterHelper.takeScreenshot(driver, "Search-Full_Set_Sites_Result");
        reporterHelper.log("Number of Search results: " + actualSearchResultsCountInt);

  
	}
	
	
	@When("^a new 'Increase/Decrease' GPC is created for all sites$")
	public void aNewIncreaseDecreaseGPCIsCreatedForAllSites() throws Throwable {
		
		// Click Add GPC Button
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_AddGPCButton, "visible").click();
		
		// Click the correct GPC type button
		// TODO - CHange to a Switch and parameterise type
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_IncreaseDecreaseButton, "visible").click();

		// Click Search Icon to display embedded search
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_SearchIcon, "visible").click();
		
		// Click Search Button in embedded search
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_EmbeddedSearchSearchButtonNotDisabled, "visible");
		reporterHelper.startTimer("Search for all sites in Market Pricing Embedded Search");
		tempWebElement.click();

		// Sync On Search Button becoming enabled again (indicating Search has finished)
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_EmbeddedSearchSearchButtonNotDisabled, "visible");
		reporterHelper.stopTimer("Search for all sites in Market Pricing Embedded Search");

		// Click Search Icon to hide embedded search
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_SearchIcon, "visible").click();

		
		// Set Times
		
			// Get the default current from the first time field
			String durationFromTimeCurrent = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_DurationFromTime, "visible").getAttribute("Value");
			reporterHelper.log("Curernt time captured from Duration From Time field: " + durationFromTimeCurrent);
	
			DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm a");
			LocalTime time = formatter.parseLocalTime(durationFromTimeCurrent);
	
			String tempNewTime = "";
			// set duration from time
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_DurationFromTime, "visible");
			tempWebElement.clear();
			tempNewTime = formatter.print(time.plusMinutes(5));
			tempWebElement.sendKeys(tempNewTime.substring(0, tempNewTime.length() - 1));
			
			// set duration to time
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_DurationToTime, "visible");
			tempWebElement.clear();
			tempNewTime = formatter.print(time.plusMinutes(15));
			tempWebElement.sendKeys(tempNewTime.substring(0, tempNewTime.length() - 1));
		
			// set process at time
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_ProcessAtTime, "visible");
			tempWebElement.clear();
			tempNewTime = formatter.print(time.plusMinutes(3));
			tempWebElement.sendKeys(tempNewTime.substring(0, tempNewTime.length() - 1));
	
			// set export at time
			tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_ExportAtTime, "visible");
			tempWebElement.clear();
			tempNewTime = formatter.print(time.plusMinutes(4));
			tempWebElement.sendKeys(tempNewTime.substring(0, tempNewTime.length() - 1));

		// Set Values
			
	        List<WebElement> listElements = BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_ProductsTableAdjustmentValueCells, "present");
			reporterHelper.log("\tNumber of items found: " + listElements.size());
			
			for (int j = 0; j < listElements.size(); j++) {
				tempWebElement = listElements.get(j);
				tempWebElement.sendKeys("-0.01");
			}
			
		// Click Create GPC
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_CreateGPCButton, "visible");
		reporterHelper.startTimer("Click Create GPC");
		tempWebElement.click();
		
		// Wait for success notification
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_SuccessNotificationDisplayed, "visible");
		reporterHelper.stopTimer("Click Create GPC");
		
		// Capture text from success notification
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_SuccessNotification, "visible");
		String newGPCSummaryInformaiton = tempWebElement.getText();
		reporterHelper.log("newGPCSummaryInformaiton: " + newGPCSummaryInformaiton);
		
		reporterHelper.stopTimer("Click Create GPC", newGPCSummaryInformaiton);
	}

	@Then("^the new GPC will be present in the Active GPC's section$")
	public void theNewGPCWillBePresentInTheActiveGPCSSection() throws Throwable {
		// Capture Summary information from First Active GPC
		tempWebElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_marketPricingWidget_ActiveGeneralPriceChanges_FirstActiveGPC, "visible");
		reporterHelper.log("Created GPC Summary;");
		reporterHelper.log("");
		reporterHelper.log(tempWebElement.getText());

	}
	
	
	
	// *** Start of Gayathri's Methods ***
	
	// 
	//G3
		@When("^Pull \"([^\"]*)\" widget to \"([^\"]*)\"$")
		public void pullWidgetTo(String arg1, String arg2) throws Throwable {
			  // Write code here that turns the phrase above into concrete actions
					WebElement configWidgetsMenu = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon,"present");
					configWidgetsMenu.click();
					browserHelper.customSleep(1000);

					WebElement selectedWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon,"present");
					WebElement container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainers,"present");
				       // Create Actions instance and define behaviour
				       Actions builder;
				       Action dragAndDrop;
				       builder = new Actions(driver);
				       
				       
				switch (arg1)
				{
				case "Site Manager": 
					
				{
			       WebElement managementWidgetMenu = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ManagementWidgetIcon,"present");
			       managementWidgetMenu.click();
			       reporterHelper.log("Management widgets clicked");
			       browserHelper.customSleep(3000);
			        selectedWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_SiteManagerWidgetIcon,"present");
			       reporterHelper.log("Site Manager widget identified");
			       break;

				}
				
				case "Data Manager": 
					
				{
			       WebElement managementWidgetMenu = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ManagementWidgetIcon,"present");
			       managementWidgetMenu.click();
			       reporterHelper.log("Management widgets clicked");
			       browserHelper.customSleep(3000);
			        selectedWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_DataManagerWidgetIcon,"present");
			       reporterHelper.log("Data Manager widget identified");
			       break;

				}
				
				case "Search":
					
				{
			       WebElement toolsWidgetMenu = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ToolsWidgetIcon,"present");
			       toolsWidgetMenu.click();
			       reporterHelper.log("Tools widgets clicked");
			       browserHelper.customSleep(3000);
			        selectedWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_SearchWidgetIcon,"present");
			       reporterHelper.log("Search widget identified");
			       break;
				}	
				case "Administrator":
					
				{
			       WebElement toolsWidgetMenu = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ToolsWidgetIcon,"present");
			       toolsWidgetMenu.click();
			       reporterHelper.log("Tools widgets clicked");
			       browserHelper.customSleep(3000);
			        selectedWidget = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_AdministratorWidgetIcon,"present");
			       reporterHelper.log("Administrator widget identified");
			       break;
				}	
				}
				
				switch (arg2)
				{
				case "Container1":
					
				{
				 container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer1,"present");
			       reporterHelper.log("Container3 identified");
			       break;   
				}
				
				case "Container2":
					
				{
				 container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer2,"present");
			       reporterHelper.log("Container2 identified");
			       break;

			     
				}
				case "Container3":
					
				{
				 container = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer3,"present");
			       reporterHelper.log("Container3 identified");
			       break;   
				}
				
				}   

			       dragAndDrop = builder.clickAndHold(selectedWidget)
			       .moveToElement(container)
			       .release(container)	         
			       .build();
			       
			       // Perform action
			       dragAndDrop.perform();
					configWidgetsMenu.click();

			       reporterHelper.log(arg1 + "added to" + arg2 );
					browserHelper.customSleep(3000);
		}	

		
		@And("^User navigates to \"([^\"]*)\" workspace$")
		public void userNavigatesToWorkspace(String arg1) throws Throwable {
		 
			// Click workspace menu to expand
	        WebElement workspaceMenuElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader));
	        workspaceMenuElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuHeader));
	        workspaceMenuElement.click();
	        reporterHelper.log("Workspace menu Expanded");

      reporterHelper.log("Workspace menu LIST Expanded");

	        List <WebElement> workspaceMenuList =  BrowserHelper.syncOnElements(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuList,"present");
	        System.out.println(workspaceMenuList.size());
			reporterHelper.log("\tNumber of Workspaces Menu identified: " + workspaceMenuList.size());
			int menuItem = workspaceMenuList.size();
//			Iterator itr = workspaceMenuList.iterator();
			//WebElement tempwebelement = workspaceMenuList.get(0);
			//reporterHelper.log("\n\t First Menu item " + tempwebelement.getText());
			for (int i = 0 ; i < menuItem;i++)
			{
				WebElement tempwebelement = workspaceMenuList.get(i);
				reporterHelper.log("\n\t Menu item " + tempwebelement.getText());
//				if(tempwebelement.getText().equalsIgnoreCase(arg1))
				if(tempwebelement.getText().contains(arg1))

				{
					
					reporterHelper.log("\n\t Menu item " + tempwebelement.getText());
					tempwebelement.click();
					reporterHelper.log("\n\t " + arg1 +  "Workspace clicked");
				}
			}
			
	
			{
					reporterHelper.log(arg1 + " Workspace is not available");
					reporterHelper.log("Please create workspace for " + arg1);
					reporterHelper.log("Calling Function ");

				       workspaceMenuElement = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WorkspaceMenuExpanded, "clickable");
				        workspaceMenuElement.click();
						reporterHelper.log("Calling Function ");

					userCreatesANewTripleColumnWorkspaceNamed(arg1);
					reporterHelper.log("Function called ");

					
				}
			//}
	       

		//	browserHelper.customSleep(1000);
	  		WebElement workspaceContainer2 = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(kalibrateLocatorLibrary.kalibrate_main_WorkspaceContainer2));
	  		workspaceContainer2.click();
	        reporterHelper.log("Container 2  selected in workspace" + arg1);


		}		
		
		
		@When("^Close \"([^\"]*)\" widget$")
		public void closeWidget(String arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon,"present");
			
			switch (arg1)
			{
			case "Search": 
				
			{
		      BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Sync,"present");
		        WebElement SearchClose  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"present");
		        SearchClose.click();
		        reporterHelper.log(arg1 + "Widget Closed");
		       break;

			}
					
			case "Data Manager": 
				
			{
		      BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_sync,"present");
		        WebElement SearchClose  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_dataManagerWidget_CloseWidgetIcon,"present");
		        SearchClose.click();
		        reporterHelper.log(arg1 + "Widget Closed");
		       break;

			}
					
			
		case "Site Manager": 
				
			{
		      BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_Sync,"present");
		        WebElement SearchClose  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_Site_Manager_CloseWidgetIcon,"present");
		        SearchClose.click();
		        reporterHelper.log(arg1 + "Widget Closed");
		       break;

			}		
			
			
			
		case "MultiPricing": 
			
		{
	      BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync,"present");
	        WebElement SearchClose  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_CloseWidgetIcon,"present");
	        SearchClose.click();
	        reporterHelper.log(arg1 + "Widget Closed");
	       break;

		}	
		
		case "Pricing": 
			
		{
	      BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_Sync,"present");
	        WebElement SearchClose  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_multiPricingWidget_CloseWidgetIcon,"present");
	        SearchClose.click();
	        reporterHelper.log(arg1 + "Widget Closed");
	       break;

		}	
		}
		
		
		}
		
		
		@When("^Take Screenshot for feature$")
		public void takeScreenshotForFeature() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
			browserHelper.customSleep(3000);
			reporterHelper.takeScreenshot(driver, "WorkSpace_height");
			browserHelper.customSleep(1000);

		}

		
		
		@When("^Scroll \"([^\"]*)\" Kalibrate$")
		public void scrollKalibrate(String arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon,"present");

			BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_WidgetContainers,"present");
						
			
			switch (arg1)
			{
			case "Up": 
				
			{
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				browserHelper.customSleep(1000);

		       break;

			}
			
			
			case "Down": 
				
			{
				browserHelper.customSleep(3000);

				JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				browserHelper.customSleep(1000);

		       break;

			}
			
			case "Left": 
				
			{
		      BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Sync,"present");
		        WebElement SearchClose  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"present");
		        SearchClose.click();
		        reporterHelper.log(arg1 + "Widget Closed");
		       break;

			}
			
			case "Right": 
				
			{
		      BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_Sync,"present");
		        WebElement SearchClose  = BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_searchWidget_CloseWidgetIcon,"present");
		        SearchClose.click();
		        reporterHelper.log(arg1 + "Widget Closed");
		       break;

			}
			
			
			
			}
						
		}
		
		
		@Then("^a valid username \"([^\"]*)\" and a valid password \"([^\"]*)\" are entered$")
		public void aValidUsernameAndAValidPasswordAreEntered(String arg1, String arg2) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions

				
				BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_UsernameTextBox, "clickable").sendKeys(arg1);
		        reporterHelper.log("Username entered Successfully");

		        // Enter Valid Password
		        BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_login_PasswordTextBox, "clickable").sendKeys(arg2);
		        reporterHelper.log("Password entered Successfully");
		        
		        reporterHelper.takeScreenshot(driver, "Login-Populated");
			}
			
			
		
		
		
		
		
		
		
		
		// *** End of Gayathri's Methods ***			
}	
