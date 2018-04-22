package domain.Airline.Repository;

import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Airline.Name;

public interface Repository extends domain.Repository<Airline>
{
    /**
     * Fetch an airline by name.
     * Will return null if no airline exists with this name
     *
     * @param name Airline name
     * @return Airline|null
     */
    Airline fetchByName(Name name);

    /**
     * Fetch an airline by designation.
     * Will return null if no airline exists with this designation
     *
     * @param designation Airline designation
     * @return Airline|null
     */
    Airline fetchByDesignation(Designation designation);
}
