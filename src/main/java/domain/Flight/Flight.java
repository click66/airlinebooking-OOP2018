package domain.Flight;

import domain.Airline.Airline;
import domain.Identifiable;

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

    /**
     * "Flight" constructor
     *
     * @param airline
     * @param route
     * @param flightNumber
     * @param gufi
     */
    public Flight(Airline airline, Route route, FlightNumber flightNumber, GUFI gufi)
    {
        this.airline = airline;
        this.route = route;
        this.flightNumber = flightNumber;
        this.gufi = gufi;
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
