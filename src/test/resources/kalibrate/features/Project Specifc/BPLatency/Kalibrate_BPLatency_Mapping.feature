Feature: Kalibrate BP Latency

  Scenarios used to test BP Latency

  @kalibrate @BP_LATENCY
  Scenario: Mapping
    
    Given KalibrateBP main page is displayed
    When the 'Auto01_Mapping' workspace is selected from workspace menu
    Then the 'Auto01_Mapping' workspace is displayed
    
    When Map widget search initiated with a name filter of 'Sloterplas'
    Then a filtered set of site results will be displayed in Map Search widget with 1 match
    And map will update to display 1 site
    
    When the single own site is clicked
    Then pump price history information is displayed


  @kalibrate @BP_LATENCY
  Scenario: Pricing
    
    Given KalibrateBP main page is displayed
    When the 'Auto02_Daily_Pricing' workspace is selected from workspace menu
    Then the 'Auto02_Daily_Pricing' workspace is displayed
    
    When Search widget filter applied with an 'Area' filter of 'Retail Area 3'
    Then a filtered set of site results will be displayed in Search widget with 2 matches
    
    When a site is selected from 'Search' widget results
    Then the site selected in 'Search' widget should be displayed in linked 'Pricing' widget
    #And the site selected in 'Search' widget should be displayed in linked 'Intel' widget
    
    
  @kalibrate @BP_LATENCY 
  Scenario: Intel
    
    Given KalibrateBP main page is displayed
    When the 'Auto03_Intel' workspace is selected from workspace menu
    Then the 'Auto03_Intel' workspace is displayed correctly
    
    When Search widget search initiated with a name filter of 'PLASPOELPOLDER'
    Then a filtered set of site results will be displayed in search widget with 1 match
    
    When a site is selected from 'Search' widget results
    Then the site selected in 'Search' widget should be displayed in linked 'Intel' widget

