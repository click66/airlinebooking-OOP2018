package domain.Flight;

import domain.Airport.Airport;
import domain.Exception.InvalidArgument;

/**
 * "Route" value object
 */
public class Route
{
    /**
     * "Route" constructor
     *
     * @param origin      Originating Airport
     * @param destination Destination Airport
     */
    public Route(Airport origin, Airport destination)
    {
        if (origin.getUuid() == destination.getUuid()) {
            throw new InvalidArgument("The origin and destination airports cannot be the same");
        }
    }
}
