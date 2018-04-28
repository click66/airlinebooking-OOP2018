package domain.Exception;

/**
 * Exception asserting a flight number was invalid
 */
public class InvalidFlightNumber extends RuntimeException
{
    public InvalidFlightNumber(String message)
    {
        super(message);
    }
}
