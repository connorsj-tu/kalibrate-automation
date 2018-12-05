package com.common.page_objects;

import org.openqa.selenium.By;

public class KMobile_Generic_LocatorLibrary  {
	
	public final static By kMobile_settings_KalibrateHeaderImage = By.xpath("//android.view.View[3]/android.widget.Image");
//	public final static By kMobile_settings_ConnectStringTextboxAlt = By.xpath("//*[@id='ConnString']");
//	public final static By kMobile_settings_ConnectStringTextbox = By.className("android.widget.EditText");
	public final static By kMobile_settings_ConnectStringTextbox = By.id("ConnString");
//	public final static By kMobile_settings_AcceptPrivacyPolicyCheckbox = By.xpath("//*[contains(@content-desc, 'Accept privacy policy')]/following-sibling::android.widget.Button");
	public final static By kMobile_settings_AcceptPrivacyPolicyCheckbox = By.id("acceptPrivacy");
//	public final static By kMobile_settings_NextButton = By.xpath("//android.widget.Button[contains(@content-desc, 'Next')]");
	public final static By kMobile_settings_ProtocolSelector = By.id("ProtocolSelect");
	public final static By kMobile_settings_ProtocolSelectorItemHttps = By.xpath("//android.widget.CheckedTextView[1]");
	public final static By kMobile_settings_ProtocolSelectorItemHttp = By.xpath("//android.widget.CheckedTextView[2]");
	public final static By kMobile_settings_NextButton = By.id("ConnectionStringButton");
	
	
//	public final static By kMobile_login_LoginButton = By.xpath("//android.widget.Button[contains(@content-desc, 'Login')]");
	public final static By kMobile_login_LoginButton = By.id("loginSubmit");
//	public final static By kMobile_login_AcceptPrivacyPolicyCheckbox = By.xpath("//android.view.View[contains(@content-desc, 'Accept privacy policy')]");
	public final static By kMobile_login_AcceptPrivacyPolicyCheckbox = By.id("acceptPrivacy");
//	public final static By kMobile_login_UsernameTextbox = By.xpath("//android.widget.EditText[@class='android.widget.EditText'][position()=1]");
	public final static By kMobile_login_UsernameTextbox = By.id("Username");
//	public final static By kMobile_login_PasswordTextbox = By.xpath("//android.widget.EditText[@class='android.widget.EditText'][position()=2]");
	public final static By kMobile_login_PasswordTextbox = By.id("Password");
	
//	public final static By kMobile_map_GoogleMapsPegman = By.xpath("//android.view.View[contains(@content-desc, 'Street View Pegman Control')]");
//	public final static By kMobile_map_GoogleMapsPegman = By.xpath("//[contains(., 'Pegman')]");
	public final static By kMobile_map_GoogleMapsPegman = By.name("Street View Pegman Control");
	
//	public final static By kMobile_map_GoogleMapsZoomInButton = By.xpath("//android.widget.Button[contains(text(), 'Zoom in')]");
//	public final static By kMobile_map_GoogleMapsZoomOutButton = By.xpath("//android.widget.Button[contains(text(), 'Zoom out')]");
	public final static By kMobile_map_GoogleMapsZoomInButton = By.name("Zoom in");
	public final static By kMobile_map_GoogleMapsZoomOutButton = By.name("Zoom out");

	
	
