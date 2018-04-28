package domain.Flight;

public interface Repository extends domain.Repository<Flight>
{
    /**
     * Fetch a flight by its GUFI
     * Will return null if flight not found
     *
     * @param gufi Flight GUFI
     *
     * @return Flight|null
     */
    Flight fetchByGUFI(GUFI gufi);
}
