Feature: Analytics widgets Displayed on Workspace
  I want to be able to drag widgets onto a workspace, displaying widgets accordingly

  Background: The user is already logged in with a new workspace
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    #When the 'Add Workspace' workspace is selected from workspace menu
    #Then the 'New Workspace' workspace is displayed
    When User creates a new Workspace named 'Analytics_WS1' 
    Then The created workspace is displayed

  @kalibrate @menu_widget @all_widgets @regression @positive @pullrequest
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

		########################
		
    When User creates a new Workspace named 'Management_WS' 
    Then The created workspace is displayed
    
    Given the user is on the 'Management' widgets category
    
    When the user drags the following widgets into their respective containers:
      | containerNumber | widgetName    	|
      |               1 | Data Manager  	|
      |               2 | Events Manager	|
      |               2 | Export Manager	|
#			|               1 | Simulation Manager		|
      |               2 | Site Manager		|
      |               2 | Site Planner		|
#    	|               1 | Validation Viewer		|
           
    Then the following widgets are displayed:
      | widgetName    	|
      | Data Manager  	|
      | Events Manager	|
      | Export Manager	|
#     | Simulation Manager		|
      | Site Manager		|
      | Site Planner		|
#     | Validation Viewer		|     

   
    ###########

    When User creates a new Workspace named 'Pricing_WS' 
    Then The created workspace is displayed
    
    Given the user is on the 'Pricing' widgets category
    
    When the user drags the following widgets into their respective containers:
      | containerNumber | widgetName       |
      |               1 | Market Pricing   |
      |               2 | Market Strategy  |
      |               2 | Multi Pricing    |
      |               1 | Price Request    |
      |               2 | Site Strategy    |
      
    Then the following widgets are displayed:
      | widgetName    |
      | Market Pricing   |
      | Market Strategy  |
      | Multi Pricing    |
      | Price Requests   |
      | Site Strategy    | 


    ###########

    When User creates a new Workspace named 'Support_WS' 
    Then The created workspace is displayed
    
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

    ###########

    When User creates a new Workspace named 'Tools_WS' 
    Then The created workspace is displayed
    
    Given the user is on the 'Tools' widgets category
    
    When the user drags the following widgets into their respective containers:
      | containerNumber | widgetName    	|
      |               1 | Administrator  	|
      |               2 | Notes	|
      |               2 | RSS Feed	|
 			|               1 | Search		|
      |               2 | To Do		|
      |               2 | Twitter		|
 			|               1 | University		|
      |               2 | User Settings		|
           
    Then the following widgets are displayed:
      | widgetName    	|
      | Administrator  	|
      | Notes	|
      | RSS Feed	|
      | Search		|
      | To Do		|
      | Twitter		|
      | University |
      | User Settings |

                
    ########################
    
    # Logout and close browser
    Given Kalibrate main page is displayed
    When User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed