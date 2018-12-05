Feature: Kalibrate Performance Login/Logout SFR_01
  Scenarios used to record timings for Login/Logout

  Background: The user is already logged in with a new workspace
		Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
		
	@kalibrate @login/logout @perf @login/logout01 @_01
  Scenario: Login and Logout End To End Scenario 01_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed

	@kalibrate @login/logout @perf @login/logout02 @_01
  Scenario: Login and Logout End To End Scenario 02_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed

    
	@kalibrate @login/logout @perf @login/logout03 @_01
  Scenario: Login and Logout End To End Scenario 03_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed
    

	@kalibrate @login/logout @perf @login/logout04 @_01
  Scenario: Login and Logout End To End Scenario 04_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed


	@kalibrate @login/logout @perf @login/logout05 @_01
  Scenario: Login and Logout End To End Scenario 05_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed
    

	@kalibrate @login/logout @perf @login/logout06 @_01
  Scenario: Login and Logout End To End Scenario 06_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed
        
	@kalibrate @login/logout @perf @login/logout07 @_01
  Scenario: Login and Logout End To End Scenario 07_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed
    

	@kalibrate @login/logout @perf @login/logout08 @_01
  Scenario: Login and Logout End To End Scenario 08_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed

    
	@kalibrate @login/logout @perf @login/logout09 @_01
  Scenario: Login and Logout End To End Scenario 09_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed
    

	@kalibrate @login/logout @perf @login/logout10 @_01
  Scenario: Login and Logout End To End Scenario 10_01

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
      |               3 | Map           | 
  
  	And widgets are dragged from Search widget footer in the following configuration:
  	  | containerNumber | widgetName    |
  	  |               1 | Multi Pricing |
  	  
		Then the pricing widget is configured selecting standard pricing agents items

    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    
		Then a 'Retail Pricing Analyst' logs back in and Kalibrate main page is displayed
    