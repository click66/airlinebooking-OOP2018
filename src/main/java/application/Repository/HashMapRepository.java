package application.Repository;

import domain.Identifiable;
import domain.Repository;

import java.util.HashMap;
import java.util.UUID;

/**
 * Implementation of generic domain repository using HashMap
 *
 * @param <T>
 */
public class HashMapRepository<T extends Identifiable> implements Repository<T>
{
    protected HashMap<UUID, T> map = new HashMap<>();

    @Override
    public void store(T object)
    {
        map.put(object.getUuid(), object);
    }

    @Override
    public T fetch(UUID uuid)
    {
        return map.get(uuid);
    }
}
