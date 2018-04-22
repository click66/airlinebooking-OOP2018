package domain.Airline.Repository;

import domain.Airline.Airline;
import domain.Airline.Name;

import java.util.UUID;

public interface Repository extends domain.Repository<Airline>
{
    /**
     * Fetch an airline by name.
     * Will return null if no airline exists with this name
     *
     * @param name Airline name
     * @return Airline|null
     */
    Airline fetchByName(Name name);
}
