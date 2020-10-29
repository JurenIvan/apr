package hr.fer.zemris.apr.optimisations;

import org.junit.jupiter.api.Test;

import static hr.fer.zemris.apr.math.vector.Vector.unit;
import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UniModalIntervalTest {

    @Test
    void uniModalIntervalTest1() {
        var result = UniModalInterval.of(x -> pow(x.get(0), 2) - 2, 1.0, unit(1, 100), 0);

        assertEquals(-156, result.getFirst().get(0));
        assertEquals(36, result.getSecond().get(0));
    }

    @Test
    void uniModalIntervalTest2() {
        var result = UniModalInterval.of(x -> pow(x.get(0) - 4, 2), 1.0, unit(1, 0.0), 0);

        assertEquals(2, result.getFirst().get(0));
        assertEquals(8, result.getSecond().get(0));
    }

    @Test
    void uniModalIntervalTest3() {
        var result = UniModalInterval.of(x -> pow(x.get(0), 2), 1.0, unit(1, 0.0), 0);

        assertEquals(-1, result.getFirst().get(0));
        assertEquals(1, result.getSecond().get(0));
    }

    @Test
    void uniModalIntervalTest4() {
        var result = UniModalInterval.of(x -> pow(x.get(0), 2), 1.0, unit(2, 0.0), 0);

        assertEquals(-1, result.getFirst().get(0));
        assertEquals(1, result.getSecond().get(0));
    }

    @Test
    void uniModalIntervalTest5() {
        var result = UniModalInterval.of(x -> pow(x.get(0) - 4, 2), 1.0, unit(1, 1), 0);

        assertEquals(2, result.getFirst().get(0));
        assertEquals(5, result.getSecond().get(0));
    }
}
