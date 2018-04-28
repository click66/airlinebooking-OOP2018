package application.Repository;

import domain.Airport.Airport;
import domain.Airport.Name;
import domain.Airport.Repository;

import java.util.HashMap;
import java.util.UUID;

public class AirportRepository extends application.Repository.HashMapRepository<Airport> implements Repository
{
    private HashMap<UUID, Airport> map;

    public AirportRepository(HashMap<UUID, Airport> map)
    {
        super(map);

        this.map = map;
    }

    @Override
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
