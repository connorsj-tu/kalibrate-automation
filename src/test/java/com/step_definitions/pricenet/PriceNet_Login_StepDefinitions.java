package com.step_definitions.pricenet;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.helpers.BrowserHelper;
import com.common.helpers.DatabaseHelper;
import com.common.helpers.ReporterHelper;
import com.common.helpers.Utils;
import com.common.page_objects.PriceNet_Generic_LocatorLibrary;
import com.step_definitions.kalibrate.Kalibrate_Hooks;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PriceNet_Login_StepDefinitions {
	
	private WebDriver driver = Kalibrate_Hooks.driver;

	private ReporterHelper reporterHelper = Kalibrate_Hooks.reporterHelper;
	private BrowserHelper browserHelper = Kalibrate_Hooks.browserHelper;
	private DatabaseHelper databaseHelper = Kalibrate_Hooks.databaseHelper;

	private String applicationName = Kalibrate_Hooks.applicationName;
	
	private WebElement tempWebElement;

	private boolean performSync = true;

	private PriceNet_Generic_LocatorLibrary priceNetLocatorLibrary = Kalibrate_Hooks.priceNetLocatorLibrary;

	
	private String currentPage;
	
	@When("^a site is selected from the sites list$")
	public void aSiteIsSelectedFromTheSitesList() throws Throwable {
		
		String targetSite = Utils.getProperty("PRICENET_PRICING_SCRIPT_OWN_SITE_ID");
		
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, priceNetLocatorLibrary.priceNet_EntityPane_SitesList, "EntityPane", "present");
		
		reporterHelper.log("\tNumber of Site list items identified: " + elements.size());
		
		String currentFilterItem = "";
		
		int titleWaitCount;
		boolean foundFilter = false;
		
		for (int i = 0; i < elements.size(); i++) {
			
			tempWebElement = elements.get(i);
			
			if(tempWebElement.getText().equals(targetSite)) {
				reporterHelper.log("\tClicking list item number " + (i + 1) + ": " + targetSite);
				tempWebElement.click();
				foundFilter = true;
				break;
			}
		}
		
		if(!foundFilter) {
			reporterHelper.customFailScript("Unable to find item: " + targetSite);
		}
	}

	@Then("^the selected site is displayed in the main pane$")
	public void theSelectedSiteIsDisplayedInTheMainPane() throws Throwable {
		
		String targetSite = Utils.getProperty("PRICENET_PRICING_SCRIPT_OWN_SITE_ID");
		
		tempWebElement = BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_MainPane_SiteCode, null, "clickable");
		
		if(tempWebElement.getText().equals(targetSite)) {
			reporterHelper.log("Site has been successfully displayed in the Main Pane: " + targetSite);
		} else {
			reporterHelper.customFailScript("Site has NOT been successfully displayed in the Main Pane: " + targetSite);
		}
		
	}

	@When("^'(.*)' is selected from the show page drop-down$")
	public void siteSurveyIsSelectedFromTheShowPageDropDown(String filterParam) throws Throwable {
		
		currentPage = filterParam;
		
		tempWebElement = BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_MainPane_ShowPageDropDown, null, "clickable");
		tempWebElement.click();
		
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, priceNetLocatorLibrary.priceNet_MainPane_ShowPageItems, null, "present");
		
		reporterHelper.log("\tNumber of list items identified: " + elements.size());
		
		String currentFilterItem = "";
		
		int titleWaitCount;
		boolean foundFilter = false;
		
		for (int i = 0; i < elements.size(); i++) {
			tempWebElement = elements.get(i);
			if(elements.get(i).getText().equalsIgnoreCase(filterParam)) {
				reporterHelper.log("\tClicking list item number " + (i + 1) + ": " + filterParam);
				tempWebElement.click();
				foundFilter = true;
				break;
			}
		}
		
		if(!foundFilter) {
			reporterHelper.customFailScript("Unable to find item: " + filterParam);
		}

	}

	@Then("^the selected page is displayed in the main pane$")
	public void theSelectedPageIsDisplayedInTheMainPane() throws Throwable {
		
		reporterHelper.log("Checking that '" + currentPage + "' page has finished loading");
		
		switch (currentPage) {
        case "Competitor Prices":
        	BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_Sync, "ContentPane", "visible");
            break;
        default:
            reporterHelper.customFailScript("Unknown Page name in method theSelectedPageIsDisplayedInTheMainPane(): " + currentPage);
		}
		reporterHelper.log("\t'" + currentPage + "' page sync success");
	}

	@Then("^competitor prices are submitted without change - in a loop for (.*) times$")
	public void competitorPricesAreSubmittedWithoutChangeInALoop(int numberOfIterations) throws Throwable {
		
		Random r = new Random();
		int lowestRandomNumber = 1;
		int highestRandomNumber = 10;
				
		int randomSeconds;
		
		String firstSurveyTime = "00:00";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    LocalTime surveyTime = LocalTime.parse(firstSurveyTime);
	    
	    ResultSet resultSet;
	    
	     
	    
		resultSet = databaseHelper.executeSQLStringWithResultSet("SELECT MAX (OwnPriceID) FROM [Speedway_PN].[dbo].[PNOwnPrice]");
		resultSet.next();
		String previousMaxOwnPriceID = resultSet.getString(1);
		System.out.println("previousMaxOwnPriceID: " + previousMaxOwnPriceID);
		
		resultSet = databaseHelper.executeSQLStringWithResultSet("SELECT MAX (CompetitorPriceID) FROM [Speedway_PN].[dbo].[PNCompetitorPrice]");
		resultSet.next();
		String previousMaxCompetitorPriceID = resultSet.getString(1);
		System.out.println("previousMaxCompetitorPriceID: " + previousMaxCompetitorPriceID);

		
		for (int i = 0; i < numberOfIterations; i++) {
			
			competitorPricesAreSubmittedWithoutChange(dateFormatter.format(surveyTime));
			surveyTime = surveyTime.plusMinutes(1);

			reporterHelper.log("\nCompleted Submitted Prices iteration number " + (i+1) + ". Number of iterations remaining: " + (numberOfIterations-(i+1)) );
				
//			// get random number between 
//			randomSeconds = r.nextInt(highestRandomNumber-lowestRandomNumber) + lowestRandomNumber;
//			
//			// Wait for 60 seconds 
//			BrowserHelper.customSleep((120 + randomSeconds) * 1000);
//			
//			// refresh the Site Survey page
//			aSiteIsSelectedFromTheSitesList();
//			browserHelper.syncOnElement(driver, LocatorLibrary.priceNet_MainPane_RefreshButton, null, "present").click();
		
//			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNCompetitorPrice] "
//					+ "Set Created = EffectiveTime, LastUpdated = EffectiveTime "
//					+ "WHERE CompetitorPriceID = "
//					+ "(SELECT MAX(CompetitorPriceID) FROM  [Speedway_PN].[dbo].[PNCompetitorPrice] WHERE Origin = 'auto1')");

			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNCompetitorPrice] "
					+ "Set Created = EffectiveTime, LastUpdated = EffectiveTime "
					+ "WHERE CompetitorPriceID > " + previousMaxCompetitorPriceID);

