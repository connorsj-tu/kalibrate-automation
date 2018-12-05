Feature: Kalibrate Market Pricing - GPC
  Scenarios used to test General Price Changes


  Background: The user is already logged in with a new workspace
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    

	@kalibrate @marketpricing @gpc @perf @newperf
  Scenario: Market Pricing - Create new Increase/Decrease GPC
    When User creates a new triple column Workspace named 'Market_Pricing_GPC' 
    Then The created workspace is displayed
    
    When the user is on the 'Pricing' widgets category
    And the user drags the following widgets into their respective containers:
      | containerNumber | widgetName    	|
      |               1 | Market Pricing	|
      
    Then the following widgets are displayed:
      | widgetName    	|
      | Market Pricing	|
      
		When a new 'Increase/Decrease' GPC is created for all sites
		Then the new GPC will be present in the Active GPC's section
		
		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed 		