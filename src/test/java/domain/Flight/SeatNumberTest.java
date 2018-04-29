package domain.Flight;

import domain.Exception.InvalidArgument;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SeatNumberTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void canBeRenderedAsString()
    {
        SeatNumber sut = new SeatNumber(1, "A");

        Assert.assertEquals("1A", sut.toString());
    }

    @Test
    public void twoAreEqual()
    {
        SeatNumber first = new SeatNumber(1, "A");
        SeatNumber second = new SeatNumber(1, "A");

        Assert.assertTrue(first.equals(second));
        Assert.assertEquals(first, second);
    }

    @Test
    public void canBeFactoriedFromString()
    {
        SeatNumber sut = SeatNumber.fromString("1A");

        Assert.assertEquals((Integer) 1, sut.getRow());
        Assert.assertEquals("A", sut.getColumn());
        Assert.assertEquals("1A", sut.toString());
    }

    @Test
    public void fromStringFailsWithNoRow()
    {
        thrown.expect(InvalidArgument.class);

        SeatNumber.fromString("A");
    }

    @Test
    public void fromStringFailedWithNoColumn()
    {
        thrown.expect(InvalidArgument.class);

        SeatNumber.fromString("1");
    }

    @Test
    public void fromStringAllowsColumnAndRowSwapped()
    {
        Assert.assertEquals("1A", SeatNumber.fromString("A1").toString());
    }

    @Test
    public void fromStringAllowsSmallLetters()
    {
        Assert.assertEquals("1A", SeatNumber.fromString("1a").toString());
    }

    @Test
    public void trimsWhitespace()
    {
        SeatNumber sut = new SeatNumber(1, "A ");

        Assert.assertEquals("A", sut.getColumn());
        Assert.assertEquals("1A", sut.toString());
    }

    @Test
    public void fromStringTrimsWhitespace()
    {
        SeatNumber sut = SeatNumber.fromString("1 A");

        Assert.assertEquals("A", sut.getColumn());
        Assert.assertEquals("1A", sut.toString());
    }

    @Test
    public void getRow()
    {
        Integer row = 3;

        SeatNumber sut = new SeatNumber(row, "A");

        Assert.assertSame(row, sut.getRow());
    }

    @Test
    public void getColumn()
    {
        String column = "A";

        SeatNumber sut = new SeatNumber(1, column);

        Assert.assertSame(column, sut.getColumn());
    }

    @Test
    public void getColumnAsInteger()
    {
        String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        for (int i = 0; i <= 25; i ++) {
            Assert.assertEquals((Integer)(i+1), (new SeatNumber(1, strings[i]).getColumnAsInteger()));
        }
    }
}