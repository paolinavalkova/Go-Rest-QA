Feature: Get existing User

  Scenario: Get previously created User
    Given an existing user
    When I get a user with valid ID
    Then the user should exist
