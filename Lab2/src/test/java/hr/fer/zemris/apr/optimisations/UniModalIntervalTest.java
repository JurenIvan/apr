package hr.fer.zemris.apr.optimisations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UniModalIntervalTest {

    @Test
    void uniModalIntervalTest1() {
        var result = UniModalInterval.of(x -> Math.pow(x, 2) - 2, 1.0, 100);

        Assertions.assertEquals(-156, result.getFirst());
        Assertions.assertEquals(36, result.getSecond());
    }

    @Test
    void uniModalIntervalTest2() {
        var result = UniModalInterval.of(x -> Math.pow(x - 4, 2), 1.0, 0.0);

        Assertions.assertEquals(2, result.getFirst());
        Assertions.assertEquals(8, result.getSecond());
    }

    @Test
    void uniModalIntervalTest3() {
        var result = UniModalInterval.of(x -> Math.pow(x, 2), 1.0, 0.0);

        Assertions.assertEquals(-1, result.getFirst());
        Assertions.assertEquals(1, result.getSecond());
    }
}
