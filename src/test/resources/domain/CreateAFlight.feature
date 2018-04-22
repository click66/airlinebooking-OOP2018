@CreateAFlight
Feature: Create A Flight
  As an administrator
  I want to create new flight
  So that there can be a schedule for what flights fly and from/to where

  Rules:
   - On a flight, originating and destination airports cannot be the same
   - A flight has a UFID (Universal Flight Identifier)

  Scenario: Create a new flight
    Given airline SWEST has designation SW
    When I create flight 1234 under airline SWEST
    Then a flight with flight no. SW1234 will exist

  Scenario: Create a flight with an invalid number
    Given airline SWEST has designation SW
    When I create flight 12345 under airline SWEST
    Then it should fail to create the flight

  Scenario: Create a flight with a duplicate flight number
    Given airline SWEST has designation SW
    When I create flight 1234 under airline SWEST for 01/01/2018
    And I create flight 1234 under airline SWEST for 01/01/2018
    Then it should fail to create the flight

  Scenario: Given a flight with a duplicate flight number
    Given airline SWEST has designation SW
    When I create flight 1234 under airline SWEST for 01/01/2018
    And I create flight 1234 under airline SWEST for 02/01/2018
    Then two flights with flight no. SW1234 will exist

  Scenario: Create a new flight between two airports
    Given a flight can fly between airports LGW and LAX
    When I create flight 1234 between LGW and LAX
    Then a flight is registered to fly between LGW and LAX

  Scenario: Create a flight between the same two airports
    Given a flight can fly between airports LGW and LAX
    When I create flight 1234 between LGW and LGW
    Then it should fail to create the flight

