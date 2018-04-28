package domain.Airport;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class AirportTest
{
    private Mockery context = new Mockery();

    private Repository repository;

    @Before
    public void setUp()
    {
        repository = context.mock(Repository.class);

        context.checking(new Expectations()
        {{
            oneOf(repository).fetchByName(with(any(domain.Airport.Name.class)));
            will(returnValue(null));
        }});
    }

    @Test
    public void getName()
    {
        Name name = new Name("LON");

        Airport sut = new Airport(repository, UUID.randomUUID(), name);

        assertSame(name, sut.getName());
    }

    @Test
    public void getUuid()
    {
        UUID uuid = UUID.randomUUID();

        Airport sut = new Airport(repository, uuid, new Name("LON"));

        assertSame(uuid, sut.getUuid());
    }
}
