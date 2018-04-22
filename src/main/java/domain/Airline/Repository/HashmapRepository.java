package domain.Airline.Repository;

import domain.Airline.Airline;
import domain.Airline.Name;

import java.util.HashMap;
import java.util.UUID;

public class HashmapRepository implements Repository
{
    private HashMap<UUID, Airline> airlines = new HashMap<>();

    /**
     * Store an airline (can be new or updated existing)
     *
     * @param airline The airline save
     */
    public void store(Airline airline)
    {
        this.airlines.put(airline.getUuid(), airline);
    }

    /**
     * Fetch an airline by UUID.
     * Will return null if no airline exists with this UUID
     *
     * @param uuid Airline identifier
     *
     * @return Airline|null
     */
    public Airline fetch(UUID uuid)
    {
        return airlines.get(uuid);
    }

    /**
     * Fetch an airline by name.
     * Will return null if no airline exists with this name
     *
     * @param name Airline name
     * @return Airline|null
     */
    public Airline fetchByName(Name name)
    {
        for (Airline airline : airlines.values()) {
            if (airline.getName().equals(name)) {
                return airline;
            }
        }

        return null;
    }
}
