Feature: Kalibrate Performance Pricing SFR_01
  Scenarios used to record timings across different Pricing E2E scenarios


  Background: The user is already logged in with a new workspace
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    

	@kalibrate @pricing @perf @sfr01 @ignore
  Scenario: Process Sites For Review End To End Scenario 01_01
    When User creates a new single column Workspace named 'Auto_Test_WS1' 
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
      |               1 | Notes       			|
      |               1 | Pricing       		|
#      |               1 | Map       				|
      |               1 | Pump Price Update	|
      |               1 | Site Manager			|

  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
    Then the pricing widget is configured selecting all items
      
    When in mutli pricing widget set number of sites per page to 100 
    And in mutli pricing widget prices are generated using paging with 'Do not auto-implement this price generation' selected
    And Search widget filter applied with a 'Sites for review' filter of '<checked>'
    And a filtered set of site results will be displayed
    Then in prices widget sites for review are processed

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed 
    
	@kalibrate @pricing @perf @sfr01m
  Scenario: Process Sites For Review End To End Scenario 01m_01
    When User creates a new single column Workspace named 'Auto_Test_WS1' 
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
      |               1 | Notes       			|
      |               1 | Pricing       		|
      |               1 | Map       				|
      |               1 | Pump Price Update	|
      |               1 | Site Manager			|

  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
    Then the pricing widget is configured selecting all items
      
    When in mutli pricing widget set number of sites per page to 100 
    And in mutli pricing widget prices are generated using paging with 'Do not auto-implement this price generation' selected
    And Search widget filter applied with a 'Sites for review' filter of '<checked>'
    And a filtered set of site results will be displayed
    Then in prices widget sites for review are processed

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed 
    	
	@kalibrate @pricing @perf @sfr02 @ignore
  Scenario: Process Sites For Review End To End Scenario 02_01
    When User creates a new triple column Workspace named 'Auto_Test_WS1' 
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
      | containerNumber | widgetName    |
      |               2 | Pricing       |
      #|               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
    Then the pricing widget is configured selecting all items
      
    When in mutli pricing widget set number of sites per page to 100 
    And in mutli pricing widget prices are generated using paging with 'Do not auto-implement this price generation' selected
    And Search widget filter applied with a 'Sites for review' filter of '<checked>'
    And  a filtered set of site results will be displayed
    Then in prices widget sites for review are processed

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed
   
	@kalibrate @pricing @perf @sfr02m
  Scenario: Process Sites For Review End To End Scenario 02m_01
    When User creates a new triple column Workspace named 'Auto_Test_WS1' 
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
      | containerNumber | widgetName    |
      |               2 | Pricing       |
      |               2 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
    Then the pricing widget is configured selecting all items
      
    When in mutli pricing widget set number of sites per page to 100 
    And in mutli pricing widget prices are generated using paging with 'Do not auto-implement this price generation' selected
    And Search widget filter applied with a 'Sites for review' filter of '<checked>'
    And  a filtered set of site results will be displayed
    Then in prices widget sites for review are processed

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed
    
	@kalibrate @pricing @perf @sfr03 @ignore
  Scenario: Process Sites For Review End To End Scenario 03_01
    When User creates a new triple column Workspace named 'Auto_Test_WS1' 
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
      | containerNumber | widgetName    |
      |               2 | Pricing       |
#      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
    Then the pricing widget is configured selecting standard pricing agents items
      
    When in mutli pricing widget set number of sites per page to 100 
    And in mutli pricing widget prices are generated using paging with 'Do not auto-implement this price generation' selected
    And Search widget filter applied with a 'Sites for review' filter of '<checked>'
    And a filtered set of site results will be displayed
    And Multi Pricing widget is closed
    Then in prices widget sites for review are processed

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed   


	@kalibrate @pricing @perf @sfr04 
  Scenario: Process Sites For Review End To End Scenario 04_01
    When User creates a new triple column Workspace named 'Auto_Test_WS1' 
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
      | containerNumber | widgetName    |
      |               2 | Pricing       |
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
    Then the pricing widget is configured selecting standard pricing agents items
    
    When in mutli pricing widget set number of sites per page to 100
    And in mutli pricing widget prices are generated using paging with 'Do not auto-implement this price generation' selected
    And Search widget filter applied with a 'Sites for review' filter of '<checked>'
    And a filtered set of site results will be displayed
    And Multi Pricing widget is closed
    Then in prices widget sites for review are processed

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed       
    
             

	@kalibrate @pricing @perf @sfr05 @comppricesurvery @newperf
  Scenario: Process Sites For Review With Competitor Survery Drop End To End Scenario 04_01
    When User creates a new triple column Workspace named 'Auto_Test_WS1' 
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
      | containerNumber | widgetName    |
      |               2 | Pricing       |
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
    Then the pricing widget is configured selecting standard pricing agents items
    
    When in mutli pricing widget set number of sites per page to 100
    And in mutli pricing widget prices are generated using paging with 'Do not auto-implement this price generation' selected
    And Search widget filter applied with a 'Sites for review' filter of '<checked>'
    And a filtered set of site results will be displayed
    And Multi Pricing widget is closed
    Then in prices widget sites for review are processed with competitor price surveys submitted during run

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed       
    
                          