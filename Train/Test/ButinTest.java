import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ButinTest {
    private Train train = new Train();
    private Locomotive locomotive = new Locomotive(5, train);

    private Butin butinBourse = new Butin(ButinType.Bourse, locomotive.next);
    private Butin butinBijoux = new Butin(ButinType.Bijoux, locomotive.next.next);
    private Butin butinMagot = new Butin(ButinType.Magot, locomotive.next.next.next);

    @Test
    public void testConstructors()
    {
        //Bourse
        assertFalse(butinBourse.isOnRoof);
        assert(butinBourse.Price > 0 && butinBourse.Price < 501);
        assertEquals(ButinType.Bourse ,butinBourse.type);

        //Bijoux
        assertFalse(butinBijoux.isOnRoof);
        assertEquals(500 ,butinBijoux.Price);
        assertEquals(ButinType.Bijoux ,butinBijoux.type);

        //Magot
        assertFalse(butinMagot.isOnRoof);
        assertEquals(1000 ,butinMagot.Price);
        assertEquals(ButinType.Magot ,butinMagot.type);
    }

}