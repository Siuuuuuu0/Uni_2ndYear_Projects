import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainTest {
    Train train = new Train();

    @Test
    void spawn() {
        assert(train.personnes.isEmpty());
        train.Spawn(1);
        assertFalse(train.personnes.isEmpty());
    }
}