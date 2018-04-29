package domain.Flight.Section;

import domain.Exception.InvalidSection;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SectionTest
{
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // Valid row and column combinations
            {   1,   1,     1, true },
            {   2,   1,     2, true },
            {   2,   3,     6, true },
            {   4,   3,    12, true },
            {   6,   5,    30, true },
            {  99,   1,    99, true },
            { 100,   1,   100, true },
            { 100,   10, 1000, true },

            // Too many columns
            { 1,  11,  99, false },
            { 1,  99,  99, false },
            { 1, 100, 100, false },

            // Too many rows
            {  101, 1,  101, false },
            {  999, 1,  999, false },
            { 1000, 1, 1000, false },

            // Zero for either value
            { 0, 0, 0, false },
            { 0, 1, 1, false },
            { 1, 0, 1, false },

            // Negative for either value
            {   -1,  -1,    1, false },
            {  -99, -99, 9801, false },
            { -100, -10, 1000, false },
            {   -5,   5,  -25, false },
            {    5,  -5,  -25, false },
        });
    }

    private Integer rows;

    private Integer columns;

    private Integer seats;

    private Boolean valid;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public SectionTest(Integer rows, Integer columns, Integer seats, Boolean valid)
    {
        this.rows = rows;
        this.columns = columns;
        this.seats = seats;
        this.valid = valid;
    }

    @Test
    public void countSeats()
    {
        if (!valid) {
            thrown.expect(InvalidSection.class);
        }

        Section sut = Section.ofRowsAndColumns(rows, columns);

        Assert.assertEquals(seats, sut.countSeats());
    }
}
