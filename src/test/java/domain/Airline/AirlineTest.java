package domain.Airline;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertSame;

public class AirlineTest
{
    private Mockery context = new Mockery();

    private Repository repository;

    @Before
    public void setUp()
    {
        repository = context.mock(Repository.class);

        context.checking(new Expectations()
        {{
            oneOf(repository).fetchByName(with(any(Name.class)));
            will(returnValue(null));

            oneOf(repository).fetchByDesignation(with(any(Designation.class)));
            will(returnValue(null));
        }});
    }

    @Test
    public void getName()
    {
        Name name = new Name("SWEST");

        Airline sut = new Airline(repository, UUID.randomUUID(), name, new Designation("SW"));

        assertSame(name, sut.getName());
    }

    @Test
    public void getUuid()
    {
        UUID uuid = UUID.randomUUID();

        Airline sut = new Airline(repository, uuid, new Name("SWEST"), new Designation("SW"));

        assertSame(uuid, sut.getUuid());
    }

    @Test
    public void getDesignation()
    {
        Designation designation = new Designation("SW");

        Airline sut = new Airline(repository, UUID.randomUUID(), new Name("SWEST"), designation);

        assertSame(designation, sut.getDesignation());
    }
}
