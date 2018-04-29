package domain.Flight.Section;

import domain.Exception.InvalidSection;

import java.util.ArrayList;

/**
 * "Section" entity
 */
public class Section
{
    private Integer seatCount;

    private ArrayList<String> bookedSeats = new ArrayList<>();

    /**
     * Private "Section" constructor
     *
     * @param seatCount Count of seats in this section
     */
    private Section(Integer seatCount)
    {
        this.seatCount = seatCount;
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
     *
     * @return Created section
     */
    public static Section ofRowsAndColumns(Integer rows, Integer columns)
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

        return new Section(rows * columns);
    }

    public Boolean isSeatAvailable(String seatNumber)
    {
        return !bookedSeats.contains(seatNumber);
    }

    public void markSeatBooked(String seatNumber)
    {
        bookedSeats.add(seatNumber);
    }
}
