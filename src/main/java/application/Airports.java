package application;

import domain.Airport.Airport;
import domain.Airport.Name;
import domain.Airport.Repository.Repository;

import java.util.UUID;

/**
 * "application.Airports" application service
 */
public class Airports
{
    private Repository repository;

    /**
     * "application.Airports" constructor
     *
     * @param repository Airport repository
     */
    public Airports(Repository repository)
    {
        this.repository = repository;
    }

    /**
     * Register a new Airport with the supplied name
     *
     * @param name The desired name
     */
    public void registerNewAirport(Name name)
    {
        Airport airport = new Airport(
            UUID.randomUUID(),
            name
        );

        if (repository.fetchByName(name) == null) {
            repository.store(airport);
        }
    }
}
