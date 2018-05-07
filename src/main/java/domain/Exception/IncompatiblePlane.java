package domain.Exception;

/**
 * Exception asserting that a supplied Plane is incompatible with an existing Flight
 */
public class IncompatiblePlane extends RuntimeException
{
    public IncompatiblePlane(String message)
    {
        super(message);
    }
}
