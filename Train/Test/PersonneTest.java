import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonneTest {
    Train train = new Train();

    Locomotive locomotive = new Locomotive(3, train);

    Bandit bandit1 = new Bandit(locomotive, false, train);
    Bandit bandit2 = new Bandit(locomotive, false, train);
    Bandit bandit3 = new Bandit(locomotive, false, train);
    Bandit bandit4 = new Bandit(locomotive, false, train);

    @Test
    void climbs() {
        assertFalse(bandit1.roof);
        bandit1.climbs();
        assertTrue(bandit1.roof);
    }

    @Test
    void descends() {
        assertFalse(bandit2.roof);
        bandit2.descends();
        assertFalse(bandit2.roof);
        bandit2.climbs();
        assertTrue(bandit2.roof);
        bandit2.descends();
        assertFalse(bandit2.roof);
    }

    @Test
    void advances() {
        Wagon bandit3Pos = bandit3.wagon;
        bandit3.advances();
        assertEquals(bandit3Pos, bandit3.wagon.prev);
    }

    @Test
    void returns() {
        bandit4.climbs();
        Wagon bandit4Pos = bandit4.wagon;
        bandit4.advances();
        assertNotEquals(bandit4Pos, bandit4.wagon);
        bandit4.returns();
        assertEquals(bandit4Pos, bandit4.wagon);
    }
}