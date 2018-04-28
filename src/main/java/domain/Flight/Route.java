package domain.Flight;

import domain.Airport.Airport;
import domain.Exception.InvalidArgument;

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
        if (origin.getUuid() == destination.getUuid()) {
            throw new InvalidArgument("The origin and destination airports cannot be the same");
        }
    }
}
