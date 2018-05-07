package domain.Flight;

import domain.Airline.Airline;
import domain.Exception.BookingRejected;
import domain.Exception.IncompatiblePlane;
import domain.Exception.InvalidFlightNumber;
import domain.Flight.FlightNumber.FlightNumber;
import domain.Flight.FlightNumber.Registrar;
import domain.Flight.Class.Class;
import domain.Identifiable;

import java.time.LocalDateTime;
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

    private Plane plane;

    /**
     * "Flight" constructor
     *
     * @param registrar    Flight number registrar
     * @param airline      Airline under which this flight is flying
     * @param route        The route this airline is flying
     * @param flightNumber The flight number of this flight (unique to the date)
     * @param gufi         Globally Unique Flight Identifier
     * @param dateTime     Date and time of flight
     * @param plane        Plane
     */
    public Flight(
        Registrar registrar,
        Airline airline,
        Route route,
        FlightNumber flightNumber,
        GUFI gufi,
        LocalDateTime dateTime,
        Plane plane
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
        this.plane = plane;
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
     * Re-assign this flight to a different plane
     * Will transfer all existing bookings to a new plane
     *
     * @param newPlane The new plane to apply
     *
     * @throws IncompatiblePlane If the existing bookings could not be applied to the new plane
     */
    public void changePlane(Plane newPlane) throws IncompatiblePlane
    {
        plane = newPlane.withBookingsFrom(plane);
    }

    /**
     * Get the Plane to which this Flight is assigned
     *
     * @return The plane
     */
    public Plane getPlane()
    {
        return plane;
    }

    /**
     * Get a count of the number of sections on this Flight
     *
     * @return The number of sections on the flight
     */
    public int countSections()
    {
        return plane.countSections();
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
        this.plane = plane.withBookedSeat(sectionClass, seatNumber);
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
        return plane.isSeatAvailable(sectionClass, seatNumber);
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
        return plane.isSeatBooked(sectionClass, seatNumber);
    }

    /**
     * Check if any seat of the supplied class is available.
     *
     * @param sectionClass The class strategy
     * @return True if any seats are available
     */
    public Boolean hasAvailableSeats(Class sectionClass)
    {
        return plane.hasAvailableSeats(sectionClass);
    }

    /**
     * Check if any seats are available across all sections of the flight.
     *
     * @return True if any seats are available
     */
    public Boolean hasAvailableSeats()
    {
        return plane.hasAvailableSeats();
    }
}
