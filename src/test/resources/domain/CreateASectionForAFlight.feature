@CreateASectionForAFlight
Feature: Create A Section For A Flight
  As an administrator
  I want to create a section for a flight
  So that I can divide flight seating into tiers

  Rules:
  - In a flight section, the total number of seats is equal to the number of rows times the number of columns
  - A flight section must have at least one seat
  - A flight section can contain at most 100 rows and 10 columns
  - There can only be one flight section of a particular seat class on a flight

  Scenario: Create a section for a flight
    Given flight JT123 exists
    When I create a section of class Economy, with 2 rows and 2 columns, on flight JT123
    Then flight JT123 should contain 1 sections

  Scenario: Create multiple sections for a flight
    Given flight JT123 exists
    When I create a section of class Economy, with 1 rows and 3 columns, on flight JT123
    And I create a section of class First, with 2 rows and 3 columns, on flight JT123
    Then flight JT123 should contain 2 sections

  Scenario: Create multiple sections of the same type on a flight
    Given flight JT123 exists
    When I create a section of class Economy, with 2 rows and 2 columns, on flight JT123
    And I create a section of class Economy, with 1 rows and 3 columns, on flight JT123
    Then it should fail to create the section
    And flight JT123 should contain 1 sections

  Scenario: Get number of seats
    Given flight JT123 exists
    When I create a section of class Economy, with 2 rows and 2 columns, on flight JT123
    Then flight JT123 should contain 1 sections
    And the created section should have 4 seats

  Scenario: Create a flight section with zero rows
    Given flight JT123 exists
    When I create a section of class Economy, with 0 rows and 2 columns, on flight JT123
    Then it should fail to create the section

  Scenario: Create a flight section with zero columns
    Given flight JT123 exists
    When I create a section of class Economy, with 2 rows and 0 columns, on flight JT123
    Then it should fail to create the section

  Scenario Outline: Create a flight section with exactly 100 seats
    Given flight JT123 exists
    When I create a section of class Economy, with <rows> rows and <columns> columns, on flight JT123
    Then flight JT123 should contain 1 sections
    And the created section should have 100 seats
    Examples:
      | rows | columns |
      | 1    | 100     |
      | 100  | 1       |
      | 2    | 50      |
      | 50   | 2       |
      | 20   | 5       |
      | 5    | 20      |
      | 10   | 10      |

  Scenario Outline: Create a flight section with more than 100 seats
    Given flight JT123 exists
    When I create a section of class Economy, with <rows> rows and <columns> columns, on flight JT123
    Then it should fail to create the section
    Examples:
      | rows | columns |
      | 1    | 101     |
      | 101  | 1       |
      | 2    | 51      |
      | 51   | 2       |
      | 21   | 5       |
      | 5    | 21      |
