package domain.Airport;

import java.util.UUID;

/**
 * "Airport" entity
 */
public class Airport
{
    private UUID uuid;

    private Name name;

    /**
     * "Airport" constructor
     *
     * @param name Airport name
     */
    public Airport(UUID uuid, Name name)
    {
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
