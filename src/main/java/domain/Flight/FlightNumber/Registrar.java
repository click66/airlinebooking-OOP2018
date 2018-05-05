package domain.Flight.FlightNumber;

import java.time.LocalDate;

/**
 * "Registrar" domain service
 * Handles registration and validity of flight numbers
 */
public interface Registrar
{
    /**
     * Checks if the provided flight number is valid, given what flight numbers already exist
     *
     * @param flightNumber Flight number to check
     * @param date         Date of flight
     *
     * @return True if flight number is valid, false otherwise
     */
    boolean isValidFlightNumber(FlightNumber flightNumber, LocalDate date);

    /**
     * Registers the flight number (ensures it cannot be re-used)
     *
     * @param flightNumber Flight number to register
     * @param date         Date
     */
    void registerFlightNumber(FlightNumber flightNumber, LocalDate date);
}
