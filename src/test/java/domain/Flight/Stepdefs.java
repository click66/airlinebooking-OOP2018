package domain.Flight;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs
{
    @Before
    public void before()
    {

    }

    @Given("^airline (.*) has designation (.*)$")
    public void airline_has_designation(String airlineName, String airlineDesignation) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I create flight (\\d+) under airline (.*)(?: for (\\d+)/(\\d+)/(\\d+))?$")
    public void i_create_flight_under_airline(int number, String airlineName, int date, int month, int year) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^a flight with flight no\\. (.*) will exist(?: for (\\d+)/(\\d+)/(\\d+))?$")
    public void a_flight_with_flight_no_will_exist(String flightNumber, int date, int month, int year) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^it should fail to create the flight$")
    public void it_should_fail_to_create_the_flight() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^two flights with flight no\\. (.*) will exist$")
    public void two_flights_with_flight_no_SW_will_exist(String flightNumber) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a flight can fly between airports (.*) and (.*)")
    public void a_flight_can_fly_between_airports(String origAirportName, String destAirportName) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I create flight (\\d+) between (.*) and (.*)")
    public void i_create_flight_between(int number, String origAirportName, String destAirportName) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^a flight is registered to fly between (.*) and (.*)$")
    public void a_flight_is_registered_to_fly_between(String origAirportName, String destAirportName) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
