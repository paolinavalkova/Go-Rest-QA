Feature: Delete User

  Scenario: Delete existing User
    Given an User to delete
    When I deleted the user
    Then the user shouldn't exist
