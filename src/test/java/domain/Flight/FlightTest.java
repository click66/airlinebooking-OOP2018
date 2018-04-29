package domain.Flight;

import domain.Airline.Airline;
import domain.Airport.Airport;
import domain.Flight.FlightNumber.FlightNumber;
import domain.Flight.FlightNumber.Registrar;
import domain.Flight.Class.Class;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

public class FlightTest
{
    private Mockery context = new Mockery();

    @Test
    public void getUuidReturnsGufiUuid()
    {
        UUID uuid = UUID.randomUUID();

        Airline airline = makeTestAirline();

        Flight sut = new Flight(
            new Registrar(new HashMap<>()),
            airline,
            new Route(makeTestAirport("LGW"), makeTestAirport("LAX")),
            new FlightNumber(airline.getDesignation(), 1234),
            new GUFI(uuid),
            LocalDateTime.now(),
            new HashMap<>()
        );

        assertSame(uuid, sut.getUuid());
    }

    @Test
    public void canAddAndCountSections()
    {
        Class mockClassOne = context.mock(Class.class, "classOne");
        Class mockClassTwo = context.mock(Class.class, "classTwo");

        context.checking(new Expectations()
        {{
            allowing(mockClassOne).getKey();
            will(returnValue("testFirst"));

            allowing(mockClassTwo).getKey();
            will(returnValue("testSecond"));
        }});

        Airline airline = makeTestAirline();

        Flight sut = new Flight(
            new Registrar(new HashMap<>()),
            airline,
            new Route(makeTestAirport("LGW"), makeTestAirport("LAX")),
            new FlightNumber(airline.getDesignation(), 1234),
            GUFI.randomGUFI(),
            LocalDateTime.now(),
            new HashMap<>()
        );

        Assert.assertEquals((Integer)0, sut.countSections());

        sut.addSection(mockClassOne, 1, 1);

        Assert.assertEquals((Integer)1, sut.countSections());

        sut.addSection(mockClassTwo, 1, 1);

        Assert.assertEquals((Integer)2, sut.countSections());
    }

    /**
     * Make a test airline
     *
     * @return Test airline
     */
    private Airline makeTestAirline()
    {
        domain.Airline.Name name = new domain.Airline.Name("SWEST");
        domain.Airline.Designation designation = new domain.Airline.Designation("SW");

        domain.Airline.Repository airlineRepository = context.mock(domain.Airline.Repository.class, "airlineRepository");
        context.checking(new Expectations()
        {{
            oneOf(airlineRepository).fetchByName(name);
            will(returnValue(null));

            oneOf(airlineRepository).fetchByDesignation(designation);
            will(returnValue(null));
        }});

        return new Airline(
            airlineRepository,
            UUID.randomUUID(),
            name,
            designation
        );
    }

    /**
     * Make a test airport
     *
     * @param name Airport name
     *
     * @return Test airport
     */
    private Airport makeTestAirport(String name)
    {
        domain.Airport.Name airportName = new domain.Airport.Name(name);

        domain.Airport.Repository airportRepository = context.mock(domain.Airport.Repository.class, "airportRepository" + name);
        context.checking(new Expectations()
        {{
            oneOf(airportRepository).fetchByName(airportName);
            will(returnValue(null));
        }});

        return new Airport(
            airportRepository,
            UUID.randomUUID(),
            airportName
        );
    }
}