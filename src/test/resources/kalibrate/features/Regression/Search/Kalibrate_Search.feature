@kalibrate @search @regression 
Feature: Kalibrate Search Widget
  
  This feature tests the Search widget within Kalibrate

  Background: 
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    And the 'Search' Widget is displayed in workspace

  @positive
  Scenario: A user can search for all sites by specifying no filter
    
    TODO: Add Scenario Description here

    When Search initiated with no filter
    Then a full set of site results will be displayed

  @positive @pullrequest
  Scenario: A user can search for sites using a name filter
    
    TODO: Add Scenario Description here

    When Search initiated with a name filter
    Then a filtered set of site results will be displayed matching the name filter

  @wip
  Scenario: A user can search for sites using a location filter
    
    TODO: Add Scenario Description here

    When Search initiated with a location filter
    Then a filtered set of site results will be displayed matching the name filter
