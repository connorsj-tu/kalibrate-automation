Feature: Kalibrate Performance Search_01
  Scenarios used to record timings for Search widget

  Background: The user is already logged in with a new workspace
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed

	@kalibrate @search @perf @search01 @_01
  Scenario: Search Performance Scenario 01_01

    When User creates a new triple column Workspace named 'Auto_Test_WS1' 
    Then The created workspace is displayed
    
    When the user is on the 'Tools' widgets category
    And the user drags the following widgets into their respective containers:
      | containerNumber | widgetName    |
      |               1 | Search        |
      
    Then the following widgets are displayed:
      | widgetName    |
      | Search        |
      
     
    When Search widget search initiated with a name filter of ''
    Then a filtered set of site results will be displayed

    When Search widget search initiated with a name filter of 'MI OUTSTATE'
    Then a filtered set of site results will be displayed

    When Search widget search initiated with a name filter of 'DETROIT'
    Then a filtered set of site results will be displayed
    
    When Search widget search initiated with a name filter of 'CLEVELAND'
    Then a filtered set of site results will be displayed

    When Search widget search initiated with a name filter of 'CHATTANOOGA'
    Then a filtered set of site results will be displayed


    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    

    