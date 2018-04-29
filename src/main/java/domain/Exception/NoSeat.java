package domain.Exception;

/**
 * Exception asserting a specified Seat does not exist on a Flight
 */
public class NoSeat extends RuntimeException
{
    public NoSeat(String message)
    {
        super(message);
    }
}
