package hr.fer.zemris.apr.math.matrix.inverse;

import hr.fer.zemris.apr.math.matrix.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InverseStrategiesTest {

    @Test
    public void testLUPDecompositinInverse() {
        var inverse = Matrix.parseString("25 5 1 | 64 8 1 | 144 12 1").inverse(InverseStrategies.LUP_DECOMPOSITION_INVERSE);

        assertEquals(0.047619047619047616, inverse.get(0, 0), 1e-7);
        assertEquals(-0.08333333333333333, inverse.get(0, 1), 1e-7);
        assertEquals(0.03571428571428572, inverse.get(0, 2), 1e-7);

        assertEquals(-0.9523809523809524, inverse.get(1, 0), 1e-7);
        assertEquals(1.4166666666666665, inverse.get(1, 1), 1e-7);
        assertEquals(-0.46428571428571436, inverse.get(1, 2), 1e-7);

        assertEquals(4.571428571428572, inverse.get(2, 0), 1e-7);
        assertEquals(-5.000000000000001, inverse.get(2, 1), 1e-7);
        assertEquals(1.4285714285714288, inverse.get(2, 2), 1e-7);
    }

    @Test
    public void testSubViewInverse() {
        var inverse = Matrix.parseString("25 5 1 | 64 8 1 | 144 12 1").inverse(InverseStrategies.SUBVIEW_INVERSE);

        assertEquals(0.047619047619047616, inverse.get(0, 0), 1e-7);
        assertEquals(-0.08333333333333333, inverse.get(0, 1), 1e-7);
        assertEquals(0.03571428571428572, inverse.get(0, 2), 1e-7);

        assertEquals(-0.9523809523809524, inverse.get(1, 0), 1e-7);
        assertEquals(1.4166666666666665, inverse.get(1, 1), 1e-7);
        assertEquals(-0.46428571428571436, inverse.get(1, 2), 1e-7);

        assertEquals(4.571428571428572, inverse.get(2, 0), 1e-7);
        assertEquals(-5.000000000000001, inverse.get(2, 1), 1e-7);
        assertEquals(1.4285714285714288, inverse.get(2, 2), 1e-7);
    }

}