package domain.Airport.Service;

import domain.Airport.Airport;
import domain.Airport.Name;
import domain.Airport.Repository.Repository;
import org.jmock.Mockery;
import org.jmock.*;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class UniqueNameCheckTest
{
    private Mockery context = new Mockery();

    private Repository airportRepository;

    @Before
    public void setUp()
    {
        airportRepository = context.mock(Repository.class);
    }

    @Test
    public void knowsIfAirportAlreadyExists()
    {
        Name existsName      = new Name("LHR");
        Name doesntExistName = new Name("LGW");

        UniqueNameCheck sut = new UniqueNameCheck(airportRepository);

        context.checking(new Expectations()
        {{
            oneOf(airportRepository).fetchByName(existsName);
            will(returnValue(new Airport(UUID.randomUUID(), existsName)));

            oneOf(airportRepository).fetchByName(doesntExistName);
            will(returnValue(null));
        }});

        assertTrue(sut.alreadyExistsByName(existsName));
        assertFalse(sut.alreadyExistsByName(doesntExistName));
    }
}