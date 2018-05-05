package domain.Booking;

import application.FlightNumberRegistrar;
import application.Repository.AirlineRepository;
import application.Repository.AirportRepository;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.Airline.Airline;
import domain.Airport.Airport;
import domain.Flight.Flight;
import domain.Flight.FlightNumber.FlightNumber;
import domain.Flight.FlightNumber.Registrar;
import domain.Flight.GUFI;
import domain.Flight.Route;
import domain.Flight.SeatNumber;
import domain.Flight.Class.Business;
import domain.Flight.Class.Class;
import domain.Flight.Class.Economy;
import domain.Flight.Class.First;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Stepdefs
{
    private Flight flight;

    private ArrayList<Exception> bookingExceptions;

    @Before
    public void before()
    {
        Airline airline = new Airline(new AirlineRepository(new HashMap<>()), UUID.randomUUID(), new domain.Airline.Name("DELTA"), new domain.Airline.Designation("DE"));
        Airport origin = new Airport(new AirportRepository(new HashMap<>()), UUID.randomUUID(), new domain.Airport.Name("LGW"));
        Airport dest = new Airport(new AirportRepository(new HashMap<>()), UUID.randomUUID(), new domain.Airport.Name("LAX"));

        flight = new Flight(
            new FlightNumberRegistrar(new HashMap<>()),
            airline,
            new Route(origin, dest),
            new FlightNumber(airline.getDesignation(), 1234),
            GUFI.randomGUFI(),
            LocalDateTime.now(),
            new HashMap<>()
        );

        bookingExceptions = new ArrayList<>();
    }

    @Given("^a flight has a (\\d+)x(\\d+) (.*) class section$")
    public void a_flight_has_a_x__class_section(int rows, int columns, String sectionClass)
    {
        flight.addSection(
            getClassFromString(sectionClass),
            rows,
            columns
        );
    }

    @When("^I( try and)? book seat (.*) in (.*)$")
    public void i_book_seat_in(String trying, String seatId, String sectionClass)
    {
        try {
            flight.bookSeat(
                getClassFromString(sectionClass),
                SeatNumber.fromString(seatId)
            );
        } catch (Exception exception) {
            if (trying.isEmpty()) { // If there is no try, do or do not.
                throw exception;
            }

            // Log any exception that occurred during this booking
            bookingExceptions.add(exception);
        }
    }

    @Then("^seat (.*) in (.*) will be available$")
    public void seat_in_will_be_available(String seatId, String sectionClass)
    {
        try {
            Assert.assertTrue(
                flight.isSeatAvailable(
                    getClassFromString(sectionClass),
                    SeatNumber.fromString(seatId)
                )
            );
        } catch (Exception exception) {
            // Ignore exception
        }
    }

    @Then("^seat (.*) in (.*) will be booked$")
    public void seat_in_will_be_booked(String seatId, String sectionClass)
    {
        try {
            Assert.assertTrue(
                flight.isSeatBooked(
                    getClassFromString(sectionClass),
                    SeatNumber.fromString(seatId)
                )
            );
        } catch (Exception exception) {
            // Ignore exception
        }
    }

    @Then("^the booking should fail$")
    public void the_booking_should_fail()
    {
        // Assert that we have triggered at least one exception during a booking
        Assert.assertTrue(bookingExceptions.size() > 0);
    }

    /**
     * Get Class from representational string
     *
     * @param classString Class identifier
     * @return Class
     */
    private Class getClassFromString(String classString)
    {
        switch (classString) {
            case "Economy":
                return new Economy();
            case "Business":
                return new Business();
            case "First":
                return new First();
        }

        throw new PendingException("Test code for this class not defined (yet)");
    }
}
