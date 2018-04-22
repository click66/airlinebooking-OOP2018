package domain;

import java.util.HashMap;
import java.util.UUID;

/**
 * Implementation of generic domain repository using HashMap
 *
 * @param <T>
 */
public class HashMapRepository<T extends Identifiable> implements Repository<T>
{
    private HashMap<UUID, T> map = new HashMap<>();

    /**
     * Store an object (can be new or updated existing)
     *
     * @param object The object to store save
     */
    public void store(T object)
    {
        map.put(object.getUuid(), object);
    }

    /**
     * Fetch an object by UUID.
     * Will return null if no identifiable object exists with this UUID
     *
     * @param uuid T identifier
     *
     * @return T|null
     */
    public T fetch(UUID uuid)
    {
        return map.get(uuid);
    }
}
