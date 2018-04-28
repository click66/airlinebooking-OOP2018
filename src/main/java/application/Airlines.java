package application;

import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Airline.Name;
import domain.Airline.Repository;

import java.util.UUID;

/**
 * "application.Airlines" application service
 */
public class Airlines
{
    private Repository repository;

    /**
     * "application.Airlines" constructor
     *
     * @param repository Airline repository
     */
    public Airlines(Repository repository)
    {
        this.repository = repository;
    }

    /**
     * Register a new Airline with the supplied name
     *
     * @param name The desired name
     */
    public void registerNewAirline(Name name, Designation designation)
    {
        Airline airline = new Airline(
            repository,
            UUID.randomUUID(),
            name,
            designation
        );

        repository.store(airline);
    }
}
