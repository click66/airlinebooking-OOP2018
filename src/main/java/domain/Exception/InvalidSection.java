package domain.Exception;

/**
 * Exception asserting a Section specification violated an invariant
 */
public class InvalidSection extends RuntimeException
{
    public InvalidSection(String message)
    {
        super(message);
    }
}
