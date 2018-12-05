Feature: Kalibrate Murphy 01

  Scenarios used to attempt to replicate Speedway Performance Degredation

  @kalibrate @MURPHY
  Scenario: Process Sites For Review 01
    
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    When the 'AutoMurphy' workspace is selected from workspace menu
    Then the 'AutoMurphy' workspace is displayed correctly
    
#		When Search widget Search Types set to 'Own Sites'
    When Murphy Search widget filter applied with a 'Sites for review' filter of '<unchecked>'
    Then a filtered set of site results will be displayed matching the filter
    
    When in mutli pricing widget prices are generated for all sites with 'Do not auto-implement this price generation' selected
    
    Then Murphy Search widget filter applied with a 'Sites for review' filter of '<checked>'
    Then a filtered set of site results will be displayed matching the filter
    
    Then in prices widget sites for review are processed
