package domain.Airline;

import org.junit.Test;

import static org.junit.Assert.*;

public class DesignationTest
{
    @Test
    public void testTwoDesignationsAreEqual()
    {
        Designation one = new Designation("BA");
        Designation two = new Designation("BA");

        assertTrue(one.equals(two));
    }

    @Test
    public void toStringOutputsString()
    {
        String string = "FA";

        Designation sut = new Designation(string);

        assertSame(string, sut.toString());
    }
}
