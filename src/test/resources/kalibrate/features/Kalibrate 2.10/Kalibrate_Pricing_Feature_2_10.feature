#Author: gayathri.kesavakannan@kalibrate.com
#Keywords Summary : Kalibrate 2.10 Product Feature - Improvemet & User Stories
#Feature: Kalibrate 2.10 Product Feature SPRINT1
#Scenario: Kalibrate 2.10 Product Feature SPRINT1
#Kalibrate 2.10 SPRINT 1 includes below
# KP-2241 - Equalise height of widget columns
# KP-191 - User Profiles: Unassigned Primary Role
# KP-871 - AS a Retail Pricing Analyst I am able to see who leads who in the search widget at a glance when lead follow relations exist
# KP-2105 - Copy Proposed V Implemented Report (with the options) to Kalibrate Report Viewer
# KP-2139 - To split out the import code and site name in the export from Intel to make it possible to use pivot tables in Excel on Import Codes (Average Sales Volume chart only).
# KP-898 - As a Retail Pricing Analyst, I have a report whereby I can see key site targets over a flexible date range
# KP-2016 - Requirement: Update Site Manager with missing Product Group Level Setting


#Sample Feature Definition Template
@Kalibrate_Pricing_Feature_2_10
Feature: Kalibrate Pricing Feature 2 10
Kalibrate Pricing Feature 2.10 SPRINT1 includes
1. KP-2241 - Equalise height of widget columns
2. KP-191 - User Profiles: Unassigned Primary Role
3. KP-871 - AS a Retail Pricing Analyst I am able to see who leads who in the search widget at a glance when lead follow relations exist
4. KP-2105 - Copy Proposed V Implemented Report (with the options) to Kalibrate Report Viewer
5. KP-2139 - To split out the import code and site name in the export from Intel to make it possible to use pivot tables in Excel on Import Codes (Average Sales Volume chart only).
6. KP-898 - As a Retail Pricing Analyst, I have a report whereby I can see key site targets over a flexible date range
7. KP-2016 - Requirement: Update Site Manager with missing Product Group Level Setting
Background: The user is already logged in with a new workspace
    Given a 'Retail Pricing Analyst' is logged in and Kalibrate main page is displayed
    And User navigates to "Retail Pricing" workspace
		
		When the user is on the 'Tools' widgets category
		Then the user drags the following widgets into their respective containers:
		  | containerNumber | widgetName    |
		  |               1 | Search        |
		  
@Kalibrate_Pricing_Feature_2_10 @KP_2105_Report_Viewer_Proposedvs_Impleted_Report 
Scenario Outline: Verify for new report Proposed vs Implemented report

And Search & select for "<ImportCode>" ownsite
Then Pull "Report Viewer" widget for selected site to "Container2"
And Open "Daily Operation" option in "Report Viewer" widget
Then Export data from "Report Viewer" Widget
Examples:
 | ImportCode |  
 | K002 |

@Kalibrate_Pricing_Feature_2_10 @KP_898_Report_Viewer_Key_Site_Target
Scenario Outline: KP-898 - As a Retail Pricing Analyst, I have a report whereby I can see key site targets over a flexible date range 
And Search & select for "<ImportCode>" ownsite
Then Pull "Report Viewer" widget for selected site to "Container2"
And Open "Key Site Target" option in "Report Viewer" widget
Then Export data from "Report Viewer" Widget
Examples:
 | ImportCode |  
 | K002 |

@Kalibrate_Pricing_Feature_2_10 @KP_2139_Intel_Avg_Sales_volume_Split_name_code
Scenario Outline: KP_2139_Intel_Avg_Sales_volume_Split_name_code 
And Search & select for "<ImportCode>" ownsite
 Then Pull "Intel" widget for selected site to "Container2"
And Open "Average Sales Volume" chart in "Standard Chart" of Intel widget
Then Export data from "Intel" Widget
Examples:
 | ImportCode |  
 | K002 |
    
@Kalibrate_Pricing_Feature_2_10 @KP_871_Leadandfollow_in_Search
Scenario Outline: Kalibrate_Pricing_Feature_2_10 KP- 871 Lead & follow tool tip in Search Widget 
K002 has Lead Site as K003  & Followed Site as K005 
#And Search & select for "<ImportCode>" ownsite
#When Pull "Site Manager" widget for selected site to "Container2"
#And Open "Lead & Follow" option in "Site Manager" widget
#Then Assign "<LeadImportCode>" as "ProductGroup" LEAD Sites
#And Close "Site Manager" widget
##And Search & select for "<FollowImportCode>" ownsite
#When Pull "Site Manager" widget for selected site to "Container2"
#And Open "Lead & Follow" option in "Site Manager" widget
#Then Assign "<ImportCode>" as "Product" LEAD Sites
And Search & select for "<ImportCode>" ownsite
And Hover & Verify for Lead Follow tool tip
  Examples:
| ImportCode |LeadImportCode | FollowImportCode |
| K005 | K003 | K005 |


@Kalibrate_Pricing_Feature_2_10 @KP_2241_Equalise_height_of_widget_columns
Scenario Outline: Kalibrate_Pricing_Feature_2_10 Workspace Height of Widget column
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container2"
When Pull "Pricing" widget for selected site to "Container2"
When Pull "MultiPricing" widget for selected site to "Container2"
When Pull "Notes" widget for selected site to "Container2"
When Pull "Site Strategy" widget for selected site to "Container2"
And  Close "Search" widget
And Take Screenshot for feature
When Pull "Search" widget to "Container2"
And Search & select for "<ImportCode>" ownsite
And Scroll "Down" Kalibrate
When Pull "Site Manager" widget for selected site to "Container1"
When Pull "Site Metrics" widget for selected site to "Container1"
And Take Screenshot for feature
# Delete the workspace
#    When User deletes the created Workspace
#  Then The created workspace is deleted

@Kalibrate_Pricing_Feature_2_10 @KP_191_User_Profiles_Unassigned_Primary_Role
Scenario Outline: Kalibrate_Pricing_Feature_2_10 Primary Role is Removed we have only Pricing Role by default
Then Pull "Administrator" widget to "Container2"
Then Open "User Profiles" option in "Administrator" widget
And Create new profile "TESTPROFILE" 
Then Open "Users" option in "Administrator" widget
And Create new user "USER1" and  "letmein123" for profile "QAPROFILE"  
When User Logout from Kalibrate
Then User is returned to Login Page
And a valid username "USER1" and a valid password "letmein123" are entered 
And the Login button is clicked
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container1"
And Pull "Pricing" widget for selected site to "Container1"
And Pull "MultiPricing" widget for selected site to "Container3"
And Pull "Intel" widget for selected site to "Container3"
And Pull "Map" widget for selected site to "Container1"
# Delete the workspace
#    When User deletes the created Workspace
#  Then The created workspace is deleted
Examples:
 | ImportCode |  
    | K005 |



@Kalibrate_Pricing_Feature_2_10 @KP_2016_Lead_Follow_Settings
Scenario Outline: Kalibrate_Pricing_Feature_2_10 KP_2016_Lead_Follow_Settings
And Search & select for "<ImportCode>" ownsite
When Pull "Site Manager" widget for selected site to "Container2"
And Open "LEAD AND FOLLOW" option in "Site Manager" widget
Then Assign "<LeadImportCode>" as LEAD Sites
And Open "Product Group" option in "Site Manager" widget
And Set "Auto Implement" for "Lead Follow" for <Product Groups> Product Group
Examples:
| ImportCode |LImportCode | 
| K002 | K003 | 






    
