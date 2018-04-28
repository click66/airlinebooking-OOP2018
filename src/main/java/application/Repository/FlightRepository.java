package application.Repository;

import domain.Flight.Flight;
import domain.Flight.GUFI;
import domain.Flight.Repository;

public class FlightRepository extends HashMapRepository<Flight> implements Repository
{
    @Override
    public Flight fetchByGUFI(GUFI gufi)
    {
        return this.fetch(gufi.getUuid());
    }
}
