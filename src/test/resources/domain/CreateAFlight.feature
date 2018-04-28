@CreateAFlight
Feature: Create A Flight
  As an administrator
  I want to create new flight
  So that there can be a schedule for what flights fly and from/to where

  Rules:
   - On a flight, originating and destination airports cannot be the same
   - A flight has a GUFI (Globally Unique Flight Identifer)

  Scenario: Create a new flight
    Given airline SWEST has designation SW
    When I create flight 1234 under airline SWEST for 01/01/2018
    Then a flight with flight no. SW1234 will exist

  Scenario: Create a flight with an invalid number
    Given airline SWEST has designation SW
    When I create flight 12345 under airline SWEST for 01/01/2018
    Then it should fail to create the flight

  Scenario: Create a flight with a duplicate flight number, on the same day (invalid)
    Given airline SWEST has designation SW
    When I create flight 1234 under airline SWEST for 01/01/2018
    And I create flight 1234 under airline SWEST for 01/01/2018
    Then it should fail to create the flight

  Scenario: Create a flight with a duplicate flight number, on different days (valid)
    Given airline SWEST has designation SW
    When I create flight 1234 under airline SWEST for 01/01/2018
    And I create flight 1234 under airline SWEST for 02/01/2018
    Then it should successfully create the flight
    And 2 flights with flight no. SW1234 will exist

  Scenario: Create a new flight between two airports
    Given LGW is an airport
    And LAX is an airport
    When I create flight 1234 between LGW and LAX
    Then it should successfully create the flight

  Scenario: Create a flight between the same two airports
    Given LGW is an airport
    When I create flight 1234 between LGW and LGW
    Then it should fail to create the flight

