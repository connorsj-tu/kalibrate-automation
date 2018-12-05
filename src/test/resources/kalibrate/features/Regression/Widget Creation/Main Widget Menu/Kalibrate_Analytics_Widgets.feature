Feature: Analytics widgets Displayed on Workspace
  I want to be able to drag widgets onto a workspace, displaying widgets accordingly

  Background: The user is already logged in with a new workspace
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    #When the 'Add Workspace' workspace is selected from workspace menu
    #Then the 'New Workspace' workspace is displayed
    When User creates a new Workspace named 'Auto_Test_WS1' 
    Then The created workspace is displayed

  @kalibrate @menu_widget @analytics @regression @positive
  Scenario: As a user I want to be able to add widgets to my workspace which display accordingly
    Given the user is on the 'Analytics' widgets category
    
    When the user drags the following widgets into their respective containers:
      | containerNumber | widgetName    |
      |               1 | Intel         |
      |               2 | Map           |
      |               2 | Report Viewer |
      
    Then the following widgets are displayed:
      | widgetName    |
      | Intel         |
      | Map           |
      | Report Viewer |

		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    Given Kalibrate main page is displayed
    When User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed