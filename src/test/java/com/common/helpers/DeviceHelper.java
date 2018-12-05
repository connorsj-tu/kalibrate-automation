package com.common.helpers;

import java.awt.Robot;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;

import com.common.hooks.Hooks;
import com.common.page_objects.KMobile_Generic_LocatorLibrary;
import com.step_definitions.KMobile.KMobile_Hooks;

import io.appium.java_client.AppiumDriver;


public class DeviceHelper {

	private static AppiumDriver appiumDriver;
	private static ReporterHelper reporterHelper;
	
	public Robot robot;
	
	private static int maxRetries;
	
	private static WebElement tempWebElement;
	private static String currentFrame;
	
//	private KMobile_Generic_LocatorLibrary kMobileLocatorLibrary = KMobile_Hooks.kMobileLocatorLibrary;
	
	public DeviceHelper(Hooks hooks) {
	
		
		appiumDriver = Hooks.appiumDriver;
		
		reporterHelper = Hooks.reporterHelper;
	
		maxRetries = Integer.parseInt(Utils.getProperty("MAX_SYNC_RETRIES"));
		
	}

	public void messageBox(String message) {
		
		if (Boolean.parseBoolean(Utils.getProperty("ALLOW_FRAMEWORK_ALERTS"))) {
		    Toolkit.getDefaultToolkit().beep();
		    JOptionPane optionPane = new JOptionPane(message, JOptionPane.WARNING_MESSAGE);
		    JDialog dialog = optionPane.createDialog("Warning!");
		    dialog.setAlwaysOnTop(true);
		    dialog.setVisible(true);
		} else
			reporterHelper.log("\n Suppressed Framework Alert '" + message + "' as ALLOW_FRAMEWORK_ALERTS is set to false in config");

	}

	public static void syncMap() {

		// ########### Sync with Google Map loaded ########### 
		
		// Will need to be updated if need to sync with Baidu Maps
		
		boolean exitLoop = false;
		boolean passed = false;
		int loopCount = 0;
		
		KMobile_Generic_LocatorLibrary kMobileLocatorLibrary = KMobile_Hooks.kMobileLocatorLibrary;
		
		reporterHelper.log("\nSynchronising with map screen");
		
		
		BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_map_GoogleMapsZoomInButton, "clickable");
//		BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_map_GoogleMapsPegman, "clickable");
		
		
		
		while(!exitLoop && loopCount < 60) {
			if(loopCount++ == 60) {
				reporterHelper.log("map sync failed after " + loopCount + " attempts");
			}
			
			// Check for Google Maps Pegman
			if(BrowserHelper.isElementPresent(appiumDriver, kMobileLocatorLibrary.kMobile_map_GoogleMapsPegman)) {
				reporterHelper.log("Google Maps Pegman identified, map sync complete");
				passed = true;
				exitLoop = true;
				break;
			}
			
			// Check for Baidu Maps Panoramic View Icon
			if(BrowserHelper.isElementPresent(appiumDriver, kMobileLocatorLibrary.kMobile_map_BaiduMapsPanoramicView)) {
				reporterHelper.log("Baidu Maps panoramic view icon identified, map sync complete");
				passed = true;
				exitLoop = true;
				break;
			}
			
			// Check for Error Popup
			if(BrowserHelper.isElementPresent(appiumDriver, kMobileLocatorLibrary.kMobile_map_ErrorPopupHeading)) {
				reporterHelper.log("Error popup identified, clicking OK");

				reporterHelper.takeScreenshot(appiumDriver, "Login_Page-Error_Popup");
				
				BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_map_ErrorPopupOkButton, "present").click();
				reporterHelper.log("Error popup cleared, map sync complete");
				
				// May need to add check here to make sure OK worked
				BrowserHelper.customSleep(1 * 1000);
				
				reporterHelper.takeScreenshot(appiumDriver, "Login_Page-Error_Popup_OK'd");
				
				passed = true;
				exitLoop = true;
				break;
			}

			BrowserHelper.customSleep(1 * 1000);
		}
		
		if(passed) {
			reporterHelper.log("Map present, map sync complete");
		} else 
			reporterHelper.customFailScript("Map was not displyed correctly");
		
	}
	
	public static void syncLoggedIn() throws InterruptedException {

		// ########### Sync with Google Map loaded ########### 
		
		// Will need to be updated if need to sync with Baidu Maps
		
		boolean exitLoop = false;
		boolean passed = false;
		int loopCount = 0;
		
		KMobile_Generic_LocatorLibrary kMobileLocatorLibrary = KMobile_Hooks.kMobileLocatorLibrary;
		
		reporterHelper.log("\nSynchronising with menu hamburger icon");
		
		BrowserHelper.syncOnElement(appiumDriver, kMobileLocatorLibrary.kMobile_general_HamburgerMenuButton, "clickable");

		reporterHelper.log("Menu Hamburger Icon identified, kMobile logged in sync complete");
		
	}
	public static void switchToContext(String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(appiumDriver);
        Map<String,String> params = new HashMap<>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }
	public static String getCurrentContextHandle() {         
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(appiumDriver);
        String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
        return context;
    }
	public static List<String> getContextHandles() {         
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(appiumDriver);
        List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
        return contexts;
    }
}
