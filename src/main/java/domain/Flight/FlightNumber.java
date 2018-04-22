package domain.Flight;

import domain.Airline.Designation;
import domain.exception.InvalidArgument;

public class FlightNumber
{
    private Designation airlineDesignation;

    private Integer number;

    public FlightNumber(Designation airlineDesignation, Integer number)
    {
        this.airlineDesignation = airlineDesignation;

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
}
