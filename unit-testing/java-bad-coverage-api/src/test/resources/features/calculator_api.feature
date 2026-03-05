Feature: Calculator API Operations
  As a user
  I want to call the Calculator REST API with two numbers and a symbol operator
  So that I can compute arithmetic results

  Scenario: Add two numbers
    Given I have the calculator API
    When I send a POST request with a=5, b=3, and operation="+"
    Then the response result should be 8.0

  Scenario: Subtract two numbers
    Given I have the calculator API
    When I send a POST request with a=10, b=4, and operation="-"
    Then the response result should be 6.0

  # NOTE: multiply, divide, divide-by-zero NOT covered – intentional bad coverage
