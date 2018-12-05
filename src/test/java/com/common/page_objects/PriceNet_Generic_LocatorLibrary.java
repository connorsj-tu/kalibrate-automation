package com.common.page_objects;

import org.openqa.selenium.By;

public class PriceNet_Generic_LocatorLibrary  {
	
	public final static By priceNet_login_Heading = By.id("WelcomeLabel");
	public final static By priceNet_login_LoginButton = By.id("LoginButton");
	public final static By priceNet_login_PasswordTextBox = By.id("PasswordTextBox");
	public final static By priceNet_login_UsernameTextBox = By.id("UsernameTextBox");
	public final static By priceNet_login_LoginErrorMessage = By.id("ErrorLabel");
	
	public final static By priceNet_EntityPane_AdvancedSearchResultsDropDown = By.id("AdvancedSearchCombo");
	public final static By priceNet_EntityPane_FiltersDropDown = By.xpath(".//input[@id='EntityTypeCombo__Box']");
	public final static By priceNet_EntityPane_FiltersItems = By.xpath(".//div[@id='EntityTypeCombo__Panel']//div[contains(@class, 'comboItem')]");
	public final static By priceNet_EntityPane_SitesList = By.xpath(".//div[@id='EntityPanel']//div[contains(@class, 'Row')]/div[@class='et2']/span[@class='importCode']");
	public final static By priceNet_EntityPane_ItemsCount = By.id("CurrentDisplayLabel");
	
	public final static By priceNet_MainPane_ShowPageDropDown = By.xpath(".//td[@id='MainPane']//td[@id='SelectorPane']//div[@id='ShowPageListPanel']//span[@id='ShowPageList']//input[@id='ShowPageList__Box']");
	public final static By priceNet_MainPane_ShowPageItems = By.xpath(".//td[@id='MainPane']//td[@id='SelectorPane']//div[@id='ShowPageListPanel']//span[@id='ShowPageList']//div[@class='comboPanel']//div[contains(@class, 'comboItem')]/span");
	public final static By priceNet_MainPane_SiteCode = By.xpath(".//td[@id='MainPane']//td[@id='SelectorPane']//div[@id='ShowMePanelPanel']//span[@class='ImportCode']");
	public final static By priceNet_MainPane_RefreshButton = By.xpath(".//td[@id='MainPane']//td[@id='SelectorPane']//input[@id='RefreshPane']");
	
	public final static By priceNet_ContentPane_WelcomeHeading = By.id("WelcomeTitle");
	public final static By priceNet_ContentPane_SiteSurveyPage_SiteSurveyModeDropDown = By.xpath(".//form[@id='aspnetForm']//table[@class='ssBand top']//input[@id='ctl00_MainContentHolder_SiteSurveyMode__Box']");
	public final static By priceNet_ContentPane_SiteSurveyPage_TimeTextbox = By.xpath(".//form[@id='aspnetForm']//table[@class='ssBand top']//input[@id='ctl00_MainContentHolder_SiteSurveyDateTimeTextBox']");
	public final static By priceNet_ContentPane_SiteSurveyPage_TableTitle = By.xpath(".//form[@id='aspnetForm']//span[@id='ctl00_MainContentHolder_SiteSurveyTitle']");
	public final static By priceNet_ContentPane_SiteSurveyPage_ProductCheckBoxes = By.xpath(".//form[@id='aspnetForm']//table[@id='ctl00_MainContentHolder_SiteSurveyTable']//input[@type='checkbox']");
	public final static By priceNet_ContentPane_SiteSurveyPage_ProductPriceTextBoxes = By.xpath(".//form[@id='aspnetForm']//table[@id='ctl00_MainContentHolder_SiteSurveyTable']//input[contains(@class, 'ssPriceBox inputbox')]");
	public final static By priceNet_ContentPane_SiteSurveyPage_SaveButton = By.xpath(".//form[@id='aspnetForm']//div[@class='MainPageFooter']//input[@id='ctl00_ButtonContentHolder_SaveButton']");
	
	public final static By priceNet_ContentPane_SiteSurveyPage_Sync = priceNet_ContentPane_SiteSurveyPage_TableTitle;
		
	
	
}
