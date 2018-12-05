@kalibrate @login @regression 
Feature: Kalibrate Login - Credentials
  Scenarios used to test the validation of the user credentials entered

  @positive @pullrequest
  Scenario: It is possible to login if valid credentials are provided
    Given the Kalibrate Login page is displayed
    When a 'Retail Pricing Analyst' valid username and valid password are entered
    And the Login button is clicked
    Then Kalibrate main page is displayed
      
    # Logout and close browser
    When Kalibrate main page is displayed
    And User Logout from Kalibrate
    Then User is returned to Login Page
    And The browser is closed  
     
  Scenario: It is not possible to login when username and password are not provided
    Given the Kalibrate Login page is displayed
    When no username and no password are entered
    And the Login button is clicked
    Then the following error message should be displayed 'Please enter a username and password'

  Scenario: It is not possible to login when only a username is provided
    Given the Kalibrate Login page is displayed
    When a 'Retail Pricing Analyst' valid username and no password are entered
    And the Login button is clicked
    Then the following error message should be displayed 'Please enter a username and password'

  Scenario: It is not possible to login when only a password is provided
    Given the Kalibrate Login page is displayed
    When a 'Retail Pricing Analyst' no username and valid password are entered
    And the Login button is clicked
    Then the following error message should be displayed 'Please enter a username and password'

  Scenario: It is not possible to login if invalid credentials are provided
    Given the Kalibrate Login page is displayed
    When a 'Retail Pricing Analyst' valid username and an invalid password are entered
    And the Login button is clicked
    Then the following error message should be displayed 'The entered details were incorrect. Please try again.'

 
   