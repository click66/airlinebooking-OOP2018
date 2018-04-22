package domain.Flight;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class UFIDTest
{
    @Test
    public void staticFactoryCreatesRandomUfid()
    {
        UFID randomUfid = UFID.randomUFID();

        UFID anotherRadomUfid = UFID.randomUFID();

        assertNotSame(randomUfid, anotherRadomUfid);
    }

    @Test
    public void twoUfidsWithTheSameUuidAreEqual()
    {
        UUID uuid = UUID.randomUUID();

        UFID one = new UFID(uuid);
        UFID another = new UFID(uuid);

        assertEquals(one, another);
        assertTrue(one.equals(another));
    }
}
