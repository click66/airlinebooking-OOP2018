package domain.Airport;

public interface Repository extends domain.Repository<Airport>
{
    /**
     * Fetch an airport by name.
     * Will return null if no airport exists with this name
     *
     * @param name Airport name
     * @return Airport|null
     */
    Airport fetchByName(Name name);
}
