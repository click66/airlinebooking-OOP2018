package domain;

import java.util.UUID;

/**
 * Generic domain repository interface
 *
 * @param <T>
 */
public interface Repository<T extends Identifiable>
{
    /**
     * Store an object (can be new or updated existing)
     *
     * @param object The object to save
     */
    void store(T object);

    /**
     * Fetch an object by UUID.
     *
     * Will return null if no object exists with this UUID
     *
     * @param uuid Object identifier
     *
     * @return T|null
     */
    T fetch(UUID uuid);
}
