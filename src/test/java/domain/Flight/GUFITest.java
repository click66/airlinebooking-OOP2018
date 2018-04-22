package domain.Flight;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class GUFITest
{
    @Test
    public void staticFactoryCreatesRandomGufi()
    {
        GUFI randomGUFI = GUFI.randomGUFI();

        GUFI anotherRadomGUFI = GUFI.randomGUFI();

        assertNotSame(randomGUFI, anotherRadomGUFI);
    }

    @Test
    public void twoGufisWithTheSameUuidAreEqual()
    {
        UUID uuid = UUID.randomUUID();

        GUFI one = new GUFI(uuid);
        GUFI another = new GUFI(uuid);

        assertEquals(one, another);
        assertTrue(one.equals(another));
    }
}
