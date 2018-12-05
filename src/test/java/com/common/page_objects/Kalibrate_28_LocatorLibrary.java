package com.common.page_objects;

import org.openqa.selenium.By;

public class Kalibrate_28_LocatorLibrary extends Kalibrate_Generic_LocatorLibrary {

	// KALIBRATE
	public Kalibrate_28_LocatorLibrary() {
		
		// Use this section to set version specific By locators by reinitialising the By locator that is defined in the parent class
		// make sure the By locator in parent class Kalibrate_Generic_LocatorLibrary is initialised as null to indicate it is initialised in child class 
		
		kalibrate_mapWidget_Sync = kalibrate_mapWidget_WhiteGoogleText_ClickToOpenInWindow_Image;
		
		kalibrate_priceRequestsWidget_RequestIdInputField = By.xpath("//price-request-widget//div[contains(@class, 'price-request-filter')]//input[@type = 'text'][@data-ng-model = 'filter.priceRequestId']");
		kalibrate_priceRequestsWidget_Sync = kalibrate_priceRequestsWidget_RequestIdInputField;

		kalibrate_pricingWidget_Title= By.xpath("//div[@name='pricing-widget']//div[contains(@class, 'widget-head')]//span[contains(@class, 'widget-head-pricing')]/span[3]");
		kalibrate_pricingWidget_Configuration_PricingItems = By.xpath("//div[@name='pricing-widget']//div[contains(@data-ng-repeat, 'item in vm.availablePricingItems')][contains(@class, 'pricing-group-option')]");

	}
}
