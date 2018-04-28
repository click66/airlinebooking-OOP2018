package domain.Flight.FlightNumber;

import domain.Airline.Designation;
import domain.Exception.InvalidArgument;
import domain.Flight.FlightNumber.FlightNumber;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class FlightNumberTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toStringCreatesProperlyFormattedFlightNumber()
    {
        Designation airline = new Designation("BA");
        Integer number      = 5432;

        String expectedFlightNumber = "BA5432";

        assertEquals(expectedFlightNumber, (new FlightNumber(airline, number).toString()));
    }

    @Test
    public void numberCannotBeLongerThan4Digits()
    {
        Designation airline = new Designation("BA");

        thrown.expect(InvalidArgument.class);

        new FlightNumber(airline, 12345);
    }

    @Test
    public void twoFlightNumbersAreEqual()
    {
        FlightNumber one = new FlightNumber(new Designation("BA"), 1234);
        FlightNumber two = new FlightNumber(new Designation("BA"), 1234);

        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.equals(two));
    }
}