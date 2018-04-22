package domain.Flight;

import domain.Airport.Airport;
import domain.exception.InvalidArgument;

public class Route
{
    /**
     * "Route" constructor
     *
     * @param origin
     * @param destination
     */
    public Route(Airport origin, Airport destination)
    {
        if (origin == destination || origin.equals(destination)) {
            throw new InvalidArgument("The origin and destination airports cannot be the samde");
        }
    }
}
