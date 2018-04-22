package domain.Flight;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class GUFITest
{
    @Test
    public void staticFactoryCreatesRandomUfid()
    {
        GUFI randomGUFI = GUFI.randomUFID();

        GUFI anotherRadomGUFI = GUFI.randomUFID();

        assertNotSame(randomGUFI, anotherRadomGUFI);
    }

    @Test
    public void twoUfidsWithTheSameUuidAreEqual()
    {
        UUID uuid = UUID.randomUUID();

        GUFI one = new GUFI(uuid);
        GUFI another = new GUFI(uuid);

        assertEquals(one, another);
        assertTrue(one.equals(another));
    }
}
