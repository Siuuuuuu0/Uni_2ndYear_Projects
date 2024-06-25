import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WagonTest {

    private int wagonCount = 3;
    private Train train = new Train();

    private Locomotive locomotive = new Locomotive(wagonCount, train);


    @Test
    void otherTests() {
        Wagon tmp = locomotive;
        assert(tmp != null);
        for (int i = 0; i < wagonCount - 1; i++) // remaining is wagon's total count :-/
        {
            assert(!tmp.butins.isEmpty());
            tmp = tmp.next;
            assert(tmp != null);
        }
    }

    @Test
    void addButin() {
        int butinsSize = locomotive.next.butins.size();
        locomotive.next.addButin(new Butin(ButinType.Bijoux, locomotive.next));
        assertEquals(butinsSize+1, locomotive.next.butins.size());
        assertEquals(ButinType.Bijoux, locomotive.next.butins.getLast().type);
    }

    @Test
    void removeButin() {
        int butinsSize = locomotive.next.butins.size();
        locomotive.next.removeButin(locomotive.next.butins.size()-1);
        assertEquals(butinsSize-1, locomotive.next.butins.size());
    }
}