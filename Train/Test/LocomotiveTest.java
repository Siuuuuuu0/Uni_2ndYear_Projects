import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocomotiveTest {

    private Train train = new Train();

    private Locomotive locomotive = new Locomotive(3, train);

    @Test
    public void locomotiveConstructorTest()
    {
        assertNotNull(locomotive.butins);
        assertEquals(ButinType.Magot, locomotive.butins.getFirst().type);
        assertNull(locomotive.prev);
        assertNotNull(locomotive.next);
        assertNotNull(locomotive.next.next);
        assertNull(locomotive.next.next.next);
    }
}