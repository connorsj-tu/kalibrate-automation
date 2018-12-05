@kalibrate @dealer_pricing
Feature: DTA General Setup - Configuring or Setup Dealer Trade Area
#### THIS FEATURE WILL BE UNDER EPICS-184 - Kalibrate Dealer Pricing - Configuring or Setup DTA
## STEPS AS FOLLOWS: 
## 1. CREATE 5 DEALER TRADE AREA 
## Note: 1a - DealerPricingWidgetsDefaultOptions - Site Manager Add all options in Site Mananger to support following steps 
## 2. ASSIGN & ACTIVATE PRODUCTS & PRODUCTGROUPS
## 3. ADD BASE COST & ADDITIONAL COST
## 4. ASSOCIATE DEALER SITES & COMPETITOR SITES
## 5. CONFGURE DTA PRICING BASED ON DTA NAME
## 6. CONFIGURE PRICING RULES IN DTA STRATEGY
## 7. TRIGGER DTA PRICE GENERATION
## NOTE: ADD COST HISTORY MANUALLY OR IMPORT 




Background: The user is already logged in with a new workspace
    Given the user is logged in and Kalibrate main page is displayed
    #When the 'Add Workspace' workspace is selected from workspace menu
    #Then the 'New Workspace' workspace is displayed
    And User navigates to "Retail Pricing" workspace

@kalibrate @dealer_pricing @DTARegression
Scenario Outline: Dealer Pricing Regression
#Given the user is logged in and Kalibrate main page is displayed
#When User creates a new Workspace named 'Dealer Pricing' 
#And User navigates to "Dealer Pricing" workspace
When Pull "Site Manager" widget to "Container2"
Then User creates a new Dealer Trade Area with "<AreaName>" and "<ImportCode>"
Given User can search & select DTA "<AreaName>" 
And Pull "Site Manager" widget for selected DTA
When Select all DTA Options are Default DTA Site Manager options 
#When Go to Products tab & assign Products
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
And Search & Add Select Competitor sites <Comp Site Code> to DTA
| C021 |
| C022 |
When Go to 'Dealer Pricing - Pricing' Tab
And Set NSP Delta "1.256" & Calculation method "Average"
And Configure Editable DTW
And Configure DTW Pricing Method
And Add DTA Margin value "10.25"
When Pull "Pricing" widget for selected DTA
Then Trigger Price Generation for selected DTA
# Delete the workspace
When User deletes the created Workspace
Then The created workspace is deleted
# Logout and close browser
#Given Kalibrate main page is displayed
When User Logout from Kalibrate
Then User is returned to Login Page
And The browser is closed  
Examples:
   | AreaName | ImportCode | 
   | TradeArea 01 | Area01 | 
#   | DTA02 | DTA02 | 
 #  | DTA03 | DTA03 | 
  # | DTA04 | DTA04 | 
   

@kalibrate @dealer_pricing @createDTA
Scenario Outline: CreateDTA
Given the user is logged in and Kalibrate main page is displayed
When User creates a new Workspace named 'Dealer Pricing' 
And User navigates to "Dealer Pricing" workspace
When Pull "Site Manager" widget to "Container2"
Then User creates a new Dealer Trade Area with "<AreaName>" and "<ImportCode>"
#And User can search & select DTA "<AreaName>"
Examples:
   | AreaName | ImportCode | 
#    |   Street Back Net DTW   |  SN01  |  
#    |   Street Back Gross DTW   |  SG01  |  
# 		|  	Cost Based  Net DTW |  CN01  |  
#    |   Cost Based  Gross DTW |  CG01  |  
#    |  TradeArea01 |  Area01  |  
	| DTA02 | DTA02 |

@kalibrate @dealer_pricing @widgets_default_options
Scenario Outline: WidgetsDefaultOptions
Given the user is logged in and Kalibrate main page is displayed 
And User navigates to "Dealer Pricing" workspace
Given User can search & select DTA "<AreaName>" 
And Pull "Site Manager" widget for selected DTA
When Select all DTA Options are Default DTA Site Manager options  
#When Pull "Pricing" widget for selected DTA
#When User adds <Pricing Default options> to DTA Pricing widget 
Examples:
    | AreaName | 
   # |  SN01   | 
	| DTA02 | 



      	
@kalibrate @dealer_pricing @AssignDTAProduct&Groups
Scenario Outline: AssignDTAProduct&Groups
Given the user is logged in and Kalibrate main page is displayed 
	And User navigates to "Dealer Pricing" workspace
Given User can search & select DTA "<AreaName>" 
And Pull "Site Manager" widget for selected DTA
When Go to Products tab & assign Products
#And Goto Product groups tab & assign activate groups
#Then Pull "Pricing" widget for selected DTA
Examples:
    | AreaName | 

#    |  SN01   | 
#    |  SG01   |  
#    |  CN01   | 
#    |  CG01   |  
#    |  Area01   |
	| DTA02 |

