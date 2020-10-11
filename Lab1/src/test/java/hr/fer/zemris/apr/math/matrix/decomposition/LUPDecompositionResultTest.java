package hr.fer.zemris.apr.math.matrix.decomposition;

import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class LUPDecompositionResultTest {

    @Test
    void getPMatrix() {
        var m1 = Matrix.parseString("1");
        var m2 = Matrix.parseString("2");
        var result = new LUPDecompositionResult(m1, m2);

        assertSame(m2, result.getPMatrix());
    }
}