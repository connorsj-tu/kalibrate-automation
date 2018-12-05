#Author: gayathri.kesavakannan@kalibrate.com
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
@Kalibrate_E2E_Demo
Feature: Kalibrate Retail Pricing E2E Automation Suite
Kalibrate Retail Pricing E2E Automation Suite below steps are performed 
Background: The user is already logged in with a new workspace
    Given the user is logged in and Kalibrate main page is displayed
    #When the 'Add Workspace' workspace is selected from workspace menu
    #Then the 'New Workspace' workspace is displayed
    And User navigates to "Demo" workspace


@Kalibrate_E2E_Demo @Retail_Pricing
Scenario Outline: Kalibrate General Regression - Retail Pricing - 
Setup new Own Site, 
Assign and activate  Products, 
Create new Comp Site, 
Add Comp site to Own Site 
Add Own Site Competitors to own site
Add Pump /Survey Price
Trigger pricing Generation
When Pull "Site Manager" widget to "Container2"
Then User creates a new Own Site with "<SiteName>" and "<ImportCode>" and "<Brand>",  "<Area>", "<Network>"
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container2"
And  Open "Products" option in "Site Manager" widget
When Assign Products <Product>
| Regular |
| Mid-Grade |
| Premium |
| Diesel |
When  "Activate" Products <Product>
| Regular |
| Mid-Grade |
| Premium |
| Diesel |
And  Open "Product Groups" option in "Site Manager" widget
Then User creates a new Competitor Site with "<CSiteName>" and "<CImportCode>" and "<Brand>",  "<CompetitorGroup>"
And  Close "Search" widget
Then Pull "Search" widget to "Container1"
And Search & select for "<ImportCode>" ownsite 
When Pull "Site Manager" widget for selected site to "Container2"
And  Open "Local Market" option in "Site Manager" widget
And Search & Add Competitor sites <Comp Site Code> to OwnSite Market
| C001 |
| C002 |
| CS101 |
And Search & Add Own Site Competitor sites <Own Site Comp Code> to OwnSite Market
| K001 |
| K006 |
When Pull "Pump Price Update" widget for selected site to "Container1"
And Add Own Pump Price between "145.9" and "150.9"
When Pull "Pricing" widget for selected site to "Container2"
And Trigger Price Generation in 'Pricing' widget
When Pull "MultiPricing" widget for selected site to "Container3"
Then Export data from "MultiPricing" Widget
Then Export data from "Pricing" Widget
Then Pull "Intel" widget for selected site to "Container2"
And Open "Average brand price" chart in "Standard Chart" of Intel widget
Then Export data from "Intel" Widget
 When User deletes the created Workspace
 Then The created workspace is deleted 
Examples:
   | SiteName | ImportCode |  Brand | Area | Network | CompetitorGroup |CSiteName |CImportCode|
   | OwnSite 101 | OS101 | Papa | East | North | KSS Competitor |Comp Site 101 | CS101|



@Kalibrate_E2E_Demo @Dealer_Pricing
Scenario Outline: Dealer Pricing Regression
When Pull "Site Manager" widget to "Container2"
Then User creates a new Dealer Trade Area with "<AreaName>" and "<ImportCode>"
Given User can search & select DTA "<AreaName>" 
And Pull "Site Manager" widget for selected DTA
When Select all DTA Options are Default DTA Site Manager options 
And  Open "Products" option in "Site Manager" widget
When Assign Products <Product>
| Regular |
| Mid-Grade |
| Premium |
| Diesel |
When  "Activate" Products <Product>
| Regular |
| Mid-Grade |
| Premium |
| Diesel |
And  Open "Product Groups" option in "Site Manager" widget
When Go to 'Dealer Pricing - Costs' Tab
And Configure Base Cost as "TOTALCOSTS + RETAILTAX"
And Configure Additional Cost as "(STREETPRICE / (1 + RETAILTAX)) * RETAILTAX "
And Go to 'Dealer Pricing - Add New Sites' Tab
And Search & Add Select own sites <Own Site Code> to DTA
| K021 |
| K022 |
| K023 |
| K024 |
| K025 |
| K026 |
| OS101 |
And Search & Add Select Competitor sites <Comp Site Code> to DTA
| C021 |
| C022 |
| CS101 |
When Go to 'Dealer Pricing - Pricing' Tab
And Set NSP Delta "1.256" & Calculation method "Average"
And Configure Editable DTW
And Configure DTW Pricing Method
And Add DTA Margin value "10.25"
When Pull "Pricing" widget for selected DTA
Then Trigger Price Generation for selected DTA
Examples:
   | AreaName | ImportCode | 
   |  DTA02 | DTA02 | 

   
@Kalibrate_E2E_Demo @Data_Manager
Scenario: 
When Pull "Data Manager" widget to "Container2"
Then Open "Sales Types" option in "Data Manager" widget
And Create a new Sales Breakdown <Sales BreakDown>
| Demo_Sales_BreakDown |
Then Open "Data Sources" option in "Data Manager" widget
And Create new data sources visible in Pricing & Map widget <Data Source> 
| Kalibrate |
| KMOBILE |
| Virtual |
| CSV |
| XML |
| User Input |
Then Open "Cost Types" option in "Data Manager" widget
And Create a new Cost types <Cost Breakdown>
| QA_CostBreak_01 |
| QA_CostBreak_02 |
And Create a new aggregated Cost type 
| AggregateCostName | AggregatedCostFormula | 
| QA_Aggregate_Cost | QA_CostBreak_01 + QA_CostBreak_02 |
And Flag the Cost types to display in Pricing widget
Then Open "Custom Display Data" option in "Data Manager" widget
And Create new custom data visible in Pricing widget 
| CustomData | CustomDataFormula | 
| Custom_QA_Price | CURRENT_PRICE+LAST_PROPOSED+DISPLAY_PRICE+PUMP_PRICE |
| Custom_QA_Cost | QA_CostBreak_01+QA_CostBreak_02+TOTALCOSTS |

@Kalibrate_E2E_Demo @KP_191_User_Profiles_Unassigned_Primary_Role
Scenario Outline: Kalibrate_Pricing_Feature_2_10 Primary Role is Removed we have only Pricing Role by default
Then Pull "Administrator" widget to "Container2"
Then Open "User Profiles" option in "Administrator" widget
And Create new profile "DEMOPROFILE" 
Then Open "Users" option in "Administrator" widget
And Create new user "USER101" and  "letmein123" for profile "DEMOPROFILE"  
When User Logout from Kalibrate
Then User is returned to Login Page
And a valid username "USER101" and a valid password "letmein123" are entered 
And the Login button is clicked
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container1"
And Pull "Pricing" widget for selected site to "Container1"
And Pull "MultiPricing" widget for selected site to "Container3"
And Pull "Intel" widget for selected site to "Container3"
And Pull "Map" widget for selected site to "Container1"
Examples:
 | ImportCode | 
 | K003 | 