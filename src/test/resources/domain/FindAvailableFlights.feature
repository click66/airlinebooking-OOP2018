@FindAvailableFlights
Feature: Find Available Flight
  As a user
  I want to query for all available flights
  So that I can see what flights have available seats

  Rules:


  Scenario: Find a seat of any class
    Given a flight has a 5x5 Economy class section
    And I query for available flights of any class
    Then it will find the flight

  Scenario: Find a seat of business class
    Given a flight has a 5x5 Business class section
    And I query for available flights of Business class
    Then it will find the flight

  Scenario Try to find a seat in business class when there are only economy seats available
    Given a flight has a 5x5 Economy class section
    And I query for available flights of Business class
    Then it will not find the flight
