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
        if (value.length() != 3) {
            throw new InvalidArgument("Airport names must be 3 characters long");
        }

        if (value.matches("[A-Z]+\\.?")) {
            throw new InvalidArgument("Airport names must only consist of upper-case characters");
        }

        this.value = value;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Name && ((Name) obj).value.equals(value);
    }
}
