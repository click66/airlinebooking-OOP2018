package domain.Exception;

/**
 * Exception asserting a booking has failed (usually due to an unfulfilled precondition)
 */
public class BookingRejected extends RuntimeException
{
    public BookingRejected(String message)
    {
        super(message);
    }
}
