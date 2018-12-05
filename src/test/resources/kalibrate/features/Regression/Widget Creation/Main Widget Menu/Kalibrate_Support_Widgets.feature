Feature: Support widgets Displayed on Workspace
  I want to be able to drag widgets onto a workspace, displaying widgets accordingly

  Background: The user is already logged in with a new workspace
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    When User creates a new Workspace named 'Auto_Test_WS1' 
    Then The created workspace is displayed

  @kalibrate @menu_widget @support @regression @positive
  Scenario: As a user I want to be able to add widgets to my workspace which display accordingly
    Given the user is on the 'Support' widgets category
    
    When the user drags the following widgets into their respective containers:
      | containerNumber | widgetName    	|
      |               1 | Batch Audit  	|
      |               2 | Configuration	|
      |               2| ETL Log	|
 			|               1 | Import Log		|
      |               2 | Install Log		|
      |               2 | System Health Check		|
           
    Then the following widgets are displayed:
      | widgetName    	|
      | Batch Audit  	|
      | Configuration	|
      | ETL Log	|
      | Import Log		|
      | Install Log		|
      | System Health Check		|

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    Given Kalibrate main page is displayed
    When User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed