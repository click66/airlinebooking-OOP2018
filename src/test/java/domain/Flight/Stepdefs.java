package domain.Flight;

import application.Repository.AirlineRepository;
import application.Repository.AirportRepository;

import application.Repository.HashMapRepository;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.Airline.Airline;
import domain.Airport.Airport;
import domain.Flight.FlightNumber.FlightNumber;
import domain.Flight.FlightNumber.Registrar;
import domain.Flight.Section.Class.Business;
import domain.Flight.Section.Class.Class;
import domain.Flight.Section.Class.Economy;
import domain.Flight.Section.Class.First;
import domain.Flight.Section.Section;
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
    private Integer expectedSectionCount = 0;
    private String lastAddedSectionKey;

    private HashMap<String, Airline> airlines;
    private HashMap<String, Airport> airports;
    private HashMap<String, Flight> flights;
    private HashMap<String, Section> sections;

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
        flights  = new HashMap<>();
        sections = new HashMap<>();

        flightNumberRegistrar = new Registrar(new HashMap<>());
    }

    @Given("^airline (.*) has designation (.*)$")
    public void airline_has_designation(String airlineName, String airlineDesignation)
    {
        makeAirline(airlineName, airlineDesignation);
    }

    @Given("^(.*) is an airport$")
    public void is_an_airport(String airportName)
    {
        makeAirport(airportName);
    }

    @Given("^flight (.*) exists$")
    public void flight_exists(String flightNumber)
    {
        String airlineDesignation = flightNumber.substring(0, 2);
        int number                = Integer.parseInt(flightNumber.substring(2));

        Airline airline     = makeAirline(airlineDesignation, airlineDesignation);
        Airport origin      = makeAirport("LAX");
        Airport destination = makeAirport("LGW");

        Flight flight = new Flight(
            flightNumberRegistrar,
            airline,
            new Route(origin, destination),
            new FlightNumber(new domain.Airline.Designation(airlineDesignation), number),
            GUFI.randomGUFI(),
            LocalDateTime.now(),
            sections
        );

        flightRepository.store(flight);
        flights.put(flight.getFlightNumber().toString(), flight);
    }

    @When("^I create flight (\\d+) under airline (.*) for (.*)/(.*)/(.*)$")
    public void i_create_flight_under_airline(int number, String airlineName, String date, String month, String year)
    {
        Airline airline     = airlines.get(airlineName);
        Airport origin      = makeAirport("LGW");
        Airport destination = makeAirport("LAX");

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
                ), LocalTime.now()),
                sections
            ));
        } catch (Exception exception) {
            // Suppress exception
        }
    }

    @When("^I create flight (\\d+) between (.*) and (.*)$")
    public void i_create_flight_between(int number, String origAirportName, String destAirportName)
    {
        Airline airline     = makeAirline("SWEST", "SW");
        Airport origin      = airports.get(origAirportName);
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
                LocalDateTime.now(),
                sections
            ));
        } catch (Exception exception) {
            // Ignore exception
        }
    }

    @When("^I create a section of class (.*), with (\\d+) rows and (\\d+) columns, on flight (.*)$")
    public void i_create_section_of_class_with_rows_and_columns_on_flight(String sectionClass, int rows, int columns, String flightNumber)
    {
        Flight flight = flights.get(flightNumber);

        try {
            flight.addSection(getSectionClass(sectionClass), rows, columns);
        } catch (Exception exception) {
            // Ignore exception
        }

        lastAddedSectionKey = getSectionClass(sectionClass).getKey();
        expectedSectionCount++;

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

    @Then("^flight (.*) should contain (\\d+) sections$")
    public void flight_should_contain_sections(String flightNumber, int sections)
    {
        Flight flight = flights.get(flightNumber);

        Assert.assertEquals((Integer)sections, flight.countSections());
    }

    @Then("^the created section should have (\\d+) seats$")
    public void the_created_section_should_have_seats(Integer seatCount)
    {
        Section lastAdded = sections.get(lastAddedSectionKey);

        Assert.assertEquals(seatCount, lastAdded.countSeats());
    }

    @Then("^it should fail to create the section$")
    public void it_should_fail_to_create_the_section()
    {
        Assert.assertNotEquals(expectedSectionCount, (Integer)sections.size());
    }

    /**
     * Build an airport for use in preconditions
     *
     * @param name Airport name
     *
     * @return Airport
     */
    private Airport makeAirport(String name)
    {
        Airport airport = new Airport(
            airportRepository,
            UUID.randomUUID(),
            new domain.Airport.Name(name)
        );

        airports.put(name, airport);

        return airport;
    }

    /**
     * Build an airline for use in preconditions
     *
     * @param name        Airline name
     * @param designation Airline designation
     *
     * @return Airline
     */
    private Airline makeAirline(String name, String designation)
    {
        Airline airline = new Airline(
            airlineRepository,
            UUID.randomUUID(),
            new domain.Airline.Name(name),
            new domain.Airline.Designation(designation)
        );

        airlines.put(name, airline);

        return airline;
    }

    /**
     * Get the class by for the provided string
     *
     * @param sectionClass String representing class
     *
     * @return Class object
     */
    private Class getSectionClass(String sectionClass)
    {
        switch (sectionClass) {
            case "Economy":
                return new Economy();
            case "Business":
                return new Business();
            case "First":
                return new First();
        }

        throw new PendingException("No test routine for this class (yet)");
    }
}
