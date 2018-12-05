package com.step_definitions.kalibrate;

import java.sql.ResultSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.common.helpers.BrowserHelper;
import com.common.helpers.DatabaseHelper;
import com.common.helpers.KalibrateHelper;
import com.common.helpers.ReporterHelper;
import com.common.helpers.Utils;
import com.common.page_objects.Kalibrate_Generic_LocatorLibrary;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Kalibrate_WidgetStepDefinitions {

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
	
	

	public class Widget {
		public String containerNumber;
		public String widgetName;
	}
	
	@Given("^the user is on the '(.*)' widgets category$")
	public void theUserIsOnThePricingCategory(String widgetCategory) {

		// Click configure widgets menu icon to collapse menu
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon, "visible").click();

		// Click Widget Category
		BrowserHelper.syncOnElement(driver, By.xpath("//div[@id='QuickWidgetMenu']//li[contains(text(), '" + widgetCategory + "')]"), "visible").click();

	}

	@When("^the user drags the following widgets into their respective containers:$")
	public void theUserDragsTheFollowingWidgetsOnToTheWorkspace(List<Widget> widgets) {
		for (Widget widget : widgets) {

			Actions builder;
			Action dragAndDrop;

			WebElement searchDraggableWidgetElement;
			WebElement widgetContainerElement;
			
			// Identify Widget icon
			searchDraggableWidgetElement = BrowserHelper.syncOnElement(driver, By.xpath("//div[@title='" + widget.widgetName + "']"), "visible");

			// Identify container to drag widget to
			widgetContainerElement = BrowserHelper.syncOnElement(driver, By.id("container" + widget.containerNumber + ""), "visible");
			
			// Create Actions instance and define behaviour
			builder = new Actions(driver);
			dragAndDrop = builder.clickAndHold(searchDraggableWidgetElement).moveToElement(widgetContainerElement)
					.release(widgetContainerElement).build();

			// Perform action
			dragAndDrop.perform();

		}
		
		// Click configure widgets menu icon to collapse menu
		BrowserHelper.syncOnElement(driver, kalibrateLocatorLibrary.kalibrate_main_ConfigureWidgetsIcon, "visible").click();
		
	}

	@Then("^the following widgets are displayed:$")
	public void marketPricingWidgetIsDisplayedOnTheWorkspace(List<Widget> widgets) {

		for (Widget widget : widgets) {

			String currentWidget = null; 
			
			
			if(widget.widgetName.equalsIgnoreCase("MAP") && Boolean.parseBoolean(Utils.getProperty("RELAX_MAP_VALIDATION"))) {
				reporterHelper.log("Skipping Validating that the Map widget has been created due to RELAX_MAP_VALIDATION in config file being set to true");
			} else {
				
				currentWidget = BrowserHelper
					.syncOnElement(driver, By.xpath("//h4[contains(text(), '" + widget.widgetName + "')]"), "clickable")
					.getText();


				if(currentWidget.equals(widget.widgetName))
					reporterHelper.log("\nCurrent Widget valid: " + currentWidget);
				else
					reporterHelper.customFailScript("Current widget is " + currentWidget + ", expecting " + widget.widgetName);
			}
			
		}

		reporterHelper.takeScreenshot(driver, "Pricing-Widgets_Added");
		
		kalibrateHelper.syncAllPresentWidgets();
		BrowserHelper.checkForConsoleErrors();
	}

}
