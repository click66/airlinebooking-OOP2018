package domain.Flight;

import domain.Airline.Airline;
import domain.Exception.BookingRejected;
import domain.Exception.InvalidFlightNumber;
import domain.Exception.NoSection;
import domain.Flight.FlightNumber.FlightNumber;
import domain.Flight.FlightNumber.Registrar;
import domain.Flight.Class.Class;
import domain.Identifiable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

/**
 * "Flight" aggregate root entity
 */
public class Flight implements Identifiable
{
    private Airline airline;

    private Route route;

    private FlightNumber flightNumber;

    private GUFI gufi;

    private LocalDateTime dateTime;

    private HashMap<String, Section> sections;

    /**
     * "Flight" constructor
     *
     * @param registrar    Flight number registrar
     * @param airline      Airline under which this flight is flying
     * @param route        The route this airline is flying
     * @param flightNumber The flight number of this flight (unique to the date)
     * @param gufi         Globally Unique Flight Identifier
     * @param dateTime     Date and time of flight
     * @param sections     Storage of sections
     */
    public Flight(
        Registrar registrar,
        Airline airline,
        Route route,
        FlightNumber flightNumber,
        GUFI gufi,
        LocalDateTime dateTime,
        HashMap<String, Section> sections
    )
    {
        if (!registrar.isValidFlightNumber(flightNumber, dateTime.toLocalDate())) {
            throw new InvalidFlightNumber("The provided flight number is already in use on the provided date.");
        }

        registrar.registerFlightNumber(flightNumber, dateTime.toLocalDate());

        this.airline = airline;
        this.route = route;
        this.flightNumber = flightNumber;
        this.gufi = gufi;
        this.dateTime = dateTime;
        this.sections = sections;
    }

    @Override
    public UUID getUuid()
    {
        return this.gufi.getUuid();
    }

    /**
     * "FlightNumber" accessor
     *
     * @return Flight number entity
     */
    public FlightNumber getFlightNumber()
    {
        return flightNumber;
    }

    /**
     * Add a section to this Flight
     *
     * @param sectionClass The class strategy to use for creating this section
     * @param rows         The desired number of rows for this section
     * @param columns      The desired number of columns for this section
     */
    public void addSection(Class sectionClass, Integer rows, Integer columns)
    {
        sections.put(sectionClass.getKey(), Section.ofRowsAndColumns(rows, columns));
    }

    /**
     * Get a count of the number of sections on this Flight
     *
     * @return The number of sections on the flight
     */
    public int countSections()
    {
        return sections.size();
    }

    /**
     * Book a seat on this flight identified by the supplied seat number, of the supplied class.
     * Will throw a BookingRejected exception if the seat is already booked.
     *
     * @param sectionClass The class strategy
     * @param seatNumber   The number of the seat to book
     * @throws BookingRejected if the seat is already booked
     */
    public void bookSeat(Class sectionClass, SeatNumber seatNumber) throws BookingRejected
    {
        Section section = getSection(sectionClass);

        if (!section.isSeatAvailable(seatNumber)) {
            throw new BookingRejected("Seat " + seatNumber + " is already booked in the desired section");
        }

        section.markSeatBooked(seatNumber);
    }

    /**
     * Check if a seat of the supplied class is available.
     *
     * @param sectionClass The class strategy
     * @param seatNumber   The number of the seat to check
     * @return True if the seat is available, false if it is booked
     */
    public Boolean isSeatAvailable(Class sectionClass, SeatNumber seatNumber)
    {
        Section section = getSection(sectionClass);

        return section.isSeatAvailable(seatNumber);
    }

    /**
     * Check if a seat of the supplied class is booked.
     *
     * @param sectionClass The class strategy
     * @param seatNumber   The number of the seat to check
     * @return False if the seat is available, true if it is booked
     */
    public Boolean isSeatBooked(Class sectionClass, SeatNumber seatNumber)
    {
        Section section = getSection(sectionClass);

        return !section.isSeatAvailable(seatNumber);
    }

    /**
     * Check if any seat of the supplied class is available.
     *
     * @param sectionClass The class strategy
     * @return True if any seats are available
     */
    public Boolean hasAvailableSeats(Class sectionClass)
    {
        Section section = getSection(sectionClass);

        return section.hasAvailableSeats();
    }

    /**
     * Check if any seats are available across all sections of the flight.
     *
     * @return True if any seats are available
     */
    public Boolean hasAvailableSeats()
    {
        // Loop through all sections, immediately returning true if any have available seats

        for (Section section : sections.values()) {
            if (section.hasAvailableSeats()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get a section in this flight that corresponds with the supplied class strategy.
     * If this flight does not have an appropriate section, a NoSection exception will be thrown.
     *
     * @param sectionClass The class strategy
     * @return Section if one exists
     * @throws NoSection if section does not exist
     */
    private Section getSection(Class sectionClass) throws NoSection
    {
        if (!sections.containsKey(sectionClass.getKey())) {
            throw new NoSection("No section of the provided class exists on Flight " + flightNumber.toString());
        }

        return sections.get(sectionClass.getKey());
    }
}
