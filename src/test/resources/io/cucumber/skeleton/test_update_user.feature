Feature: Update User

  Scenario: Update User address
    Given an User with wrong address
    When the address is updated
    Then the User should have the new address
