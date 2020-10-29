package hr.fer.zemris.apr.optimisations.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PairTest {

    @Test
    void testToString() {
        Assertions.assertEquals("Pair{first=1, second=2}", new Pair<>(1, 2).toString());
    }
}
