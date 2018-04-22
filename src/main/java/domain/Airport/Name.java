package domain.Airport;

import domain.exception.InvalidArgument;

/**
 * "Airport Name" value object
 */
public class Name
{
    private String value;

    /**
     * "Airport Name" constructor
     *
     * @param value String value
     */
    public Name(String value)
    {
        if (!value.matches("[A-Z]{3}")) {
            throw new InvalidArgument("Airport names must consist of 3 upper-case letters");
        }

        this.value = value;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Name && ((Name) obj).value.equals(value);
    }

    @Override
    public int hashCode()
    {
        return value.hashCode();
    }
}
