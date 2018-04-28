package application.Repository;

import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Airline.Name;
import domain.Airline.Repository;

import java.util.HashMap;
import java.util.UUID;

public class AirlineRepository extends HashMapRepository<Airline> implements Repository
{
    private HashMap<UUID, Airline> map;

    public AirlineRepository(HashMap<UUID, Airline> map)
    {
        super(map);

        this.map = map;
    }

    @Override
    public Airline fetchByName(Name name)
    {
        for (Airline airline : map.values()) {
            if (airline.getName().equals(name)) {
                return airline;
            }
        }

        return null;
    }

    @Override
    public Airline fetchByDesignation(Designation designation)
    {
        for (Airline airline : map.values()) {
            if (airline.getDesignation().equals(designation)) {
                return airline;
            }
        }

        return null;
    }
}
