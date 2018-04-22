package domain.Airline.Repository;

import domain.Airline.Airline;
import domain.Airline.Name;

import java.util.UUID;

public interface Repository
{
    /**
     * Store an airline (can be new or updated existing)
     *
     * @param airline The airline save
     */
    void store(Airline airline);

    /**
     * Fetch an airline by UUID.
     * Will return null if no airline exists with this UUID
     *
     * @param uuid Airline identifier
     *
     * @return Airline|null
     */
    Airline fetch(UUID uuid);

    /**
     * Fetch an airline by name.
     * Will return null if no airline exists with this name
     *
     * @param name Airline name
     * @return Airline|null
     */
    Airline fetchByName(Name name);
}
