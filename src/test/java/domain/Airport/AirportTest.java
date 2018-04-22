package domain.Airport;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class AirportTest
{
    @Test
    public void getName()
    {
        Name name = new Name("LON");

        Airport sut = new Airport(UUID.randomUUID(), name);

        assertSame(name, sut.getName());
    }

    @Test
    public void getUuid()
    {
        UUID uuid = UUID.randomUUID();

        Airport sut = new Airport(uuid, new Name("LON"));

        assertSame(uuid, sut.getUuid());
    }
}
