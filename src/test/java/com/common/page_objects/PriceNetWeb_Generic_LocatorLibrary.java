package com.common.page_objects;

import org.openqa.selenium.By;

public class PriceNetWeb_Generic_LocatorLibrary  {
	
	public final static By priceNetWeb_Security_Heading = By.xpath(".//body[@class='securityError']//table//h1[@id='mainTitle']");
	public final static By priceNetWeb_Security_ContinueLink = By.xpath(".//body[@class='securityError']//table//a[@id='overridelink']");
	
	public final static By priceNetWeb_login_Heading = By.xpath(".//form[@id='frmLogin']//table//td[@class='logoRow']//img[@src='images/PriceNetWeb.png']");
	public final static By priceNetWeb_login_LoginButton = By.xpath(".//form[@id='frmLogin']//table//input[@id='btnLogin']");
	public final static By priceNetWeb_login_CloseButton = By.xpath(".//form[@id='frmLogin']//table//input[@id='btnClose']");
	public final static By priceNetWeb_login_PasswordTextBox = By.xpath(".//form[@id='frmLogin']//table//input[@id='txtPassword']");
	public final static By priceNetWeb_login_UsernameTextBox = By.xpath(".//form[@id='frmLogin']//table//input[@id='txtUserName']");
//	public final static By priceNetWeb_login_LoginErrorMessage = By.id("ErrorLabel");
	
//	public final static By priceNetWeb_EntityPane_AdvancedSearchResultsDropDown = By.id("AdvancedSearchCombo");
//	public final static By priceNetWeb_EntityPane_FiltersDropDown = By.xpath(".//input[@id='EntityTypeCombo__Box']");
//	public final static By priceNetWeb_EntityPane_FiltersItems = By.xpath(".//div[@id='EntityTypeCombo__Panel']//div[contains(@class, 'comboItem')]");
//	public final static By priceNetWeb_EntityPane_SitesList = By.xpath(".//div[@id='EntityPanel']//div[contains(@class, 'Row')]/div[@class='et2']/span[@class='importCode']");
//	public final static By priceNetWeb_EntityPane_ItemsCount = By.id("CurrentDisplayLabel");
//	
//	public final static By priceNetWeb_MainPane_ShowPageDropDown = By.xpath(".//td[@id='MainPane']//td[@id='SelectorPane']//div[@id='ShowPageListPanel']//span[@id='ShowPageList']//input[@id='ShowPageList__Box']");
//	public final static By priceNetWeb_MainPane_ShowPageItems = By.xpath(".//td[@id='MainPane']//td[@id='SelectorPane']//div[@id='ShowPageListPanel']//span[@id='ShowPageList']//div[@class='comboPanel']//div[contains(@class, 'comboItem')]/span");
//	public final static By priceNetWeb_MainPane_SiteCode = By.xpath(".//td[@id='MainPane']//td[@id='SelectorPane']//div[@id='ShowMePanelPanel']//span[@class='ImportCode']");
//	public final static By priceNetWeb_MainPane_RefreshButton = By.xpath(".//td[@id='MainPane']//td[@id='SelectorPane']//input[@id='RefreshPane']");
//	
	public final static By priceNetWeb_ifmPage_WelcomeHeading = By.xpath(".//form[@id='frmWelcome']//div[@id='UpdatePanel1']//span[@id='WelcomeLabel']");
	
	public final static By priceNetWeb_Default_Tabs = By.xpath(".//form[@id='frmDefault']//table[@id='tblContainer']//table[@id='tblTabStrip']//td[@class='tabStripTabFill']");
	
//	public final static By priceNetWeb_ContentPane_SiteSurveyPage_SiteSurveyModeDropDown = By.xpath(".//form[@id='aspnetForm']//table[@class='ssBand top']//input[@id='ctl00_MainContentHolder_SiteSurveyMode__Box']");
	public final static By priceNetWeb_ifmPage_CompetitorPricesPage_TimeTextbox = By.xpath(".//form[@id='frmCompetitorPrices']//table[@id='tblContainer']//input[@id='txtEffectiveTime']");
	public final static By priceNetWeb_ifmPage_CompetitorPricesPage_DateTextbox = By.xpath(".//form[@id='frmCompetitorPrices']//table[@id='tblContainer']//input[@id='txtEffectiveDate']");
	
//	public final static By priceNetWeb_ContentPane_SiteSurveyPage_TableTitle = By.xpath(".//form[@id='aspnetForm']//span[@id='ctl00_MainContentHolder_SiteSurveyTitle']");
	public final static By priceNetWeb_ifmPage_CompetitorPricesPage_ProductCheckBoxes = By.xpath(".//form[@id='frmCompetitorPrices']//table[@id='tblContainer']//table[@id='tblProductPrices']//input[@type='checkbox']");
	public final static By priceNetWeb_ifmPage_CompetitorPricesPage_ProductPriceTextBoxes = By.xpath(".//form[@id='frmCompetitorPrices']//table[@id='tblContainer']//table[@id='tblProductPrices']//input[contains(@name, 'txtPrices')]");
	public final static By priceNetWeb_ifmPage_CompetitorPricesPage_OkButton = By.xpath(".//form[@id='frmCompetitorPrices']//table[@id='tblContainer']//input[@id='btnOk']");
//	
	public final static By priceNetWeb_ifmPage_CompetitorPricesPage_PricesSumbittedSuccessfully = By.xpath(".//form[@id='frmCompetitorPrices']//table[@id='tblContainer']//table[@id='tblProductPrices']//input[@name='txtPrices1'][not(@value)]");
	
	public final static By priceNetWeb_ifmPage_CompetitorPricesPage_Sync = priceNetWeb_ifmPage_CompetitorPricesPage_TimeTextbox;
	
}
