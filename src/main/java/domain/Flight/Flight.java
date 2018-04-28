package domain.Flight;

import domain.Airline.Airline;
import domain.Exception.InvalidFlightNumber;
import domain.Flight.FlightNumber.FlightNumber;
import domain.Flight.FlightNumber.Registrar;
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

    /**
     * "Flight" constructor
     *
     * @param registrar    Flight number registrar
     * @param airline      Airline under which this flight is flying
     * @param route        The route this airline is flying
     * @param flightNumber The flight number of this flight (unique to the date)
     * @param gufi         Globally Unique Flight Identifier
     * @param dateTime     Date and time of flight
     */
    public Flight(
        Registrar registrar,
        Airline airline,
        Route route,
        FlightNumber flightNumber,
        GUFI gufi,
        LocalDateTime dateTime
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
    }

    @Override
    public UUID getUuid()
    {
        return this.gufi.getUuid();
    }

    public FlightNumber getFlightNumber()
    {
        return flightNumber;
    }
}
