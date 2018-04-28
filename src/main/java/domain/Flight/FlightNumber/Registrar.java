package domain.Flight.FlightNumber;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * "Registrar" domain services
 * Handles registration and validity of flight numbers
 */
public class Registrar
{
    private HashMap<FlightNumber, LocalDate> flightNumbers;

    /**
     * "Registrar" constructor
     *
     * @param flightNumbers Raw storage of flight numbers
     */
    public Registrar(HashMap<FlightNumber, LocalDate> flightNumbers)
    {
        this.flightNumbers = flightNumbers;
    }

    /**
     * Checks if the provided flight number is valid, given what flight numbers already exist
     *
     * @param flightNumber Flight number to check
     * @param date         Date of flight
     *
     * @return True if flight number is valid, false otherwise
     */
    public boolean isValidFlightNumber(FlightNumber flightNumber, LocalDate date)
    {
        if (!flightNumbers.containsKey(flightNumber)) {
            return true;
        }

        LocalDate existingDate = flightNumbers.get(flightNumber);

        return !existingDate.equals(date);
    }

    /**
     * Registers the flight number (ensures it cannot be re-used)
     *
     * @param flightNumber Flight number to register
     * @param date         Date
     */
    public void registerFlightNumber(FlightNumber flightNumber, LocalDate date)
    {
        flightNumbers.put(flightNumber, date);
    }
}
