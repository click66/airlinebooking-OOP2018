package domain.Airline;

import domain.exception.InvalidArgument;

/**
 * "Airline Designation" value object
 */
public class Designation
{
    private String value;

    /**
     * "Airline Designation" constructor
     *
     * @param value String value
     */
    public Designation(String value)
    {
        if (value.isEmpty()) {
            throw new InvalidArgument("Airline designations cannot be empty");
        }

        if (value.length() > 2) {
            throw new InvalidArgument("Airline names are maximum 2 characters");
        }

        if (!value.matches("[A-Z]*")) {
            throw new InvalidArgument("Airline names must consist of upper-case letters");
        }

        this.value = value;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Designation && ((Designation) obj).value.equals(value);
    }

    @Override
    public int hashCode()
    {
        return value.hashCode();
    }

    @Override
    public String toString()
    {
        return value;
    }
}
