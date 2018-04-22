package application;

import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Airline.Name;
import domain.Airline.Repository.Repository;

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
            UUID.randomUUID(),
            name,
            designation
        );

        if (repository.fetchByName(name) == null && repository.fetchByDesignation(designation) == null) {
            repository.store(airline);
        }
    }
}
