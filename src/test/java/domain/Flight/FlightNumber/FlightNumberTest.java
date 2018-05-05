package domain.Flight.FlightNumber;

import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Airline.Name;
import domain.Airline.Repository;
import domain.Exception.InvalidArgument;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.*;

public class FlightNumberTest
{
    private Mockery context = new Mockery();

    private Airline airline;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp()
    {
        Repository airlineRepository = context.mock(Repository.class);

        Name name = new Name("BRIT");
        Designation designation = new Designation("BA");

        context.checking(new Expectations()
        {{
            oneOf(airlineRepository).fetchByName(name);
            will(returnValue(null));

            oneOf(airlineRepository).fetchByDesignation(designation);
            will(returnValue(null));
        }});

        airline = new Airline(airlineRepository, UUID.randomUUID(), name, designation);
    }

    @Test
    public void toStringCreatesProperlyFormattedFlightNumber()
    {
        Integer number = 5432;

        String expectedFlightNumber = "BA5432";

        assertEquals(expectedFlightNumber, (new FlightNumber(airline, number).toString()));
    }

    @Test
    public void numberCannotBeLongerThan4Digits()
    {
        thrown.expect(InvalidArgument.class);

        new FlightNumber(airline, 12345);
    }

    @Test
    public void twoFlightNumbersAreEqual()
    {
        FlightNumber one = new FlightNumber(airline, 1234);
        FlightNumber two = new FlightNumber(airline, 1234);

        assertEquals(one.hashCode(), two.hashCode());
        assertTrue(one.equals(two));
    }
}