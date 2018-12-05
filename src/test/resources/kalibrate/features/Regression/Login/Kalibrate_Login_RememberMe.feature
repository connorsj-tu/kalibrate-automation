@wip
Feature: Kalibrate Login Screen - Remember Me
  Scenarios used to test the Remember Me function regarding logins

  @kalibrate @login @remember_me
  Scenario: If no one has logged in previously then username should not be populated
    Given the previous logged in user had ticked 'Remember Me'
    And the user has logged out
    When login page is displayed
    Then the username should be populated

  @kalibrate @login @remember_me
  Scenario: If the previous user had logged in with Remember Me ticked then username should be populated
    Given the previous logged in user had unticked 'Remember Me'
    And the user has logged out
    When login page is displayed
    Then the username should not be populated

  @kalibrate @login @remember_me
  Scenario: If the previous user had logged in with Remember Me not ticked then username should not be populated
    Given the previous logged in user had ticked 'Remember Me'
    And the user has logged out
    When login page is displayed
    Then the username should be populated
