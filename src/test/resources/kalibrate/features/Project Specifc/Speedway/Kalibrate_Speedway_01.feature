Feature: Kalibrate Speedway 01

  Scenarios used to attempt to replicate Speedway Performance Degredation

  @kalibrate @speedway01 @2.4.1 @2.8
  Scenario: Process Sites For Review 01
    
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    When the 'AutoSpeedway' workspace is selected from workspace menu
    Then the 'AutoSpeedway' workspace is displayed
    
#	When Search widget Search Types set to 'Own Sites'
    When Search widget filter applied with a 'Product' filter of 'Regular'
    Then a filtered set of site results will be displayed matching the filter
    
    When in mutli pricing widget prices are generated for all sites with 'Do not auto-implement this price generation' selected
    
    Then Search widget filter applied with a 'Sites for review' filter of '<checked>'
    Then a filtered set of site results will be displayed matching the filter
    
    Then in prices widget sites for review are processed

