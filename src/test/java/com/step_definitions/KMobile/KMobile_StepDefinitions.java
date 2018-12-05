package com.step_definitions.KMobile;

import java.sql.ResultSet;

import org.openqa.selenium.WebElement;

import com.common.helpers.BrowserHelper;
import com.common.helpers.DatabaseHelper;
import com.common.helpers.DeviceHelper;
import com.common.helpers.KalibrateHelper;
import com.common.helpers.ReporterHelper;
import com.common.helpers.Utils;
import com.common.page_objects.KMobile_Generic_LocatorLibrary;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

public class KMobile_StepDefinitions {
	
	// Class variables
	private static boolean performSync = true;

	private ReporterHelper reporterHelper = KMobile_Hooks.reporterHelper;
	private BrowserHelper browserHelper = KMobile_Hooks.browserHelper;
	private DatabaseHelper databaseHelper = KMobile_Hooks.databaseHelper;
	private KalibrateHelper kalibrateHelper = KMobile_Hooks.kalibrateHelper;
	private DeviceHelper deviceHelper = KMobile_Hooks.deviceHelper;
	
	private KMobile_Generic_LocatorLibrary kMobileLocatorLibrary = KMobile_Hooks.kMobileLocatorLibrary;
	
	private ResultSet sqlDbResultSet = KMobile_Hooks.kalibrateDbResultSet;
	private String applicationName = KMobile_Hooks.applicationName;

	
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
	
	private AppiumDriver appiumDriver = KMobile_Hooks.appiumDriver;

	private WebElement tempWebElement;

	private String workspaceName;


//	private static final String APP_PATH = config.getProperty("AUTOMATION_RESOURCES_DIRECTORY_PATH") + "/Apps/KMobile/latest";
	private static String APP_PATH = null;
//	private static final String APP_PATH = "//MAN-TST-01/Automation/Automation/KalibrateTestSuite/Apps/KMobile/latest";

	private static String APK_NAME = "KalibrateMobile-debug.apk";
	
//	private static final String DEVICE_ID = config.getProperty("DEVICE_ID");
	private static String DEVICE_ID = null;
	
//	private static final String CONNECT_STRING = config.getProperty("CONNECT_STRING");
	private static String CONNECT_STRING = null;
	
	@Before
	public void initialise() {
		reporterHelper.log("In KMobile_StepDefinitions.initialise() method");
//		APP_PATH = config.getProperty("AUTOMATION_RESOURCES_DIRECTORY_PATH") + "/Apps/KMobile/latest";
		APP_PATH = "C:/Apps/KMobile/latest";
		APK_NAME = "KalibrateMobile-debug.apk";
		DEVICE_ID = Utils.getProperty("DEVICE_ID");
		CONNECT_STRING = Utils.getProperty("CONNECT_STRING");

	}

