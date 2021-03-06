package domain.Airport;

import domain.Identifiable;
import domain.Exception.ValueNotUnique;

import java.util.UUID;

/**
 * "Airport" aggregate root entity
 */
public class Airport implements Identifiable
{
    private UUID uuid;

    private Name name;

    /**
     * "Airport" constructor
     *
     * @param repository Repository of airports
     * @param uuid       Universally unique identifier for this airport
     * @param name       Desired airport name
     */
    public Airport(Repository repository, UUID uuid, Name name)
    {
        if (repository.fetchByName(name) != null) {
            throw new ValueNotUnique("The name provided for this Airport was not unique");
        }

        this.uuid = uuid;
        this.name = name;
    }

    /**
     * Returns the name of the airport
     *
     * @return Name
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Returns the identifier of the airport
     *
     * @return UUID
     */
    public UUID getUuid()
    {
        return uuid;
    }
}
