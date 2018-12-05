Feature: Kalibrate Performance Pricing TTP-128 SFR_01
  Scenarios used to record timings across different Pricing E2E scenarios

  Background: The user is already logged in with a new workspace
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed

	@kalibrate @pricing @ttp-128 @perf @_01 @ignore
  Scenario: Process Sites For Review End To End Scenario 03_01
    When User creates a new triple column Workspace named '1Auto_Test_WS1' 
    Then The created workspace is displayed
    
    When the user is on the 'Tools' widgets category
    And the user drags the following widgets into their respective containers:
      | containerNumber | widgetName    |
      |               1 | Search        |
      
    Then the following widgets are displayed:
      | widgetName    |
      | Search        |
      
     
    When Search widget filter applied with a 'Product' filter of '<PRODUCT_GROUP_FILTER>'
    Then a filtered set of site results will be displayed
    
    
    When widgets are dragged from first site in Search widget in the following configuration:
      | containerNumber | widgetName    		|
      |               2 | Intel							|
      |               2 | Map           		|
      |               2 | Report Viewer			|
      |               2 | Pump Price Update	|
      |               2 | Site Metrics			|
      |               2 | Pricing						|
      |               2 | Events Manager		|
      |               2 | Site Manager			|
      |               2 | Site Strategy			|
      |               2 | Site Planner			|
      |               2 | Notes							|
      

  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    		|
  	  |               3 | Intel							|
  	  |               3 | Map								|
  	  |               3 | Report Viewer			|
  	  |               3 | Multi Pricing 		|
  	  |               3 | Events Manager		|
  	  |               3 | Market Strategy		|
  	  |               3 | Site Manager			|
  	  |               3 | Site Strategy			|
  	  |               3 | Market Pricing		|
  	  
    #Then the pricing widget is configured selecting standard pricing agents items
      #
    #When in mutli pricing widget set number of sites per page to 50 
    #And in mutli pricing widget prices are generated using paging with 'Do not auto-implement this price generation' selected
    #And Search widget filter applied with a 'Sites for review' filter of '<checked>'
    #And a filtered set of site results will be displayed
    #And Multi Pricing widget is closed
    #Then in prices widget sites for review are processed
#
#		# Delete the workspace
    #When User deletes the created Workspace
    #Then The created workspace is deleted
    #
    # Logout and close browser
    #When Kalibrate main page is displayed
    #And User Logout from Kalibrate
    #Then User is returned to Login Page
    #And The browser is closed 