	@Given("^the KMobile Login page is displayed$")
	public void theKMobileLoginPageIsDisplayed() throws Throwable {

		reporterHelper.log("Navigating to Login Page...");
		
		populateAndNavigateSettingsPage();
		
	}

	
	@Given("^the Login button is tapped$")
	public void theLoginButtonIsTapped() throws Throwable {

		
		// click Login
		tempWebElement = BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_login_LoginButton, "clickable");
		tempWebElement.click();
		
	}

	@Given("^kMobile main page is displayed$")
	public void kMobileMainPageIsDisplayed() throws Throwable {

		deviceHelper.messageBox("JAMES");

		reporterHelper.log("appiumDriver.getContextHandles(): " + appiumDriver.getContextHandles());
		//  [NATIVE_APP, WEBVIEW_com.kssfuels.kMobile.appstore, WEBVIEW_chrome]

		deviceHelper.messageBox("JAMES");

		
		deviceHelper.syncLoggedIn();
		appiumDriver.context("WEBVIEW_com.kssfuels.kMobile.appstore");
		deviceHelper.syncMap();
		
	}

	@Given("^a valid username a valid password are entered and privacy policy checked$")
	public void aValidUsernameAValidPasswordAreEnteredAndPrivacyPolicyChecked() throws Throwable {

		reporterHelper.log("Entering User Credentials...");
		
		deviceHelper.messageBox("Change Protocol on device to 'http://' then click OK");
		
		// ########### START OF - LOGIN PAGE ###########
		
		tempWebElement = BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_login_AcceptPrivacyPolicyCheckbox, "visible");
		reporterHelper.takeScreenshot(appiumDriver, "Login_Page-Initial_State");
		
		// enter username
		tempWebElement = BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_login_UsernameTextbox, "visible");

		if (DEVICE_ID.equals("PHYSICAL_6.0.1_23api_1080x1920_445ppi")) {
			new TouchAction(appiumDriver).longPress(tempWebElement).release().perform();
			reporterHelper.log("\nSending username via keyboard sendKeys");
			appiumDriver.getKeyboard().sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME"));
		}
		else {
			tempWebElement.sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME"));
		}
		
		appiumDriver.hideKeyboard();
		

		// enter password
		tempWebElement = BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_login_PasswordTextbox, "visible");

		if (DEVICE_ID.equals("PHYSICAL_6.0.1_23api_1080x1920_445ppi")) {
			new TouchAction(appiumDriver).longPress(tempWebElement).release().perform();
			reporterHelper.log("\nSending password via keyboard sendKeys");
			appiumDriver.getKeyboard().sendKeys(Utils.getProperty(applicationName + "_CREDENTIALS_PASSWORD"));
		}
		else {
			tempWebElement.sendKeys(Utils.getProperty(applicationName + "_CREDENTIALS_PASSWORD"));
		}

		appiumDriver.hideKeyboard();

		reporterHelper.takeScreenshot(appiumDriver, "Login_Page-Populated");

		


		// ########### END OF - LOGIN PAGE ###########		
		
	}	 
	
	public void populateAndNavigateSettingsPage() throws Throwable {

		
		// ########### START OF - SETTINGS PAGE ###########
		
		WebElement tempWebElement;
		
		if (DEVICE_ID.equals("PHYSICAL_5.0")) {
			
			appiumDriver.context("WEBVIEW_com.kssfuels.kMobile.appstore");
			
			BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_settings_AcceptPrivacyPolicyCheckbox, "visible");
			
			appiumDriver.context("NATIVE_APP");
			reporterHelper.takeScreenshot(appiumDriver, "Settings-Initial_State");
			
			appiumDriver.context("WEBVIEW_com.kssfuels.kMobile.appstore");

			tempWebElement = BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_settings_ConnectStringTextbox, "visible");
			tempWebElement.sendKeys(CONNECT_STRING);
			
			appiumDriver.context("NATIVE_APP");

		} 
		else {
		
//				BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_settings_ConnectStringTextbox, "visible");
			
			reporterHelper.takeScreenshot(appiumDriver, "Settings-Initial_State");

		
			
			tempWebElement = BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_settings_AcceptPrivacyPolicyCheckbox, "clickable");
			tempWebElement.click();
			
//			// Select http protocol
//			BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_settings_ProtocolSelector, "visible").click();
////			appiumDriver.context("NATIVE_APP");
//			BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_settings_ProtocolSelectorItemHttp, "visible").click();
//			appiumDriver.context("WEBVIEW_com.kssfuels.kMobile.appstore");
//			
			
			// Select Enter connect string
			BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_settings_ConnectStringTextbox, "visible").sendKeys(CONNECT_STRING);
			
			appiumDriver.hideKeyboard();
		}				
		//######

		


		
		reporterHelper.takeScreenshot(appiumDriver, "Settings-Populated");	
	
		
		// @@@@@@@@@@@@
		
		// *** Credentials Page Navigation and Synchronisation ***

//		deviceHelper.messageBox("JAMES");
		
		boolean exitLoop = false;
		boolean passed = false;
		
		int loopCount = 0;
	
		reporterHelper.log("\nSynchronising with Credentials screen");
		
		
		
		tempWebElement = BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_settings_NextButton, "clickable");
		tempWebElement.click();
//		Thread.sleep(50000);
		
//		while(!exitLoop && loopCount < 10) {
//			
//			if(loopCount++ == 10) {
//				reporterHelper.log("Credentials screen sync failed after " + loopCount + " attempts");
//			}
//			
//			
//			
//			
//			if(BrowserHelper.isElementPresent(appiumDriver, kMobileLocatorLibrary.kMobile_settings_NextButton)) {
//				reporterHelper.log("Next button identified, clicking");
//				appiumDriver.findElement(kMobileLocatorLibrary.kMobile_settings_NextButton).click();
//				Thread.sleep(5000);
//			}
//			
//			if(BrowserHelper.isElementPresent(appiumDriver, kMobileLocatorLibrary.kMobile_login_LoginButton)) {
//				reporterHelper.log("Login button identified, credentials page sync complete");
//				passed = true;
//				exitLoop = true;
//				break;
//			}
//			
//			Thread.sleep(1000);
//		}
		
//		if(passed) {
//			reporterHelper.log("Login page sync complete");
//		} else
//			reporterHelper.customFailScript("Assertion failure exception caught, taking script error exit screenshot");
//		
	}
		
}

