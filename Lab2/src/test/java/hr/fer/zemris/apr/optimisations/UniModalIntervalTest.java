package hr.fer.zemris.apr.optimisations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static hr.fer.zemris.apr.math.vector.Vector.unit;

class UniModalIntervalTest {

    @Test
    void uniModalIntervalTest1() {
        var result = UniModalInterval.of(x -> Math.pow(x.get(0), 2) - 2, 1.0, unit(1, 100), 0);

        Assertions.assertEquals(-156, result.getFirst().get(0));
        Assertions.assertEquals(36, result.getSecond().get(0));
    }

    @Test
    void uniModalIntervalTest2() {
        var result = UniModalInterval.of(x -> Math.pow(x.get(0) - 4, 2), 1.0, unit(1, 0.0), 0);

        Assertions.assertEquals(2, result.getFirst().get(0));
        Assertions.assertEquals(8, result.getSecond().get(0));
    }

    @Test
    void uniModalIntervalTest3() {
        var result = UniModalInterval.of(x -> Math.pow(x.get(0), 2), 1.0, unit(1, 0.0), 0);

        Assertions.assertEquals(-1, result.getFirst().get(0));
        Assertions.assertEquals(1, result.getSecond().get(0));
    }

    @Test
    void uniModalIntervalTest4() {
        var result = UniModalInterval.of(x -> Math.pow(x.get(0), 2), 1.0, unit(2, 0.0), 0);

        Assertions.assertEquals(-1, result.getFirst().get(0));
        Assertions.assertEquals(1, result.getSecond().get(0));
    }
}
