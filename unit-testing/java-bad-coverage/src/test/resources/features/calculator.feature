Feature: Calculator Operations
  As a user
  I want to perform basic arithmetic operations
  So that I can calculate numbers easily

  Scenario: Add two numbers
    Given I have a calculator
    When I add 5 and 3
    Then the result should be 8.0

  Scenario: Subtract two numbers
    Given I have a calculator
    When I subtract 4 from 10
    Then the result should be 6.0

  Scenario: Multiply two numbers
    Given I have a calculator
    When I multiply 6 and 7
    Then the result should be 42.0

  Scenario: Divide two numbers
    Given I have a calculator
    When I divide 10 by 2
    Then the result should be 5.0

  Scenario: Divide by zero
    Given I have a calculator
    When I divide 10 by 0
    Then an ArithmeticException should be thrown
