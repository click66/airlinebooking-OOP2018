package domain.Exception;

/**
 * Exception asserting an argument was invalid
 */
public class InvalidArgument extends RuntimeException
{
    public InvalidArgument(String message)
    {
        super(message);
    }
}
