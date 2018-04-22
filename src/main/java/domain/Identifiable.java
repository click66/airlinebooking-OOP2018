package domain;

import java.util.UUID;

public interface Identifiable
{
    /**
     * Get this object's UUID
     *
     * @return UUID
     */
    UUID getUuid();
}
