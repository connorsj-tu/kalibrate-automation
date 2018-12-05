package com.step_definitions.pricenetweb;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.helpers.BrowserHelper;
import com.common.helpers.DatabaseHelper;
import com.common.helpers.ReporterHelper;
import com.common.helpers.Utils;
import com.common.page_objects.Kalibrate_Generic_LocatorLibrary;
import com.common.page_objects.PriceNetWeb_Generic_LocatorLibrary;
import com.step_definitions.kalibrate.Kalibrate_Hooks;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PriceNetWeb_StepDefinitions {
	
	private WebDriver driver = Kalibrate_Hooks.driver;

	private ReporterHelper reporterHelper = Kalibrate_Hooks.reporterHelper;
	private BrowserHelper browserHelper = Kalibrate_Hooks.browserHelper;
	private DatabaseHelper databaseHelper = Kalibrate_Hooks.databaseHelper;

	private String applicationName = Kalibrate_Hooks.applicationName;
	
	private WebElement tempWebElement;

	private boolean performSync = true;

	private String currentPage;

	private int numOfProductsPriced;
	
	private Kalibrate_Generic_LocatorLibrary kalibrateLocatorLibrary = Kalibrate_Hooks.kalibrateLocatorLibrary;
	

	@When("^'(.*)' tab is selected$")
	public void tabIsSelected(String targetTabName) throws Throwable {
		
		currentPage = targetTabName;
		
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_Default_Tabs, "default", "present");
		
		reporterHelper.log("\tNumber of tabs identified: " + elements.size());
		
		String currentTabName = "";
		
		int titleWaitCount;
		boolean foundFilter = false;
		
		for (int i = 0; i < elements.size(); i++) {
			tempWebElement = elements.get(i);
			if(elements.get(i).getText().equalsIgnoreCase(targetTabName)) {
				reporterHelper.log("\tClicking tab number " + (i + 1) + ": " + targetTabName);
				tempWebElement.click();
				foundFilter = true;
				break;
			}
		}
		
		if(!foundFilter) {
			reporterHelper.customFailScript("Unable to find tab: " + targetTabName);
		}
	}
		


	@Then("^the selected page is displayed in the main pane$")
	public void theSelectedPageIsDisplayedInTheMainPane() throws Throwable {
		
		reporterHelper.log("Checking that '" + currentPage + "' page has finished loading");
		
		switch (currentPage) {
        case "Competitor Prices":
        	BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_Sync, "ifmPage", "visible");
            break;
        default:
            reporterHelper.customFailScript("Unknown Page name in method theSelectedPageIsDisplayedInTheMainPane(): " + currentPage);
		}
		reporterHelper.log("\t'" + currentPage + "' page sync success");
	}

	@Then("^competitor prices are submitted (with|without) change - in a loop for (.*) times$")
	public void competitorPricesAreSubmittedWithoutChangeInALoop(String changesRequired, int numberOfIterations) throws Throwable {
		
	    LocalDateTime surveyDateTime = LocalDateTime.now().minusMonths(11);
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    
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

		
		for (int i = 0; i < numberOfIterations; i++) {
			
			if(changesRequired.equalsIgnoreCase("without")) 
				competitorPricesAreSubmittedWithoutChange(surveyDateTime.format(dateFormatter), surveyDateTime.format(timeFormatter));
			else
				competitorPricesAreSubmittedWithChanges(surveyDateTime.format(dateFormatter), surveyDateTime.format(timeFormatter));

			surveyDateTime = surveyDateTime.plusMinutes(1);

			reporterHelper.log("\nCompleted Submitted Prices iteration number " + (i+1) + ". Number of iterations remaining: " + (numberOfIterations-(i+1)) );
				


//			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNCompetitorPrice] "
//					+ "Set Created = EffectiveTime, LastUpdated = EffectiveTime "
//					+ "WHERE CompetitorPriceID > " + previousMaxCompetitorPriceID);
//
//			
//			resultSet = databaseHelper.executeSQLStringWithResultSet("UPDATE [Speedway_PN].[dbo].[PNOwnPrice] "
//					+ "Set Created = EffectiveTime, LastUpdated = EffectiveTime "
//					+ "WHERE OwnPriceID > " + previousMaxOwnPriceID);
//
			  			
		}
		
		
		
	}
	
	@Then("^competitor prices are submitted (with|without) changes? - in a loop for (.*) minutes$")
	public void competitorPricesAreSubmittedInALoopForXMinutes(String changesRequired, long loopTargetDurationMinutes) throws Throwable {
		
	    LocalDateTime surveyDateTime = LocalDateTime.now().minusMonths(10);
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    
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
				competitorPricesAreSubmittedWithoutChange(surveyDateTime.format(dateFormatter), surveyDateTime.format(timeFormatter));
			else
				competitorPricesAreSubmittedWithChanges(surveyDateTime.format(dateFormatter), surveyDateTime.format(timeFormatter));
			
			surveyDateTime = surveyDateTime.plusMinutes(1);

			reporterHelper.log("\nCompleted Submitted Prices iteration number " + loopCount++ + ". Number of products priced: " + numOfProductsPriced + ". Number of minutes elapsed: " + loopElapsedMinutes + " Number of minutes remaining: " + (loopTargetDurationMinutes - loopElapsedMinutes));

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
	public void competitorPricesAreSubmittedWithoutChange(String surveyDate, String surveyTime) throws Throwable {
		
		reporterHelper.takeScreenshot(driver, "Site_Survey-Initial State");
		
		if(surveyTime != null) {

			reporterHelper.log("\nSetting survey date to: " + surveyDate);
			tempWebElement = BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_DateTextbox, "ifmPage", "present");
			tempWebElement.clear();
			tempWebElement.sendKeys(surveyDate);
			tempWebElement.sendKeys(Keys.TAB);

			reporterHelper.log("\nSetting survey time to: " + surveyTime);
			tempWebElement = BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_TimeTextbox, "ifmPage", "present");
			tempWebElement.clear();
			tempWebElement.sendKeys(surveyTime);
			tempWebElement.sendKeys(Keys.TAB);
			
			BrowserHelper.customSleep(1 * 1000);
			
			theSelectedPageIsDisplayedInTheMainPane();
		}
		
		reporterHelper.log("\nIdentifying product checkboxes;");
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_ProductCheckBoxes, "ifmPage", "present");
		
		reporterHelper.log("\tNumber of product checkboxes identified: " + elements.size());
		
