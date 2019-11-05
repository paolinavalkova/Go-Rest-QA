Feature: Smoke Test

  Scenario: working REST API
    Given I have a REST client
    When make a request
    Then the API response should be ok
    
  Scenario: Authentication is required
  	Given I have a REST client
  	When I make a request without access token
  	Then the request should fail
