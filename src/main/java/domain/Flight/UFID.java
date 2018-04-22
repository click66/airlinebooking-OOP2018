package domain.Flight;

import java.util.UUID;

public class UFID
{
    private UUID uuid;

    /**
     * "UFID" constructor
     *
     * @param uuid UUID from which to generate this UFID
     */
    public UFID(UUID uuid)
    {
        this.uuid = uuid;
    }

    /**
     * Static factory for random UFID
     *
     * @return UFID
     */
    public static UFID randomUFID()
    {
        return new UFID(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof UFID && uuid.equals(((UFID) obj).uuid);
    }

    @Override
    public int hashCode()
    {
        return uuid.hashCode();
    }
}
