package domain.Airport.Repository;

import domain.Airport.Airport;
import domain.Airport.Name;

public class HashMapRepository extends domain.HashMapRepository<Airport> implements Repository
{
    /**
     * Fetch an airport by name.
     * Will return null if no airport exists with this name
     *
     * @param name Airport name
     * @return Airport|null
     */
    public Airport fetchByName(Name name)
    {
        for (Airport airport : map.values()) {
            if (airport.getName().equals(name)) {
                return airport;
            }
        }

        return null;
    }
}
