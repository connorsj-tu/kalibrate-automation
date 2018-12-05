package com.common.page_objects;

import org.openqa.selenium.By;

public class Kalibrate_241_LocatorLibrary extends Kalibrate_Generic_LocatorLibrary {

	// KALIBRATE
	public Kalibrate_241_LocatorLibrary() {
		
		// Use this section to set version specific By locators by reinitialising the By locator that is defined in the parent class
		// make sure the By locator in parent class Kalibrate_Generic_LocatorLibrary is initialised as null to indicate it is initialised in child class 
	
		kalibrate_mapWidget_Sync = kalibrate_mapWidget_GoogleMapsPegman;
		
		kalibrate_priceRequestsWidget_RequestIdInputField = By.xpath("//price-request-widget//div[contains(@class, 'price-request-list')]//input[@type = 'text'][@data-ng-model = 'priceRequestSeqId']");
		kalibrate_priceRequestsWidget_Sync = kalibrate_priceRequestsWidget_RequestIdInputField;

		kalibrate_pricingWidget_Title= By.xpath("//div[@name='pricing-widget']//div[contains(@class, 'widget-head')]//h4");
		kalibrate_pricingWidget_Configuration_PricingItems = By.xpath("//div[@name='pricing-widget']//div[contains(@data-ng-repeat, 'item in availableSinglePricingItems')]");
		
	}
}
