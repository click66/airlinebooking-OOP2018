package domain.Airport.Repository;

import domain.Airport.Airport;
import domain.Airport.Name;

import java.util.UUID;

public interface Repository
{
    /**
     * Store an airport (can be new or updated existing)
     *
     * @param airport The airport save
     */
    void store(Airport airport);

    /**
     * Fetch an airport by UUID.
     * Will return null if no airport exists with this UUID
     *
     * @param uuid Airport identifier
     *
     * @return Airport|null
     */
    Airport fetch(UUID uuid);

    /**
     * Fetch an airport by name.
     * Will return null if no airport exists with this name
     *
     * @param name Airport name
     * @return Airport|null
     */
    Airport fetchByName(Name name);
}
