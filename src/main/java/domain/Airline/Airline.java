package domain.Airline;

import domain.Identifiable;

import java.util.UUID;

/**
 * "Airline" entity
 */
public class Airline implements Identifiable
{
    private UUID uuid;

    private Name name;

    private Designation designation;

    /**
     * "Airline" constructor
     *
     * @param name Airline name
     */
    public Airline(UUID uuid, Name name, Designation designation)
    {
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
