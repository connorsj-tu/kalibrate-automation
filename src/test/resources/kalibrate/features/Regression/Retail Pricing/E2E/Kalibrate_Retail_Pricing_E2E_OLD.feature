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
#Sample Feature Definition Template

@Kalibrate_Retail_Pricing_E2E
Feature: Kalibrate Retail Pricing E2E Automation Suite
Kalibrate Retail Pricing E2E Automation Suite below steps are performed 
Background: The user is already logged in with a new workspace
    Given the user is logged in and Kalibrate main page is displayed
    #When the 'Add Workspace' workspace is selected from workspace menu
    #Then the 'New Workspace' workspace is displayed
    And User navigates to "Demo Retail" workspace


@Kalibrate_Retail_Pricing_E2E @Intel
Scenario Outline: Kalibrate General Regression - Retail Pricing - Intel Widget
And Search & select for "<ImportCode>" ownsite
Then Pull "Intel" widget for selected site to "Container2"
And Open "Average Sales Volume" chart in "Standard Chart" of Intel widget
Then Export data from "Intel" Widget
Examples:
 | ImportCode |  
 | K002 |
 
 
@Kalibrate_Retail_Pricing_E2E @Create_new_Own_Site
Scenario Outline: Kalibrate General Regression - Retail Pricing - Setup new Own Site
#When Pull "Site Manager" widget to "Container2"
#Then User creates a new Own Site with "<SiteName>" and "<ImportCode>" and "<Brand>",  "<Area>", "<Network>"
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container2"
#And  Open "Products" option in "Site Manager" widget
#When Assign Products <Product>
#| Regular |
#| Mid-Grade |
#| Premium |
#| Diesel |
#When  "Activate" Products <Product>
#| Regular |
# |Mid-Grade |
#| Premium |
#| Diesel |
And  Open "Product Groups" option in "Site Manager" widget
When "InActivate" <Product Groups> Product Group 
| All Products |
#When Assign <Product Groups> Product Group 
#| Gasoline |
#| Diesel |
#When "Activate" <Product Groups> Product Group 
#| Gasoline |
#| Diesel |
#When Pull "Pump Price Update" widget for selected site to "Container2"
#Then the site selected in 'Search' widget should be displayed in linked 'Pump Price Update' widget
#And Add Own Pump Price between "145.9" and "150.9"
    When User deletes the created Workspace
   Then The created workspace is deleted 
Examples:
   | SiteName | ImportCode |  Brand | Area | Network |
   #| Test Site 1001 | TS1001 | Alpha | East | North | 
   | G3 Site | G3 | Alpha | East | North | 


   
@Kalibrate_Retail_Pricing_E2E @Create_new_Competitor_Site
Scenario Outline: Kalibrate General Regression - Retail Pricing - Setup new Competitor Site
When Pull "Site Manager" widget to "Container2"
Then User creates a new Competitor Site with "<SiteName>" and "<ImportCode>" and "<Brand>",  "<CompetitorGroup>"
And  Open "Products" option in "Site Manager" widget
When Assign Products <Product> for Competitor Site
| Regular |
| Premium |
| Diesel |
| Mid-Grade |
When "Activate" Products <Product> for Competitor Site
| Regular |
| Premium |
| Diesel |
| Mid-Grade |
   When User deletes the created Workspace
   Then The created workspace is deleted 
Examples:
   | SiteName | ImportCode |  Brand | CompetitorGroup | 
    | Comp Site 205 | CS205 | Kilo | KSS Competitor |  
 

@Kalibrate_Retail_Pricing_E2E @Add_Market_Comp_Site_to_OwnSite
Scenario Outline: Kalibrate General Regression - Retail Pricing - Setup Local Market Sites
And Search & select for "<ImportCode>" ownsite 
When Pull "Site Manager" widget for selected site to "Container2"
And  Open "Local Market" option in "Site Manager" widget
And Search & Add Competitor sites <Comp Site Code> to OwnSite Market
| C001 |
| C002 |
| CS205 |
And Search & Add Own Site Competitor sites <Own Site Comp Code> to OwnSite Market
| K001 |
| K001 |

#And  Open "Product Groups" option in "Site Manager" widget
#And Set <Comp Site Code> as "Main Marker" for <Product Groups>
#| CS1001 |
Examples:
|ImportCode|
|G3 |

@Kalibrate_Retail_Pricing_E2E @Competitor_Survey_via_Kalibrate_PPU
Scenario: Kalibrate General Regression - Retail Pricing - Survey Competitor sites in Kalibrate Pump Price Update
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container2"
Then the site selected in 'Search' widget should be displayed in linked 'Site Manager' widget
When Pull "Pump Price Update" widget for selected site to "Container2"
Then the site selected in 'Search' widget should be displayed in linked 'Pump Price Update' widget
And Set Survey '2018-05-23' and '09:20' in Pump Price Update widget
And Add Own Pump Prices between '145.9' and '150.8' for own product
And Add Competitor Survey Prices between '145.9' and '150.8' for competitor products
When Pull "Pricing" widget for selected site to "Container3"
Then the site selected in 'Search' widget should be displayed in linked 'Pricing' widget
And Add "Competitor" option in "Pricing" Widget
And Verify <Comp Site Code> is displayed in "Competitor" in "Pricing" Widget
And Add "Pump Price" option in "Pricing" Widget




@Kalibrate_Retail_Pricing_E2E @Trigger_Price_Generation
Scenario Outline:: Kalibrate General Regression - Retail Pricing - Trigger Price Generation single & multiPricing widget
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container1"
When Pull "Pricing" widget for selected site to "Container2"
And Trigger Price Generation in 'Pricing' widget
Examples:
| ImportCode |
| G3 |


@Kalibrate_Retail_Pricing_E2E @Manual_Price_Generation
Scenario: Kalibrate General Regression - Retail Pricing - Trigger Price Generation single & multiPricing widget
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container1"
Then the site selected in 'Search' widget should be displayed in linked 'Site Manager' widget
When Pull "Pricing" widget for selected site to "Container2"
Then the site selected in 'Search' widget should be displayed in linked 'Pricing' widget
And Amend Manual Prices by '-0.45' and '+0.55' 
And Apply Manual Prices 
When Pull "MultiPricing" widget for selected site to "Container3"
Then the site selected in 'Search' widget should be displayed in linked 'Pricing' widget
And Select sites in MultiPricing Widget
And Amend Manual Prices by '-0.45' and '+0.55' 
And Select sites and Apply Manual Prices in 'MultiPricing' widget


@Kalibrate_Retail_Pricing_E2E @Sites_For_Review
Scenario: Kalibrate General Regression - Retail Pricing - Site for Review
    When Murphy Search widget filter applied with a 'Sites for review' filter of '<unchecked>'
    Then a filtered set of site results will be displayed matching the filter







