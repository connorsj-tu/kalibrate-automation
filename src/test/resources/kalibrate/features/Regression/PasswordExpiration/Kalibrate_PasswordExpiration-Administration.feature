@wip
Feature: Kalibrate - Password expiration - User Account Administration
  
  This set of scenarios test the adminstration of a users Password Expiration 
  fields in the Administrator Widget
    
	AC1 - User passwords should expire after a configurable amount of time (default 90 days)
	
  Background: 
    Given an 'admin' user is logged in and Kalibrate main page is displayed
    And the 'Administrator' Widget is displayed in workspace
    And the 'System Settings' card is selected 
    And 'User Account' item is selected

    
  Scenario: Maximum Password Age field present in Administrator widget
    
    This Scenario checks that the new 'Maximum Password Age' field has been
    added to the 'User Account' form within the 'System Settings' card of
    the 'Administrator' widget 

    Then a 'Maximum Password Age' item should be displayed with default value '90'
    And the existing set of 'User Account' items should be displayed


  Scenario: Maximum Password Age maximum value validation
    
    This Scenario checks that validation is performed on the new 'Maximum Password Age'
    value to ensure it is not greater then the maximum permitted value of '90'

		When the 'Maximum Password Age' is set to '95' 
		And the 'Save Changes' button is clicked
    Then the following error message should be displayed '\'Maximum Password Age\' cannot be more than 90 days'


  Scenario: Maximum Password Age minimum value validation
    
    This Scenario checks that validation is performed on the new 'Maximum Password Age'
    value to ensure it not less than the minimum permitted value of '1'

		When the 'Maximum Password Age' is set to '0'
		And the 'Save Changes' button is clicked 
    Then the following error message should be displayed '\'Maximum Password Age\' must be more than 0 days'


  Scenario: A valid 'Maximum Password Age' can be saved successfully
    
    This Scenario checks when a valid new 'Maximum Password Age' is saved then
    the correct message is displayed and the value is stored correctly

		When the 'Maximum Password Age' is set to '10' 
		And the 'Save Changes' button is clicked 
    Then the following success message should be displayed 'Changes saved successfully'
    And the value should be stored correctly in the database
