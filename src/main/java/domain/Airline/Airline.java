package domain.Airline;

import domain.Identifiable;
import domain.Exception.ValueNotUnique;

import java.util.UUID;

/**
 * "Airline" aggregate root entity
 */
public class Airline implements Identifiable
{
    private UUID uuid;

    private Name name;

    private Designation designation;

    /**
     * "Airline" constructor
     *
     * @param repository  Airline repository
     * @param uuid        Airline UUID
     * @param name        Name of airline
     * @param designation Two-character designation
     */
    public Airline(Repository repository, UUID uuid, Name name, Designation designation)
    {
        if (repository.fetchByName(name) != null) {
            throw new ValueNotUnique("The name provided for this Airline was not unique");
        }

        if (repository.fetchByDesignation(designation) != null) {
            throw new ValueNotUnique("The designation provided for this Airline was not unique");
        }

        this.uuid = uuid;
        this.name = name;
        this.designation = designation;
    }

    /**
     * Returns the name of the airline
     *
     * @return Name
     */
    public Name getName()
    {
        return name;
    }

    /**
     * Returns the identifier of the airline
     *
     * @return UUID
     */
    public UUID getUuid()
    {
        return uuid;
    }

    /**
     * Returns the designation of the airline
     *
     * @return Designation
     */
    public Designation getDesignation()
    {
        return designation;
    }
}