//		for (int i = 0; i < elements.size(); i++) {
		for (int i = 0; i < 3; i++) {
			elements.get(i).click();
			numOfProductsPriced++;
		}

		reporterHelper.takeScreenshot(driver, "Site_Survey-Prices_Ready_To_Sumbit");
		

		// Click OK
		BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_OkButton, "ifmPage", "present").click();
		BrowserHelper.customSleep(2 * 1000);
		
		try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        reporterHelper.log("Alert data: " + alertText);
	        alert.accept();
	    } catch (NoAlertPresentException e) {
	    	//	Do nothing
	    }
		
		// Sync on Site Survey Page once more to ensure submission complete
		BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_PricesSumbittedSuccessfully, "ifmPage", "visible");
		
		reporterHelper.takeScreenshot(driver, "Site_Survey-Prices_Submitted");
		
		reporterHelper.log("\tSite Survey form submitted successfully");

	}
	

	@Then("^competitor prices are submitted with changes$")
	public void competitorPricesAreSubmittedWithChanges(String surveyDate, String surveyTime) throws Throwable {
//	public void competitorPricesAreSubmittedWithChanges() throws Throwable {
		
		reporterHelper.takeScreenshot(driver, "Site_Survey_Adjusted-Initial State");
		
		if(surveyTime != null) {
			reporterHelper.log("\nSetting survey date to: " + surveyDate);
			tempWebElement = BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_DateTextbox, "ifmPage", "present");
			tempWebElement.clear();
			tempWebElement.sendKeys(surveyDate);
			tempWebElement.sendKeys(Keys.TAB);

			reporterHelper.log("\nSetting survey time to: " + surveyTime);
			tempWebElement = BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_TimeTextbox, "ifmPage", "present");
			tempWebElement.clear();
			tempWebElement.sendKeys(surveyTime);
			tempWebElement.sendKeys(Keys.TAB);
			
			BrowserHelper.customSleep(1 * 1000);
			
			theSelectedPageIsDisplayedInTheMainPane();
		}
		
		reporterHelper.log("\nIdentifying product Text Boxes;");
		List<WebElement> elements = BrowserHelper.syncOnElements(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_ProductPriceTextBoxes, "ifmPage", "present");
		
		reporterHelper.log("\tNumber of product text boxes identified: " + elements.size());
		
		BigDecimal adjustmentValueBigDecimal  = new BigDecimal("0.1");
		BigDecimal currentPriceBigDecimal;
		BigDecimal newPriceBigDecimal;
		
