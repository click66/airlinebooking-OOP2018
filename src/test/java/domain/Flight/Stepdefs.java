package domain.Flight;

import application.Repository.AirlineRepository;
import application.Repository.AirportRepository;

import application.Repository.HashMapRepository;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Airline.Name;
import domain.Airport.Airport;
import domain.Flight.FlightNumber.FlightNumber;
import domain.Flight.FlightNumber.Registrar;
import domain.Repository;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Stepdefs
{
    private domain.Airline.Repository airlineRepository;

    private domain.Airport.Repository airportRepository;

    private HashMap<UUID, Flight> flightHashMap;
    private Repository<Flight> flightRepository;

    private ArrayList<UUID> created;

    private HashMap<String, Airline> airlines;
    private HashMap<String, Airport> airports;

    private Registrar flightNumberRegistrar;

    @Before
    public void before()
    {
        airlineRepository = new AirlineRepository(new HashMap<>());
        airportRepository = new AirportRepository(new HashMap<>());

        flightHashMap = new HashMap<>();
        flightRepository = new HashMapRepository<>(flightHashMap);

        created = new ArrayList<>();

        airlines = new HashMap<>();
        airports = new HashMap<>();

        flightNumberRegistrar = new Registrar(new HashMap<>());
    }

    @Given("^airline (.*) has designation (.*)$")
    public void airline_has_designation(String airlineName, String airlineDesignation)
    {
        airlines.put(airlineName, new Airline(
            airlineRepository,
            UUID.randomUUID(),
            new domain.Airline.Name(airlineName),
            new domain.Airline.Designation(airlineDesignation)
        ));
    }

    @Given("^(.*) is an airport$")
    public void is_an_airport(String airportName)
    {
        airports.put(airportName, new Airport(
            airportRepository,
            UUID.randomUUID(),
            new domain.Airport.Name(airportName)
        ));
    }

    @When("^I create flight (\\d+) under airline (.*) for (.*)/(.*)/(.*)$")
    public void i_create_flight_under_airline(int number, String airlineName, String date, String month, String year)
    {
        Airline airline = airlines.get(airlineName);

        Airport origin = new Airport(airportRepository, UUID.randomUUID(), new domain.Airport.Name("LGW"));
        Airport destination = new Airport(airportRepository, UUID.randomUUID(), new domain.Airport.Name("LAX"));

        UUID toCreate = UUID.randomUUID();
        created.add(toCreate);

        try {
            flightRepository.store(new Flight(
                flightNumberRegistrar,
                airline,
                new Route(origin, destination),
                new FlightNumber(airline.getDesignation(), number),
                new GUFI(toCreate),
                LocalDateTime.of(LocalDate.of(
                    Integer.parseInt(year),
                    Integer.parseInt(month),
                    Integer.parseInt(date)
                ), LocalTime.now())
            ));
        } catch (Exception exception) {
            // Suppress exception
        }
    }

    @When("^I create flight (\\d+) between (.*) and (.*)")
    public void i_create_flight_between(int number, String origAirportName, String destAirportName)
    {
        Airline airline = new Airline(
            airlineRepository,
            UUID.randomUUID(),
            new Name("SWEST"),
            new Designation("SW")
        );

        Airport origin = airports.get(origAirportName);

        Airport destination = airports.get(destAirportName);

        UUID toCreate = UUID.randomUUID();
        created.add(toCreate);

        try {
            flightRepository.store(new Flight(
                flightNumberRegistrar,
                airline,
                new Route(origin, destination),
                new FlightNumber(airline.getDesignation(), number),
                new GUFI(toCreate),
                LocalDateTime.now()
            ));
        } catch (Exception exception) {
            // Suppress exception
        }
    }

    @Then("^a flight with flight no\\. (.*) will exist$")
    public void a_flight_with_flight_no_will_exist(String flightNumber)
    {
        boolean found = false;

        for (Flight flight : flightHashMap.values()) {
            if (flight.getFlightNumber().toString().equals(flightNumber)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found);
    }

    @Then("^it should fail to create the flight$")
    public void it_should_fail_to_create_the_flight()
    {
        Assert.assertNotEquals(created.size(), flightHashMap.size());
    }

    @Then("^(\\d+) flights with flight no\\. (.*) will exist$")
    public void flights_with_flight_no_will_exist(int expectedCount, String flightNumber)
    {
        int count = 0;

        for (Flight flight : flightHashMap.values()) {
            if (flight.getFlightNumber().toString().equals(flightNumber)) {
                count++;
            }
        }

        Assert.assertSame(expectedCount, count);
    }

    @Then("^it should successfully create the flight$")
    public void it_should_successfully_create_the_flight()
    {
        Assert.assertEquals(created.size(), flightHashMap.size());
    }
}
