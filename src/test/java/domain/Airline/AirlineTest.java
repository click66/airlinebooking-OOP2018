package domain.Airline;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertSame;

public class AirlineTest
{
    @Test
    public void getName()
    {
        Name name = new Name("SWEST");

        Airline sut = new Airline(UUID.randomUUID(), name);

        assertSame(name, sut.getName());
    }

    @Test
    public void getUuid()
    {
        UUID uuid = UUID.randomUUID();

        Airline sut = new Airline(uuid, new Name("SWEST"));

        assertSame(uuid, sut.getUuid());
    }
}
