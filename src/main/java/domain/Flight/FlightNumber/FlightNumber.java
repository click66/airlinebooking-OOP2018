package domain.Flight.FlightNumber;

import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Exception.InvalidArgument;

/**
 * "Flight number" value object
 */
public class FlightNumber
{
    private Designation airlineDesignation;

    private Integer number;

    public FlightNumber(Airline airline, Integer number)
    {
        this.airlineDesignation = airline.getDesignation();

        if (number < 1 || number > 9999) {
            throw new InvalidArgument("A flight number must be between 1 and 9999");
        }

        this.number = number;
    }

    @Override
    public String toString()
    {
        return airlineDesignation.toString().concat(number.toString());
    }

    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof FlightNumber
            && airlineDesignation.equals(((FlightNumber) obj).airlineDesignation)
            && number.equals(((FlightNumber) obj).number);
    }
}
