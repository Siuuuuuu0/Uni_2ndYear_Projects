import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BanditTest {

    Train train = new Train();

    Locomotive locomotive = new Locomotive(5, train);

    Bandit bandit1 = new Bandit(locomotive, true, train);
    Bandit bandit2 = new Bandit(locomotive.next, false, train);
    Bandit bandit3 = new Bandit(locomotive.next, true, train);
    Bandit bandit4 = new Bandit(locomotive.next.next.next, false, train);
    Bandit bandit5 = new Bandit(locomotive.next.next.next, false, train);

    @Test
    void braquer() {
        bandit1.braquer();
        assert(bandit1.collectedButins.isEmpty());
        bandit2.braquer();
        assert(!bandit2.collectedButins.isEmpty());
        bandit3.braquer();
        assert(bandit3.collectedButins.isEmpty());
        assertFalse(bandit2.collectedButins.getFirst().isOnRoof);
        bandit1.descends();
        bandit1.braquer();
        assert(!bandit1.collectedButins.isEmpty());
        assertFalse(bandit1.collectedButins.getFirst().isOnRoof);
    }

    @Test
    void lacher() {
        int butinCountWagon3 = locomotive.next.next.butins.size();
        bandit3.advances();
        bandit3.descends();
        bandit3.braquer();
        assertEquals(butinCountWagon3 - 1 ,locomotive.next.next.butins.size());
        bandit3.lacher();
        assert(bandit3.collectedButins.isEmpty());
        assertEquals(butinCountWagon3 ,locomotive.next.next.butins.size());
        assertFalse(locomotive.next.next.butins.getLast().isOnRoof);
        bandit3.braquer();
        bandit3.climbs();
        bandit3.lacher();
        assert(bandit3.collectedButins.isEmpty());
        assertTrue(locomotive.next.next.butins.getLast().isOnRoof);
    }

    @Test
    void shoot() {
        bandit4.NB_BALLES = 10;
        bandit5.NB_BALLES = 10;
        Wagon wag = locomotive.next.next.next;
        if(wag.butins.size()<2) wag.addButin(new Butin(ButinType.Bijoux, wag));
        int butinsCntWagon4 = wag.butins.size();
        bandit4.braquer();
        assertNotNull(bandit4.collectedButins);
        bandit4.climbs();
        assertNotEquals(butinsCntWagon4,wag.butins.size());
//        bandit5.shoot(Direction.Up);
//        assertEquals(butinsCntWagon4,wag.butins.size());
//        assert(bandit4.collectedButins.isEmpty());
//
//
//        bandit5.braquer();
//        bandit4.advances();
//        bandit4.descends();
//        bandit4.braquer();
//        assert(!bandit4.collectedButins.isEmpty());
//        assert(!bandit5.collectedButins.isEmpty());
//        assertEquals(butinsCntWagon4 - 2,wag.butins.size());
//        bandit5.shoot(Direction.Backwards);
//        assert(bandit4.collectedButins.isEmpty());
//        bandit4.shoot(Direction.Forward);
//        assert(bandit5.collectedButins.isEmpty());
//        assertEquals(butinsCntWagon4,wag.butins.size());
//
//        bandit5.climbs();
//        bandit4.returns();
//        bandit4.braquer();
//        assertEquals(butinsCntWagon4 - 1,wag.butins.size());
//        bandit5.shoot(Direction.Down);
//        assertEquals(butinsCntWagon4,wag.butins.size());
    }
}