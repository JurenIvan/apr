package hr.fer.zemris.apr.optimisations;

import hr.fer.zemris.apr.optimisations.function.AprFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AprFunctionTest {

    @Test
    void getCounter() {
        AprFunction aprFunction = new AprFunction(x -> x);

        for (int i = 0; i < 10; i++) {
            assertEquals(i, aprFunction.getCounter());
            aprFunction.apply(2);
        }
        assertEquals(10, aprFunction.getCounter());
    }

    @Test
    void apply_identity() {
        AprFunction aprFunction = new AprFunction(x -> x);
        assertEquals(1, aprFunction.apply(1));
        assertEquals(2, aprFunction.apply(2));
        assertEquals(3, aprFunction.apply(3));
    }

    @Test
    void apply_quadratic() {
        AprFunction aprFunction = new AprFunction(x -> Math.pow(x, 4));
        assertEquals(1, aprFunction.apply(1));
        assertEquals(16, aprFunction.apply(2));
        assertEquals(81, aprFunction.apply(3));
        assertEquals(81, aprFunction.apply(3));
    }
}