//			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNCompetitorPrice] "
//					+ "Set Created = '2017-06-19 00:01:00.000', EffectiveTime = '2017-06-19 00:01:00.000' "
//					+ "WHERE CompetitorPriceID > " + previousMaxCompetitorPriceID);


			
//			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNOwnPrice] "
//					+ "Set Created = EffectiveTime, LastUpdated = EffectiveTime "
//					+ "WHERE OwnPriceID = "
//					+ "(SELECT MAX(OwnPriceID) FROM  [Speedway_PN].[dbo].[PNOwnPrice] WHERE Origin = 'auto1')");
			
			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNOwnPrice] "
					+ "Set Created = EffectiveTime, LastUpdated = EffectiveTime "
					+ "WHERE OwnPriceID > " + previousMaxOwnPriceID);

			


//			resultSet = databaseHelper.executeSQLStringWithResultSet("SELECT * FROM [Speedway_PN].[dbo].[PNCompetitorPrice] WHERE CompetitorPriceID = (SELECT MAX(CompetitorPriceID) FROM  [Speedway_PN].[dbo].[PNCompetitorPrice] WHERE Origin = 'auto1')");
//			databaseHelper.printResultSet(resultSet);

			  			
		}
		
		
		
	}
	
	@Then("^competitor prices are submitted (with|without) changes? - in a loop for (.*) minutes$")
	public void competitorPricesAreSubmittedInALoopForXMinutes(String changesRequired, long loopTargetDurationMinutes) throws Throwable {
		
		String firstSurveyTime = "00:00";
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    LocalTime surveyTime = LocalTime.parse(firstSurveyTime);
		
//	    ResultSet resultSet;
//	    
//		resultSet = databaseHelper.executeSQLStringWithResultSet("SELECT MAX (OwnPriceID) FROM [Speedway_PN].[dbo].[PNOwnPrice]");
//		resultSet.next();
//		String previousMaxOwnPriceID = resultSet.getString(1);
//		System.out.println("previousMaxOwnPriceID: " + previousMaxOwnPriceID);
//		
//		resultSet = databaseHelper.executeSQLStringWithResultSet("SELECT MAX (CompetitorPriceID) FROM [Speedway_PN].[dbo].[PNCompetitorPrice]");
//		resultSet.next();
//		String previousMaxCompetitorPriceID = resultSet.getString(1);
//		System.out.println("previousMaxCompetitorPriceID: " + previousMaxCompetitorPriceID);

		boolean exitLoop = false;
		long loopElapsedMinutes;
		int loopCount = 0;
	    long loopStartTime = System.currentTimeMillis();

		while(!exitLoop) {
		
			loopElapsedMinutes = ((System.currentTimeMillis() - loopStartTime) / 1000) / 60;

			if(loopElapsedMinutes >= loopTargetDurationMinutes) {
				System.out.println("EXITING LOOP");
				exitLoop = true;
				break;
			} else {
//				System.out.println("\n\n\n*********************\nloopElapsedMinutes: " + loopElapsedMinutes);
//				System.out.println("loopTargetDurationMinutes: " + loopTargetDurationMinutes);
//				System.out.println("(loopElapsedMinutes >= loopTargetDurationMinutes): " + (loopElapsedMinutes >= loopTargetDurationMinutes));
			}
			
			if(changesRequired.equalsIgnoreCase("without")) 
				competitorPricesAreSubmittedWithoutChange(dateFormatter.format(surveyTime));
			else
				competitorPricesAreSubmittedWithChanges(dateFormatter.format(surveyTime));
			
			surveyTime = surveyTime.plusMinutes(1);

			reporterHelper.log("\nCompleted Submitted Prices iteration number " + loopCount++ + ". Number of minutes elapsed: " + loopElapsedMinutes + " Number of minutes remaining: " + (loopTargetDurationMinutes - loopElapsedMinutes));

//			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNCompetitorPrice] "
//					+ "Set Created = EffectiveTime, LastUpdated = EffectiveTime "
//					+ "WHERE CompetitorPriceID > " + previousMaxCompetitorPriceID);
//
//			
//			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNOwnPrice] "
//					+ "Set Created = EffectiveTime, LastUpdated = EffectiveTime "
//					+ "WHERE OwnPriceID > " + previousMaxOwnPriceID);

			  			
		}
		
	}
	
	@Then("^competitor prices are submitted without change$")
	public void competitorPricesAreSubmittedWithoutChange(String surveyTime) throws Throwable {
		
		reporterHelper.takeScreenshot(driver, "Site_Survey-Initial State");
		
		if(surveyTime != null) {
			reporterHelper.log("\nSetting survey time to: " + surveyTime);
			tempWebElement = BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_TimeTextbox, "ContentPane", "present");
			tempWebElement.clear();
			tempWebElement.sendKeys(surveyTime);
			tempWebElement.sendKeys(Keys.TAB);
			
			BrowserHelper.customSleep(1 * 1000);
			
//			theSelectedPageIsDisplayedInTheMainPane();
		}
		
		reporterHelper.log("\nIdentifying product checkboxes;");
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_ProductCheckBoxes, "ContentPane", "present");
		
		reporterHelper.log("\tNumber of product checkboxes identified: " + elements.size());
		
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).click();
		}

		reporterHelper.takeScreenshot(driver, "Site_Survey-Prices_Ready_To_Sumbit");
		
		// Click SAVE
		BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_SaveButton, "ContentPane", "present").click();
		
		// Sync on Site Survey Page once more to ensure submission complete
		BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_Sync, "ContentPane", "visible");
		
		reporterHelper.takeScreenshot(driver, "Site_Survey-Prices_Submitted");
		
		reporterHelper.log("\tSite Srvey form submitted successfully");
	}
	

	@Then("^competitor prices are submitted with changes$")
	public void competitorPricesAreSubmittedWithChanges(String surveyTime) throws Throwable {
//	public void competitorPricesAreSubmittedWithChanges() throws Throwable {
		
		reporterHelper.takeScreenshot(driver, "Site_Survey_Adjusted-Initial State");
		
		if(surveyTime != null) {
			reporterHelper.log("\nSetting survey time to: " + surveyTime);
			tempWebElement = BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_TimeTextbox, "ContentPane", "present");
			tempWebElement.clear();
			tempWebElement.sendKeys(surveyTime);
			tempWebElement.sendKeys(Keys.TAB);
			
			BrowserHelper.customSleep(1 * 1000);
			
			theSelectedPageIsDisplayedInTheMainPane();
		}
		
		reporterHelper.log("\nIdentifying product Text Boxes;");
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_ProductPriceTextBoxes, "ContentPane", "present");
		
		reporterHelper.log("\tNumber of product text boxes identified: " + elements.size());
		
		BigDecimal adjustmentValueBigDecimal  = new BigDecimal("0.1");
		BigDecimal currentPriceBigDecimal;
		BigDecimal newPriceBigDecimal;
		
		for (int i = 0; i < elements.size(); i++) {
			tempWebElement = elements.get(i);
			
			String currentProductPrice = tempWebElement.getAttribute("value");
			if(currentProductPrice.trim().equals("")) 
				currentProductPrice = "140.1";
			
			currentPriceBigDecimal  = new BigDecimal(currentProductPrice);

			if(new Random().nextBoolean())
				newPriceBigDecimal = currentPriceBigDecimal.add(adjustmentValueBigDecimal);
			else
				newPriceBigDecimal = currentPriceBigDecimal.subtract(adjustmentValueBigDecimal);
			
			tempWebElement.clear();
			tempWebElement.sendKeys(newPriceBigDecimal.toString());
		}

		reporterHelper.takeScreenshot(driver, "Site_Survey-Prices_Adjusted_Ready_To_Sumbit");
		
		// Click SAVE
		BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_SaveButton, "ContentPane", "present").click();
		
		// Sync on Site Survey Page once more to ensure submission complete
		BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_ContentPane_SiteSurveyPage_Sync, "ContentPane", "visible");
		
		reporterHelper.takeScreenshot(driver, "Site_Survey_Adjusted-Prices_Submitted");
		
		reporterHelper.log("\tSite Survey form submitted successfully");
	}
	
	@When("^'(.*)' filter is selected$")
	public void filterIsSelected(String filterParam) throws Throwable {
		
		// Click the Filters field to show filter options
		reporterHelper.log("\n\tClicking on filter drop-down to show filter options");
		tempWebElement = BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_EntityPane_FiltersDropDown, "EntityPane", "clickable");
		tempWebElement.click();
		
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, priceNetLocatorLibrary.priceNet_EntityPane_FiltersItems, "EntityPane", "present");
		
		reporterHelper.log("\tNumber of Filter list items identified: " + elements.size());
		
		String currentFilterItem = "";
		
		boolean foundFilter = false;
		
		for (int i = 0; i < elements.size(); i++) {
			tempWebElement = elements.get(i);
			if(tempWebElement.getAttribute("text").equalsIgnoreCase(filterParam)) {
				reporterHelper.log("\tClicking filter list item number " + (i + 1) + ": " + filterParam);
				tempWebElement.click();
				foundFilter = true;
				break;
			}
		}
		
		if(!foundFilter) {
			reporterHelper.customFailScript("Unable to find filter: " + filterParam);
		}
		
	}

	@Given("^the user is logged in and PriceNet main page is displayed$")
	public void theUserIsLoggedInAndPriceNetMainPageIsDisplayed() throws Throwable {
		userIsOnLoginPage();
		aValidUsernameAndAValidPasswordAreEntered();
		theLoginButtonIsClicked();
		priceNetMainPageIsDisplayed();
	}
	
	@Given("^the PriceNet Login page is displayed$")
	public void userIsOnLoginPage() {

        BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_login_UsernameTextBox, "clickable");
        
        reporterHelper.log("Login Page displayed successfully");
        reporterHelper.takeScreenshot(driver, "Login-Initial_State");

	}

	
	@When("^a valid username and a valid password are entered$")
	public void aValidUsernameAndAValidPasswordAreEntered() {
        // Enter Valid Username
        driver.findElement(priceNetLocatorLibrary.priceNet_login_UsernameTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER")));
        reporterHelper.log("Username entered Successfully");

        // Enter Valid Password
        driver.findElement(priceNetLocatorLibrary.priceNet_login_PasswordTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_PASSWORD"));
        reporterHelper.log("Password entered Successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}

	@When("^no username and no password are entered$")
	public void noUsernameAndNoPasswordAreEntered() {

		// Enter Valid Username
        driver.findElement(priceNetLocatorLibrary.priceNet_login_UsernameTextBox).clear();
        reporterHelper.log("Username cleared successfully");

        // Clear Password
        driver.findElement(priceNetLocatorLibrary.priceNet_login_PasswordTextBox).clear();
        reporterHelper.log("Password cleared successfully");
	    
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}
	

	@When("^a valid username and no password are entered$")
	public void a_valid_usernameAndNoPasswordAreEntered() {
        // Enter Valid Username
//        driver.findElement(priceNetLocatorLibrary.priceNet_login_UsernameTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER")));
        driver.findElement(priceNetLocatorLibrary.priceNet_login_UsernameTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER")));
        reporterHelper.log("Valid Username entered Successfully");

        // Clear Password
        driver.findElement(priceNetLocatorLibrary.priceNet_login_PasswordTextBox).clear();
        reporterHelper.log("Password cleared successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}

	@When("^a valid username and an invalid password are entered$")
	public void a_valid_usernameAndAnInvalidPasswordAreEntered() {
        // Enter Valid Username
        driver.findElement(priceNetLocatorLibrary.priceNet_login_UsernameTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER")));
        reporterHelper.log("Valid Username entered Successfully");

        // Enter an invalid Password
        driver.findElement(priceNetLocatorLibrary.priceNet_login_PasswordTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME_" + Utils.getProperty("AUTO_RUNNER_IDENTIFIER")));
        reporterHelper.log("Invalid Password cleared successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}	
	
	@When("^no username and a valid password are entered$")
	public void noUsernameAndAValidPasswordAreEntered() {
        // Clear Username
        driver.findElement(priceNetLocatorLibrary.priceNet_login_UsernameTextBox).clear();;
        reporterHelper.log("Username cleared Successfully");

        // Enter Valid Password
        driver.findElement(priceNetLocatorLibrary.priceNet_login_PasswordTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_PASSWORD"));
        reporterHelper.log("Valid Password entered successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}	
	
	@When("^the Login button is clicked$")
	public void theLoginButtonIsClicked() {

		// Submit the form 
        driver.findElement(priceNetLocatorLibrary.priceNet_login_LoginButton).click();
        reporterHelper.startTimer("Login");
        reporterHelper.log("\nButton clicked Successfully, syncing with PriceNet main page...");
	}

	@Then("^the following error message should be displayed '(.*)'$")
	public void theFollowingErrorMessageShouldBeDisplayed(String expectedErrorMessage) {
		
		BrowserHelper.customSleep(1 * 1000);
		
		String actualErrorMessgae = driver.findElement(priceNetLocatorLibrary.priceNet_login_LoginErrorMessage).getText();

		reporterHelper.log("\nExpected error message:\n\t" + expectedErrorMessage);
		reporterHelper.log("Actual error message:\n\t" + actualErrorMessgae);
		
		reporterHelper.takeScreenshot(driver, "Login-Error Message");
		
		if(!expectedErrorMessage.trim().equals(actualErrorMessgae.trim())) {
			reporterHelper.customFailScript("Expected error message '" + expectedErrorMessage + "' however actual message is '" + actualErrorMessgae + "'");
		}

		reporterHelper.log("SUCECSS: Error message as expected");

	}

	@Then("^PriceNet main page is displayed$")
	public void priceNetMainPageIsDisplayed() {

		reporterHelper.log("\nSyncing with PriceNet Welcome Page...");
		
		reporterHelper.log("\tSyncing with Welcome Heading...");
		BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_ContentPane_WelcomeHeading, "ContentPane", "present");
		reporterHelper.log("\tSyncing with Entity Pane Items Count...");
        BrowserHelper.syncOnElement(driver, priceNetLocatorLibrary.priceNet_EntityPane_ItemsCount, "EntityPane", "present");
        
		reporterHelper.stopTimer("Login");
		
        reporterHelper.log("Logged in Successfully");
        reporterHelper.takeScreenshot(driver, "Main_Page-Initial_State");
	}

	@Then ("^User is returned to Login Page$") 
	public void userIsReturnedToLoginPage() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(priceNetLocatorLibrary.priceNet_login_UsernameTextBox));
        
		reporterHelper.log("User returned to Login Page successfully");
	}	
}	

