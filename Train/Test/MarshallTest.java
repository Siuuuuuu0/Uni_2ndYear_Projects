import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarshallTest {

    Train train = new Train();
    Locomotive locomotive = new Locomotive(3,train);
    Bandit bandit = new Bandit(locomotive.next, false, train);
    Marshall marshall = new Marshall(locomotive, train);

    @Test
    void move() {
        marshall.move();
        assertEquals(locomotive.next, marshall.wagon);
    }

    @Test
    void scare() {
//        bandit.braquer();
//        assertFalse(bandit.roof);
//        assertFalse(bandit.collectedButins.isEmpty());
//        bandit.returns();
//        marshall.scare();
//        assertTrue(bandit.roof);
//        assertTrue(bandit.collectedButins.isEmpty());
    }
}