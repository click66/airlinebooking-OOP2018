package domain;

import application.Repository.HashMapRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class HashMapRepositoryTest
{
    Mockery context = new Mockery();

    @Test
    public void canStoreAndFetchIdentifiableObject()
    {
        UUID uuid = UUID.randomUUID();

        HashMapRepository hashMapRepository = new HashMapRepository();

        Identifiable object = context.mock(Identifiable.class);

        context.checking(new Expectations()
        {{
            oneOf(object).getUuid();
            will(returnValue(uuid));
        }});

        hashMapRepository.store(object);

        assertSame(object, hashMapRepository.fetch(uuid));
    }
}
