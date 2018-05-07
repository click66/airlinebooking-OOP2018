package domain.Flight;

import domain.Exception.BookingRejected;
import domain.Exception.IncompatiblePlane;
import domain.Exception.NoSection;
import domain.Flight.Class.Class;

import java.util.HashMap;

/**
 * "Plane" value object
 */
final public class Plane
{
    private HashMap<String, Section> sections;

    /**
     * Plane constructor
     *
     * @param sections Seat sections to include on this plane
     */
    public Plane(HashMap<String, Section> sections)
    {
        this.sections = sections;
    }

    /**
     * Plane copy constructor
     *
     * @param plane The source plane
     */
    Plane(Plane plane)
    {
        this.sections = plane.sections;
    }

    /**
     * Count the number of sections in this plane
     *
     * @return int
     */
    public int countSections()
    {
        return this.sections.size();
    }

    /**
     * Returns a plane with the provided section
     * If a section of the provided class already exists on the source plane, it will replace it. Otherwise, it will
     * append it.
     *
     * @param sectionClass The class strategy to use for creating this section
     * @param rows         The desired number of rows for this section
     * @param columns      The desired number of columns for this section
     *
     * @return Plane
     */
    public Plane withSection(Class sectionClass, Integer rows, Integer columns)
    {
        Plane clone = new Plane(this);

        clone.sections.put(sectionClass.getKey(), Section.ofRowsAndColumns(rows, columns));

        return clone;
    }

    /**
     * Will merge all the bookings from a source plan onto a new plane
     * Returns a new plane
     *
     * @param plane Plane on which existing bookings are placed
     *
     * @return The hydrated Plane
     *
     * @throws domain.Exception.IncompatiblePlane If the bookings on the provided plane were not able to be merged
     */
    public Plane withBookingsFrom(Plane plane) throws IncompatiblePlane
    {
        // TODO

        return new Plane(plane);
    }

    /**
     * Book a seat on this flight identified by the supplied seat number, of the supplied class.
     * Will throw a BookingRejected exception if the seat is already booked.
     *
     * @param sectionClass The class strategy
     * @param seatNumber   The number of the seat to book
     *
     * @return Plane
     *
     * @throws BookingRejected if the seat is already booked
     */
    public Plane withBookedSeat(Class sectionClass, SeatNumber seatNumber) throws BookingRejected
    {
        Plane clone = new Plane(this);

        Section section = clone.getSection(sectionClass);

        if (!section.isSeatAvailable(seatNumber)) {
            throw new BookingRejected("Seat " + seatNumber + " is already booked in the desired section");
        }

        section.markSeatBooked(seatNumber);

        return clone;
    }

    /**
     * Check if a seat of the supplied class is available.
     *
     * @param sectionClass The class strategy
     * @param seatNumber   The number of the seat to check
     * @return True if the seat is available, false if it is booked
     */
    public Boolean isSeatAvailable(Class sectionClass, SeatNumber seatNumber)
    {
        Section section = getSection(sectionClass);

        return section.isSeatAvailable(seatNumber);
    }

    /**
     * Check if a seat of the supplied class is booked.
     *
     * @param sectionClass The class strategy
     * @param seatNumber   The number of the seat to check
     * @return False if the seat is available, true if it is booked
     */
    public Boolean isSeatBooked(Class sectionClass, SeatNumber seatNumber)
    {
        Section section = getSection(sectionClass);

        return !section.isSeatAvailable(seatNumber);
    }

    /**
     * Check if any seat of the supplied class is available.
     *
     * @param sectionClass The class strategy
     * @return True if any seats are available
     */
    public Boolean hasAvailableSeats(Class sectionClass)
    {
        Section section = getSection(sectionClass);

        return section.hasAvailableSeats();
    }

    /**
     * Check if any seats are available across all sections of the flight.
     *
     * @return True if any seats are available
     */
    public Boolean hasAvailableSeats()
    {
        // Loop through all sections, immediately returning true if any have available seats

        for (Section section : sections.values()) {
            if (section.hasAvailableSeats()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get a section in this flight that corresponds with the supplied class strategy.
     * If this flight does not have an appropriate section, a NoSection exception will be thrown.
     *
     * @param sectionClass The class strategy
     * @return Section if one exists
     * @throws NoSection if section does not exist
     */
    private Section getSection(Class sectionClass) throws NoSection
    {
        if (!sections.containsKey(sectionClass.getKey())) {
            throw new NoSection("No section of the provided class exists on this plane");
        }

        return sections.get(sectionClass.getKey());
    }
}
