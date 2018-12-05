#@kalibrate @retail_pricing @data_manager
#Feature: Kalibrate Automation Retail- Data Manager Setup & Test Environment Configuration
#This Task/Scenario is used to create Data Manager Setup & Test Environment Configuration
#1. Competitor Data Sources
#2. Custom Display Data
#3. Aggregated Cost Type 
#4. New Sales Type
#5. Site Grouping
#6. Extended Properties
#7. System Codes - Alternate Import Codes
#8. Payment Offset 
#9. Products & Product Groups 



Feature: Kalibrate_Data_Manager_Setup_Configuration
@kalibrate @retail_pricing @data_manager @setup_configuration_in_datamanager
  Scenario: 
Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
When User creates a new Workspace named 'Retail Pricing' 
When Pull "Data Manager" widget to "Container2"
Then Open "Sales Types" option in "Data Manager" widget
And Create a new Sales Breakdown <Sales BreakDown>
| QA_Sales_BreakDown |
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


		# Delete the workspace
    When User deletes the created Workspace
    Then The created workspace is deleted
    
    # Logout and close browser
    Given Kalibrate main page is displayed
    When User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed
    
    
