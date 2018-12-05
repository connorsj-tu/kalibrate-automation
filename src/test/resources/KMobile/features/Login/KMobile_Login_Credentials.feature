Feature: KMobile Login - Credentials
  Scenarios used to test the validation of the user credentials entered

  @KMobile @login @credentials @regression
  Scenario: It is not possible to login when username and password are not provided
    Given the KMobile Login page is displayed
    When a valid username a valid password are entered and privacy policy checked 
    And the Login button is tapped
    Then kMobile main page is displayed