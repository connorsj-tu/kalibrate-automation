#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios 
#<> (placeholder)
#""
## (Comments)

#Sample Feature Definition Template
@Kalibrate_Retail_Pricing_E2E_General_Widget_Workspace
Feature: Kalibrate Retail Pricing E2E Automation Suite
Kalibrate Retail Pricing E2E Automation Suite below steps are performed 



Background: The user is already logged in with a new workspace
    Given the user is logged in and Kalibrate main page is displayed
    #When the 'Add Workspace' workspace is selected from workspace menu
    #Then the 'New Workspace' workspace is displayed
    And User navigates to "Retail Pricing" workspace
 
@Kalibrate_Retail_Pricing_E2E_General_Widget_Workspace @PullWidgets_for_Selected_OwnSite
Scenario Outline: Kalibrate General Regression - Retail Pricing - Widget loaded for existing sites
#And Pull "Search" widget to "Container1"
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container2"
And Pull "Pricing" widget for selected site to "Container2"
And Pull "MultiPricing" widget for selected site to "Container3"
And Pull "Pump Price Update" widget for selected site to "Container1"
And Pull "Intel" widget for selected site to "Container3"
And Pull "Map" widget for selected site to "Container1"
Then Verify widgets are loaded without any errors
Then the site selected in 'Search' widget should be displayed in linked 'Pricing' widget
Then the site selected in 'Search' widget should be displayed in linked 'MultiPricing' widget
Then the site selected in 'Search' widget should be displayed in linked 'Intel' widget
Then the site selected in 'Search' widget should be displayed in linked 'Map' widget
Then the site selected in 'Search' widget should be displayed in linked 'Pump Price Update' widget
Then the site selected in 'Search' widget should be displayed in linked 'Site Manager' widget


Examples:
   | ImportCode | 
   | K002 |
   
