@CreateAnAirport
Feature: Create An Airport
  As an administrator
  I want to create an airport record
  So that I can register flights against known airports

  Rules:
   - An airport must have a name consisting of exactly three alphabetic characters
   - An airport name must only consist of upper-case characters
   - No two airports can have the same name

  Scenario Outline: Create a valid airport
    When I create an airport with the name <name>
    Then an airport will exist with the name <name>
    Examples:
      | name |
      | DEN  |
      | DFW  |
      | LON  |
      | JPN  |
      | NCE  |

  Scenario Outline: Create an airport with an invalid name
    When I create an airport with the name <name>
    Then it should fail to create the airport
    Examples:
      | name    |
      | DE      |
      | DENN    |
      | TRIord9 |
      | 0.9     |
      | D3N     |
      | 123     |
      | D#N     |
      | dEN     |
      | den     |

  Scenario: Create two airports with the same name
    Given an airport exists with the name DEN
    And I create an airport with the name DEN
    Then it should fail to create the airport
