package domain.Exception;

/**
 * Exception asserting a Section does not exist on a Flight
 */
public class NoSection extends RuntimeException
{
    public NoSection(String message)
    {
        super(message);
    }
}
