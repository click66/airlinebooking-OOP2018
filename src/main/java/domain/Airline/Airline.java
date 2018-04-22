package domain.Airline;

import java.util.UUID;

/**
 * "Airline" entity
 */
public class Airline
{
    private UUID uuid;

    private Name name;

    /**
     * "Airline" constructor
     *
     * @param name Airline name
     */
    public Airline(UUID uuid, Name name)
    {
        this.uuid = uuid;
        this.name = name;
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
}
