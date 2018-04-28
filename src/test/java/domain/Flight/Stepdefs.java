package domain.Flight;

import application.Repository.AirlineRepository;
import application.Repository.AirportRepository;
import application.Repository.FlightRepository;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.Airline.Airline;
import domain.Airport.Airport;
import org.junit.Assert;

import java.util.UUID;

public class Stepdefs
{
    private domain.Airline.Repository airlineRepository;

    private domain.Airport.Repository airportRepository;

    private Repository flightRepository;

    private GUFI gufi;

    @Before
    public void before()
    {
        airlineRepository = new AirlineRepository();
        airportRepository = new AirportRepository();

        flightRepository = new FlightRepository();

        gufi = GUFI.randomGUFI();
    }

    @Given("^airline (.*) has designation (.*)$")
    public void airline_has_designation(String airlineName, String airlineDesignation) {
        airlineRepository.store(
            new Airline(
                airlineRepository,
                UUID.randomUUID(),
                new domain.Airline.Name(airlineName),
                new domain.Airline.Designation(airlineDesignation)
            )
        );
    }

    @Given("^a flight can fly between airports (.*) and (.*)")
    public void a_flight_can_fly_between_airports(String origAirportName, String destAirportName) {
        airportRepository.store(
            new Airport(
                airportRepository,
                UUID.randomUUID(),
                new domain.Airport.Name(origAirportName)
            )
        );

        airportRepository.store(
            new Airport(
                airportRepository,
                UUID.randomUUID(),
                new domain.Airport.Name(destAirportName)
            )
        );
    }

    @When("^I create flight (\\d+) under airline (.*) for (.*)/(.*)/(.*)$")
    public void i_create_flight_under_airline(int number, String airlineName, String date, String month, String year) {
        Airline airline = airlineRepository.fetchByName(new domain.Airline.Name(airlineName));

        Airport origin = new Airport(
            airportRepository,
            UUID.randomUUID(),
            new domain.Airport.Name("LGW")
        );

        Airport destination = new Airport(
            airportRepository,
            UUID.randomUUID(),
            new domain.Airport.Name("LAX")
        );

        try {
            flightRepository.store(new Flight(
                airline,
                new Route(origin, destination),
                new FlightNumber(airline.getDesignation(), number),
                gufi
            ));
        } catch (Exception exception) {
            // Suppress exception
        }
    }

    @Then("^a flight with flight no\\. (.*) will exist$")
    public void a_flight_with_flight_no_will_exist(String flightNumber) {
        Flight fetched = flightRepository.fetchByGUFI(gufi);

        Assert.assertNotNull(fetched);
        Assert.assertEquals(flightNumber, fetched.getFlightNumber().toString());
    }

    @Then("^it should fail to create the flight$")
    public void it_should_fail_to_create_the_flight() {
        Flight fetched = flightRepository.fetchByGUFI(gufi);

        Assert.assertNull(fetched);
    }

    @Then("^two flights with flight no\\. (.*) will exist$")
    public void two_flights_with_flight_no_will_exist(String flightNumber) {
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
