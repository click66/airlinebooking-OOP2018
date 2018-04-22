package domain.Flight;

import java.util.UUID;

/**
 * "GUFI" value object
 * https://www.enri.go.jp/eiwac/2010/pdf/prete/EN2010-009.pdf
 */
public class GUFI
{
    private UUID uuid;

    /**
     * "GUFI" constructor
     *
     * @param uuid UUID from which to generate this GUFI
     */
    public GUFI(UUID uuid)
    {
        this.uuid = uuid;
    }

    /**
     * Static factory for random GUFI
     *
     * @return GUFI
     */
    public static GUFI randomUFID()
    {
        return new GUFI(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof GUFI && uuid.equals(((GUFI) obj).uuid);
    }

    @Override
    public int hashCode()
    {
        return uuid.hashCode();
    }
}
