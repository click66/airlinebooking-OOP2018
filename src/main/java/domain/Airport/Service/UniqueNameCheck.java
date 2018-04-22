package domain.Airport.Service;

import domain.Airport.Name;
import domain.Airport.Repository.Repository;

/**
 * Domain service to check the uniquity of an Airport's name
 */
public class UniqueNameCheck
{
    private Repository repository;

    /**
     * "UniqueNameCheck" constructor
     *
     * @param repository Airport repository
     */
    public UniqueNameCheck(Repository repository)
    {
        this.repository = repository;
    }

    /**
     * Checks if an Airport already exists with this name.
     * Returns true if yes, false if no.
     *
     * @param airportName The name to check
     *
     * @return boolean
     */
    public boolean alreadyExistsByName(Name airportName)
    {
        return repository.fetchByName(airportName) != null;
    }
}
