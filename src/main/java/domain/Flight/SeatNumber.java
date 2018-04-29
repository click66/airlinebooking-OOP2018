package domain.Flight;

import domain.Exception.InvalidArgument;

/**
 * "Seat Number" value object
 */
public class SeatNumber
{
    private Integer row;

    private String column;

    /**
     * "SeatNumber" constructor.
     *
     * @param row    Integer of row
     * @param column Letter of column
     */
    SeatNumber(Integer row, String column)
    {
        if (row < 1) {
            throw new InvalidArgument("No or invalid row was supplied");
        }

        this.row = row;
        this.column = column.trim().toUpperCase();
    }

    /**
     * Static factory method.
     * Creates SeatNumber from a string
     *
     * @param string The source string
     * @return Created SeatNumber
     */
    public static SeatNumber fromString(String string)
    {
        String column;
        Integer row;

        column = string.replaceAll("[^A-Za-z]", "");

        if (column.isEmpty()) {
            throw new InvalidArgument("No column was supplied");
        }

        try {
            row = Integer.parseInt(string.replaceAll("\\D+", ""));
        } catch (NumberFormatException exception) {
            row = 0;
        }


        return new SeatNumber(row, column);
    }

    @Override
    public String toString()
    {
        return row.toString() + column;
    }

    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof SeatNumber
            && row.equals(((SeatNumber) obj).row)
            && column.equals(((SeatNumber) obj).column);
    }

    /**
     * Row Integer accessor
     *
     * @return Row number
     */
    public Integer getRow()
    {
        return row;
    }

    /**
     * Column String accessor
     *
     * @return Column letter as String
     */
    public String getColumn()
    {
        return column;
    }

    /**
     * Returns the column letter as an Integer (e.g. A = 1, B = 2, ...)
     *
     * @return Integer
     */
    public Integer getColumnAsInteger()
    {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(column)+1;
    }
}