//		for (int i = 0; i < elements.size(); i++) {
		for (int i = 0; i < 3; i++) {
			tempWebElement = elements.get(i);
			
			String tempString = tempWebElement.findElement(By.xpath("../../td[position()=3]")).getText();
			
			// if no previous price to capture from screen then use following
			if(tempString.trim().equals(""))
				tempString = "144.9";
			
			currentPriceBigDecimal  = new BigDecimal(tempString);

			if(new Random().nextBoolean())
				newPriceBigDecimal = currentPriceBigDecimal.add(adjustmentValueBigDecimal);
			else
				newPriceBigDecimal = currentPriceBigDecimal.subtract(adjustmentValueBigDecimal);
			
			tempWebElement.clear();
			tempWebElement.sendKeys(newPriceBigDecimal.toString());
			numOfProductsPriced++;
		}

		reporterHelper.takeScreenshot(driver, "Site_Survey-Prices_Adjusted_Ready_To_Sumbit");

		// Click OK
		BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_OkButton, "ifmPage", "present").click();
		BrowserHelper.customSleep(2 * 1000);
		
		try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        reporterHelper.log("Alert data: " + alertText);
	        alert.accept();
	    } catch (NoAlertPresentException e) {
	    	//	Do nothing
	    }
		
		// Sync on Site Survey Page once more to ensure submission complete
		BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_CompetitorPricesPage_PricesSumbittedSuccessfully, "ifmPage", "visible");
		
		reporterHelper.takeScreenshot(driver, "Site_Survey_Adjusted-Prices_Submitted");
		
		reporterHelper.log("\tSite Survey form submitted successfully");

	}
	


	@Given("^the user is logged in and main page is displayed$")
	public void theUserIsLoggedInAndMainPageIsDisplayed() throws Throwable {
		userIsOnLoginPage();
		aValidUsernameAndAValidPasswordAreEntered();
		theLoginButtonIsClicked();
		theMainPageIsDisplayed();
	}
	
	@Given("^the Login page is displayed$")
	public void userIsOnLoginPage() {
		
		BrowserHelper.customSleep(2 * 1000);
		
		if(BrowserHelper.isElementPresent(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_Security_Heading)) {
			BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_Security_ContinueLink, "clickable").click();
		}
		
        BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_UsernameTextBox, "clickable");
        
        reporterHelper.log("Login Page displayed successfully");
        reporterHelper.takeScreenshot(driver, "Login-Initial_State");

	}

	
	@When("^a valid username and a valid password are entered$")
	public void aValidUsernameAndAValidPasswordAreEntered() {
        // Enter Valid Username
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_UsernameTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME"));
        reporterHelper.log("Username entered Successfully");

        // Enter Valid Password
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_PasswordTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_PASSWORD"));
        reporterHelper.log("Password entered Successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}

	@When("^no username and no password are entered$")
	public void noUsernameAndNoPasswordAreEntered() {

		// Enter Valid Username
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_UsernameTextBox).clear();
        reporterHelper.log("Username cleared successfully");

        // Clear Password
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_PasswordTextBox).clear();
        reporterHelper.log("Password cleared successfully");
	    
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}
	

	@When("^a valid username and no password are entered$")
	public void a_valid_usernameAndNoPasswordAreEntered() {
        // Enter Valid Username
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_UsernameTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME"));
        reporterHelper.log("Valid Username entered Successfully");

        // Clear Password
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_PasswordTextBox).clear();
        reporterHelper.log("Password cleared successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}

	@When("^a valid username and an invalid password are entered$")
	public void a_valid_usernameAndAnInvalidPasswordAreEntered() {
        // Enter Valid Username
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_UsernameTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME"));
        reporterHelper.log("Valid Username entered Successfully");

        // Enter an invalid Password
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_PasswordTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_USERNAME"));
        reporterHelper.log("Invalid Password cleared successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}	
	
	@When("^no username and a valid password are entered$")
	public void noUsernameAndAValidPasswordAreEntered() {
        // Clear Username
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_UsernameTextBox).clear();;
        reporterHelper.log("Username cleared Successfully");

        // Enter Valid Password
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_PasswordTextBox).sendKeys(Utils.getProperty(applicationName + "_" + Utils.getProperty("PERSONA") + "_PASSWORD"));
        reporterHelper.log("Valid Password entered successfully");
        
        reporterHelper.takeScreenshot(driver, "Login-Populated");
	}	
	
	@When("^the Login button is clicked$")
	public void theLoginButtonIsClicked() {

		// Submit the form 
        driver.findElement(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_LoginButton).click();
        reporterHelper.startTimer("Login");
        reporterHelper.log("\nButton clicked Successfully, syncing with the main page...");
	}

	@Then("^the following error message should be displayed '(.*)'$")
	public void theFollowingErrorMessageShouldBeDisplayed(String expectedErrorMessage) {
		
		BrowserHelper.customSleep(1 * 1000);
		
		String actualErrorMessgae = "";
		
		try {
	        Alert alert = driver.switchTo().alert();
	        actualErrorMessgae = alert.getText().trim();
	        reporterHelper.log("Alert data: " + actualErrorMessgae);
	        alert.accept();
	    } catch (NoAlertPresentException e) {
	    	//	Do nothing
	    }

		
		reporterHelper.log("\nExpected error message:\n\t" + expectedErrorMessage);
		reporterHelper.log("Actual error message:\n\t" + actualErrorMessgae);
		
		reporterHelper.takeScreenshot(driver, "Login-Error Message");
		
		if(!expectedErrorMessage.trim().equals(actualErrorMessgae.trim())) {
			reporterHelper.customFailScript("Expected error message '" + expectedErrorMessage + "' however actual message is '" + actualErrorMessgae + "'");
		}

		reporterHelper.log("SUCECSS: Error message as expected");

	}

	@Then("^the main page is displayed$")
	public void theMainPageIsDisplayed() {

		reporterHelper.log("Syncing with the Welcome Page...");
		
		BrowserHelper.syncOnElement(driver, PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_ifmPage_WelcomeHeading, "ifmPage", "present");
//        BrowserHelper.syncOnElement(driver, LocatorLibrary.priceNetWeb_EntityPane_ItemsCount, "EntityPane", "present");
        
		reporterHelper.stopTimer("Login");
		
        reporterHelper.log("Logged in Successfully");
        reporterHelper.takeScreenshot(driver, "Main_Page-Initial_State");
	}

	@Then ("^User is returned to Login Page$") 
	public void userIsReturnedToLoginPage() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(PriceNetWeb_Generic_LocatorLibrary.priceNetWeb_login_UsernameTextBox));
        
		reporterHelper.log("User returned to Login Page successfully");
	}	
}	

