package com.step_definitions.KMobileWeb;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.common.helpers.BrowserHelper;
import com.common.helpers.ReporterHelper;
import com.common.helpers.Utils;
import com.common.page_objects.KMobileWeb_Generic_LocatorLibrary;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class KMobileWeb_StepDefinitions {
	
	private ReporterHelper reporterHelper = KMobileWeb_Hooks.reporterHelper;
	private KMobileWeb_Generic_LocatorLibrary kMobileWebLocatorLibrary = KMobileWeb_Hooks.kMobileWebLocatorLibrary;
	
	private String applicationName = KMobileWeb_Hooks.applicationName;

	
	private WebDriver driver = KMobileWeb_Hooks.driver;

	private WebElement tempWebElement;

	private String personaIdentifier;

	
	@Before
	public void initialise() {
		System.out.println("In KMobileWeb_StepDefinitions.initialise() method");
	}

	@Given("^the KMobile Web Login page is displayed$")
	public void userIsOnLoginPage() {
		
        BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_login_UsernameTextBox, "clickable");
        
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
		BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_login_UsernameTextBox, "clickable").sendKeys(System.getenv("COMPUTERNAME") + "_" + personaIdentifier + "_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER"));
        reporterHelper.log("Username entered Successfully");

        // Enter Valid Password
        BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_login_PasswordTextBox, "clickable").sendKeys(Utils.getProperty("DEFAULT_PASSWORD"));
        reporterHelper.log("Password entered Successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}	

	@When("^privacy policy is checked$")
	public void privacyPolicyIsChecked() throws Throwable {
		BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_login_AcceptPrivacyPolicyCheckbox, "clickable").click();
		reporterHelper.takeScreenshot(driver, "Login-Private_Policy_Checked");
	}
	
	@When("^the Login button is clicked$")
	public void theLoginButtonIsClicked() throws Throwable {
		BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_login_LoginButton, "clickable").click();
		reporterHelper.takeScreenshot(driver, "Login-Login_Button_Clicked");
	}
	
	@Then("^KMobile Web main page is displayed$")
	public void kmobileWebMainPageIsDisplayed() throws Throwable {
		BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_general_HamburgerMenuButton, "clickable");
		BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_map_GoogleMapsPegman, "clickable");
		reporterHelper.takeScreenshot(driver, "General-Main_Page_Displayed");
		
	}
	
	@When("^user selects '(.*)' from the menu$")
	public void userSelectsOptionFromTheMenu(String menuOption) {

		BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_general_HamburgerMenuButton, "clickable").click();
		
		String attributeNameString = "text";
        List<WebElement> menuItems = BrowserHelper.syncOnElements(driver, kMobileWebLocatorLibrary.kmobileweb_general_menuItems, "present");
		reporterHelper.log("\tNumber of menu items found: " + menuItems.size());
		reporterHelper.log("\tSearching for item that has at text of '" + attributeNameString + "' with a value of '" + menuOption + "'");

		reporterHelper.takeScreenshot(driver, "General-Menu_Items_Displayed");
		
		boolean success = false;
		for (int j = 0; j < menuItems.size(); j++) {
			
			tempWebElement = menuItems.get(j);
			String tempString = tempWebElement.getAttribute(attributeNameString).trim().replaceAll("&nbsp;", "");

			reporterHelper.log("\tText in menu item " + j + " = '" + tempString + "'");
			
			if(tempString.contains(menuOption.trim())) {
				reporterHelper.log("\tMenu item clicked at location: " + j);
				tempWebElement.click();
				success = true;
				break;
			}
		}

		if(!success) 
			reporterHelper.customFailScript("Menu item could not be found: " + menuOption);
	}
	
	// ###############
	
	@Then("^User is returned to Login Page$")
	public void userIsReturnedToLoginPage() throws Throwable {
		BrowserHelper.syncOnElement(driver, kMobileWebLocatorLibrary.kmobileweb_login_UsernameTextBox, "clickable");
		reporterHelper.takeScreenshot(driver, "Login-Returned_To_Login_Page");
	}

	@Then("^The browser can be closed$")
	public void theBrowserCanBeClosed() throws Throwable {
	    driver.quit();
	    reporterHelper.log("Broswer closed");
	}

	
	private void setPersonaIdentifier(String persona) {
		
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
		
	}
}	
