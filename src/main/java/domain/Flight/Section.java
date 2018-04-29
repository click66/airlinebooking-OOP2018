package domain.Flight;

import domain.Exception.InvalidSection;
import domain.Exception.NoSeat;

import java.util.ArrayList;

/**
 * "Section" entity
 */
class Section
{
    private Integer rows;

    private Integer columns;

    private Integer seatCount;

    private ArrayList<SeatNumber> bookedSeats = new ArrayList<>();

    /**
     * Private "Section" constructor
     *
     * @param rows      Count of rows
     * @param columns   Count of columns
     */
    private Section(Integer rows, Integer columns)
    {
        if (rows < 1 || columns < 1) {
            throw new InvalidSection("A section must contain at least one seat");
        }

        if (rows > 100) {
            throw new InvalidSection("A section cannot contain more than 100 rows");
        }

        if (columns > 10) {
            throw new InvalidSection("A section cannot contain more than 10 columns");
        }

        this.rows = rows;
        this.columns = columns;
        this.seatCount = rows * columns;
    }

    /**
     * Get the count of seats in this section
     *
     * @return Integer
     */
    public Integer countSeats()
    {
        return seatCount;
    }

    /**
     * Static factory for creating section of specified number of rows and columns
     *
     * @param rows    Desired number of rows
     * @param columns Desired number of columns
     * @return Created section
     */
    public static Section ofRowsAndColumns(Integer rows, Integer columns)
    {
        return new Section(rows, columns);
    }

    /**
     * Checks if a seat is available in this section by seat number.
     * If the seat does not exist, throws "NoSeat" exception.
     *
     * @param seatNumber The seat number to check
     * @return True if the seat is available, false if not
     */
    public Boolean isSeatAvailable(SeatNumber seatNumber)
    {
        validateSeatNumber(seatNumber);

        return !bookedSeats.contains(seatNumber);
    }

    /**
     * Marks a seat as booked if it is not already.
     * If the seat does not exist, throws "NoSeat" exception.
     * Note: This will not complain if the seat is already booked.
     *
     * @param seatNumber The seat number to mark as booked
     */
    public void markSeatBooked(SeatNumber seatNumber)
    {
        validateSeatNumber(seatNumber);

        bookedSeats.add(seatNumber);
    }

    /**
     * Checks if this section has any available seats
     *
     * @return True if there are any seats available
     */
    public Boolean hasAvailableSeats()
    {
        return seatCount > bookedSeats.size();
    }

    /**
     * Validate a supplied SeatNumber to ensure it refers to a seat that exists.
     *
     * @param seatNumber The seat number to check
     * @throws NoSeat If seat does not exist
     */
    private void validateSeatNumber(SeatNumber seatNumber) throws NoSeat
    {
        if (seatNumber.getRow() > rows || seatNumber.getColumnAsInteger() > columns) {
            throw new NoSeat("Seat " + seatNumber.toString() + " does not exist in this section");
        }
    }
}