@kalibrate @dealer_pricing @Kalibrate-DealerPricing_Add_Cost_Formula @3.ADD_BASE_COST_&_ADDITIONAL_COST
Scenario Outline: 3. ADD BASE COST & ADDITIONAL COST
Given the user is logged in and Kalibrate main page is displayed 
And User navigates to "Dealer Pricing" workspace
Given User can search & select DTA "<AreaName>" 
And Pull "Site Manager" widget for selected DTA
When Go to 'Dealer Pricing - Costs' Tab
And Configure Base Cost as "TOTALCOSTS + RETAILTAX"
And Configure Additional Cost as "(STREETPRICE / (1 + RETAILTAX)) * RETAILTAX "
Examples:
    | AreaName | 
   # |  SN01   | 
   # |  SG01   |  
   # |  CN01   | 
   # |  CG01   |  
   # |  Area01   |
   	| DTA02 |

@kalibrate @dealer_pricing @Kalibrate-DealerPricing_ASSOCIATE_DEALER_SITES_&_COMPETITOR_SITES @4.ASSOCIATE_SITES 
Scenario Outline: 4. ASSOCIATE DEALER SITES & COMPETITOR SITES
Given the user is logged in and Kalibrate main page is displayed 
	And User navigates to "Dealer Pricing" workspace
Given User can search & select DTA "<AreaName>" 
And Pull "Site Manager" widget for selected DTA
And Go to 'Dealer Pricing - Add New Sites' Tab
And Search & Add Select own sites <Own Site Code> to DTA
| K021 |
| K022 |
| K023 |
| K024 |
| K025 |
| K026 |
And Search & Add Select Competitor sites <Comp Site Code> to DTA
| C021 |
| C022 |
| C023 |
| C024 |
| C025 |
| C026 |

Examples:
| AreaName | 
#|  SN01   | 
#|  SG01   | 
#|  CN01   | 
#|  CG01   | 
#|  Area01 | 
	| DTA02 |


@kalibrate @dealer_pricing @Kalibrate-DealerPricing_Configure_Pricing_Methods @5.CONFIGURE_DTA_PRICING_BASED_ON_DTA_NAME 
Scenario Outline: 5. CONFIGURE DTA PRICING BASED ON DTA NAME
Given the user is logged in and Kalibrate main page is displayed 
	And User navigates to "Dealer Pricing" workspace
Given User can search & select DTA "<AreaName>" 
And Pull "Site Manager" widget for selected DTA
When Go to 'Dealer Pricing - Pricing' Tab
And Set NSP Delta "1.256" & Calculation method "Average"
And Configure Editable DTW
And Configure DTW Pricing Method
And Add DTA Margin value "10.25"

Examples:
    | AreaName | 
 #   |  SN01   | 
    #|  SG01   |  
    #|  CN01   | 
   #|  CG01   |  
#  |  Area01   |
	| DTA02 |

@kalibrate @dealer_pricing @Kalibrate-DealerPricing_Trigger_Price_Generation @7.TRIGGER_DTA_PRICE_GENERATION 
Scenario Outline: 7. TRIGGER DTA PRICE GENERATION
Given the user is logged in and Kalibrate main page is displayed 
	And User navigates to "Dealer Pricing" workspace
Given User can search & select DTA "<AreaName>" 
When Pull "Pricing" widget for selected DTA
Then Trigger Price Generation for selected DTA

Examples:
    | AreaName | 
#   |  SN01   | 
#  |  SG01   |  
#  |  CN01   | 
#  |  CG01   |  
#  |  Area01   |
  	| DTA02 |


@kalibrate @dealer_pricing @Kalibrate-DealerPricing_DeleteWorkspace @8.DealerPricing_DeleteWorkspace 
Scenario Outline: 8. DealerPricing_DeleteWorkspace
		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    Given Kalibrate main page is displayed
    When User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed  
  
  
  
@kalibrate @dealer_pricing @Kalibrate-DealerPricingConfigureDTWPricingRULE @DTWPricingRULE  
Scenario Outline: ConfigureDTWPricingMethod&DTWType
Given the user is logged in and Kalibrate main page is displayed 
And User navigates to "Dealer Pricing" workspace
Given User can search & select DTA "<AreaName>" 
And Pull "Dealer Trade Strategy" widget for selected DTA
And Configure Product level Price generation rule "DTW"
And Configure Product level validation rule "Unconstrained Price Rounding - P "
And Configure Product Group level "Auto Implement - PG"
Examples:
    | AreaName | 
  	| DTA02 |
   
   

# Kalibrate 2.9 Dealer Pricing DTW Expectation 


@kalibrate @dealer_pricing @Kalibrate-DealerPricing2.9Kp-274DeleteDTA @Kalibrate-DealerPricing2.9Kp-274DeleteDTA
Scenario Outline: 2.9 KP-274 Delete Dealer Trade Area 
Given the user is logged in and Kalibrate main page is displayed 
And User navigates to "Dealer Pricing" workspace
Given User can search & select DTA "<AreaName>" 
And Pull "Site Manager" widget for selected DTA
And Pull "Pricing" widget for selected DTA
When Pull "Administrator" widget to "Container3"
And Open Deletion & Search for "<AreaName>" in Administrator widget
Then Delete DTA "<AreaName>" 
And Verify "<AreaName>" deleted in Search widget
Examples:
    | AreaName | 
    | DTA02 |
    
    

