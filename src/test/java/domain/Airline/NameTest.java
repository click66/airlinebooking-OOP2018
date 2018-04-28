package domain.Airline;

import domain.Exception.InvalidArgument;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NameTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void canConstruct()
    {
        new Name("ABCDE");
    }

    @Test
    public void cannotBeLongerThanFiveLetters()
    {
        thrown.expect(InvalidArgument.class);

        new Name("ABCDEF");
    }

    @Test
    public void cannotBeEmpty()
    {
        thrown.expect(InvalidArgument.class);

        new Name("");
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
