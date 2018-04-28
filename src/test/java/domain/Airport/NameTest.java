package domain.Airport;

import domain.Exception.InvalidArgument;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class NameTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void canConstruct()
    {
        new Name("ABC");
    }

    @Test
    public void cannotBeLongerThanThreeLetters()
    {
        thrown.expect(InvalidArgument.class);

        new Name("ABCD");
    }

    @Test
    public void cannotBeShorterThanThreeLetters()
    {
        thrown.expect(InvalidArgument.class);

        new Name("AB");
    }

    @Test
    public void cannotContainLowerCaseCharacters()
    {
        thrown.expect(InvalidArgument.class);

        new Name("AbC");
    }

    @Test
    public void equals()
    {
        Name sut = new Name("ABC");

        Name equal    = new Name("ABC");
        Name notEqual = new Name("CBA");

        assertTrue(sut.equals(equal));
        assertFalse(sut.equals(notEqual));
    }
}