	public final static By kMobile_map_BaiduMapsPanoramicView = By.xpath("//android.view.View[contains(@content-desc, '全景')]");
	public final static By kMobile_map_ErrorPopupHeading = By.xpath("//android.view.View[contains(@content-desc, 'Error')]");
	public final static By kMobile_map_ErrorPopupOkButton = By.xpath("//android.widget.Button[contains(@content-desc, 'OK')]");
	
//	public final static By kMobile_general_HamburgerMenuButton = By.xpath("//android.widget.Button[contains(@content-desc, 'Menu')]");
	public final static By kMobile_general_HamburgerMenuButton = By.id("menuButton");
	public final static By kMobile_general_SearchTextBox = By.id("searchbox");
	public final static By kMobile_general_SearchButton = By.id("searchImage");
	
	
	public final static By kMobile_menu_SiteList = By.xpath("//android.view.View[contains(@content-desc, 'Site List')]");
	public final static By kMobile_menu_ToDo = By.xpath("//android.view.View[contains(@content-desc, 'To Do')]");
	public final static By kMobile_menu_SurveyHistory = By.xpath("//android.view.View[contains(@content-desc, 'Survey History')]");
	public final static By kMobile_menu_UserSettings = By.xpath("//android.view.View[contains(@content-desc, 'User Settings')]");
	public final static By kMobile_menu_University = By.xpath("//android.view.View[contains(@content-desc, 'University')]");
	public final static By kMobile_menu_About = By.xpath("//android.view.View[contains(@content-desc, 'About')]");
	public final static By kMobile_menu_Logout = By.xpath("//android.view.View[contains(@content-desc, 'Logout')]");
	
	public final static By kMobile_siteList_KalibrateMobileContainer = By.xpath("//*[contains(@content-desc, 'Kalibrate Mobile')]");
	public final static By kMobile_siteList_ConnecticutTurnpikeLink = By.xpath("//android.view.View[contains(@content-desc, 'CONNECTICUT TURNPIKE')]");
	public final static By kMobile_siteList_FirstSiteInListLink = By.xpath("//*[@resource-id='siteListElement']/android.view.View[1]/android.view.View[1]");
	public final static By kMobile_siteList_CompetitorSite001Link = By.xpath("//android.view.View[contains(@content-desc, 'CompetitorSite001')]");
	
	public final static By kMobile_sitesSurvey_HeadingLink = By.xpath("//android.view.View[contains(@content-desc, 'Sites Survey')]");
	public final static By kMobile_sitesSurvey_ProductPricesCheckbox = By.xpath("//android.widget.Button[@resource-id='competiorProductCheck']");
	public final static By kMobile_sitesSurvey_ReportPricesButton = By.xpath("//android.widget.Button[contains(@content-desc, 'Report Prices')]");
	public final static By kMobile_sitesSurvey_BackButton = By.xpath("//android.widget.Button[contains(@content-desc, 'Back')]");
	public final static By kMobile_sitesSurvey_SuccessPopupHeading = By.xpath("//android.view.View[contains(@content-desc, 'Success')]");
	public final static By kMobile_sitesSurvey_SuccessPopupOkButton = By.xpath("//android.widget.Button[contains(@content-desc, 'OK')]");
	public final static By kMobile_sitesSurvey_WarningPopupHeading = By.xpath("//android.widget.Button[contains(@content-desc, 'Report without GPS')]");
	public final static By kMobile_sitesSurvey_WarningPopupReportWithoutGPSButton = By.xpath("//android.widget.Button[contains(@content-desc, 'Report without GPS')]");
	
	public final static By kMobile_toDo_Heading = By.xpath("//android.view.View[contains(@content-desc, 'Alerts')]");
	
	public final static By kMobile_surveyHistory_Heading = By.xpath("//android.view.View[contains(@content-desc, 'Surveys Awaiting Review')]");
	
	public final static By kMobile_userSettings_ConnectSringText = By.xpath("//android.view.View[contains(@content-desc, 'Connection string')]");
	public final static By kMobile_userSettings_SaveChangesButton = By.xpath("//android.widget.Button[contains(@content-desc, 'Save Changes')]");
	public final static By kMobile_userSettings_SettingsChangedPopupHeading = By.xpath("//android.view.View[contains(@content-desc, 'Settings changed')]");
	public final static By kMobile_userSettings_SettingsChangedPopupOkButton = By.xpath("//android.widget.Button[contains(@content-desc, 'OK')]");
	
	public final static By kMobile_university_CloseButton = By.xpath("//android.widget.ImageButton[contains(@content-desc, 'Close')]");
	
	public final static By kMobile_about_Heading = By.xpath("//android.view.View[contains(@content-desc, 'About Kalibrate Mobile Version')]");
	public final static By kMobile_about_BackButton = By.xpath("//android.widget.Button[contains(@content-desc, 'Back')]");

	
}
