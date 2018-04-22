@CreateAnAirline
Feature: Create An Airline
  As an administrator
  I want to create an airline record
  So that I can register flights against known airlines

  Rules:
   - An airline has a name that must have a length less than 6
   - An airline name must only consist of upper-case alphabetic characters
   - No two airlines can have the same name

  Scenario Outline: Create a valid airline
    When I create an airline with the name <name>
    Then an airline called <name> will exist
    Examples:
      | name  |
      | DELTA |
      | AMER  |
      | JET   |
      | FRONT |
      | NCE   |

  Scenario Outline: Create an airline with an invalid name
    When I create an airline with the name <name>
    Then it should fail to create the airline
    And an airline called <name> will not exist
    Examples:
      | name     |
      | QANTAS   |
      | FRONTIER |
      | 123456   |
      | TRIM9    |
      | AA#$%    |

  Scenario: Create two airlines with the same name
    Given an airline exists with the name SWEST
    And I create an airline with the name SWEST
    Then it should fail to create the airline
