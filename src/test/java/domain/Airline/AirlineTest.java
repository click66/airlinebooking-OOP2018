package domain.Airline;

import org.jmock.Mockery;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertSame;

public class AirlineTest
{
    @Test
    public void getName()
    {
        Name name = new Name("SWEST");

        Airline sut = new Airline(UUID.randomUUID(), name, new Designation("SW"));

        assertSame(name, sut.getName());
    }

    @Test
    public void getUuid()
    {
        UUID uuid = UUID.randomUUID();

        Airline sut = new Airline(uuid, new Name("SWEST"), new Designation("SW"));

        assertSame(uuid, sut.getUuid());
    }

    @Test
    public void getDesignation()
    {
        Designation designation = new Designation("SW");

        Airline sut = new Airline(UUID.randomUUID(), new Name("SWEST"), designation);

        assertSame(designation, sut.getDesignation());
    }
}
