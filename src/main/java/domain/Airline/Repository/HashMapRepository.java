package domain.Airline.Repository;

import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Airline.Name;
import domain.Airport.Airport;

import java.util.HashMap;
import java.util.UUID;

public class HashMapRepository extends domain.HashMapRepository<Airline> implements Repository
{
    /**
     * Fetch an airline by name.
     * Will return null if no airline exists with this name
     *
     * @param name Airline name
     * @return Airline|null
     */
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
