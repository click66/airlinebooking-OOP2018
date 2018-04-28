package domain.exception;

/**
 * Exception asserting a specified value violated a uniqueness constraint
 */
public class ValueNotUnique extends RuntimeException
{
    public ValueNotUnique(String message)
    {
        super(message);
    }
}
