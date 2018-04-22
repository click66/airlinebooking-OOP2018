package domain.Airport;

/**
 * "Airport" entity
 */
public class Airport
{
    private Name name;

    /**
     * "Airport" constructor
     *
     * @param name Airport name
     */
    public Airport(Name name)
    {
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
}
