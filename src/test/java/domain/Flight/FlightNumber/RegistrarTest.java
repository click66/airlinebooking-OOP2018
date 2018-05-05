package domain.Flight.FlightNumber;

import domain.Airline.Airline;
import domain.Airline.Designation;
import domain.Airline.Name;
import domain.Airline.Repository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class RegistrarTest
{
    private Mockery context = new Mockery();

    private Airline airline;

    private Repository airlineRepository;

    @Before
    public void setUp()
    {
        airlineRepository = context.mock(Repository.class);

        Name name = new Name("SWEST");
        Designation designation = new Designation("SW");

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
    public void isValidByDefault()
    {
        Registrar sut = new Registrar(new HashMap<>());

        Assert.assertTrue(sut.isValidFlightNumber(
            new FlightNumber(airline, 1234),
            LocalDate.now())
        );
    }

    @Test
    public void oneFlightNumberTwiceOnTheSameDay()
    {
        int number = 1234;
        LocalDate date = LocalDate.now();

        HashMap<FlightNumber, LocalDate> map = new HashMap<>();

        Registrar sut = new Registrar(map);

        map.put(new FlightNumber(airline, number), date);

        Assert.assertFalse(sut.isValidFlightNumber(new FlightNumber(airline, number), date));
    }

    @Test
    public void oneFlightNumberOnDifferentDays()
    {
        int number = 1234;

        HashMap<FlightNumber, LocalDate> map = new HashMap<>();

        Registrar sut = new Registrar(map);

        map.put(
            new FlightNumber(airline, number),
            LocalDate.of(1955, 11, 5)
        );

        Assert.assertTrue(sut.isValidFlightNumber(
            new FlightNumber(airline, number),
            LocalDate.of(1955, 11, 12)
        ));
    }

    @Test
    public void twoFlightNumbersOnTheSameDay()
    {
        LocalDate date = LocalDate.of(2015, 10, 21);

        HashMap<FlightNumber, LocalDate> map = new HashMap<>();

        Registrar sut = new Registrar(map);

        map.put(new FlightNumber(airline, 1234), date);

        Name differentAirlineName = new Name("BRIT");
        Designation differentAirlineDesignation = new Designation("BA");

        context.checking(new Expectations()
        {{
            oneOf(airlineRepository).fetchByName(differentAirlineName);
            will(returnValue(null));

            oneOf(airlineRepository).fetchByDesignation(differentAirlineDesignation);
            will(returnValue(null));
        }});

        Airline differentAirline = new Airline(airlineRepository, UUID.randomUUID(), differentAirlineName, differentAirlineDesignation);


        Assert.assertTrue(sut.isValidFlightNumber(new FlightNumber(differentAirline, 4321), date));
    }

    @Test
    public void differentFlightNumbersDifferentDays()
    {
        HashMap<FlightNumber, LocalDate> map = new HashMap<>();

        Registrar sut = new Registrar(map);

        map.put(
            new FlightNumber(airline, 1234),
            LocalDate.of(1885, 1, 1)
        );

        Assert.assertTrue(sut.isValidFlightNumber(
            new FlightNumber(airline, 4321),
            LocalDate.of(1885, 9, 7)
        ));
    }
}
