Feature: Create new user

  Scenario: Create a User using valid data
    Given a new User data
    When the User is added
    Then the User should be created
