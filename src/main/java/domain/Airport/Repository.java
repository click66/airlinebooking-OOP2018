package domain.Airport;

import java.util.HashMap;
import java.util.UUID;

public class Repository
{
    private HashMap<UUID, Airport> airports = new HashMap<>();

    /**
     * Store an airport (can be new or updated existing)
     *
     * @param airport The airport save
     */
    public void store(Airport airport)
    {
        this.airports.put(airport.getUuid(), airport);
    }

    /**
     * Fetch an airport by UUID.
     * Will return null if no airport exists with this UUID
     *
     * @param uuid Airport identifier
     *
     * @return Airport|null
     */
    public Airport fetch(UUID uuid)
    {
        return airports.get(uuid);
    }

    /**
     * Fetch an airport by name.
     * Will return null if no airport exists with this name
     *
     * @param name Airport name
     * @return Airport|null
     */
    public Airport fetchByName(Name name)
    {
        for (Airport airport : airports.values()) {
            if (airport.getName().equals(name)) {
                return airport;
            }
        }

        return null;
    }
}
