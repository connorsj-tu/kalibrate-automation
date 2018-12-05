@wip
Feature: Kalibrate - Password expiration - Expiration Notification
  
  This set of scenarios tests the password is to expire imminently  
  notification
    
	AC2 - Add notification that password is to expire imminently (5 days before)
	

  Scenario: Password 6 days before expiration
    
    This Scenario will check that no password expiry message will be diplayed
    when a user logs in whos password is set to expire in 6 days 

    Given a user is setup with a password that will expire in '6' days
    And the Login page is displayed
    When the user credentials are entered
    And the Login button is clicked
    Then the main page is displayed
    And no password expiration message should be displayed


  Scenario: Password 5 days before expiration
    
    This Scenario will check that a password expiry message will be diplayed
    when a user logs in whos password is set to expire in 5 days 

    Given a user is setup with a password that will expire in 5 days
    And the Login page is displayed
    When the user credentials are entered
    And the Login button is clicked
    Then the main page is displayed
    And the following password expiration message should be displayed 'Password will expire in 5 days'
    
    
  Scenario: Password 1 days before expiration
    
    This Scenario will check that a password expiry message will be diplayed
    when a user logs in whos password is set to expire in 1 day 

    Given a user is setup with a password that will expire in 1 day
    And the Login page is displayed
    When the user credentials are entered
    And the Login button is clicked
    Then the main page is displayed
    And the following password expiration message should be displayed 'Password will expire in 1 day'
    

  Scenario: Password 0 days before expiration
    
    This Scenario will check that a password expiry message will be diplayed
    when a user logs in whos password is set to expire in 0 days

    Given a user is setup with a password that will expire in 0 days
    And the Login page is displayed
    When the user credentials are entered
    And the Login button is clicked
    Then the main page is displayed
    And the following password expiration message should be displayed 'Password has expired'
    When the password expiration message is dismissed 
    Then the change password form is displayed
    

  Scenario: Password 1 days after expiration
    
    This Scenario will check that a password expiry message will be diplayed
    when a user logs in whos password is set to expire in 0 days

    Given a user is setup with a password that will expire in 0 days
    And the Login page is displayed
    When the user credentials are entered
    And the Login button is clicked
    #Then the login will be declined ???
    And the following password expiration message should be displayed 'Password has expired'
