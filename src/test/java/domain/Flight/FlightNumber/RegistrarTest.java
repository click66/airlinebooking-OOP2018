package domain.Flight.FlightNumber;

import domain.Airline.Designation;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;

public class RegistrarTest
{
    @Test
    public void isValidByDefault()
    {
        Registrar sut = new Registrar(new HashMap<>());

        Assert.assertTrue(sut.isValidFlightNumber(
            new FlightNumber(new Designation("SW"), 1234),
            LocalDate.now())
        );
    }

    @Test
    public void oneFlightNumberTwiceOnTheSameDay()
    {
        String designation = "SW";
        int number         = 1234;
        LocalDate date     = LocalDate.now();

        HashMap<FlightNumber, LocalDate> map = new HashMap<>();

        Registrar sut = new Registrar(map);

        map.put(new FlightNumber(new Designation(designation), number), date);

        Assert.assertFalse(sut.isValidFlightNumber(new FlightNumber(new Designation(designation), number), date));
    }

    @Test
    public void oneFlightNumberOnDifferentDays()
    {
        String designation = "SW";
        int number         = 1234;

        HashMap<FlightNumber, LocalDate> map = new HashMap<>();

        Registrar sut = new Registrar(map);

        map.put(
            new FlightNumber(new Designation(designation), number),
            LocalDate.of(1955, 11, 5)
        );

        Assert.assertTrue(sut.isValidFlightNumber(
            new FlightNumber(new Designation(designation), number),
            LocalDate.of(1955, 11, 12)
        ));
    }

    @Test
    public void twoFlightNumbersOnTheSameDay()
    {
        LocalDate date = LocalDate.of(2015, 10, 21);

        HashMap<FlightNumber, LocalDate> map = new HashMap<>();

        Registrar sut = new Registrar(map);

        map.put(new FlightNumber(new Designation("SW"), 1234), date);

        Assert.assertTrue(sut.isValidFlightNumber(new FlightNumber(new Designation("BA"), 4321), date));
    }

    @Test
    public void differentFlightNumbersDifferentDays()
    {
        HashMap<FlightNumber, LocalDate> map = new HashMap<>();

        Registrar sut = new Registrar(map);

        map.put(
            new FlightNumber(new Designation("SW"), 1234),
            LocalDate.of(1885, 1, 1)
        );

        Assert.assertTrue(sut.isValidFlightNumber(
            new FlightNumber(new Designation("BA"), 4321),
            LocalDate.of(1885, 9, 7)
        ));
    }
}
