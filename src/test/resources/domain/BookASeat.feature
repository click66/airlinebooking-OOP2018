@BookASeat
Feature: Book A Seat
  As a user
  I want to book a seat on a flight
  So that I can see what flights have available seats

  Rules:
    - Before a seat can be booked, it must be available

  Scenario: Check availability of seat
    Given a flight has a 5x5 Economy class section
    Then seat 1A in Economy will be available

  Scenario: Book a seat in business class
    Given a flight has a 5x5 Business class section
    When I book seat 1A in Business
    Then seat 1A in Economy will be booked

  Scenario: Book two seats in economy class
    Given a flight has a 5x5 Economy class section
    When I book seat 1A in Economy
    And I book seat 1B in Economy
    Then seat 1A in Economy will be booked
    And seat 1B in Economy will be booked

  Scenario: Book two seats, one in economy and one in business
    Given a flight has a 2x2 Economy class section
    And a flight has a 2x2 Business class ection
    When I book seat 1A in Economy
    And I book seat 1A in Business
    Then seat 1A in Economy will be booked
    And seat 1A in Business will be booked

  Scenario: Book one seat in economy, none in business
    Given a flight has a 2x2 Economy class section
    And a flight has a 2x2 Business class ection
    When I book seat 1A in Economy
    Then seat 1A in Economy will be booked
    And seat 1A in Business will be available

  Scenario: Book a seat that is already booked
    Given a flight has a 2x2 Economy class section
    When I book seat 1A in Economy
    And I try and book seat 1A in Economy
    Then the booking should fail
