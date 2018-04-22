package domain.Airline;

import domain.exception.InvalidArgument;

/**
 * "Airline Name" value object
 */
public class Name
{
    private String value;

    /**
     * "Airline Name" constructor
     *
     * @param value String value
     */
    public Name(String value)
    {
        if (value.isEmpty()) {
            throw new InvalidArgument("Airline names cannot be empty");
        }

        if (value.length() >= 6) {
            throw new InvalidArgument("Airline names must be less than 6 letters");
        }

        if (!value.matches("[A-Z]*")) {
            throw new InvalidArgument("Airline names must consist of upper-case letters");
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
